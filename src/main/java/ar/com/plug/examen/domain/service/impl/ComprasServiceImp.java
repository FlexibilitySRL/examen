package ar.com.plug.examen.domain.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Compras;
import ar.com.plug.examen.domain.repository.ComprasRepository;
import ar.com.plug.examen.domain.service.ComprasService;

@Service
public class ComprasServiceImp implements ComprasService {
	
	private ComprasRepository comprasRepository;
	
	public ComprasServiceImp(ComprasRepository comprasRepository) {
		this.comprasRepository = comprasRepository;
	}
	
	public ResponseDTO saveCompra(Compras compras) {

		ResponseDTO responseDTO = new ResponseDTO();
		if(comprasRepository.save(compras) != null){
		responseDTO.setResponse("Tu Compra ha sido exitosa");
		responseDTO.setMessage("Code 200");
		}
		return responseDTO;

	}

	

	public Compras findByIdCompras(Integer id) {
		return comprasRepository.getOne(id);
	}

	public ResponseDTO deleteCompra(Integer id) {
		Compras compras = findByIdCompras(id);
		Map<String, Boolean> response = new HashMap<>();
		ResponseDTO responseDTO = new ResponseDTO();

		if (compras != null) {
			comprasRepository.delete(compras);
			response.put("delete", true);
			responseDTO.setResponse(response);
			responseDTO.setMessage("code 200");
			return responseDTO;
		}

		response.put("delete", false);
		responseDTO.setResponse(response);
		responseDTO.setMessage("code 400");
		return responseDTO;

	}
	
	public ResponseDTO findByIdcliente(Integer id) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		responseDTO.setResponse(comprasRepository.findByIDCliente(id));
		responseDTO.setMessage("codigo 200");
		
		return responseDTO;
	}

}
