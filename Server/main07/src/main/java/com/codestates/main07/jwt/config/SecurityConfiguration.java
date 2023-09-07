package com.codestates.main07.jwt.config;



import com.codestates.main07.jwt.auth.filter.JwtAuthenticationFilter;
import com.codestates.main07.jwt.auth.filter.JwtVerificationFilter;
import com.codestates.main07.jwt.auth.handler.MemberAccessDeniedHandler;
import com.codestates.main07.jwt.auth.handler.MemberAuthenticationEntryPoint;
import com.codestates.main07.jwt.auth.handler.MemberAuthenticationFailureHandler;
import com.codestates.main07.jwt.auth.handler.MemberAuthenticationSuccessHandler;
import com.codestates.main07.jwt.auth.jwt.JwtTokenizer;
import com.codestates.main07.jwt.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer,
                                   CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new MemberAuthenticationEntryPoint())
                .accessDeniedHandler(new MemberAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/*/members/signup").permitAll()         // 회원등록 누구나 가능
                        .antMatchers(HttpMethod.PATCH, "/*/members/**").hasRole("USER")  // 회원 정보 수정 회원만 가능
                        .antMatchers(HttpMethod.GET, "/*/members").hasRole("ADMIN")     // 모든 회원 정보는 관리자만 접근 가능
                        .antMatchers(HttpMethod.GET, "/*/members/**").hasAnyRole("USER", "ADMIN")  // 특정 회원 조회 누구나
                        .antMatchers(HttpMethod.DELETE, "/*/members/**").hasRole("USER") // 탈퇴 회원만
                        .anyRequest().permitAll()
                );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        configuration.setAllowedOrigins(Arrays.asList(
                // "http://munjaenow.s3-website.ap-northeast-2.amazonaws.com/", (이번 메인 프로젝트 s3 아마존 주소 입력)
                "http://localhost:3000"
        ));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true); // 인증 정보(Credentials)를 허용
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Refresh"));


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // 구현한 JwtAuthenticationFilter를 등록하는 역할
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        // configure() 메서드를 오버라이드해서 Configuration을 커스터마이징
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/signin");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils);
            // JwtVerificationFilter는 JwtAuthenticationFilter에서 로그인 인증에 성공한 후 발급받은 JWT가
            // 클라이언트의 request header(Authorization 헤더)에 포함되어 있을 경우에만 동작
            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
