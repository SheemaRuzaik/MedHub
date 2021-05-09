package com.sliit.medhub;

public class Hospital {
    private String email;
    private String register_no;
    private String name;
    private String address;
    private Integer phone;
    private String password;

    public Hospital() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegister_no() {
        return register_no;
    }

    public void setRegister_no(String register_no) {
        this.register_no = register_no;
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRegNoValid(String reg_no) {
        if((reg_no.length()==6 && reg_no.charAt(0)=='H') ) {
            return true;
        } else {
            return false;
        }
    }
}
