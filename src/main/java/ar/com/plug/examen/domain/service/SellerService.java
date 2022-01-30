package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.app.api.SellerDTO;
import java.util.List;

/**
 * Service to save, update, delete and get sellers
 */
public interface SellerService
{
    /**
     * Save a seller by the given sellerDTO
     *
     * @param sellerDTO
     * @return SellerDTO
     */
    SellerDTO save(SellerDTO sellerDTO);

    /**
     * Get all sellers
     *
     * @return List<SellerDTO>
     */
    List<SellerDTO> getAllSellers();

    /**
     * Gets seller by id
     *
     * @param id
     * @return SellerDTO
     */
    SellerDTO getSellerById(Long id);

    /**
     * Update a seller given a sellerDTO
     *
     * @param sellerDTO
     * @return SellerDTO
     */
    SellerDTO update(SellerDTO sellerDTO);

    /**
     * Delete a seller by Id
     *
     * @param id
     */
    void delete(Long id);


}
