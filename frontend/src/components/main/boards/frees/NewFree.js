import axios from "axios";
import React, { useState } from "react";
import { Link } from 'react-router-dom';
import { FormControl, TextField, Container, Box, Button } from "@mui/material";

function NewFree() {
  // 인증 관련
  let token = sessionStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  }

  // 게시글 작성 form
  const [form, setForm] = useState({
    title: "",
    description: "",
    board_id: "1",
    category_id: "1"
  });

  const onChange = (event) => {
    const { name, value } = event.target;
    setForm({
      ...form,
      [name]: value,
    });
    console.log(form);
  };

  const createArticle = () => {
    if (!form.title) {
      alert("제목을 입력하세요");
    } else if (!form.content) {
      alert("내용을 입력하세요");
    } else {
      axios.post(
        `${process.env.REACT_APP_API_ROOT}/article`,
        {
          title: form.title,
          content: form.content,
          board_id: 1,
          category_id: 1,
          tag_list: []
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
    <>
      <Box
         sx={{
          marginTop: 2,
          width: '100%',
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <h2 sx={{ alignItems: 'center' }}>게시글 작성</h2>
        <FormControl>
          <form>
            <TextField 
              sx={{ marginTop: "10px" }}
              id="title"
              name="title"
              value={form.title}
              label="제목" 
              onChange={onChange}
              variant="outlined" />
            <TextField
              sx={{ display: "flex", width: "100%", marginTop: "10px" }} 
              id="content"
              name="content"
              value={form.content}
              label="내용"
              onChange={onChange}
              multiline rows={20}
            />

            <Container>
              <Button 
                sx={{ display: "inline", width: "50%", marginTop: "10px" }} 
                variant="contained"
                onClick={createArticle}>
                  작성
              </Button>
              <Link to='/boards/free'>
                <Button 
                  sx={{ display: "inline", width: "50%", margin: "10px 10px" }} 
                  variant="contained"
                  >
                    목록
                </Button>
              </Link>
            </Container>
          </form>
        </FormControl>
      </Box>
    </>
  )
}

export default NewFree;