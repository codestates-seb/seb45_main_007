package com.codestates.main07.jwt.auth.filter;

import com.codestates.main07.jwt.auth.jwt.JwtTokenizer;
import com.codestates.main07.jwt.utils.CustomAuthorityUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer; // JWT를 검증하고 Claims(토큰에 포함된 정보)를 얻는 데 사용
    private final CustomAuthorityUtils authorityUtils; // JWT 검증에 성공하면 Authentication 객체에 채울 사용자의 권한을 생성


    public JwtVerificationFilter(JwtTokenizer jwtTokenizer,
                                 CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            Map<String, Object> claims = verifyJws(request);
            setAuthenticationToContext(claims);
        } catch (SignatureException se) {
            request.setAttribute("exception", se);
        } catch (ExpiredJwtException ee) {
            request.setAttribute("exception", ee);
        } catch (Exception e) {
            request.setAttribute("exception", e);
        }

        filterChain.doFilter(request, response);
    }

    // 특정 조건에 부합하면(true이면) 해당 Filter의 동작을 수행하지 않고 다음 Filter로 건너뛰도록
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");
        //  JWT가 Authorization header에 포함되지 않았다면 JWT 자격증명이 필요하지 않은 리소스에 대한 요청이라고 판단하고 다음(Next) Filter로 처리를 넘기는 것
        return authorization == null || !authorization.startsWith("Bearer");
    }

    /**
     * 아래 verifyJws() 메서드에서
     *  jws의 JWT는 클라이언트가 response header로 전달받은 JWT를 request header에 추가해서 서버 측에 전송한 것.
     *  replace() 메서드를 통해 Bearer 부분을 제거함.
     *  jws로 지정한 이유는 서명된 JWT를 JWS(JSON Web Token Signed)라고 부르기 때문임.
     */
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ", ""); // request의 header에서 JWT를 얻고 있습니다.
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey()); // JWT 서명(Signature)을 검증하기 위한 Secret Key
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();   // JWT에서 Claims를 파싱
        // JWT에서 Claims를 파싱 할 수 있다는 의미는 내부적으로 서명(Signature) 검증에 성공했다는 의미
        return claims;
    }

    private void setAuthenticationToContext(Map<String, Object> claims) {
        String username = (String) claims.get("username");   // JWT에서 파싱 한 Claims에서 username을 얻습니다.

        // "authorities" 클레임을 List<String>으로 형변환
        List<String> authoritiesClaim = (List<String>) claims.get("authorities");

        // 권한 정보 생성
        List<GrantedAuthority> authorities = authorityUtils.createAuthoritiesFromRoles(authoritiesClaim);

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);  // Authentication 객체를 생성
        SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext에 Authentication 객체를 저장
    }
}
