package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.logic.exception.FlexiIncompleteDataException;

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
    protected static void validate( long id )
    {
        if ( id <= 0 )
        {
            throw new FlexiIncompleteDataException( "id" );
        }
    }

    protected static void isBlank( String string, String attribute )
    {
        if ( string.chars().allMatch( Character::isWhitespace ) )
        {
            throw new FlexiIncompleteDataException( attribute );
        }
    }
}
