package ar.com.flexibility.examen.app.api;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import ar.com.flexibility.examen.domain.model.Client;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonRootName(value = "client")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Client")
public class ClientApi
{
	@JsonProperty
	@ApiModelProperty(value = "ID", position = 0)
	private Long id;
	
	@JsonProperty
	@ApiModelProperty(value = "Full Name", required = true, position = 1)
	private String fullname;
	
	@JsonProperty
	@ApiModelProperty(value = "Email", required = true, position = 2)
	private String email;
	
	public ClientApi() 
	{
	}
	
	public ClientApi(Client client)
	{
		this.id = client.getId();
		this.fullname = client.getFullname();
		this.email = client.getEmail();
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
		if (!(obj instanceof ClientApi))
			return false;
		ClientApi other = (ClientApi) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullname, other.fullname)
				&& Objects.equals(id, other.id);
	}
}
