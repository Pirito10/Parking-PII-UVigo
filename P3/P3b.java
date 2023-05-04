package P3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P3b {

    public static void main(String[] args) throws FileNotFoundException {

        // Se comprueba si hay tres argumentos de entrada (los nombres de los ficheros)
        if (args.length != 3) {
            System.out.println("Uso correcto: P3b <fichero_parking> <fichero_entradas_salidas> <fichero_destino>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea el parking a partir del primer fichero
        Parking parking = new Parking(args[0]);

        // Se crea un objeto File con el nombre del segundo fichero
        File entradas_salidas = new File(args[1]);

        // Se comprueba si el fichero existe
        if (!entradas_salidas.exists()) {
            System.out.println("No existe tal fichero");
            return; // Se termina el programa en caso de no haber fichero
        }

        // Se crea un objeto Scanner para leer el archivo
        Scanner input = new Scanner(entradas_salidas);

        // Se lee línea por línea
        while (input.hasNext()) {

            String linea = input.nextLine();

            // Si la línea empieza por '#', se considera un comentario y se ignora
            if (linea.startsWith("#")) {
                continue;
            }

            // Se separa la línea por donde había un ';'
            String[] partes = linea.split(";");

            // Si es una entrada, se almacena el coche en el parking; si es una salida, se
            // elimina el coche del parking
            if (partes[0].equals("I")) {
                parking.carEntry(partes[1], partes[2].charAt(0));
            } else {
                parking.carDeparture(partes[1]);
            }
        }

        // Se cierra el Scanner
        input.close();

        // Se guarda el parking en el fichero indicado por el tercer argumento de
        // entrada
        parking.saveParking(args[2]);

        // Se crea un objeto File para guardar el mapa del parking
        File archivo = new File("ParkingMap.txt");

        // Se crea el archivo
        PrintWriter fichero = new PrintWriter(archivo);

        // Se imprime el mapa en el archivo
        fichero.println(parking.toMap());

        // Se cierra el PrintWriter
        fichero.close();
    }
}