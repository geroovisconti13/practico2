package ar.com.practico2.controller;

import ar.com.practico2.entities.Roles;
import ar.com.practico2.entities.Servicios;
import ar.com.practico2.service.IRolesService;
import ar.com.practico2.service.IServiciosService;
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
public class ServiciosController {
    @Autowired
    IServiciosService entityService;

    @RequestMapping(value = "/servicios", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Servicios> centroPage = entityService.findAll(pageable);
        PageWrapper<Servicios> page = new PageWrapper<Servicios>(centroPage, "/servicios");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Servicios());
        return "../servicios/index";
    }

    @RequestMapping("servicios/refresh")
    public String refresh() {
        return "redirect:/servicios";
    }

    @RequestMapping(value = "servicios/search", method = RequestMethod.POST)
    public String search(Model model, Servicios entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new Servicios());
        model.addAttribute("page", null);
        return "../servicios/index";
    }

    @RequestMapping("servicios/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new Servicios());
        return "../servicios/edit";
    }

    @RequestMapping("servicios/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../servicios/edit";
    }

    @RequestMapping(value = "servicios", method = RequestMethod.POST)
    public String save(Model model, @Validated Servicios entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../servicios/edit";
        }

        entityService.save(entity);
        return "redirect:/servicios";
    }

    @RequestMapping("servicios/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Servicios entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/servicios";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<Servicios> centroPage = entityService.findAll(pageable);
            PageWrapper<Servicios> page = new PageWrapper<Servicios>(centroPage, "/servicios");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Servicios());
            return "../servicios/index";
        }
    }

}
