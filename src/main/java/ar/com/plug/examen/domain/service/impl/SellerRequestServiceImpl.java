package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.app.api.SellerDTO;
import ar.com.plug.examen.app.dtoResponse.SellerResponseDTO;
import ar.com.plug.examen.app.dtoResponse.ErrorDTO;
import ar.com.plug.examen.app.dtoResponse.ListSellerResponseDTO;
import ar.com.plug.examen.app.entity.SellerEntity;
import ar.com.plug.examen.app.enumeration.ErrorCodeEnumeration;
import ar.com.plug.examen.app.exception.ApiException;
import ar.com.plug.examen.domain.model.Seller;
import ar.com.plug.examen.domain.repository.SellerRepository;
import ar.com.plug.examen.domain.service.ISellerRequestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SellerRequestServiceImpl implements ISellerRequestService {

    private final SellerRepository sellerRepository;
    Log log = LogFactory.getLog(this.getClass());

    @Autowired
    public SellerRequestServiceImpl (final SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Transactional(readOnly = false)
    public SellerResponseDTO create(SellerDTO dto) throws IOException {
        try {
            log.info("seller - create - " + dto.toString());

            SellerEntity newSeller = SellerEntity.builder()
                    .documentNumber(dto.getPersonalData().getDocument_number())
                    .firstName(dto.getPersonalData().getFirst_name())
                    .lastName(dto.getPersonalData().getLast_name())
                    .phoneNumber(dto.getPhoneNumber())
                    .build();

            newSeller = sellerRepository.save(newSeller);

            if (newSeller != null) {
                log.info("seller - create - SUCCESS - seller_id: " + newSeller.getSellerId());
                return SellerResponseDTO.builder()
                        .sellerId(newSeller.getSellerId())
                        .status(HttpStatus.OK.toString())
                        .code("CREATED").build();
            }

            log.error("seller - create - ERROR");
            return SellerResponseDTO.builder()
                    .sellerId(newSeller.getSellerId() != null ? newSeller.getSellerId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("seller - create - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public SellerResponseDTO update(SellerDTO dto) throws IOException {
        try {
            log.info("seller - update - " + dto.toString());
            SellerEntity updateSeller = sellerRepository.findByDocumentNumber(dto.getPersonalData().getDocument_number());

            if (updateSeller != null) {
                log.info("seller - update - CLIENT EXIST");
                updateSeller.setFirstName(dto.getPersonalData().getFirst_name());
                updateSeller.setLastName(dto.getPersonalData().getLast_name());
                updateSeller.setPhoneNumber(dto.getPhoneNumber());

                sellerRepository.save(updateSeller);

                log.info("seller - update - SUCCESS - seller_id: " + updateSeller.getSellerId());
                return SellerResponseDTO.builder()
                        .sellerId(updateSeller.getSellerId())
                        .status(HttpStatus.OK.toString())
                        .code("UPDATED").build();
            }

            log.error("seller - update - CLIENT NOT EXIST");
            return SellerResponseDTO.builder()
                    .sellerId(updateSeller.getSellerId() != null ? updateSeller.getSellerId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("seller - update - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    @Transactional(readOnly = false)
    public SellerResponseDTO delete(Integer document) throws IOException {
        try {
            log.info("seller - delete - " + document);
            SellerEntity deleteSeller = sellerRepository.findByDocumentNumber(document);

            if (deleteSeller != null) {
                log.info("seller - delete - CLIENT EXIST");
                sellerRepository.delete(deleteSeller);

                log.info("seller - delete - SUCCESS - seller_id: " + deleteSeller.getSellerId());
                return SellerResponseDTO.builder()
                        .sellerId(deleteSeller.getSellerId())
                        .status(HttpStatus.OK.toString())
                        .code("DELETED").build();
            }

            log.error("seller - delete - CLIENT NOT EXIST");
            return SellerResponseDTO.builder()
                    .sellerId(deleteSeller.getSellerId() != null ? deleteSeller.getSellerId() : 0)
                    .status(HttpStatus.BAD_REQUEST.toString())
                    .code("FAILED").build();

        } catch (Exception e) {
            log.error("seller - update - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }

    public ListSellerResponseDTO list() throws IOException {
        try {
            log.info("seller - list");
            List<SellerEntity> listSeller = sellerRepository.findAll();
            ListSellerResponseDTO listSellers = new ListSellerResponseDTO();
            listSellers.setSellers(new ArrayList<>());

            if (listSeller != null) {
                log.info("seller - list - LIST SIZE: " + listSeller.size());
                for (SellerEntity sellerEntity : listSeller) {
                    listSellers.getSellers().add(new Seller(
                            sellerEntity.getSellerId(),
                            sellerEntity.getDocumentNumber(),
                            sellerEntity.getFirstName(),
                            sellerEntity.getLastName(),
                            sellerEntity.getPhoneNumber()
                    ));
                }

                return listSellers;
            }
            log.error("seller - list - LIST NOT EXIST");
            return null;
        } catch (Exception e) {
            log.error("seller - list - ERROR: " + e.getMessage());
            throw new ApiException(ErrorDTO.map(ErrorCodeEnumeration.INTERNAL_ERROR));
        }
    }
}
