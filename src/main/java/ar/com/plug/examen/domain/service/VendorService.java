package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Vendor;

import java.util.List;

public interface VendorService {

    List<Vendor> getAll();

    Vendor save(Vendor vendor);

    Vendor findById(long id);

    void deleteById(long id);
}
