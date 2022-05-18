import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

function PreferenceDetail() {
  // 인증 관련
  let token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  const [preference, setPreference] = useState({});
  const [choices, setChoices] = useState([]);
  const [myChoice, setMyChoice] = useState(0);
  const { id } = useParams();
  const navigate = useNavigate();
  // URL
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;

  function init() {
    axios({
      //   url: `http://localhost:8081/api/preference/${id}`,
      url: API_BASE_URL + `/preference/${id}`,
      method: "GET",
    })
      .then((res) => {
        console.log(res.data.data);
        setPreference(res.data.data);
        setChoices(res.data.data.preference_options_api_response_list);
      })
      .catch((err) => console.log(err));
    axios
      .get(API_BASE_URL + `/preference/${id}/find`, { headers: headers })
      .then((res) => {
        console.log(res.data.data);
        setMyChoice(res.data.data);
      })
      .catch((err) => console.log(err));
  }
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
  }
  function onPoll(event) {
    const option_id = event.target["id"];
    setMyChoice(option_id);
    // console.log(id, option_id);
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
  }
  useEffect(init, [myChoice]);
  return (
    <div>
      <h1>detail</h1>
      작성자: <input type="text" value={preference.writer} readOnly />
      <br />
      제목: <input type="text" value={preference.description} readOnly />
      <br />
      {choices.map((item, index) => {
        return (
          <li
            id={item.preference_options_id}
            key={index}
            onClick={onPoll}
            style={{
              color: myChoice === item.preference_options_id ? "red" : null,
            }}
          >
            {index + 1}번:
            {item.description} ({item.percent.toFixed(2)}%)
          </li>
        );
      })}
      <Link to="/preference">목록으로</Link>
      <br />
      <Link to={`/preference/${id}/modify`}>수정</Link>
      {/* <input type="button" value="수정" onClick={onModify} /> */}
      <input type="button" value="삭제" onClick={onDelete} />
      <hr />
      <input type="text" placeholder="댓글을 입력하세요" />
      <input type="button" value="댓글달기" />
    </div>
  );
}
export default PreferenceDetail;
