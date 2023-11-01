package com.chen.base_utils.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author giraffe
 * @create 2023/4/12 10:09
 */
public class CryptoAesUtil {


    //加解密算法
    private static final String KEY_ALGORITHM_AES = "AES";

    private static final String Key_AES = "AES/CBC/PKCS5Padding";
    //编码
    private static final String ENCODING = "UTF-8";
    //密钥长度规定（16位）
    private static final Integer KEY_LENGTH = 8;
    //填充向量
    private static final String FILL_VECTOR = "PWXtuxims72uBT1j";


    /**
     * 加密字符串
     *
     * @param content 字符串
     * @param aesSec  密钥KEY
     * @return
     * @throws Exception
     */
    public static String encrypt(String content, String aesSec) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        if ( aesSec.length() != 2*KEY_LENGTH && aesSec.length() != 3*KEY_LENGTH && aesSec.length() != 4*KEY_LENGTH) {
            throw new RuntimeException("密钥的长度需为16/24/32字节");
        }
        String password = byte2hex(aesSec.getBytes());
        byte[] raw = hex2byte(password);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM_AES);
        IvParameterSpec iv = new IvParameterSpec(FILL_VECTOR.getBytes());
        Cipher cipher = null;
        cipher = Cipher.getInstance(Key_AES);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] anslBytes = content.getBytes(ENCODING);
        byte[] encrypted = cipher.doFinal(anslBytes);
        return byte2hex(encrypted).toUpperCase();

    }

    /**
     * 解密
     *
     * @param content 解密前的字符串
     * @param key     解密KEY
     * @return
     * @throws Exception
     * @author cdduqiang
     * @date 2014年4月3日
     */
    public static String decrypt(String content, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        String password = byte2hex(key.getBytes());
        byte[] raw = hex2byte(password);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, KEY_ALGORITHM_AES);
        IvParameterSpec iv = new IvParameterSpec(FILL_VECTOR.getBytes());
        Cipher cipher = Cipher.getInstance(Key_AES);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
        byte[] encrypted1 = hex2byte(content);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, ENCODING);

    }

    /**
     * hex2byte 用于将字符串 strhex 转换为字节数组
     *
     * @param strhex strhex
     * @return {@link byte[]}
     */
    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l = strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

}
