import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class CarDB {

    // Mapa para almacenar los coches
    LinkedHashMap<String, Car> cityCars;

    // Constructor vacío
    public CarDB() {
        cityCars = new LinkedHashMap<>();
    }

    // Método que lee los coches del fichero indicado y los almacena en el mapa,
    // siempre y cuando sus datos sean correctos
    public void readCityCarsFile(String filename) throws FileNotFoundException {

        // Se crea un objeto File
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

            // Un caso diferente para cada tipo de motor
            switch (partes[0]) {
                // Para motores de combustión
                case "C":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea el
                    // coche y se añade al mapa
                    if (Car.isValidPlate(partes[1]) && CombustionCar.isValidPower(Integer.parseInt(partes[3]))) {
                        cityCars.put(partes[1], new CombustionCar(partes[1], partes[2], Integer.parseInt(partes[3])));
                    }
                    break;

                // Para motores eléctricos
                case "E":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea el
                    // coche y se añade al mapa
                    if (Car.isValidPlate(partes[1]) && ElectricCar.isValidPower(Integer.parseInt(partes[3]))
                            && ElectricCar.isValidBattery(Float.parseFloat(partes[4].replace(',', '.')))) {
                        cityCars.put(partes[1], new ElectricCar(partes[1], partes[2], Integer.parseInt(partes[3]),
                                Float.parseFloat(partes[4].replace(',', '.'))));
                    }
                    break;

                // Para motores híbridos
                case "H":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea el
                    // coche y se añade al mapa
                    if (Car.isValidPlate(partes[1]) && HybridCar.isValidMechanicalPower(Integer.parseInt(partes[3]))
                            && HybridCar.isValidElectricPower(Integer.parseInt(partes[4]))
                            && HybridCar.isValidBattery(Float.parseFloat(partes[5].replace(',', '.')))) {
                        cityCars.put(partes[1], new HybridCar(partes[1], partes[2], Integer.parseInt(partes[3]),
                                Integer.parseInt(partes[4]), Float.parseFloat(partes[5].replace(',', '.'))));
                    }
                    break;
            }
        }

        // Se cierra el Scanner
        input.close();
    }

    // Método que busca un coche a partir de una matrícula
    public Car getCarFromPlate(String plate) {
        return cityCars.get(plate);
    }

    // Método que calcula la suma total de potencia
    public int computeTotalPower() {

        // Contador de la potencia total
        int potencia = 0;

        // Se recorre el mapa de coches y se suman sus respectivas potencias
        for (Car car : cityCars.values()) {
            potencia = potencia + car.getTotalPower();
        }

        return potencia;
    }

    // Método que calcula el nivel de batería medio
    public float computeAverageBatteryLevel() {

        // Contador de coches (eléctricos + híbridos)
        int i = 0;
        // Contador de la batería total
        float bateria = 0;

        // Se recorre el mapa de coches sumando las baterías de los eléctricos e
        // híbridos
        for (Car car : cityCars.values()) {
            if (car instanceof ElectricCar) {
                bateria = bateria + ((ElectricCar) car).getBatteryCharge();
                i++;
            } else if (car instanceof HybridCar) {
                bateria = bateria + ((HybridCar) car).getBatteryCharge();
                i++;
            }
        }

        // Cálculo de la media
        return bateria / i;
    }

    // Método que imprime la lista de coches en un archivo
    public void saveCarsToFile(String filename) throws FileNotFoundException {

        // Se crea un objeto File con el nombre indicado
        File archivo = new File(filename);

        // Se crea el archivo
        PrintWriter fichero = new PrintWriter(archivo);

        // Se recorre el mapa de coches y se imprimen en el archivo uno a uno
        for (Car car : cityCars.values()) {
            fichero.println(car);
        }

        // Se cierra el PrintWriter
        fichero.close();
    }

    // Método que actualiza el nivel de batería de coches eléctricos e híbridos en
    // función del tiempo dentro del parking
    public float increaseCarBatteryChargeLevel(String plate, String entryTime, String departureTime) {

        // Se busca el coche en el mapa
        Car car = getCarFromPlate(plate);

        // Se calcula el tiempo que pasó dentro del parking
        float time = intervalInHours(entryTime, departureTime);

        float chargeTime = computeCarChargeTime(plate, time);

        // Se actualiza su nivel de batería si es un coche eléctrico o híbrido
        if (car instanceof ElectricCar) {
            ((ElectricCar) car).increaseBatteryChargeLevel(time);
        } else if (car instanceof HybridCar) {
            ((HybridCar) car).increaseBatteryChargeLevel(time);
        }

        return chargeTime * ElectricCharger.POWER;
    }

    // Método que calcula el tiempo entre dos horas
    public static float intervalInHours(String inTime, String outTime) {
        int hi = Integer.parseInt(inTime.split(":")[0].trim());
        int mi = Integer.parseInt(inTime.split(":")[1].trim());
        int ho = Integer.parseInt(outTime.split(":")[0].trim());
        int mo = Integer.parseInt(outTime.split(":")[1].trim());
        int dif = (ho * 60 + mo) - (hi * 60 + mi);
        return ((float) dif / 60);
    }

    // Método que ordena el mapa de coches por matrícula
    public void sortByPlate() {
        // Se crea una lista a partir del mapa de coches
        ArrayList<Car> lista = new ArrayList<>(cityCars.values());
        // Se ordena la lista por matrícula
        Collections.sort(lista);
        // Se vacía el mapa de coches
        cityCars.clear();
        // Se añaden los coches de la lista al mapa, una vez ordenados
        for (Car car : lista) {
            cityCars.put(car.getPlate(), car);
        }
    }

    // Método que ordena el mapa de coches por nivel de batería, o por matrícula en
    // caso de mismo nivel
    public void sortByBatteryChargeAndPlate() {
        // Se crea una lista a partir del mapa de coches
        ArrayList<Car> lista = new ArrayList<>(cityCars.values());
        // Se ordena la lista por nivel de batería
        lista.sort(new CarComparatorByBatteryLevelAndPlate());
        // Se vacía el mapa de coches
        cityCars.clear();
        // Se añaden los coches de la lista al mapa, una vez ordenados
        for (Car car : lista) {
            cityCars.put(car.getPlate(), car);
        }
    }

    public float computeCarChargeTime(String plate, float parkingDuration) {

        Car car = getCarFromPlate(plate);

        float initialBatteryLevel;
        int BATTERY_CAPACITY;

        if (car instanceof CombustionCar) {
            return 0;
        } else if (car instanceof ElectricCar) {
            initialBatteryLevel = ((ElectricCar) car).getBatteryCharge();
            BATTERY_CAPACITY = ElectricCar.BATTERY_CAPACITY;
        } else {
            initialBatteryLevel = ((HybridCar) car).getBatteryCharge();
            BATTERY_CAPACITY = HybridCar.BATTERY_CAPACITY;
        }

        int CHARGER_POWER = ElectricCharger.POWER;

        float maxChargeTime = (100 - initialBatteryLevel) / 100 * ((float) BATTERY_CAPACITY / CHARGER_POWER);

        if (parkingDuration > maxChargeTime) {
            return maxChargeTime;
        } else {
            return parkingDuration;
        }
    }
}