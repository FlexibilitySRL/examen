package ar.com.plug.examen.domain.entity;

/**
 * System:                 FlexiTest
 * Name:                   EntityFactory
 * Description:            Class that handles the use of the Factory pattern for the Entity module
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class EntityFactory
{
    /**
     * Method that returns a new instance of PaymentType Class
     */
    static public PaymentType createPaymentType()
    {
        return new PaymentType();
    }

    /**
     * Method that returns a new instance of PaymentType Class
     */
    static public PaymentType createPaymentType( long id )
    {
        return new PaymentType( id );
    }

    /**
     * Method that returns a new instance of Product Class
     */
    static public Product createProduct()
    {
        return new Product();
    }

    /**
     * Method that returns a new instance of Product Class
     */
    static public Product createProduct( long id )
    {
        return new Product( id );
    }

    /**
     * Method that returns a new instance of Transaction Class
     */
    static public Transaction createTransaction()
    {
        return new Transaction();
    }

    /**
     * Method that returns a new instance of Transaction Class
     */
    static public Transaction createTransaction( long id )
    {
        return new Transaction( id );
    }

    /**
     * Method that returns a new instance of TransactionStatus Class
     */
    static public TransactionStatus createTransactionStatus()
    {
        return new TransactionStatus();
    }

    /**
     * Method that returns a new instance of TransactionStatus Class
     */
    static public TransactionStatus createTransactionStatus( long id )
    {
        return new TransactionStatus( id );
    }

    /**
     * Method that returns a new instance of TransactionDetail Class
     */
    static public TransactionDetail createTransactionDetail()
    {
        return new TransactionDetail();
    }

    /**
     * Method that returns a new instance of TransactionDetail Class
     */
    static public TransactionDetail createTransactionDetail( long id )
    {
        return new TransactionDetail( id );
    }

    /**
     * Method that returns a new instance of User Class
     */
    static public User createUser()
    {
        return new User();
    }

    /**
     * Method that returns a new instance of User Class
     */
    static public User createUser( long id )
    {
        return new User( id );
    }
}
