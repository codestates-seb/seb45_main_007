import { useEffect } from "react";

function GoogleOAuth2RedirectPage() {
  // 1. 인가코드
  const code = new URL(window.location.href).searchParams.get("code");
  // 2. access Token 요청
  const getToken = async (code) => {
    const REST_API_KEY = process.env.REACT_APP_GOOGLE_CLIENT_ID;
    const REDIRECT_URI = process.env.REACT_APP_GOOGLE_REDIRECT_URI;
    const SECRET_KEY = process.env.REACT_APP_GOOGLE_SECRET_KEY;
    const response = await fetch(
      `https://oauth2.googleapis.com/token?grant_type=authorization_code&client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&client_secret=${SECRET_KEY}&code=${code}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
        },
      },
    );
    return response.json();
  };

  useEffect(() => {
    if (code) {
      getToken(code).then((res) => {
        console.log(res.access_token);
      });
    }
  }, [code]);
}

export default GoogleOAuth2RedirectPage;
