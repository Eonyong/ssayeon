import { DialogContent, List, ListItemButton, ListItemText } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
function MessageModal() {
  
  const { user } = useSelector((state)=>state.auth);
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  const headers = {Authorization: `Bearer ${user}`};
  const [messageList, setMessageList] = useState([]);

  const messageListSet = () => {
    axios.get(API_BASE_URL + '/user/message/list',
    {
      headers:headers
    })
    .then(res=> {
      setMessageList([
        ...messageList,
        res.data.data[0]]);
    })
    .catch(e=> console.log(e));
  };

  useEffect(()=>{
    messageListSet();
  }, []);

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