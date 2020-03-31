package ar.com.flexibility.examen.domain.dao;

import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.genomasoft.jproject.core.daos.BaseAuditedEntityDaoImpl;

@Repository
public class ProductoDaoImpl extends BaseAuditedEntityDaoImpl<Producto> implements ProductoDao {

}
