package ar.com.plug.examen.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.Product;
import ar.com.plug.examen.domain.service.ServiceDomain;
import ar.com.plug.examen.infrastructure.db.entity.ProductEntity;
import ar.com.plug.examen.infrastructure.db.repository.ProductEntityRepository;
import ar.com.plug.examen.infrastructure.rest.dto.ResponseDto;
import ar.com.plug.examen.shared.config.MenssageResponse;
import ar.com.plug.examen.shared.exception.BadRequestException;
import ar.com.plug.examen.shared.exception.ConflictException;
import ar.com.plug.examen.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ServiceDomain<Product> {
    private final ProductEntityRepository productEntityRepository;
    private final MenssageResponse menssageResponse;

    @Override
    public Product findById(String id) {
        log.info("Inicia busqueda del producto con id:{}", id);
        return productEntityRepository.findById(id).orElseThrow(() -> {
            log.error("No existe el producto con id: {}", id);
            return new NotFoundException(ResponseDto.builder()
                    .code(MenssageResponse.P404)
                    .message(menssageResponse.getMessages().get(MenssageResponse.P404).concat(id))
                    .build());
        }).toProduct();
    }

    @Override
    public List<Product> findAllByFilter(Product product) {
        log.info("Inicia listado de productos con filtro:{}", product);
        List<ProductEntity> lista = (List<ProductEntity>) (productEntityRepository.findAll());
        return lista.stream()
                .filter(productEntity -> (productEntity.getName().isEmpty() ? false
                        : Objects.equals(product.getName(), productEntity.getName()))
                        || (product.getDescription().isEmpty()
                                ? false
                                : Objects.equals(product.getDescription(), productEntity.getDescription())))
                .map(ProductEntity::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Product create(Product product) {
        log.info("Inicia la creacion del producto:{}", product);
        this.validateIfExistsByBarCode(product.getBarCode());
        return productEntityRepository.save(new ProductEntity(product)).toProduct();
    }

    @Override
    public Product upDate(Product product) {
        log.info("Inicia actualizacion del producto:{}", product);
        if (Objects.isNull(product.getId()) || product.getId().isEmpty()) {
            throw new BadRequestException(ResponseDto.builder()
                    .code(MenssageResponse.P407)
                    .message(menssageResponse.getMessages().get(MenssageResponse.P407))
                    .build());
        }
        ProductEntity productEntity = productEntityRepository.findById(product.getId())
                .orElseThrow(() -> {
                    log.error("No existe el producto con id: {}", product.getId());
                    return new NotFoundException(ResponseDto.builder()
                            .code(MenssageResponse.P404)
                            .message(menssageResponse.getMessages().get(MenssageResponse.P404).concat(product.getId()))
                            .build());
                });
        if (!Objects.equals(product.getBarCode(), productEntity.getBarCode()))
            this.validateIfExistsByBarCode(product.getBarCode());
        return productEntityRepository.save(productEntity.upDate(product)).toProduct();

    }

    @Override
    public void remove(String id) {
        log.info("Inicia eliminacion del producto con id:{}", id);
        ProductEntity productEntity = productEntityRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No existe el producto con id: {}", id);
                    return new NotFoundException(ResponseDto.builder()
                            .code(MenssageResponse.P404)
                            .message(menssageResponse.getMessages().get(MenssageResponse.P404).concat(id))
                            .build());
                });
        productEntityRepository.delete(productEntity);
    }

    private void validateIfExistsByBarCode(String barCode) {
        if (productEntityRepository.findByBarCode(barCode).isPresent()) {
            log.error("Ya existe un producto con barCode: {}", barCode);
            throw new ConflictException(ResponseDto.builder()
                    .code(MenssageResponse.P408)
                    .message(menssageResponse.getMessages().get(MenssageResponse.P408).concat(barCode))
                    .build());
        }
    }

}
