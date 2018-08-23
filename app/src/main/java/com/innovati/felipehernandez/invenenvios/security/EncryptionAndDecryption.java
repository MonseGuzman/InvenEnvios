package com.innovati.felipehernandez.invenenvios.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionAndDecryption {
    public EncryptionAndDecryption() {
    }

    byte[] data;
    String key = "guardarLosvoyAMatarHolaNonosmates3fyb44wdhnd2ghhl";

    public String encry(String password) {
        final byte[] defaultBytes = password.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();
            final StringBuffer hexString = new StringBuffer();
            for (final byte element : messageDigest) {
                final String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            password = hexString + "";
        } catch (final NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return password;
    }
}
