public class CarSpace implements Comparable<CarSpace> {

    // Atributos de instancia
    private Coordinate coordinate;
    private String plate;
    private String entryTime;

    // Constructor vacío
    public CarSpace() {
    }

    // Constructor con argumentos
    public CarSpace(Coordinate coordinate, String plate, String entryTime) {
        this.coordinate = coordinate;
        this.plate = plate;
        this.entryTime = entryTime;
    }

    // Métodos de instancia

    public boolean equals(CarSpace carSpace) {
        return this.coordinate.equals(carSpace.getCoordinate());
    }

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

    public String getEntryTime() {
        return this.entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String toText() {
        if (plate == null) {
            return coordinate.toText();
        }
        return coordinate.toText() + ";" + plate + ";" + entryTime;
    }

    public int compareTo(CarSpace carSpace) {
        return this.coordinate.compareTo(carSpace.getCoordinate());
    }
}