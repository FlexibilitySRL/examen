package ar.com.plug.examen.domain.dto;

/**
 * System:                 FlexiTest
 * Name:                   PaymentTypeDTO
 * Description:            Class that represents a PaymentType's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class PaymentTypeDTO extends BaseDTO
{
    //region Attributes

    private String name;

    //endregion

    //region Constructors

    public PaymentTypeDTO( long id )
    {
        setId( id );
    }

    public PaymentTypeDTO()
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
