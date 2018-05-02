package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.flexibility.examen.app.api.ClientApi;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="clients")
public class Client {
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="client_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="surname", nullable=false)
	private String surname;

	public Client() {
		super();
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", surname=" + surname + "]";
	}

	public Client(Long id, String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public Client(ClientApi clientApi)
    {
	    super();
	    if (clientApi.getId() != null) 
	    {
	        this.id = Long.parseLong(clientApi.getId());       
	    }
	    if (clientApi.getNombre() != null) 
        {
            this.name = clientApi.getNombre();       
        }
	    if (clientApi.getApellido() != null) 
        {
            this.surname = clientApi.getApellido();       
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
