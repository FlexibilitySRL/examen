package ar.com.plug.examen.domain.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.ProductDTO;
import ar.com.plug.examen.domain.model.SellerDTO;
import ar.com.plug.examen.entities.Product;
import ar.com.plug.examen.entities.Seller;

@Component
public class SellerMapper {
	
	public SellerDTO sellerToSellerDTO(Seller seller) {
		SellerDTO s = new SellerDTO();
		s.setId(seller.getId());
		s.setUser(seller.getUser());
		return s;
	}
	
	public Seller sellerDTOtoSeller(SellerDTO dto) {
		Seller s = new Seller();
		s.setId(dto.getId());
		s.setUser(dto.getUser());
		return s;
	}
	
	public List<SellerDTO> sellerToListSellerDTO(List<Seller> sellers) {
		return sellers.stream().map(this::sellerToSellerDTO).collect(Collectors.toList());
	}

	public List<Seller> sellerDTOToListSeller(List<SellerDTO> sellersDTO) {
		return sellersDTO.stream().map(this::sellerDTOtoSeller).collect(Collectors.toList());
	}

}
