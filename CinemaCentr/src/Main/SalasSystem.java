package Main;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SalasSystem {

    private String username;
    private ArrayList<Sala> salasList = new ArrayList<>();
    private File salasFile = new File("CinemaCentr/usuarios/" + username + "/salas/salas.txt");

    public SalasSystem(String username) {
        this.username = username;
        salasFile = new File("CinemaCentr/usuarios/" + username + "/salas/salas.txt");
        readSalas();
    }

    public List<Sala> getSalasList() {
        return salasList;
    }

    public void createSalas(String nombre, int rows, int columns, JLabel l1, JTextField t1, JTextField t2, JTextField t3) {
        // Verificar si ya existe una sala con el mismo nombre
        if (existeSala(nombre)) {
            l1.setText("Ya existe una sala con el nombre");
            return;
        }

        salasList.add(new Sala(nombre, rows, columns));
        updateSalasFile();
        t1.setText(null);
        t2.setText(null);
        t3.setText(null);
        System.out.println("Sala creada y añadida correctamente.");
    }

    public void readSalas() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(salasFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":");
                if (data.length >= 3) {
                    String nombre = data[0];
                    int rows = Integer.parseInt(data[1]);
                    int columns = Integer.parseInt(data[2]);
                    salasList.add(new Sala(nombre, rows, columns));
                }
            }
            reader.close();
            System.out.println("Salas cargadas correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de salas: " + e.getMessage());
        }
    }

    private boolean existeSala(String nombre) {
        for (Sala sala : salasList) {
            if (sala.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void removeSala(Sala sala) {
        salasList.remove(sala);
        updateSalasFile();
        System.out.println("Sala eliminada correctamente.");
    }

    private void updateSalasFile() {
        try {
            FileWriter writer = new FileWriter(salasFile);
            for (Sala sala : salasList) {
                writer.write(sala.getNombre() + ":" + sala.getRows() + ":" + sala.getColumns() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de salas: " + e.getMessage());
        }
    }

}

class Sala {

    private String nombre;
    private int rows;
    private int columns;

    public Sala(String nombre, int rows, int columns) {
        this.nombre = nombre;
        this.rows = rows;
        this.columns = columns;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
