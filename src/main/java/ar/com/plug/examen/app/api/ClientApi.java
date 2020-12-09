package ar.com.plug.examen.app.api;

public class ClientApi {
	private Long id;
	private String document;
	private String name;
	
	public ClientApi(Long id, String document, String name) {
		super();
		this.id = id;
		this.document = document;
		this.name = name;
	}
	public ClientApi(Long id) {
		super();
		this.id = id;
	}
	
	public ClientApi() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
}
