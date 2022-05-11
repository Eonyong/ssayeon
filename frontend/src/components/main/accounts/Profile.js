import { Avatar, Button, Container, Divider, Grid, TextField } from "@mui/material";
import { Card, CardActions, CardContent, CardHeader } from '@mui/material';
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { withdrawal } from "../../../user/auth";

export default function Profile() {

  const dispatch = useDispatch();
  const navigate = useNavigate();

  return(
    <Container sx={{ width: '150%' }}>
      <Card>
        <CardHeader title='회원정보' />
        <CardContent>
          <Grid container sx={{ alignItems: 'center' }}>
            <Grid item xs={2}>
              <Avatar variant="rounded">
                example
              </Avatar>
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
                defaultValue='이름' label='이름' fullWidth
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                id='studentId' name="studentId" sx={{ mb: 1 }}
                defaultValue='0000000' label='학번' fullWidth
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>

          </Grid>
          {/* 이메일, 닉네임 Text Field */}
          <Grid container spacing={1} sx={{ my:2 }}>
            <Grid item xs={12} sm={6}>
              <TextField
                id='email' name="email" sx={{ mb: 1 }} fullWidth
                defaultValue='example@example.com' label='이메일'
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>
            <Grid item xs={12} sm={6}>
              <TextField
                id='nickname' name="nickname" sx={{ mb: 1 }}
                defaultValue='닉네임' label='닉네임' fullWidth
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>

          </Grid>
          {/* 회사, 선호 기술 스택 Text Field */}
          <Grid container spacing={1} sx={{ my:2 }}>
            <Grid item xs={12} sm={6}>
              <TextField
                id='company' name="company" sx={{ mb: 1 }}
                defaultValue='삼성전자' label='회사' fullWidth
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>
            <Grid item xs={12} sm={6} sx={{ mb: 5 }}>
              <TextField
                id='favor-skills' name="favor-skills" sx={{ mb: 1 }}
                defaultValue='#자바 #파이썬' label='선호 기술 스택' fullWidth
                InputProps={{ readOnly: true }} variant="standard"
              />
            </Grid>
          </Grid>
        </CardContent>
        <Divider />
        <CardActions sx={{ justifyContent: 'space-between' }}>
          <Button
            color="warning" variant="outlined"
            onClick={()=>{
              dispatch(withdrawal())
              .then(() => {
                localStorage.removeItem('token');
              })
              navigate('/');
            }}
          >
            회원 탈퇴
          </Button>
          <Button type="submit" sx={{ backgroundColor: '#4B7BF5' }} variant='filled'>수정하기</Button>
        </CardActions>
      </Card>
    </Container>
  );
};