package ar.com.plug.examen.utils;

public enum TransactionStatus {


    APPROVED("A", "Transaccion Aprobada"),
    REJECTED("R","Transaccion Rechazada"),
    PENDING("P", "Transaccion Pendiente");

    private final String code;
    private final String message;



    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    private TransactionStatus(String code, String message){
        this.code = code;
        this.message = message;
    }



}
