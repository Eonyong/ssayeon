import React from 'react';
import { Button, Fab, ButtonGroup, Divider, Typography } from '@mui/material';
import { Accordion, AccordionSummary, AccordionDetails } from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import styled from  'styled-components';

const Side = styled.div`
  display: flex;
  border-right: 1px solid #e0e0e0;
  flex-direction: column;
  justify-content: center;
  width: 10%;
`

function SideBar() {
  return(
    <Side>
      <Divider />
      <Button>공지사항</Button>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
        >
          <Typography>게시판</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <ButtonGroup fullWidth
            orientation="vertical" variant="text"
          >
            <Fab variant="extended" sx={{ m:1 }}>자유 게시판</Fab>
            <Fab variant="extended">질문 게시판</Fab>
            <Fab variant="extended">꿀팁 게시판</Fab>
          </ButtonGroup>
        </AccordionDetails>
      </Accordion>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
        >
          <Typography>싸피 놀이터</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <ButtonGroup fullWidth
            orientation="vertical" variant="text"
          >
            <Fab variant="extended">밸런스 게임</Fab>
            <Fab variant="extended">선호도 조사</Fab>
          </ButtonGroup>
        </AccordionDetails>
      </Accordion>
      <Button>모임</Button>
      <Button>공지사항</Button>
    </Side>
  );
};

export default SideBar;