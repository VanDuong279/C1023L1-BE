package com.example.projectc1023i1.component;

import com.example.projectc1023i1.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtils {
    @org.springframework.beans.factory.annotation.Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.secretKey}")
    private String secret;


    public String generateToken(Users user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        try {
            // tạo ra token
            String token = Jwts.builder()
                    .setClaims(claims)// thêm claims vào trong token
                    .setSubject(user.getUsername())// chur thể của token chính là số điện thoại
                    .setExpiration(new Date(System.currentTimeMillis() + expiration*1000L))// xét thời gian tồn tại của token
                    .signWith(getSignInkey(), SignatureAlgorithm.HS256)// sử dụng thuật toán để mã hóa token
                    .compact();// phải có để xây dựng token
            return token;
        }catch (Exception e) {
            // có thể sử dụng Logger
//            System.out.println("cannot create token"+ e.getMessage());
            throw new InvalidParameterException("không thể tra token"+e.getMessage());

        }
    }
    // phương thức này giải hóa bí mật dưới dạng key và nó trả về key
    private Key getSignInkey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);//BDAspyT6CJi0W/Ca5d9A5cKCi+pZSAhia7w83vk9gms=)
        return Keys.hmacShaKeyFor(keyBytes);

    }
    // trích xuất tất cả claims từ 1 token cụ thể
    public Claims extractAllClams(String  token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Phương thức này trích xuất một claim cụ thể từ token bằng cách sử dụng một hàm giải quyết claims.
    public <T> T extractClaim(String token, Function<Claims,T> clamsResolver){
        Claims claims = extractAllClams(token);
        return clamsResolver.apply(claims);
    }

    private String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        String secretKey = Encoders.BASE64.encode(keyBytes);
        return secretKey;
    }

    // đây laf hafm kiểm tra Jwt có hết hạn chưa
    public boolean isTokenExpired(String token, Users user) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    // lấy ra số  điện thoại từ 1 token
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // kiểm tra xem token này có hợp lệ hay khoong
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String phoneNumber = extractUserName(token);
        Users users = null;
        // kiểm tra xem số điện thoại đem làm account có trùng không và xem thử token nó có còn hạn hay không
        return (phoneNumber.equals(userDetails.getUsername()) && !isTokenExpired(token,users));
    }
}
