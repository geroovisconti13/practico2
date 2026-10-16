package ar.com.practico2.dao;

import ar.com.practico2.entities.MotivosReclamos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMotivosReclamosDao extends JpaRepository<MotivosReclamos, Integer> {
    @Query("select c from MotivosReclamos c where c.descrip like ?1")
    public List<MotivosReclamos> findByDescrip(String descrip);

    Page<MotivosReclamos> findAll(Pageable pageable);
}
