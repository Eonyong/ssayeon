import React, { useEffect, useState } from "react";
import {
  Table,
  TableCell,
  TableHead,
  TableRow,
  Container,
  TextField,
  Button,
  TableBody,
  ListSubheader,
  Card,
  CardContent,
} from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const API_BASE_URL = process.env.REACT_APP_API_ROOT;
function PreferenceList() {
  const [list, setList] = useState([]);
  const [query, setQuery] = useState("");
  const navigate = useNavigate();
  function func() {
    axios({
      // url: `http://localhost:8081/api/preference`,
      url: API_BASE_URL + `/preference`,
      method: "GET",
    })
      .then((res) => {
        console.log(res.data.data);
        setList(res.data.data);
      })
      .catch((err) => console.log(err));
  }
  function search() {
    axios
      .get(API_BASE_URL + `/preference/search?query=${query}`)
      .then((res) => {
        setList(res.data.data);
      })
      .catch((err) => console.log(err));
  }
  useEffect(func, []);
  return (
    <div>
      <>
        <Container sx={{ marginTop: "100px" }} fullWidth>
          <h2 style={{ marginLeft: "50px" }}>선호도조사</h2>
          <Table style={{ marginTop: "2%" }}>
            <TableHead style={{ boxShadow: "0px 5px 10px rgb(207 206 206)" }}>
              <TableRow style={{ backgroundColor: "#C2E2F5" }}>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    width: "60%",
                    textAlign: "center",
                  }}
                >
                  질문
                </TableCell>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    width: "20%",
                    textAlign: "center",
                  }}
                >
                  작성자
                </TableCell>
                <TableCell
                  style={{
                    fontSize: "1rem",
                    width: "20%",
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
                list.map((item, idx) => 
                (
                  <TableRow>
                    <TableCell
                      style={{
                        fontSize: "1rem",
                        textAlign: "center",
                      }} onClick={()=>{navigate(`/preference/${item.preference_id}`)}}
                    >
                      {item.description}
                    </TableCell>
                    <TableCell
                      style={{
                        fontSize: "1rem",
                        textAlign: "center",
                      }}
                    >
                      {item.writer}
                    </TableCell>
                    <TableCell
                      style={{
                        fontSize: "1rem",
                        textAlign: "center",
                      }}
                    >
                      {item.updated_at.substring(0, 10)}
                    </TableCell>
                  </TableRow>
                )
                ))}
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
            onChange={(e) => setQuery(e.target.value)}
          />
          <Button
            variant="outlined"
            style={{ marginLeft: "10px" }}
            onClick={search}
          >
            검색
          </Button>
        </Container>
      </>
    </div>
  );
}

export default PreferenceList;
