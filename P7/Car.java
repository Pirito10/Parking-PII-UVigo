package P7;

import java.io.Serializable;

public abstract class Car implements Comparable<Car>, Serializable {

    // Al intentar leer objetos Car del fichero binario con los coches de la batería
    // de pruebas (bt_d3b01_file4.dat), se producía un error dado que los objetos
    // del fichero tenían diferente número serial que los objetos creados durante la
    // ejecución del programa
    // Por tanto, asignando manualmente este número serial, coinciden los objetos y
    // por tanto se pueden leer sin errores
    private static final long serialVersionUID = 7999008895737219541L;

    // Atributos de instancia
    private String plate;
    private String manufacturer;

    // Atributos estáticos
    public static final String PLATE_FORMAT = "DDDDLLL";

    // Constructor vacío
    public Car() {
    }

    // Constructor con argumentos
    public Car(String plate, String manufacturer) {
        this.plate = plate;
        this.manufacturer = manufacturer;
    }

    // Métodos estáticos o de clase

    public static boolean isValidPlate(String plate) {

        // Si la longitud es diferente a siete caracteres, la matrícula errónea
        if (plate.length() != 7) {
            System.out.println("Incorrect plate value: " + plate + ". Not comply with format " + PLATE_FORMAT);
            return false;
        }

        // Se recorren todos los caracteres de la matrícula
        for (int i = 0; i < plate.length(); i++) {

            // Si uno de los tres primeros no se corresponde con un número, se considera
            // erróneo
            if (i < 3) {
                if (!Character.isDigit(plate.charAt(i))) {
                    System.out.println("Incorrect plate value: " + plate + ". Not comply with format " + PLATE_FORMAT);
                    return false;
                }
            }

            // Si uno de los cuatro últimos no es una letra mayúscula, se considera erróneo
            if (i >= 4) {

                if (!Character.isLetter(plate.charAt(i)) || !Character.isUpperCase(plate.charAt(i))) {
                    System.out.println("Incorrect plate value: " + plate + ". Not comply with format " + PLATE_FORMAT);
                    return false;
                }
            }
        }
        return true;
    }

    // Métodos de instancia

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public abstract int getTotalPower();

    public String toString() {
        return ";" + getPlate() + ";" + getManufacturer() + ";";
    }

    public int compareTo(Car car) {
        return this.plate.compareTo(car.plate);
    }
}
