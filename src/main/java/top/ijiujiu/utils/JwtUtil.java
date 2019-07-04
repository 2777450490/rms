package top.ijiujiu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/28 13:20
 */
public class JwtUtil {

    /**
     * 解析Token
     *
     * @param jsonWebToken   Token String
     * @param base64Security Base64Security Key
     * @return
     */
    public static Claims parseToken(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser().setSigningKey(base64Security).parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 生成Token
     *
     * @param username 用户名
     * @param property 自定义的jwt公共属性（包括超时时长、签发者、base64Security key）
     * @return
     */
//    public static String createToken(String username, JwtProperty property) {
//        Calendar calendar = Calendar.getInstance();
//        JwtBuilder builder = Jwts.builder()
//                .setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
//                .claim("username", username)
//                .setIssuer(property.getIssuer())
//                .signWith(SignatureAlgorithm.HS256, property.getBase64Security())
//                .setExpiration(new Date(calendar.getTimeInMillis() + property.getExpiry())).setNotBefore(calendar.getTime());
//        return builder.compact();
//    }
}
