import React, { useState, useEffect } from "react";
import axios from "axios";
import { TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper,
  Pagination,
  Button } from "@mui/material";

export default function FreeList() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT
  const [list, setList] = useState([]);
  const [page, setPage] = useState(1);

  const handleChange = (event, pageNumber) => {
    setPage(pageNumber);
  }

  // 더미 데이터
  const rows = [{
      title: "test",
      content: "test",
      created_at: "2022-05-10 10:00",
      views: "0",
      likes: "0"
  }];

  // 인증 관련
  let token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  // 자유게시판 목록 불러오기
  const getFreeList = async (page) => {
    try {
    const response = await axios.get(
      `${API_BASE_URL}/article/list/1?page=${page}`,
        {
          headers: headers,
        }
      );
      console.log(response);
      setList(response.data);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    handleChange();
    getFreeList();
  });

  return (
    <>
      <TableContainer 
        component={Paper}
        sx={{ margin: "50px" }}
      >
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>번호</TableCell>
              <TableCell align="right">제목</TableCell>
              <TableCell align="right">글쓴이</TableCell>
              <TableCell align="right">작성일</TableCell>
              <TableCell align="right">조회수</TableCell>
              <TableCell align="right">좋아요수</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {list.map((row) => (
              <TableRow
                key={row.name}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                <TableCell component="th" scope="row">
                  {row.name}
                </TableCell>
                <TableCell align="right">{row.title}</TableCell>
                <TableCell align="right">{row.content}</TableCell>
                <TableCell align="right">{row.created_at}</TableCell>
                <TableCell align="right">{row.views}</TableCell>
                <TableCell align="right">{row.likes}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Button 
        variant="contained" 
        sx={{
          display: "flex", margin: "5%", width: "100px"
        }}
        href='/boards/free/new'
        >
        글쓰기
      </Button>

      {/* 페이지네이션 */}
      <Pagination 
        count={11}
        page={page} 
        onClick={getFreeList}
        onChange={handleChange}
        sx={{ display: "flex", justifyContent: "center" }}
      />
    </>
  );
}
