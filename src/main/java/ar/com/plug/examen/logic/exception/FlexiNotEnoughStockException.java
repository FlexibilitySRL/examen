package ar.com.plug.examen.logic.exception;

/**
 * System:                  FlexiTest
 * Name:                    FlexiNotEnoughStockException
 * Description:             Exception for handling errors of general business logic
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class FlexiNotEnoughStockException extends FlexiBaseException
{
    public FlexiNotEnoughStockException()
    {
        super( "You are trying to buy more stock than there are left. Please check and try again" );
    }
}