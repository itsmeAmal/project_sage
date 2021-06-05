/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos;

import cazzendra.pos.control.CommonController;
import cazzendra.pos.core.Loading;
import cazzendra.pos.ui.ActivationUI;
import cazzendra.pos.ui.Authentication;
import cazzendra.pos.ui.Login;
import cazzendra.pos.ui.SubMenuV3;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jasypt.util.text.AES256TextEncryptor;

/**
 *
 * @author Amal
 */
public class CazzendraPOS {

    public CazzendraPOS() {
//        Cn();
        config();
    }

    private static void config() {
        try {
            // default app configurations
            Loading.setCounterId(1);
            Loading.setColorCode(Color.decode("#41d6c3")); // UI colors
//            Loading.setColorCode(Color.decode()); // UI colors
            // Btn Colors
            Loading.setColorR(0);
            Loading.setColorG(0);
            Loading.setColorB(102);
            Loading.setDbPassword("1234");
            Loading.setCopyrightStatement("© Cloud Revel Technologies 2021 | web : cloudrevel.info | Email : support@cloudrevel.info"); 


            //Deployment Options
            Loading.setEnableHirePurchase(false);
            Loading.setEnableDiscountForTotalInvoiceByValue(false);

            boolean status = CommonController.Activation();
            if (status) {
                new Login().setVisible(true);
            } else {
                new ActivationUI().setVisible(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CazzendraPOS.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Cn() {
        try {
            File myObj = new File("ext.lib\\66fgtenc.txt");

            if (myObj.exists()) {
                String cn = InetAddress.getLocalHost().getHostName();

                //encrypt key
                AES256TextEncryptor aesEncryptor = new AES256TextEncryptor();
                aesEncryptor.setPassword("mypassword");
                String myEncryptedPassword = aesEncryptor.encrypt(cn);

                Authentication authUi = new Authentication(null, true, myEncryptedPassword);
                authUi.setVisible(true);

                String returnKey = authUi.returnKey();

                //write key to config file
                FileWriter myWriter = new FileWriter("ext.lib\\66fgtenc.txt");
                myWriter.write(returnKey);
                myWriter.close();

                //read config file
                String txtInsideConfigFile = "";
                File myObj2 = new File("ext.lib\\66fgtenc.txt");

                //scan key and decrypt key
                Scanner myReader = new Scanner(myObj2);
                while (myReader.hasNextLine()) {
                    txtInsideConfigFile = myReader.nextLine();
                }
                myReader.close();
                String decryptedPassword = aesEncryptor.decrypt(txtInsideConfigFile);

                if (decryptedPassword.equalsIgnoreCase(cn)) {
                    System.out.println(decryptedPassword);
                    System.out.println(cn);

                    config();
                } else {
                    System.out.println("errorrrr...!");
                }
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(CazzendraPOS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CazzendraPOS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CazzendraPOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
//        Cn();
        config();

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SubMenuV3.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
