import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function KakaoAuth() {
  const navigate = useNavigate();
  const sendAuthCodeToBackend = async (code) => {
    try {
      const response = await axios.post(
        `https://616e-125-181-59-71.ngrok-free.app/auth/kakao/login`,
        { code: code },
      );

      console.log("Response from backend:", response.data);
      console.log("Access token received:", response);
    } catch (error) {
      console.error("Error sending auth code to backend:", error);
    }
  };

  const getAccessToken = async (code) => {
    try {
      const response = await axios.post(
        "https://kauth.kakao.com/oauth/token",
        null,
        {
          headers: {
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
          },
          params: {
            grant_type: "authorization_code",
            client_id: process.env.REACT_APP_KAKAO_CLIENT_KEY,
            redirect_uri: process.env.REACT_APP_KAKAO_REDIRECT_URI,
            code,
          },
        },
      );

      console.log("Access token received:", response.data.access_token);
      localStorage.setItem("accessToken", response.data.access_token);
      localStorage.setItem("refreshToken", response.data.refresh_token);
      localStorage.setItem("memberId", response.data.id);
    } catch (error) {
      console.error("Error getting access token:", error);
    }
  };

  useEffect(() => {
    const code = new URL(window.location.href).searchParams.get("code");
    if (code) {
      sendAuthCodeToBackend(code);
      getAccessToken(code);
      navigate("/");
    }
  }, [navigate]);

  return <div>Redirecting...</div>;
}

export default KakaoAuth;
