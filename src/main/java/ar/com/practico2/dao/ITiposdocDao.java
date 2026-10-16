package ar.com.practico2.dao;

import ar.com.practico2.entities.TiposDoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITiposdocDao extends JpaRepository<TiposDoc, Integer> {
    @Query("select c from TiposDoc c where c.descrip like ?1")
    public List<TiposDoc> findByDescrip(String descrip);

    Page<TiposDoc> findAll(Pageable pageable);
}
