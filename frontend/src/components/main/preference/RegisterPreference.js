import axios from "axios";
import React, { useState } from "react";
import { FormControl, TextField, Container, Box, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

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
        <FormControl>
          <form>
            <TextField
              sx={{ display: "flex", width: "100%", marginTop: "10px" }}
              id="title"
              name="title"
              value={description}
              label="질문"
              onChange={onChangeDesc}
              variant="outlined"
            />
            {/* <TextField
              sx={{ display: "flex", width: "100%", marginTop: "10px" }}
              id="description"
              name="description"
              label="내용"
              onChange={onChange}
              multiline
              rows={20}
            /> */}
            <input
              type="text"
              value={choice}
              placeholder="선택지 입력"
              onChange={onChangeChoice}
            />
            <input type="button" value="추가" onClick={addList} />
            <hr />
            <ul>
              {list.map((item, index) => (
                <li key={index}>
                  {item}
                  {/* <Button onClick={console.log("삭제")}>삭제</Button> */}
                  <input type="button" value="삭제" />
                </li>
              ))}
            </ul>
            <Button variant="contained" onClick={resetChoice}>
              초기화
            </Button>
            <Button
              sx={{ display: "flex", width: "100%", marginTop: "10px" }}
              variant="contained"
              onClick={onRegister}
            >
              작성
            </Button>
          </form>
        </FormControl>
      </Box>
    </Container>
  );
}

export default RegisterPreference;
