/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.PaymentController;
import cazzendra.pos.control.studentController;
import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.core.Loading;
import cazzendra.pos.core.Validations;
import cazzendra.pos.daoImpl.PaymentDaoImpl;
import cazzendra.pos.model.student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author personal
 */
public class Payment extends javax.swing.JDialog {

    private int studentId;
    private int subId;
    private String grade;
    private String subName;
    private String lecturerName;
    private int lecturerId;

    private student student;

    boolean legalClose = false;

    /**
     * Creates new form editBatch
     *
     * @param parent
     * @param modal
     * @param studentPrimaryKey
     */
    public Payment(java.awt.Frame parent, boolean modal, int studentPrimaryKey) {
        super(parent, modal);
        initComponents();
        studentId = studentPrimaryKey;
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
        radioMonthly.setSelected(true);
    }

    public Payment(java.awt.Frame parent, boolean modal, int studentPrimaryKey,
            int subjectId, String selectedGrade, String subjectName, int lecturerId, String lecturerName) {
        super(parent, modal);
        initComponents();
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
        studentId = studentPrimaryKey;
        radioMonthly.setSelected(true);
        this.subId = subjectId;
        this.grade = selectedGrade;
        this.subName = subjectName;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;

        try {
            if (isMonthlyPaymentCleared()) {
                radioMonthly.setSelected(true);
//                radioMonthly.setEnabled(false);
//                radioDaily.setEnabled(false);
//                comboPaymentYear.setEnabled(false);
//                comboPaymentMonth.setEnabled(false);
//                txtPayment.setEnabled(false);
                txtPayment.setText("PAID FOR THIS MONTH");

            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            student = studentController.getStudentByStudentId(studentId);
            lblStudentName.setText(student.getName());
        } catch (SQLException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rset = new PaymentDaoImpl().GetPaymentByAttribute("payment_student_id",
                    CommonConstants.sql.EQUAL, Integer.toString(student.getId()));
            /*
            payment_id, , payment_is_monthly_payment, , , 
            , payment_date, payment_class, payment_status, payment_detail,
            payment_subject_id, , payment_lecturer_id, payment_lecturer_name
             */
            String columnList[] = {"payment_student_id", "payment_for_month", "payment_for_year", "payment_amount", "payment_subject_name"};
            CommonController.loadDataToTable(tblPaymentHistory, rset, columnList);

        } catch (SQLException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (student.getStatus() == 0) {
            lblFreeCard.setText("FREE CARD");
            radioMonthly.setEnabled(false);
            radioDaily.setEnabled(false);
            comboPaymentYear.setEnabled(false);
            comboPaymentMonth.setEnabled(false);
            txtPayment.setEnabled(false);
        } else {
            lblFreeCard.setText("");
        }

    }

    private void AddPayment() {
        int PaymentType = 0;
        if (radioMonthly.isSelected()) {
            PaymentType = 1;
        } else if (radioDaily.isSelected()) {
            PaymentType = 2;
        }
        if (txtPayment.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Enter payment amount correctly !");
            return;
        }

        try {

            if (!isMonthlyPaymentCleared()) {
                PaymentController.AddPayment(studentId, comboPaymentMonth.getSelectedItem().toString(),
                        comboPaymentYear.getSelectedItem().toString(),
                        subId, grade, "", 1, subName,
                        Validations.getBigDecimalOrZeroFromString(txtPayment.getText().trim()),
                        lecturerId, lecturerName, PaymentType);
            }

            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isMonthlyPaymentCleared() throws SQLException {
        return PaymentController.IsMonthlyPaymentCleared(studentId, comboPaymentYear.getSelectedItem().toString(),
                comboPaymentMonth.getSelectedItem().toString(), grade, 1, subId, lecturerId);
    }

    private boolean isDayPaymentCleared() throws SQLException {
        return PaymentController.IsMonthlyPaymentCleared(studentId, comboPaymentYear.getSelectedItem().toString(),
                comboPaymentMonth.getSelectedItem().toString(), grade, 2, subId, lecturerId);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        PanelMain = new javax.swing.JPanel();
        PanelSub = new javax.swing.JPanel();
        btSave2 = new javax.swing.JButton();
        txtPayment = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        radioDaily = new javax.swing.JRadioButton();
        radioMonthly = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        comboPaymentYear = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        lblStudentName = new javax.swing.JLabel();
        comboPaymentMonth = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaymentHistory = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblFreeCard = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Payment");

        PanelMain.setBackground(new java.awt.Color(0, 0, 102));

        PanelSub.setBackground(new java.awt.Color(0, 0, 102));
        PanelSub.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btSave2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave2.setForeground(new java.awt.Color(255, 255, 255));
        btSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/saveIcon.png"))); // NOI18N
        btSave2.setToolTipText("Update Sudent");
        btSave2.setBorder(null);
        btSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSave2ActionPerformed(evt);
            }
        });
        PanelSub.add(btSave2, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 367, -1, -1));

        txtPayment.setBackground(new java.awt.Color(153, 0, 51));
        txtPayment.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        txtPayment.setForeground(new java.awt.Color(0, 255, 0));
        PanelSub.add(txtPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 305, 300, 50));

