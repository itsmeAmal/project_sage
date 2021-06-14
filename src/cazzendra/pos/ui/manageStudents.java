/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.studentController;
import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.core.Loading;
import cazzendra.pos.core.Validations;
import cazzendra.pos.daoImpl.studentDaoImpl;
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

public class manageStudents extends javax.swing.JFrame {

    public manageStudents() {
        initComponents();
        loadStudentDataToTable();
        PanelMain.setBackground(Loading.getColorCode());
        PanelSub.setBackground(Loading.getColorCode());
        setNextIdAndQrCode();
    }

    private void setNextIdAndQrCode() {
        try {
            int nextId = new studentDaoImpl().getNextStudentId();
            txtRegNo.setText(Integer.toString(nextId));
        } catch (SQLException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearAll() {
        txtContactNo.setText("");
        txtEmail1.setText("");
        txtRegNo.setText("");
        txtStudentName.setText("");
        txtCurrentAddress.setText("");
        txtGuardianContact.setText("");
        calDob.setDate(null);
        txtGuardianName.setText("");
    }

    private void addStudent() {

        if (txtStudentName.getText().trim().equalsIgnoreCase(null) || txtStudentName.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please enter student Name !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

//        if (calDob.getDate() == null || calDob.getDate().equals("")) {
//            JOptionPane.showMessageDialog(this, "Please enter student Date Of Birth !", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

        if (txtContactNo.getText().trim().equalsIgnoreCase(null) || txtContactNo.getText().trim().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please enter student Contact No !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to save this student details ?");
            if (option == JOptionPane.YES_OPTION) {

                boolean status = studentController.addStudent(txtStudentName.getText().trim(), txtEmail1.getText().trim(),
                        "", txtRegNo.getText().trim(), txtContactNo.getText().trim(), txtGuardianName.getText().trim(),
                        txtGuardianContact.getText().trim(),
                        comboGender.getSelectedItem().toString(), txtCurrentAddress.getText().trim(),
                        CommonController.getMysqlDateFromJDateChooser(calDob), comboGrade.getSelectedItem().toString());

                if (status) {
                    JOptionPane.showMessageDialog(this, "Student registered successfully !");
                    clearAll();
                    loadStudentDataToTable();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadStudentDataToTable() {
        try {
            ResultSet rset = new studentDaoImpl().getAllStudents();

            String[] columnList = {"student_id", "student_reg_no", "student_name", "student_dob",
                "student_batch_id", "student_contact_no", "student_special_id", "student_email_1",
                "student_group_id"};
            CommonController.loadDataToTable(tblStudentDetails, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void searchStudentByMoreAttributes(String attribute) {
        try {

            ArrayList<String[]> attributeConditionValueList = new ArrayList<>();

            String[] ACV1 = {"student_id", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV1);

            String[] ACV2 = {"student_name", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV2);

            String[] ACV3 = {"student_dob", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV3);

            String[] ACV4 = {"student_batch_id", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV4);

            String[] ACV5 = {"student_contact_no", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV5);

            String[] ACV6 = {"student_special_id", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV6);

            String[] ACV7 = {"student_email_1", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV7);

            String[] ACV8 = {"student_group_id", CommonConstants.sql.LIKE, "%" + attribute + "%"};
            attributeConditionValueList.add(ACV8);

            ResultSet rset = new studentDaoImpl().getStudentsByMoreAttributes(attributeConditionValueList, CommonConstants.sql.OR);
            String[] columnList = {"student_id", "student_reg_no", "student_name", "student_dob",
                "student_batch_id", "student_contact_no", "student_special_id", "student_email_1",
                "student_group_id"};
            CommonController.loadDataToTable(tblStudentDetails, rset, columnList);
        } catch (SQLException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void editSelectedStudent() {
        int selectedRaw = tblStudentDetails.getSelectedRow();
        if (selectedRaw == -1) {
            JOptionPane.showMessageDialog(this, "Please select the row you want to update !", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DefaultTableModel dtm = (DefaultTableModel) tblStudentDetails.getModel();
        int studentId = CommonController.getIntOrZeroFromString(dtm.getValueAt(selectedRaw, 0).toString());
        new editStudent(this, true, studentId).setVisible(true);
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
        tblStudentDetails = new javax.swing.JTable();
        PanelSub = new javax.swing.JPanel();
        txtStudentName = new javax.swing.JTextField();
        btSave = new javax.swing.JButton();
        txtEmail1 = new javax.swing.JTextField();
        txtRegNo = new javax.swing.JTextField();
        txtContactNo = new javax.swing.JTextField();
        comboGender = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        calDob = new com.toedter.calendar.JDateChooser();
        txtCurrentAddress = new javax.swing.JTextField();
        txtGuardianContact = new javax.swing.JTextField();
        txtGuardianName = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        comboGrade = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnEdit = new javax.swing.JButton();
        btViewStudentDetails = new javax.swing.JButton();
        txtSearchAttribute = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btAllStudents = new javax.swing.JButton();
        btStudentAddressPrintReport = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Student Management");
        setMaximumSize(new java.awt.Dimension(1300, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setResizable(false);

        PanelMain.setMaximumSize(new java.awt.Dimension(1269, 643));
        PanelMain.setMinimumSize(new java.awt.Dimension(1269, 643));
        PanelMain.setPreferredSize(new java.awt.Dimension(1269, 643));

        tblStudentDetails.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        tblStudentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Reg. No", "Name", "D.O.B.", "Gender", "Contact", "Address", "Email ", "Guardian Contact"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudentDetails.setRowHeight(20);
        tblStudentDetails.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblStudentDetails.getTableHeader().setOpaque(false);
        tblStudentDetails.getTableHeader().setBackground(new Color(0, 0, 102));
        tblStudentDetails.getTableHeader().setForeground(new Color(255, 255, 255));
        tblStudentDetails.setRowMargin(2);
        tblStudentDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblStudentDetails);
        if (tblStudentDetails.getColumnModel().getColumnCount() > 0) {
            tblStudentDetails.getColumnModel().getColumn(0).setMinWidth(0);
            tblStudentDetails.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblStudentDetails.getColumnModel().getColumn(0).setMaxWidth(0);
            tblStudentDetails.getColumnModel().getColumn(1).setMinWidth(100);
            tblStudentDetails.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblStudentDetails.getColumnModel().getColumn(1).setMaxWidth(100);
            tblStudentDetails.getColumnModel().getColumn(2).setResizable(false);
            tblStudentDetails.getColumnModel().getColumn(3).setMinWidth(100);
            tblStudentDetails.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblStudentDetails.getColumnModel().getColumn(3).setMaxWidth(100);
            tblStudentDetails.getColumnModel().getColumn(4).setMinWidth(100);
            tblStudentDetails.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblStudentDetails.getColumnModel().getColumn(4).setMaxWidth(100);
            tblStudentDetails.getColumnModel().getColumn(5).setMinWidth(100);
            tblStudentDetails.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblStudentDetails.getColumnModel().getColumn(5).setMaxWidth(100);
            tblStudentDetails.getColumnModel().getColumn(7).setMinWidth(130);
            tblStudentDetails.getColumnModel().getColumn(7).setPreferredWidth(130);
            tblStudentDetails.getColumnModel().getColumn(7).setMaxWidth(130);
            tblStudentDetails.getColumnModel().getColumn(8).setMinWidth(120);
            tblStudentDetails.getColumnModel().getColumn(8).setPreferredWidth(120);
            tblStudentDetails.getColumnModel().getColumn(8).setMaxWidth(120);
        }

        PanelSub.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtStudentName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtStudentName.setToolTipText("Student Name");
        txtStudentName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtStudentName.setSelectionColor(new java.awt.Color(255, 255, 0));

        btSave.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        btSave.setForeground(new java.awt.Color(255, 255, 255));
        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/save.png"))); // NOI18N
        btSave.setToolTipText("Add new student ");
        btSave.setBorder(null);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });

        txtEmail1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtEmail1.setToolTipText("Email ");
        txtEmail1.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtEmail1.setSelectionColor(new java.awt.Color(255, 255, 0));

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

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("Student Contact No");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 102));
        jLabel14.setText("Registration No");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("Email");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 102));
        jLabel16.setText("Grade");

        calDob.setToolTipText("Week Begining Date");

        txtCurrentAddress.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtCurrentAddress.setToolTipText("Contact No");
        txtCurrentAddress.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCurrentAddress.setSelectionColor(new java.awt.Color(255, 255, 0));

        txtGuardianContact.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtGuardianContact.setToolTipText("Contact No");
        txtGuardianContact.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtGuardianContact.setSelectionColor(new java.awt.Color(255, 255, 0));

        txtGuardianName.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtGuardianName.setToolTipText("Contact No");
        txtGuardianName.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtGuardianName.setSelectionColor(new java.awt.Color(255, 255, 0));

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("Guardian Name");

        comboGrade.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        comboGrade.setForeground(new java.awt.Color(0, 0, 102));
        comboGrade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nursary", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Grade 13" }));

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("Student Name");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout PanelSubLayout = new javax.swing.GroupLayout(PanelSub);
        PanelSub.setLayout(PanelSubLayout);
        PanelSubLayout.setHorizontalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCurrentAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calDob, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGuardianName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGuardianContact, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSave, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelSubLayout.createSequentialGroup()
                                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(PanelSubLayout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(1, 1, 1)
                                        .addComponent(txtStudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelSubLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel12))
                                .addGap(7, 7, 7)
                                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelSubLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(0, 0, 0)
                                .addComponent(txtGuardianName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10)
                                .addGap(7, 7, 7)
                                .addComponent(txtGuardianContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelSubLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelSubLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(1, 1, 1)
                                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelSubLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel14)
                                                .addGap(1, 1, 1)
                                                .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(PanelSubLayout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCurrentAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(PanelSubLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 0, 0)
                                        .addComponent(calDob, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 6, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSubLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btSave))))
                    .addGroup(PanelSubLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/editIcon.png"))); // NOI18N
        btnEdit.setToolTipText("Edit Student Details");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btViewStudentDetails.setText("View Profile");
        btViewStudentDetails.setToolTipText("Veiw Student Details");
        btViewStudentDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btViewStudentDetailsActionPerformed(evt);
            }
        });

        txtSearchAttribute.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtSearchAttribute.setToolTipText("Search by Student Name / Registration Number");
        txtSearchAttribute.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtSearchAttribute.setSelectionColor(new java.awt.Color(255, 255, 0));
        txtSearchAttribute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchAttributeKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 102));
        jLabel18.setText(" Search");

        btAllStudents.setText("All Students Report");
        btAllStudents.setToolTipText("Veiw Student Details");
        btAllStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAllStudentsActionPerformed(evt);
            }
        });

        btStudentAddressPrintReport.setText("Post Letter");
        btStudentAddressPrintReport.setToolTipText("Veiw Student Details");
        btStudentAddressPrintReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStudentAddressPrintReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 382, Short.MAX_VALUE)
                .addComponent(btStudentAddressPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btAllStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btViewStudentDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearchAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18))
                    .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelMainLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btViewStudentDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btAllStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btStudentAddressPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(PanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        addStudent();
        setNextIdAndQrCode();

    }//GEN-LAST:event_btSaveActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        editSelectedStudent();
        loadStudentDataToTable();
    }//GEN-LAST:event_btnEditActionPerformed

    private void txtSearchAttributeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchAttributeKeyReleased
        searchStudentByMoreAttributes(txtSearchAttribute.getText().trim());
    }//GEN-LAST:event_txtSearchAttributeKeyReleased

    private void btViewStudentDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btViewStudentDetailsActionPerformed
        try {
            int selectedRow = tblStudentDetails.getSelectedRow();
            if (selectedRow != -1) {
                int studentId = Validations.getIntOrZeroFromString(tblStudentDetails.getValueAt(selectedRow, 0).toString());
                HashMap<String, Object> hm = new HashMap<>();
                hm.put("student_id", studentId);
                CommonController.printCommonReport("student_profile", hm);
            }
        } catch (SQLException | JRException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btViewStudentDetailsActionPerformed

    private void btAllStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAllStudentsActionPerformed
        try {
            HashMap<String, Object> hm = new HashMap<>();
            CommonController.printCommonReport("all_student_report", hm);
        } catch (SQLException | JRException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btAllStudentsActionPerformed

    private void btStudentAddressPrintReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStudentAddressPrintReportActionPerformed
        try {
            HashMap<String, Object> hm = new HashMap<>();
            CommonController.printCommonReport("all_student_report_post_letter_report", hm);
        } catch (SQLException | JRException ex) {
            Logger.getLogger(manageStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btStudentAddressPrintReportActionPerformed

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
            java.util.logging.Logger.getLogger(manageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new manageStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMain;
    private javax.swing.JPanel PanelSub;
    private javax.swing.JButton btAllStudents;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btStudentAddressPrintReport;
    private javax.swing.JButton btViewStudentDetails;
    private javax.swing.JButton btnEdit;
    private com.toedter.calendar.JDateChooser calDob;
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
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tblStudentDetails;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtCurrentAddress;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtGuardianContact;
    private javax.swing.JTextField txtGuardianName;
    private javax.swing.JTextField txtRegNo;
    private javax.swing.JTextField txtSearchAttribute;
    private javax.swing.JTextField txtStudentName;
    // End of variables declaration//GEN-END:variables
}
