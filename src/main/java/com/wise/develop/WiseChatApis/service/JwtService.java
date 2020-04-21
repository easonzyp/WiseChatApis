package com.wise.develop.WiseChatApis.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private HttpServletRequest request;

    //设置过期时间
    private static final long EXPIRE_DATE = 30 * 60 * 100000;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";
    private static final String HEADER = "Authorization";
    private static final String TOKEN_USER_ID = "userId";

    public int getUserId() {
        return verifyToken(TOKEN_USER_ID);
    }

    public static String token(int id) {

        String token;
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String, Object> header = new HashMap<>();
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            //携带username，password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim(TOKEN_USER_ID, id)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return token;
    }

    private int verifyToken(String key) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(request.getHeader(HEADER));
        Map<String, Claim> claims = decodedJWT.getClaims();

        Map<String, Integer> returnMap = new HashMap<>(claims.size());
        claims.forEach((k, v) -> returnMap.put(k, v.asInt()));

        return returnMap.get(key);
    }
}
