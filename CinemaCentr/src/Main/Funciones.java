package Main;

import GUIresources.Animations;
import java.awt.Color;
import java.awt.Font;
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
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.table.JTableHeader;

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
    private java.util.List<Object[]> dataList;
    private JButton[][] seats;
    private int[][] seatStates;
    private Vector<String> listaPeliculas;
    private String usuario;
    private SalasSystem salasSystem;
    List<Sala> salasList;

    public Funciones(Vector<String> listaPeliculas, String usuario) {
        this.listaPeliculas = listaPeliculas;
        this.usuario = usuario;
        salasSystem = new SalasSystem(usuario);
        salasList = salasSystem.getSalasList();

        initComponents();
        //cargar lista
        loadDataListFromFile();
        // codigo de creación de los Comboboxes de las fechas
        this.setBackground(new Color(0, 0, 0, 0));
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

        // Agregar las salas 
        DefaultComboBoxModel<String> auditoriumModel = new DefaultComboBoxModel<>();
        for (Sala sala : salasList) {
            String auditoriumName = (String) sala.getNombre();
            auditoriumModel.addElement(auditoriumName);
        }
        combosala.setModel(auditoriumModel);

        dataList = new java.util.ArrayList<>(java.util.Arrays.asList(row));
        tablemodel = new DefaultTableModel(row, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactivar la edición de todas las celdas
            }
        };
        myTable.setModel(tablemodel);
        JTableHeader header = myTable.getTableHeader();
        Font headerFont = new Font("Roboto", Font.PLAIN, 18);
        header.setFont(headerFont);
        lablePeli1.show();
        seatsPanel.show();
        Bg1Panel.hide();
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
            String ruta = "CinemaCentr/usuarios/" + usuario + "/funciones/";
            String filename = usuario + "/" + movie + "_" + function + ".txt";

            // Replace any invalid characters in the filename
            filename = filename.replaceAll("[^a-zA-Z0-9_\\.\\-]", "_");

            File file = new File(ruta + filename);

            if (file.exists()) {
                try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
            String ruta = "CinemaCentr/usuarios/" + usuario + "/funciones/";
            String filename = usuario + "/" + movie + "_" + function + ".txt";

            // Replace any invalid characters in the filename
            filename = filename.replaceAll("[^a-zA-Z0-9_\\.\\-]", "_");

            File file = new File(ruta + filename);

            try ( BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
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
        for (Sala sala : salasList) {
            if (sala.getNombre().equals(auditoriumName)) {
                return new int[]{sala.getRows(), sala.getColumns()};
            }
        }
        return null;
    }

    private String createFunctionId(String date, String time, String auditorium) {
        return date + "_" + time + "_" + auditorium;
    }

    private int getAvailableSeats(String auditoriumName) {
        for (Sala sala : salasList) {
            if (sala.getNombre().equals(auditoriumName)) {
                int rows = sala.getRows();
                int cols = sala.getColumns();
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
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("CinemaCentr/usuarios/" + usuario + "/funciones/dataList.txt"))) {
            for (Object[] row : dataList) {
                writer.write(String.join(",", Arrays.stream(row).map(Object::toString).collect(Collectors.toList())));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataListFromFile() {
        File file = new File("CinemaCentr/usuarios/" + usuario + "/funciones/dataList.txt");
        if (file.exists()) {
            try ( BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
        Error1Text = new javax.swing.JLabel();
        Error2Text = new javax.swing.JLabel();
        Bg1Panel = new GUIresources.RoundPanel2();
        Bg2Panel = new GUIresources.RoundPanel2();
        BgRoundPanel = new GUIresources.RoundPanel2();

        setBackground(new java.awt.Color(0, 0, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seatsPanel.setLayout(new java.awt.GridLayout(1, 0));
        add(seatsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 450, 430));

        PeliCombo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        PeliCombo.setForeground(new java.awt.Color(56, 48, 46));
        PeliCombo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PeliCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PeliComboActionPerformed(evt);
            }
        });
        add(PeliCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 290, -1));

        FuncCombo.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        FuncCombo.setForeground(new java.awt.Color(56, 48, 46));
        FuncCombo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        FuncCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuncComboActionPerformed(evt);
            }
        });
        add(FuncCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 290, -1));

        Reservar.setBackground(new java.awt.Color(75, 0, 216));
        Reservar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Reservar.setForeground(new java.awt.Color(255, 255, 255));
        Reservar.setText("RESERVAR");
        Reservar.setBorderPainted(false);
        Reservar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReservarActionPerformed(evt);
            }
        });
        add(Reservar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 535, -1, -1));

        Crear.setBackground(new java.awt.Color(75, 0, 216));
        Crear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Crear.setForeground(new java.awt.Color(255, 255, 255));
        Crear.setText("CREAR");
        Crear.setBorderPainted(false);
        Crear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearActionPerformed(evt);
            }
        });
        add(Crear, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 535, -1, -1));

        informa.setBackground(new java.awt.Color(75, 0, 216));
        informa.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        informa.setForeground(new java.awt.Color(255, 255, 255));
        informa.setText("INFORMACIÓN");
        informa.setBorderPainted(false);
        informa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        informa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                informaActionPerformed(evt);
            }
        });
        add(informa, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 535, -1, -1));

        leve.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        leve.setForeground(new java.awt.Color(56, 48, 46));
        leve.setText("Selecciona el evento");
        add(leve, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 170, -1));

        lableFunc.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lableFunc.setForeground(new java.awt.Color(56, 48, 46));
        lableFunc.setText("Selecciona la función");
        add(lableFunc, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 180, -1));

        yearBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        yearBox.setForeground(new java.awt.Color(56, 48, 46));
        yearBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        yearBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearBoxActionPerformed(evt);
            }
        });
        add(yearBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 270, -1, -1));

        monthBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        monthBox.setForeground(new java.awt.Color(56, 48, 46));
        monthBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        monthBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBoxActionPerformed(evt);
            }
        });
        add(monthBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, -1, -1));

        dayBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        dayBox.setForeground(new java.awt.Color(56, 48, 46));
        dayBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(dayBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, -1, -1));

        ldia.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        ldia.setForeground(new java.awt.Color(56, 48, 46));
        ldia.setText("Dia:");
        add(ldia, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 270, -1, -1));

        laño.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        laño.setForeground(new java.awt.Color(56, 48, 46));
        laño.setText("Año:");
        add(laño, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 270, -1, -1));

        lmes.setFont(new java.awt.Font("Roboto", 0, 16)); // NOI18N
        lmes.setForeground(new java.awt.Color(56, 48, 46));
        lmes.setText("Mes:");
        add(lmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        lfecha.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lfecha.setForeground(new java.awt.Color(56, 48, 46));
        lfecha.setText("Seleccione la fecha");
        add(lfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 160, -1));

        lentradas.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lentradas.setForeground(new java.awt.Color(56, 48, 46));
        lentradas.setText("Digite el valor de las entradas");
        add(lentradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 240, -1));

        fieldentradas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        fieldentradas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldentradasKeyTyped(evt);
            }
        });
        add(fieldentradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 220, -1));

        comboeve.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        comboeve.setForeground(new java.awt.Color(56, 48, 46));
        comboeve.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2D", "3D", "SUB-2D", "SUB-3D", "4D" }));
        comboeve.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(comboeve, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, 170, -1));

        combosala.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        combosala.setForeground(new java.awt.Color(56, 48, 46));
        combosala.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(combosala, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, 170, -1));

        lsala.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lsala.setForeground(new java.awt.Color(56, 48, 46));
        lsala.setText("Seleccione la sala");
        add(lsala, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 220, -1));

        lablePeli1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lablePeli1.setForeground(new java.awt.Color(56, 48, 46));
        lablePeli1.setText("Selecciona la pelicula");
        add(lablePeli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 180, -1));

        lhora.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lhora.setForeground(new java.awt.Color(56, 48, 46));
        lhora.setText("Seleccione la hora del evento");
        add(lhora, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 330, -1, -1));

        combohor.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        combohor.setForeground(new java.awt.Color(56, 48, 46));
        combohor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        combohor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(combohor, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 370, -1, -1));

        combomin.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        combomin.setForeground(new java.awt.Color(56, 48, 46));
        combomin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55" }));
        combomin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(combomin, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        ldospuntos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        ldospuntos.setForeground(new java.awt.Color(56, 48, 46));
        ldospuntos.setText(":");
        add(ldospuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, 20, -1));

        buttoncrear.setBackground(new java.awt.Color(75, 0, 216));
        buttoncrear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        buttoncrear.setForeground(new java.awt.Color(255, 255, 255));
        buttoncrear.setText("Agregar función");
        buttoncrear.setBorderPainted(false);
        buttoncrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttoncrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoncrearActionPerformed(evt);
            }
        });
        add(buttoncrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, -1, -1));

        myTable.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
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
        myTable.setSelectionBackground(new java.awt.Color(232, 72, 85));
        myTable.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(myTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 940, 450));

        deleteButton.setBackground(new java.awt.Color(232, 72, 85));
        deleteButton.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("Eliminar función");
        deleteButton.setBorderPainted(false);
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 535, -1, -1));

        Error1Text.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Error1Text.setForeground(new java.awt.Color(232, 72, 85));
        add(Error1Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 395, 200, 20));

        Error2Text.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        Error2Text.setForeground(new java.awt.Color(232, 72, 85));
        add(Error2Text, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 295, 200, 20));

        Bg1Panel.setColor(new java.awt.Color(242, 242, 242));
        Bg1Panel.setRadius(13);
        add(Bg1Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 960, 470));

        Bg2Panel.setColor(new java.awt.Color(242, 242, 242));
        Bg2Panel.setRadius(13);
        add(Bg2Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 460, 460));

        BgRoundPanel.setBackground(new java.awt.Color(0, 0, 0));
        BgRoundPanel.setBottomLeftRadius(13);
        BgRoundPanel.setBottomRightRadius(13);
        BgRoundPanel.setColor(new java.awt.Color(0, 0, 0));
        add(BgRoundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 570));
    }// </editor-fold>//GEN-END:initComponents

    private void CrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearActionPerformed
        Bg1Panel.show();
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
        Error1Text.setText(null);
        Bg1Panel.hide();
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
        Error1Text.setText(null);
        Bg1Panel.show();
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
            JOptionPane.showMessageDialog(null, "Función eliminada correctamente");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed


    private void buttoncrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoncrearActionPerformed
        // crear
        if (fieldentradas.getText().equals("") || combosala.getSelectedIndex() == -1) {
            if (fieldentradas.getText().equals("")) {
                Error1Text.setText("Ingrese el valor de la entrada");

            }

            if (combosala.getSelectedIndex() == -1) {
                Error2Text.setText("No hay sala seleccionada");
            }

            if (!fieldentradas.getText().equals("") && combosala.getSelectedIndex() == -1) {
                Error1Text.setText(null);
                Error2Text.setText("No hay sala seleccionada");
            }
        } else {
            Error1Text.setText(null);
            Error2Text.setText(null);
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
                    JOptionPane.showMessageDialog(null, "Uno o más campos están vacíos. Compruebe los datos introducidos.");
                    return;
                }
            }

            // Check if newRow already exists in uniqueRows
            String newRowString = java.util.Arrays.toString(newRow);
            if (uniqueRows.contains(newRowString)) {
                JOptionPane.showMessageDialog(null, "Esta función ya existe. Verifique las opciones");
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
            JOptionPane.showMessageDialog(null, "Función agregada correctamente.");
        }
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
    private GUIresources.RoundPanel2 Bg1Panel;
    private GUIresources.RoundPanel2 Bg2Panel;
    private GUIresources.RoundPanel2 BgRoundPanel;
    private javax.swing.JButton Crear;
    private javax.swing.JLabel Error1Text;
    private javax.swing.JLabel Error2Text;
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
