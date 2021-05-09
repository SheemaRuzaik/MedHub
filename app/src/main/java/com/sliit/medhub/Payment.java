package com.sliit.medhub;

public class Payment {

    private Integer payID = 0;
    private String PatientName;
    private String phoneNumber;
    private String NIC;
    private String Age;
    //private String Gender;
    private String CardType;
    private String CardNumber;
    private String NameOnCard;
    private String ExpDate;
    private String CVC;

    public Payment() {
    }


    public Integer getPayID() {

        payID = payID + 1;

        return payID;
    }

    public void setPayID(Integer payID) {
        this.payID = payID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return NameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        NameOnCard = nameOnCard;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
    }


 //  testing methods

    public boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.length() == 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCVCValid(String CVC) {
        if (CVC.length() == 3) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isCardNumValid(String CardNumber) {
        if (CardNumber.length() == 16) {
            return true;
        } else {

            return false;
        }

    }


}
