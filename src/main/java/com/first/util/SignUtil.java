package com.first.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: SignUtil
 * @Description: 请求校验工具类 
 * @author sere
 */
public class SignUtil {  
    // 与接口配置信息中的 Token 要一致   
    private static String token = "weixin";  
    /** 
     * 验证签名 
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String signature, String timestamp, String nonce) {  
        String[] arr = new String[] { token, timestamp, nonce };  
        // 将 token、timestamp、nonce 三个参数进行字典序排序   
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }  
        MessageDigest md = null;  
        String tmpStr = null;  

        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行 sha1 加密   
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  

        content = null;  
        // 将 sha1 加密后的字符串可与 signature 对比，标识该请求来源于微信   
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }  

    /** 
     * 将字节数组转换为十六进制字符串 
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  

    /** 
     * 将字节转换为十六进制字符串 
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
        String s = new String(tempArr);  
        return s;  
    }  
    
    public static void main(String args[]) {
    	MessageDigest md = null;  
        String tmpStr = null;  
        
        Long timestamp = new Date().getTime();
        System.out.println("timpstamp:"+timestamp);
        String nonceStr = "weixin";
        String jsapi_ticket = "kgt8ON7yVITDhtdwci0qef-cBmjo4QziPtxpQcAcE_PylypiRK0jqqJByg3jRPeGxto-MRpxksEnTn_xtsL3CA";
        String openId = "oUMJAxLbZqJSNwPFXmSeW4OMCyAY";
        String url = "http://bxu2359350675.my3w.com/index.html?openId="+openId;
        
        
        Map<String,String> data = new HashMap<String,String>();
        data.put("noncestr",nonceStr);
        data.put("timestamp", String.valueOf(timestamp));
        data.put("jsapi_ticket", jsapi_ticket);
        data.put("url", url);
        
        // 将 token、timestamp、nonce 三个参数进行字典序排序   
        List<String> keys = new ArrayList<String>(data.keySet());
		Collections.sort(keys);
		
		StringBuilder content = new StringBuilder();
		
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = data.get(key);
			if (i == keys.size() - 1) {
				content.append(key).append("=").append(value);
			}else{
				content.append(key).append("=").append(value).append("&");
			}
		}

        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行 sha1 加密   
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
            System.out.println("signture:"+tmpStr);
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
	}
}