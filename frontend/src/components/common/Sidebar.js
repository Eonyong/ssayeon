import React from 'react';
import { Button, Divider } from '@mui/material';
import { Collapse, List, ListItemButton, ListItemText } from '@mui/material';
import { ExpandMore, ExpandLess } from '@mui/icons-material';
import styled from  'styled-components';

const Side = styled.div`
  display: flex;
  border-right: 1px solid #e0e0e0;
  flex-direction: column;
  justify-content: center;
  width: 10%;
`

function SideBar() {
  
  // 게시판 드롭다운 기능
  const [openBoards, setOpenBoards] = React.useState(false);
  const handleBoardsClick = () => {
    setOpenBoards(!openBoards);
  };

  // 싸피 놀이터 드롭다운 기능
  const [openPlayGround, setOpenPlayGround] = React.useState(false);
  const handlePlayGround = () => {
    setOpenPlayGround(!openPlayGround);
  };
  
  return(
    <Side>
      <Divider />
      <Button>공지사항</Button>
      {/* 게시판 리스트 버튼 */}
      <ListItemButton onClick={handleBoardsClick}>
        <ListItemText primary="게시판" />
        {openBoards ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={openBoards} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemText primary="💚 자유 게시판" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemText primary="💚 질문 게시판" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemText primary="💚 꿀팁 게시판" />
          </ListItemButton>
        </List>
      </Collapse>

      {/* 싸피 놀이터 리스트 버튼 */}
      <ListItemButton onClick={handlePlayGround}>
        <ListItemText primary="싸피 놀이터" />
        {openPlayGround ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={openPlayGround} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemText primary="밸런스 게임" />
          </ListItemButton>
          <ListItemButton sx={{ pl: 4 }}>
            <ListItemText primary="선호도 조사" />
          </ListItemButton>
        </List>
      </Collapse>

      <Button>모임</Button>
      <Button>공지사항</Button>
    </Side>
  );
};

export default SideBar;