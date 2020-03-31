package ar.com.flexibility.examen.domain.dao;

import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Compra;
import ar.com.genomasoft.jproject.core.daos.BaseAuditedEntityDaoImpl;

@Repository
public class CompraDaoImpl extends BaseAuditedEntityDaoImpl<Compra> implements CompraDao {

}
