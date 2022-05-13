import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import axios from "axios";
import {useEffect, useState} from "react";

function Balance({balanceId, description,setStatistics,setPoll, dir}){

    const [flag,isFlag] = useState(false)
    const [stats, setStats] = useState([])
    const [render,setRender] = useState("");

    useEffect(()=>{
        setRender(stats.left_ratio)
    },[stats])
    // 투표하기
    const onClick = async ()=>{

        const token = localStorage.getItem('token')
        const headers = {'Authorization' : `Bearer ${token}`}

        const pollResponse = await axios.post(`${process.env.REACT_APP_API_ROOT}/balance/${balanceId}/poll`,
            {poll:dir},{headers:headers})

        const response = await axios.get(
            `${process.env.REACT_APP_API_ROOT}/balance/statics/${balanceId}`
        )
        setStats(response.data.data)
        isFlag(cur=>!cur)
        console.log(stats)
    }

    return(
        <Box width='300px'>
            <Card>
                <CardActions>
                    <Button size="small"
                        onClick={onClick}
                    >{description}</Button>
                </CardActions>
            </Card>
            <Card>
                <CardActions>
                    {render}
                </CardActions>
            </Card>
        </Box>
    )
}

export default Balance

