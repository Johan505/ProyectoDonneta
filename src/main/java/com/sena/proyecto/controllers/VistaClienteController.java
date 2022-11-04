package com.sena.proyecto.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.sena.proyecto.model.Detalle;
import com.sena.proyecto.model.Domicilio;
import com.sena.proyecto.model.Venta;
import com.sena.proyecto.model.Producto;
import com.sena.proyecto.service.ICategoriaService;
import com.sena.proyecto.service.IDetalleService;
import com.sena.proyecto.service.IDomicilioService;
import com.sena.proyecto.service.IVentaService;
import com.sena.proyecto.service.IProductoService;

@Controller
public class VistaClienteController {

	private final Logger log = LoggerFactory.getLogger(VistaClienteController.class);
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

	List<Detalle> detalles = new ArrayList<Detalle>();

	List<Venta> ventas = new ArrayList<Venta>();

	List<Domicilio> domicilios = new ArrayList<Domicilio>();

	Venta venta = new Venta();

	Domicilio domicilio = new Domicilio();

	@GetMapping(path = { "/landing", "", "/" })
	public String landing(Model m) {
		return "VistaCliente/Landing";
	}

	@GetMapping(path = { "/index" })
	public String inicio(Model m) {
		m.addAttribute("productos", productod.findAll());
		m.addAttribute("categorias", categoriad.findAll());
		return "VistaCliente/index";
	}

	@GetMapping(path = { "/Login" })
	public String login() {
		return "VistaCliente/Login";
	}

	@GetMapping(path = { "/register" })
	public String register() {
		return "VistaCliente/Register";
	}

	@GetMapping("/ver-producto/{idProducto}")
	public String verProducto(@PathVariable Integer idProducto, Model m) {
		log.info("Id producto enviado como parámetro {}", idProducto);
		Producto producto = new Producto();
		Optional<Producto> productoOptional = productod.get(idProducto);
		producto = productoOptional.get();

		m.addAttribute("producto", producto);

		return "VistaCliente/product-detail";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
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

		Integer idProducto = producto.getIdProducto();
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
	public String deleteProductoCart(@PathVariable Integer id, Model model) {

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

		return "VistaCliente/DatosDomicilio";
	}

	@PostMapping("/guardarVenta")
	public String guardarVenta(@Valid Domicilio domicilio, HttpSession session, SessionStatus status, Model m) {

		Date fechaCreacion = new Date();
		venta.setFechaCreacion(fechaCreacion);
		venta.setNumero(ventaService.generarNumeroVenta());

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

		return "redirect:/index";
	}

}