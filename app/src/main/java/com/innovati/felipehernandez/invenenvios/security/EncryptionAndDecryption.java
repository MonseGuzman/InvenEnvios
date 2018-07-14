package com.innovati.felipehernandez.invenenvios.security;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class EncryptionAndDecryption {
    private static final String CHARSET = "UTF-8";
    private static final int DEFAULT_KEY_SIZE = 128;
    String password = "guardarLosvoyAMatarHolaNonosmates3fyb44wdhnd2ghhl";

    public EncryptionAndDecryption() {
    }

    public enum ALGORITHM {
        AES("AES"),
        DES("DES"),
        MD2("MD2"),
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256"),
        SHA384("SHA-384"),
        SHA512("SHA-512");

        private final String text;

        ALGORITHM(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static String encrypt(final String content, final String password) {
        return encrypt(content, password, ALGORITHM.AES);
    }

    public static String encrypt(final String content, final String password, final ALGORITHM algorithm) {
        return encrypt(content, password, null, algorithm);
    }

    public static String encrypt(final String content, final String password, final byte[] iv, final ALGORITHM algorithm) {
        try {
            final SecretKeySpec keySpec = generateKey(password, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm.toString());
            if (iv == null) {
                cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            } else {
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            }
            byte[] result = cipher.doFinal(content.getBytes(CHARSET));
            return byte2Hex(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(final String encryptedContent, final String password) {
        return decrypt(encryptedContent, password, ALGORITHM.AES);
    }


    public static String decrypt(final String encryptedContent, final String password, final ALGORITHM algorithm) {
        return decrypt(encryptedContent, password, null, algorithm);
    }

    public static String decrypt(final String encryptedContent, final String password, final byte[] iv, final ALGORITHM algorithm) {
        try {
            final SecretKeySpec keySpec = generateKey(password, algorithm);
            Cipher cipher = Cipher.getInstance(algorithm.toString());
            if (iv == null) {
                cipher.init(Cipher.DECRYPT_MODE, keySpec);
            } else {
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            }
            byte[] result = cipher.doFinal(hex2byte(encryptedContent));
            return new String(result, CHARSET);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SecretKeySpec generateKey(final String password, final ALGORITHM algorithm) {
        try {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.toString());
            SecureRandom secureRandom;
            if (isAndroidPlatform()) {
                secureRandom = SecureRandom.getInstance("SHA1PRNG", "Crypto");
            } else {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
            }
            secureRandom.setSeed(password.getBytes());
            int keySize = DEFAULT_KEY_SIZE;
            switch (algorithm) {
                case DES:
                    keySize = 56;
                    break;
            }
            keyGenerator.init(keySize, secureRandom);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] encodedFormat = secretKey.getEncoded();
            return new SecretKeySpec(encodedFormat, algorithm.toString());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sha1(String content) {
        return digest(content, ALGORITHM.SHA1);
    }

    public static String sha1(File file) {
        return digest(file, ALGORITHM.SHA1);
    }

    public static String md5(String content) {
        return digest(content, ALGORITHM.MD5);
    }

    public static String md5(File file) {
        return digest(file, ALGORITHM.MD5);
    }

    public static String digest(String content, ALGORITHM algorithm) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm.toString());
            messageDigest.update(content.getBytes(CHARSET));
            byte[] result = messageDigest.digest();
            return byte2Hex(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String digest(File file, ALGORITHM algorithm) {
        DigestInputStream inputStream = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm.toString());
            inputStream = new DigestInputStream(new FileInputStream(file), messageDigest);
            byte[] buffer = new byte[128 * 1024];
            while (inputStream.read(buffer) > 0);   // message digest will update during reading file
            messageDigest = inputStream.getMessageDigest();
            byte[] result = messageDigest.digest();
            return byte2Hex(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // more about message digest: http://commons.apache.org/proper/commons-codec/archives/1.9/apidocs/src-html/org/apache/commons/codec/digest/DigestUtils.html

    private static String byte2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b: bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;   // one byte to double-digit hex
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private static byte[] hex2byte(String hex) {
        if (hex == null || hex.length() < 1) {
            return null;
        }
        int len = hex.length() / 2;
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            int high = Integer.parseInt(hex.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hex.substring(i * 2 + 1, i * 2 + 2), 16);
            bytes[i] = (byte) (high * 16 + low);
        }
        return bytes;
    }
    private static boolean isAndroidPlatform() {
        Properties properties = System.getProperties();
        return properties.getProperty("java.vendor").contains("Android") ||
                properties.getProperty("java.vm.vendor").contains("Android");
    }

    public String encryp (String s){
        return encrypt(s, password);
    }
    /*public String desencryp (String s){
        return decrypt(s, password);
    }*/
}
