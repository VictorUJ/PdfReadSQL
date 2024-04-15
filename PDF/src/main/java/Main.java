import PdfReader.PdfReader;
import conexionDB.Conexion;
import conexionDB.DataConexion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  {
        String directorio = "/home/victor/Documentos/Trabajos/Lectura_PDF_JAVA";
        String[] archivosPDF = obtenerArchivosPDF(directorio);


        for (String archivo : archivosPDF) {
            PdfReader libro = new PdfReader(directorio + "/" + archivo);
        }


        //Conexion conec = new Conexion("Prueba2",1,"Lo que sea");

    }

    public static String[] obtenerArchivosPDF(String directorio) {

        File carpeta = new File(directorio);
        List<String> archivosPDF = new ArrayList<>();

        if (carpeta.exists() && carpeta.isDirectory()) {

            File[] archivos = carpeta.listFiles();

            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().toLowerCase().endsWith(".pdf")) {
                    archivosPDF.add(archivo.getName());
                }
            }
        } else {
            System.out.println("El directorio especificado no existe o no es un directorio válido.");
        }


        return archivosPDF.toArray(new String[0]);
    }
}

