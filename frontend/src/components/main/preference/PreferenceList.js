import React, { useState } from "react";
import {
  Table,
  TableCell,
  TableHead,
  TableRow,
  Container,
  TextField,
  Button,
  TableBody,
} from "@mui/material";
import axios from "axios";

// import NoticeContent from './NoticeContent';
// import Pagination from './Pagination';

function PreferenceList() {
  const [list, setList] = useState([]);
  function func() {
    axios
      .get(`localhost:8001/api/preference`)
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
  }
  // func();
  return (
    <div>
      <>
        <Container sx={{ marginTop: "100px" }} fullWidth>
          <h2 style={{ marginLeft: "50px" }}>선호도조사</h2>
          <Table style={{ /* display: "flex", */ marginTop: "2%" }}>
            <TableHead style={{ boxShadow: "0px 5px 10px rgb(207 206 206)" }}>
              <TableRow style={{ backgroundColor: "#C2E2F5" }}>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    width: "70%",
                    textAlign: "center",
                  }}
                >
                  투표제목
                </TableCell>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    width: "30%",
                    textAlign: "center",
                  }}
                >
                  작성일
                </TableCell>
              </TableRow>
            </TableHead>
            {/* <NoticeContent list={list} /> */}
            <TableBody>
              {list.length === 0 ? (
                <TableRow>
                  <TableCell
                    colSpan="2"
                    style={{
                      fontSize: "1rem",
                      textAlign: "center",
                    }}
                  >
                    등록된 게시물이 없습니다
                  </TableCell>
                </TableRow>
              ) : (
                <TableRow>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      textAlign: "center",
                    }}
                  >
                    <h1>"게시글이 있습니다"</h1>
                  </TableCell>
                </TableRow>
              )}
              <TableRow>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    textAlign: "center",
                  }}
                >
                  제목임ㅋ
                </TableCell>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    textAlign: "center",
                  }}
                >
                  날짜임ㅎ
                </TableCell>
              </TableRow>
              <TableRow>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    textAlign: "center",
                  }}
                >
                  제목임ㅋㅋ
                </TableCell>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    textAlign: "center",
                  }}
                >
                  날짜임ㅎㅎ
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
          {/* <Pagination setList={setList} /> */}
        </Container>
      </>
      <>
        <Container style={{ marginTop: "100px" }}>
          <Button
            variant="contained"
            style={{ display: "flex", alignItems: "right" }}
            href="/preference/new"
          >
            작성
          </Button>
        </Container>
        <Container style={{ marginTop: "15px" }}>
          <TextField
            id="outlined-basic"
            size="small"
            label="검색어를 입력해주세요"
            variant="outlined"
          />
          <Button variant="outlined" style={{ marginLeft: "10px" }}>
            검색
          </Button>
        </Container>
      </>
    </div>
  );
}

export default PreferenceList;
