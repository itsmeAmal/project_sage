/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.lecturerController;
import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.core.Loading;
import cazzendra.pos.core.Validations;
import cazzendra.pos.daoImpl.subjectDaoImpl;
import cazzendra.pos.model.student;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Amal
 */
public class Commission extends javax.swing.JFrame {

    private String Lang;
    boolean PaidStatus = false;

    int StudentId = 0;
    student Student = null;

    /**
     * @param Language
     */
    public Commission(String Language) {
        initComponents();
        this.Lang = Language;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        setDefaults();
        panel.setBackground(Loading.getColorCode());
        HotKeys();
    }

    private void setDefaults() {
        btnCloseApp.setBackground(new java.awt.Color(0, 0, 102));
        //-------------------------------
    }

    private void HotKeys() {
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        InputMap im = btnCloseApp.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        im.put(ks, "C");
        btnCloseApp.getActionMap().put("C", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
//                new Invoice(Lang).setVisible(true);
            }
        });
    }

    private void LoadSubjectsTotable() {
        DefaultTableModel Dtm = (DefaultTableModel) tblLecturers.getModel();
        Dtm.setRowCount(0);
        DefaultTableModel Dtm2 = (DefaultTableModel) tblSubjects.getModel();
        Dtm2.setRowCount(0);
        try {
            //subject_id, , subject_module_code, subject_detail, subject_status, subject_course_id, subject_course_level, subject_semester
            ResultSet Rset = new subjectDaoImpl().getSubjectsByGrade(comboGrade.getSelectedItem().toString());
            String[] ColumnList = {"subject_id", "subject_name"};
            CommonController.loadDataToTable(tblSubjects, Rset, ColumnList);

        } catch (SQLException ex) {
            Logger.getLogger(Commission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void LoadSubjectsDataObjectsToComboBox() {
//        try {
//            ResultSet Rset = new subjectDaoImpl().getSubjectsByGrade(comboGrade.getSelectedItem().toString());
//            String[] 
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(AttendanceMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    private void loadLecturerDetailstoTable() {
        DefaultTableModel Dtm = (DefaultTableModel) tblSubjects.getModel();
        int SelectedRow = tblSubjects.getSelectedRow();
        if (SelectedRow != -1) {
            try {
                String SubjectName = Dtm.getValueAt(SelectedRow, 1).toString();
                ResultSet Rset = lecturerController.getLecturerResultSetByOneAttribute("lecturer_detail", CommonConstants.sql.EQUAL, SubjectName);
                String[] ColumnList = {"lecturer_id", "lecturer_name"};
                CommonController.loadDataToTable(tblLecturers, Rset, ColumnList);
            } catch (SQLException ex) {
                Logger.getLogger(Commission.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void ClearStudentDetails() {

        DefaultTableModel Dtm = (DefaultTableModel) tblLecturers.getModel();
        Dtm.setRowCount(0);
        DefaultTableModel Dtm2 = (DefaultTableModel) tblSubjects.getModel();
        Dtm2.setRowCount(0);

        comboGrade.setEnabled(false);
        Student = null;
        StudentId = 0;

    }

    private void AddAttendanceAndPaymentRecord() {

        int LecturerId = 0;
        String LecturerName = "";
        int SubjectId = 0;
        String SubjectName = "";

        DefaultTableModel Dtm = (DefaultTableModel) tblLecturers.getModel();
        int SelectedLecRow = tblLecturers.getSelectedRow();
        if (SelectedLecRow != -1) {
            LecturerId = Validations.getIntOrZeroFromString(Dtm.getValueAt(SelectedLecRow, 0).toString());
            LecturerName = Dtm.getValueAt(SelectedLecRow, 1).toString();
        } else {
            JOptionPane.showMessageDialog(this, "Pls select lecturer !");
            return;
        }

        DefaultTableModel Dtm2 = (DefaultTableModel) tblSubjects.getModel();
        int SelectedSubjectRow = tblSubjects.getSelectedRow();
        if (SelectedSubjectRow != -1) {
            SubjectId = Validations.getIntOrZeroFromString(Dtm2.getValueAt(SelectedSubjectRow, 0).toString());
            SubjectName = Dtm2.getValueAt(SelectedSubjectRow, 1).toString();

        } else {
            JOptionPane.showMessageDialog(this, "Pls select lecturer !");
            return;
        }

        Payment payment = new Payment(this, true, StudentId, SubjectId, comboGrade.getSelectedItem().toString(),
                SubjectName, LecturerId, LecturerName);
        payment.setVisible(true);

        boolean legalClose = payment.legalClose;

        if (legalClose) {
            ClearStudentDetails();
            LecturerId = 0;
            LecturerName = "";
            SubjectId = 0;
            SubjectName = "";
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSubjects = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLecturers = new javax.swing.JTable();
        btnApply = new javax.swing.JButton();
        btnCloseApp = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        comboGrade = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attendance");
        setBackground(new java.awt.Color(0, 0, 102));
        setMaximumSize(new java.awt.Dimension(418, 676));
        setMinimumSize(new java.awt.Dimension(418, 676));
        setResizable(false);
        setSize(new java.awt.Dimension(418, 676));

        panel.setMaximumSize(new java.awt.Dimension(1360, 600));
        panel.setMinimumSize(new java.awt.Dimension(1360, 600));
        panel.setPreferredSize(new java.awt.Dimension(1360, 600));

        tblSubjects.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tblSubjects.setForeground(new java.awt.Color(0, 0, 102));
        tblSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Subject Id", "Subject Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSubjects.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblSubjects.getTableHeader().setOpaque(false);
        tblSubjects.getTableHeader().setBackground(new Color(0, 0, 102));
        tblSubjects.getTableHeader().setForeground(new Color(255, 255, 255));
        tblSubjects.setOpaque(false);
        tblSubjects.setRowHeight(20);
        tblSubjects.setRowMargin(3);
        tblSubjects.setShowHorizontalLines(false);
        tblSubjects.setShowVerticalLines(false);
        tblSubjects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSubjectsMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblSubjectsMouseExited(evt);
            }
        });
        jScrollPane1.setViewportView(tblSubjects);
        if (tblSubjects.getColumnModel().getColumnCount() > 0) {
            tblSubjects.getColumnModel().getColumn(0).setMinWidth(0);
            tblSubjects.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblSubjects.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        tblLecturers.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tblLecturers.setForeground(new java.awt.Color(0, 0, 102));
        tblLecturers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lecturer Id", "Lecturer Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLecturers.setRowHeight(20);
        tblLecturers.setRowMargin(3);
        tblLecturers.setShowHorizontalLines(false);
        tblLecturers.setShowVerticalLines(false);
        tblLecturers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblLecturersMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(tblLecturers);
        tblLecturers.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblLecturers.getTableHeader().setOpaque(false);
        tblLecturers.getTableHeader().setBackground(new Color(0, 0, 102));
        tblLecturers.getTableHeader().setForeground(new Color(255, 255, 255));
        if (tblLecturers.getColumnModel().getColumnCount() > 0) {
            tblLecturers.getColumnModel().getColumn(0).setMinWidth(0);
            tblLecturers.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblLecturers.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btnApply.setBackground(new java.awt.Color(0, 153, 204));
        btnApply.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        btnApply.setForeground(new java.awt.Color(0, 0, 102));
        btnApply.setText("Commission Report");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnCloseApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/newtheme/icons/exit-button-40x40.png"))); // NOI18N
        btnCloseApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseAppMouseClicked(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setText("SUBJECT NAME");

        jLabel22.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 102));
        jLabel22.setText("GRADE");

        comboGrade.setFont(new java.awt.Font("Ubuntu Medium", 0, 36)); // NOI18N
        comboGrade.setForeground(new java.awt.Color(0, 0, 102));
        comboGrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Grade 13" }));
        comboGrade.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                comboGradePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel23.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 102));
        jLabel23.setText("LECTURER NAME");

        jTextField1.setText("22");

        jLabel24.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 102));
        jLabel24.setText("COMMISSION RATE");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel22)
                                .addGap(1222, 1222, 1222))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(922, 922, 922)))
                        .addComponent(btnCloseApp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(btnCloseApp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel22)
                        .addGap(3, 3, 3)
                        .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 418, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 676, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleName("Commission");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseAppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseAppMouseClicked
        try {
            boolean status = CommonController.Activation();
            if (status) {
                try {
                    CommonController.AutoBackupDB();
                } catch (URISyntaxException | IOException | InterruptedException ex) {
                    Logger.getLogger(Commission.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Login().setVisible(true);
                this.dispose();
            } else {
                new ActivationUI().setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Commission.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCloseAppMouseClicked

    private void comboGradePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboGradePopupMenuWillBecomeInvisible
        LoadSubjectsTotable();
    }//GEN-LAST:event_comboGradePopupMenuWillBecomeInvisible

    private void tblSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectsMouseClicked
        loadLecturerDetailstoTable();
    }//GEN-LAST:event_tblSubjectsMouseClicked

    private void tblSubjectsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectsMouseExited
        loadLecturerDetailstoTable();
    }//GEN-LAST:event_tblSubjectsMouseExited

    private void tblLecturersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLecturersMouseExited

    }//GEN-LAST:event_tblLecturersMouseExited

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed

        if (!comboGrade.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Select Student & Grade !");
            return;
        }

        if (tblSubjects.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select Subject Name !");
            return;
        }
        if (tblLecturers.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select Lecturer Name !");
            return;
        }

        AddAttendanceAndPaymentRecord();

    }//GEN-LAST:event_btnApplyActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Commission.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Commission.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Commission.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Commission.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Commission("test param").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JLabel btnCloseApp;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboGrade;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tblLecturers;
    private javax.swing.JTable tblSubjects;
    // End of variables declaration//GEN-END:variables
}
