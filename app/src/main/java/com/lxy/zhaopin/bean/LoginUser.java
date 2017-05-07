package com.lxy.zhaopin.bean;

/**
 * Created by Administrator on 2017/4/12.
 */

public class LoginUser {

    /**
     * result : {"code":200,"dispalyMsg":"success"}
     * user : {"id":2,"nikeName":"zhaopin07268267","name":null,"phone":"18370600881","email":null,"password":"0dd9349d0ac0ecd42ef4f7b7c871fc95","imgUrl":null,"gender":2}
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
         * id : 2
         * nikeName : zhaopin07268267
         * name : null
         * phone : 18370600881
         * email : null
         * password : 0dd9349d0ac0ecd42ef4f7b7c871fc95
         * imgUrl : null
         * gender : 2
         */

        private long id;
        private String nikeName;
        private String name;
        private String phone;
        private String email;
        private String password;
        private String imgUrl;
        private int gender;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNikeName() {
            return nikeName;
        }

        public void setNikeName(String nikeName) {
            this.nikeName = nikeName;
        }

        public Object getName() {
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

        public Object getEmail() {
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

        public Object getImgUrl() {
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
