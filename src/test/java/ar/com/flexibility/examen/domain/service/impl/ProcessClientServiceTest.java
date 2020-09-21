package ar.com.flexibility.examen.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import ar.com.flexibility.examen.domain.dao.IClientDao;



@SpringBootTest
@AutoConfigureTestDatabase
@Sql("/detalles-archivos-h2.sql")
public class ProcessClientServiceTest {

	@Autowired
	private IClientDao clientDao;



}
