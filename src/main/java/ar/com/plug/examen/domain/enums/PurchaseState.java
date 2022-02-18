package ar.com.plug.examen.domain.enums;

import java.util.Arrays;

public enum PurchaseState {

    SOLICITADA("0"), APROBADA("1");

    public final String valor;

    PurchaseState(String valor) {
        this.valor = valor;
    }

    public static PurchaseState valueOfLabel(String valor) {
        return Arrays.stream(values())
                .filter(purchase -> purchase.valor.equals(valor))
                .findFirst()
                .orElse(null);
    }
}