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

import com.sena.proyecto.model.Cliente;
import com.sena.proyecto.service.IClienteService;


@Controller
@SessionAttributes("cliente")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IClienteService cliented;    

    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("clientes", cliented.findAll());
        return "cliente/listar";    
    }

    @GetMapping("/ver-cliente/{idCliente}")
        public String verCliente(@PathVariable Integer idCliente,Model m){
        Cliente cliente=null;
        if(idCliente>0){
        cliente=cliented.findOne(idCliente);
        }else{
        return "redirect:listar";
        }
        m.addAttribute("cliente",cliente);
        m.addAttribute("accion", "Detalle cliente");
        return "cliente/verc";
    }

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{idCliente}")
    public String ver(@PathVariable Integer idCliente,Model m){
    Cliente cliente=null;
    if(idCliente>0){
    cliente=cliented.findOne(idCliente);
    }else{
    return "redirect:listar";
    }
    m.addAttribute("cliente",cliente);
    m.addAttribute("accion", "Actualizar Cliente");
    return "cliente/form";
    }

    @GetMapping("/form")
    public String form(Model m){
    Cliente cliente=new Cliente();
    m.addAttribute("cliente", cliente);
    m.addAttribute("accion", "Agregar Cliente");
    return "cliente/form";
    }
    @PostMapping("/add")
    public String add(@Valid Cliente cliente,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "cliente/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        cliented.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{idCliente}")
    public String delete(@PathVariable Integer idCliente) {

		if(idCliente > 0) {
			cliented.delete(idCliente);
		}
		return "redirect:/cliente/listar";
	}
}
