/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.commonConstants;
import cazzendra.pos.control.studentController;
import cazzendra.pos.core.Loading;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author personal
 */
public class searchStudent extends javax.swing.JDialog {

    int StudentId = 0;

    /**
     * Creates new form editBatch
     *
     * @param parent
     * @param modal
     * @param subjectPrimaryKey
     */
    public searchStudent(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        LoadStudentsToTable(txtSearchBySubNameOrCode.getText().trim());
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
        setHotKey();
    }

    private void setHotKey() {
//        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
//        InputMap inputMap2 = btnSearch.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
//        inputMap2.put(tab, "B");
//
//        btnSearch.getActionMap().put("B", new AbstractAction() {
//            public void actionPerformed(ActionEvent e) {
//                okButtonAction();
//            }
//        });
//        KeyStroke tab2 = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
//        InputMap inputMap3 = btnSearch.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
//        inputMap3.put(tab2, "B");
//
//        btnSearch.getActionMap().put("B", new AbstractAction() {
//            public void actionPerformed(ActionEvent e) {
//                tblItems.requestFocus();
//            }
//        });
//
        KeyStroke tab4 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        InputMap inputMap4 = btOkButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        inputMap4.put(tab4, "B");

        btOkButton.getActionMap().put("B", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                OkButtonAction();
            }
        });
        createKeybindings(tblStudentDetails);
    }

    private void createKeybindings(JTable table) {
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        table.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                OkButtonAction();
            }
        });
    }

    private void LoadStudentsToTable(String StudentNameAndNicAndContactAndId) {
        try {
            ArrayList<String[]> attributeConditionValueList = new ArrayList<>();

            String[] arr1 = {"student_name", commonConstants.Sql.LIKE, "%" + StudentNameAndNicAndContactAndId + "%"};
            attributeConditionValueList.add(arr1);

            String[] arr2 = {"student_id", commonConstants.Sql.LIKE, "%" + StudentNameAndNicAndContactAndId + "%"};
            attributeConditionValueList.add(arr2);

            String[] arr3 = {"student_special_id", commonConstants.Sql.LIKE, "%" + StudentNameAndNicAndContactAndId + "%"};
            attributeConditionValueList.add(arr3);

            String[] arr4 = {"student_contact_no", commonConstants.Sql.LIKE, "%" + StudentNameAndNicAndContactAndId + "%"};
            attributeConditionValueList.add(arr4);

            ResultSet rset = studentController.GetStudentByMoreAttributes(attributeConditionValueList, commonConstants.Sql.OR);
            String[] columnList = {"student_id", "student_id", "student_name", "student_contact_no", "student_special_id"};
            CommonController.loadDataToTable(tblStudentDetails, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(searchStudent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getSelectedLecturerId() {
        return StudentId;
    }

    private void OkButtonAction() {
        int selectedRaw = tblStudentDetails.getSelectedRow();
        if (selectedRaw != -1) {
            DefaultTableModel dtm = (DefaultTableModel) tblStudentDetails.getModel();
            StudentId = CommonController.getIntOrZeroFromString(dtm.getValueAt(selectedRaw, 0).toString());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select Student !", "Error", JOptionPane.ERROR_MESSAGE);
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
        btOkButton = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtSearchBySubNameOrCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudentDetails = new javax.swing.JTable();
        btSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Search Student");

        btOkButton.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btOkButton.setForeground(new java.awt.Color(0, 0, 102));
        btOkButton.setText("OK");
        btOkButton.setToolTipText("Add Lecturer");
        btOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkButtonActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Student ID, Name, Contact & NIC");

        txtSearchBySubNameOrCode.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtSearchBySubNameOrCode.setToolTipText("Search by Name / Email / Title / Contact No");
        txtSearchBySubNameOrCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSearchBySubNameOrCode.setSelectionColor(new java.awt.Color(255, 255, 0));
        txtSearchBySubNameOrCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchBySubNameOrCodeKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/lableIcons/Lecturer.png"))); // NOI18N
        jLabel2.setToolTipText("Lecturer");

        tblStudentDetails.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tblStudentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Student ID", "Name", "Contact", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudentDetails.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblStudentDetails.getTableHeader().setOpaque(false);
        tblStudentDetails.getTableHeader().setBackground(new Color(0, 0, 102));
        tblStudentDetails.getTableHeader().setForeground(new Color(255, 255, 255));
        tblStudentDetails.setRowHeight(20);
        tblStudentDetails.setRowMargin(2);
        tblStudentDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblStudentDetails);
        if (tblStudentDetails.getColumnModel().getColumnCount() > 0) {
            tblStudentDetails.getColumnModel().getColumn(0).setMinWidth(0);
            tblStudentDetails.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblStudentDetails.getColumnModel().getColumn(0).setMaxWidth(0);
            tblStudentDetails.getColumnModel().getColumn(1).setMinWidth(200);
            tblStudentDetails.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblStudentDetails.getColumnModel().getColumn(1).setMaxWidth(200);
            tblStudentDetails.getColumnModel().getColumn(3).setMinWidth(200);
            tblStudentDetails.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblStudentDetails.getColumnModel().getColumn(3).setMaxWidth(200);
            tblStudentDetails.getColumnModel().getColumn(4).setMinWidth(300);
            tblStudentDetails.getColumnModel().getColumn(4).setPreferredWidth(300);
            tblStudentDetails.getColumnModel().getColumn(4).setMaxWidth(300);
        }

        btSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/searchIcon.png"))); // NOI18N
        btSearch.setToolTipText("Search");
        btSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelSubLayout.createSequentialGroup()
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PanelSubLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearchBySubNameOrCode))
                            .addGroup(PanelSubLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1087, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchBySubNameOrCode, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btOkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void btOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkButtonActionPerformed
        OkButtonAction();
    }//GEN-LAST:event_btOkButtonActionPerformed

    private void btSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btSearchActionPerformed

    private void txtSearchBySubNameOrCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchBySubNameOrCodeKeyReleased
        LoadStudentsToTable(txtSearchBySubNameOrCode.getText().trim());
    }//GEN-LAST:event_txtSearchBySubNameOrCodeKeyReleased

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
            java.util.logging.Logger.getLogger(searchStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                searchStudent dialog = new searchStudent(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btOkButton;
    private javax.swing.JButton btSearch;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStudentDetails;
    private javax.swing.JTextField txtSearchBySubNameOrCode;
    // End of variables declaration//GEN-END:variables
}
