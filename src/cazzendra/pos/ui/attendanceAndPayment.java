/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.AttendanceV3Controller;
import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.lecturerController;
import cazzendra.pos.control.studentController;
import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.core.Validations;
import cazzendra.pos.model.lecturer;
import cazzendra.pos.model.student;
import java.awt.Color;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amal
 */
public class attendanceAndPayment extends javax.swing.JFrame {
    /**
     * Creates new form addStudent
     */
    public attendanceAndPayment() {
        initComponents();
        loadDataToTable();
        calculateFeeTotal();
        loadLecturerCode();
        txtAttendedStudentCount.setText(Integer.toString(tblAttendanceV3.getRowCount()));
        System.out.println("rc : " + tblAttendanceV3.getRowCount());
    }

    private void clearAll() {
        txtClassFee.setText("");
        txtStudentCode.setText("");
    }

    private void markAttendanceByCode(String studenCode) {
        try {
            if (txtStudentCode.getText().trim().length() < 3) {
                JOptionPane.showMessageDialog(this, "Please enter valid code !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String prefixCode = studenCode.substring(0, 2);
            String studentCode = studenCode.substring(2);
            student student = studentController.getStudentByStudentId(Validations.getIntOrZeroFromString(studentCode));
            lecturer lecturer = lecturerController.getLecturerByPrefixCode(prefixCode);

            if (!lecturerController.validatePrefixCode(prefixCode)) {
                JOptionPane.showMessageDialog(this, "Invalid lecture prefix code !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (student == null) {
                JOptionPane.showMessageDialog(this, "Invalid student code !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            AttendanceV3Controller.addAttendance(prefixCode, studentCode, CommonController.getCurrentJavaSqlDate(),
                    Validations.getBigDecimalOrZeroFromString(txtClassFee.getText().trim()), "", lecturer.getName(), student.getName());

            clearAll();
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataToTable() {
        try {
            ResultSet rset = AttendanceV3Controller.getAll();
            String[] columnList = {"id", "att_date", "lec_code", "lecturer_name", "student_code", "student_name", "fee", "remark"};
            CommonController.loadDataToTable(tblAttendanceV3, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void calculateFeeTotal() {
        BigDecimal feeTotal = BigDecimal.ZERO;
        DefaultTableModel dtm = (DefaultTableModel) tblAttendanceV3.getModel();
        for (int i = 0; i < dtm.getRowCount(); i++) {
            BigDecimal lineTotal = Validations.getBigDecimalOrZeroFromString(dtm.getValueAt(i, 6).toString());
            feeTotal = feeTotal.add(lineTotal);
        }
        txtFeeTotal.setText(Validations.formatWithTwoDigits(feeTotal.toString()));
    }

    private void advanceSearch() {
        try {
            DefaultTableModel dtm = (DefaultTableModel) tblAttendanceV3.getModel();
            dtm.setRowCount(0);

            ArrayList<String[]> attributeConditionValueList = new ArrayList<>();

            if (dateChooserFromDate.getDate() != null) {
                String[] ACV1 = {"att_date", CommonConstants.sql.EQUAL,
                    Validations.getSqlDateByUtilDate(dateChooserFromDate.getDate()).toString()};
                attributeConditionValueList.add(ACV1);
            }

            if (!comboLecturerPrefixCode.getSelectedItem().toString().equalsIgnoreCase("All")) {
                String[] ACV2 = {"lec_code", CommonConstants.sql.EQUAL, comboLecturerPrefixCode.getSelectedItem().toString()};
                attributeConditionValueList.add(ACV2);
            }

            ResultSet rset = AttendanceV3Controller.getByMoreAttributes(attributeConditionValueList, CommonConstants.sql.AND);

            String[] columnList = {"id", "att_date", "lec_code", "lecturer_name", "student_code", "student_name", "fee", "remark"};
            CommonController.loadDataToTable(tblAttendanceV3, rset, columnList);

            txtAttendedStudentCount.setText(Integer.toString(tblAttendanceV3.getRowCount()));

        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLecturerCode() {
        try {
            ResultSet rset = lecturerController.getAllLecturers();
            CommonController.loadDataToComboBox(comboLecturerPrefixCode, rset, "lecturer_prefix_code");
            comboLecturerPrefixCode.addItem("All");
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
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

        PanelMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAttendanceV3 = new javax.swing.JTable();
        PanelSub = new javax.swing.JPanel();
        txtStudentCode = new javax.swing.JTextField();
        btSave = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtClassFee = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        dateChooserFromDate = new com.toedter.calendar.JDateChooser();
        btSave1 = new javax.swing.JButton();
        comboLecturerPrefixCode = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtFeeTotal = new javax.swing.JTextField();
        txtAttendedStudentCount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attendance & Payment");
        setResizable(false);

        PanelMain.setBackground(new java.awt.Color(0, 0, 102));

        tblAttendanceV3.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tblAttendanceV3.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblAttendanceV3.getTableHeader().setOpaque(false);
        tblAttendanceV3.getTableHeader().setBackground(new Color(0, 0, 102));
        tblAttendanceV3.getTableHeader().setForeground(new Color(255, 255, 255));
        tblAttendanceV3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Date", "Lec. Code", "Lec. Name", "Stu. Code", "Stu. Name", "Fee", "Detail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAttendanceV3.setRowHeight(20);
        tblAttendanceV3.setRowMargin(2);
        tblAttendanceV3.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblAttendanceV3);
        if (tblAttendanceV3.getColumnModel().getColumnCount() > 0) {
            tblAttendanceV3.getColumnModel().getColumn(0).setMinWidth(0);
            tblAttendanceV3.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblAttendanceV3.getColumnModel().getColumn(0).setMaxWidth(0);
            tblAttendanceV3.getColumnModel().getColumn(1).setMinWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(1).setMaxWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(2).setMinWidth(80);
            tblAttendanceV3.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblAttendanceV3.getColumnModel().getColumn(2).setMaxWidth(80);
            tblAttendanceV3.getColumnModel().getColumn(3).setResizable(false);
            tblAttendanceV3.getColumnModel().getColumn(4).setMinWidth(80);
            tblAttendanceV3.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblAttendanceV3.getColumnModel().getColumn(4).setMaxWidth(80);
            tblAttendanceV3.getColumnModel().getColumn(5).setResizable(false);
            tblAttendanceV3.getColumnModel().getColumn(6).setMinWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(6).setMaxWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(7).setMinWidth(0);
            tblAttendanceV3.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblAttendanceV3.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        PanelSub.setBackground(new java.awt.Color(51, 51, 255));

        txtStudentCode.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtStudentCode.setToolTipText("");
        txtStudentCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtStudentCode.setSelectionColor(new java.awt.Color(255, 255, 0));

        btSave.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave.setForeground(new java.awt.Color(255, 255, 255));
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/addItem.png"))); // NOI18N
        btSave.setToolTipText("");
        btSave.setBorder(null);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Lecture Code and Student Code");

        txtClassFee.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtClassFee.setToolTipText("");
        txtClassFee.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtClassFee.setSelectionColor(new java.awt.Color(255, 255, 0));

        jLabel21.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Class Fee");

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(txtStudentCode, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(txtClassFee, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(498, Short.MAX_VALUE))
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtStudentCode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClassFee, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Class Date");

        btSave1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave1.setForeground(new java.awt.Color(255, 255, 255));
        btSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/search_32x32.png"))); // NOI18N
        btSave1.setToolTipText("");
        btSave1.setBorder(null);
        btSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSave1ActionPerformed(evt);
            }
        });

        comboLecturerPrefixCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Total Student Count");

        jLabel27.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Lecturer");

        jLabel28.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Fee Total ");

        txtFeeTotal.setEditable(false);
        txtFeeTotal.setBackground(new java.awt.Color(255, 255, 0));
        txtFeeTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtFeeTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtFeeTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtAttendedStudentCount.setEditable(false);
        txtAttendedStudentCount.setBackground(new java.awt.Color(255, 255, 0));
        txtAttendedStudentCount.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtAttendedStudentCount.setForeground(new java.awt.Color(255, 0, 0));
        txtAttendedStudentCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateChooserFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboLecturerPrefixCode, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PanelSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAttendedStudentCount, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFeeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateChooserFromDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSave1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboLecturerPrefixCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAttendedStudentCount, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtFeeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        markAttendanceByCode(txtStudentCode.getText().trim());
        loadDataToTable();
        calculateFeeTotal();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSave1ActionPerformed
        advanceSearch();
        calculateFeeTotal();
    }//GEN-LAST:event_btSave1ActionPerformed

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
            java.util.logging.Logger.getLogger(attendanceAndPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(attendanceAndPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(attendanceAndPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(attendanceAndPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new attendanceAndPayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelSub;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btSave1;
    private javax.swing.JComboBox<String> comboLecturerPrefixCode;
    private com.toedter.calendar.JDateChooser dateChooserFromDate;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAttendanceV3;
    private javax.swing.JTextField txtAttendedStudentCount;
    private javax.swing.JTextField txtClassFee;
    private javax.swing.JTextField txtFeeTotal;
    private javax.swing.JTextField txtStudentCode;
    // End of variables declaration//GEN-END:variables
}
