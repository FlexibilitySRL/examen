package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.app.api.PurchaseApi;
import ar.com.flexibility.examen.app.api.StatusApi;
import ar.com.flexibility.examen.domain.model.Client;
import ar.com.flexibility.examen.domain.model.Purchase;
import ar.com.flexibility.examen.domain.repository.PurchaseRepository;
import ar.com.flexibility.examen.domain.service.PurchaseService;
import ar.com.flexibility.examen.exception.ClientException;
import ar.com.flexibility.examen.exception.PurchaseException;

@Service
public class PurchaseServiceImpl implements PurchaseService
{
    @Autowired
    PurchaseRepository purchaseRepository;

    @Override
    public PurchaseApi add(PurchaseApi purchaseApi) throws PurchaseException
    {
        purchaseApi.notApproved();

        if (purchaseApi.getClientId() == null)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Client id cannot be null.");
        }
        if (Long.parseLong(purchaseApi.getClientId()) < 0L)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Client id must be greater than zero.");
        }

        if (purchaseApi.getSellerId() == null)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Seller id cannot be null.");
        }
        if (Long.parseLong(purchaseApi.getSellerId()) < 0L)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Seller id must be greater than zero.");
        }

        if (purchaseApi.getItems() == null)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Items cannot be null.");
        }

        Purchase purchase = new Purchase(purchaseApi);

        try
        {
            purchaseRepository.saveAndFlush(purchase);
            return new PurchaseApi(purchase);
        }
        catch (Exception e)
        {
            throw new PurchaseException(PurchaseException.E001, e.getMessage());
        }
    }

    @Override
    public PurchaseApi update(PurchaseApi purchaseApi) throws PurchaseException
    {
  
        if (Long.parseLong(purchaseApi.getClientId()) < 0L)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Client id must be greater than zero.");
        }

        if (Long.parseLong(purchaseApi.getSellerId()) < 0L)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Seller id must be greater than zero.");
        }

        Purchase purchase = new Purchase(purchaseApi);

        try
        {
            purchase = purchaseRepository.findOne(purchase.getId());
        }
        catch (Exception e)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Purchase not found.");
        }

        if (purchase == null)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Purchase not found.");
        }
        try
        {
            purchase.setSellerId(Long.parseLong(purchaseApi.getSellerId()));
            purchase.setClientId(Long.parseLong(purchaseApi.getClientId()));
            purchase.setApproved(purchaseApi.isApproved());
            purchase.setItemsFromApi(purchaseApi.getItems());

            purchaseRepository.saveAndFlush(purchase);

            return new PurchaseApi(purchase);
        }
        catch (Exception e)
        {
            throw new PurchaseException(PurchaseException.E001,
                    "Error updating purchase.");
        }

    };

    @Override
    public PurchaseApi remove(Long id) throws PurchaseException
    {
        Purchase purchase;

        try
        {
            purchase = purchaseRepository.findOne(id);
            if (purchase == null)
            {
                throw new PurchaseException(PurchaseException.E001,
                        "Purchase not found.");
            }
            purchaseRepository.delete(purchase);
            return new PurchaseApi(purchase);
        }
        catch (Exception e)
        {
            throw new PurchaseException(PurchaseException.E001, e.getMessage());
        }
    }

    @Override
    public PurchaseApi get(Long id) throws PurchaseException
    {
        Purchase purchase;
        try
        {
            purchase = purchaseRepository.findOne(id);
            if (purchase != null)
            {
                return new PurchaseApi(purchase);
            }
            else
            {
                throw new PurchaseException(PurchaseException.E001,
                        "Purchase not found.");
            }

        }
        catch (Exception e)
        {
            throw new PurchaseException(PurchaseException.E001, e.getMessage());
        }
    }

    @Override
    public List<PurchaseApi> getAll(Long page, Long pageSize)
            throws PurchaseException
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
            throw new PurchaseException(PurchaseException.E002,
                    "Page must be greater than or equal to zero.");
        }
        if (pageSize <= 0L)
        {
            throw new PurchaseException(PurchaseException.E002,
                    "Page size must be greater than zero.");
        }
        Page<Purchase> purchases;
        try
        {
            purchases = purchaseRepository.findAll(new PageRequest(page
                    .intValue(), pageSize.intValue()));
        }
        catch (Exception e)
        {
            throw new PurchaseException(PurchaseException.E002,
                    "Solicited info was not found.");
        }

        try
        {
            List<PurchaseApi> purchaseApiList = new ArrayList<PurchaseApi>();

            for (Purchase purchase : purchases)
            {
                purchaseApiList.add(new PurchaseApi(purchase));
            }
            return purchaseApiList;
        }
        catch (Exception e)
        {
            throw new PurchaseException(PurchaseException.E001, e.getMessage());
        }
    }

}
