package ar.com.practico2.controller;


import java.util.List;

import ar.com.practico2.entities.*;
import ar.com.practico2.service.IClienteServicioContratoService;
import ar.com.practico2.service.IClientesService;
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
public class ClienteServicioContratoController {

    @Autowired
    IClienteServicioContratoService entityService;
    @Autowired
    IClientesService clienteService;
    @Autowired
    IServiciosService servicioService;

    @RequestMapping(value = "/clienteserviciocontrato", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<ClienteServicioContrato> centroPage = entityService.findAll(pageable);
        PageWrapper<ClienteServicioContrato> page = new PageWrapper<ClienteServicioContrato>(centroPage, "/clienteserviciocontrato");

        model.addAttribute("entities", page.getContent());
        model.addAttribute("page", page);
        model.addAttribute("entity", new ClienteServicioContrato());

        return "../clienteserviciocontrato/index";
    }

    @RequestMapping("/clienteserviciocontrato/refresh")
    public String refresh() {
        return "redirect:/clienteserviciocontrato";
    }

    @RequestMapping("/clienteserviciocontrato/create")
    public String create( Model model) {

        model.addAttribute("entity", new ClienteServicioContrato());

        List<Clientes> lista = clienteService.getAll();
        model.addAttribute("clientes", lista);

        List<Servicios> list = servicioService.getAll();
        model.addAttribute("servicios", list);

        return "../clienteserviciocontrato/edit";
    }

    @RequestMapping("/clienteserviciocontrato/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        ClienteServicioContrato entity = entityService.get(id);
        model.addAttribute("entity", entity);

        List<Clientes> lista = clienteService.getAll();
        model.addAttribute("clientes", lista);

        List<Servicios> list = servicioService.getAll();
        model.addAttribute("servicios", list);

        return "../clienteserviciocontrato/edit";
    }

    @RequestMapping(value = "clienteserviciocontrato", method = RequestMethod.POST)
    public String save(Model model, @Validated ClienteServicioContrato entity) {

        entityService.save(entity);

        return "redirect:/clienteserviciocontrato";
    }

    @RequestMapping("/clienteserviciocontrato/delete/{id}")
    public String delete(@PathVariable Integer id, Model model, Pageable pageable) {
        try {
            ClienteServicioContrato entity = entityService.get(id);
            if (entity != null) {
                entityService.delete(entity);
            }
            return "redirect:/clienteserviciocontrato";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            Page<ClienteServicioContrato> centroPage = entityService.findAll(pageable);
            PageWrapper<ClienteServicioContrato> page =
                    new PageWrapper<ClienteServicioContrato>(centroPage, "/clienteserviciocontrato");
            model.addAttribute("entities", page.getContent());
            model.addAttribute("page", page);
            model.addAttribute("entity", new ClienteServicioContrato());

            return "../clienteserviciocontrato/index";
        }
    }
}
