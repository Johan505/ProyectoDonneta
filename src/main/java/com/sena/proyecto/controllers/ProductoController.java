package com.sena.proyecto.controllers;

import java.util.List;

import javax.validation.Valid;

import com.sena.proyecto.model.Categoria;
import com.sena.proyecto.model.Producto;
import com.sena.proyecto.service.ICategoriaService;
import com.sena.proyecto.service.IProductoService;

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
@SessionAttributes("producto")
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private IProductoService productod;

    @Autowired
    private ICategoriaService categoriad;

    
    @GetMapping(path={"/listar","","/"})
    public String listar(Model m){
        m.addAttribute("productos", productod.findAll());
        return "producto/listar";    
    }

    @GetMapping("/ver-producto/{idProducto}")
    public String verProducto(@PathVariable Integer idProducto,Model m){
        Producto producto=null;
        if(idProducto>0){
            producto=productod.findOne(idProducto);
        }else{
            return "redirect:listar";
        }
        m.addAttribute("producto",producto);
        m.addAttribute("accion", "Detalle Producto");
        return "producto/verc";
    }

    

    @GetMapping("/ver/{idProducto}")
    public String ver(@PathVariable Integer idProducto,Model m){
        Producto producto=null;
        if(idProducto>0){
            producto=productod.findOne(idProducto);
        }else{
            return "redirect:listar";
        }

        List<Categoria>listade=categoriad.findAll();
        m.addAttribute("ca", listade);
        m.addAttribute("producto",producto);
        m.addAttribute("accion", "Actualizar Producto");
        return "producto/form";
    }

    @GetMapping("/form")     
    public String form(Model m){
        Producto producto=new Producto();

        List<Categoria>listade=categoriad.findAll();
        m.addAttribute("ca", listade);
        m.addAttribute("producto", producto);
        m.addAttribute("accion", "Agregar Producto");
        return "producto/form"; 
    }

    @PostMapping("/add")
    public String add(@Valid Producto producto,BindingResult res, Model m,SessionStatus status){
        if(res.hasErrors()){
            return "producto/form";
        }
        /*m.addAttribute("cliente",cliente); 
        return "cliente/verc";*/
        productod.save(producto);
        status.setComplete();
        return "redirect:listar";
    }    
    @GetMapping("/delete/{idProducto}")
    public String delete(@PathVariable Integer idProducto) {
		
		if(idProducto > 0) {
			productod.delete(idProducto);
		}
		return "redirect:/producto/listar";
	}
}
