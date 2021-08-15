package ar.com.plug.examen.domain.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * System:                 FlexiTest
 * Name:                   BaseEntity
 * Description:            Implementation used for the base entity class
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
    //region Attributes

    private static final long serialVersionUID = 1L;

    /**
     * Name:                   Id
     * Description:            BaseEntity's Id attribute that every Entity will inherit
     */
    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    //endregion

    //region Constructors

    /**
     * Name:                   BaseEntity's Constructor
     * Description:            Method that initialize BaseEntity's class
     */
    public BaseEntity( long id )
    {
        this.id = id;
    }

    public BaseEntity()
    {
    }

    //endregion

    //region Getters & Setters

    public long getId()
    {
        return id;
    }

    //endregion
}
