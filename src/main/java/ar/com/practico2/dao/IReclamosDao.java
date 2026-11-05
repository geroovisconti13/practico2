package ar.com.practico2.dao;

import ar.com.practico2.entities.Clientes;
import ar.com.practico2.entities.Reclamos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IReclamosDao extends JpaRepository<Reclamos, Integer> {

    List<Reclamos> findByCliente(Clientes cliente);

    @Query("select r from Reclamos r where r.asunto like ?1")
    List<Reclamos> findByAsunto(String asunto);

    Page<Reclamos> findAll(Pageable pageable);

}
