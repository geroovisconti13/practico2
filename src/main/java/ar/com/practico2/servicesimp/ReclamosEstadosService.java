package ar.com.practico2.servicesimp;

import ar.com.practico2.dao.IReclamosEstadosDao;
import ar.com.practico2.entities.ReclamosEstados;
import ar.com.practico2.service.IReclamosEstadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReclamosEstadosService implements IReclamosEstadosService {

    @Autowired
    private IReclamosEstadosDao entityDao;

    public List<ReclamosEstados> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<ReclamosEstados> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<ReclamosEstados> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public ReclamosEstados get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(ReclamosEstados entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(ReclamosEstados entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
