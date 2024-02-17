package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.TraderApi;
import ar.com.plug.examen.app.dto.TraderDto;
import ar.com.plug.examen.domain.mapper.TraderMapper;
import ar.com.plug.examen.domain.repository.TraderRepository;
import ar.com.plug.examen.domain.service.TraderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TraderServiceImpl implements TraderService {

    private final TraderRepository traderRepository;

    /**
     * Adds a new trader based on the provided trader request.
     *
     * @param  traderApi   the trader request to be added
     * @return                   the response containing the newly added trader
     */
    @Override
    public TraderDto addTrader(TraderApi traderApi) {
        if (traderApi == null) {
            log.error("TraderService :: addTrader :: TraderRequest cannot be null");
            throw new IllegalArgumentException("TraderRequest cannot be null");
        }

        var newTrader = TraderMapper.toTrader(traderApi);
        newTrader = traderRepository.save(newTrader);
        log.info("TraderService :: addTrader :: Trader added {}", newTrader);
        return TraderMapper.toTraderDto(newTrader);
    }

    @Override
    public List<TraderDto> findAll() {
        var traders = traderRepository.findAll();
        log.info("TraderService :: findAll :: FindAll {}", traders.size());
        return traders.stream()
                .map(TraderMapper::toTraderDto)
                .collect(Collectors.toList());
    }

    /**
     * Find a trader by ID.
     *
     * @param  id  the ID of the trader to find
     * @return     the trader DTO if found, otherwise null
     */
    @Override
    public TraderDto findTraderById(Long id) {
        var trader = traderRepository.findById(id).orElse(null);
        if (Objects.isNull(trader)) {
            log.error("TraderService :: findTraderById :: Trader not found");
            return null;
        }
        log.info("TraderService :: findTraderById :: Trader found {}", trader);
        return TraderMapper.toTraderDto(trader);
    }

    /**
     * Update a trader with the given ID using the provided trader request.
     *
     * @param  id            the ID of the trader to be updated
     * @param  traderRequest the trader request containing updated information
     * @return               the updated trader DTO
     */
    @Override
    public TraderDto updateTrader(Long id, TraderApi traderRequest) {
        var trader = traderRepository.findById(id).orElse(null);
        if (Objects.isNull(trader)) {
            log.error("TraderService :: updateTrader :: Trader not found");
            return null;
        }
        var traderUpdated = TraderMapper.updateTrader(trader, traderRequest);
        traderRepository.save(traderUpdated);
        log.info("TraderService :: updateTrader :: Trader was updated {}", traderUpdated);
        return TraderMapper.toTraderDto(traderUpdated);
    }

    /**
     * Delete a trader by ID.
     *
     * @param  id   the ID of the trader to delete
     * @return      the deleted trader DTO, or null if the trader was not found
     */
    @Override
    public TraderDto deleteTrader(Long id) {
        var trader = traderRepository.findById(id).orElse(null);
        if (Objects.isNull(trader)) {
            log.error("TraderService :: deleteTrader :: Trader not found");
            return null;
        }
        traderRepository.deleteById(id);
        log.info("TraderService :: deleteTrader :: Trader {} was deleted", id);
        return TraderMapper.toTraderDto(trader);
    }
}
