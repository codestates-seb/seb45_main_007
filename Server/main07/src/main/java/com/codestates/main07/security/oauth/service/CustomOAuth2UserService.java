package com.codestates.main07.security.oauth.service;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// Google로부터 받은 사용자 정보를 처리
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private MemberRepository memberRepository;

    /**
     *loadUser 메서드에서는 사용자의 이메일을 사용하여 DB에서 멤버를 조회하고,
     * 만약 해당 멤버가 존재하지 않는다면 새로 생성하여 저장하는 로직을 포함하고 있습니다.
     * 생성된 또는 조회된 멤버의 ID를 OAuth2User의 attributes에 추가한 후, 그 정보를 반환합니다.
     */
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // `oAuth2User.getAttribute("email")`를 통해 OAuth2 토큰에서 email 속성 값을 가져옵니다.
        // Optional.ofNullable()은 가져온 email 속성 값이 null인지 아닌지를 확인합니다.
        String email = Optional.ofNullable(oAuth2User.getAttribute("email"))
                // .map(Object::toString)을 사용하여 가져온 email 속성 값을 문자열로 변환합니다.
                // 여기서 Object::toString 메서드 참조를 사용하므로 실제 값이 null이 아닌 경우에만 변환 작업이 이루어집니다.
                .map(Object::toString)
                // orElseThrow()를 사용하여 email 속성 값이 null인 경우 예외를 발생시킵니다.
                // 이 때, OAuth2AuthenticationException 예외가 발생하며 "Email not found in OAuth2 token" 메시지가 포함됩니다.
                .orElseThrow(() -> new OAuth2AuthenticationException(
                        new OAuth2Error("invalid_token", "Email not found in OAuth2 token", null)
                ));

        // 멤버 조회
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        Member member;
        // 멤버가 없으면 새로 생성
        if (!optionalMember.isPresent()) {
            member = new Member();
            member.setEmail(email);
            // `oAuth2User.getAttribute("name")`를 통해 OAuth2 토큰에서 name 속성 값을 가져옵니다.
            String name = Optional.ofNullable(oAuth2User.getAttribute("name"))
                    .map(Object::toString)
                    .orElseThrow(() -> new OAuth2AuthenticationException(
                            new OAuth2Error("invalid_token", "Name not found in OAuth2 token", null)
                    ));
            // 변환된 name 값을 멤버 객체의 username 속성에 설정합니다.
            member.setUsername(name);
            memberRepository.save(member);
        } else {
            member = optionalMember.get();
        }

        // 멤버 정보를 OAuth2User의 attributes에 추가
        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("memberId", member.getMemberId());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "name"  // Google의 경우 'name' 속성을 사용하여 사용자의 이름을 구분.
        );
    }
}
