package ar.com.plug.examen.logic.exception;

/**
 * System:                  FlexiTest
 * Name:                    FlexiInvalidOperationException
 * Description:             Exception for handling errors of general business logic
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class FlexiInvalidOperationException extends FlexiBaseException
{
    public FlexiInvalidOperationException()
    {
        super( "You are trying to perform an invalid operation. Please check" );
    }
}