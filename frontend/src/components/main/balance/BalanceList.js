import { Link } from "react-router-dom";
import {
    Box,
    Button, Card, CardContent,
    Container, CssBaseline,
    Divider,
    Grid, Stack,
    Typography
} from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import Balance from "./Balance";

const BalanceList = (props) => {

    const [list,setList] = useState([]);
    const [ratio,setRatio] = useState([]);

    const createArticle = async () => {
        const response = await axios.get(
            `${process.env.REACT_APP_API_ROOT}/balance/list`
        );
        setList(response.data.data);
    };

    useEffect(()=>{
        createArticle();
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
                <Grid container spacing={2} columns={{ xs: 4, sm: 8, md: 12 }} mt={2}>
                {
                    list.map((item,index) => {
                        return(
                            <Grid item xs={2} sm={4} md={4} key={index}
                            sx={{ paddingBottom:1 }}>
                                <Grid container sx={{ backgroundColor: `rgba(0,0,0,0.04)` }} height='117.98px'>
                                    <Grid item xs={5}>
                                        <Balance
                                            ratio = {ratio}
                                            setRatio={setRatio}
                                            balanceId={item.balance_id}
                                            description={item.left_description}
                                            dir="LEFT"
                                        />
                                        
                                    </Grid>
                                    <Grid item xs={2} alignSelf='center'>
                                        <Typography variant="body1" component="h2">VS</Typography>
                                    </Grid>
                                    <Grid item xs={5}>
                                        <Balance
                                            ratio = {ratio}
                                            setRatio={setRatio}
                                            balanceId={item.balance_id}
                                            description={item.right_description}
                                            dir="RIGHT"
                                        />
                                    </Grid>
                                </Grid>
                            </Grid>
                        );
                    })
                }
                </Grid>
        </Container>
    );
};

export default BalanceList;
