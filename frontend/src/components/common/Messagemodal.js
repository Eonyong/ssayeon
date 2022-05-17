import { ArrowBack } from "@mui/icons-material";
import { Button, DialogContent, DialogTitle, List, ListItem, ListItemButton, ListItemText, ListSubheader } from "@mui/material";
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

  const style = (pram) => {
    if (pram.sender_id === otherUser) return({textAlign: 'start'});
    return({textAlign: 'end'});
  }

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
      <DialogContent sx={{ padding: '0', minWidth: '500px', maxHeight: '350px' }} >
        {
        isClick ?
        <List>
          {
            messageDetail.map((data, idx)=>{
              console.log(data);
              return (
                <ListItem key={idx} sx={{ paddingX:'1' }}>
                  <ListItemText sx={style(data)}>
                    <strong>{data.description}</strong>
                    <ListSubheader>
                      {data.sender_nickname}
                    </ListSubheader>
                  </ListItemText>
                </ListItem>
              );
            })
          }
        <Button>전송</Button>
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