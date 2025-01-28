package P7;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class P7b {

    // Variables para almacenar la lista de coches y el parking
    static CarDB cdb;
    static Parking miParking;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        // Se comprueba si hay cinco argumentos de entrada (los nombres de los ficheros)
        if (args.length != 5) {
            System.out.println(
                    "Uso correcto: P7b <fichero_parking> <fichero_entradas_salidas> <fichero_parking_actualizado> <fichero_coches_ciudad> <fichero_lista_coches>");
            return; // Se cierra el programa en caso de argumentos incorrectos
        }

        // Se crea una objeto CarDB para almacenar los coches
        cdb = new CarDB();

        String carsFilename = "P7/" + args[3];
        String[] partes = carsFilename.split("\\.");
        carsFilename = partes[0];

        // Se leen y se almacenan los coches a partir del fichero con los coches de la
        // ciudad
        try {
            cdb.readCityCarsFile(carsFilename + ".txt");
        } catch (PIIFileArgumentNotFound e) {
            cdb.readBinaryCityCars(carsFilename + ".dat");
        }

        // Se crea el parking a partir del fichero del parking
        miParking = new Parking("P7/" + args[0]);

        // Se actualiza el parking a partir del fichero de entradas/salidas
        processIO("P7/" + args[1]);

        // Se guarda el parking en el fichero indicado por el tercer argumento de
        // entrada
        miParking.saveParking("P7/" + args[2]);

        // Se ordena la colección de coches por su matrícula
        cdb.sortByBatteryChargeAndPlate();

        // Se guarda la colección de coches en el fichero indicado por el quinto
        // argumento de entrada
        cdb.saveCarsToFile("P7/" + args[4]);

        byte[] bytesArray = cdb.readBinaryFile(carsFilename + ".dat");

        TreeMap<String, Car> tm = cdb.streamToMap(bytesArray);

        cdb.saveSeparatedCarTypes(tm, carsFilename);
    }

    // Método que actualiza el parking según un fichero de entradas/salidas
    public static void processIO(String filename) throws IOException {

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
