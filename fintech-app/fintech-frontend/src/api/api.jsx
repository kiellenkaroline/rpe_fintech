import axios from 'axios';

const api = axios.create({
    baseURL: 'http://fintech-backend:8080', 
})

export default api;