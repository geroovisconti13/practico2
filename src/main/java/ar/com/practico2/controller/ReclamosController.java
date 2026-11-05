package ar.com.practico2.controller;


import java.util.List;

import ar.com.practico2.entities.*;
import ar.com.practico2.service.IClienteServicioContratoService;
import ar.com.practico2.service.IClientesService;
import ar.com.practico2.service.IMotivosReclamosService;
import ar.com.practico2.service.IReclamosEstadosService;
import ar.com.practico2.service.IReclamosService;
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
public class ReclamosController {

    @Autowired
    IReclamosService entityService;
    @Autowired
    IClientesService clienteService;
    @Autowired
    IClienteServicioContratoService clienteserviciocontratoService;
    @Autowired
    IReclamosEstadosService reclamosestadosService;
    @Autowired
    IMotivosReclamosService motivosreclamosService;

    @RequestMapping(value = "/reclamos", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Reclamos> centroPage = entityService.findAll(pageable);
        PageWrapper<Reclamos> page = new PageWrapper<Reclamos>(centroPage, "/reclamos");

        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new Reclamos());

        return "../reclamos/index";
    }

    @RequestMapping("/reclamos/refresh")
    public String refresh() {
        return "redirect:/reclamos";
    }

    @RequestMapping(value = "/reclamos/search", method = RequestMethod.POST)
    public String search(Model model, Reclamos entity) {
        if (entity.getAsunto() == null || entity.getAsunto().trim().isEmpty()) {
            return refresh();
        }
        model.addAttribute("entities", entityService.findByAsunto(entity.getAsunto().trim()));
        model.addAttribute("entity", new Reclamos());
        model.addAttribute("page", null);
        return "../reclamos/index";
    }

    @RequestMapping("/reclamos/create/{id}")
    public String create( Model model) {

        model.addAttribute("entity", new Reclamos());

        List<Clientes> lista = clienteService.getAll();
        model.addAttribute("clientes", lista);

        List<ClienteServicioContrato> clienteserviciocontrato = clienteserviciocontratoService.getAll();
        model.addAttribute("clienteserviciocontrato", clienteserviciocontrato);

        List<ReclamosEstados> reclamosestados = reclamosestadosService.getAll();
        model.addAttribute("reclamosestados", reclamosestados);

        List<MotivosReclamos> motivosreclamos = motivosreclamosService.getAll();
        model.addAttribute("motivosreclamos", motivosreclamos);

        return "../reclamos/edit";
    }

    @RequestMapping("/reclamos/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        Reclamos entity = entityService.get(id);
        model.addAttribute("entity", entity);

        List<Clientes> lista = clienteService.getAll();
        model.addAttribute("clientes", lista);

        List<ClienteServicioContrato> clienteserviciocontrato = clienteserviciocontratoService.findByCliente(entity.getCliente());
        model.addAttribute("clienteserviciocontrato", clienteserviciocontrato);

        List<ReclamosEstados> reclamosestados = reclamosestadosService.getAll();
        model.addAttribute("reclamosestados", reclamosestados);

        List<MotivosReclamos> motivosreclamos = motivosreclamosService.getAll();
        model.addAttribute("motivosreclamos", motivosreclamos);

        return "../reclamos/edit";
    }

    @RequestMapping(value = "reclamos", method = RequestMethod.POST)
    public String save(Model model, @Validated Reclamos entity) {

        entityService.save(entity);

        return "redirect:/reclamos";
    }

    @RequestMapping("/reclamos/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            Reclamos entity = entityService.get(id);
            if (entity != null) {
                entityService.delete(entity);
            }
            return "redirect:/reclamos";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            Page<Reclamos> centroPage = entityService.findAll(pageable);
            PageWrapper<Reclamos> page =
                    new PageWrapper<Reclamos>(centroPage, "/reclamos");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new Reclamos());

            return "../reclamos/index";
        }
    }
}
