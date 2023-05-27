/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Experimental;

/**
 *
 * @author Andres Chinchilla
 */
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.border.EmptyBorder;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class GestionPeliculas implements Serializable {

    private JPanel panel;
    private JPanel panelGlobal;
    private ArrayList<Pelicula> peliculas;
    private JPanel panelInformacion;
    private JPanel panelBotones;
    private JScrollPane scrollPane;
    private String usuario;
    Color color = Color.decode("#4B00D8");

    public GestionPeliculas(String usuario) {
        this.usuario = usuario;
        //Se crea una lista que almacenara objetos de tipo pelicula posteriormente
        peliculas = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        //ImageIcon backgroundImage = new ImageIcon("src/Images/background.jpg ");
        //JLabel backgroundLabel = new JLabel(backgroundImage);
        //backgroundLabel.setBounds(0, 0, 1000, 562);

        panelGlobal = new JPanel();
        panelGlobal.setBounds(0, 0, 1000, 570);
        panelGlobal.setLayout(new BorderLayout());
        //panelGlobal.add(backgroundLabel);

        panel = new JPanel();//Este panel contendra todas las peliculas
        panel.setBackground(new Color(255, 255, 255));
        panel.setLayout(new GridLayout(0, 3, 10, 10));//El panel sera una cuadricula de 3 columnas y un numero de filas indefinido
        scrollPane = new JScrollPane(panel);/*Se agrega el panel a un scrollPane para cuando el contenido del panel supere las dimensiones de
        la ventana, este le permita al usuario desplazarse verticalmente y poder visualizar todo el contenido*/
        panelGlobal.add(scrollPane);

        panelInformacion = new JPanel();//Este panel contendra la informacion especifica de una pelicula
        panelInformacion.setBackground(new Color(255, 255, 255));
        panelInformacion.setBounds(0, 0, 1000, 570);
        panelInformacion.setLayout(null);//Esto permite que los componentes del panel puedan agregarse en cualquier posicion
        panelInformacion.setVisible(false);

        panelBotones = new JPanel();
        panelBotones.setBounds(0, 500, 1000, 50);
        panelBotones.setBackground(new Color(56, 48, 46));
        panelGlobal.add(panelBotones, BorderLayout.SOUTH);
        cargarPeliculas();

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(color);
        btnAgregar.setBorderPainted(false);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula();
            }
        });

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(color);
        btnEliminar.setBorderPainted(false);
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPelicula();
            }
        });

        JButton btnInformacion = new JButton("Información");
        btnInformacion.setBackground(color);
        btnInformacion.setBorderPainted(false);
        btnInformacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                informacion();
            }
        });

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("Roboto/Roboto-Regular.ttf"));
            Font robotoFont = font.deriveFont(Font.PLAIN, 14); // Tamaño de fuente 14, estilo normal (plain)
            btnAgregar.setFont(robotoFont);
            btnEliminar.setFont(robotoFont);
            btnInformacion.setFont(robotoFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnInformacion);
    }

    //Este metodo se encarga de guardar y escribir los objetos de tipo pelicula en un archivo serializado
    private void guardarPeliculas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("CinemaCentr/usuarios/" + usuario + "/peliculas/peliculas.dat"))) {
            oos.writeObject(peliculas);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panelGlobal, "Error al guardar las películas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*Este metodo se usa una vez cada que se ejecuta el programa y se encarga de leer el archivo y agregar a la lista peliculas los objetos de tipo 
    pelicula que se encuentren en este, para luego crear un panel para cada pelicula compuesto por su imagen y nombre, y los añade al panel principal
     */
    private void cargarPeliculas() {
        File archivo = new File("CinemaCentr/usuarios/" + usuario + "/peliculas/peliculas.dat");
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                peliculas = (ArrayList<Pelicula>) ois.readObject();
                for (Pelicula pelicula : peliculas) {
                    JPanel peliculaPanel = createPeliculaPanel(pelicula);
                    panel.add(peliculaPanel);
                }
                panelGlobal.revalidate();
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(panelGlobal, "Error al cargar las películas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void agregarPelicula() {
        String nombre, genero, duracion, fecha;
        nombre = JOptionPane.showInputDialog(panelGlobal, "Ingrese el nombre de la película");
        genero = JOptionPane.showInputDialog(panelGlobal, "Ingrese el genero de la película");
        duracion = JOptionPane.showInputDialog(panelGlobal, "Ingrese la duración de la película (en minutos)");
        fecha = JOptionPane.showInputDialog(panelGlobal, "Ingrese el año de estreno de la pelicula");
        if (nombre != null && !nombre.trim().isEmpty() && genero != null && !genero.trim().isEmpty() && duracion != null && !duracion.trim().isEmpty() && fecha != null && !fecha.trim().isEmpty()) {
            JFileChooser fileChooser = new JFileChooser();//Permite al usuario seleccionar una imagen de su dispositivo
            int resultado = fileChooser.showOpenDialog(panelGlobal);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                try {
                    Image imagen = ImageIO.read(fileChooser.getSelectedFile());//Se crea un objeto imagen que almacera la imagen seleccionada
                    File directorioImagenes = new File("CinemaCentr/usuarios/" + usuario + "/peliculas/imagenespeliculas");//Se accede a un directorio de imagenes, si no existe se creara
                    if (!directorioImagenes.exists()) {
                        directorioImagenes.mkdirs();
                    }
                    String rutaImagen = "CinemaCentr/usuarios/" + usuario + "/peliculas/imagenespeliculas/" + nombre + ".png";
                    ImageIO.write((BufferedImage) imagen, "png", new File(rutaImagen));//Se guarda la imagen en el archivo y se especifica su ruta
                    Pelicula pelicula = new Pelicula(nombre, rutaImagen, genero, duracion, fecha);
                    peliculas.add(pelicula);
                    guardarPeliculas();//Se guarda el objeto de tipo pelicula en un archivo
                    JPanel peliculaPanel = createPeliculaPanel(pelicula);//Se crea un panel con la foto y el nombre de la pelicula y se añade al principal
                    panel.add(peliculaPanel);

                    panelGlobal.revalidate();

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(panelGlobal, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(panelGlobal, "Error al ingresar la duración: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void eliminarPelicula() {
        //Envia un mensaje si no hay peliculas para eliminar
        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(panelGlobal, "No hay películas para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Se encarga de verificar si al momento de presionar el boton eliminar hay pelicula seleccionada
        int indiceSeleccionado = -1;
        for (int i = 0; i < panel.getComponentCount(); i++) {
            Component componente = panel.getComponent(i);
            if (componente instanceof JPanel) {
                JPanel peliculaPanel = (JPanel) componente;
                if (peliculaPanel.getBackground().equals(new Color(232, 72, 85))) {
                    indiceSeleccionado = i;
                    break;
                }
            }
        }
        //Si hay una pelicula seleccionada
        if (indiceSeleccionado != -1) {
            //Se obtiene el indice de la pelicula seleccionada dentro de la lista y se le asigna a un objeto 
            Pelicula peliculaSeleccionada = peliculas.get(indiceSeleccionado);
            int opcion = JOptionPane.showConfirmDialog(panelGlobal, "¿Está seguro de que desea eliminar la película " + peliculaSeleccionada.getNombre() + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                File imagenArchivo = new File(peliculaSeleccionada.getRutaImagen());
                if (imagenArchivo.exists()) {
                    imagenArchivo.delete();//Se elimina la imagen del archivo
                }
                peliculas.remove(indiceSeleccionado);//Se elimina de la lista
                guardarPeliculas();
                panel.remove(indiceSeleccionado);//Se elimina del panel
                panel.revalidate();
                panel.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(panelGlobal, "Seleccione una película para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void informacion() {
        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(panelGlobal, "No hay películas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Verifica si se seleciono una pelicula
        int indiceSeleccionado = -1;
        for (int i = 0; i < panel.getComponentCount(); i++) {
            Component componente = panel.getComponent(i);
            if (componente instanceof JPanel) {
                JPanel peliculaPanel = (JPanel) componente;
                if (peliculaPanel.getBackground().equals(new Color(232, 72, 85))) {
                    indiceSeleccionado = i;
                    break;
                }
            }
        }
        if (indiceSeleccionado != -1) {
            panel.setVisible(false);
            panelBotones.setVisible(false);
            scrollPane.setVisible(false);
            panelInformacion.setVisible(true);//Se hace visible la ventana que mostrara la informacion especifica de una pelicula
            Pelicula peliculaSeleccionada = peliculas.get(indiceSeleccionado);
            Font font = new Font("Arial", Font.BOLD, 20);//Se establece el tipo de letra, estilo y tamaño que tendran los Jlabel

            JLabel nombreLabel = new JLabel("Nombre: " + peliculaSeleccionada.getNombre());
            nombreLabel.setForeground(Color.BLACK);
            nombreLabel.setBounds(600, 50, 300, 50);
            nombreLabel.setFont(font);

            JLabel generoLabel = new JLabel("Genero: " + peliculaSeleccionada.getGenero());
            generoLabel.setForeground(Color.BLACK);
            generoLabel.setBounds(600, 170, 300, 50);
            generoLabel.setFont(font);

            JLabel duracionLabel = new JLabel("Duracion(min) : " + peliculaSeleccionada.getDuracion());
            duracionLabel.setForeground(Color.BLACK);
            duracionLabel.setBounds(600, 290, 250, 50);
            duracionLabel.setFont(font);

            JLabel fechaLabel = new JLabel("Año de estreno: " + peliculaSeleccionada.getFecha());
            fechaLabel.setForeground(Color.BLACK);
            fechaLabel.setBounds(600, 410, 250, 50);
            fechaLabel.setFont(font);

            panelInformacion.removeAll();//Antes de añadir las nuevas componentes al panel se borra lo que contenga anteriormente
            panelInformacion.add(nombreLabel);
            panelInformacion.add(generoLabel);
            panelInformacion.add(duracionLabel);
            panelInformacion.add(fechaLabel);

            // Crear y añadir la etiqueta con la imagen de la película
            try {
                BufferedImage imagen = ImageIO.read(new File(peliculaSeleccionada.getRutaImagen()));
                BufferedImage bufferedImage = getScaledImage(imagen, 400, 500);//Se llama al metodo getScaledImage y se establece el nuevo tamaño de la imagen
                JLabel imagenLabel = new JLabel(new ImageIcon(bufferedImage));
                imagenLabel.setBounds(110, 20, 400, 500);
                panelInformacion.add(imagenLabel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(panelGlobal, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            JPanel panelColor = new JPanel();
            panelColor.setBounds(0, 530, 1000, 40);
            panelColor.setBackground(new Color(56, 48, 46));

            JPanel panelColor1 = new JPanel();
            panelColor1.setBounds(0, 0, 40, 550);
            panelColor1.setBackground(new Color(56, 48, 46));

            JPanel panelColor2 = new JPanel();
            panelColor2.setBounds(960, 0, 40, 550);
            panelColor2.setBackground(new Color(56, 48, 46));

            JButton btnAtras = new JButton("Atrás");
            btnAtras.setBackground(color);
            btnAtras.setBorderPainted(false);
            try {
                Font font1 = Font.createFont(Font.TRUETYPE_FONT, new File("Roboto/Roboto-Regular.ttf"));
                Font robotoFont = font1.deriveFont(Font.PLAIN, 14); // Tamaño de fuente 14, estilo normal (plain)
                btnAtras.setFont(robotoFont);
            } catch (IOException | FontFormatException e) {
                e.printStackTrace();
            }
            btnAtras.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {//Si presiona el boton "atras" lo llevara a la ventana principal
                    panelInformacion.setVisible(false);
                    panel.setVisible(true);
                    panelBotones.setVisible(true);
                    scrollPane.setVisible(true);
                }
            });
            btnAtras.setBounds(465, 535, 70, 30);
            panelColor.add(btnAtras);
            panelInformacion.add(panelColor);
            panelInformacion.add(panelColor1);
            panelInformacion.add(panelColor2);
            panelGlobal.add(panelInformacion);
        } else {
            JOptionPane.showMessageDialog(panelGlobal, "Seleccione una película.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Este metodo toma una imagen y la redimensiona a las dimensiones especificadas suavizando la escala y manteniendo la calidad de imagen
    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double) width / imageWidth;
        double scaleY = (double) height / imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
                image,
                new BufferedImage(width, height, image.getType()));
    }

    private JPanel createPeliculaPanel(Pelicula pelicula) {
        JPanel panelp = new JPanel(new BorderLayout());//Se crea el panel que contendra una pelicula y su nombre
        panelp.setBackground(new Color(255, 255, 255));
        panelp.setBorder(new EmptyBorder(10, 0, 50, 0));

        JLabel nombreLabel = new JLabel(pelicula.getNombre());
        nombreLabel.setForeground(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 17);
        nombreLabel.setFont(font);
        //Se obtiene el ancho del nombre de la pelicula para centrarlo con respecto a la imagen    
        int widthString = nombreLabel.getFontMetrics(nombreLabel.getFont()).stringWidth(nombreLabel.getText());
        if (peliculas.size() < 4) {
            nombreLabel.setBounds(162 - widthString / 2, 370, 200, 50);
        } else {
            nombreLabel.setBounds(162 - widthString / 2, 255, 200, 50);
        }
        panelp.add(nombreLabel);
        panelp.addMouseListener(new MouseAdapter() {//Interfaz que permite detectar y responder a eventos relacionados con el mouse
            @Override
            public void mouseClicked(MouseEvent e) {//Si se le da click a una pelicula
                for (Component componente : panel.getComponents()) {
                    if (componente instanceof JPanel) {
                        JPanel otroPanelPelicula = (JPanel) componente;
                        if (otroPanelPelicula == panelp) {
                            if (panelp.getBackground().equals(new Color(232, 72, 85))) {//Si el fondo de la pelicula es amarillo se hace nulo
                                panelp.setBackground(null);
                            } else {//En caso contrario el fondo se hace amarillo
                                panelp.setBackground(new Color(232, 72, 85));
                            }
                        } else {
                            otroPanelPelicula.setBackground(null);
                        }
                    }
                }
            }
        });
        try {
            BufferedImage imagen = ImageIO.read(new File(pelicula.getRutaImagen()));
            BufferedImage bufferedImage = getScaledImage(imagen, 200, 250);
            JLabel imagenLabel = new JLabel(new ImageIcon(bufferedImage));

            panelp.add(imagenLabel, BorderLayout.CENTER);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(panelGlobal, "Error al cargar la imagen: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return panelp;
    }

    public JPanel getPanelGlobal() {
        return panelGlobal;
    }

    public Vector<String> getNamesPeli() {
        Vector<String> nombres = new Vector<>();
        for (Pelicula pelicula : peliculas) {
            nombres.add(pelicula.getNombre());
        }
        return nombres;
    }
}
