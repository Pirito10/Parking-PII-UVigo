public class ElectricCar extends Car {

    // Atributos de instancia
    private int electricPower;
    private float batteryCharge;

    // Atributos estáticos
    private static int minPower = 50;
    private static int maxPower = 800;
    private static final float minCharge = 0;
    private static final float maxCharge = 100;
    public static final int BATTERY_CAPACITY = 100;

    // Constructor vacío
    public ElectricCar() {
    }

    // Constructor con argumentos
    public ElectricCar(String plate, String manufacturer, int electricPower, float batteryCharge) {
        super(plate, manufacturer);
        this.electricPower = electricPower;
        this.batteryCharge = batteryCharge;
    }

    // Métodos estáticos o de clase

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

    public int getTotalPower() {
        return this.electricPower;
    }

    public void increaseBatteryChargeLevel(float chargeTime) {

        // Se calcula el nuevo valor de la carga
        float newLevel = this.batteryCharge + (chargeTime * ElectricCharger.POWER / BATTERY_CAPACITY) * 100;

        // Se comprueba que no sea mayor a cien
        if (newLevel > 100) {
            this.batteryCharge = 100;
        } else {
            this.batteryCharge = newLevel;
        }
    }

    public String toString() {
        return "E" + super.toString() + electricPower + ";" + Float.toString(batteryCharge).replace('.', ',');
    }
}