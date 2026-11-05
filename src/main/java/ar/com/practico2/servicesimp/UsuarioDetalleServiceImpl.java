package ar.com.practico2.servicesimp;

import ar.com.practico2.dao.IUsuariosDao;
import ar.com.practico2.entities.Usuarios;
import ar.com.practico2.security.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userDetailsService")
public class UsuarioDetalleServiceImpl implements UserDetailsService {
    @Autowired
    private IUsuariosDao dao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        final Usuarios retrievedUser = dao.findOneByUsername(userName);

        if (retrievedUser == null) {
            throw new UsernameNotFoundException("Usuario o Password Inválido(s)");
        }

        if (Boolean.TRUE.equals(retrievedUser.getInactivo())) {
            throw new UsernameNotFoundException("Usuario Inhabilitado");
        }

        return UserDetailsMapper.build(retrievedUser);
    }

}

