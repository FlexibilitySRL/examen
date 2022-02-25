package ar.com.plug.examen.domain.enums;

import java.util.Arrays;

public enum Result {
    ERROR("0"), SUCCESS("1");

    public final String valor;

    Result(String valor) {
        this.valor = valor;
    }

    public static Result valueOfLabel(String valor) {
        return Arrays.stream(values())
                .filter(result -> result.valor.equals(valor))
                .findFirst()
                .orElse(null);
    }
}
