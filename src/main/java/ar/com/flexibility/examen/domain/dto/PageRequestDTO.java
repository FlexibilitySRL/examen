package ar.com.flexibility.examen.domain.dto;

import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageRequestDTO {
	@JsonProperty
	private int pageNumber;
	
	@JsonProperty
	private int pageSize;
	
	public PageRequestDTO(int pageNumber, int pageSize) {
		
	}
	
	protected PageRequestDTO() {
		
	}
	
	
	
	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @post Convierte a 'Page Request'
	 */
	public PageRequest toPageRequest() {
		return new PageRequest(this.pageNumber, this.pageSize);
	}
}
