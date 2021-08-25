package ar.com.plug.examen.domain.dto;

public class ProductoDTO {
	
	private long idProducto; 
	private String codigo;
	private String descripcion;
	private int cantidad;
	private Double precio;
	public ProductoDTO() {

	}
	public ProductoDTO(long idProducto, String codigo, String descripcion, int cantidad, Double precio) {
		super();
		this.idProducto = idProducto;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	public long getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	@Override
	public String toString() {
		return "ProductoDTO [idProducto=" + idProducto + ", codigo=" + codigo + ", descripcion=" + descripcion
				+ ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}
}
