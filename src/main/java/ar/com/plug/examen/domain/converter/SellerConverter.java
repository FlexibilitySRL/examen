package ar.com.plug.examen.domain.converter;

import ar.com.plug.examen.app.api.ClientDTO;
import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Seller;
import org.springframework.stereotype.Component;


@Component
public class SellerConverter
{
	public Seller toModel(SellerDTO sellerDTO) {
		return Seller.builder()
				.id(sellerDTO.getId())
				.documentId(sellerDTO.getDocumentId())
				.documentType(sellerDTO.getDocumentType())
				.email(sellerDTO.getEmail())
				.firstName(sellerDTO.getFirstName())
				.lastName(sellerDTO.getLastName())
				.build();
	}

	public SellerDTO toDTO(Seller seller) {
		return SellerDTO.builder()
				.documentId(seller.getDocumentId())
				.documentType(seller.getDocumentType())
				.email(seller.getEmail())
				.firstName(seller.getFirstName())
				.lastName(seller.getLastName())
				.id(seller.getId())
				.build();
	}
}
