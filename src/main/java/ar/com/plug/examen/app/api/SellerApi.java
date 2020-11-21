package ar.com.plug.examen.app.api;

public class SellerApi {

	private Long id;
	
	private String name;

	public SellerApi() { }
	
	public SellerApi(String name) {
		this.name = name;
	}
	
	public SellerApi(Long id, String name) {
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