package com.sena.proyectodonneta.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sena.proyectodonneta.model.Producto;


public class ProductosExporterPDF {
    private List<Producto> listproducto ;
    
    public ProductosExporterPDF(List<Producto> listproducto){
        super();
        this.listproducto = listproducto;
    }
    private void escribirCabeceraDeTabla(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();
    celda.setBackgroundColor(new Color(189, 80, 80));
    celda.setPadding(5);

    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
    fuente.setColor(Color.white);

    celda.setPhrase(new Phrase("Id",fuente));
    tabla.addCell(celda);

    celda.setPhrase(new Phrase("Nombre",fuente));
    tabla.addCell(celda);

    celda.setPhrase(new Phrase("Cantidad",fuente));
    tabla.addCell(celda);

    celda.setPhrase(new Phrase("Descripcion",fuente));
    tabla.addCell(celda);

    celda.setPhrase(new Phrase("Precio",fuente));
    tabla.addCell(celda);

    celda.setPhrase(new Phrase("Categoria",fuente));
    tabla.addCell(celda);
    
    celda.setPhrase(new Phrase("Actividad",fuente));
    tabla.addCell(celda);
    
}

    private void escribirDatosDeLaTabla(PdfPTable tabla){
        for(Producto producto : listproducto){
            tabla.addCell(String.valueOf(producto.getIdProducto()));
            tabla.addCell(producto.getNombre());
            tabla.addCell(producto.getCantidad().toString());
            tabla.addCell(producto.getDescripcion());
            tabla.addCell(producto.getPrecio().toString());
            tabla.addCell(producto.getCategoria().getNombre());
            tabla.addCell(producto.getEstado().toString());
        }
    }

    public void exportar(HttpServletResponse res) throws DocumentException, IOException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, res.getOutputStream());
        
        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(new Color(189, 80, 80));
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de productos",fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(7);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] {0.8f, 3.5f, 2f, 2.5f, 2f, 2.5f,2f} );
        tabla.setWidthPercentage(110);


        escribirCabeceraDeTabla(tabla);
        escribirDatosDeLaTabla(tabla);
        documento.add(tabla);
        documento.close();
    }
}
