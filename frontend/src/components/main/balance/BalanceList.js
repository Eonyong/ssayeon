import { Link, useNavigate } from "react-router-dom";
import {
    Box,
    Button, Card, CardActions, CardContent, CardMedia,
    Checkbox,
    Container, CssBaseline,
    FormControlLabel,
    Grid, Stack,
    TextField,
    ThemeProvider,
    Typography
} from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";
import Balance from "./Balance";
import {Copyright} from "@mui/icons-material";

const BalanceList = (props) => {

    const [list,setList] = useState([])

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
            <main>
                {/* Hero unit */}
                <Box
                    sx={{
                        bgcolor: 'background.paper',
                        pt: 8,
                        pb: 6,
                    }}
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
                    <Grid container spacing={8}>
                        {/*<Grid xs={12} sm={6} md={4}>*/}

                        {list.map((item,index) => (
                        <Link to={`/balance/${item.balance_id}`}>
                            <Grid item key={index} xs={12} sm={6} md={4}>
                                <Card
                                    sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                                >
                                    <CardMedia
                                        component="img"
                                        sx={{
                                            // 16:9
                                            pt: '56.25%',
                                        }}
                                        image="https://source.unsplash.com/random"
                                        alt="random"
                                    />
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
                                    </CardContent>
                                </Card>
                            </Grid>
                            <hr/>
                        </Link>
                        ))}
                    </Grid>
                </Container>
            </main>
        </Container>
    );
};

export default BalanceList;
