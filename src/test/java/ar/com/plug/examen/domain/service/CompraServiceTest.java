package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.dto.CompraRestDto;
import ar.com.plug.examen.domain.model.entity.Compra;
import ar.com.plug.examen.domain.model.repository.CompraRepository;
import ar.com.plug.examen.domain.service.impl.CompraServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class CompraServiceTest {

    @InjectMocks
    private CompraServiceImpl compraServiceImpl;

	@Mock
	private CompraRepository compraRepository;

    
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void updateCompraTest()
    {
    	
    	CompraRestDto compraRestDto = new CompraRestDto();
    	compraRestDto.setFecha(new Date());
    	
    	Compra compra = new Compra();
    	compra.setFecha(compraRestDto.getFecha());
    	compra.setId(1L);


		Mockito.when(compraRepository.save(Mockito.any()))
				.thenReturn(compra);

    	CompraRestDto compraRestDtoResp = compraServiceImpl.updateCompra(compraRestDto);
 	
        assertEquals(compraRestDto.getFecha(), compraRestDtoResp.getFecha());
    }
}
