package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.TraderApi;
import ar.com.plug.examen.app.dto.TraderDto;

import java.util.List;

public interface TraderService {
    TraderDto addTrader(TraderApi traderApi);

    List<TraderDto> findAll();

    TraderDto findTraderById(Long id);

    TraderDto updateTrader(Long id, TraderApi traderApi);

    TraderDto deleteTrader(Long id);
}
