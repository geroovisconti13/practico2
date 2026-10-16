package ar.com.practico2.servicesimp;

import ar.com.practico2.dao.IRolesDao;
import ar.com.practico2.dao.IServiciosDao;
import ar.com.practico2.entities.Roles;
import ar.com.practico2.entities.Servicios;
import ar.com.practico2.service.IServiciosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiciosService implements IServiciosService {

    @Autowired
    private IServiciosDao entityDao;

    public List<Servicios> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<Servicios> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<Servicios> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public Servicios get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Servicios entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Servicios entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
