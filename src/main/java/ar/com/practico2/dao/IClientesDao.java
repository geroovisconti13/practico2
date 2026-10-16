package ar.com.practico2.dao;

import ar.com.practico2.entities.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientesDao extends JpaRepository<Clientes, Integer> {
    @Query("select c from Clientes c where c.nombre like ?1")
    List<Clientes> findByNombre(String nombre);

    Page<Clientes> findAll(Pageable pageable);
}
