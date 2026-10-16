package ar.com.practico2.service;

import ar.com.practico2.entities.MotivosReclamos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMotivosReclamosService {
    List<MotivosReclamos> getAll();
    Page<MotivosReclamos> findAll(Pageable pageable);
    List<MotivosReclamos> findByDescrip(String descrip);
    MotivosReclamos get(Integer id);
    void save(MotivosReclamos entity);
    String delete(MotivosReclamos entity);
}
