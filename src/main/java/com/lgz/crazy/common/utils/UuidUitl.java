package com.lgz.crazy.common.utils;

import java.util.UUID;

/**
 * Created by lgz on 2019/2/22.
 */
public class UuidUitl {

    public static String[] chars62 = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static final String SKU = "SKU";
    //des 加密秘钥
    public static final String DES_KEK="asdfghjkl";
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateUuidNo(){
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = getUuid();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars62[x % 62]);
        }
        return shortBuffer.toString();
    }

    public static String getSalt(){
        return generateUuidNo();
    }

    public static String generateSkuNo(){
        StringBuffer sb = new StringBuffer();
        sb.append(SKU);
        sb.append(generateUuidNo());
        String timestamp = String.valueOf(DateUtil.obtainTimestamp());
        String enTimestamp = EncryptUtils.encrypt(DES_KEK,timestamp);
        sb.append(enTimestamp);
        return sb.toString();
    }

    public static String decryptSkuNo(String str){
        System.out.println(str);
        String substring = str.substring(11, str.length());
        String decrypt = EncryptUtils.decrypt(DES_KEK, substring);
        System.out.println(decrypt);
        System.out.println(DateUtil.Timestamp2DateTime(Long.valueOf(decrypt)));
        return null;
    }

    public static void main(String[] args) {
        /*System.out.println(generateSkuNo());*/
        decryptSkuNo(generateSkuNo());
    }
}
