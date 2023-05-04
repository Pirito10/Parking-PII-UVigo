package P5a;

public class ElectricCarSpace extends CarSpace {

    // Atributos de instancia
    private final ElectricCharger charger;

    // Constructor con argumentos
    public ElectricCarSpace(Coordinate coordinate, String plate, String entryTime, ElectricCharger charger) {
        super(coordinate, plate, entryTime);
        this.charger = charger;
    }

    // Métodos de instancia

    public ElectricCharger getCharger() {
        return this.charger;
    }
}