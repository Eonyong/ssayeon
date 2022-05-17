import { Divider, Grid } from "@mui/material";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";

import SideBar from './components/common/Sidebar'
import Signup from './components/main/accounts/Signup';
import Login from './components/main/accounts/Login';
import NoticeList from './components/main/boards/notice/NoticeList';
import NoticeDetail from './components/main/boards/notice/NoticeDetail';
import NewNotice from './components/main/boards/notice/NewNotice';
import FreeList from './components/main/boards/frees/FreeList';
import NewFree from './components/main/boards/frees/NewFree';
import Profile from './components/main/accounts/Profile';
import FreeDetail from './components/main/boards/frees/FreeDetail';
import Main from './components/common/Main';
import { useDispatch } from 'react-redux';
import { userProfile } from './user/auth';
import BalanceList from "./components/main/balance/BalanceList";
import BalanceContent from "./components/main/balance/BalanceContent";
import PreferenceList from "./components/main/preference/PreferenceList";
import RegisterPreference from "./components/main/preference/RegisterPreference";
import PreferenceDetail from "./components/main/preference/PreferenceDetail";

function App() {
  const dispatch = useDispatch();
  if (!localStorage.getItem("token")) {
    localStorage.clear();
  } else{
    dispatch(userProfile());

  }

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
              <Route path='/' element={ <Main /> } />
              <Route path='auth/login' element={ <Login /> } />
              <Route path='auth/join' element={ <Signup /> } />
              <Route path='/profile' element={ <Profile /> } />
              <Route path='/boards/notice/new' element={ <NewNotice /> } />
              <Route path='/boards/notice' element={ <NoticeList />}/>
              <Route path='/balance/list' element={ <BalanceList />}/>
              <Route path='/balance/:id' element={ <BalanceContent />}/>
              {/* id값으로 rerouting */}
              <Route path="/boards/notice/detail" element={<NoticeDetail />} />
              <Route path="/preference" element={<PreferenceList />} />
              <Route path="/preference/new" element={<RegisterPreference />} />
              <Route path="/preference/:id" element={<PreferenceDetail />} />
              <Route path='/boards/free' element={<FreeList />} />
              <Route path='/boards/free/new' element={ <NewFree /> } />
              <Route path='/boards/free/detail' element={ <FreeDetail /> } />
              {/* <Route path='/boards/question' element={<QnaList />} />
              <Route path='/boards/tip' element={<TipList />} /> */}
            </Routes>
          </Grid>
        </Grid>
      </BrowserRouter>
    </div>
  );
}

export default App;
