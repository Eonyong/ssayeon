import { Link } from "react-router-dom";
import { Box, Button, Checkbox, Container, FormControlLabel, Grid, TextField, Typography } from "@mui/material";
import { useState } from "react";
import axios from "axios";

export default function Login () {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;

  const [Email, setEmail] = useState("");
  const [Password, setPassword] = useState("");

  const onEmailHandler = e => {
    setEmail(e.currentTarget.value);
  };

  const onPasswordHandler = e => {
    setPassword(e.currentTarget.value);
  };

  const handleSubmit = e => {
    e.preventDefault();
    axios({
      url: API_BASE_URL + '/api/auth/login',
      method: 'POST',
      data: {
        email: Email,
        password: Password,
      }
    }).then(res => console.log(res))
  };

  return (
    <Container>
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Grid container direction='column'>
          <Typography variant="h5" sx={{ mb: 2 }}>Login</Typography>
          <Typography>Sign in with your data that you entered during your registration.</Typography>
          <Box
            noValidate container component='form'
            sx={{ my: 5 }} onSubmit={ handleSubmit }
          >
            <TextField
              id='Email' name="Email" autoComplete="Email" margin='normal'
              type='email' placeholder="Email@email.com" label='Email'
              value={ Email } onChange={ onEmailHandler }
              fullWidth sx={{ mb: 1 }}
            />
            <TextField
              id="Password" name="Password" autoComplete='current-Password'
              type='Password' placeholder="패스워드를 입력해주세요" label='Password'
              value={ Password } onChange={ onPasswordHandler }
              fullWidth sx={{ mb: 1 }}
            />
            <FormControlLabel
              control={<Checkbox value='remember' color="primary" />}
              label='Keep me logged in'
            />
            <Button
              type="sumbmit" sx={{ py: 1, backgroundColor: '#4B7BF5' }}
              fullWidth variant="contained"
            >
              Sign In
            </Button>
          </Box>
          <Link to='#' variant="body2" color="#4B7BF5">
            Forgot password?
          </Link>
        </Grid>
      </Box>
    </Container>
  );
};