package com.hfc.utils;

/**
 * Created by user-hfc on 2018/8/19.
 *
 * @author user-hfc.
 */
public class StringUtils
{
    public static boolean isEmpty(String str)
    {
        return (str == null || str.length() == 0);
    }

    public static void main(String[] args)
    {
        String str1 = null;
        String str2 = "";
        String str3 = "s";

        System.out.println(isEmpty(str1));
        System.out.println(isEmpty(str2));
        System.out.println(isEmpty(str3));
    }
}
