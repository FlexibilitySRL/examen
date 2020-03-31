package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.dao.ProductoDao;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.ProductoService;
import ar.com.genomasoft.jproject.core.services.BaseAuditedEntityServiceImpl;

@Service
public class ProductoServiceImpl extends BaseAuditedEntityServiceImpl<Producto, ProductoDao> 
implements ProductoService {


	
}
