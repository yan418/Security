package com.dream.blog.common.web.config;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Encrypt {
	

	//使用账号作为盐值加密
	public static Md5Hash doEncrypt(String username,String password){		
		// 3 次加密
		Md5Hash hash = new Md5Hash(password, username, 3);	
		return hash;
	}
	
	
	//加密
	public static void main(String[] args) {
        Md5Hash hash = new Md5Hash("666", "zhangsan", 3);
        System.out.println(hash);
        
        //yan 123 加密后      070a5181674a4585f4540887ed72adb2
        //admin 123 加密后 9d7281eeaebded0b091340cfa658a7e8
    }
}
