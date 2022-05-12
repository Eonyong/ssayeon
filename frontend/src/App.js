import { Divider, Grid } from "@mui/material";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";

import SideBar from "./components/common/Sidebar";
import Signup from "./components/main/accounts/Signup";
import Login from "./components/main/accounts/Login";
import NoticeList from "./components/main/boards/notice/NoticeList";
import NoticeDetail from "./components/main/boards/notice/NoticeDetail";
import NewNotice from "./components/main/boards/notice/NewNotice";
import Profile from "./components/main/accounts/Profile";
import Main from "./components/common/Main";
import { useCallback, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { logout } from "./user/auth";
import PreferenceList from "./components/main/preference/PreferenceList";
import RegisterPreference from "./components/main/preference/RegisterPreference";

function App() {
  const { user: currentUser } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const logOut = useCallback(() => {
    dispatch(logout());
  }, [dispatch]);

  useEffect(() => {
    if (currentUser) {
      console.log("hi");
    } else {
      console.log("bye");
    }
  });

  return (
    <div className="App">
      <BrowserRouter>
        <Grid container>
          <Grid item xs={2}>
            <SideBar />
          </Grid>
          <Divider orientation="vertical" flexItem />
          <Grid item sx={{ textAlign: "-webkit-center" }} xs={9}>
            <Routes>
              <Route path="/" element={<Main />} />
              <Route path="auth/login" element={<Login />} />
              <Route path="auth/join" element={<Signup />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/boards/notice/new" element={<NewNotice />} />
              <Route path="/boards/notice" element={<NoticeList />} />
              {/* id값으로 rerouting */}
              <Route path="/boards/notice/detail" element={<NoticeDetail />} />
              <Route path="/preference" element={<PreferenceList />} />
              <Route path="/preference/new" element={<RegisterPreference />} />
            </Routes>
          </Grid>
        </Grid>
      </BrowserRouter>
    </div>
  );
}

export default App;
