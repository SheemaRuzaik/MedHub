package com.sliit.medhub;

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

    public void saveSession(Payment payment){

        Integer un=payment.getPayID();
        editor.putInt(SESSION_KEY,un).commit();

    }

    public Integer getSession(){

        return sharedPreferences.getInt(SESSION_KEY,0);
    }



}
