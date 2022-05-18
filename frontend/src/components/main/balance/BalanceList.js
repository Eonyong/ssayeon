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

import BalanceCreate from "./BalanceCreate";




const themeData = {
    textColor: '#19181f',
    mainColor: '#00B87B',
    backgroundColor: 'white',
    alignment: 'center',
    leftColor: '#00B87B',
    rightColor: '#FF2E00'
}



const BalanceList = (props) => {

    const sampleData = [
        { id: 0, text: 'Answer 1', votes: 15 },
        { id: 1, text: 'Answer 2', votes: 8 },
    ]

    const [resData,setResData] = useState(sampleData)
    const [list,setList] = useState([])
    const [ratio,setRatio] = useState([])

    const createArticle = async () => {
        const response = await axios.get(
            `${process.env.REACT_APP_API_ROOT}/balance/list`
        )
        setList(response.data.data)
    };

    function vote(item, results) {
        // Here you probably want to manage
        // and return the modified data to the server.
        const sampleData = [
            { id: 0, text: 'Answer 1zzzz', votes: 15 },
            { id: 1, text: 'Answer 2zzzz', votes: 31 },
        ]
        setResData(sampleData)
    }

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
                        <Stack
                            sx={{ pt: 4 }}
                            direction="row"
                            spacing={2}
                            justifyContent="center"
                        >
                            <Link to={`/balance/create`}>
                            <Button variant="contained">밸런스 게임 생성하기</Button>
                            </Link>
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
                                                    <Button/>
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
                                                    {/*<Link to={`/balance/${item.balance_id}`}>*/}
                                                        <CardMedia
                                                            component="img"
                                                            image="https://source.unsplash.com/random/"
                                                            alt="random"
                                                        />
                                                        {/*<LeafPoll*/}
                                                        {/*    type='multiple'*/}
                                                        {/*    question={item.description}*/}
                                                        {/*    results={resData}*/}
                                                        {/*    theme={themeData}*/}
                                                        {/*    onVote={vote}*/}
                                                        {/*    isVoted={false}*/}
                                                        {/*/>*/}
                                                    {/*</Link>*/}
                                                    <Typography gutterBottom variant="h2" component="h2">
                                                        {item.description}
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
