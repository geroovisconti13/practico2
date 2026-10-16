package ar.com.practico2.dao;

import ar.com.practico2.entities.ReclamosEstados;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReclamosEstadosDao extends JpaRepository<ReclamosEstados, Integer> {
    @Query("select c from ReclamosEstados c where c.descrip like ?1")
    public List<ReclamosEstados> findByDescrip(String descrip);

    Page<ReclamosEstados> findAll(Pageable pageable);
}
