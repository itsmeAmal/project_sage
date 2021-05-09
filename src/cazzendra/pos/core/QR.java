/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cazzendra.pos.core;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 *
 * @author personal
 */
public class QR {

    private static final String qcip = "D:\\qr.jpg";

    public static void generateQRCode(String text, int width, int height, String filePath)
            throws Exception {
        QRCodeWriter qcwobj = new QRCodeWriter();
        BitMatrix bmobj = qcwobj.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path pobj = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bmobj, "PNG", pobj);
    }

//    public static void main(String[] args) {
//        try {
//            generateQRCode("My Name Is Kamalpreet singh", 128, 128, "D:\\qr\\qr1.jpg");
//        } catch (Exception e) {
//            System.out.println("Could not generate QR Code" + e);
//        }
//    }
}
