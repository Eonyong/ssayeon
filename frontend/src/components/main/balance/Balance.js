import { Button, Card, CardContent, Typography } from "@mui/material";
import {useEffect, useState} from "react";
import axios from "axios";

function Balance({balanceId, dir,ratio,setRatio}){

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
        <Card
            sx={{
                height: 
                flag && dir === "LEFT" ? (localRatio["left_ratio"] > 0 ? `${localRatio["left_ratio"]}%` : '40%') : 
                flag && dir === "RIGHT" ? (localRatio["right_ratio"] > 0 ? `${localRatio["right_ratio"]}%` : '40%' ) : '100%',
                backgroundColor:
                flag && dir === "LEFT" ? 'blue' : 
                flag && dir === "RIGHT" ? 'red' : null,
            }}
        >
            <CardContent>
                <Button
                    size="small" sx={{ display:'flex' }}
                    onClick={onClick}
                >
                    { !flag ? <Typography color='black' variant="body1" >선택!!</Typography> : null }
                    {flag && dir === "LEFT" ? <Typography color='black' variant="h5" >{localRatio["left_ratio"]}%</Typography>  : null}
                    {flag && dir === "RIGHT" ? <Typography color='black' variant="h5" >{localRatio["right_ratio"]}%</Typography> : null}
                </Button>
            </CardContent>
        </Card>
    )
}

export default Balance

