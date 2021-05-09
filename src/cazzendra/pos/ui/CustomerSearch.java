/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.ui;

import cazzendra.pos.core.CommonConstants;
import cazzendra.pos.core.Loading;
import cazzendra.pos.core.Options;
import cazzendra.pos.core.Validations;
import cloudrevel.lnguage.src.keys.LangSinhala;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;

/**
 *
 * @author Amal
 */
public class CustomerSearch extends javax.swing.JDialog {

    private String Lang;

    /**
     * Creates new form ItemSearch
     *
     * @param parent
     * @param modal
     * @param Languge
     */
    public CustomerSearch(java.awt.Frame parent, boolean modal, String Languge) {
        super(parent, modal);
        initComponents();
//        loadDataToTable();
        panel.setBackground(Loading.getColorCode());
        setHotKey();
        txtSearchAttribute.requestFocus();
        Loading.customJButton(0, 0, 102, btnOk);
        this.Lang = Languge;
        SetLanguageUi();
    }

    private void setHotKey() {

        KeyStroke tab = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
        InputMap inputMap2 = btnSearch.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(tab, "B");

        btnSearch.getActionMap().put("B", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                okButtonAction();
            }
        });

        KeyStroke tab2 = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        InputMap inputMap3 = btnSearch.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        inputMap3.put(tab2, "B");

        btnSearch.getActionMap().put("B", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tblCustomers.requestFocus();
            }
        });

        KeyStroke tab4 = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        InputMap inputMap4 = btnSearch.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW);
        inputMap4.put(tab4, "B");

        btnOk.getActionMap().put("B", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                okButtonAction();
            }
        });

        createKeybindings(tblCustomers);
    }

    private void createKeybindings(JTable table) {
        table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
        table.getActionMap().put("Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                okButtonAction();
            }
        });
    }

