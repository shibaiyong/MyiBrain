package com.uec.ibrain.ibrain.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: 加密的工具类</p>
 * <p>Author:pqwu/吴佩群</p>
 * <p>Date: 2018/4/16</p>
 */
public class EncryptUtil {

    /**
     * shiro 的 md5加密
     * @param str 需要加密的字符串
     * @param salt 盐值
     * @param iteration 迭代次数
     * @return
     */
    public static String MD5Encyp(String str, String salt, int iteration){
        return new Md5Hash(str, salt, iteration).toString();
    }
    /**
     * shiro 的 sha256加密
     * @param str 需要加密的字符串
     * @param salt 盐值
     * @param iteration 迭代次数
     * @return
     */
    public static String SHA256Encyp(String str, String salt, int iteration){
        return new Sha256Hash(str, salt, iteration).toString();
    }
}
