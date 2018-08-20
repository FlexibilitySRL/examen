package ar.com.flexibility.examen.app.rest.mapper;

import ar.com.flexibility.examen.app.api.SellerApi;
import ar.com.flexibility.examen.domain.model.Seller;

/**
 * Mapper to transform {@link SellerApi} to {@link Seller} and vice versa.
 */
public class SellerApiMapper implements EntityMapper<SellerApi, Seller> {

    /**
     * {@inheritDoc}
     */
    public SellerApi buildApi(Seller entity) {
        return SellerApi.newBuilder()
                .setId(entity.getId())
                .setDateCreated(entity.getDateCreated())
                .setName(entity.getName())
                .setDocumentId(entity.getDocumentId())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    public Seller buildEntity(SellerApi api) {
        return new Seller(
                api.getId(),
                api.getDateCreated(),
                api.getName(),
                api.getDocumentId());
    }
}
