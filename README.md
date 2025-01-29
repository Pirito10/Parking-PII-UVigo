# Parking
Parking is a **Parking Management System** developed as part of the course "[Programación II](https://secretaria.uvigo.gal/docnet-nuevo/guia_docent/index.php?centre=305&ensenyament=V05G301V01&assignatura=V05G301V01110&any_academic=2021_22)" in the Telecommunications Engineering Degree at the Universidad de Vigo (2021 - 2022).

## About The Project
This project simulates a management system for a parking, where cars are classified depending on their type and charged based on the service used. The system incorporates concepts of object-oriented programming such as inheritance, polymorphism, and encapsulation, which were key learning objectives of the course.

The project features:
- A database system where cars are stored.
- Modular design for extensibility.
- Text-based input/output using predefined files for the parking, the cars, and the entry/exit of those.

## How To Run
### Compilation
Make sure you have a Java JDK installed on your system. Then compile all Java classes and generate the `.class` files with:

```bash
javac -d bin P8/*.java
```

This command creates the compiled files inside the `bin/` directory.

### Execution
Once compiled, you can run the system with:

```bash
java -cp bin P8 <f_parking> <f_entradas_salidas> <f_parking_actualizado> <f_coches_ciudad> <f_lista_coches> <f_dibujo_parking> <f_ingresos>
```

| Option | Description | Example |
|--------|-------------|---------|
| `f_parking` | Specifies the file containing parking data | `parking.txt` |
| `f_entradas_salidas` | Specifies the file containing cars entry/exit data | `entradasSalidas.txt` |
| `f_parking_actualizado` | Specifies the file where the updated parking data will be written | `parkingActualizado.txt` |
| `f_coches_ciudad` | Specifies the file containing car data | `cityCars.txt` |
| `f_lista_coches` | Specifies the file where the updated cars data will be written | `cityCarsActualizado.txt` |
| `f_dibujo_parking` | Specifies the file where a drawing of the parking will be written | `dibujoParking.txt` |
| `f_ingresos` | Specifies the file where the income data will be written | `ingresos.txt` |

#### Example
```bash
java -cp bin P8 test/parking.txt test/entradasSalidas.txt parkingActualizado.txt test/cityCars.txt cityCarsActualizado.txt dibujoParking.txt ingresos.txt
```

## About The Code

The original specification document for this project was lost, so the only way to properly understand the system and how it works is by inspecting the code directly.

However, the final version of the project is located in the [**P8**](P8) directory.

Previous versions, found in other directories, may have different or fewer features. Therefore, the provided [**test**](test) files may not be compatible with these older versions.