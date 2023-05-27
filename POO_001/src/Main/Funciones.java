package Main;

import GUIresources.Animations;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.table.DefaultTableModel;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @Andres Chinchilla
 */
public class Funciones extends javax.swing.JPanel {

    private Set<String> uniqueRows = new HashSet<>();
    private LocalDate currentDate;
    Animations Animations = new Animations();
    private DefaultTableModel tablemodel;
    private String[] columns = new String[]{"Peliculas", "Fecha", "Valor Entrada", "Tipo Evento", "Sala", "Hora", "Sillas"};
    private Object[][] row = new Object[][]{ // Add more rows as needed
    };
    private ArrayList<Object[]> auditoriumList = new ArrayList<>();
    private java.util.List<Object[]> dataList;
    private JButton[][] seats;
    private int[][] seatStates;
    private Vector<String> listaPeliculas;
    private String usuario;

    public Funciones(Vector<String> listaPeliculas, String usuario) {
        this.listaPeliculas = listaPeliculas;
        this.usuario = usuario;
        initComponents();
        //cargar lista
        loadDataListFromFile();
        // codigo de creación de los Comboboxes de las fechas
        this.setBackground(Color.white);
        seatsPanel.setBackground(Color.white);
        currentDate = LocalDate.now();
        for (int i = currentDate.getYear(); i <= currentDate.getYear() + 100; i++) {
            yearBox.addItem(Integer.toString(i));
        }
        for (int i = 1; i <= 12; i++) {
            monthBox.addItem(Integer.toString(i));
        }
        // Convert the integer values to strings when setting the selected items
        yearBox.setSelectedItem(Integer.toString(currentDate.getYear()));
        monthBox.setSelectedItem(Integer.toString(currentDate.getMonthValue()));
        updateDayBox();
        PeliCombo.setModel(new javax.swing.DefaultComboBoxModel(listaPeliculas));
        // Agregar auditorios falsos a la lista
        auditoriumList.add(new Object[]{"Sala 1", "3", "5"});
        auditoriumList.add(new Object[]{"Sala 2", "4", "4"});
        auditoriumList.add(new Object[]{"Sala 3", "2", "6"});
        DefaultComboBoxModel<String> auditoriumModel = new DefaultComboBoxModel<>();
        for (Object[] auditorium : auditoriumList) {
            String auditoriumName = (String) auditorium[0];
            auditoriumModel.addElement(auditoriumName);
        }

        combosala.setModel(auditoriumModel);
        dataList = new java.util.ArrayList<>(java.util.Arrays.asList(row));
        tablemodel = new DefaultTableModel(row, columns);
        myTable.setModel(tablemodel);
        lablePeli1.show();
        seatsPanel.show();
        
        FuncCombo.show();
        PeliCombo.show();
        leve.show();
        lableFunc.show();
        lfecha.hide();
        laño.hide();
        lmes.hide();
        ldia.hide();
        yearBox.hide();
        monthBox.hide();
        dayBox.hide();
        lentradas.hide();
        fieldentradas.hide();
        lentradas.hide();
        fieldentradas.hide();
        lsala.hide();
        leve.hide();
        comboeve.hide();
        combosala.hide();
        lhora.hide();
        combohor.hide();
        ldospuntos.hide();
        combomin.hide();
        buttoncrear.hide();
        deleteButton.hide();
        jScrollPane1.hide();
        myTable.hide();
        updateFuncCombo();
    }

    private void loadSeatStates(int ROWS, int COLS) {
        String movie = (String) PeliCombo.getSelectedItem();
        String function = (String) FuncCombo.getSelectedItem();

        if (movie != null && function != null) {
            String filename = "CinemaCentr/usuarios/" + usuario +"/" +movie + "_" + function + ".txt";

            // Replace any invalid characters in the filename
            filename = filename.replaceAll("[^a-zA-Z0-9_\\.\\-]", "_");

            File file = new File(filename);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    for (int row = 0; row < ROWS; row++) {
                        String line = reader.readLine();
                        if (line != null) {
                            String[] values = line.trim().split(",");
                            for (int col = 0; col < COLS && col < values.length; col++) {
                                seatStates[row][col] = Integer.parseInt(values[col]);
                                seats[row][col].setBackground(seatStates[row][col] == 1 ? Color.RED : null);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Initialize the seatStates array with all default values (0)
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLS; col++) {
                        seatStates[row][col] = 0;
                    }
                }
            }
        }
    }

