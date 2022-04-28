import React from "react";
import { Table, TableCell, TableHead, TableRow, Container, TextField, Button } from "@mui/material";

function NoticeList() {
  return (
    <div>
      <>
        <Container style={{ marginTop: "100px" }}>
          <h2 style={{ marginLeft: "50px" }}>공지사항</h2>
            <Table style={{ marginTop: "2%" }}>
              <TableHead style={{ boxShadow: "0px 5px 10px rgb(207 206 206)" }}>
                <TableRow style={{ backgroundColor: "#C2E2F5" }}>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "10%",
                      textAlign: "left",
                    }}
                  >
                    번호
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "60%",
                      textAlign: "left",
                    }}
                  >
                    제목
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "10%",
                      textAlign: "left",
                    }}
                  >
                    글쓴이
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "10%",
                      textAlign: "left",
                    }}
                  >
                    작성일
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "10%",
                      textAlign: "left",
                    }}
                  >
                    조회수
                  </TableCell>
                  <TableCell
                    style={{
                      fontSize: "1rem",
                      width: "10%",
                      textAlign: "left",
                    }}
                  ></TableCell>
                </TableRow>
              </TableHead>
            </Table>
          </Container>
        </>
        <>
          <Container style={{ marginTop: "100px" }}>
           <TextField id="outlined-basic" size="small" label="검색어를 입력해주세요" variant="outlined" />
           <Button variant="outlined" style={{ marginLeft: "10px" }}>검색</Button>
          </Container>
        </>
    </div>
  );
}

export default NoticeList;
