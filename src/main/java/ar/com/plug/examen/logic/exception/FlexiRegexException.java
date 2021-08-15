package ar.com.plug.examen.logic.exception;

/**
 * System:                  FlexiTest
 * Name:                    FlexiRegexException
 * Description:             Exception for handling errors when a trying to persist a record but format isn't as expected
 *
 * @author teixbr
 * @version 1.0
 * @since 15/08/21
 */
public class FlexiRegexException extends FlexiBaseException
{
    public FlexiRegexException( String attribute, String data )
    {
        super( "Non compliant format on attribute: " + attribute + " data: " + data );
    }
}