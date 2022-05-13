import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

function PreferenceDetail() {
  const [preference, setPreference] = useState({});
  const { id } = useParams();
  const navigate = useNavigate();

  function init() {
    axios({
      url: `http://localhost:8081/api/preference/${id}`,
      // url: API_BASE_URL + `api/preference/${id}`,
      method: "GET",
    })
      .then((res) => {
        console.log(res.data.data);
        setPreference(res.data.data);
      })
      .catch((err) => console.log(err));
  }
  useEffect(init, []);
  function onDelete() {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      axios
        .delete(
          `http://localhost:8081/api/preference/${id}`
          // url: API_BASE_URL + `api/preference`,
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

      <input type="button" value="삭제하기" onClick={onDelete} />
      <hr />
      <input type="text" value="" placeholder="댓글을 입력하세요" />
      <input type="button" value="댓글달기" />
    </div>
  );
}
export default PreferenceDetail;
