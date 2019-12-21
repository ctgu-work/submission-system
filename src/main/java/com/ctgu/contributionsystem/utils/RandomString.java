package com.ctgu.contributionsystem.utils;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-20 18:49
 * @ClassName RandomString
 * @Version 1.0.0
 */
import java.util.Random;
public class RandomString {
    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    public static String randStr(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for( int i = 0 ; i < length ; i++ ){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
