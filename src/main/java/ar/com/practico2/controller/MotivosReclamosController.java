package ar.com.practico2.controller;

import ar.com.practico2.entities.MotivosReclamos;
import ar.com.practico2.service.IMotivosReclamosService;
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
public class MotivosReclamosController {

    @Autowired
    IMotivosReclamosService entityService;

    @RequestMapping(value = "/motivosreclamos", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<MotivosReclamos> centroPage = entityService.findAll(pageable);
        PageWrapper<MotivosReclamos> page = new PageWrapper<MotivosReclamos>(centroPage, "/motivosreclamos");
        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new MotivosReclamos());
        return "../motivosreclamos/index";
    }

    @RequestMapping("motivosreclamos/refresh")
    public String refresh() {
        return "redirect:/motivosreclamos";
    }

    @RequestMapping(value = "motivosreclamos/search", method = RequestMethod.POST)
    public String search(Model model, MotivosReclamos entity) {
        if (entity.getDescrip().equals("")) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByDescrip(entity.getDescrip()));
        model.addAttribute("entity", new MotivosReclamos());
        model.addAttribute("page", null);
        return "../motivosreclamos/index";
    }

    @RequestMapping("motivosreclamos/create/{id}")
    public String create(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", new MotivosReclamos());
        return "../motivosreclamos/edit";
    }

    @RequestMapping("motivosreclamos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("entity", entityService.get(id));
        return "../motivosreclamos/edit";
    }

    @RequestMapping(value = "motivosreclamos", method = RequestMethod.POST)
    public String save(Model model, @Validated MotivosReclamos entity) {
        if (entity.getDescrip().equals("")) {
            model.addAttribute("message", "Descripción Incorrecta");
            model.addAttribute("entity", entity);
            return "../motivosreclamos/edit";
        }

        entityService.save(entity);
        return "redirect:/motivosreclamos";
    }

    @RequestMapping("motivosreclamos/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            MotivosReclamos entity = entityService.get(id);
            entityService.delete(entity);
            return "redirect:/motivosreclamos";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage().toString());
            Page<MotivosReclamos> centroPage = entityService.findAll(pageable);
            PageWrapper<MotivosReclamos> page = new PageWrapper<MotivosReclamos>(centroPage, "/motivosreclamos");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new MotivosReclamos());
            return "../motivosreclamos/index";
        }
    }
}
