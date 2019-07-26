package com.pockectstate.api.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author feri
 *@Date Created in 2019/7/25 11:18
 */
public class Qrcode_Util {
    public static BufferedImage createQrCode(String msg,int width){
        BufferedImage bufferedImage=null;
        //创建集合 设置二维码的属性
        Map<EncodeHintType,Object> map=new HashMap<>();
        //设置边距
        map.put(EncodeHintType.MARGIN,1);
        //设置图片质量
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置内容的编码格式
        map.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        try {
            //
            /**参数说明
             * 1、二维码的内容
             * 2、二维码的类型
             * 3、宽度
             * 4、高度
             * 5、二维码的属性*/
            BitMatrix bitMatrix=new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE,width,width,map);
            MatrixToImageConfig matrixToImageConfig=new MatrixToImageConfig(0xFF000000,0xFFFFFFFF);
            bufferedImage= MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bufferedImage;
    }
    public static String createQrCodeFile(String filePath,String msg,int width){
        BufferedImage bufferedImage=createQrCode(msg, width);
         File file=new File(filePath);
        try {
            ImageIO.write(bufferedImage,"PNG",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

}
