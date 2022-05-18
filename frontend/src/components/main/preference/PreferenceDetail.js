import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { LeafPoll } from 'react-leaf-polls';
import "react-leaf-polls/dist/index.css";
import { Box, Button, TextField, Typography } from "@mui/material";


const API_BASE_URL = process.env.REACT_APP_API_ROOT;
const token = localStorage.getItem("token");
const headers = {
  Authorization: `Bearer ${token}`,
};

function PreferenceDetail() {
  // 인증 관련

  const [preference, setPreference] = useState({});
  const [choices, setChoices] = useState([]);
  const [myChoice, setMyChoice] = useState(0);
  const { id } = useParams();
  const navigate = useNavigate();

  const themeData = {
    textColor: '#19181f',
    mainColor: '#00B87B',
    backgroundColor: 'white',
    alignment: 'center',
    leftColor: '#00B87B',
    rightColor: '#FF2E00'
  };

  // URL
  function init() {
    axios({
      url: API_BASE_URL + `/preference/${id}`,
      method: "GET",
    })
    .then((res) => {
      setPreference(res.data.data);
      var items = [];
      var rpls = res.data.data.preference_options_api_response_list
      for (let idx = 0; idx < rpls.length; idx++) {
        let item = {'id':idx, 'text': rpls[idx]['text'], 'votes': rpls[idx]['votes']};
        items.push(item);
      }
      setChoices(items);
      
    })
    .catch((err) => console.log(err))

    axios
      .get(API_BASE_URL + `/preference/${id}/find`, { headers: headers })
      .then((res) => {
        setMyChoice(res.data.data);
      })
      .catch((err) => console.log(err));
  };
  function onDelete() {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      axios
        .delete(API_BASE_URL + `/preference/${id}`)
        .then((res) => {
          alert("삭제 완료!");
          navigate("/preference");
        })
        .catch((err) => console.log(err));
    }
  };

  function vote(item, results) {
    console.log('voted', item, results);
  };

  function onPoll(event) {
    const option_id = event.target["id"];
    setMyChoice(option_id);
    axios
      .post(
        API_BASE_URL + `/preference/${id}/${option_id}`,
        {},
        { headers: headers }
      )
      .then((res) => {
        alert("투표 완료!");
        // navigate("/preference");
      })
      .catch((err) => console.log(err));
  };
  useEffect(init, [myChoice]);
  return (
    <>
      <Typography variant='h4' m={5}>작성자: {preference.writer}</Typography>
      <LeafPoll
        type="multiple"
        question={preference.description}
        results={choices}
        theme={themeData}
      />
      <Box component='div'>
        <Button variant="text" onClick={()=>navigate('/preference')}>
          목록으로
        </Button>
        <Button variant="text" onClick={()=>navigate(`/preference/${id}/modify`)}>
          수정
        </Button>
        <Button onClick={onDelete}>
          삭제
        </Button>
        <Box component="form">
          <TextField
            type="text" placeholder="댓글을 입력하세요"
          />
          <Button type="submit" variant='contained'>
            댓글 달기
          </Button>
        </Box>
      </Box>
    </>
  );
}
export default PreferenceDetail;
