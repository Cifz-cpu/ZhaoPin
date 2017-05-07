package com.lxy.zhaopin.company.bean;

/**
 * Created by Administrator on 2017/5/1.
 */

public class ComLogin {

    /**
     * result : {"code":200,"dispalyMsg":"success"}
     * company : {"id":3,"name":"百度","address":null,"desc":null,"telPhone":"13596325844","password":"password"}
     */

    private ResultBean result;
    private CompanyBean company;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
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

    public static class CompanyBean {
        /**
         * id : 3
         * name : 百度
         * address : null
         * desc : null
         * telPhone : 13596325844
         * password : password
         */

        private int id;
        private String name;
        private Object address;
        private Object desc;
        private String telPhone;
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getDesc() {
            return desc;
        }

        public void setDesc(Object desc) {
            this.desc = desc;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
