package ar.com.plug.examen.app.rest;


import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.repository.TransactionRepository;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Joan Rey
 */
@RestController
@RequestMapping("/transaction")
public class TrasactionRestController {
    @Autowired
    TransactionRepository transactionRepository;


    @GetMapping()
    public List<Transaction> list() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable long id) {
        return transactionRepository.findById(id).map(x -> ResponseEntity.ok(x)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/transactions")
    public List<Transaction> get(@RequestParam String ibanAccount) {
        return transactionRepository.findByIbanAccount(ibanAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Transaction input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Transaction input) {
        Transaction save = transactionRepository.save(input);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }
}
