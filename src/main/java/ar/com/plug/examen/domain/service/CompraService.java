package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Compra;
import ar.com.plug.examen.exception.MicroserviceErrorException;

import java.util.List;

public interface CompraService {

    List<Compra> findAll() throws MicroserviceErrorException;

    Compra findCompraById(Integer id);

    Compra aprobarCompra(Integer id) throws MicroserviceErrorException;

    Compra saveCompra(Compra compra) throws MicroserviceErrorException;

    Compra updateCompra(Compra compra, Integer id) throws MicroserviceErrorException;

    void deleteCompraById(Integer id) throws MicroserviceErrorException;
}