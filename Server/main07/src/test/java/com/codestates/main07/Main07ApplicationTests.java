package com.codestates.main07;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
class Main07ApplicationTests {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testMemberRegistration() throws Exception {
		// 회원 가입 테스트 코드
		mockMvc.perform(MockMvcRequestBuilders.post("/members")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"test@example.com\", \"password\": \"password123\"}"))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").exists());
	}

	@Test
	public void testLogin() throws Exception {
		// 로그인 테스트 코드
		mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"test@example.com\", \"password\": \"password123\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
	}

	@Test
	public void testJwtTokenValidation() throws Exception {
		// JWT 토큰 유효성 검증 테스트 코드
		// 먼저 로그인 API를 호출하여 JWT 토큰을 얻어온다.
		String jwtToken = mockMvc.perform(MockMvcRequestBuilders.post("/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"test@example.com\", \"password\": \"password123\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();

		// 얻어온 JWT 토큰을 이용하여 보호된 엔드포인트에 접근해본다.
		// 보호받는 URI (Admin 권한으로 들어가야 하는 URI)를 get 부분에 넣어주기
		mockMvc.perform(MockMvcRequestBuilders.get("/members")
						.header("Authorization", "Bearer " + jwtToken))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testAuthorization() throws Exception {
		// 권한 테스트 코드
		// 일반 사용자 역할로 로그인하여 권한이 필요한 엔드포인트에 접근을 시도합니다.
		String jwtToken = mockMvc.perform(MockMvcRequestBuilders.post("/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"user@example.com\", \"password\": \"userpassword\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();

		mockMvc.perform(MockMvcRequestBuilders.get("/members")
						.header("Authorization", "Bearer " + jwtToken))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	public void testExceptionHandling() throws Exception {
		// 예외 상황 테스트 코드
		mockMvc.perform(MockMvcRequestBuilders.post("/signin")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"email\": \"test@example.com\", \"password\": \"wrongpassword\"}"))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	public void testCors() throws Exception {
		// CORS 테스트 코드 get(접근 테스트 원하는 url 입력)
		mockMvc.perform(MockMvcRequestBuilders.get("/members")
						.header("Origin", "http://localhost:3000"))
				.andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", "http://localhost:3000"));
	}

//	@Test
//	public void testSecurity() throws Exception {
//		// 보안 테스트 코드
//
//	}
}
