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


    Payment payment;

    @Before

    public void setUp() {

        payment = new Payment();
    }


    //IT19116570-M.R.F.Sheema

    @Test
    public void isNumberCorrect() {

        boolean value = payment.isPhoneNumberValid("0770156468");
        assertEquals(true, value);

    }

    //IT19116570-M.R.F.Sheema

    @Test
    public void isCardNumberCorrect() {

        boolean value = payment.isCardNumValid("0125369999569789");
        assertEquals(true, value);
    }

    //IT19116570-M.R.F.Sheema

    @Test
    public void isCVCCorrect(){

        boolean value=payment.isCVCValid("225");
        assertEquals(true,value);
    }

}