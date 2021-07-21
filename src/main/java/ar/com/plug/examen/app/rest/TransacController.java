package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.model.Transac;
import ar.com.plug.examen.domain.service.ITransacService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransacController {
    private static final Logger log = LoggerFactory.getLogger(TransacController.class);

    @Autowired
    private ITransacService transacService;

    @GetMapping("/api/v1/transacs")
    public Page<Transac> getTransacs(@PageableDefault(page = 0, size = 20) @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) }) Pageable pageable) {

        return transacService.findAll(pageable);
    }

    @GetMapping("/api/v1/transacs/{transacId}")
    public ResponseEntity<Transac> getTransacById(@PathVariable("transacId") long transacId) {

        Transac oTransac = transacService.getById(transacId);
        if (oTransac != null)
            return new ResponseEntity<>(oTransac, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/v1/transacs/{transacId}/approve")
    public ResponseEntity<?> approveTransac(@PathVariable("transacId") long transacId) {

        Boolean result = transacService.approve(transacId);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
