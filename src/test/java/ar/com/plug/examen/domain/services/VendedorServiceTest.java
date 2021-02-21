package ar.com.plug.examen.domain.services;

import ar.com.plug.examen.domain.dtos.VendedorDTO;
import ar.com.plug.examen.domain.mappers.VendedorMapper;
import ar.com.plug.examen.domain.models.Vendedor;
import ar.com.plug.examen.domain.repository.VendedorRepository;
import ar.com.plug.examen.domain.serviceimpl.VendedorServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class VendedorServiceTest {




    @Test
    public void testGetVendedores() {

        VendedorServiceImpl vendedorService = new VendedorServiceImpl();
        vendedorService.setVendedorRepository(Mockito.mock(VendedorRepository.class));
        vendedorService.setVendedorMapper(Mockito.mock(VendedorMapper.class));

        VendedorDTO v = new VendedorDTO();
        v.setApellido("olala");
        v.setDocumento("12333333");
        v.setNombre("hernan");
        v.setNumLegajo(12345);
        VendedorDTO ultimo =vendedorService.save(v);

        //int total =vendedorService.getAll().size();
        Mockito.when(vendedorService.getAll());

        Assert.assertEquals(12345,ultimo.getNumLegajo() );
    }
}
