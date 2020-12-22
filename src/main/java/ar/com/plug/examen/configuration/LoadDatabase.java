package ar.com.plug.examen.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.com.plug.examen.domain.repository.CustomerRepository;
import ar.com.plug.examen.domain.repository.OperationRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.SalespersonRepository;

@Configuration
class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(CustomerRepository customerRepository, SalespersonRepository salespersonRepository, ProductRepository productRepository, OperationRepository operationRepository) {

    return args -> {
//    	customerRepository.save(new Customer(0, "PFIZER S.A.", "111111111", "vacuna@pfizer.com", "123456789", null));
//    	customerRepository.save(new Customer(0, "Mercedes Benz S.A.", "222222222", "mecha@mb.com", "123456789", null));
//    	salespersonRepository.save(new Salesperson(0, "Juan Manuel", "Fangio", "jmf@mail.com", null));
//    	salespersonRepository.save(new Salesperson(0, "Enzo", "Francescoli", "jmf@mail.com", null));
//    	productRepository.save(new Product(0, "Producto 1", "Descripcion producto 1", (float) 1000.1, null));
//    	productRepository.save(new Product(0, "Producto 1", "Descripcion producto 1", (float) 1000.1, null));
//    	productRepository.save(new Product(0, "Producto 1", "Descripcion producto 1", (float) 1000.1, null));
//    	productRepository.save(new Product(0, "Producto 2", "Descripcion producto 2", (float) 2000.2, null));
//    	productRepository.save(new Product(0, "Producto 2", "Descripcion producto 2", (float) 2000.2, null));
//    	productRepository.save(new Product(0, "Producto 2", "Descripcion producto 2", (float) 2000.2, null));
//    	productRepository.save(new Product(0, "Producto 2", "Descripcion producto 2", (float) 2000.2, null));
//    	productRepository.save(new Product(0, "Producto 2", "Descripcion producto 2", (float) 2000.2, null));
//    	productRepository.save(new Product(0, "Producto 2", "Descripcion producto 2", (float) 2000.2, null));
//    	productRepository.save(new Product(0, "Producto 2", "Descripcion producto 2", (float) 2000.2, null));
    };
  }
  
  
}