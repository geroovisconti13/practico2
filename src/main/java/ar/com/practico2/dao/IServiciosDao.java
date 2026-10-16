package ar.com.practico2.dao;

import ar.com.practico2.entities.Servicios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiciosDao extends JpaRepository<Servicios, Integer> {
    @Query("select c from Servicios c where c.descrip like ?1")
    public List<Servicios> findByDescrip(String descrip);

    Page<Servicios> findAll(Pageable pageable);
}

