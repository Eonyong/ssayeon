import { Button, Card, CardContent } from "@mui/material";
import {useEffect, useState} from "react";
import axios from "axios";

function Balance({balanceId, description, dir}){

    const [ratio,setRatio] = useState([])
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
    }

    useEffect(()=>{

    },[ratio])

    const aa = ()=>{
        for(let k in ratio){
            console.log(ratio[k])
        }
    }


    useEffect(()=>{
    },[])

    return(
        <Card>
            <CardContent>
                <Button
                    size="small"
                    onClick={onClick}
                >{description}</Button>
                {flag && dir === "LEFT" ? ratio["left_ratio"] : ratio["right_ratio"]}
            </CardContent>
        </Card>
    )
}

export default Balance

