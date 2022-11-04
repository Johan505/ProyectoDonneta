package com.sena.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.sena.proyecto.model.Venta;
import com.sena.proyecto.service.IDomicilioService;
import com.sena.proyecto.service.IVentaService;

@Controller
@RequestMapping("/domiciliarios")
public class DomiciliariosController {

	@Autowired
	private IDomicilioService domiciliosService;

	@Autowired
	private IVentaService ventasService;

	@GetMapping("/domicilios")
	public String domicilio(Model model) {
		model.addAttribute("domicilios", domiciliosService.findAll());
		return "VistaDomiciliario/index";
	}

	@GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {

		Venta venta = ventasService.findById(id).get();

		model.addAttribute("detalles", venta.getDetalle());

		return "VistaDomiciliario/detalle";
	}

}
