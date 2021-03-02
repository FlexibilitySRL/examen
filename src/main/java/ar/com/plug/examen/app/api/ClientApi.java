package ar.com.plug.examen.app.api;

public class ClientApi {

	private Long id;

	private String name;

	public ClientApi() { }

	public ClientApi(Long id) {
		this.id = id;
	}

	public ClientApi(String name) {
		this.name = name;
	}

	public ClientApi(Long id, String name) {
		this.id = id;
		this.name = name;
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
		this.name = name;
	}
	
}
