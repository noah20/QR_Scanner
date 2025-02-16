package com.dev.qrscanner.utils;

import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {

    public static String secretKey = "256bitsecretkey12345678901234567";
    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;
    private static final String iv = "abcdefghijklmnop";


    // Generate an AES key (you can store this securely in your app)
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);  // Key size
        return keyGenerator.generateKey();
    }

    // Encrypt data
    public static String encrypt(String data, String key) {
        if(data == null || key == null)
            return null;
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivSpec);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Base64.getEncoder().encodeToString(encryptedData);
            }else {
                return android.util.Base64.encodeToString(encryptedData, android.util.Base64.DEFAULT);
            }
        }catch (Exception e){
            e.printStackTrace();
            return data;
        }
    }




    public static String decrypt(String encryptedText, String key) {
        try {
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] encryptedBytes =android.util.Base64.decode(encryptedText, android.util.Base64.DEFAULT);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);

        }catch (Exception e){
            return encryptedText;
        }
    }

    public static String generatePaymentRequestSignature(String data) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Base64.getEncoder().encodeToString(rawHmac);
            }else {
                return android.util.Base64.encodeToString(rawHmac, android.util.Base64.DEFAULT);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC-SHA256 signature", e);
        }

    }

}
