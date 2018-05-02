package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.flexibility.examen.app.api.SellerApi;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="sellers")
public class Seller {
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="seller_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="surname", nullable=false)
	private String surname;

	public Seller() {
		super();
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

	public Seller(Long id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public Seller(SellerApi sellerApi)
    {
	    super();
	    if (sellerApi.getId() != null) 
	    {
	        this.id = Long.parseLong(sellerApi.getId());       
	    }
	    if (sellerApi.getNombre() != null) 
        {
            this.name = sellerApi.getNombre();       
        }
	    if (sellerApi.getApellido() != null) 
        {
            this.surname = sellerApi.getApellido();       
        }
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
	    if(name != null) 
	    {
	        this.name = name; 
	    }
		
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
	    if(surname != null) 
        {
            this.surname = surname; 
        }
	}
}
