import React from 'react';

import styled from  'styled-components';

const Side = styled.div`
  display: flex;
  border-right: 1px solid #e0e0e0;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 10%;
`

function SideBar() {
  return(
    <Side>
      <button>공지사항</button>
      {/* dropdown */}
      <button>게시판</button>
      <button>싸피 놀이터</button>
      <button>모임</button>
      <button>공지사항</button>
    </Side>
  );
};

export default SideBar;