package P4;

public class HybridCar extends Car {

    // Atributos de instancia
    private int mechanicalPower;
    private int electricPower;
    private float batteryCharge;

    // Atributos estáticos
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
        super(plate, manufacturer);
        this.mechanicalPower = mechanicalPower;
        this.electricPower = electricPower;
        this.batteryCharge = batteryCharge;
    }

    // Métodos estáticos o de clase

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

    public int getTotalPower() {
        return this.mechanicalPower + this.electricPower;
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

    public String toString() {
        return "H" + super.toString() + mechanicalPower + ";" + electricPower + ";" + batteryCharge;
    }

}
