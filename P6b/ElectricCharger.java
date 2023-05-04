package P6b;

public class ElectricCharger {

    // Atributos de instancia
    private boolean connected;
    public final static int POWER = 25;

    // Constructor vacío
    public ElectricCharger() {
        connected = false;
    }

    // Métodos de instancia

    public void connect() {
        this.connected = true;
    }

    public void disconnect() {
        this.connected = false;
    }
}