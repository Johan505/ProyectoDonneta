package com.sena.proyecto.controllers;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyecto.model.Venta;
import com.sena.proyecto.model.Cliente;
import com.sena.proyecto.model.Detalle;
import com.sena.proyecto.model.Empleado;
import com.sena.proyecto.service.IVentaService;
import com.sena.proyecto.service.IClienteService;
import com.sena.proyecto.service.IDetalleService;
import com.sena.proyecto.service.IEmpleadoService;

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
@SessionAttributes("venta")
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private IVentaService ventad;

    @Autowired
    private IDetalleService detalled;

    @Autowired
    private IClienteService cliented;

    @Autowired
    private IEmpleadoService empleadod;

    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("ventas", ventad.findAll());
        return "venta/listar";    
    }

    @GetMapping("/ver-venta/{idVenta}")
    public String verAlbum(@PathVariable Integer idVenta,Model m){
        Venta venta=null;
        if(idVenta>0){
            venta=ventad.findOne(idVenta);
        }else{
            return "redirect:listar";
        }
        m.addAttribute("venta",venta);
        m.addAttribute("accion", "Detalle Venta");
        return "venta/verc";
    }

    

    @GetMapping("/ver/{idVenta}")
    public String ver(@PathVariable Integer idVenta,Model m){
        Venta venta=null;
        if(idVenta>0){
            venta=ventad.findOne(idVenta);
        }else{
            return "redirect:listar";
        }


        List<Detalle>listade=detalled.findAll();
        List<Cliente>listac=cliented.findAll();
        List<Empleado>listaem=empleadod.findAll();
        m.addAttribute("em",listaem);
        m.addAttribute("cl", listac);
        m.addAttribute("de", listade);
        m.addAttribute("venta",venta);
        m.addAttribute("accion", "Actualizar Venta");
        return "venta/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Venta venta=new Venta();

        List<Detalle>listade=detalled.findAll();
        List<Cliente>listac=cliented.findAll();
        List<Empleado>listaem=empleadod.findAll();
        m.addAttribute("em",listaem);
        m.addAttribute("cl", listac);
        m.addAttribute("de", listade);
        m.addAttribute("venta", venta);
        m.addAttribute("accion", "Agregar Venta");
        return "venta/form"; 
    }

    @PostMapping("/add")
    public String add(@Valid Venta venta,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "venta/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        ventad.save(venta);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{idVenta}")
    public String delete(@PathVariable Integer idVenta) {
		
		if(idVenta > 0) {
			ventad.delete(idVenta);
		}
		return "redirect:/venta/listar";
	}
}
