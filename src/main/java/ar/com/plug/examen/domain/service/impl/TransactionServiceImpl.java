package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.jpa.TransactionJpaDao;
import ar.com.plug.examen.domain.service.ITransactionService;
import ar.com.plug.examen.dto.TransactionDto;
import ar.com.plug.examen.mapper.TransactionMapper;

@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	private TransactionMapper mapper;
	
	@Autowired
	private TransactionJpaDao dao;
	
	
	@Override
	public List<TransactionDto> findAll() {
		return mapper.entityListToDtoList(dao.findAll());
	}

	@Override
	public TransactionDto findById(Long id) throws Exception{
		return mapper.entityToDto(dao.findById(id).orElseThrow(() -> new Exception("No se encontro la tranasaccion Nro: " + id)));
	}

	@Override
	public TransactionDto save(TransactionDto transaction) {
		return mapper.entityToDto(dao.save(mapper.dtoToEntity(transaction)));
	}

}
