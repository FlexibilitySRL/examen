package ar.com.plug.examen.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import ar.com.plug.examen.domain.model.ProductosModel;
import ar.com.plug.examen.app.api.ProductosRepository;

@Service
public class ProductoService{

	@Autowired
	ProductosRepository productosRepository;

	public ArrayList<ProductosModel> findProductos(){
		return (ArrayList<ProductosModel>) productosRepository.findAll();
	}
	
	public ProductosModel insertProduct(ProductosModel producto)  {
		return productosRepository.save(producto);
	}

	public Optional<ProductosModel> findId(Long id){
		return productosRepository.findById(id);
	}

	public Optional<ProductosModel> findMark(String mark){
		return productosRepository.findMark(mark);
	}

	public boolean deleteProduct(Long id){
		try{

			productosRepository.deleteById(id);
			return true;

		}catch(Exception err){
			return false;
		}
	}


}