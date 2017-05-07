package com.lxy.zhaopin.company.bean;

/**
 * Created by Administrator on 2017/5/2.
 */

public class SAeeResume {

    /**
     * result : {"code":200,"dispalyMsg":"success"}
     * id : 3
     * name : fff
     * userName : eed
     * userId : 2
     * birthday : 145784003
     * education : ddf
     * expectPosition : xdx
     * expectCity : sddp
     * experience : dd
     * contact : {"email":":15779699552","phone":":xsd"}
     * salary : {"min":2000,"max":3000}
     */

    private ResultBean result;
    private int id;
    private String name;
    private String userName;
    private int userId;
    private int birthday;
    private String education;
    private String expectPosition;
    private String expectCity;
    private String experience;
    private ContactBean contact;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExpectPosition() {
        return expectPosition;
    }

    public void setExpectPosition(String expectPosition) {
        this.expectPosition = expectPosition;
    }

    public String getExpectCity() {
        return expectCity;
    }

    public void setExpectCity(String expectCity) {
        this.expectCity = expectCity;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public ContactBean getContact() {
        return contact;
    }

    public void setContact(ContactBean contact) {
        this.contact = contact;
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

    public static class ContactBean {
        /**
         * email : :15779699552
         * phone : :xsd
         */

        private String email;
        private String phone;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }

    public static class SalaryBean {
        /**
         * min : 2000
         * max : 3000
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
