package Main;

import GUIresources.Animations;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Salas extends javax.swing.JPanel {

    private DefaultTableModel salasModel;
    private String username;

    String name, rowsString, colsString;
    int rows, columns;
    boolean ConNombre, ConFilas, ConColumnas;

    private SalasSystem salasSystem;
    List<Sala> salasList;

    public void updateTable() {
        salasModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactivar la edición de todas las celdas
            }
        };

        salasModel.addColumn("Nombre");
        salasModel.addColumn("Filas");
        salasModel.addColumn("Columnas");

        Collections.sort(salasList, Comparator.comparing(Sala::getNombre));

        // Agregar los datos de los clientes al modelo de la tabla
        for (Sala sala : salasList) {
            Object[] rowData = {sala.getNombre(), sala.getRows(), sala.getColumns()};
            salasModel.addRow(rowData);
        }
    }

    public Salas(String usuario) {
        this.username = usuario;
        salasSystem = new SalasSystem(username);
        salasList = salasSystem.getSalasList();

        updateTable();

        initComponents();

        JTableHeader header = SalasTable.getTableHeader();
        Font headerFont = new Font("Roboto", Font.PLAIN, 18);
        header.setFont(headerFont);
        SalasTable.setModel(salasModel);

        this.setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BgPanel = new GUIresources.RoundPanel2();
        ScrollPane = new javax.swing.JScrollPane();
        SalasTable = new javax.swing.JTable();
        EliminarSalaButton = new javax.swing.JButton();
        AgregarSalaButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        NameSalaField = new javax.swing.JTextField();
        Error1Text = new javax.swing.JLabel();
        AgregarClienteLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        NumFilField = new javax.swing.JTextField();
        Error2Text = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NumColField = new javax.swing.JTextField();
        Error3Text = new javax.swing.JLabel();

        BgPanel.setBackground(new java.awt.Color(0, 0, 0));
        BgPanel.setBottomLeftRadius(13);
        BgPanel.setBottomRightRadius(13);
        BgPanel.setColor(new java.awt.Color(0, 0, 0));
        BgPanel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        BgPanel.setInheritsPopupMenu(true);
        BgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SalasTable.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        SalasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Filas", "Columnas"
            }
        ));
        SalasTable.setSelectionBackground(new java.awt.Color(232, 72, 85));
        SalasTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        SalasTable.setShowHorizontalLines(true);
        ScrollPane.setViewportView(SalasTable);

        BgPanel.add(ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 600, 370));

        EliminarSalaButton.setBackground(new java.awt.Color(232, 72, 85));
        EliminarSalaButton.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        EliminarSalaButton.setForeground(new java.awt.Color(255, 255, 255));
        EliminarSalaButton.setText("Eliminar Sala");
        EliminarSalaButton.setBorderPainted(false);
        EliminarSalaButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        EliminarSalaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarSalaButtonActionPerformed(evt);
            }
        });
        BgPanel.add(EliminarSalaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, 140, 30));

        AgregarSalaButton.setBackground(new java.awt.Color(75, 0, 216));
        AgregarSalaButton.setFont(new java.awt.Font("Lato", 1, 18)); // NOI18N
        AgregarSalaButton.setForeground(new java.awt.Color(255, 255, 255));
        AgregarSalaButton.setText("Añadir Sala");
        AgregarSalaButton.setBorderPainted(false);
        AgregarSalaButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AgregarSalaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarSalaButtonActionPerformed(evt);
            }
        });
        BgPanel.add(AgregarSalaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 190, 30));

        jLabel4.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nombre de la Sala");
        jLabel4.setMaximumSize(new java.awt.Dimension(120, 18));
        jLabel4.setMinimumSize(new java.awt.Dimension(120, 18));
        jLabel4.setPreferredSize(new java.awt.Dimension(120, 18));
        BgPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 150, 20));

        NameSalaField.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        NameSalaField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NameSalaField.setMaximumSize(new java.awt.Dimension(120, 18));
        NameSalaField.setMinimumSize(new java.awt.Dimension(120, 18));
        NameSalaField.setPreferredSize(new java.awt.Dimension(120, 18));
        BgPanel.add(NameSalaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 190, 20));

        Error1Text.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Error1Text.setForeground(new java.awt.Color(232, 72, 85));
        BgPanel.add(Error1Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 210, 20));

        AgregarClienteLabel.setFont(new java.awt.Font("Lato", 1, 28)); // NOI18N
        AgregarClienteLabel.setForeground(new java.awt.Color(255, 255, 255));
        AgregarClienteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AgregarClienteLabel.setText("Salas");
        AgregarClienteLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        AgregarClienteLabel.setIconTextGap(10);
        BgPanel.add(AgregarClienteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 100, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );

        BgPanel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 10, 570));

        jLabel2.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Filas de Asientos");
        jLabel2.setMaximumSize(new java.awt.Dimension(120, 18));
        jLabel2.setMinimumSize(new java.awt.Dimension(120, 18));
        jLabel2.setPreferredSize(new java.awt.Dimension(120, 18));
        BgPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 150, 20));

        NumFilField.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        NumFilField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NumFilField.setMaximumSize(new java.awt.Dimension(120, 18));
        NumFilField.setMinimumSize(new java.awt.Dimension(120, 18));
        NumFilField.setPreferredSize(new java.awt.Dimension(120, 18));
        NumFilField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NumFilFieldKeyTyped(evt);
            }
        });
        BgPanel.add(NumFilField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 190, 20));

        Error2Text.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Error2Text.setForeground(new java.awt.Color(232, 72, 85));
        BgPanel.add(Error2Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 210, 20));

        jLabel3.setFont(new java.awt.Font("Lato", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Columnas de Asientos");
        jLabel3.setMaximumSize(new java.awt.Dimension(120, 18));
        jLabel3.setMinimumSize(new java.awt.Dimension(120, 18));
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 18));
        BgPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 190, 20));

        NumColField.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        NumColField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NumColField.setMaximumSize(new java.awt.Dimension(120, 18));
        NumColField.setMinimumSize(new java.awt.Dimension(120, 18));
        NumColField.setPreferredSize(new java.awt.Dimension(120, 18));
        NumColField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NumColFieldKeyTyped(evt);
            }
        });
        BgPanel.add(NumColField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 190, 20));

        Error3Text.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Error3Text.setForeground(new java.awt.Color(232, 72, 85));
        BgPanel.add(Error3Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 190, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BgPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AgregarSalaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarSalaButtonActionPerformed
        name = NameSalaField.getText();
        rowsString = NumFilField.getText();
        colsString = NumColField.getText();

        if (name.equals("")) {
            Error1Text.setText("Ingrese un nombre");
            ConNombre = false;
        } else {
            Error1Text.setText(null);
            ConNombre = true;
        }

        if (rowsString.equals("")) {
            Error2Text.setText("Ingrese un número de filas");
            ConFilas = false;
        } else {
            Error2Text.setText(null);
            rows = Integer.parseInt(NumFilField.getText());
            ConFilas = true;
        }

        if (colsString.equals("")) {
            Error3Text.setText("Ingrese un número de columnas");
            ConColumnas = false;
        } else {
            Error3Text.setText(null);
            columns = Integer.parseInt(NumColField.getText());
            ConColumnas = true;
        }

        if (ConNombre && ConFilas && ConColumnas) {
            salasSystem.createSalas(name, rows, columns, Error1Text, NameSalaField, NumFilField, NumColField);
            updateTable();
            SalasTable.setModel(salasModel);
        }
    }//GEN-LAST:event_AgregarSalaButtonActionPerformed

    private void EliminarSalaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarSalaButtonActionPerformed
        // Obtener la fila seleccionada
        int selectedRow = SalasTable.getSelectedRow();

        // Verificar si hay una fila seleccionada
        if (selectedRow != -1) {
            // Obtener la sala correspondiente a la fila seleccionada
            Sala salaSeleccionada = salasList.get(selectedRow);

            // Eliminar la sala del arreglo y del archivo
            salasSystem.removeSala(salaSeleccionada);
            salasList.remove(salaSeleccionada);

            updateTable();
            SalasTable.setModel(salasModel);
        }
    }//GEN-LAST:event_EliminarSalaButtonActionPerformed

    private void NumFilFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumFilFieldKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_NumFilFieldKeyTyped

    private void NumColFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NumColFieldKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_NumColFieldKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AgregarClienteLabel;
    private javax.swing.JButton AgregarSalaButton;
    private GUIresources.RoundPanel2 BgPanel;
    private javax.swing.JButton EliminarSalaButton;
    private javax.swing.JLabel Error1Text;
    private javax.swing.JLabel Error2Text;
    private javax.swing.JLabel Error3Text;
    private javax.swing.JTextField NameSalaField;
    private javax.swing.JTextField NumColField;
    private javax.swing.JTextField NumFilField;
    private javax.swing.JTable SalasTable;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
