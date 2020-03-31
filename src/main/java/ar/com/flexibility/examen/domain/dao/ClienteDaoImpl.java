package ar.com.flexibility.examen.domain.dao;

import org.springframework.stereotype.Repository;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.genomasoft.jproject.core.daos.BaseAuditedEntityDaoImpl;

@Repository
public class ClienteDaoImpl extends BaseAuditedEntityDaoImpl<Cliente> implements ClienteDao {

}
