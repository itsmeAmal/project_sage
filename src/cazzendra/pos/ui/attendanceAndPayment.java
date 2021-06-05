/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.subjectController;
import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.core.Loading;
import java.awt.Color;
import java.awt.Font;
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
//    public attendanceAndPayment() {
//        initComponents();
//        loadSubjectsToTable();
//        setDefaults();
//        txtModuleCode.setVisible(false);
//        jLabel5.setVisible(false);
//        jLabel21.setVisible(false);
//    }
//
//    private void setDefaults() {
//        txtModuleCode.setText("");
//        txtSubjectName.setText("");
//        comboSemester.setSelectedIndex(0);
//        PanelMain.setBackground(Loading.getColorCode());
//        PanelSub.setBackground(Loading.getColorCode());
//    }
//    private void loadCourseDetailsDataObjectsToComboBox() {
//        try {
//            ResultSet rset = courseController.getAllCourses();
//            String[] columnList = {"course_id", "course_name", "course_type", "course_detail", "course_satus"};
//            commonController.loadDataObjectsIntoComboBox(comboCourse, rset, columnList, "course_type");
//        } catch (SQLException ex) {
//            Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
////    }
//    private void addSubject() {
//
//        if (txtSubjectName.getText().trim().equalsIgnoreCase(null) || txtSubjectName.getText().trim().equalsIgnoreCase("")) {
//            JOptionPane.showMessageDialog(this, "Please enter subject name !", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        int option = JOptionPane.showConfirmDialog(this, "do you want to add new subject?");
//        if (option == JOptionPane.YES_OPTION) {
//            try {
//                int courseId = 0;
////                if (!comboCourse.getSelectedItem().toString().equalsIgnoreCase(subject.COMMON_SUBJECT)) {
////                    DataObject dataObjCourse = (DataObject) comboCourse.getSelectedItem();
////                    courseId = commonController.getIntOrZeroFromString(dataObjCourse.get("course_id"));
////                }
//                boolean status = subjectController.addSubject("", "", txtModuleCode.getText().trim(), txtSubjectName.getText().trim(),
//                        comboSemester.getSelectedItem().toString(), courseId);
//                if (status) {
//                    JOptionPane.showMessageDialog(this, "Subject registered successfully !");
//                    setDefaults();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(attendanceAndPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            }
//        }
//    }
    private void loadSubjectsToTable() {
        try {
            ResultSet rset = subjectController.getAllSubjects();
            String[] columnList = {"subject_id", "subject_name_concat", "subject_module_code", "subject_semester"};
            CommonController.loadDataToTable(tblHistory, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void searchSubjectByNameAndGrade() {

        try {
            String[] columnList = {"subject_id", "subject_name_concat", "subject_module_code", "subject_semester"};

            ArrayList<String[]> attributeConditionValueList = new ArrayList<>();

            String[] ACV1 = {"subject_name", CommonConstants.sql.LIKE, "%" + txtSearchValue.getText().trim() + "%"};
            attributeConditionValueList.add(ACV1);

            String[] ACV2 = {"subject_semester", CommonConstants.sql.LIKE, "%" + txtSearchValue.getText().trim() + "%"};
            attributeConditionValueList.add(ACV2);

            ResultSet rset = subjectController.getSubjectByMoreAttributes(attributeConditionValueList, CommonConstants.sql.OR);

            CommonController.loadDataToTable(tblHistory, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void editSelectedCourse() {
        int selectedRaw = tblHistory.getSelectedRow();
        if (selectedRaw == -1) {
            JOptionPane.showMessageDialog(this, "Please select the row you want to update !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblHistory.getModel();
        int subjectId = CommonController.getIntOrZeroFromString(dtm.getValueAt(selectedRaw, 0).toString());
        new editSubject(this, true, subjectId).setVisible(true);
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
        tblHistory = new javax.swing.JTable();
        PanelSub = new javax.swing.JPanel();
        txtSubjectName = new javax.swing.JTextField();
        btSave = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtSearchValue = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        btSave1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attendance & Payment");
        setResizable(false);

        PanelMain.setBackground(new java.awt.Color(102, 102, 255));

        tblHistory.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tblHistory.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblHistory.getTableHeader().setOpaque(false);
        tblHistory.getTableHeader().setBackground(new Color(0, 0, 102));
        tblHistory.getTableHeader().setForeground(new Color(255, 255, 255));
        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lecturer", "Attended Date", "Detail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHistory.setRowHeight(20);
        tblHistory.setRowMargin(2);
        tblHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblHistory);
        if (tblHistory.getColumnModel().getColumnCount() > 0) {
            tblHistory.getColumnModel().getColumn(0).setResizable(false);
            tblHistory.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblHistory.getColumnModel().getColumn(1).setResizable(false);
            tblHistory.getColumnModel().getColumn(2).setResizable(false);
            tblHistory.getColumnModel().getColumn(2).setPreferredWidth(0);
        }

        txtSubjectName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtSubjectName.setToolTipText("Module Name");
        txtSubjectName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSubjectName.setSelectionColor(new java.awt.Color(255, 255, 0));

        btSave.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave.setForeground(new java.awt.Color(255, 255, 255));
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/addItem.png"))); // NOI18N
        btSave.setToolTipText("Add new subject");
        btSave.setBorder(null);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Lecture Code and Student Code");

        txtSearchValue.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtSearchValue.setToolTipText("Search By Subject and Grade");
        txtSearchValue.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSearchValue.setSelectionColor(new java.awt.Color(255, 255, 0));
        txtSearchValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchValueActionPerformed(evt);
            }
        });
        txtSearchValue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchValueKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setText("Class Fee");

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(txtSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(txtSearchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel24.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 102));
        jLabel24.setText("From Date");

        jLabel25.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 102));
        jLabel25.setText("To Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btSave1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave1.setForeground(new java.awt.Color(255, 255, 255));
        btSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/search_32x32.png"))); // NOI18N
        btSave1.setToolTipText("Add new subject");
        btSave1.setBorder(null);
        btSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSave1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainLayout.createSequentialGroup()
                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelMainLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btSave1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(19, 19, 19))
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
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
//        addSubject();
        loadSubjectsToTable();
    }//GEN-LAST:event_btSaveActionPerformed

    private void txtSearchValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchValueKeyReleased
        searchSubjectByNameAndGrade();
    }//GEN-LAST:event_txtSearchValueKeyReleased

    private void txtSearchValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchValueActionPerformed

    private void btSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSave1ActionPerformed
        // TODO add your handling code here:
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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTextField txtSearchValue;
    private javax.swing.JTextField txtSubjectName;
    // End of variables declaration//GEN-END:variables
}
