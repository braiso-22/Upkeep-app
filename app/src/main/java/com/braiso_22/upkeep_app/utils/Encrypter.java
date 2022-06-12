package com.braiso_22.upkeep_app.utils;

import android.util.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {

    public static String encrypt(String text) throws Exception {
        SecretKeySpec key = generateKey(text);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(text.getBytes());
        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }

    private static SecretKeySpec generateKey(String text) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] key = text.getBytes(StandardCharsets.UTF_8);
        key = sha.digest(key);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        return secretKey;
    }
}