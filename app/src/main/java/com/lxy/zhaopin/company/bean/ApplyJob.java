package com.lxy.zhaopin.company.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/2.
 */

public class ApplyJob {

    /**
     * result : {"code":200,"dispalyMsg":"success"}
     * user : [{"id":2,"nikeName":"zhaopin07268267","name":"王震","phone":"18370600881","email":"996242587@qq.com","password":"c8b90ac204d07e224870a18804e777a4","imgUrl":null,"gender":0}]
     */

    private ResultBean result;
    private List<UserBean> user;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
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
         * name : 王震
         * phone : 18370600881
         * email : 996242587@qq.com
         * password : c8b90ac204d07e224870a18804e777a4
         * imgUrl : null
         * gender : 0
         */

        private int id;
        private String nikeName;
        private String name;
        private String phone;
        private String email;
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
