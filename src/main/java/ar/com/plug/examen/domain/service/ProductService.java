package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

public interface ProductService {

	List<ProductApi> listAll();

	ProductApi findById(long id) throws NotFoundException;

	List<ProductApi> findByName(String name);

	ProductApi save(ProductApi product) throws BadRequestException;

	void deleteById(long id) throws NotFoundException, BadRequestException;

	ProductApi update(ProductApi product) throws NotFoundException, BadRequestException;

}