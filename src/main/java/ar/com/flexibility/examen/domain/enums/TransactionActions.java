package ar.com.flexibility.examen.domain.enums;

public enum TransactionActions implements Encodeable {

    APPROVE("approve");

    private String code;

    TransactionActions(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
