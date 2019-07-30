package ar.com.flexibility.examen.domain.service.impl;


import ar.com.flexibility.examen.domain.model.Purcharse;
import ar.com.flexibility.examen.domain.repository.PurcharseRepository;
import ar.com.flexibility.examen.domain.service.PurcharseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PurcharseServiceImpl implements PurcharseService {
    private PurcharseRepository purcharseRepository;

    public PurcharseServiceImpl(PurcharseRepository purcharseRepository) {
        this.purcharseRepository = purcharseRepository;
    }

    @Override
    public Purcharse addPurcharse(Purcharse purcharse) {
        return purcharseRepository.save(purcharse);
    }

    @Override
    public Purcharse updatePurcharse(Purcharse purcharse) {
        return purcharseRepository.save(purcharse);
    }

    @Override
    public Purcharse findById(Long id) {
        return purcharseRepository.findOne(id);
    }

    @Override
    public void deletePurcharse(Long id) {
        purcharseRepository.delete(id);
    }

    @Override
    public List<Purcharse> findAll() {
        return (List<Purcharse>) purcharseRepository.findAll();
    }
}
