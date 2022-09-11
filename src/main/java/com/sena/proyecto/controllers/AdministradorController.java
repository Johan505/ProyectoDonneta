package com.sena.proyecto.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.proyecto.model.Venta;
import com.sena.proyecto.model.Producto;
import com.sena.proyecto.service.IVentaService;
import com.sena.proyecto.service.IProductoService;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IVentaService ventasService;
	
	private Logger logg= LoggerFactory.getLogger(AdministradorController.class);

	@GetMapping("")
	public String home(Model model) {

		List<Producto> productos = productoService.findAll();
		model.addAttribute("productos", productos);


		return "administrador/home";
	}
	
	@GetMapping("/ventas")
	public String ventas(Model model) {
		model.addAttribute("ventas", ventasService.findAll());
		return "administrador/venta";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {
		logg.info("Id de la venta {}",id);
		Venta venta= ventasService.findById(id).get();
		
		model.addAttribute("detalles", venta.getDetalle());
		
		return "administrador/detalle";
	}
	
	
}
