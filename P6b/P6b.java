package P6b;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P6b {

    // Variables para almacenar la lista de coches y el parking
    static CarDB cdb;
    static Parking miParking;

    public static void main(String[] args) throws FileNotFoundException {

        // Se comprueba si hay cinco argumentos de entrada (los nombres de los ficheros)
        if (args.length != 6) {
            System.out.println(
                    "Uso correcto: P6b <fichero_parking> <fichero_entradas_salidas> <fichero_parking_actualizado> <fichero_coches_ciudad> <fichero_lista_coches> <fichero_dibujo_parking>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea una objeto CarDB para almacenar los coches
        cdb = new CarDB();

        // Se leen y se almacenan los coches a partir del fichero con los coches de la
        // ciudad
        cdb.readCityCarsFile("P6b/" + args[3]);

        // Se crea el parking a partir del fichero del parking
        miParking = new Parking("P6b/" + args[0]);

        // Se actualiza el parking a partir del fichero de entradas/salidas
        processIO("P6b/" + args[1]);

        // Se guarda el parking en el fichero indicado por el tercer argumento de
        // entrada
        miParking.saveParking("P6b/" + args[2]);

        // Se ordena la colección de coches por su matrícula
        cdb.sortByPlate();

        // Se guarda la colección de coches en el fichero indicado por el quinto
        // argumento de entrada
        cdb.saveCarsToFile("P6b/" + args[4]);

        // Se crea un objeto File para guardar el mapa del parking
        File archivo = new File("P6b/" + args[5]);

        // Se crea el archivo
        PrintWriter fichero = new PrintWriter(archivo);

        // Se imprime el mapa en el archivo
        fichero.println(miParking.toMap());

        // Se cierra el PrintWriter
        fichero.close();
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

            // Se busca el coche en la lista
            Car car = cdb.getCarFromPlate(partes[1]);

            // Si es una entrada, se almacena el coche en el parking
            if (partes[0].equals("I")) {
                miParking.carEntry(car, partes[2]);
                // Si es una salida, se elimina el coche del parking y se actualiza su nivel de
                // batería en caso de ser eléctrico o híbrido
            } else {
                String entryTime = miParking.carDeparture(partes[1]);
                if (car instanceof ElectricCar || car instanceof HybridCar) {
                    cdb.increaseCarBatteryChargeLevel(partes[1], entryTime, partes[2]);
                }
            }
        }

        // Se cierra el Scanner
        input.close();
    }
}
