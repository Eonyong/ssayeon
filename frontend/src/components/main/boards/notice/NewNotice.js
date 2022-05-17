import axios from "axios";
import React, { useState } from "react";
import { FormControl, TextField, Container, Box, Button } from "@mui/material";

function NewNotice() {
  // 인증 관련
  let token = sessionStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  }

  // 게시글 작성 form
  const [form, setForm] = useState({
    title: "",
    description: "",
  });

  const onChange = (event) => {
    const { name, value } = event.target;
    setForm({
      ...form,
      [name]: value,
    });
    console.log(form);
  };

  const createNotice = () => {
    if (!form.title) {
      alert("제목을 입력하세요");
    } else if (!form.description) {
      alert("내용을 입력하세요");
    } else {
      axios.post(
        `${process.env.REACT_APP_API_ROOT}/notification`,
        {
          title: form.title,
          description: form.description,
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
    <Container>
      <Box
         sx={{
          marginTop: 2,
          width: 'auto',
          display: "flex",
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <h2 sx={{ alignItems: 'center' }}>게시글 작성</h2>
        <FormControl>
          <form>
            <TextField 
              sx={{ display: "flex", width: "100%", marginTop: "10px" }}
              id="title"
              name="title"
              value={form.title}
              label="제목" 
              onChange={onChange}
              variant="outlined" />
            <TextField
              sx={{ display: "flex", width: "100%", marginTop: "10px" }} 
              id="description"
              name="description"
              value={form.description}
              label="내용"
              onChange={onChange}
              multiline rows={20}
            />
            <Button 
              sx={{ display: "flex", width: "100%", marginTop: "10px" }} 
              variant="contained"
              onClick={createNotice}>
            </Button>
          </form>
        </FormControl>
      </Box>
    </Container>
  )
}

export default NewNotice;