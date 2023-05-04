package P5a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P5a {

    static CarDB cdb;
    static Parking miParking;

    public static void main(String[] args) throws FileNotFoundException {

        // Se comprueba si hay cinco argumentos de entrada (los nombres de los ficheros)
        if (args.length != 5) {
            System.out.println(
                    "Uso correcto: P5a <fichero_parking> <fichero_entradas_salidas> <fichero_parking_actualizado> <fichero_coches_ciudad> <fichero_coleccion_coches>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea una objeto CarDB para almacenar los coches
        cdb = new CarDB();

        // Se leen y se almacenan los coches a partir del fichero con los coches de la
        // ciudad
        cdb.readCityCarsFile(args[3]);

        // Se crea el parking a partir del fichero del parking
        miParking = new Parking(args[0]);

        // Se actualiza el parking a partir del fichero de entradas/salidas
        processIO(args[1]);

        // Se guarda el parking en el fichero indicado por el tercer argumento de
        // entrada
        miParking.saveParking(args[2]);

        // Se guarda la colección de coches en el fichero indicado por el quinto
        // argumento de entrada
        cdb.saveCarsToFile(args[4]);
    }

    // Método que actualiza el parking según un fichero de entradas/salidas
    public static void processIO(String filename) throws FileNotFoundException {

        // Se crea un objeto File con el nombre del fichero
        File archivo = new File(filename);

        // Se comprueba si el fichero existe
        if (!archivo.exists()) {
            System.out.println("No existe tal fichero");
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

            // Si es una entrada, se almacena el coche en el parking; si es una salida, se
            // elimina el coche del parking
            if (partes[0].equals("I")) {
                miParking.carEntry(cdb.getCarFromPlate(partes[1]), partes[2]);
            } else {
                miParking.carDeparture(partes[1]);
            }
        }

        // Se cierra el Scanner
        input.close();
    }
}
