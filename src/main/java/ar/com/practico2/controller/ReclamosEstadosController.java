package ar.com.practico2.controller;

import ar.com.practico2.entities.ReclamosEstados;
import ar.com.practico2.service.IReclamosEstadosService;
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
public class ReclamosEstadosController {

    @Autowired
    IReclamosEstadosService entityService;

    @RequestMapping(value = "/reclamosestados", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<ReclamosEstados> centroPage = entityService.findAll(pageable);
        PageWrapper<ReclamosEstados> page = new PageWrapper<ReclamosEstados>(centroPage, "/reclamosestados");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new ReclamosEstados());
        return "../reclamosestados/index";
    }

    @RequestMapping("reclamosestados/refresh")
    public String refresh() {
        return "redirect:/reclamosestados";
    }

    @RequestMapping(value = "reclamosestados/search", method = RequestMethod.POST)
    public String search(Model model, ReclamosEstados entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new ReclamosEstados());
        model.addAttribute("page", null);
        return "../reclamosestados/index";
    }

    @RequestMapping("reclamosestados/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new ReclamosEstados());
        return "../reclamosestados/edit";
    }

    @RequestMapping("reclamosestados/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../reclamosestados/edit";
    }

    @RequestMapping(value = "reclamosestados", method = RequestMethod.POST)
    public String save(Model model, @Validated ReclamosEstados entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../reclamosestados/edit";
        }

        entityService.save(entity);
        return "redirect:/reclamosestados";
    }

    @RequestMapping("reclamosestados/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            ReclamosEstados entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/reclamosestados";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<ReclamosEstados> centroPage = entityService.findAll(pageable);
            PageWrapper<ReclamosEstados> page = new PageWrapper<ReclamosEstados>(centroPage, "/reclamosestados");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new ReclamosEstados());
            return "../reclamosestados/index";
        }
    }
}
