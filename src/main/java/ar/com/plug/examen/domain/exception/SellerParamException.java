package ar.com.plug.examen.domain.exception;

public class SellerParamException extends RuntimeException{
        public SellerParamException() {
            super();
        }

        public SellerParamException(String message, Throwable cause) {
            super(message, cause);
        }

        public SellerParamException(String message) {
            super(message);
        }

        public SellerParamException(Throwable cause) {
            super(cause);
        }

}
