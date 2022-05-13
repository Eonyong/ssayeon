import { DialogContent, List, ListItemButton, ListItemText } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { useSelector } from "react-redux";
function MessageModal() {
  
  const { user } = useSelector((state)=>state.auth);
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  const headers = {Authorization: `Bearer ${user}`};
  const [messageList, setMessageList] = useState([
    {
      id: 6,
      sender_id: 37,
      sender_nickname: "ssafy",
      receiver_id: 6,
      receiver_nickname: "test1",
      description: "테스트1 유저에게 메시지 전송!!",
      created_at: "2022-05-09T10:39:17"
    },
    {
      id: 5,
      sender_id: 14,
      sender_nickname: "ssafy1",
      receiver_id: 37,
      receiver_nickname: "ssafy",
      description: "나는 이싸피",
      created_at: "2022-05-09T10:36:56"
    }
  ]);

  const messageListSet = () => {
    axios.get(API_BASE_URL + '/user/message/list',
    {
      headers:headers
    })
    .then(res=> console.log(res.data))
    .catch(e=> console.log(e));
  };
  messageListSet();

  return (
    user ?
      <DialogContent>
        <List>
          {
            messageList.map((data, idx) => {
              var date = new Date(data.created_at);
              return (
                <ListItemButton divider key={idx}>
                  <ListItemText
                    primary={data.description}
                    secondary={`${date.getMonth() + 1}/${date.getDate() + 1} ${date.getHours()}:${date.getMinutes()} - ${data.sender_nickname}`}
                  />
                </ListItemButton>
              );
            })
          }
        </List>
      </DialogContent> : <></>
  );
}

export default MessageModal;