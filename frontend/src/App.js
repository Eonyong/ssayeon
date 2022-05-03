import { Divider, Grid } from '@mui/material';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import SideBar from './components/common/Sidebar'
import Signup from './components/main/accounts/Signup';
import Login from './components/main/accounts/Login';
import Profile from './components/main/accounts/Profile';
import Main from './components/common/Main';

function App() {

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
              <Route path='/' element={ <Main /> } />
              <Route path='auth/login' element={ <Login /> } />
              <Route path='auth/join' element={ <Signup /> } />
              <Route path='/profile' element={ <Profile /> } />
            </Routes>
          </Grid>
        </Grid>
      </BrowserRouter>
    </div>
  );
}

export default App;
