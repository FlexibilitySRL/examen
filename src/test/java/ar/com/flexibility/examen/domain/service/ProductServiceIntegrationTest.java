package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.Application;
import ar.com.flexibility.examen.domain.exception.GenericException;
import ar.com.flexibility.examen.domain.model.Product;
import ar.com.flexibility.examen.domain.service.impl.ProductServiceImpl;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceIntegrationTest
{
	@Autowired
	private ProductServiceImpl productService;

	private static long COUNT;
	private static long ID_EXIST_IN_DB;
	private static long ID_NOT_EXIST_IN_DB;

	@Before
	public void setUp() throws GenericException 
	{
		productService.deleteAll();

		Product product = new Product();
		product.setDescription("first product");
		product.setPrice(new BigDecimal(2.50));

		productService.add(product);
		
		List<Product> productList = productService.findAll();

		COUNT = productList.size();
		ID_EXIST_IN_DB = productList.get((int) COUNT - 1).getId();
		ID_NOT_EXIST_IN_DB = ID_EXIST_IN_DB + 1;
	}

	@Test
	public void testFindAll()
	{
		// given

		// when
		List<Product> products = null;
		try
		{
			products = productService.findAll();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNotNull(products);
		assertFalse(products.isEmpty());
		assertEquals(COUNT, products.size());
	}

	@Test
	public void testFindOne()
	{
		// given
		Product p1 = null;
		Product p2 = null;

		// when
		try
		{
			p1 = productService.findOne(ID_EXIST_IN_DB);
			p2 = productService.findOne(ID_NOT_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNotNull(p1);
		assertEquals(ID_EXIST_IN_DB, (long) p1.getId());
		assertNull(p2);
	}

	@Test
	public void testAddOk()
	{
		// given
		Product productToAdd = new Product();
		productToAdd.setDescription("prueba add");
		productToAdd.setPrice(new BigDecimal(100.00));

		// when
		Product productAdded = null;
		try
		{
			productAdded = productService.add(productToAdd);
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertEquals(ID_EXIST_IN_DB + 1, (long) productAdded.getId());
		assertEquals(productToAdd.getDescription(), productAdded.getDescription());
		assertEquals(productToAdd.getPrice(), productAdded.getPrice());
	}

	@Test
	public void testAddErrorIdNotNull()
	{
		// given
		Product productToAdd = new Product();
		productToAdd.setId(12L);
		productToAdd.setDescription("prueba add with id not null");
		productToAdd.setPrice(new BigDecimal(200.00));

		// when
		Product productAdded = null;
		try
		{
			productAdded = productService.add(productToAdd);
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNull(productAdded);
	}

	@Test
	public void testUpdateOk()
	{
		// given
		Product productOriginal = null;
		try
		{
			// when
			productOriginal = productService.findOne(ID_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}
		// then
		assertNotNull(productOriginal);

		// given
		Product productToUse = new Product();
		productToUse.setId(ID_EXIST_IN_DB);
		productToUse.setDescription("prueba update");

		Product productUpdated = null;
		try
		{
			// when
			productUpdated = productService.update(productToUse);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNotNull(productUpdated);
		assertEquals(productUpdated, productToUse);
		assertNotEquals(productUpdated, productOriginal);
	}

	@Test
	public void testUpdateErrorIdNotFound()
	{
		// given
		Product productToUse = new Product();
		productToUse.setId(ID_NOT_EXIST_IN_DB);

		Product productUpdated = new Product();
		try
		{
			// when
			productUpdated = productService.update(productToUse);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNull(productUpdated.getId());
	}

	@Test
	public void testUpdateErrorWithoutChanges()
	{
		// given
		Product productOriginal = null;
		try
		{
			// when
			productOriginal = productService.findOne(ID_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}
		// then
		assertNotNull(productOriginal);

		// given
		Product productToUse = new Product();
		productToUse.setId(productOriginal.getId());
		productToUse.setDescription(productOriginal.getDescription());
		productToUse.setPrice(productOriginal.getPrice());

		// when
		Product productUpdated = null;
		try
		{
			productUpdated = productService.update(productToUse);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}

		// then
		assertNull(productUpdated);
		assertEquals(productToUse, productOriginal);
	}

	@Test
	public void testDeleteOk()
	{

		Product product = null;
		try
		{
			// given
			assertNotNull(productService.findOne(ID_EXIST_IN_DB));
			// when
			productService.delete(ID_EXIST_IN_DB);
			product = productService.findOne(ID_EXIST_IN_DB);

		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}
		// then
		assertNull(product);
	}

	@Test
	public void testDeleteIdNotFound()
	{
		// given
		Product product = null;
		try
		{
			// when
			productService.delete(ID_NOT_EXIST_IN_DB);
			product = productService.findOne(ID_NOT_EXIST_IN_DB);
		}
		catch (NotFoundException e)
		{
			e.printStackTrace();
		}
		catch (GenericException e)
		{
			e.printStackTrace();
		}
		// then
		assertNull(product);
	}
}
