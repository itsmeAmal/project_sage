/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.core;

/**
 *
 * @author 4m4l
 */
public class Options {

    private static int activeUser = 1;
    private static int pending = 2;
    private static int inactiveUser = 3;

    public static int PAYMENT_SUCCESS = 1;
    public static int PRODUCT_ISSUED = 2;
    public static int PRODUCT_DELIVERED = 3;

    public static String serverPw;

    private static String user = "User";
    private static String manager = "Manager";
    private static String admin = "Admin";
    
    public static String LANG_SINHALA = "Sinhala";
    public static String LANG_ENGLISH = "ENGLISH";

    /**
     * @return the activeUser
     */
    public static int getActiveUser() {
        return activeUser;
    }

    /**
     * @param aActiveUser the activeUser to set
     */
    public static void setActiveUser(int aActiveUser) {
        activeUser = aActiveUser;
    }

    /**
     * @return the inactiveUser
     */
    public static int getInactiveUser() {
        return inactiveUser;
    }

    /**
     * @param aInactiveUser the inactiveUser to set
     */
    public static void setInactiveUser(int aInactiveUser) {
        inactiveUser = aInactiveUser;
    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the manager
     */
    public static String getManager() {
        return manager;
    }

    /**
     * @param aManager the manager to set
     */
    public static void setManager(String aManager) {
        manager = aManager;
    }

    /**
     * @return the admin
     */
    public static String getAdmin() {
        return admin;
    }

    /**
     * @param aAdmin the admin to set
     */
    public static void setAdmin(String aAdmin) {
        admin = aAdmin;
    }
//    private static int user = 1;
//    private static int admin = 2;
//    private static int manager = 3;
//    private static int superUser = 4;

    /**
     * @return the pending
     */
    public static int getPending() {
        return pending;
    }

    /**
     * @param aPending the pending to set
     */
    public static void setPending(int aPending) {
        pending = aPending;
    }

}
