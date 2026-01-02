/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import controller.AppointmentController;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.DoctorAvailability;

/**
 *
 * @author amritchand
 */
public class AppointmentUi extends javax.swing.JFrame {

    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem bookAppointmentItem;
    private int clickedRow = -1;

    private static final java.util.logging.Logger logger = java.util.logging.Logger
            .getLogger(AppointmentUi.class.getName());

    /**
     * Creates new form AppointmentUi
     */
    public AppointmentUi() {
        initComponents();
        setLocationRelativeTo(null);
        setupPopupMenu();
        addTableMouseListener();
        loadAppointmentTable();

    }

    private void setupPopupMenu() {
        popupMenu = new JPopupMenu();
        bookAppointmentItem = new JMenuItem("Book Appointment");

        bookAppointmentItem.setFont(new Font("Segoe UI", Font.BOLD, 14));
        bookAppointmentItem.setBackground(new Color(0, 125, 234));
        bookAppointmentItem.setForeground(Color.WHITE);
        bookAppointmentItem.setOpaque(true);

        popupMenu.add(bookAppointmentItem);
        bookAppointmentItem.addActionListener(e -> openBooking());
    }

    private void addTableMouseListener() {
        jTable1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(evt)) {
                    clickedRow = jTable1.rowAtPoint(evt.getPoint());
                    if (clickedRow >= 0) {
                        openBooking();
                    }
                    return;
                }

                if (SwingUtilities.isRightMouseButton(evt)) {
                    clickedRow = jTable1.rowAtPoint(evt.getPoint());
                    if (clickedRow >= 0) {
                        jTable1.setRowSelectionInterval(clickedRow, clickedRow);
                        popupMenu.show(jTable1, evt.getX(), evt.getY());
                    }
                }
            }
        });
    }

    public void loadAppointmentTable() {
        try {
            AppointmentController controller = new AppointmentController();
            List<DoctorAvailability> list = controller.getAllAvailability();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm");

            for (DoctorAvailability d : list) {
                model.addRow(new Object[] {
                        d.getId(),
                        d.getName(),
                        d.getSpecialization(),
                        df.format(d.getAvailableDate()),
                        tf.format(d.getAvailableTime()),
                        d.getEmail()
                });
            }

            if (jTable1.getColumnModel().getColumnCount() >= 6) {
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
                jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
                jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
            }

            jTable1.setDefaultEditor(Object.class, null);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setRowSelectionAllowed(true);
            jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading appointments: " + e.getMessage());
        }
    }

    // BOOKING ACTION
    private void openBooking() {
        if (clickedRow == -1)
            return;

        int appointmentId = Integer.parseInt(jTable1.getValueAt(clickedRow, 0).toString());
        String doctorName = jTable1.getValueAt(clickedRow, 1).toString();
        String department = jTable1.getValueAt(clickedRow, 2).toString();
        String dateStr = jTable1.getValueAt(clickedRow, 3).toString();
        String time = jTable1.getValueAt(clickedRow, 4).toString();
        String doctorEmail = jTable1.getValueAt(clickedRow, 5).toString();

        try {
            // Convert String date back to Date
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date date = df.parse(dateStr);
            BookAppointment bookDash = new BookAppointment(appointmentId, doctorName, doctorEmail, department, date,
                    time);
            bookDash.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to open booking: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        goToDashboardBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jLabel2.setBackground(new java.awt.Color(240, 230, 230));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CLINIC MANAGEMENT SYSTEM");

        goToDashboardBtn.setBackground(new java.awt.Color(255, 153, 153));
        goToDashboardBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        goToDashboardBtn.setText("Go To Dashboard ");
        goToDashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToDashboardBtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Appointment ID", "Doctor Name", "Department", "Date", "Time", "Doctor Email "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(goToDashboardBtn)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(304, 304, 304))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(goToDashboardBtn)
                .addGap(0, 74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goToDashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goToDashboardBtnActionPerformed
        // TODO add your handling code here:
        PatientDashUi pWin = new PatientDashUi();
        pWin.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_goToDashboardBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new AppointmentUi().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton goToDashboardBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
