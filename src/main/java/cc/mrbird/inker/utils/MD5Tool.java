package cc.mrbird.inker.utils;

import java.security.MessageDigest;

/**
 * Created by yinpeng on 2017/10/30.
 */
public class MD5Tool {
    public static String Md5Encoder(String str,String codeStyle){
        //md5 加密工具
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        String newStr = base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
//        return newStr;
        String md5 = "";
        try{
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            byte[] messageByte = str.getBytes(codeStyle);
            byte[] digest = mDigest.digest(messageByte);
            md5 = byteToHex(digest);
        }catch(Exception e){
            e.printStackTrace();
        }
        return md5;
    }

    //二进制转十六进制
    private static String byteToHex(byte[] bytes){
        StringBuffer hexStr = new StringBuffer();
        int num;
        for(int i=0;i<bytes.length;i++){
            num=bytes[i];
            if(num<0){
                num+=256;
            }
            if(num<16){
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }
}
