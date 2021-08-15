package ar.com.plug.examen.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   Transaction
 * Description:            Class that represents a Transaction's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Entity
@Table( name = "transaction", schema = "public" )
public class Transaction extends BaseEntity
{
    //region Attributes

    /**
     * Name:                   Date
     * Description:            Transaction's Date Attribute
     */
    @Column( name = "date", nullable = false )
    private LocalDateTime date;

    /**
     * Name:                   Amount
     * Description:            Transaction's Amount Attribute
     */
    @Column( name = "amount", nullable = false )
    private double amount;

    /**
     * Name:                   FK_UserBuyer
     * Description:            Foreign key of Transaction-User
     */
    @ManyToOne
    @JoinColumn( name = "fk_userbuyer", nullable = false )
    private User buyer;

    /**
     * Name:                   FK_UserSeller
     * Description:            Foreign key of Transaction-User
     */
    @ManyToOne
    @JoinColumn( name = "fk_userseller", nullable = false )
    private User seller;

    /**
     * Name:                   FK_TransactionType
     * Description:            Foreign key of Transaction-PaymentType
     */
    @ManyToOne
    @JoinColumn( name = "fk_paymenttype", nullable = false )
    private PaymentType paymentType;

    /**
     * Name:                   FK_TransactionStatus
     * Description:            Foreign key of Transaction-TransactionStatus
     */
    @ManyToOne
    @JoinColumn( name = "fk_transactionstatus", nullable = false )
    private TransactionStatus transactionStatus;

    /**
     * Name:                   TransactionDetail List
     * Description:            List of TransactionDetail that contains a Transaction
     */
    @OneToMany( mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    private List<TransactionDetail> transactionDetailList;

    //endregion

    //region Constructors

    /**
     * Name:                Transaction
     * Description:         Method to initializes a Transaction Entity (Empty constructor)
     */
    public Transaction()
    {
        transactionDetailList = new ArrayList<>();
    }

    /**
     * Name:                Transaction
     * Description:         Method to initializes a Transaction Entity
     */
    public Transaction( long id )
    {
        super( id );
        transactionDetailList = new ArrayList<>();
    }

    //endregion

    //region Getters & Setters

    public User getBuyer()
    {
        return buyer;
    }

    public void setBuyer( User buyer )
    {
        this.buyer = buyer;
    }

    public User getSeller()
    {
        return seller;
    }

    public void setSeller( User seller )
    {
        this.seller = seller;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate( LocalDateTime date )
    {
        this.date = date;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount( double amount )
    {
        this.amount = amount;
    }

    public PaymentType getTransactionType()
    {
        return paymentType;
    }

    public void setTransactionType( PaymentType paymentType )
    {
        this.paymentType = paymentType;
    }

    public PaymentType getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType( PaymentType paymentType )
    {
        this.paymentType = paymentType;
    }

    public List<TransactionDetail> getTransactionDetailList()
    {
        return transactionDetailList;
    }

    public TransactionStatus getTransactionStatus()
    {
        return transactionStatus;
    }

    public void setTransactionStatus( TransactionStatus transactionStatus )
    {
        this.transactionStatus = transactionStatus;
    }

    public void setTransactionDetailList( List<TransactionDetail> transactionDetailList )
    {
        this.transactionDetailList = transactionDetailList;
    }

    //endregion
}
