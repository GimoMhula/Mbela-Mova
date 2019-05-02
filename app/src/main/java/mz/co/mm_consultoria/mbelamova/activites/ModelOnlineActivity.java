package mz.co.mm_consultoria.mbelamova.activites;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;

import mz.co.mm_consultoria.mbelamova.R;

public class ModelOnlineActivity extends FragmentedActivity{
    private int viewId;

    public void showMessage(String message){
        Snackbar.make(findViewById(getViewId()), message, Snackbar.LENGTH_LONG).show();
    }

    public void loading(){
        showMessage(getString(R.string.text_loading));
    }

    public boolean hasInternetConnection(){
        boolean isConnectedToInternet = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            isConnectedToInternet = true;
        }
        return isConnectedToInternet;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }
}
