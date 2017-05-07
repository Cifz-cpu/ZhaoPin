package com.lxy.zhaopin.company.bean;

/**
 * Created by Administrator on 2017/5/1.
 */

public class SendBacK {

    /**
     * result : {"code":200,"dispalyMsg":"success"}
     * id : 9
     * name : javaqwe
     * company : {"id":1,"name":"井冈山大学","address":"江西省吉安市学院路","desc":"江西省教育厅直属","telPhone":"18370600881","password":null}
     * desc : sssssssss
     * requireNumber : 10
     * resume : sssssssfdsfsdafdsafdsafsda
     * salary : {"min":3000,"max":5000}
     */

    private ResultBean result;
    private int id;
    private String name;
    private CompanyBean company;
    private String desc;
    private int requireNumber;
    private String resume;
    private SalaryBean salary;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRequireNumber() {
        return requireNumber;
    }

    public void setRequireNumber(int requireNumber) {
        this.requireNumber = requireNumber;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public SalaryBean getSalary() {
        return salary;
    }

    public void setSalary(SalaryBean salary) {
        this.salary = salary;
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
         * id : 1
         * name : 井冈山大学
         * address : 江西省吉安市学院路
         * desc : 江西省教育厅直属
         * telPhone : 18370600881
         * password : null
         */

        private int id;
        private String name;
        private String address;
        private String desc;
        private String telPhone;
        private Object password;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }
    }

    public static class SalaryBean {
        /**
         * min : 3000
         * max : 5000
         */

        private int min;
        private int max;

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }
}
