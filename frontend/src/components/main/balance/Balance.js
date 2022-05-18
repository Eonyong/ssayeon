import { Button, Card, CardContent } from "@mui/material";
import {useEffect, useState} from "react";
import axios from "axios";

function Balance({balanceId, description, dir,ratio,setRatio}){

    const [localRatio,setLocalRatio] = useState([])
    const [flag,setFlag] = useState(false)

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
        setLocalRatio(response.data.data)
    }

    useEffect(()=>{
        getData()
    },[ratio])

    return(
        <Card>
            <CardContent>
                <Button
                    size="small"
                    onClick={onClick}
                >{description}</Button>
                {flag && dir === "LEFT" ? localRatio["left_ratio"] : null}
                {flag && dir === "RIGHT" ? localRatio["right_ratio"] : null}
            </CardContent>
        </Card>
    )
}

export default Balance

