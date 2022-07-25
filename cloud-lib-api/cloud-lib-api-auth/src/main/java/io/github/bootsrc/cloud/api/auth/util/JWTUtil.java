package io.github.bootsrc.cloud.api.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.bootsrc.cloud.api.auth.consts.ResponseCodeEnum;
import io.github.bootsrc.cloud.api.auth.exception.TokenAuthenticationException;

import java.util.Date;

public class JWTUtil {

    public static final long TOKEN_EXPIRE_TIME = 3600 * 24 * 7 * 1000;
    private static final String ISSUER = "bootsrc.github.io";

    public static String generateToken(String userId, String username, String secretKey) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withClaim("userId", userId)
                .withClaim("username", username)
                .sign(algorithm);
    }

    public static void verifyToken(String token, String secretKey) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            jwtVerifier.verify(token);
        } catch (JWTDecodeException jwtDecodeException) {
            throw new TokenAuthenticationException(ResponseCodeEnum.TOKEN_INVALID.getCode(), ResponseCodeEnum.TOKEN_INVALID.getMessage());
        } catch (SignatureVerificationException signatureVerificationException) {
            throw new TokenAuthenticationException(ResponseCodeEnum.TOKEN_SIGNATURE_INVALID.getCode(), ResponseCodeEnum.TOKEN_SIGNATURE_INVALID.getMessage());
        } catch (TokenExpiredException tokenExpiredException) {
            throw new TokenAuthenticationException(ResponseCodeEnum.TOKEN_EXPIRED.getCode(), ResponseCodeEnum.TOKEN_INVALID.getMessage());
        } catch (Exception ex) {
            throw new TokenAuthenticationException(ResponseCodeEnum.UNKNOWN_ERROR.getCode(), ResponseCodeEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    public static String getUserName(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String username = decodedJWT.getClaim("username").asString();
            return username;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUserId(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            String userId = decodedJWT.getClaim("userId").asString();
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
