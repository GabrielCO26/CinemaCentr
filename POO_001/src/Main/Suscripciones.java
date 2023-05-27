package Main;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Gaco
 */
public class Suscripciones extends javax.swing.JPanel {

    private DefaultTableModel suscripcionesModel;

    private MainFrame MainFrame;
    private String username;
    String nombre, costo;
    double costoValue;
    boolean ConNombre, ConCosto;

    private SuscripcionesSystem suscripcionesSystem;
    List<Suscripcion> suscripcionesList;

    public void updateTable() {
        suscripcionesModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactivar la edición de todas las celdas
            }
        };

        suscripcionesModel.addColumn("Nombre");
        suscripcionesModel.addColumn("Costo");

        // Agregar los datos de los clientes al modelo de la tabla
        for (Suscripcion suscripcion : suscripcionesList) {
            Object[] rowData = {suscripcion.getNombre(), suscripcion.getCosto()};
            suscripcionesModel.addRow(rowData);
        }
    }

    public Suscripciones(MainFrame MainFrame) {
        this.MainFrame = MainFrame;
        username = MainFrame.loggedInUser.getUsername();
        suscripcionesSystem = new SuscripcionesSystem(username);
        suscripcionesList = suscripcionesSystem.getSuscripcionesList();
        
        updateTable();

        initComponents();

        JTableHeader header = SuscripcionesTable.getTableHeader();
        Font headerFont = new Font("Roboto", Font.PLAIN, 18);
        header.setFont(headerFont);
        SuscripcionesTable.setModel(suscripcionesModel);

        
        this.setBackground(new Color(0, 0, 0, 0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BgPanel = new GUIresources.RoundPanel2();
        ScrollPane = new javax.swing.JScrollPane();
        SuscripcionesTable = new javax.swing.JTable();
        NameField = new javax.swing.JTextField();
        CostoField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Error1Text = new javax.swing.JLabel();
        Error2Text = new javax.swing.JLabel();
        DeleteUser = new javax.swing.JButton();
        AgregarClienteLabel = new javax.swing.JLabel();
        NombreLabel = new javax.swing.JLabel();
        CorreoLabel = new javax.swing.JLabel();
        ClientesLabel = new javax.swing.JLabel();
        grayBgPanel2 = new GUIresources.RoundPanel2();

        BgPanel.setBottomLeftRadius(13);
        BgPanel.setBottomRightRadius(13);
        BgPanel.setColor(new java.awt.Color(255, 255, 255));
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SuscripcionesTable.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        SuscripcionesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        SuscripcionesTable.setFocusable(false);
        SuscripcionesTable.setSelectionBackground(new java.awt.Color(232, 72, 85));
        SuscripcionesTable.setShowHorizontalLines(true);
        ScrollPane.setViewportView(SuscripcionesTable);

        BgPanel.add(ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 540, 420));

        NameField.setBackground(new java.awt.Color(255, 255, 255));
        NameField.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        NameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NameFieldKeyTyped(evt);
            }
        });
        BgPanel.add(NameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 200, 20));

        CostoField.setBackground(new java.awt.Color(255, 255, 255));
        CostoField.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        CostoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CostoFieldKeyTyped(evt);
            }
        });
        BgPanel.add(CostoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 200, 20));

        jButton1.setBackground(new java.awt.Color(75, 0, 216));
        jButton1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Crear suscripción");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        BgPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 200, -1));

        Error1Text.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Error1Text.setForeground(new java.awt.Color(232, 72, 85));
        BgPanel.add(Error1Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 200, 20));

        Error2Text.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Error2Text.setForeground(new java.awt.Color(232, 72, 85));
        BgPanel.add(Error2Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 200, 20));

        DeleteUser.setBackground(new java.awt.Color(232, 72, 85));
        DeleteUser.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        DeleteUser.setForeground(new java.awt.Color(255, 255, 255));
        DeleteUser.setText("Eliminar suscripción");
        DeleteUser.setBorderPainted(false);
        DeleteUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        DeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteUserActionPerformed(evt);
            }
        });
        BgPanel.add(DeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 510, 200, -1));

        AgregarClienteLabel.setFont(new java.awt.Font("Roboto Medium", 0, 22)); // NOI18N
        AgregarClienteLabel.setForeground(new java.awt.Color(56, 48, 46));
        AgregarClienteLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/tarjeta-de-creditob.png"))); // NOI18N
        AgregarClienteLabel.setText("Agregar suscripción");
        AgregarClienteLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AgregarClienteLabel.setIconTextGap(10);
        BgPanel.add(AgregarClienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 240, -1));

        NombreLabel.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        NombreLabel.setForeground(new java.awt.Color(56, 48, 46));
        NombreLabel.setText("Nombre");
        NombreLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        NombreLabel.setIconTextGap(10);
        BgPanel.add(NombreLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 110, -1));

        CorreoLabel.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        CorreoLabel.setForeground(new java.awt.Color(56, 48, 46));
        CorreoLabel.setText("Costo");
        CorreoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        CorreoLabel.setIconTextGap(10);
        BgPanel.add(CorreoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 110, -1));

        ClientesLabel.setFont(new java.awt.Font("Roboto Medium", 0, 22)); // NOI18N
        ClientesLabel.setForeground(new java.awt.Color(56, 48, 46));
        ClientesLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/base-de-datos.png"))); // NOI18N
        ClientesLabel.setText("Suscripciones");
        ClientesLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ClientesLabel.setIconTextGap(10);
        BgPanel.add(ClientesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        grayBgPanel2.setBackground(new java.awt.Color(242, 242, 242));
        grayBgPanel2.setColor(new java.awt.Color(242, 242, 242));
        grayBgPanel2.setRadius(13);

        javax.swing.GroupLayout grayBgPanel2Layout = new javax.swing.GroupLayout(grayBgPanel2);
        grayBgPanel2.setLayout(grayBgPanel2Layout);
        grayBgPanel2Layout.setHorizontalGroup(
            grayBgPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 870, Short.MAX_VALUE)
        );
        grayBgPanel2Layout.setVerticalGroup(
            grayBgPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        BgPanel.add(grayBgPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 870, 540));

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

    private void NameFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NameFieldKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_NameFieldKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        nombre = NameField.getText();
        costo = CostoField.getText();

        if (nombre.equals("")) {
            Error1Text.setText("Ingrese un nombre");
            ConNombre = false;
        } else {
            Error1Text.setText(null);
            ConNombre = true;
        }

        if (costo.equals("")) {
            Error2Text.setText("Ingrese un costo");
            ConCosto = false;
        } else {
            costoValue = Double.parseDouble(costo);
            Error2Text.setText(null);
            ConCosto = true;
        }

        if (ConNombre && ConCosto) {
            suscripcionesSystem.createSuscripcion(nombre, costoValue, Error1Text);
            updateTable();
            SuscripcionesTable.setModel(suscripcionesModel);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CostoFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoFieldKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_CostoFieldKeyTyped

    private void DeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteUserActionPerformed
        // Obtener la fila seleccionada

        int selectedRow = SuscripcionesTable.getSelectedRow();
        System.out.println(selectedRow);

        // Verificar si hay una fila seleccionada
        if (selectedRow != -1) {
            // Obtener el cliente correspondiente a la fila seleccionada
            Suscripcion suscripcionSeleccionada = suscripcionesList.get(selectedRow);

            // Eliminar el cliente del arreglo y del archivo
            suscripcionesSystem.removeSuscripcion(suscripcionSeleccionada);
            suscripcionesList.remove(suscripcionSeleccionada);

            updateTable();
            SuscripcionesTable.setModel(suscripcionesModel);
            System.out.println("Suscripción eliminada");
        } else{
            System.out.println("No hay fila seleccionada");
        }
    }//GEN-LAST:event_DeleteUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AgregarClienteLabel;
    private GUIresources.RoundPanel2 BgPanel;
    private javax.swing.JLabel ClientesLabel;
    private javax.swing.JLabel CorreoLabel;
    private javax.swing.JTextField CostoField;
    private javax.swing.JButton DeleteUser;
    private javax.swing.JLabel Error1Text;
    private javax.swing.JLabel Error2Text;
    private javax.swing.JTextField NameField;
    private javax.swing.JLabel NombreLabel;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JTable SuscripcionesTable;
    private GUIresources.RoundPanel2 grayBgPanel;
    private GUIresources.RoundPanel2 grayBgPanel2;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
