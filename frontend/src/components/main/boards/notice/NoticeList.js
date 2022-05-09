import React, { useState } from "react";
import { Table, TableCell, TableHead, TableRow, Container, TextField, Button } from "@mui/material";

import NoticeContent from './NoticeContent';
import Pagination from './Pagination';

function NoticeList() {
  const [list, setList] = useState([]);

  return (
    <div>
      <>
        <Container sx={{ marginTop: "100px" }} fullWidth >
          <h2 style={{ marginLeft: "50px" }}>공지사항</h2>
            <Table style={{ display: "flex", marginTop: "2%" }}>
              <TableHead style={{ boxShadow: "0px 5px 10px rgb(207 206 206)" }}>
                <TableRow style={{ backgroundColor: "#C2E2F5" }}>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "100%",
                      textAlign: "left",
                    }}
                  >
                    번호
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "100%",
                      textAlign: "center",
                    }}
                  >
                    제목
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "100%",
                      textAlign: "center",
                    }}
                  >
                    작성일
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "100%",
                      textAlign: "center",
                    }}
                  >
                    조회수
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "100%",
                      textAlign: "center",
                    }}
                  ></TableCell>
                </TableRow>
              </TableHead>
              <NoticeContent list={list} />
            </Table>
            <Pagination setList={ setList }/>
          </Container>
        </>
        <>
          <Container style={{ marginTop: "100px" }}>
            <Button 
            variant="contained" 
            style={{ display:"flex", alignItems: "right" }} 
            href='/boards/new'>작성
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
