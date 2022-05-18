import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import { useState } from 'react';
import axios from 'axios';

export default function Companies() {

  const [CompanySet, SetCompanySet] = useState([]);
  function skills(company) {
    return axios.get('https://www.jobplanet.co.kr/api/v1/autocomplete/suggest.json',
    { params: { term: company } })
    .then(res => res.data.autocompletes)
    .catch(e => console.log(e));
  };

  const [cp, SetCp] = useState('');
  const onHandler = e => {
    SetCompanySet([]);
    skills(e.target.value)
    .then(res=>SetCompanySet(res))
    .catch(e=>console.log(e));
  };

  return (
    <Autocomplete
      size="small" value={cp}
      onChange={(_, newValue)=>SetCp(newValue.value)}
      options={CompanySet}
      defaultValue={cp}
      isOptionEqualToValue={(option)=>option.value}
      renderInput={(params) => (
        <TextField
          {...params}
          variant="standard"
          label="재직 중인 회사"
          placeholder="Company"
          onChange={onHandler}
        />
      )}
    />
  );
}
