package ar.com.plug.examen.logic.exception;

/**
 * System:                  FlexiTest
 * Name:                    FlexiAmountsDoesntMatchException
 * Description:             Exception for handling errors when a trying to persist a record but format isn't as expected
 *
 * @author teixbr
 * @version 1.0
 * @since 15/08/21
 */
public class FlexiAmountsDoesntMatchException extends FlexiBaseException
{
    public FlexiAmountsDoesntMatchException()
    {
        super( "The amount on the transaction is not correct. Please check and try again." );
    }
}