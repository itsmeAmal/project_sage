/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.commonConstants;
import cazzendra.pos.control.lecturerController;
import cazzendra.pos.control.subjectController;
import cazzendra.pos.core.Loading;
import cazzendra.pos.core.Validations;
import cazzendra.pos.daoImpl.AttendanceDaoImpl;
import cazzendra.pos.model.student;
import cazzendra.pos.model.subject;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class managePayments extends javax.swing.JFrame {

    subject subject = null;
    student Student = null;
    int subjectId = 0;
    int StudentId = 0;

    /**
     * Creates new form addStudent
     */
    public managePayments() {
        initComponents();
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
        LoadDataToTable();
        LoadLecturersToComboBox();
        LoadSubjectsToComboBox();
    }

    private void LoadDataToTable() {
        try {
            String[] ColumnList = {"attendance_id", "attendance_date", "attendance_student_id",
                "attendance_student_name", "attendance_subject_id", "attendance_subject_name",
                "attendance_lecturer_id", "attendance_lecturer_name", "attendance_day_payment",
                "is_monthly_payment", "attendance_paid_for_month", "attendance_paid_for_year"};
            ResultSet Rset = new AttendanceDaoImpl().GetAllAttendanceRecord();
            CommonController.loadDataToTable(tblPaymentHistory, Rset, ColumnList);
        } catch (SQLException ex) {
            Logger.getLogger(managePayments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadLecturersToComboBox() {
        try {
            ResultSet Rset = lecturerController.getAllLecturers();
            CommonController.loadDataToComboBox(comboLecturerName, Rset, "lecturer_name");
        } catch (SQLException ex) {
            Logger.getLogger(managePayments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadSubjectsToComboBox() {
        try {
            ResultSet Rset = subjectController.getAllSubjects();
            CommonController.loadDataToComboBox(comboSubjectName, Rset, "subject_name");
        } catch (SQLException ex) {
            Logger.getLogger(managePayments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadDataToTableByAtributes() {
        try {
            String[] ColumnList = {"attendance_id", "attendance_date", "attendance_student_id",
                "attendance_student_name", "attendance_subject_id", "attendance_subject_name",
                "attendance_lecturer_id", "attendance_lecturer_name", "attendance_day_payment",
                "is_monthly_payment", "attendance_paid_for_month", "attendance_paid_for_year"};

            ArrayList<String[]> attributeConditionValueList = new ArrayList<>();

            String[] arr1 = {"attendance_paid_for_year", commonConstants.Sql.EQUAL, comboPaymentYear.getSelectedItem().toString()};
            attributeConditionValueList.add(arr1);

            String[] arr2 = {"attendance_paid_for_month", commonConstants.Sql.EQUAL, comboPaymentMonth.getSelectedItem().toString()};
            attributeConditionValueList.add(arr2);

            String[] arr3 = {"attendance_lecturer_name", commonConstants.Sql.EQUAL, comboLecturerName.getSelectedItem().toString()};
            attributeConditionValueList.add(arr3);

            String[] arr4 = {"attendance_subject_name", commonConstants.Sql.EQUAL, comboSubjectName.getSelectedItem().toString()};
            attributeConditionValueList.add(arr4);

            ResultSet Rset = new AttendanceDaoImpl().GetattendanceByMoreAttributes(attributeConditionValueList, commonConstants.Sql.AND);
            CommonController.loadDataToTable(tblPaymentHistory, Rset, ColumnList);
        } catch (SQLException ex) {
            Logger.getLogger(managePayments.class.getName()).log(Level.SEVERE, null, ex);
        }
        CalculateTotalFees();
    }

    private void CalculateTotalFees() {
        BigDecimal Total = BigDecimal.ZERO;
        BigDecimal LineTotal = BigDecimal.ZERO;
        DefaultTableModel Dtm = (DefaultTableModel) tblPaymentHistory.getModel();
        for (int i = 0; i < Dtm.getRowCount(); i++) {
            LineTotal = Validations.getBigDecimalOrZeroFromString(tblPaymentHistory.getValueAt(i, 8).toString());
            Total = Total.add(LineTotal);
        }
        txtPayment.setText(Validations.formatWithTwoDigits(Total.toString()));
    }

    private void CalculateCommissionForLecturer() {
        BigDecimal Total = Validations.getBigDecimalOrZeroFromString(txtPayment.getText().trim());
        BigDecimal CommissionRate = Validations.getBigDecimalOrZeroFromString(txtCommissionRate.getText().trim());
        BigDecimal OutPut = (Total.multiply(CommissionRate)).divide(new BigDecimal("100"));
        lblCommission.setText(Validations.formatWithTwoDigits(OutPut.toString()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendarBeanInfo1 = new com.toedter.calendar.JCalendarBeanInfo();
        buttonGroup1 = new javax.swing.ButtonGroup();
        PanelMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaymentHistory = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        PanelSub = new javax.swing.JPanel();
        comboLecturerName = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        comboPaymentMonth = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        comboPaymentYear = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        comboSubjectName = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblCommission = new javax.swing.JLabel();
        txtPayment = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtCommissionRate = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Payment & Attendance History");
        setMaximumSize(new java.awt.Dimension(1340, 642));
        setMinimumSize(new java.awt.Dimension(1340, 642));
        setPreferredSize(new java.awt.Dimension(1340, 642));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelMain.setMaximumSize(new java.awt.Dimension(1340, 642));
        PanelMain.setMinimumSize(new java.awt.Dimension(1340, 642));
        PanelMain.setPreferredSize(new java.awt.Dimension(1340, 642));
        PanelMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblPaymentHistory.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tblPaymentHistory.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblPaymentHistory.getTableHeader().setOpaque(false);
        tblPaymentHistory.getTableHeader().setBackground(new Color(0, 0, 102));
        tblPaymentHistory.getTableHeader().setForeground(new Color(255, 255, 255));
        tblPaymentHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Attendance ID", "Date", "Student ID", "Student ", "Subject ID", "Subject ", "Lecturer ID", "Lecturer ", "Day Pay", "Is Monthly Pay", "Month", "Year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPaymentHistory.setRowHeight(20);
        tblPaymentHistory.setRowMargin(2);
        tblPaymentHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblPaymentHistory);
        if (tblPaymentHistory.getColumnModel().getColumnCount() > 0) {
            tblPaymentHistory.getColumnModel().getColumn(0).setMinWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(0).setMaxWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(1).setMinWidth(110);
            tblPaymentHistory.getColumnModel().getColumn(1).setPreferredWidth(110);
            tblPaymentHistory.getColumnModel().getColumn(1).setMaxWidth(110);
            tblPaymentHistory.getColumnModel().getColumn(2).setMinWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(2).setMaxWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(3).setMinWidth(200);
            tblPaymentHistory.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblPaymentHistory.getColumnModel().getColumn(3).setMaxWidth(200);
            tblPaymentHistory.getColumnModel().getColumn(4).setMinWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(4).setMaxWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(6).setMinWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(6).setMaxWidth(0);
            tblPaymentHistory.getColumnModel().getColumn(7).setMinWidth(200);
            tblPaymentHistory.getColumnModel().getColumn(7).setPreferredWidth(200);
            tblPaymentHistory.getColumnModel().getColumn(7).setMaxWidth(200);
            tblPaymentHistory.getColumnModel().getColumn(8).setMinWidth(150);
            tblPaymentHistory.getColumnModel().getColumn(8).setPreferredWidth(150);
            tblPaymentHistory.getColumnModel().getColumn(8).setMaxWidth(150);
            tblPaymentHistory.getColumnModel().getColumn(9).setMinWidth(150);
            tblPaymentHistory.getColumnModel().getColumn(9).setPreferredWidth(150);
            tblPaymentHistory.getColumnModel().getColumn(9).setMaxWidth(150);
            tblPaymentHistory.getColumnModel().getColumn(10).setMinWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(10).setPreferredWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(10).setMaxWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(11).setMinWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(11).setPreferredWidth(100);
            tblPaymentHistory.getColumnModel().getColumn(11).setMaxWidth(100);
        }

        PanelMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 244, 1310, 320));

        jLabel1.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Payment History");
        PanelMain.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 146, -1));

        PanelSub.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PanelSub.setOpaque(false);

        comboLecturerName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboLecturerName.setToolTipText("Type ");

        jLabel21.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setText("Lecturer Name");

        comboPaymentMonth.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboPaymentMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        comboPaymentMonth.setToolTipText("Month");

        jLabel24.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 102));
        jLabel24.setText("Payment For Month");

        comboPaymentYear.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboPaymentYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        comboPaymentYear.setToolTipText("Month");

        jLabel23.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 102));
        jLabel23.setText("Payment For Year");

        comboSubjectName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboSubjectName.setToolTipText("Type ");

        jLabel22.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 102));
        jLabel22.setText("Subject Name");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/searchIcon.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboPaymentMonth, javax.swing.GroupLayout.Alignment.LEADING, 0, 220, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboPaymentYear, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel24))
                .addGap(40, 40, 40)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(comboSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(comboLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboLecturerName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(3, 3, 3)
                        .addComponent(comboPaymentYear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(3, 3, 3)
                        .addComponent(comboPaymentMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(4, 4, 4)
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelMain.add(PanelSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, -1, 200));

        lblCommission.setFont(new java.awt.Font("Ubuntu Medium", 0, 48)); // NOI18N
        lblCommission.setForeground(new java.awt.Color(0, 0, 102));
        lblCommission.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCommission.setText("0.00");
        lblCommission.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PanelMain.add(lblCommission, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, 290, 80));

        txtPayment.setEditable(false);
        txtPayment.setBackground(new java.awt.Color(153, 0, 51));
        txtPayment.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        txtPayment.setForeground(new java.awt.Color(0, 255, 0));
        txtPayment.setMaximumSize(new java.awt.Dimension(14, 36));
        PanelMain.add(txtPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 140, -1));

        jLabel19.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 102));
        jLabel19.setText("Rs.");
        PanelMain.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, -1, 36));

        txtCommissionRate.setBackground(new java.awt.Color(153, 0, 51));
        txtCommissionRate.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        txtCommissionRate.setForeground(new java.awt.Color(0, 255, 0));
        txtCommissionRate.setText("22");
        PanelMain.add(txtCommissionRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 39, 180, -1));

        jLabel25.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 102));
        jLabel25.setText("Total Commission");
        PanelMain.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 60, 195, -1));

        jButton2.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setText("CALCULATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        PanelMain.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, 180, 50));

        jLabel26.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 102));
        jLabel26.setText("Total ");
        PanelMain.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 170, -1));

        jLabel27.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 102));
        jLabel27.setText("%");
        PanelMain.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, -1, 30));

        jLabel28.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 102));
        jLabel28.setText("Commission Rate");
        PanelMain.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(662, 16, 170, -1));

        getContentPane().add(PanelMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 680));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoadDataToTableByAtributes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CalculateCommissionForLecturer();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(managePayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(managePayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(managePayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(managePayments.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new managePayments().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelSub;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboLecturerName;
    private javax.swing.JComboBox<String> comboPaymentMonth;
    private javax.swing.JComboBox<String> comboPaymentYear;
    private javax.swing.JComboBox<String> comboSubjectName;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JCalendarBeanInfo jCalendarBeanInfo1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCommission;
    private javax.swing.JTable tblPaymentHistory;
    private javax.swing.JTextField txtCommissionRate;
    private javax.swing.JTextField txtPayment;
    // End of variables declaration//GEN-END:variables
}
