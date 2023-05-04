package P3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Parking {

    // Atributos de instancia
    private char maxZone;
    private int sizeZone;
    private char lowerElectricZone;
    private CarSpace[][] carSpaces;

    // Constructor vacío
    public Parking() {
    }

    // Constructor con argumentos
    public Parking(char maxZone, int sizeZone, char lowerElectricZone) {
        this.maxZone = maxZone;
        this.sizeZone = sizeZone;
        this.lowerElectricZone = lowerElectricZone;
        this.carSpaces = new CarSpace[maxZone - 64][sizeZone];
    }

    // Constructor a partir de fichero
    public Parking(String filename) throws FileNotFoundException {

        // Se crea un objeto File con el nombre indicado
        File archivo = new File(filename);

        // Se comprueba si el fichero existe
        if (!archivo.exists()) {
            System.out.println("No existe tal archivo");
            return; // Se termina el programa en caso de no haber fichero
        }

        // Se crea un objeto Scanner para leer el archivo
        Scanner input = new Scanner(archivo);

        // Se lee línea por línea
        while (input.hasNext()) {

            String linea = input.nextLine();

            // Si la línea empieza por '#', se considera un comentario y se ignora
            if (linea.startsWith("#")) {
                continue;
            }

            // Se separa la línea por donde había un ';'
            String[] partes = linea.split(";");

            // Al llegar a la línea con los datos de las dimensiones, se inicializan los
            // atributos con la respectiva información
            this.maxZone = partes[0].charAt(0);
            this.sizeZone = Integer.parseInt(partes[1]);
            this.lowerElectricZone = partes[2].charAt(0);
            this.carSpaces = new CarSpace[maxZone - 64][sizeZone];

            // Se recorre el array carSpaces, inicializando cada plaza con su coordenada y
            // la matrícula (null si no hay ningún coche)
            for (int i = 0; i < carSpaces.length; i++) {
                for (int j = 0; j < sizeZone; j++) {
                    // El carácter de la coordenada se obtiene sumando 65
                    carSpaces[i][j] = new CarSpace(new Coordinate((char) (i + 65), j + 1), null);
                }
            }

            // Se sale del bucle
            break;

        }

        // Se sigue leyendo línea por línea
        while (input.hasNext()) {

            String linea = input.nextLine();

            // Si la línea empieza por '#', se considera un comentario y se ignora
            if (linea.startsWith("#")) {
                continue;
            }

            // Se separa la línea por donde había un ';'
            String[] partes = linea.split(";");

            // Se obtiene la coordenada correspondiente
            Coordinate coordinate = new Coordinate(partes[0].charAt(0), Integer.parseInt(partes[0].substring(1)));

            // Se busca esa coordenada en el array carSpaces, y se le asigna el
            // correspondiente valor de matrícula
            for (CarSpace[] carSpace : carSpaces) {
                for (int i = 0; i < sizeZone; i++) {
                    if (carSpace[i].getCoordinate().isEqualTo(coordinate)) {
                        carSpace[i].setPlate(partes[1]);
                    }
                }
            }
        }

        // Se cierra el Scanner
        input.close();
    }

    // Método que almacena en el fichero indicado el estado del parking
    public void saveParking(String filename) throws FileNotFoundException {

        // Se crea un objeto File con el nombre indicado
        File archivo = new File(filename);

        // Se crea el archivo
        PrintWriter fichero = new PrintWriter(archivo);

        // Se imprime la línea con la información sobre las dimensiones del parking
        fichero.println(maxZone + ";" + sizeZone + ";" + lowerElectricZone);

        // Se recorre el array carSpaces
        for (CarSpace[] carSpace : carSpaces) {
            for (int i = 0; i < sizeZone; i++) {
                // Si una plaza no está ocupada, se imprime su información (plaza + matrícula)
                if (carSpace[i].getPlate() != null) {
                    fichero.println(carSpace[i].toText());
                }
            }
        }

        // Se cierra el PrintWriter
        fichero.close();
    }

    // Método que almacena un coche en el parking
    public void carEntry(String plate, char carType) {

        // Si el coche es de combustión, se aparcará en la primera plaza disponible,
        // siempre fuera de la zona eléctrica
        if (carType == 'C') {

            // Se recorre el array carSpaces hasta encontrar una plaza vacía
            for (int i = 0; i < lowerElectricZone; i++) {
                for (int j = 0; j < sizeZone; j++) {
                    // Se establece la matrícula si esta es null, es decir, si no había coche en esa
                    // plaza
                    if (carSpaces[i][j].getPlate() == null) {
                        carSpaces[i][j].setPlate(plate);
                        return;
                    }
                }
            }

            // Si es eléctrico o híbrido, se aparcará en la primera plaza disponible a
            // partir de la zona eléctrica
        } else {

            // Se recorre el array carSpaces hasta encontrar una plaza vacía
            for (int i = lowerElectricZone - 65; i < maxZone - 64; i++) {
                for (int j = 0; j < sizeZone; j++) {
                    // Se establece la matrícula si esta es null, es decir, si no había coche en esa
                    // plaza
                    if (carSpaces[i][j].getPlate() == null) {
                        carSpaces[i][j].setPlate(plate);
                        return;
                    }
                }
            }
        }
    }

    // Método que elimina un coche del parking
    public void carDeparture(String plate) {

        // Se recorre el array carSpaces buscando la matrícula indicada
        for (CarSpace[] carSpace : carSpaces) {
            for (int i = 0; i < sizeZone; i++) {
                // Cuando se encuentre, se elimina, poniendo su valor a null
                if (carSpace[i].getPlate() == null) {
                    continue;
                }
                if (carSpace[i].getPlate().equals(plate)) {
                    carSpace[i].setPlate(null);
                    return;
                }
            }
        }

    }

    // Método que devuelve un dibujo del parking
    public String toMap() {

        // Variable para almacenar el mapa
        String mapa = "";

        // Se recorre el array carSpaces
        for (int i = 0; i < carSpaces.length; i++) {
            for (int j = 0; j < sizeZone; j++) {

                // Si estamos en la zona eléctrica, se indicará en la plaza
                if (i + 65 >= lowerElectricZone) {
                    // Si no hay coche aparcado en la plaza, se dejan espacios en blanco
                    if (carSpaces[i][j].getPlate() == null) {
                        mapa = mapa.concat(carSpaces[i][j].getCoordinate().toText() + " E        |");
                        // Si hay coche, se imprime su matrícula
                    } else {
                        mapa = mapa.concat(
                                carSpaces[i][j].getCoordinate().toText() + " E " + carSpaces[i][j].getPlate() + "|");
                    }

                    // Si no estamos en la zona eléctrica, no se indica nada
                } else {
                    // Si no hay coche aparcado en la plaza, se dejan espacios en blanco
                    if (carSpaces[i][j].getPlate() == null) {
                        mapa = mapa.concat(carSpaces[i][j].getCoordinate().toText() + "          |");
                        // Si hay coche, se imprime su matrícula
                    } else {
                        mapa = mapa.concat(
                                carSpaces[i][j].getCoordinate().toText() + "   " + carSpaces[i][j].getPlate() + "|");
                    }
                }
            }
            // Se añade un salto de línea después de cada zona
            mapa = mapa.concat("\n");
        }

        return mapa;
    }

    // Métodos de instancia

    public char getMaxZone() {
        return this.maxZone;
    }

    public void setMaxZone(char maxZone) {
        this.maxZone = maxZone;
    }

    public int getSizeZone() {
        return this.sizeZone;
    }

    public void setSizeZone(int sizeZone) {
        this.sizeZone = sizeZone;
    }

    public char getLowerElectricZone() {
        return this.lowerElectricZone;
    }

    public void setLowerElectricZone(char lowerElectricZone) {
        this.lowerElectricZone = lowerElectricZone;
    }

    public CarSpace[][] getCarSpaces() {
        return this.carSpaces;
    }

    public void setCarSpaces(CarSpace[][] carSpaces) {
        this.carSpaces = carSpaces;
    }

}