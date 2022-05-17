import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import {
  Avatar,
  Badge,
  Box,
  Button,
  CardActions,
  CardContent,
  Container,
  Dialog,
  Divider,
  Typography,
} from "@mui/material";
import { Collapse, List, ListItemButton, ListItemText } from "@mui/material";
import { ExpandMore, ExpandLess, Mail } from "@mui/icons-material";
import { logout } from "../../user/auth";
import axios from "axios";
import MessageModal from "./Messagemodal";


function SideBar() {
  const navigate = useNavigate();
  const { user } = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  const headers = { Authorization: `Bearer ${user}` };
  const userItems = JSON.parse(localStorage.getItem("user"));

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const [User] = useState({
    class_id: userItems ? userItems.class_id : "",
    company: userItems ? userItems.company : "",
    email: userItems ? userItems.email : "",
    id: userItems ? userItems.id : 0,
    name: userItems ? userItems.name : "",
    nickname: userItems ? userItems.nickname : "",
    picture: userItems ? userItems.picture : "",
    tech_stacks: userItems ? userItems.tech_stacks : [],
  });

  // 게시판 드롭다운 기능
  const [openBoards, setOpenBoards] = useState(false);
  const handleBoardsClick = () => {
    setOpenBoards(!openBoards);
  };

  // 싸피 놀이터 드롭다운 기능
  const [openPlayGround, setOpenPlayGround] = useState(false);
  const handlePlayGround = () => {
    setOpenPlayGround(!openPlayGround);
  };

  // 안읽은 쪽지 갯수 기능
  const [unReadMessage, setUnReadMessage] = useState(0);
  const UnReadMessageCnt = () => {
    return axios
      .get(API_BASE_URL + "/user/message/unread-cnt", { headers: headers })
      .then((res) => setUnReadMessage(res.data.data.unread_message_cnt))
      .catch((e) => console.log(e));
  };

  if (user) {
    UnReadMessageCnt();
  };

  return (
    <Container>
      <Box
        component="img" alt="logo"
        onClick={()=>{navigate('/')}} src={require("../images/ssayeon.png")}
        sx={{
          width: "150px",
          marginTop: "20px",
          cursor: "pointer"
        }}
      />
      {user ? (
        <Box mt={1}>
          <Box
            sx={{
              display: "flex",
              flexWrap: "wrap",
              justifyContent: "center",
              flexDirection: { md: "column", lg: "row" },
              alignContent: "center",
            }}
          >
            <Avatar src={User.picture} sx={{ alignSelf: "center", mx: 2 }} />
            <CardContent sx={{ flex: "1 0 auto" }}>
              <Typography component="div" variant="h5" border={true}>
                {User.nickname}
              </Typography>
              <Typography variant="subtitle2" color="text.secondary">
                {User.company}{" "}
              </Typography>
            </CardContent>
            <CardActions>
              <Button
                onClick={() => {
                  handleOpen();
                }}
              >
                <Badge badgeContent={unReadMessage} color="primary">
                  <Mail fontSize="inherit" />
                </Badge>
              </Button>
            </CardActions>
          </Box>
          <Dialog
            open={open} fullWidth
            keepMounted maxWidth='md'
            onClose={handleClose}
            aria-describedby="alert-dialog-slide-description"
          >
            <MessageModal />
          </Dialog>
          <Box
            sx={{
              display: "flex wrap",
              flexDirection: { md: "column", lg: "row" },
            }}
          >
            <Button
              onClick={() => {
                navigate("/profile", { state: { id: User.id } });
              }}
              sx={{ width: { md: "100%", lg: "50%" } }}
            >
              프로필
            </Button>
            <Button
              onClick={() => {
                dispatch(logout());
                navigate(0);
              }}
              sx={{ width: { md: "100%", lg: "50%" } }}
            >
              로그아웃
            </Button>
          </Box>
        </Box>
      ) : (
        <Box orientation="vertical" sx={{ width: "100%", my: 3 }}>
          <Link to="/auth/login">
            <Button sx={{ py: 1, my: 1, width: "100%" }} variant="outlined">
              로 그 인
            </Button>
          </Link>
          <Link to="/auth/join">
            <Button sx={{ py: 1, my: 1, width: "100%" }} variant="outlined">
              회 원 가 입
            </Button>
          </Link>
        </Box>
      )}
      <Divider />
      <ListItemButton sx={{ py: 2 }} component={Link} to="/boards/notice">
        공지사항
      </ListItemButton>
      {/* 게시판 리스트 버튼 */}
      <ListItemButton onClick={handleBoardsClick}>
        <ListItemText primary="게시판" />{" "}
        {openBoards ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={openBoards} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton
            sx={{ pl: 4, py: 2 }}
            component={Link}
            to="/boards/free"
          >
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
      <ListItemButton onClick={handlePlayGround}>
        <ListItemText primary="싸피 놀이터" />{" "}
        {openPlayGround ? <ExpandLess /> : <ExpandMore />}
      </ListItemButton>
      <Collapse in={openPlayGround} timeout="auto" unmountOnExit>
        <List component="div" disablePadding>
          <ListItemButton sx={{ pl: 4, py: 2 }}>
            <Link to="/balance/list">
              <ListItemText primary="⚖️ 밸런스 게임" />
            </Link>
          </ListItemButton>
          <ListItemButton
            sx={{ pl: 4, py: 2 }}
            component={Link}
            to="/preference"
          >
            <ListItemText primary="👍 선호도 조사" />
          </ListItemButton>
        </List>
      </Collapse>

      <ListItemButton sx={{ py: 2 }}>모임</ListItemButton>
    </Container>
  );
}

export default SideBar;
