import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_ROOT;


const register = (nickname, name, email, class_id, password) => {
  return axios.post(API_BASE_URL + 'api/auth/join', {
    nickname,
    name,
    email,
    class_id,
    password,
  });
};

const login = (email, password) => {
  return axios.post(API_BASE_URL + "api/auth/login", {
      email,
      password,
    })
    .then((res) => {
      console.log('로그인 데이터 들어옴');
      if (res.data.data) {
        console.log(res);
        localStorage.setItem("token", res.data.data);

      }
      return res.data;
    });
};

const logout = () => {
  localStorage.removeItem("token");
};

const withdrawal = () => {
  const token = localStorage.getItem('token');
  const headers = {Authorization: `Bearer ${token}`};

  return axios.delete(API_BASE_URL + "api/user",{ headers:headers })
  .then(() => {})
  .catch(e => console.log(e, headers));
}

const userProfile = () => {
  const token = localStorage.getItem('token');
  const headers = {Authorization: `Bearer ${token}`};
  return axios.get(API_BASE_URL + 'api/user/mypage', { headers:headers })
  .then(res => localStorage.setItem('user', JSON.stringify(res.data.data)))
  .catch(e => console.log(e));
}

const form = new FormData();
const profileEdit = (userData) => {
  const token = localStorage.getItem('token');

  for (var user in userData) {
    form.append(user, userData[user])
  };
  console.log(form);
  
  return axios.patch(`${API_BASE_URL}api/user/${userData['id']}`, form,
  {
    headers: {'Authorization': `Bearer ${token}`}
  })
  .then(res => console.log(res))
  .catch(e => console.log(e));
}

const authService = {
  register,
  login,
  logout,
  withdrawal,
  userProfile,
  profileEdit,
};

export default authService;