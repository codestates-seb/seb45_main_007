import { createSlice } from "@reduxjs/toolkit";

export const userSlice = createSlice({
  name: "user",
  initialState: {
    loggedIn: false,
    email: "",
    name: "",
    memberId: 1,
  },
  reducers: {
    setUser: (state, action) => {
      state.loggedIn = action.payload.loggedIn;
      state.email = action.payload.email;
      state.name = action.payload.name;
      state.memberId = action.payload.memberId;
      return state;
    },
  },
});

// Action creators are generated for each case reducer function
export const { setUser } = userSlice.actions;

export default userSlice.reducer;
