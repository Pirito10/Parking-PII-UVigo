public class Visit implements Comparable<Visit> {

    // Atributos de instancia
    private String plate;
    private String entryTime;

    // Constructor vacío
    public Visit() {
    }

    // Constructor con argumentos
    public Visit(String plate, String entryTime) {
        this.plate = plate;
        this.entryTime = entryTime;
    }

    // Métodos de instancia

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

    public int compareTo(Visit visit) {
        // En caso de misma hora de entrada, se comparan sus coordenadas
        if (this.entryTime.compareTo(visit.getEntryTime()) == 0) {
            return this.plate.compareTo(visit.getPlate());
        }

        return this.entryTime.compareTo(visit.getEntryTime());
    }
}