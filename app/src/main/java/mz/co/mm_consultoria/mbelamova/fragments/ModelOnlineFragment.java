package mz.co.mm_consultoria.mbelamova.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import mz.co.mm_consultoria.mbelamova.R;

public class ModelOnlineFragment extends Fragment {
    public void showMessage(String message){
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    public void loading(){
        showMessage(getString(R.string.text_loading));
    }

    public boolean hasInternetConnection(){
        boolean isConnectedToInternet = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            isConnectedToInternet = true;
        }
        return isConnectedToInternet;
    }
}
