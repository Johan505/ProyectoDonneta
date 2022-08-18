package com.sena.proyecto.controllers;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyecto.model.Domiciliario;
import com.sena.proyecto.model.Domicilio;
import com.sena.proyecto.model.Venta;
import com.sena.proyecto.service.IDomiciliarioService;
import com.sena.proyecto.service.IDomicilioService;
import com.sena.proyecto.service.IVentaService;

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



@Controller
@SessionAttributes("domicilio")
@RequestMapping("/domicilio")
public class DomicilioController {

    @Autowired
    private IDomicilioService domiciliod;

    @Autowired
    private IDomiciliarioService domiciliariod;

    @Autowired
    private IVentaService ventad;

    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("domicilios", domiciliod.findAll());
        return "domicilio/listar";    
    }

    @GetMapping("/ver-domicilio/{idDomicilio}")
    public String verDomicilio(@PathVariable Integer idDomicilio,Model m){
        Domicilio domicilio=null;
        if(idDomicilio>0){
            domicilio=domiciliod.findOne(idDomicilio);
        }else{
            return "redirect:listar";
        }

        m.addAttribute("domicilio",domicilio);
        m.addAttribute("accion", "Detalle domicilio");
        return "domicilio/verc";
    }

    

    @GetMapping("/ver/{idDomicilio}")
    public String ver(@PathVariable Integer idDomicilio,Model m){
        Domicilio domicilio=null;
        if(idDomicilio>0){
            domicilio=domiciliod.findOne(idDomicilio);
        }else{
            return "redirect:listar";
        }

        List<Domiciliario>listado=domiciliariod.findAll();
        List<Venta>listave=ventad.findAll();
        m.addAttribute("do", listado);
        m.addAttribute("ve", listave);
        m.addAttribute("domicilio",domicilio);
        m.addAttribute("accion", "Actualizar Domicilio");
        return "Domicilio/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Domicilio domicilio=new Domicilio();

        List<Domiciliario>listado=domiciliariod.findAll();
        List<Venta>listave=ventad.findAll();
        m.addAttribute("do", listado);
        m.addAttribute("ve", listave);
        m.addAttribute("domicilio", domicilio);
        m.addAttribute("accion", "Agregar Domicilio");
        return "domicilio/form"; 
    }

    @PostMapping("/add")
    public String add(@Valid Domicilio domicilio,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "domicilio/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        domiciliod.save(domicilio);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{idDomicilio}")
    public String delete(@PathVariable Integer idDomicilio) {
		
		if(idDomicilio > 0) {
			domiciliod.delete(idDomicilio);
		}
		return "redirect:/domicilio/listar";
	}
}
