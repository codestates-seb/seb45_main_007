import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function KakaoAuth() {
    const navigate = useNavigate();

    console.log(process.env);

    const sendAuthCodeToBackend = async (code) => {
        try {
            const response = await axios.post(
                `https://6b4f-125-181-59-71.ngrok-free.app/auth/kakao/login`,
                { code: code },
            );
            console.log("Response from backend:", response.data);
        } catch (error) {
            console.error("Error sending auth code to backend:", error);
        }
    };

    useEffect(() => {
        const code = new URL(window.location.href).searchParams.get("code");
        if (code) {
            sendAuthCodeToBackend(code);
            navigate("/"); // 로그인 페이지로 리디렉션
        }
    }, [navigate]);

    return <div>Redirecting...</div>;
}

export default KakaoAuth;