package com.zwb.fileserver.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Title: MD5Util
 * </p>
 * <p>
 * Description: MD5工具类----提供一系列获取和校验MD5的方法
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/8/30 14:33
 */
public class MD5Util {

    /***
     * @Title: getMD5
     * @Description: 获取该输入流的MD5值
     * @param is 输入流
     * @return: java.lang.String   MD5字符串
     * @author zhouwenbo
     * @date 2018/8/30 14:52
     * @version 1.0
     */
    public static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");//获取java加密对象(MD5加密)
        byte[] dataBytes = new byte[1024];
        int nread = 0;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes,0,nread);
        };
        byte[] mdbytes = md.digest();

        //将字节转换为十六进制格式
        for(int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }

    /***
     * @Title: getMD5
     * @Description: 获取文件的MD5值
     * @param file
     * @return: java.lang.String
     * @author zhouwenbo
     * @date 2018/8/30 15:04
     * @version 1.0
     */
    public static String getMD5(File file) throws NoSuchAlgorithmException, IOException {
        FileInputStream fis = new FileInputStream(file);
        return getMD5(fis);
    }

    /***
     * @Title: getMD5
     * @Description: 获取指定路径文件的MD5值
     * @param path
     * @return: java.lang.String
     * @author zhouwenbo
     * @date 2018/8/30 15:07
     * @version 1.0
     */
    public static String getMD5(String path) throws NoSuchAlgorithmException, IOException {
        FileInputStream fis = new FileInputStream(path);
        return getMD5(fis);
    }

    /***
     * @Title: md5CheckSum
     * @Description: 校验该输入流的MD5值
     * @param is
     * @param toBeCheckSum
     * @return: boolean
     * @author zhouwenbo
     * @date 2018/8/30 15:09
     * @version 1.0
     */
    public static boolean md5CheckSum(InputStream is,String toBeCheckSum) throws NoSuchAlgorithmException, IOException {
        return getMD5(is).equals(toBeCheckSum);
    }

    /***
     * @Title: md5CheckSum
     * @Description: 校验文件的MD5值
     * @param file 文件
     * @param toBeCheckSum 待校验的MD5值
     * @return: boolean
     * @author zhouwenbo
     * @date 2018/8/30 15:13
     * @version 1.0
     */
    public static boolean md5CheckSum(File file,String toBeCheckSum) throws NoSuchAlgorithmException, IOException {
        return getMD5(file).equals(toBeCheckSum);
    }

    /***
     * @Title: md5CheckSum
     * @Description: 校验指定文件的MD5值
     * @param path
     * @param toBeCheckSum
     * @return: boolean
     * @author zhouwenbo
     * @date 2018/8/30 15:14
     * @version 1.0
     */
    public static boolean md5CheckSum(String path,String toBeCheckSum) throws NoSuchAlgorithmException, IOException {
        return getMD5(path).equals(toBeCheckSum);
    }

}
