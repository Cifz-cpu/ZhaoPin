package com.lxy.zhaopin.bean;

/**
 * Created by Administrator on 2017/5/1.
 */

public class ChangeBack {

    /**
     * result : {"code":200,"dispalyMsg":"success"}
     * user : {"id":1,"nikeName":"beyond","name":"xxxx","phone":"110","email":"wwwww@wwww","password":"wwwwwww","imgUrl":"www.6666666666666.jpg","gender":1}
     */

    private ResultBean result;
    private UserBean user;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class ResultBean {
        /**
         * code : 200
         * dispalyMsg : success
         */

        private int code;
        private String dispalyMsg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDispalyMsg() {
            return dispalyMsg;
        }

        public void setDispalyMsg(String dispalyMsg) {
            this.dispalyMsg = dispalyMsg;
        }
    }

    public static class UserBean {
        /**
         * id : 1
         * nikeName : beyond
         * name : xxxx
         * phone : 110
         * email : wwwww@wwww
         * password : wwwwwww
         * imgUrl : www.6666666666666.jpg
         * gender : 1
         */

        private int id;
        private String nikeName;
        private String name;
        private String phone;
        private String email;
        private String password;
        private String imgUrl;
        private int gender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNikeName() {
            return nikeName;
        }

        public void setNikeName(String nikeName) {
            this.nikeName = nikeName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }
    }
}
