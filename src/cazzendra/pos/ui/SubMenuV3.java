/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.control.lecturerController;
import cazzendra.pos.control.studentController;
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
public class SubMenuV3 extends javax.swing.JFrame {

    private String Lang;
    boolean PaidStatus = false;

    int StudentId = 0;
    student Student = null;

    /**
     * @param Language
     */
    public SubMenuV3(String Language) {
        initComponents();
        this.Lang = Language;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        lblDateTime.setText(dtf.format(now));
        setDefaults();
        panel.setBackground(Loading.getColorCode());
        lblLoggedUser.setText(Loading.getUser().getUserName());
        HotKeys();
    }

    private void setDefaults() {
        comboGrade.setEnabled(false);
        btnCloseApp.setBackground(new java.awt.Color(0, 0, 102));
        lblCopyrightStatement.setText(Loading.getCopyrightStatement());
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
            Logger.getLogger(SubMenuV3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void LoadSubjectsDataObjectsToComboBox() {
//        try {
//            ResultSet Rset = new subjectDaoImpl().getSubjectsByGrade(comboGrade.getSelectedItem().toString());
//            String[] 
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(SubMenuV3.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    private void SetSearchStudentDetails() {
        comboGrade.setEnabled(true);
        try {
            Student = studentController.getStudentByStudentId(StudentId);
            txtSearchStudentById.setText(Integer.toString(Student.getId()));
            if (Student != null) {
                lblName.setText("<html>" + Student.getName() + "</html>");
                txtGender.setText(Student.getGender());
                txtRegNo.setText(Student.getRegNo());
                txtContact1.setText(Student.getContactNo());
                txtEmail1.setText(Student.getEmail1());
                txtCuardianContact.setText(Student.getGuardianContact());
                txtAddress.setText(Student.getCurrentAddress());
                txtDOB.setText(Student.getDetail());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubMenuV3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
                Logger.getLogger(SubMenuV3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void ClearStudentDetails() {

        DefaultTableModel Dtm = (DefaultTableModel) tblLecturers.getModel();
        Dtm.setRowCount(0);
        DefaultTableModel Dtm2 = (DefaultTableModel) tblSubjects.getModel();
        Dtm2.setRowCount(0);

        comboGrade.setEnabled(false);
        lblName.setText("");
        txtGender.setText("");
        txtRegNo.setText("");
        txtContact1.setText("");
        txtEmail1.setText("");
        txtCuardianContact.setText("");
        txtAddress.setText("");
        txtDOB.setText("");
        txtSearchStudentById.setText("");
        txtSearchStudentById.requestFocus();
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
        lblCopyrightStatement = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSubjects = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLecturers = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        txtContact1 = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        txtCuardianContact = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtRegNo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblImage1 = new javax.swing.JLabel();
        btnApply = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        lblLoggedUser = new javax.swing.JLabel();
        lblnamePriceUpdate2 = new javax.swing.JLabel();
        btnCloseApp = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSearchStudentById = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        comboGrade = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        lblDateTime = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuIconLecturers = new javax.swing.JMenuItem();
        menuIconStudents = new javax.swing.JMenuItem();
        menuIconSubjects = new javax.swing.JMenuItem();
        menuIconUsers = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuIconLecturerPayments = new javax.swing.JMenuItem();
        menuIconStudentFees = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Attendance");
        setBackground(new java.awt.Color(0, 0, 102));
        setMinimumSize(new java.awt.Dimension(1368, 718));
        setResizable(false);
        setSize(new java.awt.Dimension(1368, 718));

        panel.setBackground(new java.awt.Color(0, 0, 102));
        panel.setMaximumSize(new java.awt.Dimension(1360, 600));
        panel.setMinimumSize(new java.awt.Dimension(1360, 600));
        panel.setPreferredSize(new java.awt.Dimension(1360, 600));

        lblCopyrightStatement.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        lblCopyrightStatement.setForeground(new java.awt.Color(0, 0, 102));
        lblCopyrightStatement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCopyrightStatement.setText("######");

        tblSubjects.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
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
        tblSubjects.setRowHeight(40);
        tblSubjects.setRowMargin(5);
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

        tblLecturers.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
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
        tblLecturers.setRowHeight(30);
        tblLecturers.setRowMargin(5);
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

        jPanel2.setOpaque(false);

        lblName.setFont(new java.awt.Font("Ubuntu Medium", 1, 36)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setText("Name");
        lblName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        lblName.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("CONTACT ");

        jLabel4.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("EMAIL");

        jLabel5.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("ADDRESS");

        jLabel8.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("GUARDIAN CONTACT");

        jLabel7.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("DATE OF BIRTH");

        jLabel27.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 102));
        jLabel27.setText("GENDER");

        txtDOB.setEditable(false);
        txtDOB.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtDOB.setForeground(new java.awt.Color(153, 0, 0));
        txtDOB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtGender.setEditable(false);
        txtGender.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtGender.setForeground(new java.awt.Color(153, 0, 0));
        txtGender.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtContact1.setEditable(false);
        txtContact1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtContact1.setForeground(new java.awt.Color(153, 0, 0));
        txtContact1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtEmail1.setEditable(false);
        txtEmail1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtEmail1.setForeground(new java.awt.Color(153, 0, 0));
        txtEmail1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtCuardianContact.setEditable(false);
        txtCuardianContact.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCuardianContact.setForeground(new java.awt.Color(153, 0, 0));
        txtCuardianContact.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtAddress.setEditable(false);
        txtAddress.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(153, 0, 0));
        txtAddress.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        txtRegNo.setEditable(false);
        txtRegNo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtRegNo.setForeground(new java.awt.Color(153, 0, 0));
        txtRegNo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel9.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("REG. NO");

        lblImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/cloudrevel/kek/icon/user.png"))); // NOI18N
        lblImage1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblImage1)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGender, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRegNo))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtContact1))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                                    .addComponent(txtCuardianContact, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDOB))))
                        .addGap(0, 60, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblImage1)
                        .addGap(51, 51, 51)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuardianContact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDOB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        btnApply.setBackground(new java.awt.Color(0, 153, 204));
        btnApply.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        btnApply.setForeground(new java.awt.Color(0, 0, 102));
        btnApply.setText("PAY & MARK");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 102));
        jLabel25.setText("Logged As ");

        lblLoggedUser.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        lblLoggedUser.setForeground(new java.awt.Color(255, 0, 0));
        lblLoggedUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLoggedUser.setText("User Name");
        lblLoggedUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoggedUserMouseClicked(evt);
            }
        });

        lblnamePriceUpdate2.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblnamePriceUpdate2.setForeground(new java.awt.Color(255, 255, 255));
        lblnamePriceUpdate2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/logged_user.png"))); // NOI18N
        lblnamePriceUpdate2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblnamePriceUpdate2MouseClicked(evt);
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

        jLabel20.setFont(new java.awt.Font("Ubuntu Medium", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 102));
        jLabel20.setText("STUDENT SELECTION");

        txtSearchStudentById.setFont(new java.awt.Font("Ubuntu Medium", 0, 24)); // NOI18N
        txtSearchStudentById.setForeground(new java.awt.Color(0, 0, 102));
        txtSearchStudentById.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchStudentByIdActionPerformed(evt);
            }
        });
        txtSearchStudentById.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchStudentByIdKeyReleased(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ttms/labelIcons2/searchIcon.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

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

        lblDateTime.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        lblDateTime.setForeground(new java.awt.Color(0, 0, 102));
        lblDateTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDateTime.setText("2020-01-14");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(817, 817, 817)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel25))
                            .addComponent(lblLoggedUser, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(lblnamePriceUpdate2)
                        .addGap(46, 46, 46)
                        .addComponent(btnCloseApp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(txtSearchStudentById, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel22))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblCopyrightStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 1340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel25)
                        .addGap(6, 6, 6)
                        .addComponent(lblLoggedUser))
                    .addComponent(lblnamePriceUpdate2)
                    .addComponent(btnCloseApp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel22)
                        .addGap(3, 3, 3)
                        .addComponent(comboGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel21)
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearchStudentById, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCopyrightStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jMenu1.setText("Manage");

        menuIconLecturers.setText("Lecturer");
        menuIconLecturers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIconLecturersActionPerformed(evt);
            }
        });
        jMenu1.add(menuIconLecturers);

        menuIconStudents.setText("Students");
        menuIconStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIconStudentsActionPerformed(evt);
            }
        });
        jMenu1.add(menuIconStudents);

        menuIconSubjects.setText("Subject");
        menuIconSubjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIconSubjectsActionPerformed(evt);
            }
        });
        jMenu1.add(menuIconSubjects);

        menuIconUsers.setText("User");
        menuIconUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIconUsersActionPerformed(evt);
            }
        });
        jMenu1.add(menuIconUsers);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Payments");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        menuIconLecturerPayments.setText("Lecturer Payments");
        menuIconLecturerPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuIconLecturerPaymentsActionPerformed(evt);
            }
        });
        jMenu2.add(menuIconLecturerPayments);

        menuIconStudentFees.setText("Student Fees");
        jMenu2.add(menuIconStudentFees);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1368, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblLoggedUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoggedUserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblLoggedUserMouseClicked

    private void lblnamePriceUpdate2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblnamePriceUpdate2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblnamePriceUpdate2MouseClicked

    private void btnCloseAppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseAppMouseClicked
        try {
            boolean status = CommonController.Activation();
            if (status) {
                try {
                    CommonController.AutoBackupDB();
                } catch (URISyntaxException | IOException | InterruptedException ex) {
                    Logger.getLogger(SubMenuV3.class.getName()).log(Level.SEVERE, null, ex);
                }
                new Login().setVisible(true);
                this.dispose();
            } else {
                new ActivationUI().setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubMenuV3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCloseAppMouseClicked

    private void comboGradePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_comboGradePopupMenuWillBecomeInvisible
        LoadSubjectsTotable();
    }//GEN-LAST:event_comboGradePopupMenuWillBecomeInvisible

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchStudent searchStudent = new searchStudent(this, true);
        searchStudent.setVisible(true);
        StudentId = searchStudent.getSelectedLecturerId();
        if (StudentId != 0) {
            SetSearchStudentDetails();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchStudentByIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchStudentByIdActionPerformed
        if (StudentId != 0) {
            SetSearchStudentDetails();
        }
    }//GEN-LAST:event_txtSearchStudentByIdActionPerformed

    private void tblSubjectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectsMouseClicked
        loadLecturerDetailstoTable();
    }//GEN-LAST:event_tblSubjectsMouseClicked

    private void tblSubjectsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSubjectsMouseExited
        loadLecturerDetailstoTable();
    }//GEN-LAST:event_tblSubjectsMouseExited

    private void txtSearchStudentByIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchStudentByIdKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SetSearchStudentDetails();
        }
    }//GEN-LAST:event_txtSearchStudentByIdKeyReleased

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

    private void menuIconLecturersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIconLecturersActionPerformed
        new manageLecturersNewUI().setVisible(true);
    }//GEN-LAST:event_menuIconLecturersActionPerformed

    private void menuIconStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIconStudentsActionPerformed
        new manageStudents().setVisible(true);
    }//GEN-LAST:event_menuIconStudentsActionPerformed

    private void menuIconSubjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIconSubjectsActionPerformed
        new manageSubjects().setVisible(true);
    }//GEN-LAST:event_menuIconSubjectsActionPerformed

    private void menuIconUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIconUsersActionPerformed
        new userManagement().setVisible(true);
    }//GEN-LAST:event_menuIconUsersActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        new managePayments().setVisible(true);
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void menuIconLecturerPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuIconLecturerPaymentsActionPerformed
        new Commission("English").setVisible(true);
    }//GEN-LAST:event_menuIconLecturerPaymentsActionPerformed

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
            java.util.logging.Logger.getLogger(SubMenuV3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SubMenuV3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SubMenuV3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubMenuV3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new SubMenuV3("test param").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JLabel btnCloseApp;
    private javax.swing.JButton btnSearch;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> comboGrade;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCopyrightStatement;
    private javax.swing.JLabel lblDateTime;
    private javax.swing.JLabel lblImage1;
    private javax.swing.JLabel lblLoggedUser;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblnamePriceUpdate2;
    private javax.swing.JMenuItem menuIconLecturerPayments;
    private javax.swing.JMenuItem menuIconLecturers;
    private javax.swing.JMenuItem menuIconStudentFees;
    private javax.swing.JMenuItem menuIconStudents;
    private javax.swing.JMenuItem menuIconSubjects;
    private javax.swing.JMenuItem menuIconUsers;
    private javax.swing.JPanel panel;
    private javax.swing.JTable tblLecturers;
    private javax.swing.JTable tblSubjects;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContact1;
    private javax.swing.JTextField txtCuardianContact;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtRegNo;
    private javax.swing.JTextField txtSearchStudentById;
    // End of variables declaration//GEN-END:variables
}
