package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class SuscripcionesSystem {

    private String username;
    private ArrayList<Suscripcion> suscripcionesList = new ArrayList<>();
    private File suscripcionesFile = new File("CinemaCentr/usuarios/" + username + "/clientes/suscripciones.txt");

    public SuscripcionesSystem(String username) {
        this.username = username;
        suscripcionesFile = new File("CinemaCentr/usuarios/" + username + "/clientes/suscripciones.txt");
        readSuscripciones();
    }

    public List<Suscripcion> getSuscripcionesList() {
        return suscripcionesList;
    }

    public void createSuscripcion(String nombre, double costo, JLabel l1) {
        // Verificar si ya existe una suscripción con el mismo nombre
        if (existeSuscripcion(nombre)) {
            l1.setText("Ya existe la misma suscripción");
            return;
        }

        suscripcionesList.add(new Suscripcion(nombre, costo));
        updateSuscripcionesFile();
        System.out.println("Suscripción creada y añadida correctamente.");
    }

    public void readSuscripciones() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(suscripcionesFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":");
                if (data.length >= 2) {
                    String nombre = data[0];
                    double costo = Double.parseDouble(data[1]);
                    suscripcionesList.add(new Suscripcion(nombre, costo));
                }
            }
            reader.close();
            System.out.println("Suscripciones cargadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de suscripciones: " + e.getMessage());
        }
    }

    public void removeSuscripcion(Suscripcion suscripcion) {
        suscripcionesList.remove(suscripcion);
        updateSuscripcionesFile();
        
        ClientesSystem c = new ClientesSystem(username);
        c.removeClientesBySuscripcion(suscripcion.getNombre());
        System.out.println("Suscripción eliminada correctamente.");
    }

    private void updateSuscripcionesFile() {
        try {
            FileWriter writer = new FileWriter(suscripcionesFile);
            for (Suscripcion suscripcion : suscripcionesList) {
                writer.write(suscripcion.getNombre() + ":" + suscripcion.getCosto() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de suscripciones: " + e.getMessage());
        }
    }

    private boolean existeSuscripcion(String nombre) {
        for (Suscripcion suscripcion : suscripcionesList) {
            if (suscripcion.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
}

class Suscripcion {

    private String nombre;
    private double costo;

    public Suscripcion(String nombre, double costo) {
        this.nombre = nombre;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
