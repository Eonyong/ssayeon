import { DialogContent, List, ListItemButton, ListItemText } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { userProfile } from "../../user/auth";
const API_BASE_URL = process.env.REACT_APP_API_ROOT;
const token = localStorage.getItem('token');
const headers = {Authorization: `Bearer ${token}`};
function MessageModal() {

  const [user, setUser] = useState({});  
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

  const messageGet = () => axios.get(API_BASE_URL + 'api/user/message/list',
  { params: {size:100, page:1}, headers: headers })
  .then(res => {
    setMessageList(res.data.data);
  })
  .catch(e => console.log(e));

  const userSetting = () => {
    console.log(userProfile());
  };

  useEffect(() => {
    messageGet();
    userSetting();
  }, []);

  return (
    token ?
      <DialogContent>
        <List>
          {
            messageList.map((data) => {
              var date = new Date(data.created_at);
              return (
                (data.receiver_id === user.id || data.sender_id === user.id) ?
                <ListItemButton divider>
                  <ListItemText
                    primary={data.description}
                    secondary={`${date.getMonth() + 1}/${date.getDate() + 1} ${date.getHours()}:${date.getMinutes()} - ${data.sender_nickname}`}
                  />
                </ListItemButton> : <></>
              );
            })
          }
        </List>
      </DialogContent> : <></>
  );
}

export default MessageModal;