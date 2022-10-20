package com.sena.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.proyecto.service.IDomicilioService;

@Controller
@RequestMapping("/domiciliarios")
public class DomiciliariosController {

	@Autowired
	private IDomicilioService domiciliosService;

	@GetMapping("/domicilios")
	public String domicilio(Model model) {
		model.addAttribute("domicilios", domiciliosService.findAll());
		return "VistaDomiciliario/index";
	}

}
