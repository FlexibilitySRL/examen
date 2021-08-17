package ar.com.plug.examen.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   Buyer
 * Description:            Class that represents a Buyer's Entity in the application
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@Entity
@Table( name = "buyer", schema = "public" )
public class Buyer extends BaseEntity
{
    //region Attributes

    /**
     * Name:                   Name
     * Description:            Buyer's Name Attribute
     */
    @Column( name = "name", nullable = false )
    private String name;

    /**
     * Name:                   Last Name
     * Description:            Buyer's Last Name Attribute
     */
    @Column( name = "lastname", nullable = false )
    private String lastName;

    /**
     * Name:                   Document
     * Description:            Buyer's Document Attribute
     */
    @Column( name = "document", nullable = false )
    private String document;

    /**
     * Name:                   Email
     * Description:            Buyer's Email Attribute
     */
    @Column( name = "email", nullable = false )
    private String email;

    /**
     * Name:                   Transaction List
     * Description:            List of Transaction that contains a Buyer
     */
    @OneToMany( mappedBy = "buyer", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
    private List<Transaction> transactionList;

    //endregion

    //region Constructors

    /**
     * Name:                Buyer
     * Description:         Method to initializes a Buyer Entity (Empty constructor)
     */
    public Buyer()
    {
        transactionList = new ArrayList<>();
    }

    /**
     * Name:                Buyer
     * Description:         Method to initializes a Buyer Entity
     */
    public Buyer( long id )
    {
        super( id );
        transactionList = new ArrayList<>();
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

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getDocument()
    {
        return document;
    }

    public void setDocument( String document )
    {
        this.document = document;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public List<Transaction> getTransactionList()
    {
        return transactionList;
    }

    public void setTransactionList( List<Transaction> transactionList )
    {
        this.transactionList = transactionList;
    }

    //endregion
}
