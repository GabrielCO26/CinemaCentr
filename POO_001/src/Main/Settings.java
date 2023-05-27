package Main;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Settings extends javax.swing.JPanel {

    private MainFrame MainFrame;
    private User loggedInUser;
    LoginSystem LoginSystem = new LoginSystem();
    List<User> userList = LoginSystem.getUserList();

    String ChangeName;

    public Settings(MainFrame MainFrame) {
        this.MainFrame = MainFrame;
        LoginSystem.readUsers();

        initComponents();
        ImageLabel.setBounds(6, 6, 258, 258);
        ImagePanel.add(ImageLabel);
        setImageLabel(ImageLabel, "CinemaCentr/usuarios/" + MainFrame.loggedInUser.getUsername() + "/UserImage.png");

        UserField.setText(MainFrame.loggedInUser.getUsername());
        CineField.setText(MainFrame.Cine.getName());

        this.setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BgPanel = new GUIresources.RoundPanel2();
        DeleteAccountButton = new javax.swing.JButton();
        ImagePanel = new GUIresources.RoundPanel2();
        ImageLabel = new javax.swing.JLabel();
        UserLabel1 = new javax.swing.JLabel();
        CineField = new javax.swing.JTextField();
        newUserLabel1 = new javax.swing.JLabel();
        UserField = new javax.swing.JTextField();
        UserLabel = new javax.swing.JLabel();
        ChangePFPbutton = new javax.swing.JButton();
        newUserLabel2 = new javax.swing.JLabel();

        BgPanel.setBottomLeftRadius(13);
        BgPanel.setBottomRightRadius(13);
        BgPanel.setColor(new java.awt.Color(255, 255, 255));
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DeleteAccountButton.setBackground(new java.awt.Color(232, 72, 85));
        DeleteAccountButton.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DeleteAccountButton.setForeground(new java.awt.Color(255, 255, 255));
        DeleteAccountButton.setText("Eliminar");
        DeleteAccountButton.setBorderPainted(false);
        DeleteAccountButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAccountButtonActionPerformed(evt);
            }
        });
        BgPanel.add(DeleteAccountButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, -1, -1));

        ImagePanel.setColor(new java.awt.Color(75, 0, 216));
        ImagePanel.setRadius(13);

        ImageLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanel);
        ImagePanel.setLayout(ImagePanelLayout);
        ImagePanelLayout.setHorizontalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        ImagePanelLayout.setVerticalGroup(
            ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ImagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ImageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );

        BgPanel.add(ImagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 270, 270));

        UserLabel1.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        UserLabel1.setForeground(new java.awt.Color(56, 48, 46));
        UserLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/user.png"))); // NOI18N
        UserLabel1.setText("Usuario");
        UserLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        UserLabel1.setIconTextGap(10);
        BgPanel.add(UserLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 110, -1));

        CineField.setEditable(false);
        CineField.setBackground(new java.awt.Color(242, 242, 242));
        CineField.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        CineField.setForeground(new java.awt.Color(56, 48, 46));
        CineField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CineField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        CineField.setFocusable(false);
        BgPanel.add(CineField, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, 220, 25));

        newUserLabel1.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        newUserLabel1.setForeground(new java.awt.Color(56, 48, 46));
        newUserLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cheque-de-dinero.png"))); // NOI18N
        newUserLabel1.setText("Nombre del cine");
        newUserLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        newUserLabel1.setIconTextGap(10);
        BgPanel.add(newUserLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        UserField.setEditable(false);
        UserField.setBackground(new java.awt.Color(242, 242, 242));
        UserField.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        UserField.setForeground(new java.awt.Color(56, 48, 46));
        UserField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UserField.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        UserField.setFocusable(false);
        BgPanel.add(UserField, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 220, 25));

        UserLabel.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        UserLabel.setForeground(new java.awt.Color(56, 48, 46));
        UserLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/imagen.png"))); // NOI18N
        UserLabel.setText("Imagen");
        UserLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        UserLabel.setIconTextGap(10);
        BgPanel.add(UserLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 110, -1));

        ChangePFPbutton.setBackground(new java.awt.Color(75, 0, 216));
        ChangePFPbutton.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        ChangePFPbutton.setForeground(new java.awt.Color(255, 255, 255));
        ChangePFPbutton.setText("Cambiar");
        ChangePFPbutton.setBorderPainted(false);
        ChangePFPbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ChangePFPbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangePFPbuttonActionPerformed(evt);
            }
        });
        BgPanel.add(ChangePFPbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, -1, -1));

        newUserLabel2.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        newUserLabel2.setForeground(new java.awt.Color(56, 48, 46));
        newUserLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/basura.png"))); // NOI18N
        newUserLabel2.setText("Eliminar cuenta");
        newUserLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        newUserLabel2.setIconTextGap(10);
        BgPanel.add(newUserLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(BgPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DeleteAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAccountButtonActionPerformed
        new DeleteAccount(MainFrame, true, this).setVisible(true);
    }//GEN-LAST:event_DeleteAccountButtonActionPerformed

    private void ChangePFPbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangePFPbuttonActionPerformed
        selectUserProfileImage(ImageLabel, MainFrame.loggedInUser.getUsername());
    }//GEN-LAST:event_ChangePFPbuttonActionPerformed

    public void DeleteUser() {
        getUserIndex();
        LoginSystem.removeLoggedInUser(loggedInUser);
        MainFrame.logout();
    }

    public void setImageLabel(JLabel label, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setIcon(icon);
        this.repaint();
    }

    public void selectUserProfileImage(JLabel label, String username) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione una imagen de perfil");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Imágenes", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(imageFilter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String imagePath = selectedFile.getAbsolutePath();
            saveUserProfileImage(label, imagePath, username);
        }
    }

    public void saveUserProfileImage(JLabel label, String imagePath, String username) {
        String destinationPath = "CinemaCentr/usuarios/" + username + "/UserImage.png";
        File destinationFile = new File(destinationPath);
        try {
            File selectedFile = new File(imagePath);
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            setImageLabel(label, "CinemaCentr/usuarios/" + username + "/UserImage.png");
            setImageLabel(MainFrame.ImageLabel, "CinemaCentr/usuarios/" + username + "/UserImage.png");
            System.out.println("Imagen de perfil guardada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen de perfil: " + e.getMessage());
        }
    }

    //Método para obtener la instancia "real" de User desde LoginSystem
    public void getUserIndex() {
        int loggedInUserIndex = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(MainFrame.loggedInUser.getUsername())) {
                loggedInUserIndex = i;
                break;
            }
        }
        loggedInUser = userList.get(loggedInUserIndex);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUIresources.RoundPanel2 BgPanel;
    private javax.swing.JButton ChangePFPbutton;
    private javax.swing.JTextField CineField;
    private javax.swing.JButton DeleteAccountButton;
    public static javax.swing.JLabel ImageLabel;
    private GUIresources.RoundPanel2 ImagePanel;
    private javax.swing.JTextField UserField;
    private javax.swing.JLabel UserLabel;
    private javax.swing.JLabel UserLabel1;
    private javax.swing.JLabel newUserLabel1;
    private javax.swing.JLabel newUserLabel2;
    // End of variables declaration//GEN-END:variables
}
