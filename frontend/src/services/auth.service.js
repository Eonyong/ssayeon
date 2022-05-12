import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_ROOT;
const token = localStorage.getItem('token');
const headers = {'Authorization': `Bearer ${token}`};

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
      if (res.data.data) localStorage.setItem("token", res.data.data);
      return res.data;
    });
};


const logout = () => {
  localStorage.removeItem("token");
};

const withdrawal = () => {
  return axios.delete(API_BASE_URL + "api/user",{ headers:headers })
  .then(() => {})
  .catch(e => console.log(e));
}

const userProfile = () => {
  return axios.get(API_BASE_URL + 'api/user/mypage', { headers:headers })
  .then(res => console.log(res))
  .catch(e => console.log(e));
}

const profileEdit = (userData) => {
  const form = new FormData();
  for (var user in userData) {
    form.append(user, userData[user])
  };
  return axios.patch(`${API_BASE_URL}api/user/${userData['id']}`, form, {headers: headers,})
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