package hotel.service.alojamiento;

import hotel.model.entity.*;

import java.util.ArrayList;

public class CargarAlojamientos {

    public Alojamiento cargarAlojamiento(String[] datos){
        Alojamiento alojamiento= null;
        String tipo = datos[0];
        String nombre = datos[1];
        int calificacion = Integer.parseInt(datos[2]);
        String ciudad = datos[3];

        switch (tipo.toLowerCase()) {
            case "hotel":
               alojamiento = new Hotel(tipo, nombre, calificacion, ciudad, new ArrayList<>());
                break;
            case "apartamento":
                int cantidadPersonasApto = Integer.parseInt(datos[4]);
                boolean disponibilidadApto = Boolean.parseBoolean(datos[5]);
                double precioNocheApto = Double.parseDouble(datos[6]);
                alojamiento = new Apartamento(tipo, nombre, calificacion, ciudad, precioNocheApto, cantidadPersonasApto, disponibilidadApto);
                break;
            case "finca":
                int cantidadPersonasFinca = Integer.parseInt(datos[4]);
                boolean disponibilidadFinca = Boolean.parseBoolean(datos[5]);
                double precioNocheFinca = Double.parseDouble(datos[6]);
                alojamiento = new Finca(tipo, nombre, calificacion, ciudad, precioNocheFinca, cantidadPersonasFinca, disponibilidadFinca);
                break;
            case "diadesol":
                String actividades = datos[4];
                boolean refrigerio = Boolean.parseBoolean(datos[5]);
                double precioActividad = Double.parseDouble(datos[6]);
                int cantidadPersonasDia = Integer.parseInt(datos[7]);
                boolean disponibilidadDia = Boolean.parseBoolean(datos[8]);
                alojamiento = new DiaDeSol(tipo, nombre, calificacion, ciudad, actividades, refrigerio, precioActividad, cantidadPersonasDia, disponibilidadDia);
                break;
            default:
                System.out.println("Tipo desconocido: " + tipo);
        }

        return alojamiento;
    }
}
