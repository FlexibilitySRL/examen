package ar.com.plug.examen.domain.converter;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.domain.model.Client;
import org.springframework.stereotype.Component;


@Component
public class ClientConverter
{
	public Client toModel(ClientDTO clientDTO) {
		return Client.builder()
				.id(clientDTO.getId())
				.documentId(clientDTO.getDocumentId())
				.documentType(clientDTO.getDocumentType())
				.email(clientDTO.getEmail())
				.firstName(clientDTO.getFirstName())
				.lastName(clientDTO.getLastName())
				.build();
	}

	public ClientDTO toDTO(Client client) {
		return ClientDTO.builder()
				.documentId(client.getDocumentId())
				.documentType(client.getDocumentType())
				.email(client.getEmail())
				.firstName(client.getFirstName())
				.lastName(client.getLastName())
				.id(client.getId())
				.build();
	}
}
