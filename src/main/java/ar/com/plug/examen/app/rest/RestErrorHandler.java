package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.logic.exception.FlexiAmountsDoesntMatchException;
import ar.com.plug.examen.logic.exception.FlexiIncompleteDataException;
import ar.com.plug.examen.logic.exception.FlexiInvalidOperationException;
import ar.com.plug.examen.logic.exception.FlexiNotEnoughStockException;
import ar.com.plug.examen.logic.exception.FlexiNotFoundException;
import ar.com.plug.examen.logic.exception.FlexiRegexException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * System:                 FlexiTest
 * Name:                   RestErrorHandler
 * Description:            Class that handles all exceptions returns to the client
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
@ControllerAdvice
public class RestErrorHandler
{
    @ExceptionHandler( FlexiNotFoundException.class )
    @ResponseStatus( HttpStatus.NOT_FOUND )
    @ResponseBody
    public Object processValidationError( FlexiNotFoundException ex )
    {
        return ex;
    }

    @ExceptionHandler( Exception.class )
    @ResponseStatus( HttpStatus.INTERNAL_SERVER_ERROR )
    @ResponseBody
    public Object processValidationError( Exception ex )
    {
        return ex;
    }

    @ExceptionHandler( FlexiIncompleteDataException.class )
    @ResponseStatus( HttpStatus.EXPECTATION_FAILED )
    @ResponseBody
    public Object processValidationError( FlexiIncompleteDataException ex )
    {
        return ex;
    }

    @ExceptionHandler( FlexiRegexException.class )
    @ResponseStatus( HttpStatus.EXPECTATION_FAILED )
    @ResponseBody
    public Object processValidationError( FlexiRegexException ex )
    {
        return ex;
    }

    @ExceptionHandler( FlexiAmountsDoesntMatchException.class )
    @ResponseStatus( HttpStatus.EXPECTATION_FAILED )
    @ResponseBody
    public Object processValidationError( FlexiAmountsDoesntMatchException ex )
    {
        return ex;
    }

    @ExceptionHandler( FlexiInvalidOperationException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ResponseBody
    public Object processValidationError( FlexiInvalidOperationException ex )
    {
        return ex;
    }

    @ExceptionHandler( FlexiNotEnoughStockException.class )
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    @ResponseBody
    public Object processValidationError( FlexiNotEnoughStockException ex )
    {
        return ex;
    }
}