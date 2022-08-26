package com.sena.proyecto.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.sena.proyecto.model.Domiciliario;
import com.sena.proyecto.service.IDomiciliarioService;
import com.sena.proyecto.service.IUploadFileService;

@Controller
@SessionAttributes("domiciliario")
@RequestMapping("/domiciliario")
public class DomiciliarioController {

    @Autowired
    private IDomiciliarioService domiciliariod;

    @Autowired
    private IUploadFileService uploadFileService;

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {
        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()

                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +

                        recurso.getFilename() + "\"")
                .body(recurso);

    }

    @GetMapping(path = { "/listar", "", "/" })
    public String listar(Model m) {
        m.addAttribute("domiciliarios", domiciliariod.findAll());
        return "domiciliario/listar";
    }

    @GetMapping("/ver-domiciliario/{idDomiciliario}")
    public String verDomiciliario(@PathVariable Integer idDomiciliario, Model m) {
        Domiciliario domiciliario = null;
        if (idDomiciliario > 0) {
            domiciliario = domiciliariod.findOne(idDomiciliario);
        } else {
            return "redirect:listar";
        }
        m.addAttribute("domiciliario", domiciliario);
        m.addAttribute("accion", "Detalle domiciliario");
        return "domiciliario/verc";
    }

    /*
     * @GetMapping("/ver")
     * public String ver(@RequestParam int id,String nom,Model m){
     * m.addAttribute("msn",
     * "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
     * return "cliente/ver";
     * }
     */

    @GetMapping("/ver/{idDomiciliario}")
    public String ver(@PathVariable Integer idDomiciliario, Model m) {
        Domiciliario domiciliario = null;
        if (idDomiciliario > 0) {
            domiciliario = domiciliariod.findOne(idDomiciliario);
        } else {
            return "redirect:listar";
        }

        m.addAttribute("domiciliario", domiciliario);
        m.addAttribute("accion", "Actualizar Domiciliario");
        return "domiciliario/form";
    }

    @GetMapping("/form")
    public String form(Model m) {
        Domiciliario domiciliario = new Domiciliario();

        m.addAttribute("domiciliario", domiciliario);
        m.addAttribute("accion", "Agregar Domiciliario");
        return "domiciliario/form";
    }

    @PostMapping("/add")
    public String add(@Valid Domiciliario domiciliario, BindingResult res, Model m,
            @RequestParam("file") MultipartFile foto, SessionStatus status) {
        if (res.hasErrors()) {
            return "domiciliario/form";
        }
        /*
         * m.addAttribute("cliente",cliente);
         * return "cliente/verc";
         */

        if (!foto.isEmpty()) {
            if (domiciliario.getIdDomiciliario() != null && domiciliario.getIdDomiciliario() > 0
                    && domiciliario.getFoto() != null

                    && domiciliario.getFoto().length() > 0) {

                // Si hay un archivo previo se elimina
                uploadFileService.delete(domiciliario.getFoto());
            }
            // Si no hay archivos definimos un nombre único con el método copy
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Guardamos el archivo con el nombre nuevo en el objeto cliente
            domiciliario.setFoto(uniqueFilename);
        }

        domiciliariod.save(domiciliario);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/delete/{idDomiciliario}")
    public String delete(@PathVariable Integer idDomiciliario) {

        if (idDomiciliario > 0) {
            // Buscamos el registro a eliminar
            Domiciliario domiciliario = domiciliariod.findOne(idDomiciliario);
            // Eliminamos el registro
            domiciliariod.delete(idDomiciliario);
            // Verificamos si tiene un archivo asociado y lo eliminamos del directorio
            if (uploadFileService.delete(domiciliario.getFoto())) {
            }
        }
        return "redirect:/domiciliario/listar";
    }
}
