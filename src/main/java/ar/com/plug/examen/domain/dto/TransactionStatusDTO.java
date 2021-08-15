package ar.com.plug.examen.domain.dto;

/**
 * System:                 FlexiTest
 * Name:                   TransactionStatusDTO
 * Description:            Class that represents a TransactionStatus's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class TransactionStatusDTO extends BaseDTO
{
    //region Attributes

    private String name;

    //endregion

    //region Constructors

    public TransactionStatusDTO( long id )
    {
        setId( id );
    }

    public TransactionStatusDTO()
    {
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

    //endregion
}