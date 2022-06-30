package ar.com.plug.examen.domain.enums;

public enum TransactionStatusEnum {

    PENDING("pnd"),
    APROVED("aprb");

    private String code;

    TransactionStatusEnum(String code){
        this.code= code;
    }

    public String getCode() {
        return code;
    }
}
