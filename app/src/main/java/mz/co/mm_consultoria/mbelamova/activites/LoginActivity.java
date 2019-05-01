package mz.co.mm_consultoria.mbelamova.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.fragments.RegistoDadosFragment;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;

public class LoginActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getApplicationContext());
        if(sharedPreferencesManager.getPassageiro().getContaPassageiro().getPalavra_passe()!=null)
            startActivityNoAnimation(MainActivity.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //AUXILIO
    private void startActivityNoAnimation(Class activityClass){
        Intent i = new Intent(getApplicationContext(), activityClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        overridePendingTransition(0,0);
    }
}
