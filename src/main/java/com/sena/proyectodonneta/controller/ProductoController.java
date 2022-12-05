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
import com.sena.proyectodonneta.model.Producto;
import com.sena.proyectodonneta.security.SecurityUtils;
import com.sena.proyectodonneta.service.impl.ICategoriaService;
import com.sena.proyectodonneta.service.impl.IProductoService;
import com.sena.proyectodonneta.service.impl.IUploadFileService;
import com.sena.proyectodonneta.util.ProductosExporterPDF;
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
@SessionAttributes("producto")
@RequestMapping("/admin/producto")
public class ProductoController {

    @Autowired
    private IProductoService productod;

    @Autowired
    private IProductoService productoda;

    @Autowired
    private ICategoriaService categoriad;

    @Autowired
    private IUploadFileService uploadFileService;

    @Autowired
    private  IProductoService productoService;


    @GetMapping("/pdfgenerate")
    public void generatePDF(HttpServletResponse response) throws DocumentException,IOException{
        response.setContentType("application/pdf");	
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerkey = "content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime +  ".pdf";
        response.setHeader(headerkey,headerValue);

        List<Producto> productos = productoService.findAll();

        ProductosExporterPDF exporterPDF = new ProductosExporterPDF(productos);
        exporterPDF.exportar(response);
    }

    @GetMapping(value = "/uploads/{filename:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

        Resource recurso = null;
        try {
            recurso = uploadFileService.load(filename);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()

                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +

                        recurso.getFilename() + "\"")
                .body(recurso);

    }

    @GetMapping("/listar")
    public String listar(Model m) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        m.addAttribute("productos", productoda.findAll());
        return "producto/listar";
    }

    @GetMapping("/ver/{idProducto}")
    public String ver(@PathVariable Long idProducto, Model m) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        Producto producto = null;
        if (idProducto > 0) {
            producto = productod.findOne(idProducto);
        } else {
            return "redirect:listar";
        }

        List<Categoria> listade = categoriad.findAll();
        m.addAttribute("ca", listade);
        m.addAttribute("producto", producto);
        m.addAttribute("accion", "Actualizar Producto");
        return "producto/form";
    }

    @GetMapping("/form")
    public String form(Model m) {
        String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        Producto producto = new Producto();

        List<Categoria> listade = categoriad.findAll();
        m.addAttribute("ca", listade);
        m.addAttribute("producto", producto);
        m.addAttribute("accion", "Agregar Producto");
        return "producto/form";
    }

    @PostMapping("/add")
    public String add(@Valid Producto producto, BindingResult res, Model m, @RequestParam("file") MultipartFile foto,
            SessionStatus status) {
                String currentUser = SecurityUtils.getUserName();
        m.addAttribute("username", currentUser);

        if (res.hasErrors()) {
            return "producto/form";
        }

        if (!foto.isEmpty()) {
            if (producto.getIdProducto() != null && producto.getIdProducto() > 0 && producto.getFoto() != null

                    && producto.getFoto().length() > 0) {

                // Si hay un archivo previo se elimina
                uploadFileService.delete(producto.getFoto());
            }
            // Si no hay archivos definimos un nombre único con el método copy
            String uniqueFilename = null;
            try {
                uniqueFilename = uploadFileService.copy(foto);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Guardamos el archivo con el nombre nuevo en el objeto cliente
            producto.setFoto(uniqueFilename);
        }

        try {
            productod.save(producto);
            status.setComplete();
            return "redirect:listar";

        } catch (Exception e) {
            // duplicate primary key
            m.addAttribute("error", "Error, ya existe en la base de datos");
            System.out.println("ya existe");
            return "producto/form";
        }
    }

    @GetMapping("/delete/{idProducto}")
    public String delete(@PathVariable Long idProducto) {

        if (idProducto > 0) {
            // Buscamos el registro a eliminar
            Producto producto = productod.findOne(idProducto);
            // Eliminamos el registro
            productod.delete(idProducto);
            // Verificamos si tiene un archivo asociado y lo eliminamos del directorio
            if (uploadFileService.delete(producto.getFoto())) {
            }
        }
        return "redirect:/admin/producto/listar";
    }
}
