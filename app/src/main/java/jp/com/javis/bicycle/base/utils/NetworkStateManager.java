package jp.com.javis.bicycle.base.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by QuanPV on 3/20/18.
 */

public class NetworkStateManager {

    /**
     * Checks if the device is currently connected with any particular network or not.
     *
     * @param context Current context of the application
     * @return "true" if network is available, "false" if none available.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null &&
                networkInfo.isConnectedOrConnecting();
    }
}
