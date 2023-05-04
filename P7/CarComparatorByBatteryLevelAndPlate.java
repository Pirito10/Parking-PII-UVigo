package P7;

import java.util.Comparator;

public class CarComparatorByBatteryLevelAndPlate implements Comparator<Car> {

    // Método que compara dos coches por su batería
    public int compare(Car car1, Car car2) {

        // Variables para almacenar el nivel de batería de cada coche
        float batteryCar1, batteryCar2;

        // Se obtiene el nivel de batería del primer coche
        if (car1 instanceof CombustionCar) {
            batteryCar1 = 0;
        } else if (car1 instanceof ElectricCar) {
            batteryCar1 = ((ElectricCar) car1).getBatteryCharge();
        } else {
            batteryCar1 = ((HybridCar) car1).getBatteryCharge();
        }

        // Se obtiene el nivel de batería del segundo coche
        if (car2 instanceof CombustionCar) {
            batteryCar2 = 0;
        } else if (car2 instanceof ElectricCar) {
            batteryCar2 = ((ElectricCar) car2).getBatteryCharge();
        } else {
            batteryCar2 = ((HybridCar) car2).getBatteryCharge();
        }

        // Se hace la comparación
        if (batteryCar1 > batteryCar2) {
            return 1;
        } else if (batteryCar1 < batteryCar2) {
            return -1;
        } else {
            // Si tienen el mismo nivel de batería, se comparan sus matrículas
            return car1.compareTo(car2);
        }
    }
}
