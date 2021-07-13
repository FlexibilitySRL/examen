package ar.com.plug.examen.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.plug.examen.domain.InterfacesServices.IProductService;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.interfaces.IProduct;

public class ProductService implements IProductService{
	
	@Autowired
	private IProduct data;

	@Override
	public List<Product> Listar() {
		// TODO Auto-generated method stub
		return (List<Product>) data.findAll();	
		}

	@Override
	public Optional<Product> ListarId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Product p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
