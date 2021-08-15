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
 * Name:                   TransactionStatus
 * Description:            Class that represents a TransactionStatus's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Entity
@Table( name = "transactionstatus", schema = "public" )
public class TransactionStatus extends BaseEntity
{
    //region Attributes

    /**
     * Name:                   Name
     * Description:            TransactionStatus's Name Attribute
     */
    @Column( name = "name", nullable = false )
    private String name;

    /**
     * Name:                   Transaction List
     * Description:            List of Transaction that contains a TransactionStatus
     */
    @OneToMany( mappedBy = "transactionStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Transaction> transactionList;

    //endregion

    //region Constructors

    /**
     * Name:                TransactionStatus
     * Description:         Method to initializes a TransactionStatus Entity (Empty constructor)
     */
    public TransactionStatus()
    {
        transactionList = new ArrayList<>();
    }

    /**
     * Name:                PaymentType
     * Description:         Method to initializes a TransactionStatus Entity
     */
    public TransactionStatus( long id )
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