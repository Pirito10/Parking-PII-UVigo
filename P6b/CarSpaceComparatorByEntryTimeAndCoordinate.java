package P6b;

import java.util.Comparator;

public class CarSpaceComparatorByEntryTimeAndCoordinate implements Comparator<CarSpace> {

    // Método que compara dos plazas por la hora de entrada de su coche aparcado
    public int compare(CarSpace carSpace1, CarSpace carSpace2) {

        // En caso de misma hora de entrada, se comparan sus coordenadas
        if (carSpace1.getEntryTime().compareTo(carSpace2.getEntryTime()) == 0) {
            return carSpace1.getCoordinate().compareTo(carSpace2.getCoordinate());
        }

        return carSpace1.getEntryTime().compareTo(carSpace2.getEntryTime());
    }
}
