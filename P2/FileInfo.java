package P2;

import java.io.File;
import java.util.Date;

public class FileInfo {

    public static void main(String[] args) {

        // Se comprueba si hay un solo argumento de entrada (el nombre del fichero)
        if (args.length != 1) {
            System.out.println("Uso correcto: FileInfo <fichero>");
            return; // Se cierra el programa en caso de error en los argumentos de entrada
        }

        // Se crea un objeto File con el nombre indicado en la ejecución del programa
        File archivo = new File(args[0]);

        // Variables para almacenar la información del fichero
        boolean exists, readable, writable, executable;
        String path;
        long size, last_modified;

        // Se comprueba si el fichero existe
        exists = archivo.exists();

        if (exists) {
            System.out.println("Archivo existente: sí");
        } else {
            System.out.println("Archivo existente: no");
            return; // Se termina el programa en caso negativo
        }

        // Se obtiene toda la información sobre el fichero
        path = archivo.getAbsolutePath();
        size = archivo.length();
        readable = archivo.canRead();
        writable = archivo.canWrite();
        executable = archivo.canExecute();
        last_modified = archivo.lastModified();
        Date date = new Date(last_modified); // Se hace la conversión de la fecha en variable long a una fecha legible

        // Se muestra toda la información obtenida
        System.out.println("Ruta: " + path);
        System.out.println("Tamaño: " + size + " bytes");

        if (readable) {
            System.out.println("Se puede leer: sí");
        } else {
            System.out.println("Se puede leer: no");
        }

        if (writable) {
            System.out.println("Se puede escribir: sí");
        } else {
            System.out.println("Se puede escribir: no");
        }

        if (executable) {
            System.out.println("Se puede ejecutar: sí");
        } else {
            System.out.println("Se puede ejecutar: no");
        }

        System.out.println("Última modificación: " + date);

        // Se renombra el archivo
        boolean exito = archivo.renameTo(new File(archivo.getAbsoluteFile().getParent() + "\\nombreNuevo.txt"));

        // Se comprueba si se renombró exitosamente
        if (!exito) {
            System.out.println("\nHa ocurrido un error al renombrar el archivo");
        }
    }
}