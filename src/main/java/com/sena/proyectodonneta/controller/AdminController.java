package com.sena.proyectodonneta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.proyectodonneta.model.Venta;
import com.sena.proyectodonneta.security.SecurityUtils;
import com.sena.proyectodonneta.service.UserService;
import com.sena.proyectodonneta.service.impl.IProductoService;
import com.sena.proyectodonneta.service.impl.IVentaService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logg = LoggerFactory.getLogger(AdminController.class);
    
    private UserService userService;
    

	@Autowired
	private IVentaService ventasService;
    
    
    @GetMapping("")
    public String managerView(Model model)
    {
        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);
        return "VistaAdmin/index";
    }

    @GetMapping("/ventas")
	public String ventas(Model model) {
		model.addAttribute("ventas", ventasService.findAll());
		return "VistaAdmin/venta";
	}

    @GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logg.info("Id de la venta {}", id);
		Venta venta = ventasService.findById(id).get();

		model.addAttribute("detalles", venta.getDetalle());

		return "VistaAdmin/detalle";
	}

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", userService.findAllUsers());
        return "VistaAdmin/users";
    }

}
