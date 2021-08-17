package ar.com.plug.examen.domain.dto;

/**
 * System:                 FlexiTest
 * Name:                   DTOFactory
 * Description:            Class that handles the use of the Factory pattern for the DTO module
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class DTOFactory
{
    /**
     * Method that returns a new instance of PaymentTypeDTO Class
     */
    static public PaymentTypeDTO createPaymentTypeDTO()
    {
        return new PaymentTypeDTO();
    }

    /**
     * Method that returns a new instance of PaymentTypeDTO Class
     */
    static public PaymentTypeDTO createPaymentTypeDTO( long id )
    {
        return new PaymentTypeDTO( id );
    }

    /**
     * Method that returns a new instance of ProductDTO Class
     */
    static public ProductDTO createProductDTO()
    {
        return new ProductDTO();
    }

    /**
     * Method that returns a new instance of ProductDTO Class
     */
    static public ProductDTO createProductDTO( long id )
    {
        return new ProductDTO( id );
    }

    /**
     * Method that returns a new instance of TransactionDTO Class
     */
    static public TransactionDTO createTransactionDTO()
    {
        return new TransactionDTO();
    }

    /**
     * Method that returns a new instance of TransactionDTO Class
     */
    static public TransactionDTO createTransactionDTO( long id )
    {
        return new TransactionDTO( id );
    }

    /**
     * Method that returns a new instance of TransactionStatusDTO Class
     */
    static public TransactionStatusDTO createTransactionStatusDTO()
    {
        return new TransactionStatusDTO();
    }

    /**
     * Method that returns a new instance of TransactionStatusDTO Class
     */
    static public TransactionStatusDTO createTransactionStatusDTO( long id )
    {
        return new TransactionStatusDTO( id );
    }

    /**
     * Method that returns a new instance of TransactionDetailDTO Class
     */
    static public TransactionDetailDTO createTransactionDetailDTO()
    {
        return new TransactionDetailDTO();
    }

    /**
     * Method that returns a new instance of TransactionDetailDTO Class
     */
    static public TransactionDetailDTO createTransactionDetailDTO( long id )
    {
        return new TransactionDetailDTO( id );
    }

    /**
     * Method that returns a new instance of SellerDTO Class
     */
    static public SellerDTO createSellerDTO()
    {
        return new SellerDTO();
    }

    /**
     * Method that returns a new instance of SellerDTO Class
     */
    static public SellerDTO createSellerDTO( long id )
    {
        return new SellerDTO( id );
    }

    /**
     * Method that returns a new instance of BuyerDTO Class
     */
    static public BuyerDTO createBuyerDTO()
    {
        return new BuyerDTO();
    }

    /**
     * Method that returns a new instance of BuyerDTO Class
     */
    static public BuyerDTO createBuyerDTO( long id )
    {
        return new BuyerDTO( id );
    }
}
