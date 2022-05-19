import React, { useEffect, useState } from "react";
import axios from "axios";
import { Card, CardContent, Typography } from "@mui/material";
import { Link } from "@mui/material";
// import queryString from "query-string";

function SearchList() {
  const [list, setList] = useState([]); //상태값 관리 //현재상태, setter함수
  const [keyword, setKeyword] = useState("");
  
  const createArticle = async (data) => {
    const response = await axios.get(
      `${process.env.REACT_APP_API_ROOT}/article`,
      {
        params: {
          //수정
          search: keyword,
          page: 1,
          size: 3,
        },
      }
    );
    setList(response.data.data);
    console.log(list);
  };

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    let search = params.get("search");
    setKeyword(search);
  });

  useEffect(() => {
    createArticle();
  }, [keyword]); //keyword의 값이 변경될 때, 렌더링 될 때 createArticle() 사용

  const onCheckEnter = (e) => {
    if (e.key === "Enter") {
      setKeyword(e.target.value);
      window.history.pushState("", "", `/search?search=${e.target.value}`);
    }
  };

  return (
    <div>
      <>
        <input
          type="search"
          placeholder="  검색어를 입력해주세요!"
          style={{
            width: "80%",
            borderRadius: "7px",
            border: "solid 2px #e5e8eb",
            fontSize: "20px",
            height: "35px",
            marginTop: "4rem",
            marginBottom: "2rem",
          }}
          onKeyPress={onCheckEnter}
        />

        {list.map((item) => (
          <Link href={`/boards/${item.board.id===1 ? 'free' : (item.board.id === 2 ? 'question' : 'tip')}/${item.id}`} style={{
            marginBottom: 0,
            color: "black",
            textDecoration: "none"
          }}>
          <Card
            variant="outlined"
            sx={{ minWidth: 275, textAlign: "left", marginLeft: "50px" }}
          >
            <CardContent>
              <Typography sx={{ fontSize: 14 }} gutterBottom>
                {item.board.name}ㆍ{item.category.name} {item.tag_list}
              </Typography>
              <Typography
                variant="h5"
                component="div"
                sx={{ fontWeight: "bold" }}
              >
                {item.title}
              </Typography>
              <Typography variant="body1">{item.content}</Typography> <br></br>
              <div>
                <Typography variant="body2" sx={{ float: "left" }}>
                  {item.nickname}
                </Typography>
                <Typography
                  variant="body2"
                  color="text.secondary"
                  sx={{ float: "left" }}
                  marginLeft="20px"
                >
                  |
                </Typography>
                <Typography
                  variant="body2"
                  color="text.secondary"
                  sx={{ float: "left" }}
                  marginLeft="20px"
                >
                  조회수 {item.views}
                </Typography>
                <Typography
                  variant="body2"
                  color="text.secondary"
                  sx={{ float: "left" }}
                  marginLeft="15px"
                >
                  좋아요 {item.likes_count}
                </Typography>
              </div>
            </CardContent>
          </Card>
          </Link>
        ))}
      </>
    </div>
  );
} //쉐도우

export default SearchList;
