package PdfReader;

import conexionDB.Conexion;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PdfReader {
    public static String obtenerNombreArchivo(String ruta) {
        int indiceBarra = ruta.lastIndexOf("/");
        String nombreArchivo = ruta.substring(indiceBarra + 1);
        return nombreArchivo;
    }

    public static String obtenerCodigoDocumento(String nombreArchivo) {
        String codigoDocumento = nombreArchivo.substring(0, nombreArchivo.lastIndexOf("."));
        return codigoDocumento;
    }
    public PdfReader(String path){

        String nombreArchivo = obtenerNombreArchivo(path);
        String codigoDocumento = obtenerCodigoDocumento(nombreArchivo);

        String tipoLibro = codigoDocumento;
        int pagina;
        String texto;


        try {
            File file = new File(path);
            PDDocument document = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            int totalPages = document.getNumberOfPages();


            for (int i = 1; i <= totalPages; i++) {
                stripper.setStartPage(i);
                stripper.setEndPage(i);
                String text = stripper.getText(document);
                text = text.replaceAll("\n", " "); // Reemplaza los saltos de línea con espacios

                pagina = i;
                texto = text.trim();
                Conexion con = new Conexion(tipoLibro, pagina, texto);

            }


            System.out.println("Se terminó de leer y guardar el texto de todas las páginas.");
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
