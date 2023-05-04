package P4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class P4 {

    static CarDB cdb;
    static Parking parking;

    public static void main(String[] args) throws FileNotFoundException {

        // Se comprueba si hay tres argumentos de entrada (los nombres de los ficheros)
        if (args.length != 3) {
            System.out.println("Uso correcto: P4 <fichero_parking> <fichero_entradas_salidas> <fichero_destino>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea una objeto CarDB para almacenar los coches
        cdb = new CarDB();

        // Se leen y se almacenan los coches del fichero "cityCars.txt"
        cdb.readCityCarsFile();

        // Se hace el cálculo de la potencia total
        System.out.println("Total power = " + cdb.computeTotalPower());

        // Se hace el cálculo del nivel de batería medio
        System.out.println("Median Battery Charge Level = " + cdb.computeAverageBatteryLevel());

        // Se crea el parking a partir del primer fichero
        parking = new Parking(args[0]);

        // Se actualiza el parking a partir del fichero de entradas/salidas
        processIO(args[1]);

        // Se guarda el parking en el fichero indicado por el tercer argumento de
        // entrada
        parking.saveParking(args[2]);
    }

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
                parking.carEntry(cdb.getCarFromPlate(partes[1]));
            } else {
                parking.carDeparture(partes[1]);
            }
        }

        // Se cierra el Scanner
        input.close();
    }
}
