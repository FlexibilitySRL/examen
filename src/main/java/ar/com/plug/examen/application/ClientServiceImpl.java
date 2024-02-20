package ar.com.plug.examen.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.Client;
import ar.com.plug.examen.domain.service.ServiceDomain;
import ar.com.plug.examen.infrastructure.db.entity.ClientEntity;
import ar.com.plug.examen.infrastructure.db.repository.ClientEntityRepository;
import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import ar.com.plug.examen.shared.config.MenssageResponse;
import ar.com.plug.examen.shared.exception.BadRequestException;
import ar.com.plug.examen.shared.exception.ConflictException;
import ar.com.plug.examen.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ServiceDomain<Client> {
    private final ClientEntityRepository clientEntityRepository;
    private final MenssageResponse menssageResponse;

    @Override
    public Client findById(String id) {
        log.info("Inicia busqueda de cliente con id:{}", id);
        return clientEntityRepository.findById(id).orElseThrow(() -> {
            log.error("No existe el cliente con id: {}", id);
            return new NotFoundException(ResponseDto.builder()
                    .code(MenssageResponse.C403)
                    .message(menssageResponse.getMessages().get(MenssageResponse.C403).concat(id))
                    .build());
        }).toClient();
    }

    @Override
    public List<Client> findAllByFilter(Client client) {
        log.info("Inicia listado de cliente con filtro:{}", client);
        List<ClientEntity> lista = (List<ClientEntity>) (clientEntityRepository.findAll());
        return lista.stream()
                .filter(clientEntity -> (client.getName().isEmpty() ? false
                        : Objects.equals(client.getName(), clientEntity.getName()))
                        || (client.getLastName().isEmpty()
                                ? false
                                : Objects.equals(client.getLastName(), clientEntity.getLastName()))
                        || (client.getDocNumber().isEmpty()
                                ? false
                                : Objects.equals(client.getDocNumber(),
                                        clientEntity.getDocNumber()))
                        || (client.getEmail().isEmpty()
                                ? false
                                : Objects.equals(client.getEmail(),
                                        clientEntity.getEmail())))
                .map(ClientEntity::toClient)
                .collect(Collectors.toList());
    }

    @Override
    public Client create(Client client) {
        log.info("Inicia la creacion de cliente:{}", client);
        this.validateIfExistsByEmail(client.getEmail());
        return clientEntityRepository.save(new ClientEntity(client)).toClient();
    }

    @Override
    public Client upDate(Client client) {
        log.info("Inicia actualizacion de cliente:{}", client);
        if (Objects.isNull(client.getId()) || client.getId().isEmpty()) {
            throw new BadRequestException(ResponseDto.builder()
                    .code(MenssageResponse.C405)
                    .message(menssageResponse.getMessages().get(MenssageResponse.C405))
                    .build());
        }
        ClientEntity clientEntity = clientEntityRepository.findById(client.getId())
                .orElseThrow(() -> {
                    log.error("No existe el cliente con id: {}", client.getId());
                    return new NotFoundException(ResponseDto.builder()
                            .code(MenssageResponse.C403)
                            .message(menssageResponse.getMessages().get(MenssageResponse.C403).concat(client.getId()))
                            .build());
                });
        if (!Objects.equals(client.getEmail(), clientEntity.getEmail()))
            this.validateIfExistsByEmail(client.getEmail());
        return clientEntityRepository.save(clientEntity.upDate(client)).toClient();

    }

    @Override
    public void remove(String id) {
        log.info("Inicia eliminacion del cliente con id:{}", id);
        ClientEntity clientEntity = clientEntityRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No existe el cliente con id: {}", id);
                    return new NotFoundException(ResponseDto.builder()
                            .code(MenssageResponse.C403)
                            .message(menssageResponse.getMessages().get(MenssageResponse.C403).concat(id))
                            .build());
                });
        clientEntityRepository.delete(clientEntity);
    }

    private void validateIfExistsByEmail(String email) {
        if (clientEntityRepository.findByEmail(email).isPresent()) {
            log.error("Ya existe el cliente con email: {}", email);
            throw new ConflictException(ResponseDto.builder()
                    .code(MenssageResponse.C404)
                    .message(menssageResponse.getMessages().get(MenssageResponse.C404).concat(email))
                    .build());
        }
    }

}
