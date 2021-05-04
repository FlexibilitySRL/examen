package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Purchase;
import ar.com.plug.examen.domain.service.impl.ProcessPurchaseServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = PurchaseController.ROOT_PATH)
public class PurchaseController
        extends AbstractBaseModelController<ProcessPurchaseServiceImpl, Purchase> {

    //paths
    public static final String ROOT_PATH = "purchase";
    public static final String APPROVE_PATH = "approve";
    public static final String HISTORY_PATH = "history";

    @Autowired
    public PurchaseController(ProcessPurchaseServiceImpl service) {
        super(service);
    }

    @PostMapping(path = APPROVE_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectUtils.Null> approve(@RequestBody Long id) {
        service.approve(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = HISTORY_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Purchase>> history(@RequestBody ObjectNode objectNode) {
        String startDate = getString(objectNode, "creationTimeStart");
        String endDate = getString(objectNode, "creationTimeEnd");
        return new ResponseEntity<>(service.findAllByCreationDateTimeBetween(startDate, endDate), HttpStatus.OK);
    }

    private String getString(@RequestBody ObjectNode objectNode, String key) {
        final JsonNode jsonNode = objectNode.get(key);
        if (null != jsonNode) {
            return jsonNode.asText();
        } else {
            final String reason = key + " is required for request";
            log.error(reason);
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, reason);
        }
    }

}