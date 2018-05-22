package com.amit.cryptography;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Amit Jangid on 22,May,2018
 **/
public class AESCrypt
{
    private static final String TAG = AESCrypt.class.getSimpleName();

    // AESCrypt-ObjC  uses CBC and PKCS7Padding
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";

    // AESCrypt-ObjC uses SHA-256 (and so a 256-bit key)
    private static final String HASH_ALGORITHM = "SHA-256";

    // AESCrypt-ObjC uses blank IV (not the bes security, but the aim here is compatibility)
    private static final byte[] ivBytes = {0x0F, 0x01, 0x04, 0x07, 0x05, 0x09, 0x03, 0x0D, 0x06, 0x0A, 0x02, 0x08, 0x0B, 0x00, 0x0C, 0x0E};

    // toggleable log option (please turn off in live)
    public static boolean DEBUG_LOG_ENABLED = false;

    /**
     * generate Key
     * Generates SHA256 hash of the password which is used as key
     *
     * @param password used to generated key
     * @return SHA256 of the password
     *
     * @throws NoSuchAlgorithmException - no such algorithm exception
     * @throws UnsupportedEncodingException - unsupported exception
    **/
    private static SecretKeySpec generateKey(final String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes(CHARSET);
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        log("SHA-256 key ", key);

        SecretKeySpec secretKeySpec = new SecretKeySpec(key, HASH_ALGORITHM);
        return secretKeySpec;
    }

    /**
     * Encrypt and encode message using 256-bit AES with key generated from password.
     *
     * @param password used to generated key
     * @param message the thing you want to encrypt assumed String UTF-8
     * @return Base64 encoded CipherText
     * @throws GeneralSecurityException if problems occur during encryption
    **/
    public static String encrypt(final String password, String message)
            throws GeneralSecurityException
    {
        try
        {
            final SecretKeySpec key = generateKey(password);
            log("message ", message);

            byte[] cipherText = encrypt(key, ivBytes, message.getBytes(CHARSET));

            // NO_WRAP is important as was getting \n at the end
            String encoded = Base64.encodeToString(cipherText, Base64.NO_WRAP);
            log("Base64.NO_WRAP ", encoded);
            return encoded;
        }
        catch (UnsupportedEncodingException e)
        {
            if (DEBUG_LOG_ENABLED)
            {
                Log.e(TAG, "encrypt: UnsupportedEncodingException ", e);
            }

            throw new GeneralSecurityException(e);
        }
    }

    /**
     * More flexible AES encrypt that doesn't encode
     * @param key AES key typically 128, 192 or 256 bit
     * @param iv Initiation Vector
     * @param message in bytes (assumed it's already been decoded)
     * @return Encrypted cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
    **/
    public static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
            throws GeneralSecurityException
    {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        byte[] cipherText = cipher.doFinal(message);
        log("cipherText is: ", cipherText);
        return cipherText;
    }

    /**
     * Decrypt and decode ciphertext using 256-bit AES with key generated from password
     *
     * @param password used to generated key
     * @param base64EncodedCipherText the encrpyted message encoded with base64
     * @return message in Plain text (String UTF-8)
     * @throws GeneralSecurityException if there's an issue decrypting
    **/
    public static String decrypt(final String password, String base64EncodedCipherText)
            throws GeneralSecurityException
    {
        try
        {
            final SecretKeySpec key = generateKey(password);
            log("base64EncodedCipherText ", base64EncodedCipherText);
            byte[] decodedCipherText = Base64.decode(base64EncodedCipherText, Base64.NO_WRAP);
            log("decodedCipherText ", decodedCipherText);
            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);
            String message = new String(decryptedBytes, CHARSET);
            log("mesage ", message);
            return message;
        }
        catch (Exception e)
        {
            if (DEBUG_LOG_ENABLED)
            {
                Log.e(TAG, "decrypt: UnsupportedEncodingException: ", e);
            }

            throw new GeneralSecurityException(e);
        }
    }

    /**
     * More flexible AES decrypt that doesn't encode
     *
     * @param key AES key typically 128, 192 or 256 bit
     * @param iv Initiation Vector
     * @param decodedCipherText in bytes (assumed it's already been decoded)
     * @return Decrypted message cipher text (not encoded)
     * @throws GeneralSecurityException if something goes wrong during encryption
    **/
    public static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
            throws GeneralSecurityException
    {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
        log("decryptedBytes " , decryptedBytes);
        return decryptedBytes;
    }

    private static void log(String what, byte[] bytes)
    {
        if (DEBUG_LOG_ENABLED)
        {
            Log.e(TAG, what + "[" + bytes.length + "] [" + bytesToHex(bytes) + "]");
        }
    }

    private static void log(String what, String value)
    {
        if (DEBUG_LOG_ENABLED)
        {
            Log.e(TAG, what + "[" + value.length() + "] [" + value + "]");
        }
    }

    /**
     * Converts byte array to hexidecimal useful for logging and fault finding
     * @param bytes - byte array
     * @return - string
    **/
    private static String bytesToHex(byte[] bytes)
    {
        int v;
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];

        for (int i = 0; i < bytes.length; i++)
        {
            v = bytes[i] & 0xFF;
            hexChars[i * 2] = hexArray[v >>> 4];
            hexChars[i * 2  + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }
}
