package com.sena.proyectodonneta.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lowagie.text.DocumentException;

import com.sena.proyectodonneta.dto.UserDto;
import com.sena.proyectodonneta.model.User;
import com.sena.proyectodonneta.model.Venta;
import com.sena.proyectodonneta.security.SecurityUtils;
import com.sena.proyectodonneta.repository.UserFindClienteService;
import com.sena.proyectodonneta.repository.UserFindDomiciliarioService;
import com.sena.proyectodonneta.service.UserService;
import com.sena.proyectodonneta.service.impl.IProductoService;
import com.sena.proyectodonneta.service.impl.IVentaService;
import com.sena.proyectodonneta.util.VentasExporterPDF;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Logger logg = LoggerFactory.getLogger(AdminController.class);
    
    @Autowired
    private UserService userService;
    

	@Autowired
	private IVentaService ventasService;

    @Autowired
    private UserFindDomiciliarioService userFindDomiciliarioService;
    

    @Autowired
    private UserFindClienteService userFindClienteService;
    
    
    @Autowired
    private IVentaService ventaService;
    

    @GetMapping("/ventas/pdfgenerate")
    public void generatePDF(HttpServletResponse response) throws DocumentException,IOException{
        response.setContentType("application/pdf");	
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerkey = "content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime +  ".pdf";
        response.setHeader(headerkey,headerValue);

        List<Venta> ventas = ventaService.findAll();

        VentasExporterPDF exporterPDF = new VentasExporterPDF(ventas);
        exporterPDF.exportar(response);
    }

    @GetMapping("")
    public String managerView(Model model)
    {
        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);
        return "VistaAdmin/index";
    }

    @GetMapping("/ventas")
	public String ventas(Model model) {
        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);

		model.addAttribute("ventas", ventasService.findAll());
		return "VistaAdmin/venta";
	}

    @GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Integer id) {

        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);

		logg.info("Id de la venta {}", id);
		Venta venta = ventasService.findById(id).get();

		model.addAttribute("detalles", venta.getDetalle());

		return "VistaAdmin/detalle";
	}

    @GetMapping("/domiciliario/listar")
    public String usuarios(Model model) {

        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);
        
         List<User> domiciliarios = userFindDomiciliarioService.obtenerDom();

         for(User domiciliario : domiciliarios){

                
                System.out.println(domiciliario.getName());
         }

         model.addAttribute("domiciliarios", domiciliarios);
         
        return "VistaAdmin/listarDom";
    }

    @GetMapping("/domiciliario/register")
    public String showRegistrationForm(Model model){

        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);

        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "VistaAdmin/formDom";
    }

    @PostMapping("/domiciliario/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){

                                String currentUser = SecurityUtils.getUserName();
                                model.addAttribute("username", currentUser);

                                User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "VistaAdmin/formDom";
        }
        userService.saveDomiciliario(user);
        return "redirect:/admin/domiciliario/listar";
    }


    @GetMapping("/cliente/listar")
    public String clientes(Model model) {

        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);
        
         List<User> clientes = userFindClienteService.obtenerCli();

         for(User cliente : clientes){

                
                System.out.println(cliente.getName());
         }

         model.addAttribute("clientes", clientes);
         
        return "VistaAdmin/listarCli";
    }

}
