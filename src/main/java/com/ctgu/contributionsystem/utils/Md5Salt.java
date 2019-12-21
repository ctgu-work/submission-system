package com.ctgu.contributionsystem.utils;

import sun.applet.Main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @Description MD5加盐加密
 * @Author wh_lan
 * @Create 2019-10-20 10:19
 * @ClassName Md5Salt
 * @Version 1.0.0
 */
public class Md5Salt {
    //静态加盐，盐值固定
    private final static String saltStr = "f2bd0cdd2ed5a30c80bcafc9b964bf45";
    /**
     * @Author wh
     * @Description MD5普通加密
     * @Date 2019/10/20 11:12
     * @Param [str]
     * @return java.lang.String
     **/
    public static String Md5Crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }

    /**
     * @Author wh
     * @Description Md5加盐加密
     * @Date 2019/10/20 19:28
     * @Param [str]
     * @return java.lang.String
     **/
    public static String Md5SaltCrypt(String str) {
        str += saltStr;
        return Md5Crypt(str);
    }
//
//    public static void main(String[] args){
//        String s = "123";
//        System.out.println(Md5SaltCrypt(s));
//    }
}
