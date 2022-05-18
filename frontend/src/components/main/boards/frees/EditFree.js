import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from 'react-router-dom';
import { FormControl, TextField, Container, Box, Button } from "@mui/material";

function EditFree() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT
  let params = useParams();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

  const onChangeTitle = (event) => setTitle(event.target.value);
  const onChangeContent = (event) => setContent(event.target.value);

  // 인증 관련
  let token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  }

  // 페이지 이동
  const navigate = useNavigate();

  // 수정하려는 게시글 불러오기
  const getForm = async () => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/article/${params.id}`,
        {
          headers: headers,
        })
        .then(res => res.data);
        console.log(response.data);
        setTitle(response.data.title);
        setContent(response.data.content);
      } catch (err) {
        console.log(err);
    }
  };

  const editArticle = () => {
    if (!title) {
      alert("제목을 입력하세요");
    } else if (!content) {
      alert("내용을 입력하세요");
    } else {
      axios.patch(
        `${API_BASE_URL}/article/${params.id}`,
        {
          title: title,
          content: content,
          board_id: 1,
          category_id: 1,
          tag_list: []
        },
        {
          headers: headers,
        }
      )
      .then((res) => {
        console.log(res);
        navigate("/boards/free");
      })
      .catch((err) => console.log(err));
    }
  };

  useEffect(() => {
    getForm();
  }, [])

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
        <h2 sx={{ alignItems: 'center' }}>게시글 수정</h2>
        <FormControl>
          <form>
            <TextField 
              sx={{ marginTop: "10px" }}
              id="title"
              name="title"
              value={title}
              label="제목" 
              onChange={onChangeTitle}
              variant="outlined" />
            <TextField
              sx={{ display: "flex", width: "100%", marginTop: "10px" }} 
              id="content"
              name="content"
              value={content}
              label="내용"
              onChange={onChangeContent}
              multiline rows={20}
            />

            <Container>
              <Button 
                sx={{ display: "inline", width: "50%", marginTop: "10px" }} 
                variant="contained"
                onClick={editArticle}>
                  수정
              </Button>
              <Link to={`/boards/free/${params.id}`}>
                <Button 
                  sx={{ display: "inline", width: "50%", margin: "10px 10px" }} 
                  variant="contained"
                  >
                    취소
                </Button>
              </Link>
            </Container>
          </form>
        </FormControl>
      </Box>
    </>
  )
}

export default EditFree;