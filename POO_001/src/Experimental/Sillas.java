import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sillas extends JFrame {

    private int rows;
    private int cols;

    // Mover la declaración de las variables ImageIcon al ámbito de la clase
    private ImageIcon availableIcon;
    private ImageIcon selectedIcon;
    private ImageIcon occupiedIcon;

    public enum SeatState {
        AVAILABLE, SELECTED, OCCUPIED
    }

    public Sillas(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initUI();
    }

    private void initUI() {
        setTitle("Cinema Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel seatsPanel = new JPanel(new GridLayout(rows, cols));
        JLabel[][] seatLabels = new JLabel[rows][cols];
        SeatState[][] seatStates = new SeatState[rows][cols];

        // Establecer un tamaño mínimo para el panel de asientos y el marco
        seatsPanel.setMinimumSize(new Dimension(300, 300));
        setMinimumSize(new Dimension(400, 400));

        seatsPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Verificar si el panel de asientos tiene dimensiones válidas antes de escalar las imágenes
                if (seatsPanel.getWidth() > 0 && seatsPanel.getHeight() > 0) {
                    int labelWidth = seatsPanel.getWidth() / cols;
                    int labelHeight = seatsPanel.getHeight() / rows;
                    
                    Image availableImage = new ImageIcon("java/prueba/Cinemaapp/chair.png").getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    availableIcon = new ImageIcon(availableImage);

                    Image selectedImage = new ImageIcon("java/prueba/Cinemaapp/chairseleccionada.png").getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    selectedIcon = new ImageIcon(selectedImage);

                    Image occupiedImage = new ImageIcon("java/prueba/Cinemaapp/chairocupada.png").getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
                    occupiedIcon = new ImageIcon(occupiedImage);

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            if (seatLabels[i][j] == null) {
                                seatLabels[i][j] = new JLabel(availableIcon);
                                seatStates[i][j] = SeatState.AVAILABLE;

                                int row = i;
                                int col = j;
                                seatLabels[i][j].addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        if (seatStates[row][col] == SeatState.AVAILABLE) {
                                            seatLabels[row][col].setIcon(selectedIcon);
                                            seatStates[row][col] = SeatState.SELECTED;
                                        } else if (seatStates[row][col] == SeatState.SELECTED) {
                                            seatLabels[row][col].setIcon(availableIcon);
                                            seatStates[row][col] = SeatState.AVAILABLE;
                                        }
                                    }
                                });

                                seatsPanel.add(seatLabels[i][j]);
                            } else {
                                if (seatStates[i][j] == SeatState.AVAILABLE) {
                                    seatLabels[i][j].setIcon(availableIcon);
                                } else if (seatStates[i][j] == SeatState.SELECTED) {
                                    seatLabels[i][j].setIcon(selectedIcon);
                                } else {
                                    seatLabels[i][j].setIcon(occupiedIcon);
                                }
                            }
                        }
                    }
                }
            }
        });

        JButton reserveButton = new JButton("Reserve Seats");
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (seatStates[i][j] == SeatState.SELECTED) {
                            seatLabels[i][j].setIcon(occupiedIcon);
                            seatStates[i][j] = SeatState.OCCUPIED;
                        }
                    }
                }
            }
        });

        add(seatsPanel, BorderLayout.CENTER);
        add(reserveButton, BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new Sillas(20, 20); // Cambia el número de filas y columnas aquí
            ex.setVisible(true);
        });
    }
}
