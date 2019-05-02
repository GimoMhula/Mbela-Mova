package mz.co.mm_consultoria.mbelamova.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;

public class NavigatorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getApplicationContext());

        if(sharedPreferencesManager.getPassageiro().getNome()!=null)
            startActivityByClass(MainActivity.class);
        else
            startActivityByClass(LoginActivity.class);

        finish();
    }

    //AUXILIO
    private void startActivityByClass(Class activityClass){
        Intent i = new Intent(getApplicationContext(), activityClass);
        startActivity(i);
    }
}
