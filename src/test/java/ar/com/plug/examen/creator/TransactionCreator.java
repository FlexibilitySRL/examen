package ar.com.plug.examen.creator;

import java.util.ArrayList;
import java.util.List;

import ar.com.plug.examen.domain.model.entities.Client;
import ar.com.plug.examen.domain.model.entities.Product;
import ar.com.plug.examen.domain.model.entities.Transaction;
import ar.com.plug.examen.dto.TransactionDto;

public class TransactionCreator {

	public static Transaction createTransaction(Client client, List<Product> products) {
		Transaction t = new Transaction();
		t.setClient(client);
		t.setProducts(products);
		t.setTotalValue(calculateTotal(products));
		return t;
	}
	
	public static TransactionDto createTransactionDto(Client client, List<Product> products) {
		TransactionDto t = new TransactionDto();
		t.setClient(client);
		t.setProducts(products);
		t.setTotalValue(calculateTotal(products));
		return t;
	}
	
	public static Transaction createTransactionWithId(Long id) {
		Transaction t= new Transaction();
		t.setId(id);
		return t;
	}
	
	public static TransactionDto createTransactionDtoWithId(Long id) {
		TransactionDto t = new TransactionDto();
		t.setId(id);
		return t;
	}
	
	
	public static List<TransactionDto> createTransactionDtoList(TransactionDto t1, TransactionDto t2){
		List<TransactionDto> tList = new ArrayList<TransactionDto>();
		tList.add(t1);
		tList.add(t2);
		return tList;
	}
	
	public static TransactionDto createTransactionDtoMock() {
		TransactionDto t = new TransactionDto();
		List<Product> pList = new ArrayList();
		pList.add(ProductCreator.createProduct());
		t.setClient(ClientCreator.createClientMock());
		t.setProducts(pList);
		t.setTotalValue(calculateTotal(pList));
		t.setStatus("COMPLETED");
		return t;
	}
	
	
	public static int calculateTotal(List<Product> products) {
		int totalValue = 0;
		for(Product product : products) {
			totalValue =+ product.getValue();
		}
		return totalValue;
	}
}
