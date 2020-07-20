package com.paralucent.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncrypAES {

    // KeyGenerator 提供對稱密鑰生成器的功能，支持各種演算法
    private KeyGenerator keygen;
    // SecretKey 負責保存對稱密鑰
    private SecretKey deskey;
    // Cipher 負責完成加密或解密工作
    private Cipher c;
    //該字節數組負責保存加密的結果
    private byte[] cipherByte;

    public EncrypAES()
        throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        // 實例化支持 AES 演算法的密鑰生成器(演算法名稱命名需按規定，否則拋出異常)
        keygen = KeyGenerator.getInstance("AES");
        //生成密鑰
        deskey = keygen.generateKey();
        //生成 Cipher 物件,指定其支持的 AES 演算法
        c = Cipher.getInstance("AES");
    }

    /**
     * 對字符串加密
     *
     * @param str
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] Encrytor(String str)
        throws InvalidKeyException, IllegalBlockSizeException,
               BadPaddingException
    {
        // 根據密鑰，對 Cipher 物件進行初始化，ENCRYPT_MODE 表示加密模式
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = str.getBytes();
        // 加密，結果保存進 cipherByte
        cipherByte = c.doFinal(src);
        return cipherByte;
    }

    /**
     * 對字符串解密
     *
     * @param buff
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] Decryptor(byte[] buff)
        throws InvalidKeyException, IllegalBlockSizeException,
               BadPaddingException
    {
        // 根據密鑰，對 Cipher 物件進行初始化，DECRYPT_MODE 表示加密模式
        c.init(Cipher.DECRYPT_MODE, deskey);
        cipherByte = c.doFinal(buff);
        return cipherByte;
    }

    public void genEncypAES() throws Exception {
        EncrypAES de1 = new EncrypAES();
        String msg ="郭XX-搞笑相聲全集";
        byte[] encontent = de1.Encrytor(msg);
        byte[] decontent = de1.Decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密後:" + new String(encontent));
        System.out.println("解密後:" + new String(decontent));
    }
}