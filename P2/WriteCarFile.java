package P2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriteCarFile {

    public static void main(String[] args) throws FileNotFoundException {

        // Se comprueba si hay un solo argumento de entrada (el nombre del fichero)
        if (args.length != 1) {
            System.out.println("Uso correcto: WriteCarFile <fichero>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea un objeto File con el nombre indicado en la ejecución del programa
        File archivo = new File(args[0]);

        // Se comprueba si ya existe un fichero con ese nombre
        if (archivo.exists()) {
            System.out.println("El archivo ya existe. ¿Desea continuar? (si/no)");
            Scanner input = new Scanner(System.in);

            while (true) {
                String respuesta = input.nextLine(); // Se lee la respuesta del usuario
                if (respuesta.equalsIgnoreCase("si")) {
                    break; // Se continua con el programa
                } else if (respuesta.equalsIgnoreCase("no")) {
                    return; // Se cierra el programa
                } else {
                    System.out.println("Respuesta incorrecta"); // Se vuelve a pedir una respuesta
                }
            }
        }

        // Se crea el archivo
        PrintWriter fichero = new PrintWriter(archivo);

        // Se escribe la información (en este caso, datos inventados)
        fichero.println("# Lista de coches");
        fichero.println("C;1234LFG;Seat;100;150");
        fichero.println("E;2345KJK;Peugeot;60;10,5");
        fichero.println("H;3456LCP;Ford;130;70;55,5;200;400;300");
        fichero.println("# Fin de la lista");
        fichero.close();
    }
}