    private void reserveSeats(int ROWS, int COLS) {
        String movie = (String) PeliCombo.getSelectedItem();
        String function = (String) FuncCombo.getSelectedItem();

        if (movie != null && function != null) {
            String filename = "CinemaCentr/usuarios/" + usuario +"/" +movie + "_" + function + ".txt";

            // Replace any invalid characters in the filename
            filename = filename.replaceAll("[^a-zA-Z0-9_\\.\\-]", "_");
            File file = new File(filename);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (int row = 0; row < ROWS; row++) {
                    for (int col = 0; col < COLS; col++) {
                        writer.write(seatStates[row][col] + ",");
                    }
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private int[] getAuditoriumData(String auditoriumName) {
        for (Object[] auditoriumData : auditoriumList) {
            if (auditoriumData[0].equals(auditoriumName)) {
                return new int[]{Integer.parseInt((String) auditoriumData[1]), Integer.parseInt((String) auditoriumData[2])};
            }
        }
        return null;
    }

    private String createFunctionId(String date, String time, String auditorium) {
        return date + "_" + time + "_" + auditorium;
    }

    private int getAvailableSeats(String auditoriumName) {
        for (Object[] auditorium : auditoriumList) {
            if (auditorium[0].equals(auditoriumName)) {
                int rows = Integer.parseInt((String) auditorium[1]);
                int cols = Integer.parseInt((String) auditorium[2]);
                return rows * cols;
            }
        }
        return 0; // Retorna 0 si no se encuentra el auditorio
    }

    private void updateRowFromDataList() {
        row = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            row[i] = dataList.get(i);
        }
    }

    private void updateFuncCombo() {
        String selectedMovie = (String) PeliCombo.getSelectedItem();
        FuncCombo.removeAllItems();

        for (Object[] row : row) {
            String movie = (String) row[0];
            if (selectedMovie.equals(movie)) {
                String date = (String) row[1];
                String time = (String) row[5];
                String sala = (String) row[4];
                FuncCombo.addItem(date + "/" + time + "/" + sala);
            }
        }
        if (FuncCombo.getItemListeners().length == 0) {
            FuncCombo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        String selectedFunction = (String) e.getItem();
                        String[] functionParts = selectedFunction.split(" - ");
                        String date = functionParts[0];
                        String time = functionParts[1];
                        String auditorium = functionParts[2];
                        String functionId = createFunctionId(date, time, auditorium);

                    }
                }
            });
        }
    }

    private void saveDataListToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CinemaCentr/usuarios/" + usuario +"/dataList.txt"))) {
            for (Object[] row : dataList) {
                writer.write(String.join(",", Arrays.stream(row).map(Object::toString).collect(Collectors.toList())));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataListFromFile() {
        File file = new File("CinemaCentr/usuarios/" + usuario +"/dataList.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                java.util.List<Object[]> tempDataList = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    String[] rowData = line.split(",");
                    tempDataList.add(rowData);
                }
                dataList = tempDataList;
                updateRowFromDataList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            dataList = new ArrayList<>();
            updateRowFromDataList();
        }
    }

    public void updateTableModel(java.util.List<Object[]> updatedList) {
        Object[][] dow = new Object[updatedList.size()][];
        for (int i = 0; i < updatedList.size(); i++) {
            dow[i] = updatedList.get(i);
        }
        tablemodel = new DefaultTableModel(dow, columns);
        myTable.setModel(tablemodel);
    }

    private void updateDayBox() {
        Object selectedYear = yearBox.getSelectedItem();
        Object selectedMonth = monthBox.getSelectedItem();

        // Check if the selected items are not null before parsing them to integers
        if (selectedYear == null || selectedMonth == null) {
            return;
        }

        int year = Integer.parseInt((String) selectedYear);
        int month = Integer.parseInt((String) selectedMonth);

        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInThisMonth = yearMonth.lengthOfMonth();
        int previousSelection = 1; // Default value
        Object selectedDay = dayBox.getSelectedItem();
        if (selectedDay != null) {
            previousSelection = Integer.parseInt((String) selectedDay);
        }
        dayBox.removeAllItems();
        for (int i = 1; i <= daysInThisMonth; i++) {
            if (year == currentDate.getYear() && month == currentDate.getMonthValue() && i < currentDate.getDayOfMonth()) {
                continue;
            }
            dayBox.addItem(String.valueOf(i));
        }
        if (previousSelection >= dayBox.getItemCount()) {
            dayBox.setSelectedIndex(dayBox.getItemCount() - 1);
        } else {
            dayBox.setSelectedItem(String.valueOf(previousSelection));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        seatsPanel = new javax.swing.JPanel();
        PeliCombo = new javax.swing.JComboBox<>();
        FuncCombo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        Reservar = new javax.swing.JButton();
        Crear = new javax.swing.JButton();
        informa = new javax.swing.JButton();
        leve = new javax.swing.JLabel();
        lableFunc = new javax.swing.JLabel();
        yearBox = new javax.swing.JComboBox<>();
        monthBox = new javax.swing.JComboBox<>();
        dayBox = new javax.swing.JComboBox<>();
        ldia = new javax.swing.JLabel();
        laño = new javax.swing.JLabel();
        lmes = new javax.swing.JLabel();
        lfecha = new javax.swing.JLabel();
        lentradas = new javax.swing.JLabel();
        fieldentradas = new javax.swing.JTextField();
        comboeve = new javax.swing.JComboBox<>();
        combosala = new javax.swing.JComboBox<>();
        lsala = new javax.swing.JLabel();
        lablePeli1 = new javax.swing.JLabel();
        lhora = new javax.swing.JLabel();
        combohor = new javax.swing.JComboBox<>();
        combomin = new javax.swing.JComboBox<>();
        ldospuntos = new javax.swing.JLabel();
        buttoncrear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        myTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seatsPanel.setLayout(new java.awt.GridLayout(1, 0));
        add(seatsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 450, 430));

        PeliCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PeliComboActionPerformed(evt);
            }
        });
        add(PeliCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 290, -1));

        FuncCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuncComboActionPerformed(evt);
            }
        });
        add(FuncCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 290, -1));

        jLabel1.setText("FUNCIONES");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        Reservar.setText("RESERVAR");
        Reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReservarActionPerformed(evt);
            }
        });
        add(Reservar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, -1, -1));

        Crear.setText("CREAR");
        Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearActionPerformed(evt);
            }
        });
        add(Crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, -1));

        informa.setText("INFORMACIÓN");
        informa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informaActionPerformed(evt);
            }
        });
        add(informa, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        leve.setText("Selecciona el evento");
        add(leve, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 160, -1));

        lableFunc.setText("Selecciona la función");
        add(lableFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 130, -1));

        yearBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearBoxActionPerformed(evt);
            }
        });
        add(yearBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, -1, -1));

        monthBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBoxActionPerformed(evt);
            }
        });
        add(monthBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        add(dayBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, -1, -1));

        ldia.setText("Dia:");
        add(ldia, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, -1, -1));

        laño.setText("Año:");
        add(laño, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        lmes.setText("Mes:");
        add(lmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        lfecha.setText("Seleccione la fecha");
        add(lfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 120, -1));

        lentradas.setText("Digite el valor de las entradas");
        add(lentradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 210, -1));

        fieldentradas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldentradasKeyTyped(evt);
            }
        });
        add(fieldentradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, 220, -1));

        comboeve.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D", "SUB-2D", "SUB-3D", "4D" }));
        add(comboeve, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 170, -1));

        add(combosala, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 170, -1));

        lsala.setText("Seleccione la sala");
        add(lsala, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 220, -1));

        lablePeli1.setText("Selecciona la pelicula");
        add(lablePeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 160, -1));

        lhora.setText("Seleccione la hora del evento");
        add(lhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, -1, -1));

        combohor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        add(combohor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 360, -1, -1));

        combomin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        add(combomin, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 360, -1, -1));

        ldospuntos.setText(":");
        add(ldospuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 20, -1));

        buttoncrear.setText("Agregar función");
        buttoncrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoncrearActionPerformed(evt);
            }
        });
        add(buttoncrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        myTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Pelicula", "Fecha", "Valor Entrada", "Tipo Evento", "Sala", "Hora", "Sillas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(myTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1000, 410));

        deleteButton.setText("Eliminar");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearActionPerformed
        lablePeli1.show();
        seatsPanel.hide();
        FuncCombo.hide();
        PeliCombo.show();
        leve.show();
        lableFunc.hide();
        lfecha.show();
        laño.show();
        lmes.show();
        ldia.show();
        yearBox.show();
        monthBox.show();
        dayBox.show();
        lentradas.show();
        fieldentradas.show();
        lentradas.show();
        fieldentradas.show();
        lsala.show();
        leve.show();
        comboeve.show();
        combosala.show();
        lhora.show();
        combohor.show();
        ldospuntos.show();
        combomin.show();
        buttoncrear.show();
        deleteButton.hide();
        jScrollPane1.hide();
        myTable.hide();
    }//GEN-LAST:event_CrearActionPerformed

    private void ReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReservarActionPerformed
        lablePeli1.show();
        seatsPanel.show();
        FuncCombo.show();
        PeliCombo.show();
        leve.show();
        lableFunc.show();
        lfecha.hide();
        laño.hide();
        lmes.hide();
        ldia.hide();
        yearBox.hide();
        monthBox.hide();
        dayBox.hide();
        lentradas.hide();
        fieldentradas.hide();
        lentradas.hide();
        fieldentradas.hide();
        lsala.hide();
        leve.hide();
        comboeve.hide();
        combosala.hide();
        lhora.hide();
        combohor.hide();
        ldospuntos.hide();
        combomin.hide();
        buttoncrear.hide();
        deleteButton.hide();
        jScrollPane1.hide();
        myTable.hide();
        updateFuncCombo();
    }//GEN-LAST:event_ReservarActionPerformed

    private void informaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_informaActionPerformed
        lablePeli1.hide();
        seatsPanel.hide();
        FuncCombo.hide();
        PeliCombo.hide();
        leve.hide();
        lableFunc.hide();
        lfecha.hide();
        laño.hide();
        lmes.hide();
        ldia.hide();
        yearBox.hide();
        monthBox.hide();
        dayBox.hide();
        lentradas.hide();
        fieldentradas.hide();
        lentradas.hide();
        fieldentradas.hide();
        lsala.hide();
        leve.hide();
        comboeve.hide();
        combosala.hide();
        lhora.hide();
        combohor.hide();
        ldospuntos.hide();
        combomin.hide();
        buttoncrear.hide();
        deleteButton.show();
        jScrollPane1.show();
        myTable.show();
    }//GEN-LAST:event_informaActionPerformed

    private void yearBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearBoxActionPerformed
        updateDayBox();
    }//GEN-LAST:event_yearBoxActionPerformed

    private void monthBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBoxActionPerformed
        updateDayBox();
    }//GEN-LAST:event_monthBoxActionPerformed

    private void fieldentradasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldentradasKeyTyped
        int key = evt.getKeyChar();
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldentradasKeyTyped

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int selectedRow = myTable.getSelectedRow();
        if (selectedRow != -1) {
            // remove the selected row from the table model
            tablemodel.removeRow(selectedRow);
            // remove the corresponding object from the dataList
            dataList.remove(selectedRow);
            // update the row variable from the dataList
            updateRowFromDataList();
            saveDataListToFile();
            JOptionPane.showMessageDialog(null, "Borrado correctamente");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed


    private void buttoncrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoncrearActionPerformed
        // crear
        System.out.println("crear");
        String year = yearBox.getSelectedItem().toString();
        String month = monthBox.getSelectedItem().toString();
        String day = dayBox.getSelectedItem().toString();
        String date = year + "-" + month + "-" + day;

        String hour = combohor.getSelectedItem().toString();
        String minutes = combomin.getSelectedItem().toString();
        String time = hour + ":" + minutes;

        int availableSeats = getAvailableSeats(combosala.getSelectedItem().toString());

        Object[] newRow = {
            PeliCombo.getSelectedItem(),
            date,
            fieldentradas.getText(),
            comboeve.getSelectedItem(),
            combosala.getSelectedItem(),
            time,
            availableSeats
        };

        // Check if any element in newRow is null
        for (Object element : newRow) {
            if (element == null) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty or incorrect. Please check your input.");
                return;
            }
        }

        // Check if newRow already exists in uniqueRows
        String newRowString = java.util.Arrays.toString(newRow);
        if (uniqueRows.contains(newRowString)) {
            JOptionPane.showMessageDialog(null, "This entry already exists. Please check your input.");
            return;
        }

        // Add newRow to uniqueRows
        uniqueRows.add(newRowString);

        Object[][] updatedRow;
        if (row.length == 0) {
            // If row is empty, create a new 2D array with a single row
            updatedRow = new Object[1][];
            updatedRow[0] = newRow;
        } else {
            // Create a new 2D array with an additional row and copy the existing 'row' array into it
            updatedRow = new Object[row.length + 1][row[0].length];
            for (int i = 0; i < row.length; i++) {
                System.arraycopy(row[i], 0, updatedRow[i], 0, row[i].length);
            }
            // Add the new movie information to the last row of the new 2D array
            updatedRow[updatedRow.length - 1] = newRow;
        }

        // Update the 'row' array to point to the new 2D array
        row = updatedRow;
        dataList = new java.util.ArrayList<>(java.util.Arrays.asList(updatedRow));
        updateTableModel(dataList);
        saveDataListToFile();
    }//GEN-LAST:event_buttoncrearActionPerformed


    private void FuncComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuncComboActionPerformed
        if (FuncCombo.getSelectedItem() != null) {

            String functionId = (String) FuncCombo.getSelectedItem();
            String[] functionParts = functionId.split("/");
            String auditorium = functionParts[2];
            int[] result = getAuditoriumData(auditorium);
            if (result != null) {
                int x = result[0];
                int b = result[1];

                // Initialize the arrays before using them
                seatStates = new int[x][b];
                seats = new JButton[x][b];

                // Remove all previous components from seatsPanel
                seatsPanel.removeAll();
                seatsPanel.setLayout(new GridLayout(x, b));

                for (int row = 0; row < x; row++) {
                    for (int col = 0; col < b; col++) {
                        JButton seatButton = new JButton(row + "," + col);
                        seats[row][col] = seatButton;
                        seatsPanel.add(seatButton);

                        int finalRow = row;
                        int finalCol = col;
                        seatButton.addActionListener(e -> {
                            if (seatStates[finalRow][finalCol] == 0) {
                                seatStates[finalRow][finalCol] = 1;
                                seatButton.setBackground(Color.RED);
                                reserveSeats(x, b);
                            }
                        });
                    }
                }

                // Call loadSeatStates after creating the buttons
                loadSeatStates(x, b);

                // Revalidate and repaint the seatsPanel
                seatsPanel.revalidate();
                seatsPanel.repaint();
            }
        }

    }//GEN-LAST:event_FuncComboActionPerformed

    private void PeliComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PeliComboActionPerformed
        updateFuncCombo();
    }//GEN-LAST:event_PeliComboActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Crear;
    private javax.swing.JComboBox<String> FuncCombo;
    private javax.swing.JComboBox<String> PeliCombo;
    private javax.swing.JButton Reservar;
    private javax.swing.JButton buttoncrear;
    private javax.swing.JComboBox<String> comboeve;
    private javax.swing.JComboBox<String> combohor;
    private javax.swing.JComboBox<String> combomin;
    private javax.swing.JComboBox<String> combosala;
    private javax.swing.JComboBox<String> dayBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField fieldentradas;
    private javax.swing.JButton informa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lableFunc;
    private javax.swing.JLabel lablePeli1;
    private javax.swing.JLabel laño;
    private javax.swing.JLabel ldia;
    private javax.swing.JLabel ldospuntos;
    private javax.swing.JLabel lentradas;
    private javax.swing.JLabel leve;
    private javax.swing.JLabel lfecha;
    private javax.swing.JLabel lhora;
    private javax.swing.JLabel lmes;
    private javax.swing.JLabel lsala;
    private javax.swing.JComboBox<String> monthBox;
    private javax.swing.JTable myTable;
    private javax.swing.JPanel seatsPanel;
    private javax.swing.JComboBox<String> yearBox;
    // End of variables declaration//GEN-END:variables
}
