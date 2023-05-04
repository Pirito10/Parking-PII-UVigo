package P3;

public class HybridCar {

    // Atributos de instancia
    private String plate;
    private String manufacturer;
    private int mechanicalPower;
    private int electricPower;
    private float batteryCharge;

    // Atributos estáticos
    public static final String PLATE_FORMAT = "DDDDLLL";
    private static int minMechanicalPower = 60;
    private static int maxMechanicalPower = 500;
    private static int minElectricPower = 20;
    private static int maxElectricPower = 100;
    private static final float minCharge = 0;
    private static final float maxCharge = 100;

    // Constructor vacío
    public HybridCar() {
    }

    // Constructor con argumentos
    public HybridCar(String plate, String manufacturer, int mechanicalPower, int electricPower, float batteryCharge) {
        this.plate = plate;
        this.manufacturer = manufacturer;
        this.mechanicalPower = mechanicalPower;
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

    public static boolean isValidMechanicalPower(int mechanicalPower) {
        if (mechanicalPower >= minMechanicalPower && mechanicalPower <= maxMechanicalPower) {
            return true;
        } else {
            System.out.println("Incorrect power value: " + mechanicalPower + ". Valid range is " + minMechanicalPower
                    + "-" + maxMechanicalPower);
            return false;
        }
    }

    public static boolean isValidElectricPower(int electricPower) {
        if (electricPower >= minElectricPower && electricPower <= maxElectricPower) {
            return true;
        } else {
            System.out.println("Incorrect power value: " + electricPower + ". Valid range is " + minElectricPower + "-"
                    + maxElectricPower);
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

    public int getMechanicalPower() {
        return this.mechanicalPower;
    }

    public void setMechanicalPower(int mechanicalPower) {
        this.mechanicalPower = mechanicalPower;
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

    public void setMinMechanicalPower(int minMechanicalPower) {
        HybridCar.minMechanicalPower = minMechanicalPower;
    }

    public void setMaxMechanicalPower(int maxMechanicalPower) {
        HybridCar.maxMechanicalPower = maxMechanicalPower;
    }

    public void setMinElectricPower(int minElectricPower) {
        HybridCar.minElectricPower = minElectricPower;
    }

    public void setMaxElectricPower(int maxElectricPower) {
        HybridCar.maxElectricPower = maxElectricPower;
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
        return "E;" + plate + ";" + manufacturer + ";" + mechanicalPower + ";" + electricPower + ";" + batteryCharge;
    }

}
