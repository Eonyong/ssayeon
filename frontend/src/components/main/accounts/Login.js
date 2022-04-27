import { Link } from "react-router-dom";
import { Box, Button, Checkbox, FormControlLabel, Grid, TextField, Typography } from "@mui/material";

export default function Login () {

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
    });
  };

  return (
    <Box
      sx={{
        marginTop: 8,
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        width: '50%'
      }}
    >
      <Grid container direction='column'>
        <Typography variant="h5" sx={{ mb: 2 }}>Login</Typography>
        <Typography>Sign in with your data that you entered during your registration.</Typography>
        <Box
          noValidate container component='form'
          sx={{ my: 5 }}
        >
          <TextField
            id='email' name="email" autoComplete="email"
            type='email' placeholder="Email@email.com" label='Email'
            fullWidth margin='normal' required autoFocus sx={{ mb: 1 }}
          />
          <TextField
            id="password" name="password" autoComplete='current-password'
            type='password' placeholder="패스워드를 입력해주세요" label='Password'
            fullWidth sx={{ mt: 1 }}
          />
          <FormControlLabel
            control={<Checkbox value='remember' color="primary" />}
            label='Keep me logged in'
          />
          <Button
            type="sumbmit" sx={{ py: 1 }}
            fullWidth variant="contained"
          >
            Sign In
          </Button>
        </Box>
        <Link to='#' variant="body2">
          Forgot password?
        </Link>
      </Grid>
    </Box>
  );
};