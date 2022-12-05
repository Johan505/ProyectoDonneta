package com.sena.proyectodonneta.controller;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.lowagie.text.DocumentException;
import com.sena.proyectodonneta.model.Categoria;
import com.sena.proyectodonneta.model.Domicilio;
import com.sena.proyectodonneta.model.Producto;
import com.sena.proyectodonneta.model.User;
import com.sena.proyectodonneta.model.Venta;
import com.sena.proyectodonneta.repository.DomicilioIdRepository;
import com.sena.proyectodonneta.security.SecurityUtils;
import com.sena.proyectodonneta.service.UserService;
import com.sena.proyectodonneta.service.impl.ICategoriaService;
import com.sena.proyectodonneta.service.impl.IDomicilioService;
import com.sena.proyectodonneta.service.impl.IProductoService;
import com.sena.proyectodonneta.service.impl.IUploadFileService;
import com.sena.proyectodonneta.service.impl.IVentaService;
import com.sena.proyectodonneta.service.impl.VentaServiceImpl;
import com.sena.proyectodonneta.util.ProductosExporterPDF;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller

@RequestMapping("/domiciliario")
public class DomiciliarioController {

    private Logger logg = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private IProductoService productod;

    @Autowired
	private IVentaService ventasService;

    @Autowired
    private IProductoService productoda;

    @Autowired
    private ICategoriaService categoriad;

    @Autowired
    private IUploadFileService uploadFileService;

    @Autowired
    private  IProductoService productoService;

    @Autowired
	private UserService userService;

    @Autowired
    private IDomicilioService domiciliod;

    @Autowired
    private DomicilioIdRepository domicilioIdRepository;

    @Autowired
    private DomicilioIdRepository domicilioRepository;


    @GetMapping("")
    public String listar(Model m) {
        String currentUser = SecurityUtils.getUserName();
        User user = userService.findByEmail(currentUser);

        List <Domicilio> domicilios = domicilioIdRepository.findDomiciliario(user.getId());


        m.addAttribute("username", currentUser);
        

        m.addAttribute("domicilios", domicilios);

        return "VistaDomiciliario/listar";
    }

    @GetMapping("/detalle/{id}")
	public String detalle(Model model, @PathVariable Long id) {

        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);

		logg.info("Id de la venta {}", id);
		Venta venta = ventasService.findById(id).get();

		model.addAttribute("detalles", venta.getDetalle());

		return "VistaDomiciliario/detalleDom";
	}

    @GetMapping("/ver/{idDomicilio}")
    public String ver(@PathVariable Long idDomicilio, Model m) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        Domicilio domicilio = null;
        if (idDomicilio > 0) {
            domicilio = domiciliod.findOne(idDomicilio);
        } else {
            return "redirect:";
        }

        m.addAttribute("idDomicilio", idDomicilio);
     
        m.addAttribute("accion", "Actualizar Domicilio");
        return "VistaDomiciliario/form";
    }

    @GetMapping("/form")
    public String form(Model m) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        Domicilio domicilio = new Domicilio();

        
        m.addAttribute("domicilio", domicilio);
        m.addAttribute("accion", "Agregar Domicilio");
        return "VistaDomiciliario/form";
    }

    // @PostMapping("/add")
    // public String add(@Valid Domicilio domicilio, BindingResult res, Model m,
    //         SessionStatus status) {
    //             String currentUser = SecurityUtils.getUserName();
    //     m.addAttribute("username", currentUser);

    //     if (res.hasErrors()) {
    //         return "VistaDomiciliario/form";
    //     }

    //     try {
    //         domiciliod.save(domicilio);
    //         status.setComplete();
    //         return "redirect:";

    //     } catch (Exception e) {
    //         // duplicate primary key
    //         m.addAttribute("error", "Error, ya existe en la base de datos");
    //         System.out.println("ya existe");
    //         return "VistaDomiciliario/form";
    //     }
    // }


    @PostMapping("/add")
    public String update(@Valid Long idDomicilio, @Valid String estado) {
       
       
            Domicilio domicilio = domiciliod.findOne(idDomicilio);
            
            domicilio.setEstado(estado);

            domicilioRepository.save(domicilio);
            

        return "redirect:";
    }

   

}
