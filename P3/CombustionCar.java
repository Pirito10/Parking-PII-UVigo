package P3;

public class CombustionCar {

    // Atributos de instancia
    private String plate;
    private String manufacturer;
    private int mechanicalPower;

    // Atributos estáticos
    public static final String PLATE_FORMAT = "DDDDLLL";
    private static int minPower = 60;
    private static int maxPower = 500;

    // Constructor vacío
    public CombustionCar() {
    }

    // Constructor con argumentos
    public CombustionCar(String plate, String manufacturer, int mechanicalPower) {
        this.plate = plate;
        this.manufacturer = manufacturer;
        this.mechanicalPower = mechanicalPower;
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

    public static boolean isValidPower(int mechanicalPower) {
        if (mechanicalPower >= minPower && mechanicalPower <= maxPower) {
            return true;
        } else {
            System.out.println(
                    "Incorrect power value: " + mechanicalPower + ". Valid range is " + minPower + "-" + maxPower);
            return false;
        }
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

    public int getMechanicalPower() {
        return this.mechanicalPower;
    }

    public void setMechanicalPower(int mechanicalPower) {
        this.mechanicalPower = mechanicalPower;
    }

    public void setMinPower(int minPower) {
        CombustionCar.minPower = minPower;
    }

    public void setMaxPower(int maxPower) {
        CombustionCar.maxPower = maxPower;
    }

    public String toText() {
        return "E;" + plate + ";" + manufacturer + ";" + mechanicalPower;
    }

}
