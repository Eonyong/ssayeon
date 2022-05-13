import { Card, CardContent, Container, Grid, Typography } from "@mui/material";
import { useParams } from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";
import Balance from "./Balance";
import BalanceComments from "./BalanceComments";

function BalanceContent() {
    const [item, setItem] = useState([])
    const param = useParams();

    const getData = async ()=> {
        const response = await axios.get(
            `${process.env.REACT_APP_API_ROOT}/balance/${param.id}`
        )
        setItem(response.data.data);
    }
        useEffect(() => {
            getData();

        }, [])
        return (
            <Container sx={{ py: 8 }} maxWidth="md">
                <Grid container spacing={8}>
                    <Grid item key={item.balance_id} xs={12} sm={6} md={4}>
                        <Card
                            sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                        >
                            <CardContent sx={{ flexGrow: 1 }}>
                                <Typography gutterBottom variant="h5" component="h2">
                                    게임 내용 : {item.description}
                                </Typography>
                                <Typography gutterBottom variant="body1" component="h5">
                                    게임 만든 사람 : {item.user_nickname}
                                </Typography>
                                <Balance
                                    description={item.left_description}
                                />
                                <Balance
                                    description={item.right_description}
                                />
                                <br/>
                                <Typography gutterBottom variant="body1" component="h2">
                                    {item.created_at}
                                </Typography>
                                {
                                    item.comments ?
                                    <Typography>
                                        <BalanceComments
                                            userNickname={item.comments.user_nickname}
                                            description={item.comments.description}
                                            createdAt={item.comments.created_at}
                                            likes={item.comments.likes}
                                        />
                                    </Typography> :<></>
                                }
                            </CardContent>
                        </Card>
                    </Grid>
                    <hr/>
                </Grid>
                <Grid container spacing={8}>
                    {/*<Grid xs={12} sm={6} md={4}>*/}
                    <Grid item key={item.balance_id} xs={12} sm={6} md={4}>
                        <Card
                            sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                        >
                        {/*<BalanceComments*/}
                        {/*    userNickname={item.comments.user_nickname}*/}
                        {/*    description={item.comments.description}*/}
                        {/*    createdAt={item.comments.created_at}*/}
                        {/*    likes={item.comments.likes}*/}
                        {/*/>*/}
                        </Card>
                </Grid>
                    <hr/>
                </Grid>
            </Container>
        )
}

export default BalanceContent

