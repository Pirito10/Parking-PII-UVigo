package P3;

public class ElectricCar {

    // Atributos de instancia
    private String plate;
    private String manufacturer;
    private int electricPower;
    private float batteryCharge;

    // Atributos estáticos
    public static final String PLATE_FORMAT = "DDDDLLL";
    private static int minPower = 50;
    private static int maxPower = 800;
    private static final float minCharge = 0;
    private static final float maxCharge = 100;

    // Constructor vacío
    public ElectricCar() {
    }

    // Constructor con argumentos
    public ElectricCar(String plate, String manufacturer, int electricPower, float batteryCharge) {
        this.plate = plate;
        this.manufacturer = manufacturer;
        this.electricPower = electricPower;
        this.batteryCharge = batteryCharge;
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

    public static boolean isValidPower(int electricPower) {
        if (electricPower >= minPower && electricPower <= maxPower) {
            return true;
        } else {
            System.out.println(
                    "Incorrect power value: " + electricPower + ". Valid range is " + minPower + "-" + maxPower);
            return false;
        }
    }

    public static boolean isValidBattery(float batteryCharge) {
        if (batteryCharge >= minCharge && batteryCharge <= maxCharge) {
            return true;
        } else {
            System.out.println("Incorrect battery charge value: " + batteryCharge + ". Valid range is " + minCharge
                    + "-" + maxCharge);
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

    public int getElectricPower() {
        return this.electricPower;
    }

    public void setElectricPower(int electricPower) {
        this.electricPower = electricPower;
    }

    public float getBatteryCharge() {
        return this.batteryCharge;
    }

    public void setBatteryCharge(float batteryCharge) {
        this.batteryCharge = batteryCharge;
    }

    public void setMinPower(int minPower) {
        ElectricCar.minPower = minPower;
    }

    public void setMaxPower(int maxPower) {
        ElectricCar.maxPower = maxPower;
    }

    public void increaseBatteryChargeLevel(float increment) {

        // Se calcula el nuevo valor de la carga
        float newCharge = this.batteryCharge * (1 + increment / 100);

        // Se comprueba que no sea mayor a cien
        if (newCharge > 100) {
            this.batteryCharge = 100;
        } else {
            this.batteryCharge = newCharge;
        }
    }

    public String toText() {
        return "E;" + plate + ";" + manufacturer + ";" + electricPower + ";" + batteryCharge;
    }
}