package com.lgz.crazy.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by lgz on 2019/3/1.
 */
public class MD5Test {

    public static void main(String[] args) throws Exception{
        //27132b0d91540e6149bff70d8269126d
        String salt = "xoXeIPE0";
        Md5Hash md5Hash = new Md5Hash("111111", "xoXeIPE0",2);
        String s = md5Hash.toString();
        System.out.println(s);

    }
}
