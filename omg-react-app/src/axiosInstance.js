import axios from 'axios';

const backendServerUrl = process.env.React_APP_BACKEND_SERVER_URL;

const api = axios.create({
  baseURL: backendServerUrl,
});

api.interceptors.request.use(
  (config) => {
    // Access Token 추가 로직
    const accessToken = localStorage.getItem("accessToken");
    config.headers.Authorization = `${accessToken}`;
    return config;
  },
  (error) => {
    console.log(error)
    return Promise.reject(error);
  }
);

export default api;
