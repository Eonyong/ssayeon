import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import axios from "axios";

function Balance({balanceId, description,setStatistics,setIsStatistics}){

    const onClick = async ()=>{
        setIsStatistics(true)
        const response = await axios.get(
            `${process.env.REACT_APP_API_ROOT}/balance/statics/${balanceId}`
        )
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
        </Box>
    )
}

export default Balance

