package com.example.medhub;

public class Patient {
    private String email;
    private String first_name;
    private String last_name;
    private String nic;
    private String gender;
    private String dob;
    private String passcword;
    private Integer contact;

    public Patient() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPasscword() {
        return passcword;
    }

    public void setPasscword(String passcword) {
        this.passcword = passcword;
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
