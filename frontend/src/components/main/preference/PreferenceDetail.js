import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DonutChart from 'react-donut-chart';
import { Box, Button, List, ListItem, TextField, Typography } from "@mui/material";


const API_BASE_URL = process.env.REACT_APP_API_ROOT;
const token = localStorage.getItem("token");
const headers = {
  Authorization: `Bearer ${token}`,
};

function PreferenceDetail() {
  // 인증 관련

  const [preference, setPreference] = useState({});
  const [choices, setChoices] = useState([]);
  const [donut, setDonut] = useState([{label:'', value:1}]);
  const [myChoice, setMyChoice] = useState(0);
  const { id } = useParams();
  const navigate = useNavigate();

  // URL
  const init = async () => {
    const response = await axios.get(API_BASE_URL + `/preference/${id}`)
    setPreference(response.data.data);
    setChoices(response.data.data.preference_options_api_response_list);
    if (myChoice > 0) {
      const donuts = choices.map(({text: label, votes:value})=>({label, value}));
      setDonut(donuts);
    }
    axios.get(API_BASE_URL + `/preference/${id}/find`, { headers: headers })
    .then((res) => setMyChoice(res.data.data))
    .catch((err) => console.log(err));
  };

  function onDelete() {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      axios
        .delete(API_BASE_URL + `/preference/${id}`)
        .then(() => {
          alert("삭제 완료!");
          navigate("/preference");
        })
        .catch((err) => console.log(err));
    }
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
      .then(() => {})
      .catch((err) => console.log(err));
  };
  useEffect(()=>{
    init();
  }, [myChoice]);
  return (
    <>
      <Typography variant='h4' m={5}>{preference.description}</Typography>
      {
        myChoice > 0 ?
        <DonutChart
          data={donut}
        /> : <></>
      }
      <List >
        {choices.map((item, index) => {
          return (
            <ListItem divider
              id={item.id} key={index}
              onClick={onPoll}
              sx={{
                backgroundColor: myChoice === item.id ? 'teal' : null,
              }}
            >
              {item.text} <Typography sx={{ mx:3 }}>{myChoice > 0 ? `${item.votes}명 투표`:null}</Typography>
            </ListItem>
          );
        })}
      </List>

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
