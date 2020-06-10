package ar.com.flexibility.examen.domain.enums;

public enum TransactionStatus implements Encodeable {

    PENDING("pnd"), APPROVED("aprb");

    private String code;

    TransactionStatus(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
