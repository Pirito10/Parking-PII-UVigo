package P3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class P3a {

    // Arrays para cada tipo de coche
    static ElectricCar[] electricCityCars = new ElectricCar[25];
    static CombustionCar[] combustionCityCars = new CombustionCar[25];
    static HybridCar[] hybridCityCars = new HybridCar[25];

    public static void main(String[] args) throws FileNotFoundException {

        // Atributos coche 3
        String plate3 = "6288LYM", manufacturer3 = "Ford";
        int power3 = 110;
        float batteryCharge3 = 120.5F;

        // Atributos coche 4
        String plate4 = "07451JMR", manufacturer4 = "Seat";
        int power4 = 60;
        float batteryCharge4 = 10.1F;

        // Atributos coche 5
        String plate5 = "3400JXK", manufacturer5 = "Peugeot";
        int power5 = 70;
        float batteryCharge5 = 50F;

        // Atributos coche 6
        String plate6 = "0041LDR", manufacturer6 = "Seat";
        int power6 = 20;
        float batteryCharge6 = 10.1F;

        // Coche 1
        ElectricCar coche1 = new ElectricCar("1111KLS", "SEAT", 220, 30.5F);

        // Coche 2
        ElectricCar coche2 = new ElectricCar();
        coche2.setPlate("2222LSX");
        coche2.setManufacturer("FORD");
        coche2.setElectricPower(220);
        coche2.setBatteryCharge(30.5F);

        // Se imprimen por pantalla los coches uno y dos
        System.out.println(coche1.toText());
        System.out.println(coche2.toText());

        // Se crean los coches solo si los atributos son válidos. En caso de que lo
        // sean, también se imprimen por pantalla
        // Coche 3
        if (ElectricCar.isValidPlate(plate3) && ElectricCar.isValidPower(power3)
                && ElectricCar.isValidBattery(batteryCharge3)) {
            ElectricCar coche3 = new ElectricCar(plate3, manufacturer3, power3, batteryCharge3);
            System.out.println(coche3.toText());
        }

        // Coche 4
        if (ElectricCar.isValidPlate(plate4) && ElectricCar.isValidPower(power4)
                && ElectricCar.isValidBattery(batteryCharge4)) {
            ElectricCar coche4 = new ElectricCar(plate4, manufacturer4, power4, batteryCharge4);
            System.out.println(coche4.toText());
        }

        // Coche 5
        if (ElectricCar.isValidPlate(plate5) && ElectricCar.isValidPower(power5)
                && ElectricCar.isValidBattery(batteryCharge5)) {
            ElectricCar coche5 = new ElectricCar(plate5, manufacturer5, power5, batteryCharge5);
            System.out.println(coche5.toText());
        }

        // Coche 6
        if (ElectricCar.isValidPlate(plate6) && ElectricCar.isValidPower(power6)
                && ElectricCar.isValidBattery(batteryCharge6)) {
            ElectricCar coche6 = new ElectricCar(plate6, manufacturer6, power6, batteryCharge6);
            System.out.println(coche6.toText());
        }

        // Se leen los coches del fichero indicado
        readCityCarsFile("cityCars.txt");

        // Se hace el cálculo de la potencia total
        System.out.println("\nSuma de potencia = " + computeTotalPower());

        // Se hace un incremento del 10% de batería a todos los coches eléctricos
        for (ElectricCar car : electricCityCars) {
            // Si no hay coche, se llegó al final del array y se sale del bucle
            if (car == null) {
                break;
            }
            car.increaseBatteryChargeLevel(10);
        }

        // Se guardan todos los coches eléctricos en el fichero indicado
        writeElectricCityCarsFile("ElectricCarsOutput.txt");

    }

    // Método que lee los coches del fichero indicado y los almacena en el array
    // correspondiente, siempre y cuando sus datos sean correctos
    public static void readCityCarsFile(String filename) throws FileNotFoundException {

        // Se crea un objeto File con el nombre indicado en la ejecución del programa
        File archivo = new File(filename);

        // Se comprueba si el fichero existe
        if (!archivo.exists()) {
            System.out.println("No existe tal fichero");
            return; // Se termina el programa en caso de no haber fichero
        }

        // Se crea un objeto Scanner para leer el archivo
        Scanner input = new Scanner(archivo);

        // Contadores para cada tipo de coche
        int c = 0, e = 0, h = 0;

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
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea
                    // almacena el coche en el array correspondiente y se incrementa el contador
                    if (CombustionCar.isValidPlate(partes[1])
                            && CombustionCar.isValidPower(Integer.parseInt(partes[3]))) {
                        combustionCityCars[c] = new CombustionCar(partes[1], partes[2], Integer.parseInt(partes[3]));
                        c++;
                    }
                    break;

                // Para motores eléctricos
                case "E":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea
                    // almacena el coche en el array correspondiente y se incrementa el contador
                    if (ElectricCar.isValidPlate(partes[1]) && ElectricCar.isValidPower(Integer.parseInt(partes[3]))
                            && ElectricCar.isValidBattery(Float.parseFloat(partes[4].replace(',', '.')))) {
                        electricCityCars[e] = new ElectricCar(partes[1], partes[2], Integer.parseInt(partes[3]),
                                Float.parseFloat(partes[4].replace(',', '.')));
                        e++;
                    }
                    break;

                // Para motores híbridos
                case "H":
                    // Se comprueba si los datos son correctos, y en caso afirmativo, se crea
                    // almacena el coche en el array correspondiente y se incrementa el contador
                    if (HybridCar.isValidPlate(partes[1])
                            && HybridCar.isValidMechanicalPower(Integer.parseInt(partes[3]))
                            && HybridCar.isValidElectricPower(Integer.parseInt(partes[4]))
                            && HybridCar.isValidBattery(Float.parseFloat(partes[5].replace(',', '.')))) {
                        hybridCityCars[h] = new HybridCar(partes[1], partes[2], Integer.parseInt(partes[3]),
                                Integer.parseInt(partes[4]), Float.parseFloat(partes[5].replace(',', '.')));
                        h++;
                    }
                    break;

            }
        }

        // Se cierra el Scanner
        input.close();
    }

    // Método que calcula la suma total de potencia
    public static int computeTotalPower() {

        // Contador de la potencia total
        int potencia = 0;

        // Se recorre el array de coches de combustión y se suman sus respectivas
        // potencias
        for (CombustionCar car : combustionCityCars) {
            // Si no hay coche, se llegó al final del array y se sale del bucle
            if (car == null) {
                break;
            }
            potencia = potencia + car.getMechanicalPower();
        }

        // Se recorre el array de coches eléctricos y se suman sus respectivas potencias
        for (ElectricCar car : electricCityCars) {
            // Si no hay coche, se llegó al final del array y se sale del bucle
            if (car == null) {
                break;
            }
            potencia = potencia + car.getElectricPower();
        }

        // Se recorre el array de coches híbridos y se suman sus respectivas potencias
        // (tanto mecánica como eléctrica)
        for (HybridCar car : hybridCityCars) {
            // Si no hay coche, se llegó al final del array y se sale del bucle
            if (car == null) {
                break;
            }
            potencia = potencia + car.getMechanicalPower() + car.getElectricPower();
        }

        return potencia;
    }

    // Método que imprime en un fichero todos los coches eléctricos almacenados en
    // el correspondiente array
    public static void writeElectricCityCarsFile(String filename) throws FileNotFoundException {

        // Se crea un objeto File con el nombre indicado
        File archivo = new File(filename);

        // Se crea el archivo
        PrintWriter fichero = new PrintWriter(archivo);

        // Se recorre el array de coches eléctricos
        for (ElectricCar car : electricCityCars) {
            // Si no hay coche, se llegó al final del array y se sale del bucle
            if (car == null) {
                break;
            }
            // Se imprime en el fichero la información del coche
            fichero.println("E;" + car.getPlate() + ";" + car.getManufacturer() + ";" + car.getElectricPower() + ";"
                    + Float.toString(car.getBatteryCharge()).replace('.', ','));
        }

        // Se cierra el PrintWriter
        fichero.close();

    }
}