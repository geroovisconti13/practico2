package ar.com.practico2.servicesimp;

import ar.com.practico2.dao.IMotivosReclamosDao;
import ar.com.practico2.entities.MotivosReclamos;
import ar.com.practico2.service.IMotivosReclamosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MotivosReclamosService implements IMotivosReclamosService {

    @Autowired
    private IMotivosReclamosDao entityDao;

    public List<MotivosReclamos> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<MotivosReclamos> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<MotivosReclamos> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public MotivosReclamos get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(MotivosReclamos entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(MotivosReclamos entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
