package com.sena.proyectodonneta.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.sena.proyectodonneta.model.Detalle;
import com.sena.proyectodonneta.model.Domicilio;
import com.sena.proyectodonneta.model.Producto;
import com.sena.proyectodonneta.model.User;
import com.sena.proyectodonneta.model.Venta;
import com.sena.proyectodonneta.security.SecurityUtils;
import com.sena.proyectodonneta.service.UserService;
import com.sena.proyectodonneta.service.impl.ICategoriaService;
import com.sena.proyectodonneta.service.impl.IDetalleService;
import com.sena.proyectodonneta.service.impl.IDomicilioService;
import com.sena.proyectodonneta.service.impl.IProductoService;
import com.sena.proyectodonneta.service.impl.IVentaService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {
    
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
	private IProductoService productod;

	@Autowired
	private ICategoriaService categoriad;

    @Autowired
	private IDetalleService detalleService;

	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IDomicilioService domicilioService;

	@Autowired
	private UserService userService;

	List<User> users = new ArrayList<User>();

	List<Detalle> detalles = new ArrayList<Detalle>();

	List<Venta> ventas = new ArrayList<Venta>();

	List<Domicilio> domicilios = new ArrayList<Domicilio>();

	Venta venta = new Venta();

	User user = new User();

	Domicilio domicilio = new Domicilio();
    
    @GetMapping("")
    public String userView(Model model, HttpSession session)
    {

		log.info("Sesion del usuario: {}",session.getAttribute("id"));

        String currentUser = SecurityUtils.getUserName();
        model.addAttribute("username", currentUser);
        model.addAttribute("productos", productod.findProductos());
		model.addAttribute("categorias", categoriad.findAll());

		model.addAttribute("sesion", session.getAttribute("id"));
        return "VistaCliente/index";
    }


    @GetMapping("/ver-producto/{idProducto}")
	public String verProducto(@PathVariable Long idProducto, Model m) {
		log.info("Id producto enviado como parámetro {}", idProducto);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productod.get(idProducto);
		producto = productoOptional.get();

		m.addAttribute("producto", producto);

		return "VistaCliente/product-detail";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Long id, @RequestParam Long cantidad, Model model) {


		Detalle detalleVenta = new Detalle();
		Producto producto = new Producto();
		double sumaTotal = 0;


		Optional<Producto> optionalProducto = productod.get(id);
		log.info("Producto añadido: {}", optionalProducto.get());
		log.info("Cantidad: {}", cantidad);
		producto = optionalProducto.get();

		detalleVenta.setCantidad(cantidad);
		detalleVenta.setPrecio(producto.getPrecio());
		detalleVenta.setNombre(producto.getNombre());
		detalleVenta.setTotal(producto.getPrecio() * cantidad);
		detalleVenta.setProducto(producto);

		Long idProducto = producto.getIdProducto();
		boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getIdProducto() == idProducto);

		if (!ingresado) {
			detalles.add(detalleVenta);
		}

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		
		venta.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("venta", venta);

		
		return "VistaCliente/shoping-cart";
	}

	@GetMapping("/delete/cart/{id}")
	public String deleteProductoCart(@PathVariable Long id, Model model) {

		List<Detalle> ventasNuevas = new ArrayList<Detalle>();

		for (Detalle detalleVenta : detalles) {
			if (detalleVenta.getProducto().getIdProducto() != id) {
				ventasNuevas.add(detalleVenta);
			}
		}

		detalles = ventasNuevas;

		double sumaTotal = 0;
		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

		venta.setTotal(sumaTotal);
		model.addAttribute("cart", detalles);
		model.addAttribute("venta", venta);

		return "VistaCliente/shoping-cart";
	}

	@GetMapping("/getCart")
	public String getCart(Model model, HttpSession session) {

		model.addAttribute("cart", detalles);
		model.addAttribute("venta", venta);


		return "/VistaCliente/shoping-cart";
	}

	@GetMapping("/Datos")
	public String datos(Model model, HttpSession session) {

		
		
		Domicilio domicilio = new Domicilio();
		
		model.addAttribute("domicilio", domicilio);
		model.addAttribute("cart", detalles);
		model.addAttribute("venta", venta);
		model.addAttribute("usuario", user);

		return "VistaCliente/DatosDomicilio";
	}

	@PostMapping("/guardarVenta")
	public String guardarVenta(@Valid Domicilio domicilio, BindingResult res, HttpSession session, SessionStatus status, Model m) {
	
		Date fechaCreacion = new Date();
		venta.setFechaCreacion(fechaCreacion);
		venta.setNumero(ventaService.generarNumeroVenta());

		String currentUser = SecurityUtils.getUserName();
        
		User user = userService.findByEmail(currentUser);
		
		

		venta.setUser(user);
		ventaService.save(venta);

		// guardar detalles
		for (Detalle dt : detalles) {
			dt.setVenta(venta);
			detalleService.save(dt);
		}

		domicilio.setVenta(venta);

		domicilioService.save(domicilio);
		status.setComplete();
		/// limpiar lista y orden
		venta = new Venta();
		domicilio = new Domicilio();
		detalles.clear();

		return "redirect:/user";
	}



}
