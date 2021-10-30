package com.simcoder.bimbo.Admin;
import android.content.Context;
import  android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences usersession;
    SharedPreferences.Editor editor;
    Context context;

    public static final String SESSION_USERSESSION = "userLoginSession";
    public static final String SESSION_REMEMBERME = "rememberMe";


    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONENUMBER = "phoneNumber";

    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DATE = "date";
    public static final String KEY_GENDER = "gender";
    /// Session Names

//

   private static final String IS_REMEMBERME = "IsRememberMe";
    public static  final  String KEY_SESSIONEMAIL ="email";
    public static final String KEY_SESSIONPASSWORD= "password";


    public SessionManager(Context _context, String sessionName) {
        context = _context;
        usersession = context.getSharedPreferences(sessionName, Context.MODE_PRIVATE);
        editor = usersession.edit();
    }

    // public void createLoginSession(String email, String username, String email, String phoneNo, String password, String  age, String gender){}


    public void createLogInSession(String email, String password) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_FULLNAME, password);
        editor.commit();
    }
         // This is for the Login Page
         // We need another one for the Verifiation Page
    public void createRememberMeSession(String email, String password) {
        editor.putBoolean(IS_REMEMBERME, true);
        editor.putString(KEY_SESSIONEMAIL, email);
        editor.putString(KEY_SESSIONPASSWORD, password);

        editor.commit();
    }


    public HashMap<String, String> getUserDetailFromSession;

  public boolean checkLogin() {
        if (usersession.getBoolean(IS_LOGIN, false)){
             return  true;

      }
      else {
          return  false;
      }

  }

    public boolean checkRememberMe() {
        if (usersession.getBoolean(IS_REMEMBERME, false)){
            return  true;

        }
        else {
            return  false;
        }

    }

    public HashMap<String, String> getRememberMeDetailsFromSession() {
        HashMap<String, String> userData = new HashMap<>();
        userData.put(KEY_SESSIONEMAIL, usersession.getString(KEY_SESSIONEMAIL, null));
        userData.put(KEY_SESSIONPASSWORD, usersession.getString(KEY_SESSIONPASSWORD, null));

        return userData;
    };




    public void logoutUserFromSession() {
    }

}






