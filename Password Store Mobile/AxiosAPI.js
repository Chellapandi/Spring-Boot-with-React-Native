import axios from 'axios';

let APIKit = axios.create({
    baseURL: 'http://ipaddess:8080/',
    timeout: 15000,
  });

  export default APIKit;