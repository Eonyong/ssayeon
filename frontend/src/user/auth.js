import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { setMessage } from "./message";

import AuthService from "../services/auth.service";

const user = JSON.parse(localStorage.getItem("user"));

export const register = createAsyncThunk(
  "auth/join",
  async ({ nickname, name, email, class_id, password }, thunkAPI) => {
    try {
      const res = await AuthService.register(nickname, name, email, class_id, password);
      thunkAPI.dispatch(setMessage(res.data.message));
      return res.data;
    } catch (e) {
      const message = (e.response && e.response.data && e.response.data.message) ||
      e.message || e.toString();
      thunkAPI.dispatch(setMessage(message));
      return thunkAPI.rejectWithValue();
    }
  }
);

export const login = createAsyncThunk(
  "auth/login",
  async ({ email, password }, thunkAPI) => {
    console.log(thunkAPI);
    try {
      const data = await AuthService.login(email, password);
      return { user: data };
    } catch (e) {
      const message =
        (e.response && e.response.data && e.response.data.message) ||
        e.message || e.toString();
      thunkAPI.dispatch(setMessage(message));
      return thunkAPI.rejectWithValue();
    }
  }
);

export const logout = createAsyncThunk(
  'auth/logout', async () => {AuthService.logout()}
);

const initialState = user ? { isLoggedIn: true, user } : { isLoggedIn: false, user:null };

const authUser = createSlice({
  name: "auth",
  initialState,
  extraReducers: {
    [register.fulfilled]: (state, action) => {
      state.isLoggedIn = false;
    },
    [register.rejected]: (state, action) => {
      state.isLoggedIn = false;
    },
    [login.fulfilled]: (state, action) => {
      state.isLoggedIn = true;
      state.user = action.payload.user;
    },
    [login.rejected]: (state, action) => {
      state.isLoggedIn = false;
      state.user = null;
    },
    [logout.fulfilled]: (state, action) => {
      state.isLoggedIn = false;
      state.user = null;
    },
  }
});

const { reducer } = authUser;
export default reducer;