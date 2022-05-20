import * as React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import styles from "./Tip.module.css";
import { useState, useEffect } from "react";
import axios from "axios";
import Grid from '@mui/material/Grid';
import { Link } from "@mui/material";

export default function TipList() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;

  const [questionList, setQuestionList] = useState({
    articles: [],
  });

  const getQuestionList = () => {
    axios({
      url: API_BASE_URL + '/article/list/3',
      method: "GET",
    }).then((res) => {
      setQuestionList({
        articles : res.data.data,
      })
    })
      .catch((err) => {
        console.log(err);
      })
  }

  useEffect(() => {
    getQuestionList();
  }, [])

  return (
    <div className={styles.container}>
      {questionList.articles.map((article) => (
        <Link href={`/boards/tip/${article.id}`} style={{textDecoration: "none"}}><Card key={article.id} sx={{ display: 'flex', height: "170px", marginBottom: "40px" }}>
          <Grid container spacing={2}>
            <Grid item xs={8}>
              <CardContent sx={{ flex: '1 0 auto', textAlign: "left", margin: "10px" }}>
                <Typography component="div" variant="h5" sx={{ fontWeight: "bold", marginBottom: "10px" }}>
                  {article.title}
                </Typography>
                <Typography variant="subtitle1" color="text.secondary" component="div">
                  {article.nickname}
                </Typography>
                <Typography variant="subtitle1" color="text.secondary" component="div">
                  👀{article.views}
                </Typography>
                <Typography variant="subtitle1" color="text.secondary" component="div">
                  ❤️{article.likes_count}
                </Typography>
              </CardContent>
            </Grid>
            <Grid item xs={4}>
              <CardMedia
                component="img"
                // sx={{ width: 151 }}
                image="https://images.pexels.com/photos/113850/pexels-photo-113850.jpeg?cs=srgb&dl=pexels-markus-spiske-113850.jpg&fm=jpg"
                // src={require("../images/sample.jpg")}
                alt="cover"
              />
            </Grid>
          </Grid>
        </Card></Link>
      ))}
    </div>
  );
}
