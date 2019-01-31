package com.example.core.service;

import com.example.core.model.Product;
import com.example.core.model.Sale;
import com.example.core.model.SaleDetail;
import com.example.core.repository.ClientRepository;
import com.example.core.repository.ProductRepository;
import com.example.core.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SaleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaleService.class);

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity confirmBuy(Long id) {

        Sale sale = saleRepository.findProductByIdSale(id);

        if (sale == null) {
            LOGGER.info("No hay carrito pendiente {}.", id);
            return new ResponseEntity("No hay carrito pendiente " + id, HttpStatus.BAD_REQUEST);
        }

        sale.setBought(Boolean.TRUE);
        saleRepository.save(sale);
        LOGGER.info("Se realizo el pago de orden de compra.");
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    public ResponseEntity cancelBuy(Long id) {
        saleRepository.update(id, Boolean.FALSE);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /***
     * Obtenemos una lista de ordenes de compras confirmadas y pendientes.
     * @return
     */
    public List<SaleDetail> getListByIdClient(Long idClient) {
        return saleRepository.findProductsByIdClient(idClient);
    }

    /***
     * Eliminamos un producto del carrito de compras.
     * @param idCart
     * @param idDetail
     * @return
     */
    public ResponseEntity deleteFromCart(Long idCart, Long idDetail) {
        if (idCart == null || idDetail == null) {
            LOGGER.info("No existe {} o {}.", idCart, idDetail);
            return new ResponseEntity("Error en los datos de entrada", HttpStatus.BAD_REQUEST);
        }

        SaleDetail detail = saleRepository.findProductByIdSaleAndIdDetail(idCart, idDetail);
        Product product = detail.getProduct();
        product.setQuantity( product.getQuantity() + detail.getQuantity());
        productRepository.save(product);

        //Se realiza una baja logica
        saleRepository.updateCart(idCart, idDetail, Boolean.FALSE);

        LOGGER.info("Se elimino producto {} correctamente.", product.getId());
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    /***
     * Se agrega nuevos productos a un carrito, si no existe ninguno se crea uno nuevo.
     * Si el carrito existe y existe el producto se actualiza el stock.
     * Si no hay suficiente stock, calcela toda la operacion.
     * @param idSale
     * @param saleDetails
     * @return
     */
    public ResponseEntity addToCart(Long idSale, List<SaleDetail> saleDetails) {

        Sale sale = saleRepository.findProductByIdSale(idSale);
        Set<SaleDetail> details = new HashSet<>();
        SaleDetail saleDetail = null;
        Product product = null;

        //Si no hay carrito de compra, entonces se crea uno nuevo.
        if (sale == null) {
            sale = new Sale();

            for (SaleDetail detail : saleDetails) {
                saleDetail = new SaleDetail();
                saleDetail.setActive(Boolean.TRUE);
                saleDetail.setQuantity(detail.getQuantity());

                product = productRepository.findProductById(detail.getProduct().getId());
                if (product == null) {
                    LOGGER.info("No se encuentra el producto en la base de datos.");
                    return new ResponseEntity("No existe el producto", HttpStatus.BAD_REQUEST);
                }

                if (saleDetail.getQuantity() >= product.getQuantity()) {
                    product.setQuantity(product.getQuantity() - saleDetail.getQuantity());
                } else {
                    LOGGER.info("No hay suficiente stock para continuar procesando");
                    return new ResponseEntity("No hay suficiente stock", HttpStatus.BAD_REQUEST);
                }

                saleDetail.setProduct(product);
                details.add(saleDetail);
            }

            sale.setBought(Boolean.FALSE);
            LOGGER.info("Se creo nuevo carrito de compra.");
        } else {

            if (sale.getBought() == true) {
                LOGGER.info("Ya se realizo el pago de esta orden de compra.");
                return new ResponseEntity("Ya se realizo el pago de esta orden de compra.", HttpStatus.OK);
            }

            //recorremos los detalles nuevos o existentes de una lista de compras
            //y agregamos el nuevo producto o actualizando la informacion del existente.
            for (SaleDetail detail : saleDetails) {

                //verificamos si existe un detalle de venta para ese producto en esa venta pendiente.
                saleDetail = saleRepository.findProductByIdSaleAndIdProduct(idSale, detail.getProduct().getId());

                if (saleDetail == null) {
                    saleDetail = new SaleDetail();
                } else {
                    for (SaleDetail detailOrigen: sale.getSaleDetails()) {
                        if (detail.getId() == detailOrigen.getId()) {
                            saleDetail = detailOrigen;
                        }
                    }
                }

                //realizamos la consulta para saber si todavia tenemos stock del producto.
                product = productRepository.findProductById(detail.getProduct().getId());

                if (detail.getQuantity() >= product.getQuantity()) {
                    product.setQuantity(product.getQuantity() - detail.getQuantity());
                    saleDetail.setProduct(product);
                } else {
                    LOGGER.info("No hay stock para procesar la venta.");
                    return new ResponseEntity("No hay stock para procesar la venta", HttpStatus.BAD_REQUEST);
                }

                details.add(saleDetail);
            }

        }

        sale.setSaleDetails(details);
        saleRepository.save(sale);
        LOGGER.info("Se actualizo orden de compra.");
        return new ResponseEntity("OK", HttpStatus.OK);
    }
}
