package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.model.Seller;
import ar.com.flexibility.examen.domain.repository.SellerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SellerServiceImplTest {

    @InjectMocks
    private SellerServiceImpl sellerService;

    @Mock
    private SellerRepository sellerRepository;

    @Test
    public void whenFindAll_thenReturnList() {
        List<Seller> sellers = getDummySellers();

        given(sellerRepository.findAll()).willReturn(sellers);

        List<Seller> pulledSellers = sellerService.retrieveSellers();

        assertThat(pulledSellers).hasSize(sellers.size());
    }

    @Test
    public void whenFindExistingSeller_thenReturnSeller() {
        List<Seller> sellers = getDummySellers();

        given(sellerRepository.findOne(1L)).willReturn(sellers.get(0));

        Seller foundSeller = sellerService.retrieveSellerById(1L);

        assertThat(foundSeller).isEqualTo(sellers.get(0));
    }

    @Test
    public void whenFindNonExistingSeller_thenReturnNull() {
        List<Seller> sellers = getDummySellers();

        given(sellerRepository.findOne(6L)).willReturn(null);

        Seller foundSeller = sellerService.retrieveSellerById(6L);

        assertThat(foundSeller).isEqualTo(null);
    }

    @Test
    public void whenAddingSeller_thenItHasAnId() {
        Seller seller = new Seller("Peter", "Parker", 12);
        Seller sellerWithId = new Seller(1L, "Peter", "Parker", 12);
        List<Seller> sellers = new ArrayList<>();

        given(sellerRepository.findAll()).willReturn(sellers);

        assertThat(sellerService.retrieveSellers()).hasSize(0);

        sellers.add(sellerWithId);

        given(sellerRepository.save(seller)).willReturn(sellerWithId);

        Seller retrievedSeller = sellerService.addSeller(seller);

        given(sellerRepository.findAll()).willReturn(sellers);

        List<Seller> retrievedSellers = sellerService.retrieveSellers();

        assertThat(retrievedSellers).hasSize(1);
        assertThat(retrievedSeller.getId()).isEqualTo(1);
    }

    @Test
    public void whenUpdatingExistingSeller_thenItHasFieldsUpdated() {
        Seller originalSeller = getDummySellers().get(0);
        Seller updatedSeller = new Seller(1L, "Peter", "Parker", 100);

        given(sellerRepository.exists(1L)).willReturn(true);
        given(sellerRepository.save(originalSeller)).willReturn(updatedSeller);

        Seller retrievedSeller = sellerService.updateSeller(originalSeller.getId(), originalSeller);

        assertThat(retrievedSeller.getCommissionRate()).isEqualTo(100);
    }

    @Test
    public void whenUpdatingNonExistingSeller_thenReturnNull() {
        Seller seller = new Seller(6L, "Mark", "Hamill", 15);

        given(sellerRepository.exists(6L)).willReturn(false);

        Seller retrievedSeller = sellerService.updateSeller(4L, seller);

        assertThat(retrievedSeller).isEqualTo(null);
    }

    @Test
    public void whenDeletingExistingSeller_thenReturnTrue() {
        given(sellerRepository.exists(1L)).willReturn(true);

        assertThat(sellerService.deleteSeller(1L)).isTrue();
    }

    @Test
    public void whenDeletingNonExistingSeller_thenReturnFalse() {
        given(sellerRepository.exists(6L)).willReturn(false);

        assertThat(sellerService.deleteSeller(6L)).isFalse();
    }

    private List<Seller> getDummySellers() {
        List<Seller> sellers = new ArrayList<>();

        sellers.add(new Seller(1L, "Peter", "Parker", 12));
        sellers.add(new Seller(2L, "Bruce", "Wayne", 10));
        sellers.add(new Seller(3L, "Clark", "Kent", 7));
        sellers.add(new Seller(4L, "Natasha", "Romanov", 4));
        sellers.add(new Seller(5L, "Bruce", "Banner", 9));

        return sellers;
    }
}