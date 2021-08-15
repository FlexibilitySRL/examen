package ar.com.plug.examen.domain.dto;

import ar.com.plug.examen.domain.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   SellerDTO
 * Description:            Class that represents a Seller's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 15/08/21
 */
public class SellerDTO extends BaseEntity
{
    //region Attributes

    private String fullName;
    private String contactEmail;
    private List<TransactionDTO> transactionList;

    //endregion

    //region Constructors

    public SellerDTO( long id )
    {
        super( id );
        transactionList = new ArrayList<>();
    }

    public SellerDTO()
    {
        transactionList = new ArrayList<>();
    }

    //endregion

    //region Getters & Setters

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName( String fullName )
    {
        this.fullName = fullName;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail( String contactEmail )
    {
        this.contactEmail = contactEmail;
    }

    public List<TransactionDTO> getTransactionList()
    {
        return transactionList;
    }

    public void setTransactionList( List<TransactionDTO> transactionList )
    {
        this.transactionList = transactionList;
    }

    //endregion
}
