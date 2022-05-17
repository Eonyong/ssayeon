import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import styles from "./Main.module.css";
import { Link } from "@mui/material";



export default function MainArticleList({ articleList }) {

  const boardId = articleList.boardId;
  console.log(boardId);
  console.log(articleList);

  // console.log(articleList.articles[0].board.id);

  return (
    <div>
      <TableContainer>
        <Table sx={{ minWidth: 200 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              {
                boardId === 1
                ? <TableCell style={{
                  paddingLeft: 10,
                  paddingBottom: 7
                }}><h3 className={styles.boardTitle}>자유 게시판</h3></TableCell>
                : ( boardId === 2 
                    ? <TableCell style={{
                      paddingLeft: 10,
                      paddingBottom: 7
                    }}><h3 className={styles.boardTitle}>질문 게시판</h3></TableCell>
                    : (boardId === 3
                      ? <TableCell style={{
                        paddingLeft: 10,
                        paddingBottom: 7
                      }}><h3 className={styles.boardTitle}>꿀팁 게시판</h3></TableCell>
                      : <TableCell style={{
                        paddingLeft: 10,
                        paddingBottom: 0
                      }}><h3>👑 실시간 인기글</h3></TableCell>
                      )
                  )
              }
              {
                boardId === 1
                ? <TableCell align="right" style={{
                  paddingBottom: 0,
                }}><Link href={'/boards/free'} style={{
                    marginBottom: 0,
                    color: "black",
                    textDecoration: "none"
                  }}>더보기 &gt;</Link></TableCell>
                : ( boardId === 2 
                    ? <TableCell align="right" style={{
                      paddingBottom: 0
                    }}><Link href={'/boards/question'} style={{
                      marginBottom: 0,
                      color: "black",
                      textDecoration: "none"
                    }}>더보기 &gt;</Link></TableCell>
                    : (boardId === 3
                      ? <TableCell align="right" style={{
                        paddingBottom: 0
                      }}><Link href={'/boards/tip'} style={{
                        marginBottom: 0,
                        color: "black",
                        textDecoration: "none"
                      }}>더보기 &gt;</Link></TableCell>
                      : <TableCell></TableCell>
                      )
                  )
              }

            </TableRow>
          </TableHead>
          <TableBody>
            {articleList.articles.map((article) => (
              <TableRow
                key={article.id}
                sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              >
                {
                  article.board.id === 1
                  ? <TableCell component="th" scope="row" style={{padding: 12}}>
                      <Link href={`/boards/free/${article.id}`} style={{
                        marginBottom: 0,
                        color: "black",
                        textDecoration: "none"
                      }}>{article.title}</Link>
                    </TableCell>
                    : ( article.board.id === 2 
                        ? <TableCell component="th" scope="row" style={{padding: 12}}>
                            <Link href={`/boards/question/${article.id}`} style={{
                              marginBottom: 0,
                              color: "black",
                              textDecoration: "none"
                            }}>{article.title}</Link>
                          </TableCell>
                        : <TableCell component="th" scope="row" style={{padding: 12}}>
                            <Link href={`/boards/tip/${article.id}`} style={{
                              marginBottom: 0,
                              color: "black",
                              textDecoration: "none"
                            }}>{article.title}</Link>
                          </TableCell>
                      )
                }
                <TableCell align="right" style={{padding: 12}}>❤️ {article.likes_count}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  )
}