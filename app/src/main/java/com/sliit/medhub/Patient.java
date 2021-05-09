package com.sliit.medhub;

public class Patient {
    private String email;
    private String full_name;
    private String nic;
    private String gender;
    //private String dob;
    private String password;
    private Integer contact;

    public Patient() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passcword) {
        this.password = passcword;
    }

    public Integer getContact() {
        return contact;
    }

    public void setContact(Integer contact) {
        this.contact = contact;
    }

    public boolean isNICValid(String nic) {
        if((nic.length()==10 && nic.charAt(9)=='V') || (nic.length()==12)) {
            return true;
        } else {
            return false;
        }
    }

}
