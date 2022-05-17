import { Link } from "react-router-dom";
import {
    Box,
    Button, Card, CardContent, CardMedia,
    Container, CssBaseline,
    Divider,
    Grid, Stack,
    Typography
} from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import Balance from "./Balance";
import Picture from "./Picture";

const BalanceList = (props) => {

    const [list,setList] = useState([])
    const [ratio,setRatio] = useState([])

    const createArticle = async () => {
        const response = await axios.get(
            `${process.env.REACT_APP_API_ROOT}/balance/list`
        )
        setList(response.data.data)
    };

    useEffect(()=>{
        createArticle()
    },[list])

    return (
        <Container>
            <CssBaseline />
                {/* Hero unit */}
                <Box
                    py={6}
                    sx={{bgcolor: 'background.paper'}}
                >
                    <Container maxWidth="sm">
                        <Typography
                            component="h1"
                            variant="h2"
                            align="center"
                            color="text.primary"
                            gutterBottom
                        >
                            BALANCE GAME
                        </Typography>
                        <Typography variant="h5" align="center" color="text.secondary" paragraph>
                            밸런스게임!
                        </Typography>
                        <Stack
                            sx={{ pt: 4 }}
                            direction="row"
                            spacing={2}
                            justifyContent="center"
                        >
                            <Button variant="contained">왼쪽 선택!</Button>
                            <Button variant="outlined">오른쪽 선택!</Button>
                        </Stack>
                    </Container>
                </Box>
                <Container sx={{ py: 8 }} maxWidth="md">
                    {
                        list.map((item,index) => {
                            var date = new Date(item.created_at);
                            return(
                                
                                    <Card
                                        sx={{ display: 'flex', flexDirection: 'col' }} key={index}
                                    >
                                        <CardContent>
                                            <Grid container>
                                                <Grid item xs={3}>
                                                    <Button></Button>
                                                    {/*<Link to={`/balance/${item.balance_id}`}>*/}
                                                        <Balance
                                                            ratio = {ratio}
                                                            setRatio={setRatio}
                                                            balanceId={item.balance_id}
                                                            description={item.left_description}
                                                            dir="LEFT"
                                                        />
                                                    {/*</Link>*/}
                                                </Grid>
                                                <Grid item xs={6}>
                                                    <Link to={`/balance/${item.balance_id}`}>
                                                        <CardMedia
                                                            component="img"
                                                            image="https://source.unsplash.com/random"
                                                            alt="random"
                                                        />
                                                    </Link>
                                                    <Typography gutterBottom variant="h5" component="h2" sx={{ textDecorationLine:'none' }}>
                                                        게임 내용 : {item.description}
                                                    </Typography>
                                                    <Typography gutterBottom variant="body1" component="h5">
                                                        게임 만든 사람 : {item.user_nickname}
                                                    </Typography>
                                                    <Typography gutterBottom variant="body1" component="h2">
                                                        {`${date.getMonth() + 1}월 ${date.getDate() + 1}일 ${date.getHours()}시${date.getMinutes()}분`}
                                                    </Typography>
                                                </Grid>
                                                <Grid item xs={3}>
                                                    <Balance
                                                        ratio = {ratio}
                                                        setRatio={setRatio}
                                                        balanceId={item.balance_id}
                                                        description={item.right_description}
                                                        dir="RIGHT"
                                                    />
                                                </Grid>
                                            </Grid>
                                        </CardContent>
                                        <Divider />
                                    </Card>
                            );
                        })
                    }
                </Container>
        </Container>
    );
};

export default BalanceList;
