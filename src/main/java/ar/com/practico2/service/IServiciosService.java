package ar.com.practico2.service;

import ar.com.practico2.entities.Servicios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiciosService {
    List<Servicios> getAll();
    Page<Servicios> findAll(Pageable pageable);
    List<Servicios> findByDescrip(String descrip);
    Servicios get(Integer id);
    void save(Servicios entity);
    String delete(Servicios entity);
}
