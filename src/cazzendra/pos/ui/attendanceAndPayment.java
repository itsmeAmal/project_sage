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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Amal
 */
public class attendanceAndPayment extends javax.swing.JFrame {

    private String Month = "";
    private String Year = "";

    /**
     * Creates new form addStudent
     */
    public attendanceAndPayment() {
        initComponents();
        //------------------
        LocalDateTime now = LocalDateTime.now();
        lblMonthAndYear.setText(now.getMonth().toString() + " / " + Integer.toString(now.getYear()));
        Month = now.getMonth().toString();
        Year = Integer.toString(now.getYear());
        //------------------DEFAULT COMPONENT SETUP
        chkBoxMonthlyFee.setSelected(false);
        dateChooserPaymentDate.setDate(CommonController.getCurrentJavaSqlDate());
        //------------------
        loadDataToTable();
        calculateFeeTotal();
        loadLecturerCode();
        txtAttendedStudentCount.setText(Integer.toString(tblAttendanceV3.getRowCount()));
        loadLecturerCodeForSearchFilters();
        loadLecturerCode2();
        loadLecturerCode3();

    }

    private void clearAll() {
        txtClassFee.setText("");
        txtStudentCode.setText("");
        chkBoxMonthlyFee.setSelected(false);
        dateChooserPaymentDate.setDate(CommonController.getCurrentJavaSqlDate());
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
            if (dateChooserPaymentDate.getDate() == null || "".equals(dateChooserPaymentDate.getDate().toString())) {
                JOptionPane.showMessageDialog(this, "Select Payment Date !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String isMonthlyFee = "";
            if (chkBoxMonthlyFee.isSelected()) {
                isMonthlyFee = "Monthly Fee";
            } else {
                isMonthlyFee = "Day Fee";
            }

            AttendanceV3Controller.addAttendance(prefixCode, studentCode, Validations.getSqlDateByUtilDate(dateChooserPaymentDate.getDate()),
                    Validations.getBigDecimalOrZeroFromString(txtClassFee.getText().trim()), isMonthlyFee, lecturer.getName(), student.getName());

            clearAll();
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void searchPaymentsByStudentCode(String studenCode) {
        try {
            if ((txtStudentCode.getText().trim().length() > 3)) {
                String studentCode = studenCode.substring(2);
                ResultSet rset = AttendanceV3Controller.getByOneAttribute("student_code", CommonConstants.sql.LIKE, "%" + studentCode + "%");
                String[] columnList = {"id", "att_date", "lec_code", "lecturer_name", "student_code", "student_name", "fee", "remark"};
                CommonController.loadDataToTable(tblAttendanceV3, rset, columnList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataToTable() {
        try {
            ArrayList<String[]> attributeConditionValueList = new ArrayList<>();

            String[] ACV1 = {"MONTHNAME(att_date)", CommonConstants.sql.EQUAL, Month};
            attributeConditionValueList.add(ACV1);

            String[] ACV2 = {"YEAR(att_date)", CommonConstants.sql.EQUAL, Year};
            attributeConditionValueList.add(ACV2);

            ResultSet rset = AttendanceV3Controller.getByMoreAttributes(attributeConditionValueList, CommonConstants.sql.AND);
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
            CommonController.loadDataToComboBox(comboLecturerCodes2, rset, "lecturer_prefix_code");
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLecturerCode2() {
        try {
            ResultSet rset = lecturerController.getAllLecturers();
            CommonController.loadDataToComboBox(comboLecturerCodes1, rset, "lecturer_prefix_code");
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLecturerCode3() {
        try {
            ResultSet rset = lecturerController.getAllLecturers();
            CommonController.loadDataToComboBox(comboLecturerCodes3, rset, "lecturer_prefix_code");
        } catch (SQLException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadLecturerCodeForSearchFilters() {
        try {
            ResultSet rset = lecturerController.getAllLecturers();
            CommonController.loadDataToComboBox(comboLecturerCodes2, rset, "lecturer_prefix_code");
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
        chkBoxMonthlyFee = new javax.swing.JCheckBox();
        dateChooserPaymentDate = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtFeeTotal = new javax.swing.JTextField();
        txtAttendedStudentCount = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        dateChooserFromDate3 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txtCommissionRate3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        comboLecturerCodes3 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        dateChooserFromDate2 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboLecturerCodes2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        dateClassDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboLecturerCodes1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btNotAttendedReport = new javax.swing.JButton();
        comboGrade2 = new javax.swing.JComboBox<>();
        btnEdit = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        lblMonthAndYear = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        dateChooserFromDate = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        comboLecturerPrefixCode = new javax.swing.JComboBox<>();
        btSave1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attendance & Payment");
        setMaximumSize(new java.awt.Dimension(1159, 664));
        setMinimumSize(new java.awt.Dimension(1159, 664));
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
            tblAttendanceV3.getColumnModel().getColumn(2).setMinWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(2).setMaxWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(3).setResizable(false);
            tblAttendanceV3.getColumnModel().getColumn(4).setMinWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(4).setMaxWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(5).setResizable(false);
            tblAttendanceV3.getColumnModel().getColumn(6).setMinWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(6).setMaxWidth(100);
            tblAttendanceV3.getColumnModel().getColumn(7).setMinWidth(150);
            tblAttendanceV3.getColumnModel().getColumn(7).setPreferredWidth(150);
            tblAttendanceV3.getColumnModel().getColumn(7).setMaxWidth(150);
        }

        PanelSub.setBackground(new java.awt.Color(0, 153, 153));

        txtStudentCode.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtStudentCode.setToolTipText("");
        txtStudentCode.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtStudentCode.setSelectionColor(new java.awt.Color(255, 255, 0));
        txtStudentCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStudentCodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStudentCodeKeyTyped(evt);
            }
        });

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

        chkBoxMonthlyFee.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chkBoxMonthlyFee.setForeground(new java.awt.Color(255, 255, 255));
        chkBoxMonthlyFee.setText("Monthly Fee");
        chkBoxMonthlyFee.setToolTipText("Monthly Fee");
        chkBoxMonthlyFee.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        chkBoxMonthlyFee.setOpaque(false);

        dateChooserPaymentDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Payment Date");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkBoxMonthlyFee)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(dateChooserPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelSubLayout.setVerticalGroup(
            PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel21))
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserPaymentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelSubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtStudentCode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtClassFee, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(chkBoxMonthlyFee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel26.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("Student Count");

        jLabel28.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Fee Total ");

        txtFeeTotal.setEditable(false);
        txtFeeTotal.setBackground(new java.awt.Color(0, 153, 153));
        txtFeeTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtFeeTotal.setForeground(new java.awt.Color(255, 255, 255));
        txtFeeTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtAttendedStudentCount.setEditable(false);
        txtAttendedStudentCount.setBackground(new java.awt.Color(0, 153, 153));
        txtAttendedStudentCount.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtAttendedStudentCount.setForeground(new java.awt.Color(255, 255, 255));
        txtAttendedStudentCount.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Attendance & Lecturer Commission Fee Report ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 56)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("SAGE");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("KEKIRAWA");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Date");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Commission Rate ( % )");

        txtCommissionRate3.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtCommissionRate3.setToolTipText("");
        txtCommissionRate3.setSelectedTextColor(new java.awt.Color(0, 0, 0));
        txtCommissionRate3.setSelectionColor(new java.awt.Color(255, 255, 0));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Lecturer Code");

        comboLecturerCodes3.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        comboLecturerCodes3.setForeground(new java.awt.Color(0, 0, 102));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("<html><center>Print Lecturer Commission Report</center></html>");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboLecturerCodes3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserFromDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCommissionRate3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(dateChooserFromDate3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(7, 7, 7)
                .addComponent(txtCommissionRate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(comboLecturerCodes3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Lecturer Code");

        comboLecturerCodes2.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        comboLecturerCodes2.setForeground(new java.awt.Color(0, 0, 102));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("<html><center>Print Attended Student Report</center></html>");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserFromDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLecturerCodes2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(dateChooserFromDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboLecturerCodes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Class Date");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Lecturer Code");

        comboLecturerCodes1.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        comboLecturerCodes1.setForeground(new java.awt.Color(0, 0, 102));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Grade");

        btNotAttendedReport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btNotAttendedReport.setText("<html><center>Print Not Attended Student Report</center></html>");
        btNotAttendedReport.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNotAttendedReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNotAttendedReportActionPerformed(evt);
            }
        });

        comboGrade2.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        comboGrade2.setForeground(new java.awt.Color(0, 0, 102));
        comboGrade2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nursary", "Grade 1", "Grade 2", "Grade 3", "Grade 4", "Grade 5", "Grade 6", "Grade 7", "Grade 8", "Grade 9", "Grade 10", "Grade 11", "Grade 12", "Grade 13" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboGrade2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNotAttendedReport, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateClassDate, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLecturerCodes1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(dateClassDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(comboLecturerCodes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(comboGrade2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btNotAttendedReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 485, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/editIcon.png"))); // NOI18N
        btnEdit.setToolTipText("Edit Student Details");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Payment Details :");

        lblMonthAndYear.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        lblMonthAndYear.setForeground(new java.awt.Color(255, 255, 255));
        lblMonthAndYear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMonthAndYear.setText("Month & Year");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search Filters", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel5.setOpaque(false);

        jLabel24.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Class Date");

        dateChooserFromDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Ubuntu Medium", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Lecturer");

        comboLecturerPrefixCode.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(dateChooserFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(comboLecturerPrefixCode, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(75, 75, 75))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(dateChooserFromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(comboLecturerPrefixCode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btSave1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelMainLayout = new javax.swing.GroupLayout(PanelMain);
        PanelMain.setLayout(PanelMainLayout);
        PanelMainLayout.setHorizontalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 10, Short.MAX_VALUE))
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelSub, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelMainLayout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelMainLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblMonthAndYear, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(215, 215, 215)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelMainLayout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAttendedStudentCount, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFeeTotal))
                                    .addGroup(PanelMainLayout.createSequentialGroup()
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        PanelMainLayout.setVerticalGroup(
            PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMainLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(PanelSub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelMainLayout.createSequentialGroup()
                        .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelMainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblMonthAndYear, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelMainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAttendedStudentCount, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)
                                    .addComponent(txtFeeTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(3, 3, 3)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
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

    private void btNotAttendedReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNotAttendedReportActionPerformed
        try {
            if (dateClassDate.getDate() != null) {
                HashMap<String, Object> hm = new HashMap<>();
                hm.put("class_date", Validations.getSqlDateByUtilDate(dateClassDate.getDate()));
                hm.put("grade", comboGrade2.getSelectedItem().toString());
                hm.put("lec_code", comboLecturerCodes1.getSelectedItem().toString());
                CommonController.printCommonReport("not_attended_student_report", hm);
            }
        } catch (SQLException | JRException ex) {
            Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btNotAttendedReportActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (dateChooserFromDate2.getDate() != null) {
            try {
                HashMap<String, Object> hm = new HashMap<>();
                hm.put("class_date", Validations.getSqlDateByUtilDate(dateChooserFromDate2.getDate()));
                hm.put("lecturer_code", comboLecturerCodes2.getSelectedItem().toString());
                CommonController.printCommonReport("attendance_report_by_date", hm);
            } catch (SQLException | JRException ex) {
                Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (dateChooserFromDate3.getDate() != null) {
            try {
                HashMap<String, Object> hm = new HashMap<>();
                hm.put("class_date", Validations.getSqlDateByUtilDate(dateChooserFromDate3.getDate()));
                hm.put("lecturer_code", comboLecturerCodes3.getSelectedItem().toString());
                hm.put("commission_rate", Validations.getBigDecimalOrZeroFromString(txtCommissionRate3.getText().trim()));
                CommonController.printCommonReport("lecturer_comission_report", hm);
            } catch (SQLException | JRException ex) {
                Logger.getLogger(attendanceAndPayment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int selectedRow = tblAttendanceV3.getSelectedRow();
        if (selectedRow != -1) {
            int paymentId = Validations.getIntOrZeroFromString(tblAttendanceV3.getValueAt(selectedRow, 0).toString());
            AuthenticateJDialog authenticateJDialog = new AuthenticateJDialog(this, true);
            authenticateJDialog.setVisible(true);
            boolean auth = authenticateJDialog.getAuthenticateStatus();
            if (auth) {
                EditPayment editPayment = new EditPayment(this, true, paymentId);
                editPayment.setVisible(true);
                loadDataToTable();
                calculateFeeTotal();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select record to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEditActionPerformed

    private void txtStudentCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentCodeKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStudentCodeKeyTyped

    private void txtStudentCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentCodeKeyReleased
        searchPaymentsByStudentCode(txtStudentCode.getText().trim());
    }//GEN-LAST:event_txtStudentCodeKeyReleased

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
    private javax.swing.JButton btNotAttendedReport;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btSave1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JCheckBox chkBoxMonthlyFee;
    private javax.swing.JComboBox<String> comboGrade2;
    private javax.swing.JComboBox<String> comboLecturerCodes1;
    private javax.swing.JComboBox<String> comboLecturerCodes2;
    private javax.swing.JComboBox<String> comboLecturerCodes3;
    private javax.swing.JComboBox<String> comboLecturerPrefixCode;
    private com.toedter.calendar.JDateChooser dateChooserFromDate;
    private com.toedter.calendar.JDateChooser dateChooserFromDate2;
    private com.toedter.calendar.JDateChooser dateChooserFromDate3;
    private com.toedter.calendar.JDateChooser dateChooserPaymentDate;
    private com.toedter.calendar.JDateChooser dateClassDate;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMonthAndYear;
    private javax.swing.JTable tblAttendanceV3;
    private javax.swing.JTextField txtAttendedStudentCount;
    private javax.swing.JTextField txtClassFee;
    private javax.swing.JTextField txtCommissionRate3;
    private javax.swing.JTextField txtFeeTotal;
    private javax.swing.JTextField txtStudentCode;
    // End of variables declaration//GEN-END:variables
}
