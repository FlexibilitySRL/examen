package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.logic.exception.FlexiIncompleteDataException;
import ar.com.plug.examen.logic.exception.FlexiRegexException;

/**
 * System:                 FlexiTest
 * Name:                   BaseMapper
 * Description:            Abstract class that handles the use of inheritance for the Mapper module
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public abstract class BaseMapper
{
    private static String EMAIL_REGEX = "^((([^<>()\\[\\]\\.,;:*\\s@\"]+(\\.[^<>()\\[\\]\\.,*;" +
                                        ":\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\" +
                                        ".[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,})))$";

    protected static void validate( long id, String attribute )
    {
        if ( id <= 0 )
        {
            throw new FlexiIncompleteDataException( attribute );
        }
    }

    protected static void validate( double value, String attribute )
    {
        if ( value <= 0.0 )
        {
            throw new FlexiIncompleteDataException( attribute );
        }
    }

    protected static void validate( int value, String attribute )
    {
        if ( value <= 0 )
        {
            throw new FlexiIncompleteDataException( attribute );
        }
    }

    protected static void isBlank( String string, String attribute )
    {
        if ( string == null || string.chars().allMatch( Character::isWhitespace ) )
        {
            throw new FlexiIncompleteDataException( attribute );
        }
    }

    public static void validateEmail( String toBeValidated )
    {
        if ( ( toBeValidated == null ) || ( !toBeValidated.matches( EMAIL_REGEX ) ) )
        {
            throw new FlexiRegexException( "email", toBeValidated );
        }
    }
}
