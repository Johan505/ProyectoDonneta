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

import com.sena.proyecto.model.Cliente;
import com.sena.proyecto.service.IClienteService;
import com.sena.proyecto.service.IUploadFileService;

@Controller
@SessionAttributes("cliente")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteService cliented;

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
        m.addAttribute("clientes", cliented.findAll());
        return "cliente/listar";
    }

    @GetMapping("/ver-cliente/{idCliente}")
    public String verCliente(@PathVariable Integer idCliente, Model m) {
        Cliente cliente = null;
        if (idCliente > 0) {
            cliente = cliented.findOne(idCliente);
        } else {
            return "redirect:listar";
        }
        m.addAttribute("cliente", cliente);
        m.addAttribute("accion", "Detalle cliente");
        return "cliente/verc";
    }

    /*
     * @GetMapping("/ver")
     * public String ver(@RequestParam int id,String nom,Model m){
     * m.addAttribute("msn",
     * "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
     * return "cliente/ver";
     * }
     */

    @GetMapping("/ver/{idCliente}")
    public String ver(@PathVariable Integer idCliente, Model m) {
        Cliente cliente = null;
        if (idCliente > 0) {
            cliente = cliented.findOne(idCliente);
        } else {
            return "redirect:listar";
        }
        m.addAttribute("cliente", cliente);
        m.addAttribute("accion", "Actualizar Cliente");
        return "cliente/form";
    }

    @GetMapping("/form")
    public String form(Model m) {
        Cliente cliente = new Cliente();
        m.addAttribute("cliente", cliente);
        m.addAttribute("accion", "Agregar Cliente");
        return "cliente/form";
    }

    @PostMapping("/add")
    public String add(@Valid Cliente cliente, BindingResult res, Model m, @RequestParam("file") MultipartFile foto,
            SessionStatus status) {
        if (res.hasErrors()) {
            return "cliente/form";
        }

        if (!foto.isEmpty()) {
            if (cliente.getIdCliente() != null && cliente.getIdCliente() > 0 && cliente.getFoto() != null

                    && cliente.getFoto().length() > 0) {

                // Si hay un archivo previo se elimina
                uploadFileService.delete(cliente.getFoto());
            }
            // Si no hay archivos definimos un nombre único con el método copy
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Guardamos el archivo con el nombre nuevo en el objeto cliente
            cliente.setFoto(uniqueFilename);
        }
        /*
         * m.addAttribute("cliente",cliente);
         * return "cliente/verc";
         */
        cliented.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping("/delete/{idCliente}")
    public String delete(@PathVariable Integer idCliente) {

        if (idCliente > 0) {
            // Buscamos el registro a eliminar
            Cliente cliente = cliented.findOne(idCliente);
            // Eliminamos el registro
            cliented.delete(idCliente);
            // Verificamos si tiene un archivo asociado y lo eliminamos del directorio
            if (uploadFileService.delete(cliente.getFoto())) {
            }
        }
        return "redirect:/cliente/listar";
    }
}
