import { Button, Card, CardActions, CardContent, Typography } from "@mui/material";
import {useEffect, useState} from "react";
import axios from "axios";

function Balance({balanceId, dir,ratio,setRatio, description}){

    const [localRatio,setLocalRatio] = useState([]);
    const [flag,setFlag] = useState(false);

    const onClick = async ()=>{
        const token = localStorage.getItem('token')
        const header = {'Authorization':`Bearer ${token}`}

        await axios.post(process.env.REACT_APP_API_ROOT + `/balance/${balanceId}/poll`,{
            "poll":dir
        },{headers:header})

        const response = await axios.get(process.env.REACT_APP_API_ROOT + `/balance/statics/${balanceId}`)
        console.log(response.data.data)
        setFlag(true)
        setRatio(response.data.data)
        setLocalRatio(response.data.data)
    }

    const getData = async () =>{
        const response = await axios.get(process.env.REACT_APP_API_ROOT + `/balance/statics/${balanceId}`)
        setLocalRatio(response.data.data);
    }

    useEffect(()=>{
        getData()
    },[ratio])

    return(
        <Card elevation={4}
            sx={{
                height: 
                flag && dir === "LEFT" ? (localRatio["left_ratio"] > 0 ? `${localRatio["left_ratio"]}%` : '40%') : 
                flag && dir === "RIGHT" ? (localRatio["right_ratio"] > 0 ? `${localRatio["right_ratio"]}%` : '40%' ) : '100%',
                backgroundColor:
                flag && dir === "LEFT" ? '#1565c0' : 
                flag && dir === "RIGHT" ? '#b4d877' : null,
                padding:0
            }} onClick={onClick}
        >
            <CardContent sx={{ padding:0 }}>
                <Typography color='black' variant='caption'>{description}</Typography>
                {flag && dir === "LEFT" ? <Typography color='black'>{localRatio["left_ratio"].toFixed(2)}%</Typography>  : null}
                {flag && dir === "RIGHT" ? <Typography color='black'>{localRatio["right_ratio"].toFixed(2)}%</Typography> : null}
            </CardContent>
        </Card>
    )
}

export default Balance

