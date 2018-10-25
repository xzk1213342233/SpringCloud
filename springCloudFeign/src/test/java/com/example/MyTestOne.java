package com.example;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;

public class MyTestOne {
	
	
	@Test
	public void one() throws Exception {
        Map<String, String> keyMap = RSAUtils.initKey();
        String publicKeyString = keyMap.get(RSAUtils.RSA_PUBLIC_KEY);
        String privateKeyString = keyMap.get(RSAUtils.RSA_PRIVATE_KEY);
        System.out.println("公钥:" + publicKeyString);
        System.out.println("私钥:" + privateKeyString);

        // 待加密数据
        String data = "hdd250";
        // 公钥加密
        String encrypt = RSAUtils.encryptByPubKey(data, publicKeyString);
        // 私钥解密
        String decrypt = RSAUtils.decryptByPriKey(encrypt, privateKeyString);

        System.out.println("加密前:" + data);
        System.out.println("加密后:" + encrypt);
        System.out.println("解密后:" + decrypt);
		
	}

}
