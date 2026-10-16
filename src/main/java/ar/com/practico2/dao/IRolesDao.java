package ar.com.practico2.dao;

import ar.com.practico2.entities.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRolesDao extends JpaRepository<Roles, Integer> {
    @Query("select c from Roles c where c.descrip like ?1")
    public List<Roles> findByDescrip(String descrip);

    Page<Roles> findAll(Pageable pageable);
}

