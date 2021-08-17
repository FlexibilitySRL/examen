package ar.com.plug.examen.domain.dto;

import java.io.Serializable;

/**
 * System:                 FlexiTest
 * Name:                   BaseDto
 * Description:            Implementation used for the base dto class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public abstract class BaseDTO implements Serializable
{
    //region Attributes

    private long id;

    //endregion

    //region Constructors

    public BaseDTO( long id )
    {
        setId( id );
    }

    public BaseDTO()
    {
    }

    //endregion

    //region Getters & Setters

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    //endregion
}
