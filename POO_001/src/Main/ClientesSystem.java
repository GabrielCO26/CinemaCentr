package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClientesSystem {

    private String username;
    private ArrayList<Cliente> clientesList = new ArrayList<Cliente>();
    private File clientesFile = new File("CinemaCentr/usuarios/" + username + "/clientes/clientes.txt");

    public ClientesSystem(String username) {
        this.username = username;
        clientesFile = new File("CinemaCentr/usuarios/" + username + "/clientes/clientes.txt");
        readClientes();
    }

    public List<Cliente> getUserList() {
        return clientesList;
    }

    public void createCliente(String username, String nombre, String correo, String telefono, String id, String suscripcion, JLabel l1, JLabel l2, JLabel l3, JTextField t1, JTextField t2, JTextField t3, JTextField t4) {
        String directoryPath = "CinemaCentr/usuarios/" + username + "/clientes";
        File userDirectory = new File(directoryPath);
        File clientesFile = new File(userDirectory, "clientes.txt");

        if (!userDirectory.exists() || !userDirectory.isDirectory()) {
            System.out.println("Directorio del usuario no encontrado.");
            return;
        }

        try {

            if (clientesFile.createNewFile()) {
                System.out.println("Archivo de clientes creado correctamente.");
            }

            // Verificar si ya existe un cliente con el mismo ID
            if (existeClienteConID(id)) {
                l3.setText("Ya existe un cliente con el mismo ID");
                return;
            }

            // Verificar si ya existe un cliente con el mismo teléfono
            if (existeClienteConTelefono(telefono)) {
                l2.setText("Ya existe un cliente con el mismo teléfono");
                return;
            }

            // Verificar si ya existe un cliente con el mismo correo
            if (existeClienteConCorreo(correo)) {
                l1.setText("Ya existe un cliente con el mismo correo");
                return;
            }

            FileWriter writer = new FileWriter(clientesFile, true);
            writer.write(nombre + ":" + correo + ":" + telefono + ":" + id + ":" + suscripcion + "\n");
            writer.close();
            clientesList.add(new Cliente(nombre, correo, telefono, id, suscripcion));
            t1.setText(null);
            t2.setText(null);
            t3.setText(null);
            t4.setText(null);
            System.out.println("Cliente creado y añadido correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de clientes: " + e.getMessage());
        }
    }

    public void readClientes() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(clientesFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(":");
                if (data.length >= 5) {
                    String nombre = data[0];
                    String correo = data[1];
                    String telefono = data[2];
                    String id = data[3];
                    String suscripcion = data[4];
                    clientesList.add(new Cliente(nombre, correo, telefono, id, suscripcion));
                }
            }
            reader.close();
            System.out.println("Clientes cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de clientes: " + e.getMessage());
        }
    }

    public void removeCliente(Cliente cliente) {
        clientesList.remove(cliente);
        updateClientesFile();
        System.out.println("Cliente eliminado correctamente.");
    }

    
    //Este método se llama desde la clase SuscripcionesSystem
    public void removeClientesBySuscripcion(String suscripcion) {
        //Patrón de diseño iterador para recorrer cada uno de los elementos de la lista
        Iterator<Cliente> iterator = clientesList.iterator();
        while (iterator.hasNext()) {
            Cliente cliente = iterator.next();
            if (cliente.getSuscripcion().equals(suscripcion)) {
                iterator.remove();
            }
        }
        updateClientesFile();
    }

    private void updateClientesFile() {
        try {
            FileWriter writer = new FileWriter(clientesFile);
            for (Cliente cliente : clientesList) {
                writer.write(cliente.getNombre() + ":" + cliente.getCorreo() + ":" + cliente.getTelefono() + ":" + cliente.getId() + ":" + cliente.getSuscripcion() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de clientes: " + e.getMessage());
        }
    }

    private boolean existeClienteConID(String id) {
        for (Cliente cliente : clientesList) {
            if (cliente.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean existeClienteConTelefono(String telefono) {
        for (Cliente cliente : clientesList) {
            if (cliente.getTelefono().equals(telefono)) {
                return true;
            }
        }
        return false;
    }

    private boolean existeClienteConCorreo(String correo) {
        for (Cliente cliente : clientesList) {
            if (cliente.getCorreo().equals(correo)) {
                return true;
            }
        }
        return false;
    }

}

class Cliente {

    private String nombre;
    private String correo;
    private String telefono;
    private String id;
    private String suscripcion;

    public Cliente(String nombre, String correo, String telefono, String id, String suscripcion) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.id = id;
        this.suscripcion = suscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getId() {
        return id;
    }

    public String getSuscripcion() {
        return suscripcion;
    }
}
