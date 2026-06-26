import axiosInstance from "./axiosInstance";

export default postApi = {
  getPage : (page, keyword, size) => axiosInstance.get('/api/posts',{
    params : {
        page:page,
        keyword : keyword,
        size : size
    }
  }) ,
  
};
