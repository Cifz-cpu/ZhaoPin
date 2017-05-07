package com.lxy.zhaopin.bean;

/**
 * Created by Administrator on 2017/5/1.
 */

public class RegistBack {

    /**
     * result : {"code":200,"dispalyMsg":"success"}
     * user : {"id":7,"nikeName":"zhaopin89049009","name":null,"phone":"15030765493","email":null,"password":"5f4dcc3b5aa765d61d8327deb882cf99","imgUrl":null,"gender":2}
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
         * id : 7
         * nikeName : zhaopin89049009
         * name : null
         * phone : 15030765493
         * email : null
         * password : 5f4dcc3b5aa765d61d8327deb882cf99
         * imgUrl : null
         * gender : 2
         */

        private int id;
        private String nikeName;
        private Object name;
        private String phone;
        private Object email;
        private String password;
        private Object imgUrl;
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

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
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

        public void setEmail(Object email) {
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

        public void setImgUrl(Object imgUrl) {
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
