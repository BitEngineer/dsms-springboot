package com.dengjian.gomars.common.util;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 将用户id存储到JWT的payload中{"userId":}
 *
 */
public class JwtUtils {
	
	private static final String CLAIMS_KEY_USER_ID = "userId";
	
	private static final String stringKey = "lwlsecret";
	private static SecretKey key;
	
	static {
		byte[] encodedKey = Base64.decodeBase64(stringKey);
        key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
	}

	public static String buildJwt() {
        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")    //设置header
                .setHeaderParam("alg", "HS256")
                .setIssuedAt(new Date())     //设置iat
                .claim("name", "liuwillow")   //设置payload的键值对
                .claim("level", "100")
                .setIssuer("lwl")
                .signWith(signatureAlgorithm, key);    //签名，需要算法和key
        String jwt = builder.compact();
        System.out.println(jwt);
        return jwt;

	}
	
	public static Claims parseJwt(String jwt) {
		//获取claims
	    Claims claims = Jwts.parser()
	                .setSigningKey(key)     //此处的key要与之前创建的key一致
	                .parseClaimsJws(jwt)
	                .getBody();
	    //获取name和level
	    String name = (String) claims.get("name");
	    String level = (String) claims.get("level");
	    System.out.println("name:" + name + " level:" + level);
	    return claims;
	}
	
	public static String getClaimValueByKey(String jwt, String claimKey) {
		Claims claims = parseJwt(jwt);
		return (String) claims.get(claimKey);
	}
	
	public static void main(String[] args) {
		buildJwt();
		parseJwt(buildJwt());
	}
}
