package ar.com.plug.examen.domain.mapper;

import ar.com.plug.examen.app.api.TraderApi;
import ar.com.plug.examen.app.dto.TraderDto;
import ar.com.plug.examen.domain.model.Trader;

public class TraderMapper {

    public static Trader toTrader(TraderApi traderApi) {
        return Trader.builder()
                .name(traderApi.getName())
                .build();
    }

    public static TraderDto toTraderDto(Trader trader) {
        return TraderDto.builder()
                .id(trader.getId())
                .name(trader.getName())
                .build();
    }

    public static Trader updateTrader(Trader trader, TraderApi traderApi) {
        trader.setName(traderApi.getName());
        return trader;
    }
}
