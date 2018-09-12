package ar.com.flexibility.examen.app.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

/**
 * Simple exception with a message, that returns an Conflict code.
 */
public class InsufficientStockException extends AbstractThrowableProblem {

    public InsufficientStockException(String productName) {
        super(ErrorConstants.DEFAULT_TYPE, "Insufficient stock for: " + productName, Status.CONFLICT);
    }
}
