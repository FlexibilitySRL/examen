package ar.com.plug.examen.app.rest.services.impl;


import ar.com.plug.examen.app.api.PageDto;
import ar.com.plug.examen.app.api.SellerDto;
import ar.com.plug.examen.app.rest.model.Seller;
import ar.com.plug.examen.app.rest.repositories.SellerRepository;
import ar.com.plug.examen.app.rest.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService
{
    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository)
    {
        this.sellerRepository = sellerRepository;
    }


    @Override
    public PageDto<Seller> getAllSellers(int pageNumber, int pageSize)
    {
        return new PageDto<>(
                this.sellerRepository.findAll(PageRequest.of(pageNumber, pageSize))
        );
    }

    @Override
    public Seller getSellerById(Long id)
    {
        if(Objects.isNull(id)) {
            throw new NoSuchElementException("El id del cliente no puede ser nulo.");
        }
        Optional<Seller> optionalSeller = this.sellerRepository.findById(id);
        if(optionalSeller.isPresent()) {
            return optionalSeller.get();
        } else {
            throw new NoSuchElementException("El id del cliente no se encuentra en la base de datos.");
        }
    }

    @Override
    public int saveSeller(SellerDto sellerDto) throws ValidationException
    {
        if(Objects.isNull(sellerDto)) {
            throw new ValidationException("Los datos para la creación de un cliente no pueden ser nulos.");
        }
        Seller seller;
        if(sellerDto.getId()!=null){
            //Edit
            seller = this.sellerRepository.findById(sellerDto.getId()).orElseThrow(()->new NoSuchElementException("Seller With Id " + sellerDto.getId() + " doesn't exist"));
            seller.setName(sellerDto.getName());
            seller.setActive(sellerDto.getActive());
        }else{
            seller = new Seller(sellerDto.getName(),sellerDto.getActive());
        }

        Seller sellerSaved =  this.sellerRepository.save(seller);
        return sellerSaved.getName().hashCode();
    }

    @Override
    public Boolean inactivateSeller(Long id) throws ValidationException
    {
        if(Objects.isNull(id)) {
            throw new ValidationException("Los datos para la actualización de un cliente no pueden ser nulos.");
        }

        Seller sellerFromDatabase = this.sellerRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("El cliente con el id " + id + " no existe.")
                );
        sellerFromDatabase.setActive(Boolean.FALSE);
        this.sellerRepository.save(sellerFromDatabase);
         return Boolean.TRUE;
    }
}