/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.core;

import java.awt.Font;

/**
 *
 * @author personal
 */
public class CloudRevelFont {
    
    public static final String ENGLISH_FONT_NAME = "Ubuntu";
    public static final String SINHALA_FONT_NAME = "FMMalithi";
    public static final String SINHALA_UNICODE_FONT_NAME = "Noto Sans Sinhala";
    public static final Font SINHALA_UNICODE_FONT_PLAIN_85;
    public static final Font SINHALA_UNICODE_FONT_BOLD_20;
    public static final Font SINHALA_UNICODE_FONT_PLAIN_18;
    public static final Font SINHALA_UNICODE_FONT_BOLD_18;
    public static final Font SINHALA_UNICODE_FONT_PLAIN_16;
    public static final Font SINHALA_UNICODE_FONT_BOLD_12;
    public static final Font SINHALA__FONT_PLAIN_18;
    public static final Font SINHALA__FONT_BOLD_18;
    public static final Font SINHALA__FONT_PLAIN_16;
    public static final Font SINHALA__FONT_PLAIN_14;
    public static final Font ENGLISH__FONT_PLAIN_18;
    public static final Font ENGLISH__FONT_BOLD_18;
    
    static {
        SINHALA_UNICODE_FONT_PLAIN_85 = new Font("Noto Sans Sinhala", 0, 85);
        SINHALA_UNICODE_FONT_BOLD_20 = new Font("Noto Sans Sinhala", 1, 20);
        SINHALA_UNICODE_FONT_PLAIN_18 = new Font("Noto Sans Sinhala", 0, 18);
        SINHALA_UNICODE_FONT_BOLD_18 = new Font("Noto Sans Sinhala", 1, 18);
        SINHALA_UNICODE_FONT_PLAIN_16 = new Font("Noto Sans Sinhala", 0, 16);
        SINHALA_UNICODE_FONT_BOLD_12 = new Font("Noto Sans Sinhala", 1, 12);
        SINHALA__FONT_PLAIN_18 = new Font("FMMalithi", 0, 18);
        SINHALA__FONT_BOLD_18 = new Font("FMMalithi", 0, 18);
        SINHALA__FONT_PLAIN_16 = new Font("FMMalithi", 0, 16);
        SINHALA__FONT_PLAIN_14 = new Font("FMMalithi", 0, 14);
        ENGLISH__FONT_PLAIN_18 = new Font("Ubuntu", 0, 18);
        ENGLISH__FONT_BOLD_18 = new Font("Ubuntu", 1, 18);
    }
}
