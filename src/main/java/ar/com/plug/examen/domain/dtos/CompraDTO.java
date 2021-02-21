package ar.com.plug.examen.domain.dtos;


import java.util.List;

public class CompraDTO {

    private Long id;
    private ClienteDTO cliente;
    private VendedorDTO vendedor;
    private List<ProductoDTO> productos;
    private Double precioSinIva;
    private Double impuestos;
    private String medioDePago;

    public CompraDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public VendedorDTO getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public Double getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(Double precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }
}
