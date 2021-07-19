package ar.com.plug.examen.domain.builderPattern;

import ar.com.plug.examen.domain.model.ClientDTO;

public class ClientDTOBuilder {

	private ClientDTO clientDto; 
	
	public ClientDTOBuilder() {
		this.reset();
	}
	
	public void reset() {
		this.clientDto = new ClientDTO();
	}
	
	public ClientDTOBuilder withID(Long id) {
		this.clientDto.setId(id);
		return this;
	}
	
	public ClientDTOBuilder withFirstname(String name) {
		this.clientDto.setFirstname(name);
		return this;
	}
	
	public ClientDTOBuilder withLastname(String name) {
		this.clientDto.setLastname(name);
		return this;
	}
	
	public ClientDTOBuilder withEmail(String email) {
		this.clientDto.setEmail(email);
		return this;
	}
	
	public ClientDTO build() {
		return new ClientDTO(this.clientDto.getId(), this.clientDto.getFirstname(), this.clientDto.getLastname(), this.clientDto.getEmail());
	}
	
	

}
