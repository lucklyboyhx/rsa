package com.example.demo;

import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v2")
public class TestControllor {
    
    @Autowired
    private Cipher cipher;

    @RequestMapping(method = RequestMethod.POST, value = "/login", consumes="application/json")
    public @ResponseBody
    String login(@RequestBody String aa){
        String bb = "aab";
        System.out.println(bb);
        System.out.println(aa);
        return aa;
    }
    
    @SuppressWarnings("restriction")
    @RequestMapping(method = RequestMethod.POST, value = "/test", consumes = "application/json")
    public @ResponseBody
    void readWholesaleBookOrder(@RequestBody String aa) {
        
        byte[] bs = null;
        try {
            Decoder decoder = Base64.getDecoder();
            // encodePwd是前端密码使用公钥通过jscencrypt进行加密后得到的（这里也是复制github上的举例）
            String encodePwd = aa;
            bs = decoder.decode(encodePwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            System.out.println(new String(cipher.doFinal(bs)));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
//        KeyStore keyStore = null;
//        try {
//            keyStore = KeyStore.getInstance("JKS");
////            keyStore.load(new FileInputStream(".\\src\\main\\resources\\jaina.jks"), "nhsoft123".toCharArray());
////            keyStore.load(new FileInputStream("src/main/resources/jaina.jks"), "nhsoft123".toCharArray());
//            Resource resource = new ClassPathResource("jaina.jks");
//            keyStore.load(resource.getInputStream(), "nhsoft123".toCharArray());
//            Cipher cipher = Cipher.getInstance("RSA");
//            cipher.init(Cipher.DECRYPT_MODE, keyStore.getKey("jaina", "nhsoft123".toCharArray()));
//            System.out.println(new String(cipher.doFinal(bs)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
