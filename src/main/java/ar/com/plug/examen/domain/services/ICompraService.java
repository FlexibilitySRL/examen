package ar.com.plug.examen.domain.services;

import ar.com.plug.examen.domain.dtos.CompraDTO;

import java.util.List;

public interface ICompraService {

    public List<CompraDTO> getAll();
    public CompraDTO save(CompraDTO compra) throws Exception;
    public void delete(CompraDTO compra);
}
