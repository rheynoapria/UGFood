package com.ganbatee.ugfood.ugfood.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.ganbatee.ugfood.ugfood.Model.Request;
import com.ganbatee.ugfood.ugfood.Model.User;

/**
 * Created by enno on 26/10/17.
 */

public class Common {
    public static User currentUser;
    public static Request currentRequest;

    public static final String USER_KEY = "User";
    public static final String PW_KEY = "Password";


    public static boolean isConnectedToInteger(Context context)
    {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if(info != null){
                for (int i=0;i<info.length;i++){
                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }

        }
        return false;
    }

}

