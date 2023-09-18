import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    loggedIn: false,
    email: "",
    name: "",
    memberId: 1,
    accessToken: "",
    refreshToken: "",
  },
  reducers: {
    setUser: (state, action) => {
      state.loggedIn = action.payload.loggedIn;
      state.email = action.payload.email;
      state.name = action.payload.name;
      state.memberId = action.payload.memberId;
      state.accessToken = action.payload.accessToken; // Setting new state properties
      state.refreshToken = action.payload.refreshToken; // Setting new state properties
      return state;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setUser } = userSlice.actions;

export default userSlice.reducer;
