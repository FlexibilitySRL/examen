package ar.com.plug.examen.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * System:                 FlexiTest
 * Name:                   TransactionDetail
 * Description:            Class that represents a TransactionDetail's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Entity
@Table( name = "transactiondetail", schema = "public" )
public class TransactionDetail extends BaseEntity
{
    //region Attributes

    /**
     * Name:                   Quantity
     * Description:            Order's Quantity Attribute
     */
    @Column( name = "quantity", nullable = false )
    private int quantity;

    /**
     * Name:                   FK_Transaction
     * Description:            Foreign key of Order-Transaction
     */
    @ManyToOne
    @JoinColumn( name = "fk_transaction", nullable = false )
    private Transaction transaction;

    /**
     * Name:                   FK_Product
     * Description:            Foreign key of Order-Product
     */
    @ManyToOne
    @JoinColumn( name = "fk_product", nullable = false )
    private Product product;

    //endregion

    //region Constructors

    /**
     * Name:                TransactionDetail
     * Description:         Method to initializes a TransactionDetail Entity (Empty constructor)
     */
    public TransactionDetail()
    {
    }

    /**
     * Name:                TransactionDetail
     * Description:         Method to initializes a TransactionDetail Entity
     */
    public TransactionDetail( long id )
    {
        super( id );
    }

    //endregion

    //region Getters & Setters

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }

    public Transaction getTransaction()
    {
        return transaction;
    }

    public void setTransaction( Transaction transaction )
    {
        this.transaction = transaction;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct( Product product )
    {
        this.product = product;
    }

    //endregion
}
