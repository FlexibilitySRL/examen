package ar.com.plug.examen.infrastructure.rest.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.domain.Client;
import ar.com.plug.examen.domain.service.ServiceDomain;
import ar.com.plug.examen.infrastructure.rest.dto.ClientRequestDto;
import ar.com.plug.examen.infrastructure.rest.dto.ClientResponseDto;
import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import ar.com.plug.examen.shared.config.MenssageResponse;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(path = ClientController.PATH)
public class ClientController {

    private final ServiceDomain<Client> clientService;
    private final MenssageResponse menssageResponse;
    public final static String PATH = "/client";
    public final static String CLIENT_BY_FILTER = "all";

    @GetMapping
    public ResponseEntity<ClientResponseDto> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(new ClientResponseDto(clientService.findById(id)), HttpStatus.OK);
    }

    @GetMapping(path = CLIENT_BY_FILTER)
    public ResponseEntity<List<ClientResponseDto>> findAllByFilter(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String lastName,
            @RequestParam(required = false, defaultValue = "") String docNumber,
            @RequestParam(required = false, defaultValue = "") String email) {
        return new ResponseEntity<>(clientService.findAllByFilter(Client.builder()
                .name(name)
                .lastName(lastName)
                .docNumber(docNumber)
                .email(email)
                .build()).stream().map(ClientResponseDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return new ResponseEntity<>(new ClientResponseDto(clientService.create(clientRequestDto.toClient())),
                HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ClientResponseDto> upDate(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return new ResponseEntity<>(new ClientResponseDto(clientService.upDate(clientRequestDto.toClient())),
                HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> remove(@RequestParam Integer id) {
        clientService.remove(id);
        return new ResponseEntity<>(ResponseDto.builder()
                .code(MenssageResponse.OK)
                .message(menssageResponse.getMessages().get(MenssageResponse.OK))
                .build(), HttpStatus.OK);
    }

}