//    private void loadDataToTable() {
//        try {
//            ResultSet rset = CustomerControl.getAllCustomers();
//            String[] columnList = {"customer_id", "customer_name", "customer_address", "customer_contact_1", "customer_contact_2"};
//            CommonController.loadDataToTable(tblCustomers, rset, columnList);
//        } catch (SQLException ex) {
//            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public int selectedCustomer() {
        int customerId = 0;
        int selectedRow = tblCustomers.getSelectedRow();
        if (selectedRow != -1) {
            customerId = Validations.getIntOrZeroFromString(tblCustomers.getValueAt(selectedRow, 0).toString());
        } else {
            tblCustomers.requestFocus();
        }
        return customerId;
    }

    private void searchCustomer() {
        ArrayList<String[]> attributeCOnditionValueList = new ArrayList<>();

        String[] acv1 = {"customer_name", CommonConstants.sql.LIKE, "%" + txtSearchAttribute.getText().trim() + "%"};
        attributeCOnditionValueList.add(acv1);

        String[] acv5 = {"customer_contact_1", CommonConstants.sql.LIKE, "%" + txtSearchAttribute.getText().trim() + "%"};
        attributeCOnditionValueList.add(acv5);

        String[] acv2 = {"customer_contact_2", CommonConstants.sql.LIKE, "%" + txtSearchAttribute.getText().trim() + "%"};
        attributeCOnditionValueList.add(acv2);

        String[] acv3 = {"customer_address", CommonConstants.sql.LIKE, "%" + txtSearchAttribute.getText().trim() + "%"};
        attributeCOnditionValueList.add(acv3);

//        try {
//            ResultSet rset = CustomerControl.getCustomersByMoreAttribute(attributeCOnditionValueList, CommonConstants.sql.OR);
//            String[] columnList = {"customer_id", "customer_name", "customer_address", "customer_contact_1", "customer_contact_2", "customer_email", "customer_detail"};
//            CommonController.loadDataToTable(tblCustomers, rset, columnList);
//
//        } catch (SQLException ex) {
////            Logger.getLogger(CustomerManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void okButtonAction() {
        int custId = selectedCustomer();
        if (custId != 0) {
            this.dispose();
        }
    }

    private void SetLanguageUi() {
        tblCustomers.getTableHeader().setVisible(false);
        tblCustomers.getTableHeader().removeAll();
        panel.remove(pnlTblHdr);
        panel.remove(jScrollPane1);
        panel.add(pnlTblHdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 124, 840, 35));
        panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 126, 840, 350));
        String FolderName = "mainmenu";
        if (Lang.equalsIgnoreCase(Options.LANG_ENGLISH)) {

            new Loading().CustomLangImage(FolderName, LangSinhala.LABEL_SEARCH_CUSTOMER_BY_NAME_ADDRESS_CONTACT, lblSearchCustomerBy);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_NAME, tblName);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_ADDRESS, tblAddress);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_CONTACT1, tblContact1);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_CONTACT2, tblContact2);

        } else if (Lang.equalsIgnoreCase(Options.LANG_SINHALA)) {

            new Loading().CustomLangImage(FolderName, LangSinhala.LABEL_SEARCH_CUSTOMER_BY_NAME_ADDRESS_CONTACT_SINHALA, lblSearchCustomerBy);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_NAME_SINHALA, tblName);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_ADDRESS_SINHALA, tblAddress);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_CONTACT1_SINHALA, tblContact1);
            new Loading().CustomLangImage(FolderName, LangSinhala.TBL_HDR_CONTACT2_SINHALA, tblContact2);
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
        txtSearchAttribute = new javax.swing.JTextField();
        pnlTblHdr = new javax.swing.JPanel();
        tblContact2 = new javax.swing.JLabel();
        tblName = new javax.swing.JLabel();
        tblAddress = new javax.swing.JLabel();
        tblContact1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomers = new javax.swing.JTable();
        btnOk = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        lblSearchCustomerBy = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Search");
        setAlwaysOnTop(true);
        setResizable(false);

        panel.setMaximumSize(new java.awt.Dimension(1024, 720));
        panel.setMinimumSize(new java.awt.Dimension(1024, 720));
        panel.setPreferredSize(new java.awt.Dimension(1024, 720));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearchAttribute.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        txtSearchAttribute.setToolTipText("Search by Item Code, Item Name or Item Barcode");
        txtSearchAttribute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchAttributeKeyReleased(evt);
            }
        });
        panel.add(txtSearchAttribute, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 450, 40));

        pnlTblHdr.setBackground(new java.awt.Color(0, 0, 102));

        tblContact2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tblContact2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudrevel/language/src/mainmenu/tbl-hdr-contact2-sinhala.png"))); // NOI18N

        tblName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudrevel/language/src/mainmenu/tbl-hdr-name-sinhala.png"))); // NOI18N

        tblAddress.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudrevel/language/src/mainmenu/tbl-hdr-address-sinhala.png"))); // NOI18N

        tblContact1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tblContact1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudrevel/language/src/mainmenu/tbl-hdr-contact1-sinhala.png"))); // NOI18N

        javax.swing.GroupLayout pnlTblHdrLayout = new javax.swing.GroupLayout(pnlTblHdr);
        pnlTblHdr.setLayout(pnlTblHdrLayout);
        pnlTblHdrLayout.setHorizontalGroup(
            pnlTblHdrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTblHdrLayout.createSequentialGroup()
                .addComponent(tblName, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(tblContact2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlTblHdrLayout.setVerticalGroup(
            pnlTblHdrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tblContact2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(tblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tblAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tblContact1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panel.add(pnlTblHdr, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 840, 35));

        tblCustomers.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        tblCustomers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Address", "Contact 1", "Contact 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCustomers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblCustomers.getTableHeader().setFont(new Font("Ubuntu", Font.BOLD, 18));
        tblCustomers.getTableHeader().setOpaque(false);
        tblCustomers.getTableHeader().setBackground(new Color(0, 0, 102));
        tblCustomers.getTableHeader().setForeground(new Color(255, 255, 255));
        tblCustomers.setRowHeight(22);
        tblCustomers.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblCustomersFocusLost(evt);
            }
        });
        tblCustomers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblCustomersKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomers);
        if (tblCustomers.getColumnModel().getColumnCount() > 0) {
            tblCustomers.getColumnModel().getColumn(0).setMinWidth(0);
            tblCustomers.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCustomers.getColumnModel().getColumn(0).setMaxWidth(0);
            tblCustomers.getColumnModel().getColumn(1).setMinWidth(200);
            tblCustomers.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblCustomers.getColumnModel().getColumn(1).setMaxWidth(200);
            tblCustomers.getColumnModel().getColumn(3).setMinWidth(150);
            tblCustomers.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblCustomers.getColumnModel().getColumn(3).setMaxWidth(150);
            tblCustomers.getColumnModel().getColumn(4).setMinWidth(150);
            tblCustomers.getColumnModel().getColumn(4).setPreferredWidth(150);
            tblCustomers.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        panel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 116, 840, 350));

        btnOk.setBackground(new java.awt.Color(255, 255, 255));
        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/newtheme/icons/facebook-like-32.png"))); // NOI18N
        btnOk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });
        panel.add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 490, 80, 40));

        btnSearch.setBackground(new java.awt.Color(255, 255, 255));
        btnSearch.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cazzendra/pos/ui/icons/search_32x32.png"))); // NOI18N
        panel.add(btnSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 390, 40, 40));

        lblSearchCustomerBy.setFont(new java.awt.Font("Ubuntu Medium", 0, 18)); // NOI18N
        lblSearchCustomerBy.setForeground(new java.awt.Color(0, 0, 102));
        lblSearchCustomerBy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudrevel/language/src/mainmenu/search-customer-by-name-address-contact-sinhala.png"))); // NOI18N
        panel.add(lblSearchCustomerBy, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 558, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        okButtonAction();
    }//GEN-LAST:event_btnOkActionPerformed

    private void tblCustomersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblCustomersKeyPressed

    }//GEN-LAST:event_tblCustomersKeyPressed

    private void txtSearchAttributeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchAttributeKeyReleased
        searchCustomer();
    }//GEN-LAST:event_txtSearchAttributeKeyReleased

    private void tblCustomersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblCustomersFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tblCustomersFocusLost

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
            java.util.logging.Logger.getLogger(CustomerSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerSearch dialog = new CustomerSearch(new javax.swing.JFrame(), true, "");
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
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSearchCustomerBy;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel pnlTblHdr;
    private javax.swing.JLabel tblAddress;
    private javax.swing.JLabel tblContact1;
    private javax.swing.JLabel tblContact2;
    private javax.swing.JTable tblCustomers;
    private javax.swing.JLabel tblName;
    private javax.swing.JTextField txtSearchAttribute;
    // End of variables declaration//GEN-END:variables
}
