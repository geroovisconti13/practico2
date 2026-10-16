package ar.com.practico2.controller;

import ar.com.practico2.entities.Roles;
import ar.com.practico2.entities.TiposDoc;
import ar.com.practico2.service.ITiposdocService;
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
public class TiposdocController {

    @Autowired
    ITiposdocService entityService;

    @RequestMapping(value = "/tiposdoc", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<TiposDoc> centroPage = entityService.findAll(pageable);
        PageWrapper<TiposDoc> page = new PageWrapper<TiposDoc>(centroPage, "/tiposdoc");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new TiposDoc());
        return "../tiposdoc/index";
    }

    @RequestMapping("tiposdoc/refresh")
    public String refresh() {
        return "redirect:/tiposdoc";
    }

    @RequestMapping(value = "tiposdoc/search", method = RequestMethod.POST)
    public String search(Model model, TiposDoc entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new TiposDoc());
        model.addAttribute("page", null);
        return "../tiposdoc/index";
    }

    @RequestMapping("tiposdoc/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new TiposDoc());
        return "../tiposdoc/edit";
    }

    @RequestMapping("tiposdoc/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../tiposdoc/edit";
    }

    @RequestMapping(value = "tiposdoc", method = RequestMethod.POST)
    public String save(Model model, @Validated TiposDoc entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../tiposdoc/edit";
        }

        entityService.save(entity);
        return "redirect:/tiposdoc";
    }

    @RequestMapping("tiposdoc/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            TiposDoc entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/tiposdoc";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<TiposDoc> centroPage = entityService.findAll(pageable);
            PageWrapper<TiposDoc> page = new PageWrapper<TiposDoc>(centroPage, "/tiposdoc");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new TiposDoc());
            return "../tiposdoc/index";
        }
    }
}
