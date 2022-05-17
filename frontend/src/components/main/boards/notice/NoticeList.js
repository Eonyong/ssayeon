import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table, TableCell, TableHead, TableRow, 
  Container, TextField, Button } from "@mui/material";

import NoticeContent from './NoticeContent';
import Pagination from './Pagination';

function NoticeList() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT

  const [list, setList] = useState([]);

  // 인증 관련
  let token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  // 자유게시판 목록 불러오기
  const getNoticeList = async (page) => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/notification`,
        {
          params: {page: page},
          headers: headers,
        })
        .then(res => res.data);
        setList(response.data);
      } catch (err) {
        console.log(err);
      }
  };

  useEffect(() => {
    getNoticeList();
  }, []);

  return (
    <div>
      <>
        <Container sx={{ marginTop: "100px" }}>
          <h2 style={{ marginLeft: "50px" }}>공지사항</h2>
            <Table style={{ marginTop: "2%" }}>
              <TableHead style={{ boxShadow: "0px 5px 10px rgb(207 206 206)" }}>
                <TableRow style={{ backgroundColor: "#C2E2F5" }}>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "80%",
                      textAlign: "center",
                    }}
                  >
                   제목
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "10%",
                      textAlign: "center",
                    }}
                  >
                    작성일
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "10%",
                      textAlign: "center",
                    }}
                  >
                    조회수
                  </TableCell>
                </TableRow>
              </TableHead>
              <NoticeContent list={list} />
            </Table>
            <Pagination setList={ setList }/>
          </Container>
        </>
        <>
          <Container sx={{ display:"flex", justifyContent: "right", marginTop: "100px" }}>
            <Button 
            variant="contained" 
            sx={{ width: "100px" }} 
            href='/boards/notice/new'>작성
            </Button>
          </Container>
          <Container style={{ marginTop: "15px" }}>
           <TextField id="outlined-basic" size="small" label="검색어를 입력해주세요" variant="outlined" />
           <Button variant="outlined" style={{ marginLeft: "10px" }}>검색</Button>
          </Container>
        </>
    </div>
  );
}

export default NoticeList;
