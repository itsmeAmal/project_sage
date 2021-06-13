/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.lecturerController;
import cazzendra.pos.control.subjectController;
import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.core.Loading;
import cazzendra.pos.daoImpl.lecturerDaoImpl;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author personal
 */
public class manageLecturersNewUI extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public manageLecturersNewUI() {
        initComponents();
        loadLecturerData();
        loadSubjectsToComboBox();
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
    }

    private void clearAll() {
        txtContactNo.setText("");
        txtEmail1.setText("");
        txtName.setText("");
        txtPrefixCode.setText("");
    }

    private boolean validateLength() {
        boolean status = false;
        String txtValue = txtPrefixCode.getText().trim();
        if (txtValue.length() == 2) {
            status = true;
        }
        return status;
    }

    private void addLecturer() {
        int option = JOptionPane.showConfirmDialog(this, "add new lecturer?");
        if (option == JOptionPane.YES_OPTION) {
            try {

                if (txtName.getText().trim().equalsIgnoreCase("") || txtName.getText().trim().equalsIgnoreCase(null)) {
                    JOptionPane.showMessageDialog(this, "Name could not be empty ! ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (txtContactNo.getText().trim().equalsIgnoreCase("") || txtContactNo.getText().trim().equalsIgnoreCase(null)) {
                    JOptionPane.showMessageDialog(this, "Contact no could not be empty ! ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (txtPrefixCode.getText().trim().equalsIgnoreCase("") || txtPrefixCode.getText().trim() == null) {
                    JOptionPane.showMessageDialog(this, "Please add PREFIX code !", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!validateLength()) {
                    JOptionPane.showMessageDialog(this, "Please add PREFIX code with 2 digits !", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean status = lecturerController.addLecturer("", txtName.getText().trim(),
                        txtEmail1.getText().trim(), txtContactNo.getText().trim(),
                        comboSubjects.getSelectedItem().toString(), txtPrefixCode.getText().trim());
                if (status) {
                    JOptionPane.showMessageDialog(this, "Lecturer registered successfully !");
                    clearAll();
                    loadLecturerData();
                }
            } catch (SQLException ex) {
                Logger.getLogger(manageLecturersNewUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void loadLecturerData() {
        try {
            ResultSet rset = new lecturerDaoImpl().getAlllecturers();
            String[] columnList = {"lecturer_id", "lecturer_name", "lecturer_email", "lecturer_contact_no", "lecturer_detail", "lecturer_prefix_code"};
            CommonController.loadDataToTable(tblLectures, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(manageLecturersNewUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void searchLecturerByAttributes(String attribute) {
        /*
          lecturer_id, lecturer_title, lecturer_name, lecturer_email, lecturer_contact_no, lecturer_detail, lecturer_status
         */
        try {
            ArrayList<String[]> attributeConditionValueList = new ArrayList<>();
            String[] ACV1 = {"lecturer_name", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV1);

            String[] ACV2 = {"lecturer_email", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV2);

            String[] ACV3 = {"lecturer_contact_no", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV3);

            ResultSet rset = new lecturerDaoImpl().getLecturerByMoreAttributes(attributeConditionValueList, CommonConstants.sql.OR);
            String[] columnList = {"lecturer_id", "lecturer_name", "lecturer_email", "lecturer_contact_no", "lecturer_detail", "lecturer_prefix_code"};
            CommonController.loadDataToTable(tblLectures, rset, columnList);

        } catch (SQLException ex) {
            Logger.getLogger(manageLecturersNewUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadSubjectsToComboBox() {
        try {
            ResultSet rset = subjectController.getAllSubjects();
            CommonController.loadDataToComboBox(comboSubjects, rset, "subject_name");
        } catch (SQLException ex) {
            Logger.getLogger(manageLecturersNewUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editSelectedCourse() {
        int selectedRaw = tblLectures.getSelectedRow();
        if (selectedRaw == -1) {
            JOptionPane.showMessageDialog(this, "Please select the row you want to update !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblLectures.getModel();
        int lecturerId = CommonController.getIntOrZeroFromString(dtm.getValueAt(selectedRaw, 0).toString());
        new editLecturer(this, true, lecturerId).setVisible(true);
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
        tblLectures = new javax.swing.JTable();
        btnEdit = new javax.swing.JButton();
        txtSearchLecturer = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btViewStudentDetails = new javax.swing.JButton();
        PanelSub = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        txtContactNo = new javax.swing.JTextField();
        btSave = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        comboSubjects = new javax.swing.JComboBox<>();
        txtPrefixCode = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lecturer Management");
        setMaximumSize(new java.awt.Dimension(1290, 651));
        setMinimumSize(new java.awt.Dimension(1290, 651));

        PanelMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblLectures.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tblLectures.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblLectures.getTableHeader().setOpaque(false);
        tblLectures.getTableHeader().setBackground(new Color(0, 0, 102));
        tblLectures.getTableHeader().setForeground(new Color(255, 255, 255));
        tblLectures.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "lecture id", "Name", "Email", "Contact No", "Subject", "Prefix Code"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLectures.setRowHeight(20);
        tblLectures.setRowMargin(2);
        tblLectures.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblLectures);
        if (tblLectures.getColumnModel().getColumnCount() > 0) {
            tblLectures.getColumnModel().getColumn(0).setMinWidth(0);
            tblLectures.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblLectures.getColumnModel().getColumn(0).setMaxWidth(0);
            tblLectures.getColumnModel().getColumn(2).setMinWidth(250);
            tblLectures.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblLectures.getColumnModel().getColumn(2).setMaxWidth(250);
            tblLectures.getColumnModel().getColumn(3).setMinWidth(150);
            tblLectures.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblLectures.getColumnModel().getColumn(3).setMaxWidth(150);
            tblLectures.getColumnModel().getColumn(4).setMinWidth(200);
            tblLectures.getColumnModel().getColumn(4).setPreferredWidth(200);
            tblLectures.getColumnModel().getColumn(4).setMaxWidth(200);
            tblLectures.getColumnModel().getColumn(5).setMinWidth(100);
            tblLectures.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblLectures.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        PanelMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 75, 973, 530));

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/editIcon.png"))); // NOI18N
        btnEdit.setToolTipText("Edit Lecturer Details");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        PanelMain.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(943, 17, 40, 40));

        txtSearchLecturer.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtSearchLecturer.setToolTipText("Search by Name");
        txtSearchLecturer.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSearchLecturer.setSelectionColor(new java.awt.Color(255, 255, 0));
        txtSearchLecturer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchLecturerKeyReleased(evt);
            }
        });
        PanelMain.add(txtSearchLecturer, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 17, 321, -1));

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setText("Search Lecturer");
        PanelMain.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 24, 121, -1));

        btViewStudentDetails.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/viewButton.png"))); // NOI18N
        btViewStudentDetails.setToolTipText("Report");
        btViewStudentDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btViewStudentDetailsActionPerformed(evt);
            }
        });
        PanelMain.add(btViewStudentDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(893, 17, 40, 40));

        txtName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtName.setToolTipText("Lecturer Name");
        txtName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtName.setSelectionColor(new java.awt.Color(255, 255, 0));

        txtEmail1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtEmail1.setToolTipText("Email ");
        txtEmail1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtEmail1.setSelectionColor(new java.awt.Color(255, 255, 0));

        txtContactNo.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtContactNo.setToolTipText("Contact No");
        txtContactNo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtContactNo.setSelectionColor(new java.awt.Color(255, 255, 0));

        btSave.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave.setForeground(new java.awt.Color(255, 255, 255));
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/save.png"))); // NOI18N
        btSave.setToolTipText("Add new lecturer");
        btSave.setBorder(null);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Lecturer Name");

        Email.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Email.setForeground(new java.awt.Color(0, 0, 102));
        Email.setText("Email");

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 102));
        jLabel22.setText("Contact No");

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 102));
        jLabel23.setText("Subject");

        comboSubjects.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboSubjects.setToolTipText("Type");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 102));
        jLabel24.setText("Lecturer Prefix Code");

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(txtContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrefixCode, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel20)
                .addGap(11, 11, 11)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(Email)
                .addGap(6, 6, 6)
                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel22)
                .addGap(6, 6, 6)
                .addComponent(txtContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel23)
                .addGap(11, 11, 11)
                .addComponent(comboSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPrefixCode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btSave)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(PanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 1007, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        addLecturer();
        loadLecturerData();
    }//GEN-LAST:event_btSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        editSelectedCourse();
        loadLecturerData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtSearchLecturerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchLecturerKeyReleased
        searchLecturerByAttributes(txtSearchLecturer.getText().trim());
    }//GEN-LAST:event_txtSearchLecturerKeyReleased

    private void btViewStudentDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btViewStudentDetailsActionPerformed
        try {
            HashMap<String, Object> hm = new HashMap<>();
            CommonController.printCommonReport("lecturers_all", hm);
        } catch (SQLException | JRException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btViewStudentDetailsActionPerformed

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
            java.util.logging.Logger.getLogger(manageLecturersNewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manageLecturersNewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manageLecturersNewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manageLecturersNewUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manageLecturersNewUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelSub;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btViewStudentDetails;
    private javax.swing.JButton btnEdit;
    private javax.swing.JComboBox<String> comboSubjects;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLectures;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrefixCode;
    private javax.swing.JTextField txtSearchLecturer;
    // End of variables declaration//GEN-END:variables
}
