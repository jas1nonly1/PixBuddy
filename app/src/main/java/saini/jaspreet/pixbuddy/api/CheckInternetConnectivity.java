package saini.jaspreet.pixbuddy.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConnectivity {
    public static boolean internetActivelyAvailable(Context context){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = (networkInfo != null) && (networkInfo.isConnected());
        }
        return connected;
    }
}
