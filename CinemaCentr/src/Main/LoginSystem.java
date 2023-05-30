package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginSystem {

    private ArrayList<User> userList = new ArrayList<User>();
    private File userFile = new File("usuarios.txt");

    public void readUsers() {

        try {
            Scanner scanner = new Scanner(userFile);
            if (userFile.length() <= 1) {
                System.out.println("El archivo de usuarios está vacío.");
            } else {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(":");
                    String username = parts[0];
                    String password = parts[1];
                    String state = parts[2];
                    userList.add(new User(username, password, state));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void loginUser(String username, String password, Login loginFrame) {
        int loggedInUserIndex = -1;
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUserIndex = i;
                System.out.println("Login successful.");
                break;
            }
        }

        if (loggedInUserIndex != -1) {
            User loggedInUser = userList.get(loggedInUserIndex);
            loginFrame.setLoggedIn(true);
            loginFrame.setLoggedInUser(loggedInUser);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public void createUser(String username, String password, JLabel l, JTextField t1, JTextField t2, JTextField t3, JDialog J) {
        // Verificar si ya existe un usuario con el mismo nombre de usuario
        if (getUserByUsername(username) != null) {
            l.setText("Nombre de usuario en uso");
            return;
        }

        String state = "new";
        File userDirectory = new File("CinemaCentr/usuarios/" + username); // Crea el directorio del usuario
        if (userDirectory.mkdir()) {
            // Crea los subdirectorios
            File peliculasDirectory = new File(userDirectory, "peliculas");
            File salasDirectory = new File(userDirectory, "salas");
            File funcionesDirectory = new File(userDirectory, "funciones");
            File clientesDirectory = new File(userDirectory, "clientes");

            if (peliculasDirectory.mkdir() && salasDirectory.mkdir() && funcionesDirectory.mkdir() && clientesDirectory.mkdir()) {
                userList.add(new User(username, password, state)); // Crea un objeto tipo usuario y lo guarda en el arreglo
                t1.setText(null);
                t2.setText(null);
                t3.setText(null);
                try {
                    // Guarda los datos en el archivo
                    FileWriter writer = new FileWriter(userFile, true);
                    writer.write(username + ":" + password + ":" + state + "\n");
                    writer.close();
                    J.setVisible(true);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Error creating user subdirectories.");
            }
        } else {
            System.out.println("Error creating user directory.");
        }
    }

    private User getUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // No se encontró un usuario con el nombre de usuario proporcionado
    }

    public void createCineAndMarkUserAsOld(User user, String cineName) {
        String username = user.getUsername();
        String directoryPath = "CinemaCentr/usuarios/" + username + "/";
        String filePath = directoryPath + "Cine.txt";

        File userDirectory = new File(directoryPath);
        File userFile = new File(filePath);

        if (userDirectory.exists() && userDirectory.isDirectory()) {
            try {
                // Crear el archivo
                if (userFile.createNewFile()) {
                    // Escribir el texto en el archivo
                    FileWriter writer = new FileWriter(userFile);
                    writer.write(cineName);
                    writer.close();

                    // Actualizar el estado del usuario a "old"
                    user.setState("old");
                    // Actualizar el archivo de usuarios
                    updateUsersFile();

                    System.out.println("Archivo creado y usuario actualizado correctamente.");
                } else {
                    System.out.println("Error al crear el archivo.");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Directorio del usuario no encontrado.");
        }
    }

    private void updateUsersFile() {
        try {
            FileWriter writer = new FileWriter("usuarios.txt");
            for (User user : userList) {
                writer.write(user.getUsername() + ":" + user.getPassword() + ":" + user.getState() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo de usuarios: " + e.getMessage());
        }
    }

    public void removeLoggedInUser(User loggedInUser) {
        // Eliminar al usuario de la lista
        userList.remove(loggedInUser);

        // Eliminar el directorio del usuario
        String username = loggedInUser.getUsername();
        String userDirectoryPath = "CinemaCentr/usuarios/" + username;
        File userDirectory = new File(userDirectoryPath);
        if (userDirectory.exists()) {
            deleteDirectory(userDirectory);
        }

        // Actualizar el archivo de usuarios
        updateUsersFile();
    }

    private void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

}

class User {

    private String username;
    private String password;
    private String state;

    public User(String username, String password, String state) {
        this.username = username;
        this.password = password;
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
