package com.example.demo;

import java.security.KeyStore;

import javax.crypto.Cipher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean(name = "cipher")
	public Cipher Cipher() throws Exception{
	    KeyStore keyStore = null;
	    Cipher cipher = null;
            keyStore = KeyStore.getInstance("JKS");
//            keyStore.load(new FileInputStream(".\\src\\main\\resources\\jaina.jks"), "nhsoft123".toCharArray());
//            keyStore.load(new FileInputStream("src/main/resources/jaina.jks"), "nhsoft123".toCharArray());
            Resource resource = new ClassPathResource("jaina.jks");
            keyStore.load(resource.getInputStream(), "nhsoft123".toCharArray());
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyStore.getKey("jaina", "nhsoft123".toCharArray()));
            return cipher;
	    
	}
}
