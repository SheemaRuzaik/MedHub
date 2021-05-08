package com.sliit.medhub;

public class Payment {

    private Integer payID=100;
    private String PatientName;
    private Integer phoneNumber;
    private String NIC;
    private Integer Age;
    //private String Gender;
    private String CardType;
    private Integer CardNumber;
    private String NameOnCard;
    private String ExpDate;
    private Integer CVC;

    public Payment() {
    }


    public Integer  getPayID() {

        payID=payID+1;

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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

   // public String getGender() {
  //      return Gender;
  //  }

  //  public void setGender(String gender) {
  //      Gender = gender;
   // }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public Integer getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
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

    public Integer getCVC() {
        return CVC;
    }

    public void setCVC(Integer CVC) {
        this.CVC = CVC;
    }



    //testing methods

    public boolean isNICValid(String NIC) {
        if((NIC.length()==10 && NIC.charAt(9)=='V') || (NIC.length()==12)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPhoneNumberValid(String phoneNumber){
        if(phoneNumber.length()==10){
            return true;
        }else {
            return false;
        }
//
//     public boolean isCVCValid(String CVC){
//            if(CVC.length()==3){
//                return true;
//            }else{
//                return false;
//            }
//
//        }
//
//
//    public boolean isCardNumValid(String cardNum){
//            if (cardNum.length()==16){
//                return true;
//            }else{
//
//                return false;
//            }

        }
    }


