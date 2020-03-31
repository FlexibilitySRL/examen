package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.flexibility.examen.domain.dao.ClienteDao;
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteService;
import ar.com.genomasoft.jproject.core.exception.BaseException;
import ar.com.genomasoft.jproject.core.services.BaseAuditedEntityServiceImpl;

@Service
public class ClienteServiceImpl extends BaseAuditedEntityServiceImpl<Cliente, ClienteDao> 
implements ClienteService {


	
}
