import {
  Box, Button, Grid, TextField, Typography, Container,
  Dialog, DialogActions, DialogContent, DialogContentText } from "@mui/material";
import { useState } from "react";
import axios from "axios";
import { useDispatch } from "react-redux";
import { register } from "../../../user/auth";
import { useNavigate } from "react-router-dom";

export default function Signup() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  const dispatch = useDispatch();
  const navigate = useNavigate();
  // Modal 창 띄우는 변수
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  // 회원가입 입력 변수
  const [InputValue, setInputValue] = useState({
    name: '', nickname: '',
    class_id: '', email: '',
    password: '', confirmPasword: '',
    validation: '', confirmValidation: '',
  });

  // Handler 함수
  const onInputHandler = e => {
    const name = e.target.name;
    const value = e.target.value.trim();
    if (name === 'confirmPasword') {
      passwordValidation(value);
    }
    else if (name === 'class_id') {
      setVerifyState({
        ...VerifyState,
        IsClassId: false,
      })
    }
    else if (name === 'email') {
      setVerifyState({
        ...VerifyState,
        IsEmail: false,
      })
    }
    else if (name === 'nickname') {
      setVerifyState({
        ...VerifyState,
        IsNickname: false,
      })
    }

    setInputValue({
      ...InputValue,
      [ name ]: value,
    });
  };
  
  // 중복확인 변수
  const [VerifyState, setVerifyState] = useState({
    IsClassId: false,
    IsNickname: false,
    IsEmail: false,
    IsPassword: false,
    passwordWarnig: '',
  })

  // SSAFY 회원 인증
  const onIsInClassIdHandler = e => {
    e.preventDefault();
    console.log(e.target);

    axios.post(API_BASE_URL + '/auth/verify-user', {
      name: InputValue.name,
      class_id: InputValue.class_id,
    })
    .then((res) => {
      return setVerifyState({
        ...VerifyState,
        IsClassId: true,
      });
    })
    .catch((e) => {
      console.log(e);
    })
    console.log(VerifyState);
  }

  // 닉네임 중복확인 함수
  const onDuplicationNickname = e => {
    e.preventDefault();
    if (InputValue.nickname.length > 0) {
      axios({
        url: API_BASE_URL + '/auth/duplicate-nickname',
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
        else {
          console.log(e);
        }
      })
    }
  }
  
  // 이메일 중복 확인 함수
  const onDuplicationEmail = e => {
    handleOpen();
    axios({
      url: API_BASE_URL + '/auth/verify-email',
      method: 'POST',
      data: {
        email: InputValue.email,
      }
    })
    .then((e) => {
      setVerifyState({
        ...VerifyState,
        IsEmail: true,
      })
      const dt = e.data.data;
      setInputValue({
        ...InputValue,
        confirmValidation: dt,
      });
      console.log('이메일 통과', dt);
    })
    .catch(e => {
      alert('사용이 불가하거나 이미 존재하는 이메일입니다.');
      setVerifyState({
        ...VerifyState,
        IsEmail: false,
      })
    })
  };

  // 비밀번호 유효성 검사
  const passwordValidation = (prop) => {
    const prop_trim = prop.trim();
    const specialCase = /^.*(?=.{8,})(?=.*?[#?!@$%^&*-])(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])/;

    if (!specialCase.test(prop_trim)) {
      setVerifyState({
        ...VerifyState,
        IsPassword: false,
        passwordWarnig: '영문대/소문자/숫자/특수문자 조합 8자 이상입니다.',
      });
    } else {
      setVerifyState({
        ...VerifyState,
        IsPassword: true,
        passwordWarnig: '사용 가능한 비밀번호입니다.',
      });
    }
  };
  
  
  // 회원가입하기 Click 시 함수
  const onSubmitHandler = e => {
    e.preventDefault();
    console.log(VerifyState);
    if(VerifyState.IsEmail && VerifyState.IsNickname
      && VerifyState.IsPassword && VerifyState.IsClassId ) {
      dispatch(register(InputValue))
      .unwrap()
      .then(() => {
          console.log('회원가입이 완료 되었습니다.');
          navigate('/', {replace: true}); 
        })
        .catch(e=>{
          alert('회원인증을 해주세요\n이메일 중복 확인을 해주세요.\n닉네임 중복 확인을 해주세요.');
          console.log(e);
        });
    };
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
            noValidate container component='form' className="class_id"
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
              fullWidth required sx={{ mt: 1 }}
              color={InputValue.class_id.length === 7 ? 'primary': 'error'}
              helperText={ InputValue.class_id.length === 7 ? '' : '학번을 입력해주세요.'}
            />

            {/* 인증하기 Button Field */}
            <Button
              type="submit" sx={{ py: 1, mt: 2, backgroundColor: '#4B7BF5' }}
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
                  type='text' placeholder="닉네임" label='닉네임' value={ InputValue.nickname } onChange={ onInputHandler }
                  fullWidth required
                />
              </Grid>
              <Grid item xs={3}>
                <Button className="nickname"
                  onClick={ onDuplicationNickname } variant='text'
                >
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
                  helperText = { VerifyState.IsEmail ? '사용가능한 이메일 입니다.':false }
                  fullWidth required sx={{ mb: 1 }}
                />
              </Grid>
              <Grid item xs={3}>
                <Button className="email"
                  onClick={onDuplicationEmail} variant='text'
                >
                  { VerifyState.IsEmail ? "재발송":"중복 확인"}
                </Button>
                <Dialog
                  open={open} keepMounted
                  onClose={handleClose}
                  aria-describedby="alert-dialog-slide-description"
                >
                  <DialogContent>
                    <DialogContentText id="alert-dialog-slide-description">
                      해당 이메일로 인증 번호를 발송했습니다.
                    </DialogContentText>
                  </DialogContent>
                  <DialogActions>
                    <Button onClick={handleClose}>닫기</Button>
                  </DialogActions>
                </Dialog>
              </Grid>
            </Grid>

            {/* 이메일 인증번호 Field */}
            { VerifyState.IsEmail ?
              <TextField
                id='validation' name="validation" autoComplete="validation"
                type='text' placeholder="인증번호" label='이메일 인증 확인'
                value={ InputValue.validation } onChange= { onInputHandler }
                fullWidth sx={{ mb: 1 }}
                color={ InputValue.validation === InputValue.confirmValidation ? 'primary':'error' }
              />:<></>
            }
            

            {/* Password Form Field */}
            <Grid
              container spacing={2}
              sx={{ alignItems: 'center', mt: 1, mb: 3  }}
            >
              {/* 패스워드 입력 field */}
              <Grid item xs={12} >
                <TextField
                  id="password" name="password" autoComplete='current-password'
                  type='password' placeholder="패스워드를 입력해주세요" label='password'
                  value={ InputValue.password } onChange={ onInputHandler }
                  fullWidth required helperText={ VerifyState.passwordWarnig }
                  color= { VerifyState.IsPassword ? 'primary': 'error' }
                />
              </Grid>
              {/* 패스워드 확인 Field */}
              <Grid item xs={12}>
                <TextField
                  fullWidth required
                  id="confirmPasword" name="confirmPasword" autoComplete='current-passwordConfirm'
                  type='password' placeholder="패스워드를 입력해주세요" label='password 확인'
                  value={ InputValue.confirmPasword } onChange={ onInputHandler }
                  color={(InputValue.confirmPasword === InputValue.password) ? 'primary':'error'}
                />
              </Grid>
            </Grid>

            {/* 제출 Button Field */}
            <Button
              type="submit" sx={{ py: 1, backgroundColor: '#4B7BF5' }}
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