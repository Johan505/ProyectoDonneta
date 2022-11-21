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
import com.sena.proyectodonneta.model.Venta;

public class VentasExporterPDF {
    private List<Venta> listventa ;
    
    public VentasExporterPDF(List<Venta> listventa){
        super();
        this.listventa = listventa;
    }
    private void escribirCabeceraDeTabla(PdfPTable tabla){
        PdfPCell celda = new PdfPCell();
    celda.setBackgroundColor(new Color(189, 80, 80));
    celda.setPadding(5);

    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
    fuente.setColor(Color.white);

    celda.setPhrase(new Phrase("Numero De Venta",fuente));
    tabla.addCell(celda);

    celda.setPhrase(new Phrase("Fecha",fuente));
    tabla.addCell(celda);

    celda.setPhrase(new Phrase("Total",fuente));
    tabla.addCell(celda);

    
}
    private void escribirDatosDeLaTabla(PdfPTable tabla){
        for(Venta venta : listventa){
            tabla.addCell(String.valueOf(venta.getNumero()));
            tabla.addCell(venta.getFechaCreacion().toString());
            tabla.addCell(String.valueOf(venta.getTotal()));
        }
    }
    public void exportar(HttpServletResponse res) throws DocumentException, IOException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, res.getOutputStream());
        
        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(new Color(189, 80, 80));
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de Categorias",fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(3);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] {3f, 2.3f, 2.2f} );
        tabla.setWidthPercentage(110);


        escribirCabeceraDeTabla(tabla);
        escribirDatosDeLaTabla(tabla);
        documento.add(tabla);
        documento.close();
    }
}
