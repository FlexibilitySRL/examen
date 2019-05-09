package ar.com.flexibility.examen.domain.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.flexibility.examen.app.api.ClientApi;

@Entity
@Table(name = "Client")
public class Client
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fullname", length = 50, nullable = false)
	private String fullname;
	
	@Column(name = "email", length = 50, nullable = false)
	private String email;

	public Client() 
	{
	}
	
	public Client(ClientApi clienteApi) 
	{
		this.id = clienteApi.getId();
		this.fullname = clienteApi.getFullname();
		this.email = clienteApi.getEmail();
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFullname()
	{
		return fullname;
	}

	public void setFullname(String fullname)
	{
		this.fullname = fullname;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(email, fullname, id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Client))
			return false;
		Client other = (Client) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullname, other.fullname)
				&& Objects.equals(id, other.id);
	}
}
