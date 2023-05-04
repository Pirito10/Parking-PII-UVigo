package P4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CarDB {

    // Array para almacenar los coches
    Car[] cityCars = new Car[100];

    // Método que lee los coches del fichero "cityCars.txt" y los almacena en el
    // array correspondiente, siempre y cuando sus datos sean correctos
    public void readCityCarsFile() throws FileNotFoundException {

        // Se crea un objeto File
        File archivo = new File("cityCars.txt");

        // Se comprueba si el fichero existe
        if (!archivo.exists()) {
            System.out.println("No existe tal fichero");
            return; // Se termina el programa en caso de no haber fichero
        }

        // Se crea un objeto Scanner para leer el archivo
        Scanner input = new Scanner(archivo);

        // Contador de coches
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

            // Un caso diferente para cada tipo de motor
            switch (partes[0]) {
                // Para motores de combustión
                case "C":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea y
                    // almacena el coche en el array, y se incrementa el contador
                    if (Car.isValidPlate(partes[1]) && CombustionCar.isValidPower(Integer.parseInt(partes[3]))) {
                        cityCars[i] = new CombustionCar(partes[1], partes[2], Integer.parseInt(partes[3]));
                        i++;
                    }
                    break;

                // Para motores eléctricos
                case "E":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea y
                    // almacena el coche en el array, y se incrementa el contador
                    if (Car.isValidPlate(partes[1]) && ElectricCar.isValidPower(Integer.parseInt(partes[3]))
                            && ElectricCar.isValidBattery(Float.parseFloat(partes[4].replace(',', '.')))) {
                        cityCars[i] = new ElectricCar(partes[1], partes[2], Integer.parseInt(partes[3]),
                                Float.parseFloat(partes[4].replace(',', '.')));
                        i++;
                    }
                    break;

                // Para motores híbridos
                case "H":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea y
                    // almacena el coche en el array, y se incrementa el contador
                    if (Car.isValidPlate(partes[1]) && HybridCar.isValidMechanicalPower(Integer.parseInt(partes[3]))
                            && HybridCar.isValidElectricPower(Integer.parseInt(partes[4]))
                            && HybridCar.isValidBattery(Float.parseFloat(partes[5].replace(',', '.')))) {
                        cityCars[i] = new HybridCar(partes[1], partes[2], Integer.parseInt(partes[3]),
                                Integer.parseInt(partes[4]), Float.parseFloat(partes[5].replace(',', '.')));
                        i++;
                    }
                    break;
            }
        }

        // Se cierra el Scanner
        input.close();
    }

    // Método que busca un coche a partir de una matrícula
    public Car getCarFromPlate(String plate) {

        // Se recorre el array de coches hasta encontrar la matrícula correspondiente
        for (Car car : cityCars) {
            if (car.getPlate().equals(plate)) {
                return car;
            }
        }
        // Si se llegó al final del array sin encontrar el coche, se avisa de ello y se
        // devuelve null
        System.out.println("Coche no encontrado");
        return null;
    }

    // Método que calcula la suma total de potencia
    public int computeTotalPower() {

        // Contador de la potencia total
        int potencia = 0;

        // Se recorre el array de coches y se suman sus respectivas potencias
        for (Car car : cityCars) {
            // Si no hay coche, se llegó al final del array y se sale del bucle
            if (car == null) {
                break;
            }
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

        // Se recorre el array de coches, y se suman las baterías de los eléctricos e
        // híbridos
        for (Car car : cityCars) {
            // Si no hay coche, se llegó al final del array y se sale del bucle
            if (car == null) {
                break;
            } else if (car instanceof ElectricCar) {
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
}