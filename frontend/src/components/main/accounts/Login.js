import { Link, useNavigate } from "react-router-dom";
import { Box, Button, Checkbox, Container, FormControlLabel, Grid, TextField, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { clearMessage } from "../../../user/message";
import { login, userProfile } from "../../../user/auth";

const Login = () => {
  const { user } = useSelector((state)=>state.auth);
  const [loading, setLoading] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    dispatch(clearMessage());
  }, );

  if (user) navigate('/');

  const [InputValue, setInputValue] = useState({
    email: '',
    password: '',
  });

  const onInputHandler = e => {
    const {name, value} = e.target;
    setInputValue({
      ...InputValue,
      [ name ]: value,
    });
  }

  const onSubmit = e => {
    e.preventDefault();
    setLoading(true);
    const {email, password} = InputValue;
    dispatch(login({ email, password }))
    .unwrap()
    .then(() => {
      setTimeout(dispatch(userProfile()), 100);
      navigate('/');
      navigate(0);
    })
    .catch(() => {
      setLoading(false);
    });
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
            sx={{ my: 5 }} onSubmit={ onSubmit }
          >
            <TextField
              id='email' name="email" autoComplete="email" margin='normal'
              type='email' placeholder="email@email.com" label='email'
              value={ InputValue.email } onChange={ onInputHandler }
              fullWidth sx={{ mb: 1 }}
            />
            <TextField
              id="password" name="password" autoComplete='current-password'
              type='password' placeholder="패스워드를 입력해주세요" label='password'
              value={ InputValue.password } onChange={ onInputHandler }
              fullWidth sx={{ mb: 1 }}
            />
            <FormControlLabel
              control={<Checkbox value='remember' color="primary" />}
              label='Keep me logged in'
            />
            <Button
              type="submit" sx={{ py: 1, backgroundColor: '#4B7BF5' }}
              fullWidth variant="contained" disabled={loading}
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

export default Login;