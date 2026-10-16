package ar.com.practico2.service;

import ar.com.practico2.entities.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUsuariosService {
    List<Usuarios> getAll();
    Usuarios get(Integer id);
    void save(Usuarios entity);
    String delete(Usuarios entity);
    Page<Usuarios> findAll(Pageable pageable);
    List<Usuarios> findByDescrip(String descrip);

    Usuarios findUsuario(String usuario, String pass);

    Usuarios findUsuario(String username);
}
