import { Box, Button, Checkbox, Grid, TextField, Typography } from "@mui/material";
import { Link } from "react-router-dom";

export default function Login () {
  return (
    <Box component='form' sx={{ width: '60%' }}>
      <Grid container direction='column'>
        <Typography variant="h1" sx={{ mb: 2 }}>Login</Typography>
        <Typography>Sign in with your data that you entered during your registration.</Typography>
        <Box
          sx={{ flexDirection: 'column', my: 5 }} container component='form'
        >
          <TextField
            type='email' placeholder="Email@email.com" label='Email'
            fullWidth sx={{ mb: 1 }}
          />
          <TextField
            type='password' placeholder="패스워드를 입력해주세요" label='Password'
            fullWidth sx={{ mt: 1 }}
          />
          <Grid container direction='row' sx={{ alignItems: 'center', mt: 3 }}>
            <Grid item>
              <Checkbox />
            </Grid>
            <Grid item>
              <Typography>Keep me logged in</Typography>
            </Grid>
          </Grid>

          <Button fullWidth variant="contained" sx={{ py: 1 }}>Sign In</Button>
        </Box>

        <Link to='/forgot-password'>
          <Button variant="text">Forgot Password</Button>
        </Link>

      </Grid>
    </Box>
  );
};