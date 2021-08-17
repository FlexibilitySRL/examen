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

    private long sellerId;
    private int stock;
    private double price;
    private String name;
    private String description;
    private String sellerName;

    //endregion

    //region Constructors

    public ProductDTO( long id )
    {
        super( id );
    }

    public ProductDTO()
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

    public String getSellerName()
    {
        return sellerName;
    }

    public void setSellerName( String sellerName )
    {
        this.sellerName = sellerName;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice( double price )
    {
        this.price = price;
    }

    //endregion
}
