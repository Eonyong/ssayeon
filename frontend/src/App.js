import { Divider, Grid } from '@mui/material';
import React from 'react';
import { Route, Routes } from 'react-router-dom';
import './App.css';

import SideBar from './components/common/Sidebar'
import Login from './components/main/accounts/Login';

function App() {
  return (
    <div className="App">
      <Grid container>
        <Grid flexItem xs='auto'>
          <SideBar />
        </Grid>
        <Divider orientation='vertical' flexItem />
        <Grid flexItem xs={8} sx={{ textAlign:'-webkit-center' }}>
          <Routes>
            <Route path='/accounts/login' element={ <Login /> }/>
          </Routes>
        </Grid>
      </Grid>
    </div>
  );
}

export default App;
