package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.Transac;
import ar.com.plug.examen.domain.repositories.ITransacRepository;
import ar.com.plug.examen.domain.service.ITransacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransacServiceImpl implements ITransacService {
    @Autowired
    private ITransacRepository repository;

    @Override
    public Page<Transac> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Transac getById(Long id) {
        if (repository.existsById(id))
            return repository.findById(id).get();

        return null;
    }

    @Override
    public Boolean approve(Long transacId) {
        if (repository.existsById(transacId)){
            Transac oTransac = repository.findById(transacId).get();
            oTransac.setApproved(true);

            repository.save(oTransac);

            return true;
        }

        return null;
    }
}
