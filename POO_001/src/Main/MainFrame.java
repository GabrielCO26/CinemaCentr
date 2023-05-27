package Main;

import Experimental.GestionPeliculas;
import GUIresources.Animations;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Gaco
 */
public class MainFrame extends javax.swing.JFrame {

    //Coordenadas del mouse (variables)
    int mouseX, mouseY;

    public User loggedInUser;
    public Cine Cine;

    private JPanel currentPanel;
    private int currentpanel = 0;

    LoginSystem LoginSystem = new LoginSystem();

    //Función mostrar panel
    public void showPanel(JPanel p) {
        p.setSize(1000, 570);
        p.setLocation(0, 0);
        dinamicPanel.removeAll();
        dinamicPanel.add(p, BorderLayout.CENTER);
        dinamicPanel.revalidate();
        dinamicPanel.repaint();
        currentPanel = p;
    }

    // Cierra la instancia actual de MainFrame
    public void logout() {
        setVisible(false);
        dispose();
        new Login().setVisible(true);
    }

    public MainFrame(Main.User user) {
        loggedInUser = user;
        Cine = getandsetCineName(loggedInUser.getUsername(), Cine);
        System.out.println("Nombre del cine: " + Cine.getName());

        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Images/AppIcon.png")).getImage());
        setImageLabel(ImageLabel, "CinemaCentr/usuarios/" + loggedInUser.getUsername() + "/UserImage.png");

        LoginSystem.readUsers();
        dinamicPanel.setBackground(new Color(0, 0, 0, 0));
        this.setBackground(new Color(0, 0, 0, 0));
        Bg.setColor(new Color(0, 0, 0, 0));
        showPanel(new Dashboard(this));

        // Agregar el panel a la capa inferior
        LayeredPanel.add(Bg, JLayeredPane.DEFAULT_LAYER);
        // Agregar el botón a la capa superior
        LayeredPanel.add(DrawerMenu, JLayeredPane.PALETTE_LAYER);
        // Mover el botón a la posición más alta en la capa superior
        LayeredPanel.setLayer(DrawerMenu, JLayeredPane.PALETTE_LAYER.intValue(), 0);

        System.out.println("---------------------");
        System.out.println("From MainFrame");
        System.out.println("Usuario: " + loggedInUser.getUsername());
        System.out.println("Estado de usuario: " + loggedInUser.getState());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LayeredPanel = new javax.swing.JLayeredPane();
        DrawerMenu = new GUIresources.RoundPanel2();
        DashboardButton = new javax.swing.JButton();
        MoviesButton = new javax.swing.JButton();
        SalasButton = new javax.swing.JButton();
        FuncionesButton = new javax.swing.JButton();
        ClientesButton = new javax.swing.JButton();
        SuscripcionesButton = new javax.swing.JButton();
        SettingsButton = new javax.swing.JButton();
        LogoutButton = new javax.swing.JButton();
        ImageIcon = new GUIresources.RoundPanel2();
        ImageLabel = new javax.swing.JLabel();
        Bg = new GUIresources.RoundPanel();
        exitPanel = new GUIresources.RoundPanel2();
        exitLabel = new javax.swing.JLabel();
        dinamicPanel = new javax.swing.JPanel();
        Bar = new javax.swing.JPanel();
        drawerButton = new GUIresources.RoundPanel2();
        DrawerLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout LayeredPanelLayout = new javax.swing.GroupLayout(LayeredPanel);
        LayeredPanel.setLayout(LayeredPanelLayout);
        LayeredPanelLayout.setHorizontalGroup(
            LayeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        LayeredPanelLayout.setVerticalGroup(
            LayeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(LayeredPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 600));

        DrawerMenu.setBottomLeftRadius(0);
        DrawerMenu.setBottomRightRadius(13);
        DrawerMenu.setColor(new java.awt.Color(75, 0, 216));
        DrawerMenu.setMaximumSize(new java.awt.Dimension(270, 270));
        DrawerMenu.setTopLeftRadius(0);
        DrawerMenu.setTopRightRadius(13);
        DrawerMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DashboardButton.setBackground(new java.awt.Color(75, 0, 237));
        DashboardButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        DashboardButton.setForeground(new java.awt.Color(255, 255, 255));
        DashboardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/hogar.png"))); // NOI18N
        DashboardButton.setText("Panel principal");
        DashboardButton.setBorder(null);
        DashboardButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DashboardButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DashboardButton.setIconTextGap(20);
        DashboardButton.setInheritsPopupMenu(true);
        DashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashboardButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(DashboardButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 180, 30));

        MoviesButton.setBackground(new java.awt.Color(75, 0, 237));
        MoviesButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        MoviesButton.setForeground(new java.awt.Color(255, 255, 255));
        MoviesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/video-camara-alt.png"))); // NOI18N
        MoviesButton.setText("Películas");
        MoviesButton.setBorder(null);
        MoviesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MoviesButton.setFocusable(false);
        MoviesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MoviesButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MoviesButton.setIconTextGap(20);
        MoviesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MoviesButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(MoviesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 180, 30));

