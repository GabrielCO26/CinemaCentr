package Main;

import java.awt.Color;

/**
 *
 * @author Gaco
 */
public class Splash extends javax.swing.JDialog {

    public Splash(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setBackground(new Color(0,0,0,0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo1 = new GUIresources.RoundPanel();
        Exit_Panel = new javax.swing.JPanel();
        Exit_label = new javax.swing.JLabel();
        VEGA = new javax.swing.JLabel();
        Imagotipo = new javax.swing.JLabel();
        Estado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Fondo1.setBackground(new java.awt.Color(153, 255, 51));
        Fondo1.setColor(new java.awt.Color(153, 0, 255));
        Fondo1.setMinimumSize(new java.awt.Dimension(480, 350));
        Fondo1.setPreferredSize(new java.awt.Dimension(480, 350));
        Fondo1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Exit_Panel.setBackground(new java.awt.Color(153, 0, 255));
        Exit_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Exit_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Exit_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Exit_PanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Exit_PanelMousePressed(evt);
            }
        });

        Exit_label.setFont(new java.awt.Font("Roboto Medium", 0, 20)); // NOI18N
        Exit_label.setForeground(new java.awt.Color(255, 255, 255));
        Exit_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Exit_label.setText("X");

        javax.swing.GroupLayout Exit_PanelLayout = new javax.swing.GroupLayout(Exit_Panel);
        Exit_Panel.setLayout(Exit_PanelLayout);
        Exit_PanelLayout.setHorizontalGroup(
            Exit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Exit_PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Exit_label, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );
        Exit_PanelLayout.setVerticalGroup(
            Exit_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Exit_label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        Fondo1.add(Exit_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 30, 30));

        VEGA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/VEGA 70x25.png"))); // NOI18N
        Fondo1.add(VEGA, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 33));

        Imagotipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Imagotipo 300x203.png"))); // NOI18N
        Fondo1.add(Imagotipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 300, 230));

        Estado.setFont(new java.awt.Font("Lato", 1, 22)); // NOI18N
        Estado.setForeground(new java.awt.Color(255, 255, 255));
        Estado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Estado.setText("Referencia");
        Fondo1.add(Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 270, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Fondo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    carga("Cargando Interfaz...");
                    carga("Cargando Archivos...");
                    carga("Inicializando...");
                    dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }//GEN-LAST:event_formWindowOpened

    private void Exit_PanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit_PanelMousePressed
        System.exit(0);
    }//GEN-LAST:event_Exit_PanelMousePressed

    private void Exit_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit_PanelMouseExited
        Exit_Panel.setBackground(new Color(75, 0, 216));
    }//GEN-LAST:event_Exit_PanelMouseExited

    private void Exit_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Exit_PanelMouseEntered
        Exit_Panel.setBackground(new Color(48, 0, 140));
    }//GEN-LAST:event_Exit_PanelMouseEntered

    private void carga(String Text) throws Exception {
        Estado.setText(Text);
        Thread.sleep(1000);
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
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Splash dialog = new Splash(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Estado;
    private javax.swing.JPanel Exit_Panel;
    private javax.swing.JLabel Exit_label;
    private GUIresources.RoundPanel Fondo1;
    private javax.swing.JLabel Imagotipo;
    private javax.swing.JLabel VEGA;
    // End of variables declaration//GEN-END:variables
}
