package com.chen.base_utils.crypto;

import com.coocaa.crypto.AfterEncryption;
import com.coocaa.crypto.BeforeEncryption;
import com.coocaa.crypto.aes.CryptoAesUtil;
import com.coocaa.crypto.rsa.CryptoRsaUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;

/**
 * 混合加密（对称加密+非对称加密）
 *
 * @author wanglei
 * @date 2023/04/11
 */
public class CryptoMixedUtil {
    /**
     * 字符集
     */
    private static final String CHARACTER_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    /**
     * 生成密钥长度
     */
    private static final int LENGTH = 16;
    //密钥长度规定（16位）
    private static final Integer KEY_LENGTH = 8;

    /**
     * 加密
     *
     * @param publicKey RSA公钥
     * @param text      明文
     * @param aesKey    ASE密钥
     * @return {@link Object}
     * @throws InvalidAlgorithmParameterException 无效算法参数异常
     * @throws NoSuchPaddingException             没有这样填充例外
     * @throws UnsupportedEncodingException       不支持编码异常
     * @throws IllegalBlockSizeException          非法块大小异常
     * @throws NoSuchAlgorithmException           没有这样算法异常
     * @throws BadPaddingException                坏填充例外
     * @throws InvalidKeyException                无效关键例外
     */
    public static AfterEncryption encrypt(String text, String aesKey, String publicKey) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        checkAseKey(aesKey);
        String encContent = CryptoAesUtil.encrypt(text, aesKey);
        String encKey = CryptoRsaUtil.encrypt(aesKey, publicKey);
        AfterEncryption afterEncryption = new AfterEncryption();
        afterEncryption.setCipherText(encContent);
        afterEncryption.setCipherAesKey(encKey);
        return afterEncryption;
    }

    /**
     * 加密
     *
     * @param publicKey RSA公钥
     * @param text      明文
     * @return {@link Object}
     * @throws InvalidAlgorithmParameterException 无效算法参数异常
     * @throws NoSuchPaddingException             没有这样填充例外
     * @throws UnsupportedEncodingException       不支持编码异常
     * @throws IllegalBlockSizeException          非法块大小异常
     * @throws NoSuchAlgorithmException           没有这样算法异常
     * @throws BadPaddingException                坏填充例外
     * @throws InvalidKeyException                无效关键例外
     */
    public static AfterEncryption encrypt(String text, String publicKey) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String aesKey = generateAesKey();
        String encContent = CryptoAesUtil.encrypt(text, aesKey);
        String encKey = CryptoRsaUtil.encrypt(aesKey, publicKey);
        AfterEncryption afterEncryption = new AfterEncryption();
        afterEncryption.setCipherText(encContent);
        afterEncryption.setCipherAesKey(encKey);
        return afterEncryption;

    }
    public static String generateAesKey(){
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTER_SET.length());
            char randomChar = CHARACTER_SET.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    /**
     * 解密
     *
     * @param privateKey   RSA私钥
     * @param cipherText   密文
     * @param cipherAseKey 加密后的ase密钥
     * @return {@link Map}<{@link String}, {@link String}>
     * @throws InvalidAlgorithmParameterException 无效算法参数异常
     * @throws NoSuchPaddingException             没有这样填充例外
     * @throws IllegalBlockSizeException          非法块大小异常
     * @throws UnsupportedEncodingException       不支持编码异常
     * @throws NoSuchAlgorithmException           没有这样算法异常
     * @throws BadPaddingException                坏填充例外
     * @throws InvalidKeyException                无效关键例外
     */
    public static BeforeEncryption decrypt(String cipherText, String cipherAseKey, String privateKey) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        String decKey = CryptoRsaUtil.decrypt(cipherAseKey, privateKey);
        String decContent = CryptoAesUtil.decrypt(cipherText, decKey);
        BeforeEncryption beforeEncryption = new BeforeEncryption();
        beforeEncryption.setText(decContent);
        beforeEncryption.setAesKey(decKey);
        return beforeEncryption;
    }

    public static String decryptText(String cipherText, String cipherAesKey, String privateKey) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        String decKey = CryptoRsaUtil.decrypt(cipherAesKey, privateKey);
        String decContent = CryptoAesUtil.decrypt(cipherText, decKey);
        return decContent;
    }
    public static void checkAseKey(String aesKey){
        if (aesKey.length() != 2 * KEY_LENGTH && aesKey.length() != 3 * KEY_LENGTH && aesKey.length() != 4 * KEY_LENGTH) {
            throw new RuntimeException("密钥的长度需为16/24/32字节");
        }
    }


}