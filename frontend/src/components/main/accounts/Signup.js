import { Box, Button, FormControl, FormLabel, Grid, Input, InputLabel, TextField, Typography } from "@mui/material";

export default function Signup() {
  return (
    <Box component='form' sx={{ width: '60%' }}>
      <Grid container direction='column'>
        <Typography variant="h1" sx={{ mb: 2 }}>Signup</Typography>
        <Box container component='form'
          sx={{ flexDirection: 'column', my: 5 }}
        >
          <FormControl>
            <FormLabel htmlFor="name">이름</FormLabel>
            <Input
              type='text' placeholder="홍길동"
              fullWidth sx={{ mb: 1 }} id='name'
            />
          </FormControl>
          <TextField
            type='number' placeholder="0000000" label='싸피 학번'
            fullWidth sx={{ my: 1 }}
          />
          <Button fullWidth variant="contained" sx={{ py: 1 }}>교육생 인증</Button>
        </Box>

      </Grid>
    </Box>
  );
};