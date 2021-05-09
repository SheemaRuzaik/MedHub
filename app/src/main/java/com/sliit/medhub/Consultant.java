package com.sliit.medhub;

public class Consultant {

    private String Name;
    private String Email;
    private Integer Phone_No;
    private String Address;
    private String Register_No;
    private String Password;

    public Consultant() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(Integer phone_No) {
        Phone_No = phone_No;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRegister_No() {
        return Register_No;
    }

    public void setRegister_No(String register_No) {
        Register_No = register_No;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isRegisterNoValid(String Register_No){
        if (Register_No.length() == 6 && Register_No.charAt(0) == 'D'){
            return true;
        }else{
            return false;
        }
    }
}
