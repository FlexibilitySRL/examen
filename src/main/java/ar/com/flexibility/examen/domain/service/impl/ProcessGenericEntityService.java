package ar.com.flexibility.examen.domain.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ar.com.flexibility.examen.domain.service.IProcessGenericEntityService;

public abstract class ProcessGenericEntityService<E> implements IProcessGenericEntityService<E>{
	
	@Autowired
	ObjectMapper objectMapper;
	
	protected Logger logger = LogManager.getLogger(getClazz());
	
	protected String entityName = getEntityName();

	@Override
	public E add(E entity) {
		
		try {
			logger.info(String.format("Data input %s", objectMapper.writeValueAsString(entity)));
		} catch (JsonProcessingException e) {			
			logger.error(e.getMessage());
		}
		
		entity = getRepository().save(entity);
		
		logger.info(String.format("add %s", entityName));
		
		return entity;
	}

	@Override
	public Iterable<E> getAll() {
		
		Iterable<E> list = getRepository().findAll();
		
		logger.info(String.format("getAll %s", entityName));
		
		return list;
	}

	@Override
	public E update(E entity) {
		
		try {
			logger.info(String.format("Data input %s", objectMapper.writeValueAsString(entity)));
		} catch (JsonProcessingException e) {			
			logger.error(e.getMessage());
		}
		
		entity = getRepository().save(entity);
		
		logger.info(String.format("update %s", entityName));
		
		return entity;
	}

	@Override
	public void delete(long id) {
		
		logger.info(String.format("Data input ID: %s", id));
		
		E entity = getRepository().findOne(id);				
		
		getRepository().delete(entity);
		
		logger.info(String.format("delete %s", entityName));
	}
	
	public abstract JpaRepository<E, Long> getRepository();
	
	public abstract Class<?> getClazz();
	
	public abstract String getEntityName();

	
}
