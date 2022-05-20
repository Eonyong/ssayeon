import React from 'react'
import { TableBody, TableCell, TableRow, Link } from "@mui/material";

function NoticeContent({ list }) {
  return (
    <>
      <TableBody>
        {list.map((item, index) =>
          <TableRow key={index}>
          <TableCell style={{ textAlign: 'center' }}>
            <Link href={`/boards/notice/${item.id}`}>
              {item.title}
            </Link>
          </TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.created_at.slice(0,10)}</TableCell>
          <TableCell style={{ textAlign: 'center' }}>{item.views}</TableCell>
          </TableRow>)
        }
      </TableBody>
    </>
  )
}

export default NoticeContent;
