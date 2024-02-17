package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.TraderApi;
import ar.com.plug.examen.app.dto.TraderDto;
import ar.com.plug.examen.domain.mapper.TraderMapper;
import ar.com.plug.examen.domain.model.Trader;
import ar.com.plug.examen.domain.repository.TraderRepository;
import ar.com.plug.examen.domain.service.impl.TraderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TraderServiceTest {
    @Mock
    private TraderRepository traderRepository;

    @InjectMocks
    private TraderServiceImpl traderService;

    @Captor
    private ArgumentCaptor<Trader> traderCaptor;


    @Test
    public void testAddValidTrader() {
        TraderApi TraderRequest = TraderApi.builder()
                .name("Trader Name")
                .build();
        Trader Trader = TraderMapper.toTrader(TraderRequest);
        Trader.setId(1L);
        when(traderRepository.save(any())).thenReturn(Trader);

        TraderDto response = traderService.addTrader(TraderRequest);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Trader Name", response.getName());
    }

    @Test
    public void testAddNullTrader() {
        TraderApi traderRequest = null;

        assertThrows(IllegalArgumentException.class, () -> {
            traderService.addTrader(traderRequest);
        });
    }

    @Test
    public void testFindAllEmptyList() {
        // Arrange
        when(traderRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Trader> result = traderRepository.findAll();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindAllCorrectNumberOfTraderResponses() {
        // Arrange
        List<Trader> Traders = Arrays.asList(new Trader(), new Trader());
        when(traderRepository.findAll()).thenReturn(Traders);

        // Act
        List<TraderDto> result = traderService.findAll();

        // Assert
        assertEquals(Traders.size(), result.size());
    }

    @Test
    void testFindTraderById_ValidId_ReturnsTraderDto() {
        // Arrange
        Long traderId = 1L;
        Trader trader = new Trader();
        when(traderRepository.findById(traderId)).thenReturn(Optional.of(trader));

        // Act
        TraderDto result = traderService.findTraderById(traderId);

        // Assert
        assertNotNull(result);
    }

    @Test
    void testFindTraderById_NullId_ReturnsNull() {
        // Arrange
        Long traderId = null;

        // Act
        TraderDto result = traderService.findTraderById(traderId);

        // Assert
        assertNull(result);
    }

    @Test
    void testFindTraderById_NonExistingId_ReturnsNull() {
        // Arrange
        Long traderId = 2L;
        when(traderRepository.findById(traderId)).thenReturn(Optional.empty());

        // Act
        TraderDto result = traderService.findTraderById(traderId);

        // Assert
        assertNull(result);
    }
    @Test
    void testFindTraderById_validId() {
        Long validId = 1L;
        Trader mockTrader = new Trader();
        when(traderRepository.findById(validId)).thenReturn(Optional.of(mockTrader));

        TraderDto result = traderService.findTraderById(validId);

        assertNotNull(result);
    }

    @Test
    public void testUpdateExistingTrader() {
        // Arrange
        Long id = 1L;
        TraderApi TraderRequest = TraderApi.builder()
                .name("Ordinary name")
                .build();
        Trader existingTrader = Trader.builder()
                .id(1L)
                .name("Super Trader")
                .build();
        when(traderRepository.findById(id)).thenReturn(Optional.of(existingTrader));

        // Act
        traderService.updateTrader(id, TraderRequest);

        // Assert
        verify(traderRepository).save(traderCaptor.capture());
        assertEquals(TraderRequest.getName(), traderCaptor.getValue().getName());
    }

    @Test
    public void testUpdateNonExistingTrader() {
        // Arrange
        Long id = 2L;
        TraderApi traderRequest = new TraderApi();
        when(traderRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        TraderDto updatedTraderDto = traderService.updateTrader(id, traderRequest);

        // Assert
        assertNull(updatedTraderDto);
    }

    @Test
    void testDeleteTrader_Exists() {
        Long id = 1L;
        Trader trader = new Trader();
        when(traderRepository.findById(id)).thenReturn(Optional.of(trader));

        TraderDto result = traderService.deleteTrader(id);

        verify(traderRepository).deleteById(id);
        assertNotNull(result);
    }

    @Test
    void testDeleteTrader_NotExists() {
        Long id = 1L;
        when(traderRepository.findById(id)).thenReturn(Optional.empty());

        TraderDto result = traderService.deleteTrader(id);

        verify(traderRepository, never()).deleteById(id);
        assertNull(result);
    }

    @Test
    void testDeleteTrader_NullId() {
        Long id = null;

        TraderDto result = traderService.deleteTrader(id);

        verify(traderRepository, never()).deleteById(any());
        assertNull(result);
    }
}