import React from "react";

function KakaoBtn() {
  const kakao_client_id = process.env.REACT_APP_KAKAO_CLIENT_ID;
  const kakao_redirect_uri = process.env.REACT_APP_REDIRECT_URI;
  const link = `https://kauth.kakao.com/oauth/authorize?client_id=${kakao_client_id}&redirect_uri=${kakao_redirect_uri}&response_type=code`;

  const kakaoLogin = () => {
    window.location.href = link;
  };
  return (
    <>
      <button onClick={kakaoLogin}>카카오 로그인</button>
    </>
  );
}

export default KakaoBtn;
