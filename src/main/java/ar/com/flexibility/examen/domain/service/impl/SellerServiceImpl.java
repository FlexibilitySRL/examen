package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import ar.com.flexibility.examen.domain.service.SellerService;
import ar.com.flexibility.examen.exception.SellerException;
import ar.com.flexibility.examen.exception.ProductException;

@Service
public class SellerServiceImpl implements SellerService
{

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerApi add(SellerApi sellerApi) throws SellerException
    {
        if (sellerApi.getNombre() == null)
        {
            throw new SellerException(SellerException.E001,
                    "Name cannot be null");
        }
        if (sellerApi.getApellido() == null)
        {
            throw new SellerException(SellerException.E001,
                    "Surname cannot be null");
        }
        Seller seller = new Seller(sellerApi);

        try
        {
            sellerRepository.saveAndFlush(seller);
            return new SellerApi(seller);
        }
        catch (Exception e)
        {
            throw new SellerException(SellerException.E001, e.getMessage());
        }

    };

    @Override
    public SellerApi update(SellerApi sellerApi) throws SellerException
    {

        Seller seller = new Seller(sellerApi);

        try
        {
            seller = sellerRepository.findOne(seller.getId());
        }
        catch (Exception e)
        {
            throw new SellerException(SellerException.E001,
                    "Seller not found.");
        }

        if (seller == null)
        {
            throw new SellerException(SellerException.E001,
                    "Seller not found.");
        }
        try
        {
            seller.setName(sellerApi.getNombre());
            seller.setSurname(sellerApi.getApellido());

            sellerRepository.saveAndFlush(seller);

            return new SellerApi(seller);
        }
        catch (Exception e)
        {
            throw new SellerException(SellerException.E001,
                    "Error updating seller.");
        }

    };

    @Override
    public SellerApi remove(Long id) throws SellerException
    {
        Seller seller;

        try
        {
            seller = sellerRepository.findOne(id);
            if (seller == null)
            {
                throw new SellerException(SellerException.E001,
                        "Seller not found.");
            }
            sellerRepository.delete(seller);
            return new SellerApi(seller);
        }
        catch (Exception e)
        {
            throw new SellerException(SellerException.E001, e.getMessage());
        }
    }

    @Override
    public SellerApi get(Long id) throws SellerException
    {
        Seller seller;
        try
        {
            seller = sellerRepository.findOne(id);
            if (seller != null)
            {
                return new SellerApi(seller);
            }
            else
            {
                throw new SellerException(SellerException.E001,
                        "Seller not found.");
            }

        }
        catch (Exception e)
        {
            throw new SellerException(SellerException.E001, e.getMessage());
        }

    }

    @Override
    public List<SellerApi> getAll(Long page, Long pageSize)
            throws SellerException
    {

        if (page == null)
        {
            page = 0L;
        }
        if (pageSize == null)
        {
            pageSize = 10L;
        }
        if (page < 0L)
        {
            throw new SellerException(SellerException.E002,
                    "Page must be greater than or equal to zero.");
        }
        if (pageSize <= 0L)
        {
            throw new SellerException(SellerException.E002,
                    "Page size must be greater than zero.");
        }
        Page<Seller> sellers;
        try
        {
            sellers = sellerRepository.findAll(new PageRequest(page.intValue(),
                    pageSize.intValue()));
        }
        catch (Exception e)
        {
            throw new SellerException(SellerException.E002,
                    "Solicited info was not found.");
        }

        try
        {
            List<SellerApi> sellerApiList = new ArrayList<SellerApi>();

            for (Seller seller : sellers)
            {
                sellerApiList.add(new SellerApi(seller));
            }
            return sellerApiList;
        }
        catch (Exception e)
        {
            throw new SellerException(SellerException.E001, e.getMessage());
        }

    }

}
