package com.codestates.main07.security.oauth.sevice;

import com.codestates.main07.member.entity.Member;
import com.codestates.main07.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
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

        String email = oAuth2User.getAttribute("email");

        // 멤버 조회
        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        Member member;
        // 멤버가 없으면 새로 생성
        if (!optionalMember.isPresent()) {
            member = new Member();
            member.setEmail(email);
            member.setUsername(oAuth2User.getAttribute("name"));
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
