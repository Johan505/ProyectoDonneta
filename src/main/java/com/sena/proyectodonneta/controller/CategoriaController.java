package com.sena.proyectodonneta.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import javax.servlet.http.HttpServletResponse;

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

import com.lowagie.text.DocumentException;
import com.sena.proyectodonneta.model.Categoria;
import com.sena.proyectodonneta.security.SecurityUtils;
import com.sena.proyectodonneta.service.impl.ICategoriaService;
import com.sena.proyectodonneta.util.CategoriasExporterPDF;

@Controller
@SessionAttributes("categoria")
@RequestMapping("/admin/categoria")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriad;

    @Autowired
    private ICategoriaService categoriaService; 


    @GetMapping("/pdfgenerate")
    public void generatePDF(HttpServletResponse response) throws DocumentException,IOException{
        response.setContentType("application/pdf");	
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerkey = "content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime +  ".pdf";
        response.setHeader(headerkey,headerValue);

        List<Categoria> categoria = categoriaService.findAll();

        CategoriasExporterPDF exporterPDF = new CategoriasExporterPDF(categoria);
        exporterPDF.exportar(response);
    }

    @GetMapping("/listar")
    public String listar(Model m) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        m.addAttribute("categorias", categoriad.findAll());
        return "categoria/listar";
    }


    @GetMapping("/ver/{idCategoria}")
    public String ver(@PathVariable Long idCategoria, Model m) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

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
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        Categoria categoria = new Categoria();

        m.addAttribute("categoria", categoria);
        m.addAttribute("accion", "Agregar Categoria");
        return "categoria/form";
    }

    @PostMapping("/add")
    public String add(@Valid Categoria categoria, BindingResult res, Model m, SessionStatus status) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

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
            m.addAttribute("error", "Error, ya existe esta categoria");
            System.out.println("ya existe");
            return "categoria/form";
        }
    }

    @GetMapping("/delete/{idCategoria}")
    public String delete(@PathVariable Long idCategoria) {

        if (idCategoria > 0) {
            categoriad.delete(idCategoria);
        }
        return "redirect:/admin/categoria/listar";
    }
}
