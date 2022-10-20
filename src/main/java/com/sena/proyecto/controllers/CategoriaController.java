package com.sena.proyecto.controllers;

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

import com.sena.proyecto.model.Categoria;
import com.sena.proyecto.service.ICategoriaService;

@Controller
@SessionAttributes("categoria")
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriad;

    @GetMapping(path = { "/listar", "", "/" })
    public String listar(Model m) {
        m.addAttribute("categorias", categoriad.findAll());
        return "categoria/listar";
    }

    @GetMapping("/ver-categoria/{idCategoria}")
    public String verAlbum(@PathVariable Integer idCategoria, Model m) {
        Categoria categoria = null;
        if (idCategoria > 0) {
            categoria = categoriad.findOne(idCategoria);
        } else {
            return "redirect:listar";
        }
        m.addAttribute("categoria", categoria);
        m.addAttribute("accion", "Producto");
        return "categoria/verc";
    }

    @GetMapping("/ver/{idCategoria}")
    public String ver(@PathVariable Integer idCategoria, Model m) {
        Categoria categoria = null;
        if (idCategoria > 0) {
            categoria = categoriad.findOne(idCategoria);
        } else {
            return "redirect:listar";
        }
        m.addAttribute("categoria", categoria);
        m.addAttribute("accion", "Actualizar Categoria");
        return "categoria/form";
    }

    @GetMapping("/form")
    public String form(Model m) {
        Categoria categoria = new Categoria();

        m.addAttribute("categoria", categoria);
        m.addAttribute("accion", "Agregar Categoria");
        return "categoria/form";
    }

    @PostMapping("/add")
    public String add(@Valid Categoria categoria, BindingResult res, Model m, SessionStatus status) {
        if (res.hasErrors()) {
            return "categoria/form";
        }
        /*
         * m.addAttribute("cliente",cliente);
         * return "cliente/verc";
         */
        try {
            categoriad.save(categoria);
            status.setComplete();
            return "redirect:listar";

        } catch (Exception e) {
            // duplicate primary key
            m.addAttribute("error", "Error, ya existe en la base de datos");
            System.out.println("ya existe");
            return "categoria/form";
        }
    }

    @GetMapping("/delete/{idCategoria}")
    public String delete(@PathVariable Integer idCategoria) {

        if (idCategoria > 0) {
            categoriad.delete(idCategoria);
        }
        return "redirect:/categoria/listar";
    }
}
