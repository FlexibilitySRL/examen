package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.datasource.model.Purchase;
import ar.com.plug.examen.domain.service.impl.ProcessPurchaseServiceImpl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = PurchaseController.ROOT_PATH)
public class PurchaseController
        extends AbstractIdModelController<ProcessPurchaseServiceImpl, Purchase> {

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
    public ResponseEntity<ObjectUtils.Null> approve(@RequestBody ObjectNode objectNode) {
        Long id = getIdRequired(objectNode);
        Boolean approve = getBoolean(objectNode, "approve");
        service.approve(id, approve);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = HISTORY_PATH, produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Purchase>> history(@RequestBody ObjectNode objectNode) {
        String startDate = getString(objectNode, "creationTimeStart");
        String endDate = getString(objectNode, "creationTimeEnd");
        return new ResponseEntity<>(service.findAllByCreationDateTimeBetween(startDate, endDate), HttpStatus.OK);
    }

}