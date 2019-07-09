package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.com.flexibility.examen.domain.dao.IClienteDao;
import ar.com.flexibility.examen.domain.model.entity.Cliente;
import ar.com.flexibility.examen.domain.service.ProcessClienteService;

@Service
public class ProcessClienteServiceImpl implements ProcessClienteService {
	
	@Autowired
	private IClienteDao clienteDao;

	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
    public void save(Cliente cliente) {
        clienteDao.save(cliente);
    }

	@Override
    public Cliente findOne(Long id) {
        return clienteDao.findOne(id);
    }

    @Override
    public void delete(Long id) {

        clienteDao.delete(id);
    }
}
