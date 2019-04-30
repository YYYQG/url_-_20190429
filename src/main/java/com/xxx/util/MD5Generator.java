package com.xxx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class MD5Generator implements ShortUrlGenerator{
    @Override
    public String convert(String str) {

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(str.getBytes());
            String hex;
            StringBuilder md5Str = new StringBuilder();
            for (byte aByte : bytes) {
                hex = Integer.toHexString(aByte & 0xff);
                if (hex.length() < 2) {
                    md5Str.append('0');
                }
                md5Str.append(hex);
            }
            return md5Str.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
