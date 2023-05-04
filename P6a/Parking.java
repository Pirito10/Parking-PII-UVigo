package P6a;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Parking {

    // Atributos de instancia
    private char maxZone;
    private int sizeZone;
    private char lowerElectricZone;
    // Set desordenado de plazas ocupadas
    private HashSet<CarSpace> busyCarSpaces;
    // Set ordenado de plazas vacías
    private TreeSet<CarSpace> freeCarSpaces;

    // Constructor vacío
    public Parking() {
    }

    // Constructor con argumentos
    public Parking(char maxZone, int sizeZone, char lowerElectricZone) {
        this.maxZone = maxZone;
        this.sizeZone = sizeZone;
        this.lowerElectricZone = lowerElectricZone;
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
            maxZone = partes[0].charAt(0);
            sizeZone = Integer.parseInt(partes[1]);
            lowerElectricZone = partes[2].charAt(0);
            busyCarSpaces = new HashSet<>();
            freeCarSpaces = new TreeSet<>();

            // Se crean y añaden todas las plazas vacías (matrícula y hora null) al set de
            // plazas vacías
            for (int i = 0; i < maxZone - 64; i++) {
                for (int j = 0; j < sizeZone; j++) {
                    // Si estamos en plazas para coches eléctricos, se crea una plaza con cargador
                    if (i >= lowerElectricZone - 65) {
                        freeCarSpaces.add(new ElectricCarSpace(new Coordinate((char) (i + 65), j + 1), null, null,
                                new ElectricCharger()));
                    } else {
                        freeCarSpaces.add(new CarSpace(new Coordinate((char) (i + 65), j + 1), null, null));
                    }
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

            // Se crea una plaza normal y se busca en el set de plazas vacías
            CarSpace carSpace = freeCarSpaces.ceiling(new CarSpace(coordinate, null, null));

            // Se le asigna el correspondiente valor de matrícula y hora
            if (carSpace != null) {
                carSpace.setPlate(partes[1]);
                carSpace.setEntryTime(partes[2]);
            }

            // Si la plaza es eléctrica, se conecta el cargador
            if (carSpace instanceof ElectricCarSpace) {
                ((ElectricCarSpace) carSpace).getCharger().connect();
            }

            // Se mueve la plaza al set de plazas ocupadas
            busyCarSpaces.add(carSpace);
            freeCarSpaces.remove(carSpace);

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

        // Se crea un set ordenado por hora de entrada a partir del set de plazas
        // ocupadas
        TreeSet<CarSpace> busyCarSpacesSorted = new TreeSet<>(new CarSpaceComparatorByEntryTimeAndCoordinate());
        busyCarSpacesSorted.addAll(busyCarSpaces);

        // Se recorre el set ordenado
        for (CarSpace carSpace : busyCarSpacesSorted) {
            // Se imprime la información (plaza + matrícula + hora)
            fichero.println(carSpace.toText());
        }

        // Se cierra el PrintWriter
        fichero.close();
    }

    // Método que almacena un coche en el parking
    public void carEntry(Car car, String time) {

        // Variable para almacenar la plaza buscada
        CarSpace carSpace;

        // Si el coche es de combustión, se aparcará en la primera plaza disponible
        if (car instanceof CombustionCar) {

            // Se busca la primera plaza vacía
            carSpace = freeCarSpaces.first();

            // Se le asigna el correspondiente valor de matrícula y hora a la plaza
            carSpace.setPlate(car.getPlate());
            carSpace.setEntryTime(time);

            // Si es eléctrico o híbrido, se aparcará en la primera plaza disponible a
            // partir de la zona eléctrica
        } else {

            // Se busca la primera plaza vacía en la zona eléctrica
            carSpace = freeCarSpaces.ceiling(new CarSpace(new Coordinate(lowerElectricZone, 1), null, null));

            // Se le asigna el correspondiente valor de matrícula y hora a la plaza, y se
            // conecta el cargador
            if (carSpace != null) {
                carSpace.setPlate(car.getPlate());
                carSpace.setEntryTime(time);
                ((ElectricCarSpace) carSpace).getCharger().connect();
            }
        }

        // Se mueve la plaza al set de plazas ocupadas
        busyCarSpaces.add(carSpace);
        freeCarSpaces.remove(carSpace);
    }

    // Método que elimina un coche del parking
    public String carDeparture(String plate) {

        // Se recorre el set de plazas ocupadas buscando la matrícula indicada
        for (CarSpace carSpace : busyCarSpaces) {

            // Cuando se encuentre, se elimina, poniendo matrícula y hora a null
            if (carSpace.getPlate().equals(plate)) {
                carSpace.setPlate(null);
                String time = carSpace.getEntryTime();
                carSpace.setEntryTime(null);
                // Si la plaza era de coches eléctricos, se desconecta el cargador
                if (carSpace instanceof ElectricCarSpace) {
                    ((ElectricCarSpace) carSpace).getCharger().disconnect();
                }

                // Se mueve la plaza al set de plazas vacías
                freeCarSpaces.add(carSpace);
                busyCarSpaces.remove(carSpace);

                // Se devuelve la hora de entrada
                return time;
            }
        }
        return null;
    }

    // Método que devuelve un dibujo del parking
    public String toMap() {

        // Variable para almacenar el mapa
        String mapa = "";

        // Variable para almacenar la letra de las plazas
        char zona = 'A';

        // Se crea un set ordenado a partir del set de plazas ocupadas, y se le añaden
        // las plazas vacías
        TreeSet<CarSpace> carSpaces = new TreeSet<>(busyCarSpaces);
        carSpaces.addAll(freeCarSpaces);

        // Se recorre el set por completo
        for (CarSpace carSpace : carSpaces) {

            // Si la letra (zona) de la anterior plaza es diferente a la actual, se
            // introduce un salto de línea
            if (zona != carSpace.getCoordinate().getZone()) {
                mapa = mapa.concat("\n");
            }

            // Se actualiza la variable que almacena la zona actual
            zona = carSpace.getCoordinate().getZone();

            // Si estamos en la zona eléctrica, se indicará en la plaza
            if (carSpace instanceof ElectricCarSpace) {
                // Si no hay coche aparcado en la plaza, se dejan espacios en blanco
                if (carSpace.getPlate() == null) {
                    mapa = mapa.concat(carSpace.getCoordinate().toText() + " E        |");
                    // Si hay coche, se imprime su matrícula
                } else
                    mapa = mapa.concat(carSpace.getCoordinate().toText() + " E " + carSpace.getPlate() + "|");
            }

            // Si no estamos en la zona eléctrica, no se indica nada
            else {
                if (carSpace.getPlate() == null) {
                    // Si no hay coche aparcado en la plaza, se dejan espacios en blanco
                    mapa = mapa.concat(carSpace.getCoordinate().toText() + "          |");
                    // Si hay coche, se imprime su matrícula
                } else {
                    mapa = mapa.concat(carSpace.getCoordinate().toText() + "   " + carSpace.getPlate() + "|");
                }
            }
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

}