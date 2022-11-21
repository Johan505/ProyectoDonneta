package com.sena.proyectodonneta.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sena.proyectodonneta.service.UserService;
import com.sena.proyectodonneta.service.impl.IDomicilioService;

import com.sena.proyectodonneta.model.Venta;
import com.sena.proyectodonneta.repository.UserFindDomiciliarioService;
import com.sena.proyectodonneta.service.impl.IVentaService;

import com.sena.proyectodonneta.model.Domicilio;
import com.sena.proyectodonneta.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes("domicilio")
@RequestMapping("/domicilio")
public class DomicilioController {

    @Autowired
    private IDomicilioService domicilioService;

    @Autowired
    private IVentaService ventad;

    @Autowired
    private UserService userService;

    @Autowired
    private UserFindDomiciliarioService userFindDomiciliarioService;

    @Autowired
    private IVentaService ventasService;
    
    private Logger logg = LoggerFactory.getLogger(DomicilioController.class);

    @GetMapping("/listar")
    public String listar(Model m) {
        m.addAttribute("domicilios", domicilioService.findAll());
        return "domicilio/listar";
    }

    @GetMapping("/ver/{idDomicilio}")
    public String ver(@PathVariable Integer idDomicilio, Model m) {
        Domicilio domicilio = null;
        if (idDomicilio > 0) {
            domicilio = domicilioService.findOne(idDomicilio);
        } else {
            return "redirect:listar";
        }

        List<User> domiciliarios = userFindDomiciliarioService.obtenerDom();

        for(User domiciliario : domiciliarios){

               
               System.out.println(domiciliario.getName());
        }

        m.addAttribute("domiciliarios", domiciliarios);
        m.addAttribute("domicilio", domicilio);
        m.addAttribute("accion", "Actualizar Domicilio");
        return "domicilio/form";
    }

    @GetMapping("/form")
    public String form(Model m) {
        Domicilio domicilio = new Domicilio();

        List<User> domiciliarios = userFindDomiciliarioService.obtenerDom();

        for(User domiciliario : domiciliarios){

               
               System.out.println(domiciliario.getName());
        }

        List<Venta> venta = ventad.findAll();
        m.addAttribute("domiciliarios", domiciliarios);
        m.addAttribute("venta", venta);
        m.addAttribute("domicilio", domicilio);
        m.addAttribute("accion", "Agregar Domicilio");
        return "domicilio/form";
    }

    @PostMapping("/add")
    public String add(@Valid Domicilio domicilio, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "domicilio/form";
        }
        try {
            domicilioService.save(domicilio);
            status.setComplete();
            return "redirect:listar";

        } catch (Exception e) {
            // duplicate primary key
            m.addAttribute("error", "Error, ya existe en la base de datos");
            System.out.println("ya existe");
            return "domicilio/form";
        }
    }

    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Integer id) {
        logg.info("Id de la venta {}", id);
        Venta venta = ventasService.findById(id).get();

        model.addAttribute("detalles", venta.getDetalle());
        return "VistaAdmin/detalle";   
    }


}