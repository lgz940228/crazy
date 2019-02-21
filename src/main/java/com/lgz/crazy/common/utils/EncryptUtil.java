package com.lgz.crazy.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by lgz on 2019/2/21.
 */
public class EncryptUtil {

    private static final String MD5="MD5";
    private static final String CHARSET_UTF8="UTF-8";

    public static String getSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        return Base64.encodeBase64String(bytes);
    }

    public static String encryptMD5(String str) throws NoSuchAlgorithmException,UnsupportedEncodingException{
        return DigestUtils.md5Hex(str);
    }
    /*public static String encryptMD5(String str) throws NoSuchAlgorithmException,UnsupportedEncodingException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest("aaa".getBytes("utf-8"));
        return byteToHex(digest);
    }*/

    /**
     * byte数组转hex
     * @param bytes
     * @return
     */
    public static String byteToHex(byte[] bytes){
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }

    /**
     * hex转byte数组
     * @param hex
     * @return
     */
    public static byte[] hexToByte(String hex){
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte)intVal);
        }
        return ret;
    }



  /*public static void main(String[] args) throws Exception{
        String salt = getSalt();
        System.out.println(salt);
        System.out.println(encryptMD5("123456"+salt));
    }*/
}
