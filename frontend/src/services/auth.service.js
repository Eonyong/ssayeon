import axios from "axios";

const API_BASE_URL = process.env.REACT_APP_API_ROOT;


const register = (nickname, name, email, class_id, password) => {
  return axios.post(API_BASE_URL + 'api/auth/join', {
    nickname,
    name,
    email,
    class_id,
    password
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
        localStorage.setItem("user", JSON.stringify(res.data));
      }
      return res.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
};

const authService = {
  register,
  login,
  logout,
};

export default authService;