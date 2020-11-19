package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.ProductApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

public interface ProductService {

	List<ProductApi> listAll();

	ProductApi findById(Long id) throws NotFoundException;

	List<ProductApi> findByName(String name) throws NotFoundException;

	ProductApi save(ProductApi product) throws BadRequestException;

	void deleteById(Long id) throws NotFoundException;

	ProductApi update(ProductApi product) throws NotFoundException, BadRequestException;

}