import axios from "axios";
import React, { useEffect, useState } from "react";
import { FormControl, TextField, Container, Box, Button } from "@mui/material";
import { useNavigate, useParams } from "react-router-dom";

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
  const { id } = useParams();

  const onChangeDesc = (event) => setDescription(event.target.value);
  const onChangeChoice = (event) => setChoice(event.target.value);

  function init() {
    axios({
      //   url: `http://localhost:8081/api/preference/${id}`,
      url: API_BASE_URL + `/preference/${id}`,
      method: "GET",
    })
      .then((res) => {
        console.log(res.data.data);
        setDescription(res.data.data.description);
        const arr = res.data.data.preference_options_api_response_list;
        const arr2 = [];
        for (let i = 0; i < arr.length; ++i) {
          arr2.push(arr[i].description);
        }
        setList(arr2);
      })
      .catch((err) => console.log(err));
  }
  useEffect(init, []);
  function addList() {
    if (choice === "") {
      alert("선택지 내용을 입력하세요");
      return;
    }
    setList((cur) => [...cur, choice]);
    setChoice("");
  }
  function onModify() {
    if (description === "") {
      alert("질문을 입력하세요");
    } else if (list.length < 2) {
      alert("선택지를 최소 2개 이상 입력하세요");
    } else {
      axios
        .patch(
          // `http://localhost:8081/api/preference`,
          API_BASE_URL + `/preference/${id}`,
          {
            description: description,
            option_list: list,
          },
          { headers: headers }
        )
        .then((res) => {
          console.log(res);
          alert("수정 완료!");
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
    // setUsers(users.filter(user => user.id !== id));
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
        <h2 sx={{ alignItems: "center" }}>선호도 조사 수정</h2>
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
                  <input
                    type="button"
                    value="삭제"
                    onClick={() => onRemove(index)}
                  />
                </li>
              ))}
            </ul>
            <Button variant="contained" onClick={resetChoice}>
              선택지 초기화
            </Button>
            <Button
              sx={{ display: "flex", width: "100%", marginTop: "10px" }}
              variant="contained"
              onClick={onModify}
            >
              수정
            </Button>
          </form>
        </FormControl>
      </Box>
    </Container>
  );
}

export default RegisterPreference;
