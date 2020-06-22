package ar.com.flexibility.examen.domain.service;

import ar.com.flexibility.examen.app.api.VendorApi;

import java.util.List;

public interface VendorService {
    /**
     * Retrieves a all the vendor in the database
     *
     * @return List<VendorApi>
     */
    List<VendorApi> all();

    /**
     * Retrieves one vendor from the database
     *
     * @param id identifying the vendor searched
     * @return VendorApi
     */
    VendorApi get(Long id);

    /**
     * Create a new vendor entry in the database
     *
     * @param vendorApi with the values to be created
     * @return VendorApi
     */
    VendorApi create(VendorApi vendorApi);

    /**
     * Updates an existing vendor in the database
     *
     * @param id        the id of the vendor to be updated
     * @param vendorApi the new vendor information
     * @return VendorApi
     */
    VendorApi update(Long id, VendorApi vendorApi);

    /**
     * Removes a vendor from the database
     *
     * @param id identifying the vendor to remove
     */
    void remove(Long id);
}
