import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import { useState } from 'react';
import axios from 'axios';

export default function SkillTech() {

  const [skillSet, SetSkillSet] = useState([]);
  function skills(skill) {
    return axios.get('https://programmers.co.kr/api/tags/search',
    { params: { term: skill, category: 'technical' } })
    .then(res => res.data.results)
    .catch(e => console.log(e));
  };

  const onHandler = e => {
    SetSkillSet([]);
    skills(e.target.value)
    .then(res=>SetSkillSet(res))
    .catch(e=>console.log(e));
  };

  return (
    <Autocomplete
      multiple
      size="small"
      options={skillSet}
      getOptionLabel={(option) => option.name}
      renderInput={(params) => (
        <TextField
          {...params}
          variant="standard"
          label="선호 기술 스택"
          placeholder="Skills"
          onChange={onHandler}
        />
    )}
  />
  );
}
