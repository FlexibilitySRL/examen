package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.app.api.TraderApi;
import ar.com.plug.examen.app.dto.TraderDto;
import ar.com.plug.examen.domain.service.TraderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/trader")
@RequiredArgsConstructor
@Slf4j
public class TraderController {

    private final TraderService traderService;

    /**
     * A description of the entire Java function.
     *
     * @param  traderApi	description of parameter
     * @return         	description of return value
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private TraderDto addTrader(@RequestBody TraderApi traderApi) {
        TraderDto trader = traderService.addTrader(traderApi);
        log.info("TraderController :: addTrader :: Trader added {}", trader);
        return trader;
    }

    /**
     * Find all traders.
     *
     * @return         list of trader DTOs
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TraderDto> findAll() {
        List<TraderDto> traders = traderService.findAll();
        log.info("TraderController :: findAll :: FindAll {}", traders.size());
        return traders;
    }

    /**
     * Find a trader by ID.
     *
     * @param  id   The ID of the trader to find
     * @return      The trader DTO found by the ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TraderDto> findTraderById(@PathVariable("id") Long id) {
        TraderDto trader = traderService.findTraderById(id);
        if (Objects.isNull(trader)) {
            log.error("TraderController :: findTraderById :: Trader not found");
            return ResponseEntity.notFound().build();
        }
        log.info("TraderController :: findTraderById :: Trader found {}", trader);
        return ResponseEntity.ok(trader);
    }

    /**
     * updateTrader function updates a trader with the given id using the provided traderApi
     *
     * @param  id        the id of the trader to update
     * @param  traderApi the traderApi object containing the updated trader information
     * @return          the updated traderDto object
     */
    @PutMapping("/{id}")
    public ResponseEntity<TraderDto> updateTrader(@PathVariable("id") Long id, @RequestBody TraderApi traderApi) {
        TraderDto traderUpdated = traderService.updateTrader(id, traderApi);
        if (Objects.isNull(traderUpdated)) {
            log.error("TraderController :: updateTrader :: Trader not found");
            return ResponseEntity.notFound().build();
        }
        log.info("TraderController :: updateTrader :: Trader was updated {}", traderUpdated);
        return ResponseEntity.ok(traderUpdated);
    }

    /**
     * Delete a trader by ID.
     *
     * @param  id   the ID of the trader to delete
     * @return      the deleted trader DTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<TraderDto> deleteTrader(@PathVariable("id") Long id) {
        TraderDto traderDeleted = traderService.deleteTrader(id);
        if (Objects.isNull(traderDeleted)) {
            log.error("TraderController :: deleteTrader :: Trader not found");
            return ResponseEntity.notFound().build();
        }
        log.info("TraderController :: deleteTrader :: Trader {} was deleted", id);
        return ResponseEntity.ok(traderDeleted);
    }
}
