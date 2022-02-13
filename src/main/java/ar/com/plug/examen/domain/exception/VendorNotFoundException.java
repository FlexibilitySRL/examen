package ar.com.plug.examen.domain.exception;

public class VendorNotFoundException extends RuntimeException {

    public VendorNotFoundException() {
        super("Vendor not found");
    }
}
