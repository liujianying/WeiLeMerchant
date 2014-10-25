package com.ydh.weile.uitl.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Administrator
 */
public class AESCrypto {
    
	/**
	 * 加密
	 *@category
	 *@Title: encrypt
	 *@Description:
	 *@return String
	 *@throws
	 *@param key
	 *@param content
	 *@return
	 */
    public static String encrypt(String key, String content ) {
        byte[] encryptResult = null;
        String result = null;
        try {
            byte[] contentBytes = content.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encryptResult = cipher.doFinal(contentBytes);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        if (encryptResult != null) {
            result = Base64.encode(encryptResult);
        }
        result=result.replace("\r\n", "");
        return result;
    }
    
    /**
     * 解密
     *@category
     *@Title: decrypt
     *@Description:
     *@return String
     *@throws
     *@param key
     *@param content
     *@return
     */
    public static String decrypt(String key, String content ) {
        String result = null;
        byte[] decryptResult = null;
        try {
            byte[] contentBytes = Base64.decode(content);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            decryptResult = cipher.doFinal(contentBytes);
            if (decryptResult != null) {
                result = new String(decryptResult, "UTF-8");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

}
