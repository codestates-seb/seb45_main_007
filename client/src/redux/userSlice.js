import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    loggedIn: false,
    email: localStorage.getItem("email") || "",
    name: "",
    username: localStorage.getItem("username") || "",
    nickname: localStorage.getItem("nickname") || "",
    memberId: localStorage.getItem("memberId") || 1,
    accessToken: localStorage.getItem("accessToken") || "",
    refreshToken: localStorage.getItem("refreshToken") || "",
  },
  reducers: {
    setUser: (state, action) => {
      state.loggedIn = action.payload.loggedIn;
      state.email = action.payload.email;
      state.name = action.payload.name;
      state.username = action.payload.username;
      state.nickname = action.payload.nickname;
      state.memberId = action.payload.memberId;
      state.accessToken = action.payload.accessToken;
      state.refreshToken = action.payload.refreshToken;

      localStorage.setItem("memberId", action.payload.memberId);
      return state;
    },
  },
});

export const { setUser } = userSlice.actions;

export default userSlice.reducer;
