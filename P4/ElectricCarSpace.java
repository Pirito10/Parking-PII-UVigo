package P4;

public class ElectricCarSpace extends CarSpace {

    // Atributos de instancia
    private final ElectricCharger charger;

    // Constructor con argumentos
    public ElectricCarSpace(Coordinate coordinate, String plate, ElectricCharger charger) {
        super(coordinate, plate);
        this.charger = charger;
    }

    // Métodos de instancia

    public ElectricCharger getCharger() {
        return this.charger;
    }
}