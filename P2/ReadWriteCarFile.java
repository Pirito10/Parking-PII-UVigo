package P2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadWriteCarFile {

    public static void main(String[] args) throws FileNotFoundException {

        // Se comprueba si hay un solo argumento de entrada (el nombre del fichero)
        if (args.length != 2) {
            System.out.println("Uso correcto: ReadWriteCarFile <fichero_entrada> <fichero_salida>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea un objeto File con el nombre indicado en la ejecución del programa
        File archivo_entrada = new File(args[0]);

        // Se comprueba si el fichero existe
        if (!archivo_entrada.exists()) {
            System.out.println("No existe tal archivo");
            return; // Se termina el programa en caso de no haber fichero
        }

        // Se crea un objeto Scanner para leer el archivo
        Scanner input = new Scanner(archivo_entrada);

        // Variable para almacenar la cantidad de coches
        int N = 0;

        // Se lee línea por línea para calcular la cantidad de coches
        while (input.hasNext()) {

            // Si la línea empieza por '#', se considera un comentario y se ignora
            if (!input.nextLine().startsWith("#")) {
                N++;
            }
        }

        // Se crea el array para almacenar todos los coches
        String[][] coches = new String[N][];

        // Se vuelve a crear un objeto Scanner para leer el archivo
        input = new Scanner(archivo_entrada);

        // Variable de control para la posición dentro del array
        int i = 0;

        // Se lee línea por línea
        while (input.hasNext()) {

            String linea = input.nextLine();

            // Si la línea empieza por '#', se considera un comentario y se ignora
            if (linea.startsWith("#")) {
                continue;
            }

            // Se separa la línea por donde había un ';'
            String[] partes = linea.split(";");
            // Se asigna la información de esa línea a la fila 'i' del array
            coches[i] = partes;
            // Se incrementa el contador
            i++;
        }

        // Se cierra el objeto Scanner
        input.close();

        // Se crea un objeto File con el nombre indicado en la ejecución del programa
        File archivo_salida = new File(args[1]);

        // Se crea el archivo
        PrintWriter fichero = new PrintWriter(archivo_salida);

        // Se recorren todas las filas de array de coches
        for (i = 0; i < coches.length; i++) {

            // Se inicializa lo que se va a imprimir y que es común a todos los tipos de
            // motor (motor, matrícula y fabricante)
            String linea = coches[i][0] + ";" + coches[i][2] + ";" + coches[i][1] + ";";

            // Se añade el resto de información
            for (int j = 3; j < coches[i].length; j++) {
                linea = linea.concat(coches[i][j]);
                // Se comprueba si habrá más información, ya que en caso afirmativo, se debe
                // añadir un punto y coma
                if (coches[i].length > j + 1) {
                    linea = linea + ";";
                }
            }
            // Se imprime la línea en el fichero
            fichero.println(linea);

            // Para los coches eléctricos
            if (coches[i][0].equals("E")) {

                // Se cambia el separador decimal del porcentaje de carga de ',' a '.'
                String carga = coches[i][4].replace(',', '.');

                // Si su valor en inferior a 20, se imprime la línea de batería baja
                if (Float.parseFloat(carga) < 20) {
                    fichero.println("# low battery");
                }
            }
        }

        // Se cierra el objeto PrintWriter
        fichero.close();
    }
}