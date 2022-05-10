import { Divider, Grid } from '@mui/material';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import SideBar from './components/common/Sidebar'
import Signup from './components/main/accounts/Signup';
import Login from './components/main/accounts/Login';
import NoticeList from './components/main/boards/notice/NoticeList';
import NoticeDetail from './components/main/boards/notice/NoticeDetail';
import NewNotice from './components/main/boards/notice/NewNotice';
import Profile from './components/main/accounts/Profile';
import Main from './components/common/Main';
import { useCallback, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { logout } from './user/auth';

function App() {

  const { user: currentUser } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const logOut = useCallback(() => {
    dispatch(logout());
  }, [dispatch]);

  useEffect(() => {
    if (currentUser) {
      console.log('hi');
    } else {
      console.log('bye');
    }
  })

  const [IsUser, setIsUser] = useState({
    user: '',
    data: '',
    isLogin: false,
  });

  const handleUser = (data) => setIsUser(data);

  useEffect(() => {
    handleUser();
    console.log(IsUser);
  }, [IsUser]);
  

  return (
    <div className="App">
      <BrowserRouter>
        <Grid container>
          <Grid item>
            <SideBar />
          </Grid>
          <Divider orientation='vertical' flexItem />
          <Grid item sx={{ textAlign:'-webkit-center' }}>
            <Routes>
              <Route exact path='/' element={ <Main /> } />
              <Route exact path='auth/login' element={ <Login IsUser={setIsUser} /> } />
              <Route exact path='auth/join' element={ <Signup /> } />
              <Route path='/profile' element={ <Profile /> } />
              <Route path='/boards/notice/new' element={ <NewNotice /> } />
              <Route path='/boards/notice' element={ <NoticeList />}/>
              {/* id값으로 rerouting */}
              <Route path='/boards/notice/detail' element={<NoticeDetail />} />
            </Routes>
          </Grid>
        </Grid>
      </BrowserRouter>
    </div>
  );
}

export default App;
