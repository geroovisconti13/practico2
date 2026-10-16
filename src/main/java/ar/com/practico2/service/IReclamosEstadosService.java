package ar.com.practico2.service;

import ar.com.practico2.entities.ReclamosEstados;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReclamosEstadosService {
    List<ReclamosEstados> getAll();
    Page<ReclamosEstados> findAll(Pageable pageable);
    List<ReclamosEstados> findByDescrip(String descrip);
    ReclamosEstados get(Integer id);
    void save(ReclamosEstados entity);
    String delete(ReclamosEstados entity);
}