        jLabel28.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 102));
        jLabel28.setText("Paid Amount");
        PanelSub.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 285, -1, -1));

        radioDaily.setBackground(new java.awt.Color(0, 0, 102));
        buttonGroup1.add(radioDaily);
        radioDaily.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        radioDaily.setForeground(new java.awt.Color(255, 255, 255));
        radioDaily.setText("Day Payment");
        radioDaily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioDailyActionPerformed(evt);
            }
        });
        PanelSub.add(radioDaily, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 52, -1, -1));

        radioMonthly.setBackground(new java.awt.Color(0, 0, 102));
        buttonGroup1.add(radioMonthly);
        radioMonthly.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        radioMonthly.setForeground(new java.awt.Color(255, 255, 255));
        radioMonthly.setText("Monthly Payment");
        radioMonthly.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMonthlyActionPerformed(evt);
            }
        });
        PanelSub.add(radioMonthly, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 52, -1, -1));

        jLabel19.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 102));
        jLabel19.setText("Rs.");
        PanelSub.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 315, -1, -1));

        comboPaymentYear.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboPaymentYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        comboPaymentYear.setToolTipText("Month");
        PanelSub.add(comboPaymentYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 138, 277, -1));

        jLabel23.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 102));
        jLabel23.setText("Payment Year");
        PanelSub.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 115, -1, -1));

        lblStudentName.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        lblStudentName.setForeground(new java.awt.Color(0, 0, 102));
        lblStudentName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStudentName.setText("Name");
        PanelSub.add(lblStudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 595, 50));

        comboPaymentMonth.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboPaymentMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboPaymentMonth.setToolTipText("Month");
        PanelSub.add(comboPaymentMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 217, 277, -1));

        tblPaymentHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Month", "Year", "Amount", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPaymentHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPaymentHistory);
        if (tblPaymentHistory.getColumnModel().getColumnCount() > 0) {
            tblPaymentHistory.getColumnModel().getColumn(0).setMinWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(1).setMinWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(1).setMaxWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(2).setMinWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(2).setMaxWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(3).setMinWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(3).setMaxWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(4).setResizable(false);
        }

        PanelSub.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(371, 147, 595, 270));

        jLabel25.setFont(new java.awt.Font("Ubuntu Medium", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 102));
        jLabel25.setText("Payment History");
        PanelSub.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 110, -1, 33));

        jLabel26.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 102));
        jLabel26.setText("Payment Month");
        PanelSub.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 194, -1, -1));

        lblFreeCard.setFont(new java.awt.Font("Ubuntu Medium", 1, 24)); // NOI18N
        lblFreeCard.setForeground(new java.awt.Color(255, 0, 51));
        lblFreeCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFreeCard.setText("FREE CARD");
        PanelSub.add(lblFreeCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 595, 33));

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSave2ActionPerformed

        try {
            if (isMonthlyPaymentCleared()) {
                radioMonthly.setSelected(true);
                int option = JOptionPane.showConfirmDialog(this, "Paid for this month ! Do you want to change payment details?");
                if (option == JOptionPane.NO_OPTION) {
                    this.dispose();
                }
            } else {
                if (student.getStatus() != 0) {
                    AddPayment();
                } else {
                    this.dispose();
                }
                legalClose = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSave2ActionPerformed

    private void radioDailyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioDailyActionPerformed
        txtPayment.setText(null);
    }//GEN-LAST:event_radioDailyActionPerformed

    private void radioMonthlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMonthlyActionPerformed
        txtPayment.setText(null);
    }//GEN-LAST:event_radioMonthlyActionPerformed

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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Payment dialog = new Payment(new javax.swing.JFrame(), true, 0);
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
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelSub;
    private javax.swing.JButton btSave2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboPaymentMonth;
    private javax.swing.JComboBox<String> comboPaymentYear;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFreeCard;
    private javax.swing.JLabel lblStudentName;
    private javax.swing.JRadioButton radioDaily;
    private javax.swing.JRadioButton radioMonthly;
    private javax.swing.JTable tblPaymentHistory;
    private javax.swing.JTextField txtPayment;
    // End of variables declaration//GEN-END:variables
}
