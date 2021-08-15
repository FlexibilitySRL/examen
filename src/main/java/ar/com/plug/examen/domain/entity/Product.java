package ar.com.plug.examen.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   Product
 * Description:            Class that represents a Product's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Entity
@Table( name = "product", schema = "public" )
public class Product extends BaseEntity
{
    //region Attribute

    /**
     * Name:                   Stock
     * Description:            Product's Stock Attribute
     */
    @Column( name = "stock", nullable = false )
    private int stock;

    /**
     * Name:                   Price
     * Description:            Product's Price Attribute
     */
    @Column( name = "price", nullable = false )
    private double price;

    /**
     * Name:                   Name
     * Description:            Product's Name Attribute
     */
    @Column( name = "name", nullable = false )
    private String name;

    /**
     * Name:                   Description
     * Description:            Product's Description Attribute
     */
    @Column( name = "description", nullable = false )
    private String description;

    /**
     * Name:                   FK_Seller
     * Description:            Foreign key of Product-Seller
     */
    @ManyToOne
    @JoinColumn( name = "fk_seller", nullable = false )
    private Seller seller;

    /**
     * Name:                   Order Detail List
     * Description:            List of OrderDetail that contains a Payment
     */
    @OneToMany( mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<TransactionDetail> transactionDetailList;

    //endregion

    //region Constructors

    /**
     * Name:                Product
     * Description:         Method to initializes a Product Entity (Empty constructor)
     */
    public Product()
    {
        transactionDetailList = new ArrayList<>();
    }

    /**
     * Name:                Product
     * Description:         Method to initializes a Product Entity
     */
    public Product( long id )
    {
        super( id );
        transactionDetailList = new ArrayList<>();
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public int getStock()
    {
        return stock;
    }

    public void setStock( int stock )
    {
        this.stock = stock;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice( double price )
    {
        this.price = price;
    }

    public Seller getSeller()
    {
        return seller;
    }

    public void setSeller( Seller seller )
    {
        this.seller = seller;
    }

    public List<TransactionDetail> getOrderDetailList()
    {
        return transactionDetailList;
    }

    public void setOrderDetailList( List<TransactionDetail> transactionDetailList )
    {
        this.transactionDetailList = transactionDetailList;
    }

    public List<TransactionDetail> getTransactionDetailList()
    {
        return transactionDetailList;
    }

    public void setTransactionDetailList( List<TransactionDetail> transactionDetailList )
    {
        this.transactionDetailList = transactionDetailList;
    }

    //endregion
}
