import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function PreferenceDetail() {
  const [preference, setPreference] = useState({});
  const [choices, setChoices] = useState([]);
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
        // console.log(res.data.data.preference_options_api_response_list);
        // setChoices(preference.preference_options_api_response_list);
        // console.log(choices);
        // console.log(preference.preference_options_api_response_list);
      })
      .catch((err) => console.log(err));
  }
  useEffect(init, []);
  function onDelete() {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      axios
        .delete(
          //   `http://localhost:8081/api/preference/${id}`
          API_BASE_URL + `/preference`
        )
        .then((res) => {
          alert("삭제 완료!");
          navigate("/preference");
        })
        .catch((err) => console.log(err));
    }
  }
  return (
    <div>
      <h1>detail</h1>
      작성자: <input type="text" value={preference.writer} readOnly />
      <br />
      제목: <input type="text" value={preference.description} readOnly />
      <br />
      {choices.map((item) => (
        <li>{item.description}</li>
      ))}
      <input type="button" value="게시글 삭제" onClick={onDelete} />
      <hr />
      <input type="text" placeholder="댓글을 입력하세요" />
      <input type="button" value="댓글달기" />
    </div>
  );
}
export default PreferenceDetail;
