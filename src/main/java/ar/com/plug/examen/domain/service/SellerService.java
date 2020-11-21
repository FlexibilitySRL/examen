package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

public interface SellerService {

	List<SellerApi> listAll();

	SellerApi findById(long id) throws NotFoundException;

	List<SellerApi> findByName(String name);

	SellerApi save(SellerApi seller) throws BadRequestException;

	void deleteById(long id) throws NotFoundException, BadRequestException;

	SellerApi update(SellerApi sellerApi) throws NotFoundException, BadRequestException;

}