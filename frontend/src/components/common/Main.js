import { useState, useEffect } from "react";
import axios from "axios";
import MainArticleList from "./MainArticleList";
import styles from "./Main.module.css";

import * as React from "react";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";

export default function Main() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  // const API_BASE_URL = "http://localhost:8081/";

  const [articleList, setArticleList] = useState({
    articles: [],
    boardId: 1,
  });
  const [questionList, setQuestionList] = useState({
    articles: [],
    boardId: 2,
  });
  const [tipList, setTipList] = useState({
    articles: [],
    boardId: 3,
  });

  const [hotList, setHotList] = useState({
    articles: [],
    boardId: 0,
  });

  const getLatestArticles = () => {
    axios({
      url: API_BASE_URL + "/article/latest/1",
      method: "GET",
    })
      .then((res) => {
        setArticleList({
          articles: res.data.data,
          boardId: 1,
        });
      })
      .catch((err) => {
        console.log(err);
      });
    axios({
      url: API_BASE_URL + "/article/latest/2",
      method: "GET",
    })
      .then((res) => {
        setQuestionList({
          articles: res.data.data,
          boardId: 2,
        });
      })
      .catch((err) => {
        console.log(err);
      });
    axios({
      url: API_BASE_URL + "/article/latest/3",
      method: "GET",
    })
      .then((res) => {
        setTipList({
          articles: res.data.data,
          boardId: 3,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const getHotArticles = () => {
    axios({
      url: API_BASE_URL + "/article/hot",
      method: "GET",
    })
      .then((res) => {
        setHotList({
          articles: res.data.data,
          boardId: 0,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const handleChange = (e) => {
    if (e.key === "Enter") {
      const AUUrl = `/search?search=${e.target.value}`;
      const endCodeAUUrl = encodeURI(AUUrl);
      window.location.href = endCodeAUUrl;
      console.log(e.target.value);
    }
  };

  // 최신 게시글
  useEffect(() => {
    getLatestArticles();
    getHotArticles();
  }, []);

  return (
    <div>
      <div className={styles.container}>
        <input
          type="text"
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
          onKeyPress={handleChange}
        />
        <Box sx={{ flexGrow: 1 }}>
          <Grid container spacing={3} style={{ justifyContent: "center" }}>
            <Grid item xs={10}>
              <MainArticleList articleList={hotList} />
            </Grid>
            <Grid item xs={9}>
              <h3>👑 실시간 게시글</h3>
            </Grid>
            <Grid item xs={5}>
              <MainArticleList articleList={articleList} />
            </Grid>
            <Grid item xs={5}>
              <MainArticleList articleList={questionList} />
            </Grid>
            <Grid item xs={5}>
              <MainArticleList articleList={tipList} />
            </Grid>
            <Grid item xs={5}></Grid>
          </Grid>
        </Box>
      </div>
    </div>
  );
}
