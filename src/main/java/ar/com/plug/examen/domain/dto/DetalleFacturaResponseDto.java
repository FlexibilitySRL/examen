package ar.com.plug.examen.domain.dto;

public class DetalleFacturaResponseDto {
	
	private String producto;
	
	private Integer cantidada;
	
	private Integer precioUnitario;

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Integer getCantidada() {
		return cantidada;
	}

	public void setCantidada(Integer cantidada) {
		this.cantidada = cantidada;
	}

	public Integer getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Integer precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	
}
