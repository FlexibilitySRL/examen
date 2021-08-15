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
 * Name:                   PaymentType
 * Description:            Class that represents a PaymentType's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Entity
@Table( name = "paymenttype", schema = "public" )
public class PaymentType extends BaseEntity
{
    //region Attributes

    /**
     * Name:                   Name
     * Description:            PaymentType's Name Attribute
     */
    @Column( name = "name", nullable = false )
    private String name;

    /**
     * Name:                   Transaction List
     * Description:            List of Transaction that contains a PaymentType
     */
    @OneToMany( mappedBy = "paymentType", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<Transaction> transactionList;

    //endregion

    //region Constructors

    /**
     * Name:                PaymentType
     * Description:         Method to initializes a PaymentType Entity (Empty constructor)
     */
    public PaymentType()
    {
        transactionList = new ArrayList<>();
    }

    /**
     * Name:                PaymentType
     * Description:         Method to initializes a PaymentType Entity
     */
    public PaymentType( long id )
    {
        super( id );
        transactionList = new ArrayList<>();
    }

    //endregion

    //region Getters && Setters

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public List<Transaction> getPaymentList()
    {
        return transactionList;
    }

    public void setPaymentList( List<Transaction> transactionList )
    {
        this.transactionList = transactionList;
    }

    //endregion
}
