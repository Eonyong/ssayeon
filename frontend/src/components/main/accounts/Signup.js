import { Box, Button, Grid, TextField, Typography, Container } from "@mui/material";
import { useState } from "react";
import axios from "axios";

export default function Signup() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  
  // 변수
  const [InputValue, setInputValue] = useState({
    name: '',
    class_id: '',
    nickname: '',
    email: '',
    password: '',
    confirmPasword: '',
  });

  
  // Handler 함수
  const onInputHandler = e => {
    const { name, value } = e.target;
    setInputValue({
      ...InputValue,
      [ name ]: value,
    });
    console.log(VerifyState);
  };
  
  const [VerifyState, setVerifyState] = useState({
    IsClassId: false,
    IsNickname: false,
    IsEmail: false,
  })

  // SSAFY 회원정보 확인
  const onIsInClassIdHandler = e => {
    e.preventDefault();
    axios({
      url: API_BASE_URL + '/auth/verify-user',
      method: 'POST',
      data: {
        class_id: InputValue.class_id,
      }
    })
    .then(()=>{
      setVerifyState({
        ...VerifyState,
        IsClassId: true,
      })
    })
    .catch(()=>alert('싸피 학번을 확인해주세요.'))
  }

  // 닉네임 중복확인 함수
  const onDuplicationNickname = e => {
    e.preventDefault();
    axios({
      url: API_BASE_URL + '/api/auth/duplicate-nickname',
      method: 'POST',
      data: {
        nickname: InputValue.nickname,
      }
    })
    .then(res => {
      if (res.status === 200) {
        setVerifyState({
          ...VerifyState,
          IsNickname: true,
        })
        console.log('닉네임 통과');
      }
    })
    .catch(e => {
      if (e.response.status === 409) {
        alert('이미 존재하는 닉네임입니다.');
      }
    })
  }
  
  // 이메일 중복 확인 함수
  const onDuplicationEmail = e => {
    e.preventDefault();
    axios({
      url: API_BASE_URL + '/api/auth/verify-email',
      method: 'POST',
      data: {
        email: InputValue.email,
      }
    })
    .then(() => {
      setVerifyState({
        ...VerifyState,
        IsEmail: true,
      })
      console.log('이메일 통과');
    })
    .catch(e => {
      if (e.response.status === 409) {
        alert('이미 존재하는 이메일입니다.');
      } else {
        console.log('사용이 불가한 이메일 입니다.');
      }
    })
  };
  
  
  // 회원가입하기 Click 시 함수
  const onSubmitHandler = e => {
    e.preventDefault();
    console.log();
    axios({
      url: API_BASE_URL + '/api/auth/join',
      method: 'POST',
      data: InputValue,
    })
    .then((res)=> {
      if(VerifyState.IsEmail && VerifyState.IsNickname) {
        alert('회원가입이 완료 되었습니다.');
        console.log(res);
      };
    })
    .catch(e => {
      if (e.response.status === 409) {
        alert('회원인증을 해주세요\n이메일 중복 확인을 해주세요.\n닉네임 중복 확인을 해주세요.');
      } else {
        console.log(e);
      }
    })
  };

  // UI 디자인 시작
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
            // action="/auth/join" method="POST"
            sx={{ mt: 3, mb: 5 }} onSubmit={ onIsInClassIdHandler }
          >

            {/* 이름 입력 Field */}
            <TextField
              id='name' name="name" autoComplete="name" margin='normal' 
              type='text' placeholder="이름" label='name' value={ InputValue.name } onChange={ onInputHandler }
              fullWidth required autoFocus sx={{ mb: 1 }}
            />

            {/* 학번 입력 Field */}
            <TextField
              id="class_id" name="class_id" autoComplete='current-classId' 
              type='number' placeholder="0000000" label='학번' value={ InputValue.class_id } onChange={ onInputHandler }
              fullWidth required sx={{ mt: 1 }} helperText='학번을 입력해주세요.'
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
            noValidate container component='form' onSubmit={ onSubmitHandler }
          >
            {/* 닉네임, 중복확인 Field Start */}
            <Grid
              container spacing={2}
              sx={{ alignItems: 'center' }}
            >
              <Grid item xs={9}>
                <TextField
                  id='nickname' name="nickname" autoComplete="nickname" margin='normal'
                  color = { VerifyState.IsNickname ? 'primary' : 'error' }
                  helperText = { VerifyState.IsNickname ? '사용가능한 닉네임 입니다.':'' }
                  disabled = { VerifyState.IsNickname ? true:false }
                  type='text' placeholder="닉네임" label='닉네임' value={ InputValue.nickname } onChange={ onInputHandler }
                  fullWidth required
                />
              </Grid>
              <Grid item xs={3}>
                <Button onClick={ onDuplicationNickname } variant='text'>
                  중복 확인
                </Button>
              </Grid>
            </Grid>

            {/* Email 작성 Field */}
            <Grid
              container spacing={1}
              sx={{ alignItems: 'center' }}
            >
              <Grid item xs={9}>
                <TextField
                  id='email' name="email" autoComplete="email" margin='normal'
                  type='email' placeholder="email@email.com" label='email'
                  value={ InputValue.email } onChange={ onInputHandler }
                  color={ VerifyState.IsEmail ? 'primary' : 'error' }
                  helperText = { VerifyState.IsEmail ? '사용가능한 이메일 입니다.':'' }
                  disabled = { VerifyState.IsEmail ? true:false }
                  fullWidth required sx={{ mb: 1 }}
                />
              </Grid>
              <Grid item xs={3}>
                <Button onClick={ onDuplicationEmail } variant='text'>
                  중복 확인
                </Button>
              </Grid>
            </Grid>

            {/* 이메일 인증번호 Field */}
            { VerifyState.IsEmail ?
              <TextField
                id='validation' name="validation" autoComplete="validation"
                type='password' placeholder="인증번호" label='이메일 인증 확인'
                fullWidth sx={{ mb: 1 }}
              />:<></>
            }
            

            {/* Password Form Field */}
            <Grid
              container spacing={2}
              sx={{ alignItems: 'center', mt: 1, mb: 3  }}
            >
              <Grid item xs={12} sm={6} >
                <TextField
                  id="password" name="password" autoComplete='current-password'
                  type='password' placeholder="패스워드를 입력해주세요" label='password'
                  value={ InputValue.password } onChange={ onInputHandler }
                  fullWidth required 
                  />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  id="confirmPasword" name="confirmPasword" autoComplete='current-passwordConfirm'
                  type='password' placeholder="패스워드를 입력해주세요" label='Password 확인'
                  value={ InputValue.confirmPasword } onChange={ onInputHandler }
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