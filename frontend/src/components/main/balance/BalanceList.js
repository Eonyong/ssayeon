import { Link, useNavigate } from "react-router-dom";
import { Box, Button, Checkbox, Container, FormControlLabel, Grid, TextField, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "axios";

const BalanceList = (props) => {

    const [list,setList] = useState()

    const createArticle = () => {
        axios.get(
            `${process.env.REACT_APP_API_ROOT}/balance/list`
        )
            .then((res) => console.log(res))
            .catch((err) => console.log(err));
    };

    useEffect(()=>{
        createArticle()
    },[])

    return (
        <Container>
            <div>
                hello...
            </div>
        </Container>
    );
};

export default BalanceList;
