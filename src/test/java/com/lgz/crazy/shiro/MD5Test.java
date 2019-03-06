package com.lgz.crazy.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by lgz on 2019/3/1.
 */
public class MD5Test {

    public static void main(String[] args) {
        String source = "15510498910";
        String salt = "eG9YZUlQRTA=";
        int hashIterations=2;
        Md5Hash md5Hash = new Md5Hash(source,salt,hashIterations);
        String s = md5Hash.toString();
        //f3694f162729b7d0254c6e40260bf15c
        //36f2dfa24d0a9fa97276abbe13e596fc
        System.out.println(s);
        //64046c372f0ba7866ab8ce429545f24b
        //64046c372f0ba7866ab8ce429545f24b
    }
}
