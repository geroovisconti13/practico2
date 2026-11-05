package ar.com.practico2.controller;


import java.util.List;

import ar.com.practico2.entities.Clientes;
import ar.com.practico2.entities.TiposDoc;
import ar.com.practico2.entities.Usuarios;
import ar.com.practico2.service.IClientesService;
import ar.com.practico2.service.ITiposdocService;
import ar.com.practico2.service.IUsuariosService;
import ar.com.practico2.utiles.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientesController {

    @Autowired
    IClientesService entityService;
    @Autowired
    IUsuariosService usuariosService;
    @Autowired
    ITiposdocService tipodocService;

    @RequestMapping(value = "/clientes", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Clientes> centroPage = entityService.findAll(pageable);
        PageWrapper<Clientes> page = new PageWrapper<Clientes>(centroPage, "/clientes");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Clientes());
        return "../clientes/index";
    }

    @RequestMapping("/clientes/refresh")
    public String refresh() {
        return "redirect:/clientes";
    }

    @RequestMapping(value = "/clientes/search", method = RequestMethod.POST)
    public String search(Model model, Clientes entity) {
        if (entity.getNombre().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByNombre(entity.getNombre()));
        model.addAttribute("entity", new Clientes());
        model.addAttribute("page", null);
        return "../clientes/index";
    }

    @RequestMapping("/clientes/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Clientes());

        List<TiposDoc> lista = tipodocService.getAll();
        model.addAttribute("tiposdoc", lista);

        List<Usuarios> list = usuariosService.getAll();
        model.addAttribute("usuarios", list);

        return "../clientes/edit";
    }

    @RequestMapping("/clientes/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        List<TiposDoc> lista = tipodocService.getAll();
        model.addAttribute("tiposdoc", lista);
        List<Usuarios> list = usuariosService.getAll();
        model.addAttribute("usuarios", list);
        return "../clientes/edit";
    }

    @RequestMapping(value = "clientes", method = RequestMethod.POST)
    public String save(Model model, @Validated Clientes entity) {
        String errores = "";

        if (!errores.equals("")) {
            model.addAttribute("message", errores);
            model.addAttribute("entity", entity);
            return "../clientes/edit";
        }

        entityService.save(entity);
        return "redirect:/clientes";
    }

    @RequestMapping("clientes/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Clientes entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/clientes";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Clientes> centroPage = entityService.findAll(pageable);
            PageWrapper<Clientes> page = new PageWrapper<Clientes>(centroPage, "/clientes");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Clientes());
            return "../clientes/index";
        }
    }

}