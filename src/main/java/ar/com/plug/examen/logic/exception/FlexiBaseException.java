package ar.com.plug.examen.logic.exception;

import java.io.Serializable;

/**
 * System:                  FlexiTest
 * Name:                    FlexiBaseException
 * Description:             Base Exception class for handling personalized errors inside FlexiTest's API
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class FlexiBaseException extends RuntimeException implements Serializable
{
    public FlexiBaseException( Exception e )
    {
        super( e );
    }

    public FlexiBaseException( Exception e, String str )
    {
        super( str, e );
    }

    public FlexiBaseException( String str )
    {
        super( str );
    }

    public FlexiBaseException( )
    {
        super();
    }
}
