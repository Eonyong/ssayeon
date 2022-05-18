import { Box, Button, Checkbox, Container, FormControlLabel, Grid, TextField, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const BalanceCreate = () => {
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate()
    const [InputValue, setInputValue] = useState({
        description: 'a',
        left_description: 'b',
        right_description: 'c'
    });

    const [description,setDescription] = useState("")
    const [leftDescription,setLeftDescription] = useState("")
    const [rightDescription,setRightDescription] = useState("")

    const onClick = async ()=>{
        const token = localStorage.getItem('token')
        const header = {'Authorization':`Bearer ${token}`, "Content-Type": `application/json`}

        await axios.post(process.env.REACT_APP_API_ROOT + `/balance`,{
            description: description,
            left_description : leftDescription,
            right_description: rightDescription
        },{headers:header})

        navigate("/balance/list");
    }

    const onInputHandler = e => {
        console.log(e.target.value)
        setInputValue((cur)=>{
            return cur.description = e.target.value
        })
    }

    const onChangeDesc = (e)=>{
        setDescription(e.target.value)
    }
    const onChangeLeftDesc = (e)=>{
        setLeftDescription(e.target.value)
    }
    const onChangeRightDesc = (e)=>{
        setRightDescription(e.target.value)
    }

    return (
        <Container>
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <Grid container direction='column'>
                    <Typography variant="h5" sx={{ mb: 2 }}>투표 생성하기</Typography>
                    <Typography>생성할 투표를 입력해주세요</Typography>
                        <TextField
                            id='email' name="email" autoComplete="text" margin='normal'
                            type='text' placeholder="내용!" label='투표 내용'
                            value={ description } onChange={ onChangeDesc }
                            fullWidth sx={{ mb: 1 }}
                        />
                        <TextField
                            type='text' placeholder="좌측!" label='왼쪽 내용'
                            value={ leftDescription } onChange={ onChangeLeftDesc }
                            fullWidth sx={{ mb: 1 }}
                        />
                        <TextField
                            type='text' placeholder="우측!" label='오른쪽 내용'
                            value={ rightDescription } onChange={ onChangeRightDesc }
                            fullWidth sx={{ mb: 1 }}
                        />
                        <Button
                            type="submit" sx={{ py: 1, backgroundColor: '#4B7BF5' }}
                            fullWidth variant="contained" disabled={loading}
                            onClick={onClick}
                        >
                            생성하기
                        </Button>
                </Grid>
            </Box>
        </Container>
    );
};

export default BalanceCreate;
