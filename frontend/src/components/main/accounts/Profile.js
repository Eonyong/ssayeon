import { Avatar, Button, Container, Divider, Grid, TextField } from "@mui/material";
import { Card, CardActions, CardContent, CardHeader } from '@mui/material';
import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { profileEdit, userProfile, withdrawal } from "../../../user/auth";

export default function Profile(id) {

  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(()=>{
    if (localStorage.getItem('token')) {
      dispatch(userProfile());
    } else {
      navigate('/auth/login');
    }
  }, );
  const userItems = JSON.parse(localStorage.getItem('user'));

  const [User, setUser] = useState({
    class_id: userItems ? userItems.class_id: '',
    company: userItems ? userItems.company: '재직 중이지 않음',
    email: userItems ? userItems.email: '',
    id: userItems ? userItems.id: 0,
    name: userItems ? userItems.name: '',
    nickname: userItems ? userItems.nickname: '',
    picture: userItems ? userItems.picture: '',
    tech_stacks: userItems ? userItems.tech_stacks: [],
  });

  const onDeleteButton = () => {
    dispatch(withdrawal())
    .then(() => {
      localStorage.removeItem('token');
    })
    navigate('/');
  };

  const onEditButton = () => {
    dispatch(profileEdit(User))
    .then(()=>{
      dispatch(userProfile());
      console.log(User);
    })
  }

  const onInputHandler = e => {
    const {name, value} = e.target;
    setUser({
      ...User,
      [ name ]: value,
    });
    console.log(User);
  }
  
  return(
    <Container>
      <Card>
        <CardHeader title='회원정보' />
        <CardContent>
          <Grid container sx={{ alignItems: 'center' }}>
            <Grid item xs={2}>
              <Avatar variant="rounded" src={User.picture} sx={{ width: 56, height: 56 }}/>
            </Grid>
            <Grid item xs={4} />
            <Grid item xs={3}>
              <Button variant="outlined">프로필 변경</Button>
            </Grid>
            <Grid item xs={3}>
              <Button>프로필 제거</Button>
            </Grid>
          </Grid>
          {/* 이름, 학번 Text Field */}
          <Grid container spacing={1} sx={{ my:2 }}>
            <Grid item xs={12} sm={6}>
              <TextField
                id='name' name="name" sx={{ mb: 1 }}
                defaultValue={ User.name } label='이름' fullWidth
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                id='studentId' name="studentId" sx={{ mb: 1 }}
                defaultValue={ User.class_id } label='학번' fullWidth
                InputProps={{ readOnly: true }} variant="standard" 
              />
            </Grid>

          </Grid>
          {/* 이메일, 닉네임 Text Field */}
          <Grid container spacing={1} sx={{ my:2 }}>
            <Grid item xs={12} sm={6}>
              <TextField
                id='email' name="email" sx={{ mb: 1 }} fullWidth
                defaultValue={ User.email } label='이메일'
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                id='nickname' name="nickname" sx={{ mb: 1 }}
                defaultValue={ User.nickname } label='닉네임' fullWidth
                InputProps={{ readOnly: false }} variant="standard" onChange={ onInputHandler }
              />
            </Grid>

          </Grid>
          {/* 회사, 선호 기술 스택 Text Field */}
          <Grid container spacing={1} sx={{ my:2 }}>
            <Grid item xs={12} sm={6}>
              <TextField
                id='company' name="company" sx={{ mb: 1 }}
                defaultValue={ User.company } label='회사' fullWidth
                InputProps={{ readOnly: false }} variant="standard" onChange={ onInputHandler }
              />
            </Grid>
            <Grid item xs={12} sm={6} sx={{ mb: 5 }}>
              <TextField
                id='favor-skills' name="favor-skills" sx={{ mb: 1 }}
                defaultValue={ User.tech_stacks } label='선호 기술 스택' fullWidth
                InputProps={{ readOnly: false }} variant="standard" onChange={ onInputHandler }
              />
            </Grid>
          </Grid>
        </CardContent>
        <Divider />
        <CardActions sx={{ justifyContent: 'space-between' }}>
          <Button
            color="warning" variant="outlined"
            onClick={ onDeleteButton }
          >
            회원 탈퇴
          </Button>
          <Button
            type="submit" sx={{ backgroundColor: '#4B7BF5' }}
            onClick={ onEditButton } variant='filled'
          >
            수정하기
            </Button>
        </CardActions>
      </Card>
    </Container>
  );
};