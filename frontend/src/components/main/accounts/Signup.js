import { Box, Button, Grid, TextField, Typography, Container } from "@mui/material";
import axios from "axios";

export default function Signup() {
  axios.post('http://localhost:3000/auth/signup', {
    email: email,
    password: password,
    nickname: nickname,
    name: name,
    studentId:studentId,
  })

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    console.log({
      email: data.get('email'),
      password: data.get('password'),
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
          <Typography variant="h5" sx={{ mb: 1 }}>Sign Up</Typography>

          {/* 이름, 학번 입력 Field */}
          <Box
            noValidate container component='form'
            sx={{ mt: 3, mb: 5 }} onSubmit={ handleSubmit }
          >

            {/* 이름 입력 Field */}
            <TextField
              id='name' name="name" autoComplete="name" margin='normal'
              type='text' placeholder="이름" label='name'
              fullWidth required autoFocus sx={{ mb: 1 }}
            />

            {/* 학번 입력 Field */}
            <TextField
              id="studentId" name="studentId" autoComplete='current-studentId'
              type='number' placeholder="0000000" label='학번'
              fullWidth required sx={{ mt: 1 }}
            />

            {/* 인증하기 Button Field */}
            <Button
              type="sumbmit" sx={{ py: 1, mt: 2, backgroundColor: '#4B7BF5' }}
              fullWidth variant="contained"
            >
              인증하기
            </Button>
          </Box>
        </Grid>
      </Box>
      {/* 두번째 입력 Form Start */}
      <Box
        sx={{
          marginTop: 2,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Grid container direction='column'>
          <Box
            noValidate container component='form' onSubmit={ handleSubmit }
          >
            {/* 닉네임 Field Start */}
            <TextField
              id='nickname' name="nickname" autoComplete="nickname" margin='normal'
              type='text' placeholder="닉네임" label='닉네임'
              fullWidth required
            />

            {/* Email 작성 Field */}
            <Grid
              container spacing={1}
              sx={{ alignItems: 'center' }}
            >
              <Grid item xs={9}>
                <TextField
                  id='email' name="email" autoComplete="email" margin='normal'
                  type='email' placeholder="Email@email.com" label='Email'
                  fullWidth required sx={{ mb: 1 }}
                />
              </Grid>
              <Grid item xs={3}>
                <Button
                  type="submit" variant='text'
                >
                  이메일 인증하기
                </Button>
              </Grid>
            </Grid>

            {/* 이메일 인증번호 Field */}
            <TextField
              id='validation' name="validation" autoComplete="validation"
              type='password' placeholder="인증번호" label='이메일 인증 확인'
              fullWidth required sx={{ mb: 1 }}
            />
            

            {/* Password Form Field */}
            <Grid
              container spacing={2}
              sx={{ alignItems: 'center', mt: 1, mb: 3  }}
            >
              <Grid item xs={12} sm={6} >
                <TextField
                  id="password" name="password" autoComplete='current-password'
                  type='password' placeholder="패스워드를 입력해주세요" label='Password'
                  fullWidth required 
                  />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  id="passwordConfirm" name="passwordConfirm" autoComplete='current-passwordConfirm'
                  type='password' placeholder="패스워드를 입력해주세요" label='Password 확인'
                  fullWidth required
                  />
              </Grid>
            </Grid>

            {/* 제출 Button Field */}
            <Button
              type="sumbmit" sx={{ py: 1, backgroundColor: '#4B7BF5' }}
              fullWidth variant="contained"
            >
              Sign Up
            </Button>

          </Box>
        </Grid>
      </Box>
    </Container>
  );
};