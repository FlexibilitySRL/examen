package ar.com.plug.examen.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   User
 * Description:            Class that represents a User's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Entity
@Table( name = "user", schema = "public" )
public class User extends BaseEntity
{
    //region Attributes

    /**
     * Name:                   Name
     * Description:            User's Name Attribute
     */
    @Column( name = "name", nullable = false )
    private String name;

    /**
     * Name:                   Last Name
     * Description:            User's Last Name Attribute
     */
    @Column( name = "lastname", nullable = false )
    private String lastName;

    /**
     * Name:                   Document
     * Description:            User's Document Attribute
     */
    @Column( name = "document", nullable = false )
    private String document;

    /**
     * Name:                   Email
     * Description:            User's Email Attribute
     */
    @Column( name = "email", nullable = false )
    private String email;

    /**
     * Name:                   Product List
     * Description:            List of User that contains a Product
     */
    @OneToMany( mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Product> productList;

    /**
     * Name:                   Buyer Payment List
     * Description:            List of User that contains a Payment (as Buyer)
     */
    @OneToMany( mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Transaction> buyingTransactionList;

    /**
     * Name:                   Seller Payment List
     * Description:            List of User that contains a Payment (as Seller)
     */
    @OneToMany( mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Transaction> sellingTransactionList;

    //endregion

    //region Constructors

    /**
     * Name:                User
     * Description:         Method to initializes a User Entity (Empty constructor)
     */
    public User()
    {
        productList = new ArrayList<>();
        buyingTransactionList = new ArrayList<>();
        sellingTransactionList = new ArrayList<>();
    }

    /**
     * Name:                User
     * Description:         Method to initializes a User Entity
     */
    public User( long id )
    {
        super( id );
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

    public List<Product> getProductList()
    {
        return productList;
    }

    public void setProductList( List<Product> productList )
    {
        this.productList = productList;
    }

    public List<Transaction> getBuyingTransactionList()
    {
        return buyingTransactionList;
    }

    public void setBuyingTransactionList( List<Transaction> buyingTransactionList )
    {
        this.buyingTransactionList = buyingTransactionList;
    }

    public List<Transaction> getSellingTransactionList()
    {
        return sellingTransactionList;
    }

    public void setSellingTransactionList( List<Transaction> sellingTransactionList )
    {
        this.sellingTransactionList = sellingTransactionList;
    }

    //endregion
}
