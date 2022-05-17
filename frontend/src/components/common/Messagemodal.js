import { ArrowBack } from "@mui/icons-material";
import { Box, Button, DialogActions, DialogContent, DialogTitle, List, ListItem, ListItemButton, ListItemText, ListSubheader, TextField } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
function MessageModal() {
  
  const { user } = useSelector((state)=>state.auth);
  const API_BASE_URL = process.env.REACT_APP_API_ROOT;
  const headers = {Authorization: `Bearer ${user}`};
  const [messageList, setMessageList] = useState([]);
  const [messageDetail, setMessageDetail] = useState([]);
  const [otherUser, setOtherUser] = useState(0);
  const [IsClick, setIsClick] = useState(false);
  const [chatting, setChatting] = useState('');
  const onChatHandler = e => {
    const {value} = e.target;
    setChatting(value);
  }


  const messageListSet = () => {
    setMessageList([]);
    axios.get(API_BASE_URL + '/user/message/list',
    {
      headers:headers
    })
    .then(res=> {
      setMessageList([res.data.data[0], ...messageList]);
      messageList.reverse();
    })
    .catch(e=> console.log(e));
  };

  const messageSend = e => {
    e.preventDefault();
    if (chatting.trim().length > 0) {
      axios.post(API_BASE_URL + `/user/message/${otherUser}`,
      {description:chatting}, {headers:headers})
      .then(res=>setChatting(''))
      .catch(e=>console.log(e));
    }
  };

  const style = (pram) => {
    if (pram.sender_id === otherUser) return({textAlign: 'start'});
    return({textAlign: 'end'});
  }

  const msDetail = (pram) => {
    axios.get(API_BASE_URL + `/user/message/${pram}`, {headers:headers})
    .then(res=>{
      var data = res.data.data;
      setMessageDetail(data.reverse());
    })
    .catch(e=>console.log(e));
  }

  useEffect(()=>{
    setMessageList([]);
    msDetail(otherUser);
    messageListSet();
  }, [otherUser, chatting]);

  return (
    <>
      <DialogTitle>
        <Button onClick={()=>{setIsClick(false)}}>
          <ArrowBack />
        </Button>
      </DialogTitle>
      <DialogContent sx={{ padding: '0', maxHeight: '400px' }} >
        {
        IsClick ?
          <List>
            {
              messageDetail.map((data, idx)=>{
                var date = new Date(data.created_at);
                return (
                  <ListItem key={idx} sx={{ paddingX:1, flexDirection:'row-reverse' }} >
                    <ListItemText sx={style(data)}>
                      <strong>{data.description}</strong>
                      <ListSubheader sx={{padding:0}}>
                        {`${date.getMonth() + 1}/${date.getDate() + 1} ${date.getHours()}:${date.getMinutes()}`}
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
      <DialogActions>
        {
          IsClick ?
          <Box component="form" onSubmit={messageSend} sx={{ display:'flex', width:'100%' }}>
            <TextField
              id="chattext" name="chattext" autoComplete='current-chattext'
              type='text' label='메세지' value={ chatting }
              onChange={ onChatHandler } fullWidth sx={{ mb: 1 }}
            />
            <Button type="submit">전송</Button>
          </Box>:<></>
        }
      </DialogActions>
    </>
  );
}

export default MessageModal;