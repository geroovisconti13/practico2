package ar.com.practico2.controllerrest;

import ar.com.practico2.entities.Roles;
import ar.com.practico2.service.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RolesRestController {

    @Autowired
    IRolesService entityService;
    
    @GetMapping(path = "/rolesrest")
    public List<Roles> getAll() {
        return entityService.getAll();
    }

    @GetMapping(path = "/rolesrest/{id}")
    public Roles get(@PathVariable Integer id) {
        return entityService.get(id);
    }

    @GetMapping(path = "/rolesrest/search/{expresion}")
    public List<Roles> getListRoles(@PathVariable String expresion) {
        return entityService.findByDescrip(expresion);
    }

    @PostMapping(value = "/rolesrest", consumes = "application/json", produces = "application/json")
    public Roles save(@RequestBody Roles entity) {
        entityService.save(entity);
        return entity;
    }

    @GetMapping(path = "/rolesrest/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Roles entity = null;
         try {
            entity = entityService.get(id);
            entityService.delete(entity);
            return null;
        } catch (Exception e) {
            return e.getMessage().toString();
        }
    }

}
