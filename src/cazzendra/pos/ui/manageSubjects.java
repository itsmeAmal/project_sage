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
public class manageSubjects extends javax.swing.JFrame {

    /**
     * Creates new form addStudent
     */
    public manageSubjects() {
        initComponents();
        loadSubjectsToTable();
        setDefaults();
        txtModuleCode.setVisible(false);
        jLabel5.setVisible(false);
        jLabel21.setVisible(false);
    }

    private void setDefaults() {
        txtModuleCode.setText("");
        txtSubjectName.setText("");
        comboSemester.setSelectedIndex(0);
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
    }

//    private void loadCourseDetailsDataObjectsToComboBox() {
//        try {
//            ResultSet rset = courseController.getAllCourses();
//            String[] columnList = {"course_id", "course_name", "course_type", "course_detail", "course_satus"};
//            commonController.loadDataObjectsIntoComboBox(comboCourse, rset, columnList, "course_type");
//        } catch (SQLException ex) {
//            Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//    }
    private void addSubject() {

        if (txtSubjectName.getText().trim().equalsIgnoreCase(null) || txtSubjectName.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please enter subject name !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int option = JOptionPane.showConfirmDialog(this, "do you want to add new subject?");
        if (option == JOptionPane.YES_OPTION) {
            try {
                int courseId = 0;
//                if (!comboCourse.getSelectedItem().toString().equalsIgnoreCase(subject.COMMON_SUBJECT)) {
//                    DataObject dataObjCourse = (DataObject) comboCourse.getSelectedItem();
//                    courseId = commonController.getIntOrZeroFromString(dataObjCourse.get("course_id"));
//                }
                boolean status = subjectController.addSubject("", "", txtModuleCode.getText().trim(), txtSubjectName.getText().trim(),
                        comboSemester.getSelectedItem().toString(), courseId);
                if (status) {
                    JOptionPane.showMessageDialog(this, "Subject registered successfully !");
                    setDefaults();
                }
            } catch (SQLException ex) {
                Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }

    private void loadSubjectsToTable() {
        try {
            ResultSet rset = subjectController.getAllSubjects();
            String[] columnList = {"subject_id", "subject_name_concat", "subject_module_code", "subject_semester"};
            CommonController.loadDataToTable(tblSubjectDetails, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

            CommonController.loadDataToTable(tblSubjectDetails, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(manageSubjects.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void editSelectedCourse() {
        int selectedRaw = tblSubjectDetails.getSelectedRow();
        if (selectedRaw == -1) {
            JOptionPane.showMessageDialog(this, "Please select the row you want to update !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblSubjectDetails.getModel();
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
        tblSubjectDetails = new javax.swing.JTable();
        PanelSub = new javax.swing.JPanel();
        txtSubjectName = new javax.swing.JTextField();
        txtModuleCode = new javax.swing.JTextField();
        btSave = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        comboSemester = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        txtSearchValue = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Subject Management");
        setResizable(false);

        PanelMain.setBackground(new java.awt.Color(102, 102, 255));

        tblSubjectDetails.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tblSubjectDetails.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblSubjectDetails.getTableHeader().setOpaque(false);
        tblSubjectDetails.getTableHeader().setBackground(new Color(0, 0, 102));
        tblSubjectDetails.getTableHeader().setForeground(new Color(255, 255, 255));
        tblSubjectDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "subjectId", "Subject Name", "Subject Code", "Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubjectDetails.setRowHeight(20);
        tblSubjectDetails.setRowMargin(2);
        tblSubjectDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblSubjectDetails);
        if (tblSubjectDetails.getColumnModel().getColumnCount() > 0) {
            tblSubjectDetails.getColumnModel().getColumn(0).setMinWidth(0);
            tblSubjectDetails.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblSubjectDetails.getColumnModel().getColumn(0).setMaxWidth(0);
            tblSubjectDetails.getColumnModel().getColumn(1).setResizable(false);
            tblSubjectDetails.getColumnModel().getColumn(2).setMinWidth(0);
            tblSubjectDetails.getColumnModel().getColumn(2).setPreferredWidth(0);
            tblSubjectDetails.getColumnModel().getColumn(2).setMaxWidth(0);
            tblSubjectDetails.getColumnModel().getColumn(3).setMinWidth(150);
            tblSubjectDetails.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblSubjectDetails.getColumnModel().getColumn(3).setMaxWidth(150);
        }

        txtSubjectName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtSubjectName.setToolTipText("Module Name");
        txtSubjectName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSubjectName.setSelectionColor(new java.awt.Color(255, 255, 0));

        txtModuleCode.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtModuleCode.setToolTipText("Module Code");
        txtModuleCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtModuleCode.setSelectionColor(new java.awt.Color(255, 255, 0));

        btSave.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave.setForeground(new java.awt.Color(255, 255, 255));
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/save.png"))); // NOI18N
        btSave.setToolTipText("Add new subject");
        btSave.setBorder(null);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Subject Name");

        jLabel21.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setText("Subject Code");

        comboSemester.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Grade 1 ", "Grade 2 ", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Grade 13" }));
        comboSemester.setToolTipText("Semester");

        jLabel23.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 102));
        jLabel23.setText("Grade");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/lableIcons/Module_Code.png"))); // NOI18N
        jLabel5.setToolTipText("Module Code");

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtModuleCode, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboSemester, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSubjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSave)
                .addGap(134, 134, 134)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(19, 19, 19)
                .addComponent(txtModuleCode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/editIcon.png"))); // NOI18N
        btnEdit.setToolTipText("Edit Subject Details");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

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

        jLabel22.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 102));
        jLabel22.setText("Search");

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSearchValue, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel22)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        editSelectedCourse();
        loadSubjectsToTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        addSubject();
        loadSubjectsToTable();
    }//GEN-LAST:event_btSaveActionPerformed

    private void txtSearchValueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchValueKeyReleased
        searchSubjectByNameAndGrade();
    }//GEN-LAST:event_txtSearchValueKeyReleased

    private void txtSearchValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchValueActionPerformed

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
            java.util.logging.Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manageSubjects.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manageSubjects().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelSub;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> comboSemester;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSubjectDetails;
    private javax.swing.JTextField txtModuleCode;
    private javax.swing.JTextField txtSearchValue;
    private javax.swing.JTextField txtSubjectName;
    // End of variables declaration//GEN-END:variables
}
