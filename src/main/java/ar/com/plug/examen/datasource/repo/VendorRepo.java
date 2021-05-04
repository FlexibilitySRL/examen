package ar.com.plug.examen.datasource.repo;

import ar.com.plug.examen.datasource.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {

    List<Vendor> findAllByName(String name);

}