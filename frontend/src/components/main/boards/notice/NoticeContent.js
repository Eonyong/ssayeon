import React from 'react'
import { TableBody, TableCell, TableRow } from "@mui/material";
import { Link } from "react-router-dom"

function Content({ list }) {
  return (
    <>
      <TableBody>
        {list.map((item, index) =>
          <TableRow key={index}>
          <TableCell style={{ textAlign: 'center' }}>{item.id}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.title}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.views}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.author.name.slice(2,5)}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.created_at.slice(0,10)}</TableCell>
          <TableCell style={{ textAlign: 'center' }}><Link to={`/notice/${item.id}`}>상세보기</Link></TableCell>
          </TableRow>)
        }
      </TableBody>
    </>
  )
}

export default Content;
