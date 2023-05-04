package P2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCarFile {

    public static void main(String[] args) throws FileNotFoundException {

        // Se comprueba si hay un solo argumento de entrada (el nombre del fichero)
        if (args.length != 1) {
            System.out.println("Uso correcto: ReadCarFile <fichero>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea un objeto File con el nombre indicado en la ejecución del programa
        File archivo = new File(args[0]);

        // Se comprueba si el fichero existe
        if (!archivo.exists()) {
            System.out.println("No existe tal archivo");
            return; // Se termina el programa en caso de no haber fichero
        }

        // Se crea un objeto Scanner para leer el archivo
        Scanner input = new Scanner(archivo);

        // Se lee línea por línea
        while (input.hasNext()) {

            String linea = input.nextLine();

            // Si la línea empieza por '#', se considera un comentario y se ignora
            if (linea.startsWith("#")) {
                continue;
            }

            // Se separa la línea por donde había un ';'
            String[] partes = linea.split(";");

            // Se inicializa lo que se va a imprimir por pantalla y que es común a todos los
            // tipos de motor (motor, matrícula y fabricante)
            String output = "Tipo=" + partes[0] + " Mat.=" + partes[1] + " Fab.=" + partes[2];

            // Variable para almacenar la información sobre las multas
            String multas;

            // Un caso diferente para cada tipo de motor
            switch (partes[0]) {
                // Para motores de combustión, solo potencia mecánica
                case "C" -> {
                    // Se añade la nueva información a la anterior
                    output = output + " P.Mec.=" + partes[3];

                    // Se comprueba si hay multas
                    if (partes.length > 4) {
                        // En caso de haberlas, se inicializa la variable de las multas
                        multas = " MUL=";
                        // Se recorren todas las multas, añadiendo una cada vez
                        for (int i = 4; i < partes.length; i++) {
                            multas = multas.concat(partes[i]);
                            // Se comprueba si habrá más multas, ya que en caso afirmativo, se debe añadir
                            // una coma
                            if (partes.length > i + 1) {
                                multas = multas + ",";
                            }
                        }
                        // Se añaden las multas a la información del vehículo
                        output = output + multas;
                    }
                }

                // Para motores eléctricos, potencia eléctrica y porcentaje de carga
                case "E" -> {
                    // Se añade la nueva información a la anterior
                    output = output + " P.Ele.=" + partes[3] + " %carga=" + partes[4];

                    // Se comprueba si hay multas
                    if (partes.length > 5) {
                        // En caso de haberlas, se inicializa la variable de las multas
                        multas = " MUL=";
                        // Se recorren todas las multas, añadiendo una cada vez
                        for (int i = 5; i < partes.length; i++) {
                            multas = multas.concat(partes[i]);
                            // Se comprueba si habrá más multas, ya que en caso afirmativo, se debe añadir
                            // una coma
                            if (partes.length > i + 1) {
                                multas = multas + ",";
                            }
                        }
                        // Se añaden las multas a la información del vehículo
                        output = output + multas;
                    }
                }

                // Para motores híbridos, potencia mecánica, eléctrica y porcentaje de carga
                case "H" -> {
                    // Se añade la nueva información a la anterior
                    output = output + " P.Mec.=" + partes[3] + " P.Ele.=" + partes[4] + " %carga=" + partes[5];

                    // Se comprueba si hay multas
                    if (partes.length > 6) {
                        // En caso de haberlas, se inicializa la variable de las multas
                        multas = " MUL=";
                        // Se recorren todas las multas, añadiendo una cada vez
                        for (int i = 6; i < partes.length; i++) {
                            multas = multas.concat(partes[i]);
                            // Se comprueba si habrá más multas, ya que en caso afirmativo, se debe añadir
                            // una coma
                            if (partes.length > i + 1) {
                                multas = multas + ",";
                            }
                        }
                        // Se añaden las multas a la información del vehículo
                        output = output + multas;
                    }
                }
            }

            // Se imprime toda la información del vehículo
            System.out.println(output);

        }
        // Se cierra el objeto Scanner
        input.close();
    }
}