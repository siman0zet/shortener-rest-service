//package com.simandev.shortener.security;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.Getter;
//import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfTokenRepository;
//import org.springframework.security.web.csrf.DefaultCsrfToken;
//import org.springframework.stereotype.Repository;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Objects;
//import java.util.UUID;
//
//@Repository
//public class JwtTokenRepository implements CsrfTokenRepository {
//    @Getter
//    private String secret;
//
//    public JwtTokenRepository(){
//        this.secret = "springrest";
//    }
//
//    @Override
//    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
//        String id = UUID.randomUUID().toString().replace("-","");
//        Date now = new Date();
//        Date exp = Date.from(LocalDateTime.now().plusMinutes(30)
//        .atZone(ZoneId.systemDefault()).toInstant());
//        String token = "";
//        try {
//            token = Jwts.builder()
//                    .setId(id)
//                    .setIssuedAt(now)
//                    .setNotBefore(now)
//                    .setExpiration(exp)
//                    .signWith(SignatureAlgorithm.HS256, secret)
//                    .compact();
//        }
//        catch (JwtException e){
//            e.printStackTrace();
//        }
//        return new DefaultCsrfToken("x-csrf-token", "_csrf", token);
//    }
//
//    @Override
//    public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        if (Objects.nonNull(csrfToken)) {
//            if (!httpServletResponse.getHeaderNames().contains("ACCESS_CONTROL_EXPOSE_HEADERS"))
//                httpServletResponse.addHeader("ACCESS_CONTROL_EXPOSE_HEADERS", csrfToken.getHeaderName());
//
//            if (httpServletResponse.getHeaderNames().contains(csrfToken.getHeaderName()))
//                httpServletResponse.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
//            else
//                httpServletResponse.addHeader(csrfToken.getHeaderName(), csrfToken.getToken());
//        }
//    }
//
//    @Override
//    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
//            return (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());
//    }
//
//    public void clearToken(HttpServletResponse httpServletResponse){
//        if (httpServletResponse.getHeaderNames().contains("x-csrf-token"))
//            httpServletResponse.setHeader("x-csrf-token", "");
//    }
//}
