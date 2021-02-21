package ar.com.plug.examen.domain.services;

import ar.com.plug.examen.domain.dtos.VendedorDTO;

import java.util.List;

public interface IVendedorService {

    public List<VendedorDTO> getAll();
    public VendedorDTO save(VendedorDTO vendedorDTO);
    public void delete(VendedorDTO vendedorDTO);
}
