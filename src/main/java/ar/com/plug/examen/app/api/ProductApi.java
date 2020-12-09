package ar.com.plug.examen.app.api;

public class ProductApi {

	private Long id;
	private String price;
	private String name;
	
	
	public ProductApi(Long id, String price, String name) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
	}
	
	public ProductApi(String price, String name) {
		super();
		this.price = price;
		this.name = name;
	}

	public ProductApi(Long id) {
		super();
		this.id = id;
	}
	
	public ProductApi() {
		super();
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
