package ar.com.plug.examen.app.DTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(UUID id, String name, String description, BigDecimal price) {
}

