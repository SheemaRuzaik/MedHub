package com.example.medhub;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME="session";
    String SESSION_KEY="session_user";

    public SessionManagement(Context context){

        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();


    }

    public void saveSession(Patient patient){

        String un=patient.getEmail();
        editor.putString(SESSION_KEY,un).commit();

    }

    public void saveHospitalSession(Hospital hospital){

        String un=hospital.getEmail();
        editor.putString(SESSION_KEY,un).commit();
    }

    public String getSession(){

        return sharedPreferences.getString(SESSION_KEY,"No user");
    }

    public String getHospitalSession(){

        return sharedPreferences.getString(SESSION_KEY,"No user");
    }

    public void removeSession(){

        editor.putString(SESSION_KEY,"No user").commit();


    }
}
