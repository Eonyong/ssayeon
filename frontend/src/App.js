import { Divider, Grid } from '@mui/material';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

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
import { useEffect } from 'react';

function App() {
  const dispatch = useDispatch();
  if (!localStorage.getItem('token')) {
    localStorage.clear();
  } else{
    dispatch(userProfile())
  }

  return (
    <div className="App">
      <BrowserRouter>
        <Grid container>
          <Grid item xs={2}>
            <SideBar />
          </Grid>
          <Divider orientation='vertical' flexItem />
          <Grid item sx={{ textAlign:'-webkit-center' }} xs={9}>
            <Routes>
              <Route path='/' element={ <Main /> } />
              <Route path='auth/login' element={ <Login /> } />
              <Route path='auth/join' element={ <Signup /> } />
              <Route path='/profile' element={ <Profile /> } />
              <Route path='/boards/notice/new' element={ <NewNotice /> } />
              <Route path='/boards/notice' element={ <NoticeList />}/>
              {/* id값으로 rerouting */}
              <Route path='/boards/notice/detail' element={<NoticeDetail />} />
              <Route path='/boards/free' element={<FreeList />} />
              <Route path='/boards/free/new' element={ <NewFree /> } />
              <Route path='/boards/free/detail' element={ <FreeDetail /> } />
            </Routes>
          </Grid>
        </Grid>
      </BrowserRouter>
    </div>
  );

}

export default App;
