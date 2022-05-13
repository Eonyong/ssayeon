import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { setMessage } from "./message";

import AuthService from "../services/auth.service";

const user = localStorage.getItem("token");

export const register = createAsyncThunk(
  "auth/join",
  async ({ nickname, name, email, class_id, password }, thunkAPI) => {
    try {
      const res = await AuthService.register(nickname, name, email, class_id, password);
      thunkAPI.dispatch(setMessage(res.data.message));
      console.log(res.data.message);
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

export const withdrawal = createAsyncThunk(
  '/user', async() => {AuthService.withdrawal()}
);

const initialState = user ? { isLoggedIn: true, user } : { isLoggedIn: false, user:null };

export const userProfile = createAsyncThunk(
  'user/mypage', async ({token}, thunkAPI) => {
    try {
      const res = AuthService.userProfile(token);
      return res.data;
    }
    catch (e) {
      const message = (e.response && e.response.data && e.response.data.message) ||
      e.message || e.toString();
      thunkAPI.dispatch(setMessage(message));
      return thunkAPI.rejectWithValue();
    }
  }
)

export const profileEdit = createAsyncThunk(
  'user/edit',
  async (userData, thunkAPI) => {
    try {
      const res = await AuthService.profileEdit(userData);
      return res.data;
    } catch (e) {
      const message = (e.response && e.response.data && e.response.data.message) ||
      e.message || e.toString();
      thunkAPI.dispatch(setMessage(message));
      return thunkAPI.rejectWithValue();
    }
  }
)

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