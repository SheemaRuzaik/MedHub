package com.example.medhub;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    Hospital hospital;
    Patient patient;

    @Before
    public void setUp(){
        patient = new Patient();
        hospital = new Hospital();
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

}