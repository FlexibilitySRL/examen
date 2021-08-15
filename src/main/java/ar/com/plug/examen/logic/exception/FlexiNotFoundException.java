package ar.com.plug.examen.logic.exception;

/**
 * System:                  FlexiTest
 * Name:                    FlexiNotFoundException
 * Description:             Exception for handling errors when a trying to find a resource that is not persisted
 *                              on the system
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class FlexiNotFoundException extends FlexiBaseException
{
    public FlexiNotFoundException()
    {
        super( ( "The resource that you are trying to find doesn't exist." ) );
    }
}
