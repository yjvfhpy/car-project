package com.saturn.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 加密解密工具类
 * 
 * @author tajo
 */
public class MosaicUtil
{
    private static final String ALGORITHM = "DES";

    /**
     * <p>
     * 生成随机密钥
     * </p>
     * 
     * @return
     * @throws Exception
     */
    public static String getSecretKey()
        throws Exception
    {
        return getSecretKey( null );
    }

    /**
     * <p>
     * 生成密钥
     * </p>
     * 
     * @param seed 密钥种子
     * @return
     * @throws Exception
     */
    public static String getSecretKey( String seed )
        throws Exception
    {
        SecureRandom secureRandom;
        if ( seed != null && !"".equals( seed ) )
            secureRandom = new SecureRandom( seed.getBytes() );
        else
            secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance( ALGORITHM );
        keyGenerator.init( secureRandom );
        SecretKey secretKey = keyGenerator.generateKey();
        return Base64Util.encode( secretKey.getEncoded() );
    }

    /**
     * <p>
     * 加密
     * </p>
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt( byte[] data, String key )
        throws Exception
    {
        Key k = toKey( Base64Util.decode( key ) );
        Cipher cipher = Cipher.getInstance( ALGORITHM );
        cipher.init( Cipher.ENCRYPT_MODE, k );
        return cipher.doFinal( data );
    }

    /**
     * <p>
     * 解密
     * </p>
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt( byte[] data, final String key )
        throws Exception
    {
        Key k = toKey( Base64Util.decode( key ) );
        Cipher cipher = Cipher.getInstance( ALGORITHM );
        cipher.init( Cipher.DECRYPT_MODE, k );
        return cipher.doFinal( data );
    }

    /**
     * <p>
     * 转换密钥
     * </p>
     * 
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey( byte[] key )
        throws Exception
    {
        DESKeySpec dks = new DESKeySpec( key );
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance( ALGORITHM );
        SecretKey secretKey = keyFactory.generateSecret( dks );
        return secretKey;
    }

}
