package com.sliit.medhub;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Consultant consultant;
    Payment payment;
    Patient patient;
    Hospital hospital;

    @Before
    public void setUp() {
        consultant = new Consultant();
        patient = new Patient();
        hospital = new Hospital();
        payment = new Payment();
    }

    //IT19116570
    @Test
    public void isNumberCorrect() {

        boolean value = payment.isPhoneNumberValid("0770156468");
        assertEquals(true, value);

    }

    //IT19116570
    @Test
    public void isCardNumberCorrect() {

        boolean value = payment.isCardNumValid("0125369999569789");
        assertEquals(true, value);
    }

    //IT19116570
    @Test
    public void isCVCCorrect(){

        boolean value=payment.isCVCValid("225");
        assertEquals(true,value);
    }

    //IT19991986
    @Test
    public void NicIsCorrect(){
        boolean value = patient.isNICValid("980853926V");
        assertEquals(true,value);
    }

    //IT19991986
    @Test
    public void RegNoisCorrect(){
        boolean value = hospital.isRegNoValid("H12345");
        assertEquals(true,value);
    }

    //IT19963334
    @Test
    public void DocRegNoCorrect(){
        boolean value = consultant.isRegisterNoValid("D25649");
        assertEquals(true,value);
    }

}