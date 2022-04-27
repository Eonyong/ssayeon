import { Divider, Grid } from '@mui/material';
import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import SideBar from './components/common/Sidebar'
import Signup from './components/main/accounts/Signup';
import Login from './components/main/accounts/Login';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Grid container>
          <Grid>
            <SideBar />
          </Grid>
          <Divider orientation='vertical' flexItem />
          <Grid sx={{ textAlign:'-webkit-center' }}>
            <Routes>
              <Route path='accounts/singup' element={ <Signup /> } />
              <Route path='accounts/login' element={ <Login /> } />
            </Routes>
          </Grid>
        </Grid>
      </BrowserRouter>
    </div>
  );
}

export default App;
