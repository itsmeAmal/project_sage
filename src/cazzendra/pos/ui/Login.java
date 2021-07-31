package cazzendra.pos.ui;

import cazzendra.pos.control.UserControl;
import cazzendra.pos.core.Loading;
import cazzendra.pos.core.Options;
import cazzendra.pos.model.User;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Amal
 */
public class Login extends javax.swing.JFrame {

    private String Language;

    /**
     * Creates new form ActivationUI
     */
    public Login() {
        initComponents();
        panel.setBackground(Loading.getColorCode());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        lblTimeDay.setText(Integer.toString(now.getDayOfMonth()));
        lblTimeYear.setText(Integer.toString(now.getYear()));
        lblTimeMonth.setText(now.getMonth().toString());
        lblCopyrightStatement.setText(Loading.getCopyrightStatement());
        comboLanguage.setVisible(false);
    }

    private void login() {
        try {
            String uname = txtUserName.getText();
            String pw = txtPwField.getText();

            boolean access = UserControl.getUserByUnameAndPw(uname, pw);
            if (access) {
                if (comboLanguage.getSelectedItem().toString().equalsIgnoreCase("English")) {
                    Language = Options.LANG_ENGLISH;
                } else if (comboLanguage.getSelectedItem().toString().equalsIgnoreCase("Sinhala")) {
                    Language = Options.LANG_SINHALA;
                }
                User user = UserControl.getUserByUserNameAndPw(uname, pw);
                Loading.setUser(user);
                new MainMenuV3(Language).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Username or Password is incorrect !", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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

        panel = new javax.swing.JPanel();
        panelLogin = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtPwField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        lblTimeDay = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblTimeYear = new javax.swing.JLabel();
        lblTimeMonth = new javax.swing.JLabel();
        lblCopyrightStatement = new javax.swing.JLabel();
        comboLanguage = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login ");
        setMinimumSize(new java.awt.Dimension(570, 412));
        setResizable(false);

        panel.setBackground(new java.awt.Color(0, 102, 102));
        panel.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
        panel.setMaximumSize(new java.awt.Dimension(325, 412));
        panel.setMinimumSize(new java.awt.Dimension(325, 412));
        panel.setPreferredSize(new java.awt.Dimension(325, 412));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelLogin.setOpaque(false);

        txtUserName.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("USER NAME ");

        lblLogin.setBackground(new java.awt.Color(0, 0, 102));
        lblLogin.setFont(new java.awt.Font("Ubuntu Medium", 1, 18)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(255, 255, 255));
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("LOGIN");
        lblLogin.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogin.setOpaque(true);
        lblLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lblLoginFocusGained(evt);
            }
        });
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setText("PASSWORD");

        txtPwField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPwFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPwField, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel12)
                .addGap(9, 9, 9)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel13)
                .addGap(8, 8, 8)
                .addComponent(txtPwField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        panel.add(panelLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 300, 280));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, -1));

        lblTimeDay.setFont(new java.awt.Font("Arial Black", 0, 48)); // NOI18N
        lblTimeDay.setForeground(new java.awt.Color(0, 0, 102));
        lblTimeDay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTimeDay.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panel.add(lblTimeDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 70, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/logo/cloud-revel-logo.png"))); // NOI18N
        panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 150, 150));

        lblTimeYear.setFont(new java.awt.Font("Arial Black", 0, 100)); // NOI18N
        lblTimeYear.setForeground(new java.awt.Color(0, 0, 102));
        lblTimeYear.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panel.add(lblTimeYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 280, 100));

        lblTimeMonth.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        lblTimeMonth.setForeground(new java.awt.Color(0, 0, 102));
        lblTimeMonth.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        panel.add(lblTimeMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 190, 60));

        lblCopyrightStatement.setFont(new java.awt.Font("Ubuntu Light", 0, 14)); // NOI18N
        lblCopyrightStatement.setForeground(new java.awt.Color(0, 0, 102));
        lblCopyrightStatement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCopyrightStatement.setText("######");
        panel.add(lblCopyrightStatement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 660, -1));

        comboLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Sinhala" }));
        panel.add(comboLanguage, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 150, 30));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/logo/sage_png_4.png"))); // NOI18N
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 340, 210, 90));

        jLabel2.setToolTipText("");
        panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        txtPwField.requestFocus();
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
        login();
    }//GEN-LAST:event_lblLoginMouseClicked

    private void txtPwFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPwFieldActionPerformed
//            txtReorderLevel.requestFocus();
        login();
    }//GEN-LAST:event_txtPwFieldActionPerformed

    private void lblLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblLoginFocusGained

    }//GEN-LAST:event_lblLoginFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new test_().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboLanguage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCopyrightStatement;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblTimeDay;
    private javax.swing.JLabel lblTimeMonth;
    private javax.swing.JLabel lblTimeYear;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPasswordField txtPwField;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
