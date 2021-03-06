/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.studentController;
import cazzendra.pos.core.Loading;
import cazzendra.pos.model.student;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author personal
 */
public class editStudent extends javax.swing.JDialog {

    private int studentId;

    /**
     * Creates new form editBatch
     *
     * @param parent
     * @param modal
     * @param studentPrimaryKey
     */
    public editStudent(java.awt.Frame parent, boolean modal, int studentPrimaryKey) {
        super(parent, modal);
        initComponents();
        studentId = studentPrimaryKey;
        setDetails();
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
    }

    private void setDetails() {
        try {
            chkFreeCard.setSelected(false);
            student student = studentController.getStudentByStudentId(studentId);
            txtStudentName.setText(student.getName());
            txtEmail1.setText(student.getEmail1());
            txtRegNo.setText(student.getRegNo());
            txtContactNo.setText(student.getContactNo());
            comboGender.setSelectedItem(student.getGender());
            txtGuardianContact.setText(student.getGuardianContact());
            txtCurrentAddress.setText(student.getCurrentAddress());
            txtGuardianName.setText(student.getGuardianName());
            comboGrade.setSelectedItem(student.getGrade());
            calDob.setDate(student.getDob());
            chkBoxIsInstituteStudent.setSelected("Y".equals(student.getIsInstituteStudent()) ? true : false);

            if (student.getStatus() == 1) {
                chkFreeCard.setSelected(false);
            } else if (student.getStatus() == 0) {
                chkFreeCard.setSelected(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(editStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateStudentDetails() {
        try {
            if (txtStudentName.getText().trim().equalsIgnoreCase(null) || txtStudentName.getText().trim().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Please enter student Name !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (comboGrade.getSelectedItem()== null) {
                JOptionPane.showMessageDialog(this, "Please select grade !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
//            if (txtEmail1.getText().trim().equalsIgnoreCase(null) || txtEmail1.getText().trim().equalsIgnoreCase("")) {
//                JOptionPane.showMessageDialog(this, "Please enter student Email !", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            if (txtRegNo.getText().trim().equalsIgnoreCase(null) || txtRegNo.getText().trim().equalsIgnoreCase("")) {
//                JOptionPane.showMessageDialog(this, "Please enter student Registration No !", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            if (txtContactNo.getText().trim().equalsIgnoreCase(null) || txtContactNo.getText().trim().equalsIgnoreCase("")) {
//                JOptionPane.showMessageDialog(this, "Please enter student Contact No !", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }

            student student = new student();
            student.setName(txtStudentName.getText().trim());
            student.setId(studentId);
            student.setContactNo(txtContactNo.getText().trim());
            student.setEmail1(txtEmail1.getText().trim());
            student.setEmail2("");
            student.setRegNo(txtRegNo.getText().trim());
            student.setGender(comboGender.getSelectedItem().toString());
            student.setGuardianContact(txtGuardianContact.getText().trim());
            student.setCurrentAddress(txtCurrentAddress.getText().trim());
            student.setGuardianName(txtGuardianName.getText().trim());
            student.setGrade(comboGrade.getSelectedItem().toString());
            student.setIsInstituteStudent(chkBoxIsInstituteStudent.isSelected() ? "Y" : "N");
            int FreeCard = 1;
            if (chkFreeCard.isSelected()) {
                FreeCard = 0;
            } else {
                FreeCard = 1;
            }
            student.setStatus(FreeCard);
            student.setDob(CommonController.getMysqlDateFromJDateChooser(calDob));

            boolean status = studentController.updateStudentDetails(student);
            if (status) {
                JOptionPane.showMessageDialog(this, "Student updated successfully !");
                this.dispose();
            }
        } catch (SQLException ex) {
            Logger.getLogger(editStudent.class.getName()).log(Level.SEVERE, null, ex);
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
        PanelSub = new javax.swing.JPanel();
        btSave2 = new javax.swing.JButton();
        txtCurrentAddress = new javax.swing.JTextField();
        txtGuardianContact = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtStudentName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtRegNo = new javax.swing.JTextField();
        txtContactNo = new javax.swing.JTextField();
        comboGender = new javax.swing.JComboBox<>();
        calDob = new com.toedter.calendar.JDateChooser();
        chkFreeCard = new javax.swing.JCheckBox();
        jLabel20 = new javax.swing.JLabel();
        txtGuardianName = new javax.swing.JTextField();
        comboGrade = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        chkBoxIsInstituteStudent = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Selected Student ");

        PanelMain.setBackground(new java.awt.Color(102, 102, 255));

        btSave2.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave2.setForeground(new java.awt.Color(255, 255, 255));
        btSave2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/save.png"))); // NOI18N
        btSave2.setToolTipText("Update Sudent");
        btSave2.setBorder(null);
        btSave2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSave2ActionPerformed(evt);
            }
        });

        txtCurrentAddress.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtCurrentAddress.setToolTipText("Contact No");
        txtCurrentAddress.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCurrentAddress.setSelectionColor(new java.awt.Color(255, 255, 0));

        txtGuardianContact.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtGuardianContact.setToolTipText("Contact No");
        txtGuardianContact.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtGuardianContact.setSelectionColor(new java.awt.Color(255, 255, 0));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("Date Of Birth");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("Guardian Contact");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Address");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Gender");

        txtStudentName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtStudentName.setToolTipText("Student Name");
        txtStudentName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtStudentName.setSelectionColor(new java.awt.Color(255, 255, 0));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Contact No");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 102));
        jLabel14.setText("Registration No");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("Email");

        txtEmail1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtEmail1.setToolTipText("Email ");
        txtEmail1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtEmail1.setSelectionColor(new java.awt.Color(255, 255, 0));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 102));
        jLabel16.setText("Student Name");

        txtRegNo.setEditable(false);
        txtRegNo.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtRegNo.setToolTipText("Registration No");
        txtRegNo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtRegNo.setSelectionColor(new java.awt.Color(255, 255, 0));

        txtContactNo.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtContactNo.setToolTipText("Contact No");
        txtContactNo.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtContactNo.setSelectionColor(new java.awt.Color(255, 255, 0));

        comboGender.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        comboGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        comboGender.setToolTipText("Batch");

        calDob.setToolTipText("Week Begining Date");
        calDob.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                calDobFocusLost(evt);
            }
        });
        calDob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                calDobMouseExited(evt);
            }
        });
        calDob.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calDobPropertyChange(evt);
            }
        });
        calDob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calDobKeyReleased(evt);
            }
        });

        chkFreeCard.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        chkFreeCard.setForeground(new java.awt.Color(0, 0, 102));
        chkFreeCard.setText("    Free Card");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Guardian Name");

        txtGuardianName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtGuardianName.setToolTipText("Contact No");
        txtGuardianName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtGuardianName.setSelectionColor(new java.awt.Color(255, 255, 0));

        comboGrade.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        comboGrade.setForeground(new java.awt.Color(0, 0, 102));
        comboGrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nursary", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Grade 13" }));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("Grade");

        chkBoxIsInstituteStudent.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        chkBoxIsInstituteStudent.setForeground(new java.awt.Color(0, 0, 102));
        chkBoxIsInstituteStudent.setText("    Institute Student");
        chkBoxIsInstituteStudent.setFocusable(false);

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelSubLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtContactNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtGuardianContact, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSubLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCurrentAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(PanelSubLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(calDob, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtGuardianName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkFreeCard)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addComponent(chkBoxIsInstituteStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkFreeCard)))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCurrentAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calDob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGuardianName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addGap(7, 7, 7)
                        .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGuardianContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkBoxIsInstituteStudent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btSave2)
                .addGap(19, 19, 19))
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btSave2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSave2ActionPerformed
        updateStudentDetails();
    }//GEN-LAST:event_btSave2ActionPerformed

    private void calDobFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_calDobFocusLost

    }//GEN-LAST:event_calDobFocusLost

    private void calDobMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calDobMouseExited

    }//GEN-LAST:event_calDobMouseExited

    private void calDobPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calDobPropertyChange

    }//GEN-LAST:event_calDobPropertyChange

    private void calDobKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calDobKeyReleased

    }//GEN-LAST:event_calDobKeyReleased

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
            java.util.logging.Logger.getLogger(editStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(editStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(editStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(editStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                editStudent dialog = new editStudent(new javax.swing.JFrame(), true, 0);
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
    private com.toedter.calendar.JDateChooser calDob;
    private javax.swing.JCheckBox chkBoxIsInstituteStudent;
    private javax.swing.JCheckBox chkFreeCard;
    private javax.swing.JComboBox<String> comboGender;
    private javax.swing.JComboBox<String> comboGrade;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtCurrentAddress;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtGuardianContact;
    private javax.swing.JTextField txtGuardianName;
    private javax.swing.JTextField txtRegNo;
    private javax.swing.JTextField txtStudentName;
    // End of variables declaration//GEN-END:variables
}
