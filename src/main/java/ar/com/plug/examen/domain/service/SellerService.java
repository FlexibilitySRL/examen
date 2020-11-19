package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.app.api.SellerApi;
import ar.com.plug.examen.domain.exception.BadRequestException;
import ar.com.plug.examen.domain.exception.NotFoundException;

public interface SellerService {

	List<SellerApi> listAll();

	SellerApi findById(Long id) throws NotFoundException;

	List<SellerApi> findByName(String name) throws NotFoundException;

	SellerApi save(SellerApi seller) throws BadRequestException;

	void deleteById(Long id) throws NotFoundException;

	SellerApi update(SellerApi sellerApi) throws NotFoundException, BadRequestException;

}