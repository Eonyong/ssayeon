import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { Button, Container, Table, TableHead, TableBody, TableRow, TableCell, TextField,
Card, CardContent, Typography } from "@mui/material";

function TipDetail() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT
  let params = useParams();
  const [detail, setDetail] = useState([]);
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState("");

  const onChangeNewComment = (event) => setNewComment(event.target.value);

  // 인증 관련
  let token = localStorage.getItem("token");
  let currentUserInfo = JSON.parse(localStorage.getItem("user"))
  let currentUser = currentUserInfo.name

  const headers = {
    Authorization: `Bearer ${token}`,
  };

  // 페이지 이동
  const navigate = useNavigate();

  // 게시글 상세 내용 불러오기
  const getTipDetail = async () => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/article/${params.id}`,
        {
          headers: headers,
        })
        .then(res => res.data);
        setDetail(response.data);
      } catch (err) {
        console.log(err);
    }
  };

  // 댓글 불러오기
  const getComments = async () => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/article/${params.id}/comments/list`,
        {
          headers: headers,
        })
        .then(res => res.data);
        setComments(response.data);
        console.log(response.data);
      } catch (err) {
        console.log(err);
    }
  };

  // 댓글 작성하기
  const addNewComment = async () => {
    if (!newComment) {
      alert("댓글을 입력하세요")
    } else {
      axios.post(
        `${API_BASE_URL}/article/${params.id}/comments/`,
        {
          description: newComment,
        },
        {
          headers: headers,
        }
      )
      .then((res) => {
        console.log(res);
        navigate(0);
      })
      .catch((err) => console.log(err));
    }
  };

  useEffect(() => {
    getTipDetail();
    getComments();
  }, []);

  return (
    <div>
      <>
        <Container style={{ marginTop: "100px" }}>
          <h2 style={{ marginLeft: "50px" }}>자유게시판</h2>
          <Table style={{ marginTop: "2%" }}>
            <TableHead>
              <TableRow
                style={{
                  backgroundColor: "#C2E2F5",
                  boxShadow: "0px 5px 10px rgb(207 206 206)",
                }}
              >
                <TableCell
                  style={{
                    display: "grid",
                    gridTemplateColumns: "10% 80% 10%",
                  }}
                >
                  <div></div>
                  <div>{detail.title}</div>
                  <div>{detail.nickname}</div>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow style={{ height: "30rem" }}>
                <TableCell style={{ fontSize: "1rem", textAlign: "start" }}>
                  {detail.content}
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>

          {/* 수정 버튼 */}
          {currentUser === detail.nickname ? (
            <Button style={{ 
              display: "flex", 
              marginTop: "15px", 
              justifyContent: "center",
              width: "10%" }}
              variant="outlined"
              href={`/boards/free/${params.id}/edit`}>
              수정
            </Button>
          ) : null }

          {/* 댓글 작성 */}
          <Container style={{ marginTop: "100px" }}>
            <form>
              <TextField
                id="outlined-basic"
                size="small"
                width="80%"
                label="댓글을 입력하세요"
                value={newComment}
                onChange={onChangeNewComment}
                variant="outlined"
              />
              <Button 
                sx={{ marginLeft: "10px" }}
                variant="outlined"  
                onClick={addNewComment}
              >
                작성
              </Button>
            </form>
          </Container>

          {/* 댓글 */}
          {comments.map((comment) => (
            <Card sx={{ minWidth: 275 }}>
              <CardContent>
                <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                  {comment.nickname}
                </Typography>
                <Typography sx={{ fontSize: 16 }}>
                  {comment.description}
                </Typography>
              </CardContent>
            </Card>
          ))}
          
          <Button style={{ 
            display: "flex", 
            marginTop: "15px", 
            justifyContent: "center",
            width: "10%" }}
            variant="outlined"
            href="/boards/free">
              목록으로
          </Button>
        </Container>
      </>
    </div>
  );
}

export default TipDetail;
