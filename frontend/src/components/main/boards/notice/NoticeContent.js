import React from 'react'
import { TableBody, TableCell, TableRow } from "@mui/material";

function NoticeContent({ list }) {
  return (
    <>
      <TableBody>
        {list.map((item, index) =>
          <TableRow key={index}>
          <TableCell style={{ textAlign: 'center' }}>{item.id}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.title}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.created_at.slice(0,10)}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.views}</TableCell>
          </TableRow>)
        }
      </TableBody>
    </>
  )
}

export default NoticeContent;
