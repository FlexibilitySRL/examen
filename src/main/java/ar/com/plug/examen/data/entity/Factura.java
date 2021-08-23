package ar.com.plug.examen.data.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "FACTURA")
public class Factura {	
	
	@Id
	@Column(name="ID_FACTURA")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFactura; 
	
	@Column(name="ID_CLIENTE")
	private Long idCliente; 
	
	@Column(name="ID_VENDEDOR")
	private Long idVendedor; 
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="FECHA")
	private Date fecha;
	
	@Column(name="TOTAL")
	private Double total;
	
	@Column(name="ESTADO")
	private boolean estado;
	
	@Column(name = "FECHA_A")
	private Timestamp fechaA;
	
	@Column(name = "FECHA_M")
	private Timestamp fechaM;
	
	//@OneToMany(mappedBy = "book", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	//@OneToMany(mappedBy = "factura",  cascade = CascadeType.ALL)
	//@OneToMany(targetEntity=FacturaDetalle.class, mappedBy="factura",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToMany(mappedBy="factura",targetEntity = FacturaDetalle.class)
	@JsonIgnoreProperties("factura")
    private List<FacturaDetalle> facturaDetalle;
		
	public Factura() {
			
	}	
		
	public Factura(long idFactura, Long idCliente, Long idVendedor, String codigo, Date fecha, Double total,
			boolean estado, Timestamp fechaA, Timestamp fechaM, List<FacturaDetalle> facturaDetalle) {
		super();
		this.idFactura = idFactura;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		this.codigo = codigo;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
		this.fechaA = fechaA;
		this.fechaM = fechaM;
		this.facturaDetalle = facturaDetalle;
	}




	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(Long idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Timestamp getFechaA() {
		return fechaA;
	}

	public void setFechaA(Timestamp fechaA) {
		this.fechaA = fechaA;
	}

	public Timestamp getFechaM() {
		return fechaM;
	}

	public void setFechaM(Timestamp fechaM) {
		this.fechaM = fechaM;
	}

	public List<FacturaDetalle> getFacturaDetalle() {
		return facturaDetalle;
	}

	public void setFacturaDetalle(List<FacturaDetalle> facturaDetalle) {
		this.facturaDetalle = facturaDetalle;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", idCliente=" + idCliente + ", idVendedor=" + idVendedor
				+ ", codigo=" + codigo + ", fecha=" + fecha + ", total=" + total + ", estado=" + estado + ", fechaA="
				+ fechaA + ", fechaM=" + fechaM + "]";
	}
	
}
