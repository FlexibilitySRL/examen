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
 * Name:                   Seller
 * Description:            Class that represents a Seller's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 15/08/21
 */
@Entity
@Table( name = "seller", schema = "public" )
public class Seller extends BaseEntity
{
    //region Attributes

    /**
     * Name:                   Full Name
     * Description:            Buyer's Full Name Attribute
     */
    @Column( name = "fullname", nullable = false )
    private String fullName;

    /**
     * Name:                   Contact Email
     * Description:            Buyer's Contact Email Attribute
     */
    @Column( name = "contactemail", nullable = false )
    private String contactEmail;

    /**
     * Name:                   Product List
     * Description:            List of Buyer that contains a Product
     */
    @OneToMany( mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Product> productList;

    /**
     * Name:                   Transaction List
     * Description:            List of Transaction that contains a Seller
     */
    @OneToMany( mappedBy = "seller", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Transaction> transactionList;

    //endregion

    //region Constructors

    /**
     * Name:                Seller
     * Description:         Method to initializes a Seller Entity (Empty constructor)
     */
    public Seller()
    {
        productList = new ArrayList<>();
        transactionList = new ArrayList<>();
    }

    /**
     * Name:                Seller
     * Description:         Method to initializes a Seller Entity
     */
    public Seller( long id )
    {
        super( id );
        productList = new ArrayList<>();
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

    public List<Product> getProductList()
    {
        return productList;
    }

    public void setProductList( List<Product> productList )
    {
        this.productList = productList;
    }

    public List<Transaction> getTransactionList()
    {
        return transactionList;
    }

    public void setTransactionList( List<Transaction> transactionList )
    {
        this.transactionList = transactionList;
    }

    //endregion
}