        SalasButton.setBackground(new java.awt.Color(75, 0, 237));
        SalasButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        SalasButton.setForeground(new java.awt.Color(255, 255, 255));
        SalasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/salida-en-antena.png"))); // NOI18N
        SalasButton.setText("Salas");
        SalasButton.setBorder(null);
        SalasButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SalasButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SalasButton.setIconTextGap(20);
        SalasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalasButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(SalasButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 180, 30));

        FuncionesButton.setBackground(new java.awt.Color(75, 0, 237));
        FuncionesButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        FuncionesButton.setForeground(new java.awt.Color(255, 255, 255));
        FuncionesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/palomitas-de-maiz.png"))); // NOI18N
        FuncionesButton.setText("Funciones");
        FuncionesButton.setBorder(null);
        FuncionesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FuncionesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        FuncionesButton.setIconTextGap(20);
        FuncionesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuncionesButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(FuncionesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 180, 30));

        ClientesButton.setBackground(new java.awt.Color(75, 0, 237));
        ClientesButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        ClientesButton.setForeground(new java.awt.Color(255, 255, 255));
        ClientesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/usuarios.png"))); // NOI18N
        ClientesButton.setText("Clientes");
        ClientesButton.setBorder(null);
        ClientesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ClientesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ClientesButton.setIconTextGap(20);
        ClientesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClientesButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(ClientesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 180, 30));

        SuscripcionesButton.setBackground(new java.awt.Color(75, 0, 237));
        SuscripcionesButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        SuscripcionesButton.setForeground(new java.awt.Color(255, 255, 255));
        SuscripcionesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/tarjeta-de-credito.png"))); // NOI18N
        SuscripcionesButton.setText("Suscripciones");
        SuscripcionesButton.setBorder(null);
        SuscripcionesButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SuscripcionesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SuscripcionesButton.setIconTextGap(20);
        SuscripcionesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuscripcionesButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(SuscripcionesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 180, 30));

        SettingsButton.setBackground(new java.awt.Color(75, 0, 237));
        SettingsButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        SettingsButton.setForeground(new java.awt.Color(255, 255, 255));
        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/ajustes-deslizadores.png"))); // NOI18N
        SettingsButton.setText("Configuración");
        SettingsButton.setBorder(null);
        SettingsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SettingsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        SettingsButton.setIconTextGap(20);
        SettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(SettingsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 180, 30));

        LogoutButton.setBackground(new java.awt.Color(75, 0, 237));
        LogoutButton.setFont(new java.awt.Font("Roboto Medium", 0, 18)); // NOI18N
        LogoutButton.setForeground(new java.awt.Color(255, 255, 255));
        LogoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/salida.png"))); // NOI18N
        LogoutButton.setText("Logout");
        LogoutButton.setBorder(null);
        LogoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LogoutButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LogoutButton.setIconTextGap(20);
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });
        DrawerMenu.add(LogoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 180, 30));

        ImageIcon.setColor(new java.awt.Color(66, 0, 193));
        ImageIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ImageIcon.setRadius(13);

        ImageLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ImageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout ImageIconLayout = new javax.swing.GroupLayout(ImageIcon);
        ImageIcon.setLayout(ImageIconLayout);
        ImageIconLayout.setHorizontalGroup(
            ImageIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImageIconLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        ImageIconLayout.setVerticalGroup(
            ImageIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImageIconLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );

        DrawerMenu.add(ImageIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 10, 110, 110));

        getContentPane().add(DrawerMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(-180, 45, 180, 540));

        Bg.setMinimumSize(new java.awt.Dimension(1000, 600));
        Bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exitPanel.setColor(new java.awt.Color(75, 0, 216));
        exitPanel.setTopRightRadius(13);

        exitLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        exitLabel.setForeground(new java.awt.Color(255, 255, 255));
        exitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitLabel.setText("X");
        exitLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout exitPanelLayout = new javax.swing.GroupLayout(exitPanel);
        exitPanel.setLayout(exitPanelLayout);
        exitPanelLayout.setHorizontalGroup(
            exitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        exitPanelLayout.setVerticalGroup(
            exitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, exitPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(exitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        Bg.add(exitPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 50, -1));

        javax.swing.GroupLayout dinamicPanelLayout = new javax.swing.GroupLayout(dinamicPanel);
        dinamicPanel.setLayout(dinamicPanelLayout);
        dinamicPanelLayout.setHorizontalGroup(
            dinamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        dinamicPanelLayout.setVerticalGroup(
            dinamicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        Bg.add(dinamicPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1000, 570));

        Bar.setBackground(new java.awt.Color(75, 0, 216));
        Bar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarMouseDragged(evt);
            }
        });
        Bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout BarLayout = new javax.swing.GroupLayout(Bar);
        Bar.setLayout(BarLayout);
        BarLayout.setHorizontalGroup(
            BarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 770, Short.MAX_VALUE)
        );
        BarLayout.setVerticalGroup(
            BarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        Bg.add(Bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 770, 30));

        drawerButton.setColor(new java.awt.Color(75, 0, 216));
        drawerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        drawerButton.setTopLeftRadius(13);
        drawerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                drawerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                drawerButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawerButtonMousePressed(evt);
            }
        });
        drawerButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DrawerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/CinemaCentrW.png"))); // NOI18N
        drawerButton.add(DrawerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 3, -1, -1));

        Bg.add(drawerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 30));

        getContentPane().add(Bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseEntered
        exitPanel.setColor(new Color(232, 72, 85));
    }//GEN-LAST:event_exitLabelMouseEntered

    private void exitLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMouseExited
        exitPanel.setColor(new Color(75, 0, 216));
    }//GEN-LAST:event_exitLabelMouseExited

    private void exitLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLabelMousePressed
        System.exit(0);
    }//GEN-LAST:event_exitLabelMousePressed

    private void BarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarMousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
    }//GEN-LAST:event_BarMousePressed

    private void BarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarMouseDragged
        setLocation(
                getLocation().x + evt.getX() - mouseX,
                getLocation().y + evt.getY() - mouseY
        );
    }//GEN-LAST:event_BarMouseDragged

    private void DashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardButtonActionPerformed
        Dashboard Dashboard = new Dashboard(this);

        if (currentpanel != 0) {
            showPanel(Dashboard);
            currentpanel = 0;
        }
    }//GEN-LAST:event_DashboardButtonActionPerformed

    private void MoviesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MoviesButtonActionPerformed
        GestionPeliculas gestionPeliculas = new GestionPeliculas(loggedInUser.getUsername());
        JPanel mainPanel = gestionPeliculas.getPanelGlobal();
        if (currentpanel != 1) {
            showPanel(mainPanel);
            currentpanel = 1;
        }
    }//GEN-LAST:event_MoviesButtonActionPerformed

    private void SalasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalasButtonActionPerformed
        Salas Salas = new Salas(loggedInUser.getUsername());

        if (currentpanel != 2) {
            showPanel(Salas);
            currentpanel = 2;
        }
    }//GEN-LAST:event_SalasButtonActionPerformed

    private void FuncionesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuncionesButtonActionPerformed
        GestionPeliculas gestionPeliculas = new GestionPeliculas(loggedInUser.getUsername());
        Vector<String> nombresPeliculas = gestionPeliculas.getNamesPeli();
        String usuario = loggedInUser.getUsername();
        System.out.println(usuario);
        Funciones Funciones = new Funciones(nombresPeliculas, usuario);
        if (currentpanel != 3) {
            showPanel(Funciones);
            currentpanel = 3;
        }
    }//GEN-LAST:event_FuncionesButtonActionPerformed

    private void ClientesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClientesButtonActionPerformed
        Clientes Clientes = new Clientes(this);

        if (currentpanel != 4) {
            showPanel(Clientes);
            currentpanel = 4;
        }
    }//GEN-LAST:event_ClientesButtonActionPerformed

    private void SettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsButtonActionPerformed
        Settings Settings = new Settings(this);

        if (currentpanel != 6) {
            showPanel(Settings);
            currentpanel = 6;
        }
    }//GEN-LAST:event_SettingsButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        this.logout();
        System.out.println("logged out");
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void drawerButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawerButtonMousePressed
        Animations.xRight(-180, 0, 1, 1, DrawerMenu);
        Animations.xLeft(0, -180, 1, 1, DrawerMenu);
    }//GEN-LAST:event_drawerButtonMousePressed

    private void drawerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawerButtonMouseEntered
        drawerButton.setColor(new Color(48, 0, 140));
    }//GEN-LAST:event_drawerButtonMouseEntered

    private void drawerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawerButtonMouseExited
        drawerButton.setColor(new Color(75, 0, 216));
    }//GEN-LAST:event_drawerButtonMouseExited

    private void SuscripcionesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuscripcionesButtonActionPerformed
        Suscripciones Suscripciones = new Suscripciones(this);

        if (currentpanel != 5) {
            showPanel(Suscripciones);
            currentpanel = 5;
        }
    }//GEN-LAST:event_SuscripcionesButtonActionPerformed

    public void setImageLabel(JLabel label, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icon);
        this.repaint();
    }

    public static Cine getandsetCineName(String username, Cine c) {
        String rutaArchivo = "CinemaCentr/usuarios/" + username + "/Cine.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String nombreCine = br.readLine();
            return new Cine(nombreCine);
        } catch (IOException e) {
            e.printStackTrace();
            // Manejo de errores en caso de que ocurra un problema al leer el archivo
        }
        return null; // En caso de error, devuelve null
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bar;
    private GUIresources.RoundPanel Bg;
    private javax.swing.JButton ClientesButton;
    private javax.swing.JButton DashboardButton;
    private javax.swing.JLabel DrawerLabel;
    public static GUIresources.RoundPanel2 DrawerMenu;
    private javax.swing.JButton FuncionesButton;
    private GUIresources.RoundPanel2 ImageIcon;
    public static javax.swing.JLabel ImageLabel;
    private javax.swing.JLayeredPane LayeredPanel;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JButton MoviesButton;
    private javax.swing.JButton SalasButton;
    private javax.swing.JButton SettingsButton;
    private javax.swing.JButton SuscripcionesButton;
    private javax.swing.JPanel dinamicPanel;
    private GUIresources.RoundPanel2 drawerButton;
    private javax.swing.JLabel exitLabel;
    private GUIresources.RoundPanel2 exitPanel;
    // End of variables declaration//GEN-END:variables

}
