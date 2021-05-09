/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.core;

import cazzendra.pos.model.User;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Amal
 */
public class Loading {

    // Initial Data
    private static int counterId;
    private static String shopName;
    private static String userType;
    private static Color colorCode;
    private static User user;
    private static String dbName;
    private static String dbPassword;
    private static String copyrightStatement;
    private static String slogan;
    private static String ShopAddress;
    private static String ShopContact;

    // Main Menu Functions
    private static String invoice;
    private static String priceUpdate;
    private static String category;
    private static String itemManagement;
    private static String userManagement;
    private static String invoiceHistoryAndReprint;

    // Color Codes 
    private static int colorR;
    private static int colorG;
    private static int colorB;

    private static boolean EnableHirePurchase;
    private static boolean EnableDiscountForTotalInvoiceByValue;

    public static void customJButtonList(int r, int g, int b, List<JButton> btn) {
        for (JButton jButton : btn) {
            jButton.setContentAreaFilled(false);
            jButton.setOpaque(true);
            jButton.setBackground(new java.awt.Color(r, g, b));
        }
    }

    public static void customJButton(int r, int g, int b, JButton btn) {
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setBackground(new java.awt.Color(r, g, b));
    }

    public static void customJLabelFontFamilyList(Font fontFamily, List<JLabel> label) {
        for (JLabel jLabel : label) {
            jLabel.setFont(fontFamily);
        }
    }

    public void CustomLangImage(String FolderName, String ImageName, JLabel LabelName) {
        LabelName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cloudrevel/language/src/" + FolderName + "/" + ImageName + ".png")));
    }

    /**
     * @return the counterId
     */
    public static int getCounterId() {
        return counterId;
    }

    /**
     * @param aCounterId the counterId to set
     */
    public static void setCounterId(int aCounterId) {
        counterId = aCounterId;
    }

    /**
     * @return the shopName
     */
    public static String getShopName() {
        return shopName;
    }

    /**
     * @param aShopName the shopName to set
     */
    public static void setShopName(String aShopName) {
        shopName = aShopName;
    }

    /**
     * @return the userType
     */
    public static String getUserType() {
        return userType;
    }

    /**
     * @param aUserType the userType to set
     */
    public static void setUserType(String aUserType) {
        userType = aUserType;
    }

    /**
     * @return the colorCode
     */
    public static Color getColorCode() {
        return colorCode;
    }

    /**
     * @param aColorCode the colorCode to set
     */
    public static void setColorCode(Color aColorCode) {
        colorCode = aColorCode;
    }

    /**
     * @return the user
     */
    public static User getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(User aUser) {
        user = aUser;
    }

    /**
     * @return the dbName
     */
    public static String getDbName() {
        return dbName;
    }

    /**
     * @param aDbName the dbName to set
     */
    public static void setDbName(String aDbName) {
        dbName = aDbName;
    }

    /**
     * @return the dbPassword
     */
    public static String getDbPassword() {
        return dbPassword;
    }

    /**
     * @param aDbPassword the dbPassword to set
     */
    public static void setDbPassword(String aDbPassword) {
        dbPassword = aDbPassword;
    }

    /**
     * @return the copyrightStatement
     */
    public static String getCopyrightStatement() {
        return copyrightStatement;
    }

    /**
     * @param aCopyrightStatement the copyrightStatement to set
     */
    public static void setCopyrightStatement(String aCopyrightStatement) {
        copyrightStatement = aCopyrightStatement;
    }

    /**
     * @return the invoice
     */
    public static String getInvoice() {
        return invoice;
    }

    /**
     * @param aInvoice the invoice to set
     */
    public static void setInvoice(String aInvoice) {
        invoice = aInvoice;
    }

    /**
     * @return the priceUpdate
     */
    public static String getPriceUpdate() {
        return priceUpdate;
    }

    /**
     * @param aPriceUpdate the priceUpdate to set
     */
    public static void setPriceUpdate(String aPriceUpdate) {
        priceUpdate = aPriceUpdate;
    }

    /**
     * @return the category
     */
    public static String getCategory() {
        return category;
    }

    /**
     * @param aCategory the category to set
     */
    public static void setCategory(String aCategory) {
        category = aCategory;
    }

    /**
     * @return the itemManagement
     */
    public static String getItemManagement() {
        return itemManagement;
    }

    /**
     * @param aItemManagement the itemManagement to set
     */
    public static void setItemManagement(String aItemManagement) {
        itemManagement = aItemManagement;
    }

    /**
     * @return the userManagement
     */
    public static String getUserManagement() {
        return userManagement;
    }

    /**
     * @param aUserManagement the userManagement to set
     */
    public static void setUserManagement(String aUserManagement) {
        userManagement = aUserManagement;
    }

    /**
     * @return the invoiceHistoryAndReprint
     */
    public static String getInvoiceHistoryAndReprint() {
        return invoiceHistoryAndReprint;
    }

    /**
     * @param aInvoiceHistoryAndReprint the invoiceHistoryAndReprint to set
     */
    public static void setInvoiceHistoryAndReprint(String aInvoiceHistoryAndReprint) {
        invoiceHistoryAndReprint = aInvoiceHistoryAndReprint;
    }

    /**
     * @return the colorR
     */
    public static int getColorR() {
        return colorR;
    }

    /**
     * @param aColorR the colorR to set
     */
    public static void setColorR(int aColorR) {
        colorR = aColorR;
    }

    /**
     * @return the colorG
     */
    public static int getColorG() {
        return colorG;
    }

    /**
     * @param aColorG the colorG to set
     */
    public static void setColorG(int aColorG) {
        colorG = aColorG;
    }

    /**
     * @return the colorB
     */
    public static int getColorB() {
        return colorB;
    }

    /**
     * @param aColorB the colorB to set
     */
    public static void setColorB(int aColorB) {
        colorB = aColorB;
    }

    /**
     * @return the slogan
     */
    public static String getSlogan() {
        return slogan;
    }

    /**
     * @param aSlogan the slogan to set
     */
    public static void setSlogan(String aSlogan) {
        slogan = aSlogan;
    }

    /**
     * @return the EnableHirePurchase
     */
    public static boolean isEnableHirePurchase() {
        return EnableHirePurchase;
    }

    /**
     * @param aEnableHirePurchase the EnableHirePurchase to set
     */
    public static void setEnableHirePurchase(boolean aEnableHirePurchase) {
        EnableHirePurchase = aEnableHirePurchase;
    }

    /**
     * @return the EnableDiscountForTotalInvoiceByValue
     */
    public static boolean isEnableDiscountForTotalInvoiceByValue() {
        return EnableDiscountForTotalInvoiceByValue;
    }

    /**
     * @param aEnableDiscountForTotalInvoiceByValue the
     * EnableDiscountForTotalInvoiceByValue to set
     */
    public static void setEnableDiscountForTotalInvoiceByValue(boolean aEnableDiscountForTotalInvoiceByValue) {
        EnableDiscountForTotalInvoiceByValue = aEnableDiscountForTotalInvoiceByValue;
    }

    /**
     * @return the ShopAddress
     */
    public static String getShopAddress() {
        return ShopAddress;
    }

    /**
     * @param aShopAddress the ShopAddress to set
     */
    public static void setShopAddress(String aShopAddress) {
        ShopAddress = aShopAddress;
    }

    /**
     * @return the ShopContact
     */
    public static String getShopContact() {
        return ShopContact;
    }

    /**
     * @param aShopContact the ShopContact to set
     */
    public static void setShopContact(String aShopContact) {
        ShopContact = aShopContact;
    }
}
