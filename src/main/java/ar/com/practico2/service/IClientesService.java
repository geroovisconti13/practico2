package ar.com.practico2.service;

import ar.com.practico2.entities.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientesService {
    List<Clientes> getAll();
    Clientes get(Integer id);
    void save(Clientes entity);
    String delete(Clientes entity);
    Page<Clientes> findAll(Pageable pageable);
    List<Clientes> findByNombre(String nombre);
}
