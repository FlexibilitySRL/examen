package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.dtoResponse.ClientResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ErrorDTO;
import ar.com.plug.examen.app.dtoResponse.ListClientResponseDTO;
import ar.com.plug.examen.app.entity.ClientEntity;
import ar.com.plug.examen.app.enumeration.ErrorCodeEnumeration;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.service.IClientRequestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ClientRequestServiceImpl implements IClientRequestService {

    private final ClientRepository clientRepository;
    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    public ClientRequestServiceImpl (final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = false)
    public ClientResponseDTO create(ClientDTO dto) throws IOException {
        try {
            log.info("client - create - " + dto.toString());

            ClientEntity newClient = ClientEntity.builder()
                    .documentNumber(dto.getPersonalData().getDocument_number())
                    .firstName(dto.getPersonalData().getFirst_name())
                    .lastName(dto.getPersonalData().getLast_name())
                    .phoneNumber(dto.getPhoneNumber())
                    .email(dto.getEmail())
                    .build();

            newClient = clientRepository.save(newClient);

            if (newClient != null) {
                log.info("client - create - SUCCESS - client_id: " + newClient.getClientId());
                return ClientResponseDTO.builder()
                        .clientId(newClient.getClientId())
                        .status(HttpStatus.OK.toString())
                        .code("CREATED").build();
            }

            log.error("client - create - ERROR");
            return ClientResponseDTO.builder()
                    .clientId(newClient.getClientId() != null ? newClient.getClientId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("client - create - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public ClientResponseDTO update(ClientDTO dto) throws IOException {
        try {
            log.info("client - update - " + dto.toString());
            ClientEntity updateClient = clientRepository.findByDocumentNumber(dto.getPersonalData().getDocument_number());

            if (updateClient != null) {
                log.info("client - update - CLIENT EXIST");
                updateClient.setFirstName(dto.getPersonalData().getFirst_name());
                updateClient.setLastName(dto.getPersonalData().getLast_name());
                updateClient.setPhoneNumber(dto.getPhoneNumber());
                updateClient.setEmail(dto.getEmail());

                clientRepository.save(updateClient);

                log.info("client - update - SUCCESS - client_id: " + updateClient.getClientId());
                return ClientResponseDTO.builder()
                        .clientId(updateClient.getClientId())
                        .status(HttpStatus.OK.toString())
                        .code("UPDATED").build();
            }

            log.error("client - update - CLIENT NOT EXIST");
            return ClientResponseDTO.builder()
                    .clientId(updateClient.getClientId() != null ? updateClient.getClientId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("client - update - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public ClientResponseDTO delete(Integer document) throws IOException {
        try {
            log.info("client - delete - " + document);
            ClientEntity deleteClient = clientRepository.findByDocumentNumber(document);

            if (deleteClient != null) {
                log.info("client - delete - CLIENT EXIST");
                clientRepository.delete(deleteClient);

                log.info("client - delete - SUCCESS - client_id: " + deleteClient.getClientId());
                return ClientResponseDTO.builder()
                        .clientId(deleteClient.getClientId())
                        .status(HttpStatus.OK.toString())
                        .code("DELETED").build();
            }

            log.error("client - delete - CLIENT NOT EXIST");
            return ClientResponseDTO.builder()
                    .clientId(deleteClient.getClientId() != null ? deleteClient.getClientId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("client - update - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    public ListClientResponseDTO list() throws IOException {
        try {
            log.info("client - list");
            List<ClientEntity> listClient = clientRepository.findAll();
            ListClientResponseDTO listClients = new ListClientResponseDTO();
            listClients.setClients(new ArrayList<>());

            if (listClient != null) {
                log.info("client - list - LIST SIZE: " + listClient.size());
                for (ClientEntity clientEntity : listClient) {
                    listClients.getClients().add(new Client(
                            clientEntity.getClientId(),
                            clientEntity.getDocumentNumber(),
                            clientEntity.getFirstName(),
                            clientEntity.getLastName(),
                            clientEntity.getPhoneNumber(),
                            clientEntity.getEmail()
                            ));
                }

                return listClients;
            }
            log.error("client - list - LIST NOT EXIST");
            return null;
        } catch (Exception e) {
            log.error("client - list - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }
}
