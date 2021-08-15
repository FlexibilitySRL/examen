package ar.com.plug.examen.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   BuyerDTO
 * Description:            Class that represents a Buyer's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class BuyerDTO extends BaseDTO
{
    //region Attributes

    private String name;
    private String lastName;
    private String document;
    private String email;
    private List<TransactionDTO> transactionList;

    //endregion

    //region Constructors

    public BuyerDTO( long id )
    {
        super( id );
        transactionList = new ArrayList<>();
    }

    public BuyerDTO()
    {
        transactionList = new ArrayList<>();
    }

    //endregion

    //region Getters & Setters

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getDocument()
    {
        return document;
    }

    public void setDocument( String document )
    {
        this.document = document;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
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
