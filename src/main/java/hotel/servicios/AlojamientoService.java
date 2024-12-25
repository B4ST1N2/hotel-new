package hotel.servicios;

import hotel.dominios.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AlojamientoService implements IAlojamientoService {
    private final String NOMBRE_ARCHIVO = "src/main/java/hotel/data/alojamientos.txt";
    private List<Alojamiento> alojamientos = new ArrayList<>();

    public AlojamientoService() {
        var archivo = new File(NOMBRE_ARCHIVO);
        boolean existe = false;
        try{
            existe = archivo.exists();
            if(!existe){
                var salida = new PrintWriter(new FileWriter(archivo, existe));
                salida.close();
                System.out.println("Se ha creado el archivo");
            } else {
                this.alojamientos = obtenerAlojamientos();
            }
        } catch (Exception e){
            System.out.println("Error al crear el Archivo: "+e.getMessage());
        }
    }

    @Override
    public List<Alojamiento> obtenerAlojamientos() {
        var listaAlojamiento = new ArrayList<Alojamiento>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(this.NOMBRE_ARCHIVO));
            for (String linea : lineas) {
                String[] datos = linea.split(",");
                String tipo = datos[0];
                switch (tipo) {
                    case "Hotel":
                        listaAlojamiento.add(new Hotel(tipo, datos[1],Integer.parseInt(datos[2]),datos[3],null,Double.parseDouble(datos[4])));
                        break;
                    case "Finca":
                        listaAlojamiento.add(new Finca(tipo, datos[1],Integer.parseInt(datos[2]),datos[3],null,Double.parseDouble(datos[4])));
                        break;
                    case "Apartamento":
                        listaAlojamiento.add(new Apartamento(tipo, datos[1],Integer.parseInt(datos[2]),datos[3],null,Double.parseDouble(datos[4])));
                        break;
                    case "DiaDeSol":
                        listaAlojamiento.add(new DiaDeSol(tipo, datos[1],Integer.parseInt(datos[2]),datos[3],null,datos[4],Boolean.parseBoolean(datos[5]),Double.parseDouble(datos[6])));
                        break;
                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los alojamientos: " + e.getMessage());
        }
        return listaAlojamiento;
    }



}
