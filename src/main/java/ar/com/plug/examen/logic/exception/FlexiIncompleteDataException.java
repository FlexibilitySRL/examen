package ar.com.plug.examen.logic.exception;

/**
 * System:                  FlexiTest
 * Name:                    FlexiIncompleteDataException
 * Description:             Exception for handling errors when a trying to persist a record but there's missing data
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class FlexiIncompleteDataException extends FlexiBaseException
{
    public FlexiIncompleteDataException( String missing )
    {
        super( "Incomplete data. Please review missing data on attribute: " + missing );
    }
}
