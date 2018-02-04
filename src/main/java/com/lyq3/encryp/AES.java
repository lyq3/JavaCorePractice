package com.lyq3.encryp;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AES {
    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < buf.length; i++){
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if(hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /***************************************登录使用的AES算法****************************************/
    /**
     * 获取随机密钥
     *
     * @return 密钥
     * @throws NoSuchAlgorithmException
     */
    public static SecretKey getKey(String seed) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //获取一个密钥生成器实例
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");//必须指定随机数生成算法，否则在Linux环境下报错
        random.setSeed(seed.getBytes("UTF-8"));//设置加密用的种子
        keyGenerator.init(128,random);
        SecretKey secretKey = keyGenerator.generateKey(); //生成秘钥
        return secretKey;
    }

    /**
     * AES加密
     *
     * @param data 要加密的数据
     * @param seed  加密所使用的种子
     * @return 加密后的数据
     * @throws Exception
     */
    public static String doEncrypt(String data, String seed) throws Exception {
        //根据种子获取密钥，种子相同生成的密钥也相同
        SecretKey secretKey = getKey(seed);
        //用指定的密钥初始化Cipher对象，同时指定加解密模式,这里是加密模式
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //加密
        byte[] encrypData = cipher.doFinal(data.getBytes("UTF-8"));
        //将二进制转为16进制，因为AES加密后的数组和字符串是不可逆的
        byte[] hexData = parseByte2HexStr(encrypData).getBytes("UTF-8");
        //加密后是字节数组，再进行Base64加密便于传输
        //据RFC 822规定，每76个字符，还需要加上一个回车换行,这里为方便传输使用不带换行符的加密方式
        String res = Base64.encodeBase64String(hexData);
        return res;
    }

    /**
     * AES解密
     *
     * @param data 要解密的数据
     * @param seed  解密所使用的种子
     * @return 解密后的数据, 即源数据
     * @throws Exception
     */
    public static String doDecrypt(String data, String seed) throws Exception {
        //获取密钥
        SecretKey secretKey = getKey(seed);
        //用指定的密钥初始化Cipher对象，同时指定加解密模式,这里是解密模式
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //将密文先进行Base64解密
        byte[] decryptData = Base64.decodeBase64(data);
        //base64解密后转为字符串
        String st = new String (decryptData,"UTF-8");
        //16进制转2进制
        byte[] twoByte = parseHexStr2Byte(st);
        //然后进行AES解密
        byte[] strArray = cipher.doFinal(twoByte);
        //转为字符串,采用UTF-8编码
        String res = new String(strArray,"UTF-8");
        return res;
    }
}
