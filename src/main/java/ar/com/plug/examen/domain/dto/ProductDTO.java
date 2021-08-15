package ar.com.plug.examen.domain.dto;

/**
 * System:                 FlexiTest
 * Name:                   ProductDTO
 * Description:            Class that represents a Product's DTO in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class ProductDTO extends BaseDTO
{
    //region Attributes

    private long ownerId;
    private int stock;
    private String name;
    private String description;
    private String ownerFullName;

    //endregion

    //region Constructors

    public ProductDTO( long id )
    {
        setId( id );
    }

    public ProductDTO()
    {
    }

    //endregion

    //region Getters & Setters

    public long getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId( long ownerId )
    {
        this.ownerId = ownerId;
    }

    public int getStock()
    {
        return stock;
    }

    public void setStock( int stock )
    {
        this.stock = stock;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getOwnerFullName()
    {
        return ownerFullName;
    }

    public void setOwnerFullName( String ownerFullName )
    {
        this.ownerFullName = ownerFullName;
    }

    //endregion
}
