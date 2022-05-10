import React from "react";
import { Link } from "react-router-dom";
import { Button, Container, Table, TableHead, TableBody, TableRow, TableCell } from "@mui/material";

function NoticeDetail() {
  return (
    <div>
      <>
        <Container style={{ marginTop: "100px" }}>
          <h2 style={{ marginLeft: "50px" }}>공지사항</h2>
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
                  <div>제목</div>
                  <div>작성일</div>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow style={{ height: "30rem" }}>
                <TableCell style={{ fontSize: "2rem", textAlign: "center" }}>
                  내용
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
          
          <Table>
              <>
                <TableRow>
                  <TableCell style={{ display: "flex" }}>
                    <div>이전글</div>
                    <div style={{ margin: "auto" }}>
                      <Link
                        to=""
                        style={{ color: "inherit" }}
                      >
                        이전글 제목
                      </Link>
                      </div>
                    </TableCell>
                  </TableRow>
              </>
              <>
                <TableRow>
                  <TableCell style={{ display: "flex" }}>
                    <div>다음글</div>
                    <div style={{ margin: "auto" }}>
                      <Link
                        to=""
                        style={{ color: "inherit" }}
                      >
                        다음글 제목
                      </Link>
                    </div>
                  </TableCell>
                </TableRow>
              </>
          </Table>

          <Button style={{ 
            display: "flex", 
            marginTop: "15px", 
            justifyContent: "right" }}
            variant="outlined">
              목록으로
          </Button>
        </Container>
      </>
    </div>
  );
}

export default NoticeDetail;
