package P4;

public class Coordinate {

    // Atributos de instancia
    private char zone;
    private int number;

    // Constructor vacío
    public Coordinate() {
    }

    // Constructor con argumentos
    public Coordinate(char zone, int number) {
        this.zone = zone;
        this.number = number;
    }

    // Métodos estáticos o de clase

    public static boolean isValidNumber(int number) {
        return number > 0;
    }

    // Métodos de instancia

    public boolean equals(Coordinate c) {
        return this.number == c.number && this.zone == c.zone;
    }

    public char getZone() {
        return this.zone;
    }

    public void setZone(char zone) {
        this.zone = zone;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String toText() {
        return zone + String.valueOf(number);
    }

}