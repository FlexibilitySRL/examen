package ar.com.plug.examen.logic.mapper;

import ar.com.plug.examen.domain.dto.DTOFactory;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.entity.EntityFactory;
import ar.com.plug.examen.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * System:                 FlexiTest
 * Name:                   ProductMapper
 * Description:            Class for handling mapping between Dto´s and Product Entity classes
 *
 * @author teixbr
 * @version 1.0
 * @since 14/08/21
 */
public class ProductMapper extends BaseMapper
{
    public static List<ProductDTO> mapSimpleEntityToDtoList( List<Product> entityList )
    {
        return new ArrayList<ProductDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( Product entity : entityList )
                {
                    add( mapSimpleEntityToDto( entity ) );
                }
            }
        }};
    }

    public static ProductDTO mapSimpleEntityToDto( Product entity )
    {
        final ProductDTO answer = DTOFactory.createProductDTO( entity.getId() );

        answer.setDescription( entity.getDescription() );
        answer.setOwnerFullName( UserMapper.mapUserFullName( entity.getOwner() ) );
        answer.setOwnerId( entity.getOwner().getId() );
        answer.setName( entity.getName() );

        return answer;
    }

    public static ProductDTO mapDetailedEntityToDto( Product entity )
    {
        final ProductDTO answer = DTOFactory.createProductDTO( entity.getId() );

        answer.setDescription( entity.getDescription() );
        answer.setOwnerFullName( UserMapper.mapUserFullName( entity.getOwner() ) );
        answer.setOwnerId( entity.getOwner().getId() );
        answer.setName( entity.getName() );
        answer.setStock( entity.getStock() );

        return answer;
    }

    public static Product mapIdToEntity( long id )
    {
        BaseMapper.validate( id );

        return EntityFactory.createProduct( id );
    }
}