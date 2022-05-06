import axios from "axios";
import React, { useState } from "react";
import { TextField, Container, Box, Button } from "@mui/material";

function NewArticle() {
  // 인증 관련
  let token = sessionStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  // 게시글 작성 form
  const [form, setForm] = useState({
    title: "",
    description: "",
    board_id: "",
    category_id: ""
  });

  const addNewArticle = () => {
    if (!form.board_id) {
      alert("게시판을 선택하세요");
    } else if (!form.category_id) {
      alert("카테고리를 선택하세요");
    } else if (!form.title) {
      alert("제목을 입력하세요");
    } else if (!form.content) {
      alert("내용을 입력하세요");
    } else {
      axios.post(
        `${process.env.REACT_APP_API_ROOT}/article`,
        {
          title: form.title,
          description: form.description,
          board_id: form.board_id,
          category_id: form.category_id
        },
        {
          headers: headers,
        }
      )
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
    }
  };

  return (
    <Container sx={{ display: 'flex' }}>
      <Box
         sx={{
          marginTop: 2,
          width: 'auto',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <h2 sx={{ alignItems: 'center' }}>게시글 작성</h2>
        <TextField 
          sx={{ display: "flex", width: "100%", marginTop: "10px" }}
          fullWidth
          id="title" 
          name="title"
          label="제목" 
          variant="outlined" />
        <TextField
          sx={{ display: "flex", width: "100%", marginTop: "10px" }} 
          id="description"
          name="description"
          label="내용"
          multiline rows={20}
        />
        <Button 
          sx={{ display: "flex", width: "100%", marginTop: "10px" }} 
          variant="contained">
            작성
        </Button>
      </Box>
    </Container>
  )
}

export default NewArticle;