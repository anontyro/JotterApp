package co.alexwilkinson.jotter.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Spreetrip on 2/9/2017.
 */

public class SharedRef {
    SharedPreferences sharedRef;

    public SharedRef(Context context){
        sharedRef = context.getSharedPreferences("sharedRef", Context.MODE_PRIVATE);
    }

    public void saveData(String username){
        SharedPreferences.Editor editor = sharedRef.edit();

            String values = sharedRef.getString("username", "none");
            if(values.equalsIgnoreCase("none")){
                editor.putString("username", username);

            }else {
                editor.putString("username", values + ", " + username);
            }

        editor.commit();
    }

    public String loadUser(){
        String getUser = sharedRef.getString("username", "none");

        return getUser;
    }

    public String toString(){
        return sharedRef.getString("username", "none");
    }
}
