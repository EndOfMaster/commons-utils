package com.endofmaster.commons.util.crypto;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @author YQ.Huang
 */
public class CipherUtils {

    public static byte[] decryptHex(byte[] key, String hex, String algorithm, String transform) throws CryptoException {
        try {
            byte[] target = Hex.decodeHex(hex.toCharArray());
            return decrypt(key, target, algorithm, transform);
        } catch (DecoderException e) {
            throw new CryptoException(e);
        }
    }

    public static String encryptHex(byte[] key, byte[] target, String algorithm, String transform) throws CryptoException {
        return Hex.encodeHexString(encrypt(key, target, algorithm, transform));
    }

    public static byte[] encrypt(byte[] key, byte[] target, String algorithm, String transform) throws CryptoException {
        SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
        return encrypt(keySpec, target, transform);
    }

    public static byte[] encrypt(Key key, byte[] target, String transform) throws CryptoException {
        try {
            Cipher cipher = Cipher.getInstance(transform);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(target);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }

    public static byte[] decrypt(byte[] key, byte[] target, String algorithm, String transform) throws CryptoException {
        SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
        return decrypt(keySpec, target, transform);
    }

    public static byte[] decrypt(Key key, byte[] target, String transform) throws CryptoException {
        try {
            Cipher cipher = Cipher.getInstance(transform);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(target);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }
}
