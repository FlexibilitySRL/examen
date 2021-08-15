package ar.com.plug.examen.domain.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   UserDTO
 * Description:            Class that represents a User's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class UserDTO extends BaseDTO
{
    //region Attributes

    private String name;
    private String lastName;
    private String document;
    private String email;
    private List<ProductDTO> productList;
    private List<TransactionDTO> buyingTransactionList;
    private List<TransactionDTO> sellingTransactionList;

    //endregion

    //region Constructors

    public UserDTO( long id )
    {
        setId( id );
        productList = new ArrayList<>();
        buyingTransactionList = new ArrayList<>();
        sellingTransactionList = new ArrayList<>();
    }

    public UserDTO()
    {
        productList = new ArrayList<>();
        buyingTransactionList = new ArrayList<>();
        sellingTransactionList = new ArrayList<>();
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

    public List<ProductDTO> getProductList()
    {
        return productList;
    }

    public void setProductList( List<ProductDTO> productList )
    {
        this.productList = productList;
    }

    public List<TransactionDTO> getBuyingTransactionList()
    {
        return buyingTransactionList;
    }

    public void setBuyingTransactionList( List<TransactionDTO> buyingTransactionList )
    {
        this.buyingTransactionList = buyingTransactionList;
    }

    public List<TransactionDTO> getSellingTransactionList()
    {
        return sellingTransactionList;
    }

    public void setSellingTransactionList( List<TransactionDTO> sellingTransactionList )
    {
        this.sellingTransactionList = sellingTransactionList;
    }

    //endregion
}
