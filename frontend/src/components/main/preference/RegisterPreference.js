import axios from "axios";
import React, { useState } from "react";
import { FormControl, TextField, Container, Box, Button, List, ListItem, Input, IconButton, Grid } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { Add, AddBox, IndeterminateCheckBox, PlusOne } from "@mui/icons-material";

function RegisterPreference() {
  // 인증 관련
  let token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  // 페이지 이동
  const navigate = useNavigate();

  // URL
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;

  // state
  const [choice, setChoice] = useState("");
  const [list, setList] = useState([]);
  const [description, setDescription] = useState("");

  const onChangeDesc = (event) => setDescription(event.target.value);
  const onChangeChoice = (event) => setChoice(event.target.value);

  function addList() {
    if (choice === "") {
      alert("선택지 내용을 입력하세요");
      return;
    }
    setList((cur) => [...cur, choice]);
    setChoice("");
  }
  function onRegister() {
    if (description === "") {
      alert("질문을 입력하세요");
    } else if (list.length < 2) {
      alert("선택지를 최소 2개 이상 입력하세요");
    } else {
      axios
        .post(
          // `http://localhost:8081/api/preference`,
          API_BASE_URL + `/preference`,
          {
            description: description,
            option_list: list,
          },
          { headers: headers }
        )
        .then((res) => {
          console.log(res);
          alert("작성 완료!");
          navigate("/preference");
        })
        .catch((err) => console.log(err));
    }
  }
  const onRemove = (index) => {
    const arr = [];
    for (let i = 0, len = list.length; i < len; ++i) {
      if (i !== index) arr.push(list[i]);
    }
    setList(arr);
  };
  function resetChoice() {
    setList([]);
  }
  return (
    <Container sx={{ display: "flex" }}>
      <Box
        sx={{
          marginTop: 2,
          width: "auto",
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <h2 sx={{ alignItems: "center" }}>선호도 조사 작성</h2>
        <Box component='form'>
          <TextField
            sx={{ display: "flex", width: "100%", marginTop: "10px" }}
            id="title"
            name="title"
            value={description}
            label="질문"
            onChange={onChangeDesc}
            variant="outlined"
          />
            <Box component="div" mt={3}>
              <TextField
                type="text"
                value={choice}
                placeholder="선택지 입력"
                onChange={onChangeChoice}
              />
              <IconButton onClick={addList} color="error" size="large" >
                <AddBox color="error" fontSize="large" />
              </IconButton>
            </Box>
            <List>
              {list.map((item, index) => (
                <ListItem key={index} sx={{ justifyContent: 'space-between' }}>
                  {item}
                  <IconButton
                    color="primary" sx={{ justifySelf: 'end' }}
                    onClick={() => onRemove(index)}
                  >
                    <IndeterminateCheckBox />
                  </IconButton>
                </ListItem>
              ))}
            </List>
            <Grid columns={2} spacing={3}>
              <Button variant="contained" color="error" onClick={resetChoice} sx={{ marginX:1 }}>
                선택지 초기화
              </Button>
              <Button
                variant="contained"
                onClick={onRegister} sx={{ marginX:1 }}
              >
                작성
              </Button>
            </Grid>
          </Box>
      </Box>
    </Container>
  );
}

export default RegisterPreference;
