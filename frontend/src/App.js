import { Divider, Grid } from "@mui/material";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";

import SideBar from "./components/common/Sidebar";
import Signup from "./components/main/accounts/Signup";
import Login from "./components/main/accounts/Login";
import NoticeList from "./components/main/boards/notice/NoticeList";
import NoticeDetail from "./components/main/boards/notice/NoticeDetail";
import NewNotice from "./components/main/boards/notice/NewNotice";
import FreeList from "./components/main/boards/frees/FreeList";
import NewFree from "./components/main/boards/frees/NewFree";
import Profile from "./components/main/accounts/Profile";
import FreeDetail from "./components/main/boards/frees/FreeDetail";
import EditFree from "./components/main/boards/frees/EditFree";
import TipList from "./components/main/boards/tips/TipList";
import Main from "./components/common/Main";
import { useDispatch } from "react-redux";
import { userProfile } from "./user/auth";
import BalanceList from "./components/main/balance/BalanceList";
import BalanceContent from "./components/main/balance/BalanceContent";
import PreferenceList from "./components/main/preference/PreferenceList";
import RegisterPreference from "./components/main/preference/RegisterPreference";
import PreferenceDetail from "./components/main/preference/PreferenceDetail";
import ModifyPreference from "./components/main/preference/ModifyPreference";
import BalanceCreate from "./components/main/balance/BalanceCreate";
import SearchList from "./components/main/search/SearchList";

function App() {
  const dispatch = useDispatch();
  if (!localStorage.getItem("token")) {
    localStorage.clear();
  } else {
    dispatch(userProfile());
  }

  return (
    <div className="App">
      <BrowserRouter>
        <Grid container>
          <Grid item xs={2}>
            <SideBar />
          </Grid>
          <Divider orientation="vertical" flexItem variant="fullWidth" light />
          <Grid item sx={{ textAlign: "-webkit-center" }} xs={9}>
            <Routes>
              <Route path="/" element={<Main />} />
              <Route path="auth/login" element={<Login />} />
              <Route path="auth/join" element={<Signup />} />
              <Route path="/profile" element={<Profile />} />
              <Route path="/boards/notice" element={<NoticeList />} />
              <Route path="/boards/notice/new" element={<NewNotice />} />
              <Route path="/boards/notice/:id" element={<NoticeDetail />} />
              <Route path="/balance/list" element={<BalanceList />} />
              <Route path="/balance/:id" element={<BalanceContent />} />
              <Route path="/balance/create" element={<BalanceCreate />} />
              <Route path="/preference" element={<PreferenceList />} />
              <Route path="/preference/new" element={<RegisterPreference />} />
              <Route path="/preference/:id" element={<PreferenceDetail />} />
              {/* <Route
                path="/preference/:id/modify"
                element={<ModifyPreference />}
              /> */}
              <Route path="/boards/free" element={<FreeList />} />
              <Route path="/boards/free/new" element={<NewFree />} />
              <Route path="/boards/free/:id" element={<FreeDetail />} />
              <Route path="/boards/free/:id/edit" element={<EditFree />} />
              <Route path="/boards/tip" element={<TipList />} />
              <Route path="/search" element={<SearchList />} />
            </Routes>
          </Grid>
        </Grid>
      </BrowserRouter>
    </div>
  );
}

export default App;
