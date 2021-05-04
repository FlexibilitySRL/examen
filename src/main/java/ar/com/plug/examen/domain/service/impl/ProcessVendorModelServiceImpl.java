package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.datasource.model.Vendor;
import ar.com.plug.examen.datasource.repo.VendorRepo;
import org.springframework.stereotype.Service;

@Service
public class ProcessVendorModelServiceImpl extends AbstractBaseModelService<VendorRepo, Vendor> {

    public ProcessVendorModelServiceImpl(VendorRepo repo) {
        super(repo);
    }

    @Override
    Class<Vendor> getDomainClass() {
        return Vendor.class;
    }
}
