package P5a;

public class CombustionCar extends Car {

    // Atributos de instancia
    private int mechanicalPower;

    // Atributos estáticos
    private static int minPower = 60;
    private static int maxPower = 500;

    // Constructor vacío
    public CombustionCar() {
    }

    // Constructor con argumentos
    public CombustionCar(String plate, String manufacturer, int mechanicalPower) {
        super(plate, manufacturer);
        this.mechanicalPower = mechanicalPower;
    }

    // Métodos estáticos o de clase

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

    public int getTotalPower() {
        return this.mechanicalPower;
    }

    public String toString() {
        return "C" + super.toString() + mechanicalPower;
    }

}
