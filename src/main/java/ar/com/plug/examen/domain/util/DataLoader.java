package ar.com.plug.examen.domain.util;

import ar.com.plug.examen.domain.model.Client;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Trader;
import ar.com.plug.examen.domain.repository.ClientRepository;
import ar.com.plug.examen.domain.repository.ProductRepository;
import ar.com.plug.examen.domain.repository.TraderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final TraderRepository traderRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        log.info("Loading client data...");
        if (clientRepository.findAll().isEmpty()) {
            clientRepository.saveAll(
                    Arrays.asList(
                            Client.builder().name("Guybrush Threepwood").email("guybrush@gmail.com").build(),
                            Client.builder().name("Elaine Marley").email("elaine@gmail.com").build(),
                            Client.builder().name("LeChuck Pirate").email("lechuck@gmail.com").build(),
                            Client.builder().name("Herman Toothrot").email("Herman@gmail.com").build()
                    )
            );
        }
        log.info("Data client loaded.");

        log.info("Loading trader data...");
        if (traderRepository.findAll().isEmpty()) {
            traderRepository.saveAll(
                    Arrays.asList(
                            Trader.builder().name("Stan S. Stanman").build(),
                            Trader.builder().name("Wally B. Feed").build()
                    )
            );
        }
        log.info("Data trader loaded.");

        log.info("Loading products data...");
        if (productRepository.findAll().isEmpty()) {
            productRepository.saveAll(
                    Arrays.asList(
                            Product.builder().sku("00000001").name("Big Whoop Map").description("The Biggest treasure of pirates").price(2000.0).build(),
                            Product.builder().sku("00000002").name("Stan's ship").description("Regular ship").price(800.0).build(),
                            Product.builder().sku("00000003").name("Rubber Chicken").description("Is noisy").price(100.0).build(),
                            Product.builder().sku("00000004").name("Sword").description("Master Sword's sword").price(1500.0).build(),
                            Product.builder().sku("00000005").name("Grog").description("The Best Pirate's drink").price(50.0).build()
                    )
            );
        }
        log.info("Data products loaded.");
    }
}
