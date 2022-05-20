import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import { Button, Container, Table, TableHead, TableBody, TableRow, TableCell } from "@mui/material";

function NoticeDetail() {
  const API_BASE_URL = process.env.REACT_APP_API_ROOT
  let params = useParams();
  const [detail, setDetail] = useState([]);
  const [previousNotice, setPreviousNotice] = useState();
  const [previousId, setPreviousId] = useState(null);
  const [nextNotice, setNextNotice] = useState();
  const [nextId, setNextId] = useState(null);

  // 인증 관련
  let token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
 
  // 게시글 상세 내용 불러오기
  const getNoticeDetail = async () => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/notification/${params.id}`,
        {
          headers: headers,
        })
        .then(res => res.data);
        setDetail(response.data);
      }  
    catch (err) {
        console.log(err);
    }
  };

  // 이전 글, 다음 글
  const getPreviousNotice = async () => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/notification/${parseInt(params.id) - parseInt("1")}`,
        {
          headers: headers,
        })
        .then(res => res.data);
        setPreviousNotice(response.data.title);
        setPreviousId(response.data.id);
      }  
    catch (err) {
        console.log(err);
    }
  };

  const getNextNotice = async () => {
    try {
      const response = await axios.get(
        `${API_BASE_URL}/notification/${parseInt(params.id) + parseInt("1")}`,
        {
          headers: headers,
        })
        .then(res => res.data);
        setNextNotice(response.data.title);
        setNextId(response.data.id);
      }  
    catch (err) {
        console.log(err);
    }
  };

  useEffect(() => {
    getNoticeDetail();
    getPreviousNotice();
    getNextNotice();
  }, []);

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
                    gridTemplateColumns: "80% 20%",
                  }}
                >
                  <div>{detail.title}</div>
                  <div>{detail.created_at}</div>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              <TableRow style={{ height: "30rem" }}>
                <TableCell style={{ fontSize: "rem" }}>
                  {detail.description}
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
          
          <Table>
              <>
                {previousId === null ? null : (
                  <TableRow>
                    <TableCell style={{ display: "flex" }}>
                      <div>이전글</div>
                      <div style={{ margin: "auto" }}>
                        <Link
                          to={`/boards/notice/${previousId}`}
                          onClick={() => this.forceUpdate}
                          style={{ color: "inherit" }}
                        >
                          {previousNotice}
                        </Link>
                      </div>
                    </TableCell>
                  </TableRow>
                )}
              </>
              <>
                {nextId === null ? null : (
                  <TableRow>
                    <TableCell style={{ display: "flex" }}>
                      <div>다음글</div>
                      <div style={{ margin: "auto" }}>
                        <Link
                          to={`/boards/notice/${nextId}`}
                          onClick={() => this.forceUpdate}
                          style={{ color: "inherit" }}
                        >
                          {nextNotice}
                        </Link>
                      </div>
                    </TableCell>
                  </TableRow>
                )}
              </>
          </Table>

          <Button style={{ 
            display: "flex", 
            marginTop: "15px", 
            minWidth: "10%",
            justifyContent: "center" }}
            variant="outlined"
            href="/boards/notice">
              목록으로
          </Button>
        </Container>
      </>
    </div>
  );
}

export default NoticeDetail;
