import {Box, Button, Card, CardActions, CardContent, Typography} from "@mui/material";

function Balance({description}){
    return(
        <Box width='300px'>
            <Card>
                <CardActions>
                    <Button size="small">{description}</Button>
                </CardActions>
            </Card>
        </Box>
    )
}

export default Balance

