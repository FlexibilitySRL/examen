package ar.com.plug.examen.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   TransactionDTO
 * Description:            Class that represents a Transaction's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class TransactionDTO extends BaseDTO
{
    //region Attributes

    private long sellerId;
    private double amount;
    private long buyerId;
    private LocalDateTime date;
    private String buyerName;
    private String sellerName;
    private TransactionStatusDTO status;
    private PaymentTypeDTO paymentType;
    private List<TransactionDetailDTO> detailList;

    //endregion

    //region Constructors

    public TransactionDTO( long id )
    {
        super( id );
    }

    public TransactionDTO()
    {
    }

    //endregion

    //region Getters & Setters

    public long getSellerId()
    {
        return sellerId;
    }

    public void setSellerId( long sellerId )
    {
        this.sellerId = sellerId;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount( double amount )
    {
        this.amount = amount;
    }

    public long getBuyerId()
    {
        return buyerId;
    }

    public void setBuyerId( long buyerId )
    {
        this.buyerId = buyerId;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    public void setDate( LocalDateTime date )
    {
        this.date = date;
    }

    public String getBuyerName()
    {
        return buyerName;
    }

    public void setBuyerName( String buyerName )
    {
        this.buyerName = buyerName;
    }

    public String getSellerName()
    {
        return sellerName;
    }

    public void setSellerName( String sellerName )
    {
        this.sellerName = sellerName;
    }

    public TransactionStatusDTO getStatus()
    {
        return status;
    }

    public void setStatus( TransactionStatusDTO status )
    {
        this.status = status;
    }

    public PaymentTypeDTO getPaymentType()
    {
        return paymentType;
    }

    public void setPaymentType( PaymentTypeDTO paymentType )
    {
        this.paymentType = paymentType;
    }

    public List<TransactionDetailDTO> getDetailList()
    {
        return detailList;
    }

    public void setDetailList( List<TransactionDetailDTO> detailList )
    {
        this.detailList = detailList;
    }

    //endregion
}
