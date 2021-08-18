package ar.com.plug.examen.domain.repository;

import ar.com.plug.examen.domain.dto.ItemByLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

import static ar.com.plug.examen.utils.QueryUtils.QUERY_LAST_ID_ITEM;

public interface ItemLineRepository extends JpaRepository<ItemByLine, Serializable> {
    @Query(value=QUERY_LAST_ID_ITEM, nativeQuery = true)
    Long findTopByOrderByIdDesc();
    ItemByLine findById(Long id);
    List<ItemByLine> findAll();
}
