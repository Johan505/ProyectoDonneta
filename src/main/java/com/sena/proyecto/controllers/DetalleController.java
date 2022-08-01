package com.sena.proyecto.controllers;

import javax.validation.Valid;

import com.sena.proyecto.model.Detalle;
import com.sena.proyecto.service.IDetalleService;

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
@SessionAttributes("detalle")
@RequestMapping("/detalle")
public class DetalleController {

    @Autowired
    private IDetalleService detalled;
    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("detalles", detalled.findAll());
        return "detalle/listar";    
    }

    @GetMapping("/ver-genero/{idDetalle}")
    public String verGenero(@PathVariable Integer idDetalle,Model m){
        Detalle detalle=null;
        if(idDetalle>0){
            detalle=detalled.findOne(idDetalle);
        }else{
            return "redirect:listar";
        }
        m.addAttribute("detalle",detalle);
        m.addAttribute("accion", "Detalle venta");
        return "detalle/verc";
    }

    

    @GetMapping("/ver/{idDetalle}")
    public String ver(@PathVariable Integer idDetalle,Model m){
        Detalle detalle=null;
        if(idDetalle>0){
            detalle=detalled.findOne(idDetalle);
        }else{
            return "redirect:listar";
        }
        m.addAttribute("detalle",detalle);
        m.addAttribute("accion", "Actualizar Detalle");
        return "Detalle/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Detalle detalle=new Detalle();
        m.addAttribute("detalle", detalle);
        m.addAttribute("accion", "Agregar Detalle");
        return "detalle/form"; 
    }

    @PostMapping("/add")
    public String add(@Valid Detalle detalle,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "detalle/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        detalled.save(detalle);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{idDetalle}")
    public String delete(@PathVariable Integer idDetalle) {
		
		if(idDetalle > 0) {
			detalled.delete(idDetalle);
		}
		return "redirect:/detalle/listar";
	}
}
