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
    public static List<ProductDTO> mapEntityToDtoList( List<Product> entityList )
    {
        return new ArrayList<ProductDTO>() {{
            if ( !entityList.isEmpty() )
            {
                for( Product entity : entityList )
                {
                    add( mapEntityToDto( entity ) );
                }
            }
        }};
    }

    public static ProductDTO mapEntityToDto( Product entity )
    {
        final ProductDTO answer = DTOFactory.createProductDTO( entity.getId() );

        answer.setDescription( entity.getDescription() );
        answer.setSellerName( entity.getSeller().getFullName() );
        answer.setSellerId( entity.getSeller().getId() );
        answer.setName( entity.getName() );
        answer.setPrice( entity.getPrice() );
        answer.setStock( entity.getStock() );

        return answer;
    }

    public static Product mapIdToEntity( long id )
    {
        BaseMapper.validate( id, "productId" );

        return EntityFactory.createProduct( id );
    }

    private static Product mapAttributes( ProductDTO dto, Product answer )
    {
        answer.setName( dto.getName() );
        answer.setDescription( dto.getDescription() );
        answer.setStock( dto.getStock() );
        answer.setSeller( SellerMapper.mapIdToEntity( dto.getSellerId() ) );
        answer.setPrice( dto.getPrice() );

        return answer;
    }

    public static Product mapRegisterDtoToEntity( ProductDTO dto )
    {
        final Product answer = EntityFactory.createProduct();

        validateRegister( dto );
        mapAttributes( dto, answer );

        return answer;
    }

    private static void validateRegister( ProductDTO dto )
    {
        BaseMapper.isBlank( dto.getName(), "name" );
        BaseMapper.isBlank( dto.getDescription(), "description" );
        BaseMapper.validate( dto.getStock(), "stock" );
        BaseMapper.validate( dto.getSellerId(), "sellerId" );
        BaseMapper.validate( dto.getPrice(), "price" );
    }

    public static Product mapUpdateDtoToEntity( ProductDTO dto )
    {
        final Product answer = EntityFactory.createProduct( dto.getId() );

        validateRegister( dto );
        mapAttributes( dto, answer );

        return answer;
    }

    public static Product mapUpdateEntityToEntity( Product dbEntity, Product incoming )
    {
        dbEntity.setName( incoming.getName() );
        dbEntity.setDescription( incoming.getDescription() );
        dbEntity.setStock( incoming.getStock() );
        dbEntity.setSeller( incoming.getSeller() );
        dbEntity.setPrice( incoming.getPrice() );

        return dbEntity;
    }
}