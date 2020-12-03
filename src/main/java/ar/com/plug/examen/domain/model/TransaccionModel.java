package ar.com.plug.examen.domain.model;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "transacciones")

public class TransaccionModel {

    //atributos
    private Long id;

    private String serie;

    private Integer numero;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REGIST_DATE", nullable = false)
    private Calendar fecha;

    @ManyToOne
	@JoinColumn (name="id_cliente")
	private ClienteModel cliente;

    @ManyToOne
	@JoinColumn (name="id_vendedor")
	private VendedorModel vendedor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaccion")
    private List<ProductoModel> productos;

    private Double total;

    private Integer estado;

    //constructor
    public TransaccionModel(Long id, String serie, Integer numero, Calendar fecha, ClienteModel cliente, VendedorModel vendedor, List<ProductoModel> productos, Double total, Integer estado) {
        this.id = id;
        this.serie = serie;
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.productos = productos;
        this.total = total;
        this.estado = estado;
    }
    
    //getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public ClienteModel getCliente(){
        return cliente;
    }

    public void setCliente(ClienteModel cliente){
        this.cliente = cliente;
    }

    public VendedorModel getVendedor(){
        return vendedor;
    }

    public void setVendedor(VendedorModel vendedor){
        this.vendedor = vendedor;
    }

    public List<ProductoModel> getProductos(){
        return productos;
    }

    public void setProductos(List<ProductoModel> productos){
        this.productos = productos;
    }
    
    public Double getTotal(){
        return total;
    }

    public void setTotal(Double total){
        this.total = total;
    }

    public Integer getEstado(){
        return estado;
    }

    public void setEstado(Integer estado){
        this.estado = estado;
    }
}