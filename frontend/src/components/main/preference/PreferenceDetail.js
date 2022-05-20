import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import DonutChart from "react-donut-chart";
import {
  Box,
  Button,
  List,
  ListItem,
  TextField,
  Typography,
} from "@mui/material";
import { Check } from "@mui/icons-material";

// 인증 관련
const API_BASE_URL = process.env.REACT_APP_API_ROOT;
const token = localStorage.getItem("token");
const headers = {
  Authorization: `Bearer ${token}`,
};
const myId = JSON.parse(localStorage.getItem("user"))
  ? JSON.parse(localStorage.getItem("user")).id
  : null;

function PreferenceDetail() {
  const [preference, setPreference] = useState({});
  const [choices, setChoices] = useState([]);
  const [donut, setDonut] = useState([{ label: "", value: 1 }]);
  const [myChoice, setMyChoice] = useState(0);
  const { id } = useParams();
  const navigate = useNavigate();

  // URL
  const init = async () => {
    const response = await axios.get(API_BASE_URL + `/preference/${id}`);
    setPreference(response.data.data);
    setChoices(response.data.data.preference_options_api_response_list);
    if (myChoice > 0) {
      const donuts = choices.map(({ description: label, votes: value }) => ({
        label,
        value,
      }));
      setDonut(donuts);
    }
    axios
      .get(API_BASE_URL + `/preference/${id}/find`, { headers: headers })
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
  }

  function onPoll(event) {
    setMyChoice(event.target.id);
    axios
      .post(
        API_BASE_URL + `/preference/${id}/${event.target.id}`,
        {},
        { headers: headers }
      )
      .then(() => {
        navigate(0);
      })
      .catch((err) => console.log(err));
  }
  useEffect(() => {
    init();
  }, [myChoice]);
  return (
    <>
      <Typography variant="h4" m={5}>
        <strong>{preference.description}</strong>
      </Typography>
      {myChoice > 0 ? <DonutChart data={donut} /> : <></>}
      <Typography variant="body1">아래의 보기를 선택하세요.</Typography>
      <List
        sx={{
          maxWidth: "50%",
          justifyContent: "center",
          // display: "flex",
          flexDirection: "column",
        }}
      >
        {choices.map((item, index) => {
          return (
            <ListItem
              id={item.id}
              key={index}
              onClick={onPoll}
              sx={{
                justifyContent: "center",
                marginY: 1,
                backgroundColor:
                  myChoice > 0
                    ? `rgb(${Math.floor(Math.random() * 256)},${Math.floor(
                        Math.random() * 256
                      )},${Math.floor(Math.random() * 256)})`
                    : null,
                width: item.percent > 0 ? `${item.percent}%` : "fit-content",
              }}
            >
              {item.description} {myChoice > 0 ? `(${item.votes} 명)` : null}
              {myChoice === item.id ? <Check /> : null}
            </ListItem>
          );
        })}
      </List>

      <Box component="div">
        <Button variant="text" onClick={() => navigate("/preference")}>
          목록으로
        </Button>
        {/* <Button
          variant="text"
          onClick={() => navigate(`/preference/${id}/modify`)}
          style={{ display: myId !== preference.user_id ? "none" : null }}
        >
          수정
        </Button> */}
        <Button
          onClick={onDelete}
          style={{ display: myId !== preference.user_id ? "none" : null }}
        >
          삭제
        </Button>
        {/* <Box component="form">
          <TextField type="text" placeholder="댓글을 입력하세요" />
          <Button type="submit" variant="contained">
            댓글 달기
          </Button>
        </Box> */}
      </Box>
    </>
  );
}
export default PreferenceDetail;
