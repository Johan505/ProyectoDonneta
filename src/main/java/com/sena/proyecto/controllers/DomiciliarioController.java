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

import com.sena.proyecto.model.Domiciliario;
import com.sena.proyecto.service.IDomiciliarioService;


@Controller
@SessionAttributes("domiciliario")
@RequestMapping("/domiciliario")
public class DomiciliarioController {

    @Autowired
    private IDomiciliarioService domiciliariod;    

    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("domiciliarios", domiciliariod.findAll());
        return "domiciliario/listar";    
    }

    @GetMapping("/ver-domiciliario/{idDomiciliario}")
        public String verDomiciliario(@PathVariable Integer idDomiciliario,Model m){
        Domiciliario domiciliario=null;
        if(idDomiciliario>0){
        domiciliario=domiciliariod.findOne(idDomiciliario);
        }else{
        return "redirect:listar";
        }
        m.addAttribute("domiciliario",domiciliario);
        m.addAttribute("accion", "Detalle domiciliario");
        return "domiciliario/verc";
    }

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{idDomiciliario}")
    public String ver(@PathVariable Integer idDomiciliario,Model m){
    Domiciliario domiciliario=null;
    if(idDomiciliario>0){
    domiciliario=domiciliariod.findOne(idDomiciliario);
    }else{
    return "redirect:listar";
    }
    m.addAttribute("domiciliario", domiciliario);
    m.addAttribute("accion", "Actualizar Domiciliario");
    return "domiciliario/form";
    }

    @GetMapping("/form")
    public String form(Model m){
    Domiciliario domiciliario=new Domiciliario();
    m.addAttribute("domiciliario", domiciliario);
    m.addAttribute("accion", "Agregar Domiciliario");
    return "domiciliario/form";
    }
    @PostMapping("/add")
    public String add(@Valid Domiciliario domiciliario,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "domiciliario/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        domiciliariod.save(domiciliario);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{idDomiciliario}")
    public String delete(@PathVariable Integer idDomiciliario) {

		if(idDomiciliario > 0) {
			domiciliariod.delete(idDomiciliario);
		}
		return "redirect:/domiciliario/listar";
	}
}
