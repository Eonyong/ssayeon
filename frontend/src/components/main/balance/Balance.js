import { Button, Card, CardContent } from "@mui/material";

function Balance({description}){
    return(
        <Card>
            <CardContent>
                <Button size="small">{description}</Button>
            </CardContent>
        </Card>
    )
}

export default Balance

