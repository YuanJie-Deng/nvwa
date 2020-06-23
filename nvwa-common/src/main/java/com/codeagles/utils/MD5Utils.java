package com.codeagles.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * MD5工具类
 *
 * @author hcn
 * @create 2020-01-04 10:58
 **/
public class MD5Utils {

    /**
     * @Title: MD5Utils.java
     * @Package com.imooc.utils
     * @Description: 对字符串进行md5加密
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }
}
