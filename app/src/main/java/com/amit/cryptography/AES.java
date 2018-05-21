package com.amit.cryptography;

import android.util.Base64;
import android.util.Log;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Amit Jangid on 21,May,2018
 **/
public class AES
{
    private static final String TAG = AES.class.getSimpleName();

    public static String encrypt(String key, String algorithm, String value)
    {
        try
        {
            SecretKey secretKey = new SecretKeySpec(Base64.decode(key.getBytes(), Base64.NO_WRAP), "AES");
            AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decode(key.getBytes(), Base64.NO_WRAP));

            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            return new String(Base64.encode(cipher.doFinal(value.getBytes("UTF-8")), Base64.NO_WRAP));
        }
        catch (Exception e)
        {
            Log.e(TAG, "encrypt: exception while encrypting a string.");
            e.printStackTrace();
            return null;
        }
    }
}
