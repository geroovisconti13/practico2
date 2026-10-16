package ar.com.practico2.servicesimp;

import ar.com.practico2.dao.ITiposdocDao;
import ar.com.practico2.entities.TiposDoc;
import ar.com.practico2.service.ITiposdocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TiposdocService implements ITiposdocService {

    @Autowired
    private ITiposdocDao entityDao;

    public List<TiposDoc> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "descrip"));
    }

    public Page<TiposDoc> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descrip")));
    }

    public List<TiposDoc> findByDescrip(String descrip) {
        return entityDao.findByDescrip("%" + descrip + "%");
    }

    public TiposDoc get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(TiposDoc entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(TiposDoc entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}