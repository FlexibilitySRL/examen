package ar.com.plug.examen.domain.dto;

import java.util.Date;
import java.util.Set;

import com.google.common.collect.Sets;

public class TransaccionResponseDto  {
	
	private Integer id;
	
	private Date fecha;
	
	private String estado;
	
	private String cliente;
	
	private String vendedor;
	
	private Integer total = 0;
	
	private Set<DetalleTransaccionResponseDto> detalle = Sets.newHashSet();

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Set<DetalleTransaccionResponseDto> getDetalle() {
		return detalle;
	}

	public void setDetalle(Set<DetalleTransaccionResponseDto> detalle) {
		this.detalle = detalle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

}