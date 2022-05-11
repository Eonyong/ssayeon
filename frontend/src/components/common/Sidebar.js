import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { Avatar, Box, Button, Card, CardContent, Container, Divider, Typography } from '@mui/material';
import { Collapse, List, ListItemButton, ListItemText } from '@mui/material';
import { ExpandMore, ExpandLess } from '@mui/icons-material';
import { logout } from '../../user/auth';

function SideBar() {
  const navigate = useNavigate();
  const { isLoggedIn } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  const userItems = JSON.parse(localStorage.getItem('user'));

  const [User, setUser] = useState({
    class_id: userItems ? userItems.class_id: '',
    company: userItems ? userItems.company: '',
    email: userItems ? userItems.email: '',
    id: userItems ? userItems.id: 0,
    name: userItems ? userItems.name: '',
    nickname: userItems ? userItems.nickname: '',
    picture: userItems ? userItems.picture: '',
    tech_stacks: userItems ? userItems.tech_stacks: [],
  });

  // 게시판 드롭다운 기능
  const [openBoards, setOpenBoards] = useState(false);
  const handleBoardsClick = () => {
    setOpenBoards(!openBoards);
  };
  
  // 싸피 놀이터 드롭다운 기능
  const [openPlayGround, setOpenPlayGround] = React.useState(false);
  const handlePlayGround = () => {
    setOpenPlayGround(!openPlayGround);
  };

  return(
    <Container>
      {
        isLoggedIn ?
        <Box mt={1}>
            <Box sx={{ display: 'flex', flexDirection: {md:'column', lg:'row'}, alignContent: 'center' }}>
              <Avatar src={ User.picture } sx={{ alignSelf: 'center', mx:2 }} />
              <CardContent sx={{ flex: '1 0 auto' }}>
                <Typography component="div" variant="h6">
                  {User.nickname}
                </Typography>
                <Typography variant="subtitle2" color="text.secondary">
                  {User.company}
                </Typography>
              </CardContent>
            </Box>
            <Box sx={{ display: 'flex wrap', flexDirection: {md:'column', lg:'row'}}}>
              <Button onClick={() => {
                navigate('/profile', {state: {id: User.id}});
              }} sx={{width:{md:'100%', lg:'50%'}}}>
                프로필
              </Button>
              <Button onClick={() => {
                dispatch(logout());
                navigate(0);
              }} sx={{width:{md:'100%', lg:'50%'}}}>
                로그아웃
              </Button>
            </Box>
        </Box>
        :
        <Box orientation='vertical' sx={{ width: '100%', my:3 }}>
          <Link to='/auth/login'>
            <Button
              sx={{ py: 1, my: 1, width:'100%' }} variant='outlined'
            >
              로 그 인
            </Button>
          </Link>
          <Link to='/auth/join'>
            <Button
              sx={{ py: 1, my: 1, width:'100%' }} variant='outlined'
            >
              회 원 가 입
            </Button>
          </Link>
        </Box>
      }
      <Divider />
      <ListItemButton sx={{ py: 2 }} component={Link} to="/boards/notice">공지사항</ListItemButton>
      {/* 게시판 리스트 버튼 */}
      <ListItemButton onClick={ handleBoardsClick }>
        <ListItemText primary="게시판" /> {openBoards ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={openBoards} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4, py: 2 }}>
            <ListItemText primary="💚 자유 게시판" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4, py: 2 }}>
            <ListItemText primary="❓ 질문 게시판" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4, py: 2 }}>
            <ListItemText primary="🍯 꿀팁 게시판" />
          </ListItemButton>
        </List>
      </Collapse>

      {/* 싸피 놀이터 리스트 버튼 */}
      <ListItemButton onClick={ handlePlayGround }>
        <ListItemText primary="싸피 놀이터" /> { openPlayGround ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={ openPlayGround } timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4, py: 2 }}>
            <ListItemText primary="⚖️ 밸런스 게임" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4, py: 2 }}>
            <ListItemText primary="👍 선호도 조사" />
          </ListItemButton>
        </List>
      </Collapse>

      <ListItemButton sx={{ py: 2 }}>모임</ListItemButton>
    </Container>
  );
};

export default SideBar;