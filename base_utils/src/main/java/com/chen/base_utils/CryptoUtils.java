package com.chen.base_utils;

import com.coocaa.crypto.AfterEncryption;
import com.coocaa.crypto.BeforeEncryption;
import com.coocaa.crypto.CryptoMixedUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class CryptoUtils {

    public static final String PBK = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcMgHl7ao43p6bWzd6X9Udu789IOwthQMDKfAslno0xkqonczpYTxgKbvGXo+u9qG4o8bkZyl3AAtyiIqYvnCcpI6YTgYZ30Eyl19XdkndgVKS353NcypI9+ROFv399gXkO6zZRk7UmMoaRdYKaMPvNEl/TJ/6AzwyxnbPpFY9BwIDAQAB";//用于非对称加密的密钥
    public static final String PVK = "MIICXAIBAAKBgQCcMgHl7ao43p6bWzd6X9Udu789IOwthQMDKfAslno0xkqonczpYTxgKbvGXo+u9qG4o8bkZyl3AAtyiIqYvnCcpI6YTgYZ30Eyl19XdkndgVKS353NcypI9+ROFv399gXkO6zZRk7UmMoaRdYKaMPvNEl/TJ/6AzwyxnbPpFY9BwIDAQABAoGAJARD+8j533M2D4zRFh7S/bA1QNs+mqB5OVmKUD3CoDi5Lnh0r7wEOAhnjC5x415rnC/nxPMk1JPmD9r8WoxPWvMryTMyqtT4x2Z7ZyinPE0OpcKNVerSDbn0QAz6cqLWUrvpRYpzFzl67Lo7YwCsO6ZYT2K8o9mCqC0P7wZ5FMECQQDQINVYTHOsGXlDHaNub4GkOYaZZigItZ0VPIX0XwNOdNqCW3X6R8Mkq3D/h6JByh4nRFzWDdIDr+Vd5Fj0jsXjAkEAwB84eWLy6TwdFd6KoL+TwuRq+8N/D4V3gZasD72/v6xRpd7ZAIgOZQpdBjPkOJtI8ZPgjFJZfrFFat/G8Tv1jQJAfZjixmdUgq3YtLzlDFb0GCOy601vqVnX0qH0gHT57NWtq28AMiouRCnFhh7Wtvb+3Vlu4Kn0a88xDkknuWWTWwJBALqBVGyafx+mSMIDMOaONB5dafIQPZfOxE3b/Bqn5K5o1TZF2LloWzkBT38G/wVo0e4a1UDDbLfN1YrDxDhJMb0CQGVkvLu4fatJSlHEjIwQfDNx4jHniesOl9ivkOTKoJuJYN9FVSMQVCQTL5RKPlLDapfMzt7++Iv5UFlQ+Eoz7ug=";//用于非对称加密的密钥


    private static final String TAG = "CryptoUtils";
    public static void main(String[] args){
        String randomAes = "AihAjunKWQ5oKv9iZXrzJ0L4FmjjFsWcRBLF2oS9okNdG5Vkdonzcasnf0XcIihOON9WkyGv4zzBORaCNnhLOEJjhT+VIBNoNIwAB3ziE0FRZwi2vcs5bw+jle0kRUEYqYnIzL5DufwWXgK3zeBOAYbSaMZca6J5+02B5KNVZpk=";
        String encryptedMac = "932575D0CF3A871BF7FE20A05AB3FF74";
        String encryptedUDID ="920ECD68F3326101E3FA21896E22A4EC";

        try {
            BeforeEncryption beforeEncryption= CryptoMixedUtil.decrypt(encryptedMac,randomAes,PVK);
            KLog.d(TAG,"beforeDecrypt:"+beforeEncryption.getText());
            KLog.d(TAG,"beforeDecrypt:"+beforeEncryption.getAesKey());




        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BeforeEncryption beforeEncryption2= CryptoMixedUtil.decrypt(encryptedUDID,randomAes,PVK);
            KLog.d(TAG,"beforeDecrypt:"+beforeEncryption2.getText());
            KLog.d(TAG,"beforeDecrypt:"+beforeEncryption2.getAesKey());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
