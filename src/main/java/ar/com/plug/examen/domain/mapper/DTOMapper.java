package ar.com.plug.examen.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.PurchaseTransaction;
import ar.com.plug.examen.domain.model.Vendor;


@Mapper(componentModel = "spring")
public interface DTOMapper {

	DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

	public Product from(ar.com.plug.generated.model.Product productDTO);

	public ar.com.plug.generated.model.Product from(Product productEntity);

	public Customer from(ar.com.plug.generated.model.Customer customerDTO);

	public ar.com.plug.generated.model.Customer from(Customer customerEntity);

	public Vendor from(ar.com.plug.generated.model.Vendor vendorDTO);

	public ar.com.plug.generated.model.Vendor from(Vendor vendorEntity);

	public PurchaseTransaction from(ar.com.plug.generated.model.PurchaseTransaction purchaseTransactionDTO);

	public ar.com.plug.generated.model.PurchaseTransaction from(PurchaseTransaction purchaseTransactionEntity);

}