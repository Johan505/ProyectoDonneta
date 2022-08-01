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

import com.sena.proyecto.model.Empleado;
import com.sena.proyecto.service.IEmpleadoService;


@Controller
@SessionAttributes("empleado")
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService empleadod;    

    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("empleados", empleadod.findAll());
        return "empleado/listar";    
    }

    @GetMapping("/ver-empleado/{idEmpleado}")
        public String verEmpleado(@PathVariable Integer idEmpleado,Model m){
        Empleado empleado=null;
        if(idEmpleado>0){
        empleado=empleadod.findOne(idEmpleado);
        }else{
        return "redirect:listar";
        }
        m.addAttribute("empleado",empleado);
        m.addAttribute("accion", "Detalle empleado");
        return "empleado/verc";
    }

    /*@GetMapping("/ver")
    public String ver(@RequestParam int id,String nom,Model m){
        m.addAttribute("msn", "En la ruta llegó el id "+id+" y el nombre recibido es "+nom);
        return "cliente/ver";
    }*/

    @GetMapping("/ver/{idEmpleado}")
    public String ver(@PathVariable Integer idEmpleado,Model m){
    Empleado empleado=null;
    if(idEmpleado>0){
    empleado=empleadod.findOne(idEmpleado);
    }else{
    return "redirect:listar";
    }
    m.addAttribute("empleado",empleado);
    m.addAttribute("accion", "Actualizar Empleado");
    return "empleado/form";
    }

    @GetMapping("/form")
    public String form(Model m){
    Empleado empleado=new Empleado();
    m.addAttribute("empleado", empleado);
    m.addAttribute("accion", "Agregar Empleado");
    return "empleado/form";
    }
    @PostMapping("/add")
    public String add(@Valid Empleado empleado,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "empleado/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        empleadod.save(empleado);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{idEmpleado}")
    public String delete(@PathVariable Integer idEmpleado) {

		if(idEmpleado > 0) {
			empleadod.delete(idEmpleado);
		}
		return "redirect:/empleado/listar";
	}
}
