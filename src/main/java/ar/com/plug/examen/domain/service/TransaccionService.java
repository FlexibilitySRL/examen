package ar.com.plug.examen.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import ar.com.plug.examen.domain.model.TransaccionModel;
import ar.com.plug.examen.app.api.TransaccionesRepository;
import ar.com.plug.examen.domain.model.ClienteModel;
import java.util.Date;

@Service
public class TransaccionService{

	@Autowired
	TransaccionesRepository transaccionesRepository;

	public ArrayList<TransaccionModel> findTransactionsDate(Date datePurch){
		return (ArrayList<TransaccionModel>) transaccionesRepository.findTransactionsDate(datePurch);
	}

	public ArrayList<TransaccionModel> findByTransactionClient(ClienteModel client){
		return (ArrayList<TransaccionModel>) transaccionesRepository.findByTransactionClient(client);
	}
}