package ar.com.practico2.servicesimp;

import ar.com.practico2.dao.IClientesDao;
import ar.com.practico2.entities.Clientes;
import ar.com.practico2.service.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientesService implements IClientesService {

    @Autowired
    private IClientesDao entityDao;

    public List<Clientes> getAll() {
        return entityDao.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public Page<Clientes> findAll(Pageable pageable) {
        return entityDao.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre")));
    }

    public List<Clientes> findByNombre(String nombre) {
        return entityDao.findByNombre("%" + nombre + "%");
    }


    public Clientes get(Integer id) {
        return entityDao.findById(id).orElse(null);
    }

    @Transactional
    public void save(Clientes entity) {
        entityDao.save(entity);
    }

    @Transactional
    public String delete(Clientes entity) {
        try {
            entityDao.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }
}
