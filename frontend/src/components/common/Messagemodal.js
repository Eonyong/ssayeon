import { ArrowBack } from "@mui/icons-material";
import { Button, DialogContent, DialogTitle, Divider, List, ListItem, ListItemButton, ListItemText, ListSubheader, Typography } from "@mui/material";
import { textAlign } from "@mui/system";
import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
function MessageModal() {
  
  const { user } = useSelector((state)=>state.auth);
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  const headers = {Authorization: `Bearer ${user}`};
  const [messageList, setMessageList] = useState([]);
  const [messageDetail, setMessageDetail] = useState([]);
  const [isClick, setIsClick] = useState(false);
  const [otherUser, setOtherUser] = useState(0);
  

  const messageListSet = () => {
    axios.get(API_BASE_URL + '/user/message/list',
    {
      headers:headers
    })
    .then(res=> {
      setMessageList([...messageList, res.data.data[0]]);
    })
    .catch(e=> console.log(e));
  };

  const msDetail = (pram) => {
    axios.get(API_BASE_URL + `/user/message/${pram}`, {headers:headers})
    .then(res=>{
      setMessageDetail(res.data.data);
    })
    .catch(e=>console.log(e));
  }

  useEffect(()=>{
    setMessageList([]);
    messageListSet();
    msDetail(otherUser);
  }, [otherUser]);

  return (
    <>
      <DialogTitle>
        <Button onClick={()=>setIsClick(false)}>
          <ArrowBack />
        </Button>
      </DialogTitle>
      <DialogContent>
        {
        isClick ?
        <List>
          {
            messageDetail.map((data, idx)=>{
              return (
                <ListItem key={idx}>
                  <ListItemText>
                    {data.description}
                    <ListSubheader 
                    // sx={{textAlign:{
                      // if (data.send_id != otherUse) {
                        
                      // }
                      // 'end'}}}
                      >
                      {data.sender_nickname}
                    </ListSubheader>
                  </ListItemText>
                </ListItem>
              );
            })
          }
        </List>
        :
        <List>
          {
            messageList.map((data, idx) => {
              var date = new Date(data.created_at);
              return (
                <ListItemButton divider key={idx}>
                  <ListItemText
                    primary={data.description} onClick={()=>{
                      setOtherUser(data.other_user_id);
                      setIsClick(true);
                    }}
                    secondary={`${date.getMonth() + 1}/${date.getDate() + 1} ${date.getHours()}:${date.getMinutes()} - ${data.sender_nickname}`}
                  />
                </ListItemButton>
              );
            })
          }
        </List>
        }
      </DialogContent>
    </>
  );
}

export default MessageModal;