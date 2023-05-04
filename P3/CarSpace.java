package P3;

public class CarSpace {

    // Atributos de instancia
    private Coordinate coordinate;
    private String plate = null;

    // Constructor vacío
    public CarSpace() {
    }

    // Constructor con argumentos
    public CarSpace(Coordinate coordinate, String plate) {
        this.coordinate = coordinate;
        this.plate = plate;
    }

    // Métodos de instancia

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String toText() {
        if (plate == null) {
            return coordinate.toText();
        }
        return coordinate.toText() + ";" + plate;
    }
}