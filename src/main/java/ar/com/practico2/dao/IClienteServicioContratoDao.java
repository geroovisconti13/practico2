package ar.com.practico2.dao;

import ar.com.practico2.entities.ClienteServicioContrato;
import ar.com.practico2.entities.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClienteServicioContratoDao extends JpaRepository<ClienteServicioContrato, Integer> {

    List<ClienteServicioContrato> findByCliente(Clientes cliente);

    Page<ClienteServicioContrato> findAll(Pageable pageable);

}
