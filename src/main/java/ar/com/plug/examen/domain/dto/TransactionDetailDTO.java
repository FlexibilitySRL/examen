package ar.com.plug.examen.domain.dto;

/**
 * System:                 FlexiTest
 * Name:                   TransactionDetailDTO
 * Description:            Class that represents a TransactionDetail's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class TransactionDetailDTO extends BaseDTO
{
    //region Attributes

    private int quantity;
    private ProductDTO product;

    //endregion

    //region Constructors

    public TransactionDetailDTO( long id )
    {
        setId( id );
    }

    public TransactionDetailDTO()
    {
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

    public ProductDTO getProduct()
    {
        return product;
    }

    public void setProduct( ProductDTO product )
    {
        this.product = product;
    }

    //endregion
}
