import React, { useState, useEffect } from "react";
import axios from "axios";
import { TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper,
  Pagination,
  Button,
  Link, 
  Container,
  TextField} from "@mui/material";

export default function QnaList() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT
  const [list, setList] = useState([]);
  const [page, setPage] = useState(1);
  const [totalPage, setTotalPage] = useState(1);

  const handleChange = (event, pageNumber) => {
    console.log(pageNumber);
    setPage(pageNumber);
  }

  // 인증 관련
  let token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  // 질문게시판 목록 불러오기
  const getFreeList = async (page) => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/article/list/2`,
        {
          params: {page: page},
          headers: headers,
        })
        .then(res => res.data);
        setTotalPage(response.pagination.total_pages);
        setList(response.data);
      } catch (err) {
        console.log(err);
      }
  };

  useEffect(() => {
    handleChange();
    getFreeList();
  }, [page]);

  return (
    <>
      <TableContainer 
        component={Paper}
        sx={{ margin: "50px" }}
      >
        <Table sx={{ minWidth: 650, alignContent:'center' }} aria-label="simple table">
          <TableHead style={{ boxShadow: "0px 5px 10px rgb(207 206 206)" }}>
            <TableRow style={{ backgroundColor: "#C2E2F5" }}>
              <TableCell align='center'>제목</TableCell>
              <TableCell align='center'>글쓴이</TableCell>
              <TableCell align='center'>작성일</TableCell>
              <TableCell align='center'>조회수</TableCell>
              <TableCell align='center'>좋아요수</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {
            list.map((row, idx) => {
              var date = new Date(row.created_at);
              return (
              <TableRow
                key={row.id}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell align="center" ><Link href={`/boards/free/${row.id}`}>{row.title}</Link></TableCell>
                <TableCell align="center">{row.nickname}</TableCell>
                <TableCell align="center">
                  {`${date.getMonth() + 1}월 ${date.getDate() + 1}일 ${date.getHours()}시${date.getMinutes()}분`}
                </TableCell>
                <TableCell align="center">{row.views}</TableCell>
                <TableCell align="center">{row.likes_count}</TableCell>
              </TableRow>
              );})}
          </TableBody>
        </Table>
      </TableContainer>
      
      <Container sx={{ display:"flex", justifyContent: "right", marginTop: "100px" }}>
        <Button 
          variant="contained"
          href='/boards/free/new' placeholder="글쓰기"
          >
          글쓰기
        </Button>
      </Container>
      <Container sx={{ marginBottom:'2rem' }}>
        <TextField id="outlined-basic" size="small" label="검색어를 입력해주세요" variant="outlined" />
        <Button variant="outlined" style={{ marginLeft: "10px" }}>검색</Button>
      </Container>

      {/* 페이지네이션 */}
      <Pagination 
        count={totalPage}
        page={page} defaultValue={page}
        onClick={getFreeList}
        onChange={(_e, res) =>{console.log(res)}}
        sx={{ display: "flex", justifyContent: "center" }}
      />
    </>
  );
}
