package mz.co.mm_consultoria.mbelamova.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText numeroTelefone;
    private EditText password;
    private CheckBox lembrar;
    private ImageButton entrar;
    private Button registar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createViews();
    }

    private void createViews() {
        EditTextManager editTextManager = new EditTextManager(getApplicationContext());

        numeroTelefone = findViewById(R.id.edit_text_login_numero_telefone);
        password = findViewById(R.id.edit_text_login_password);
//        lembrar = findViewById(R.id.checkbox_login_lembrar);

        createAndSetListener(entrar, R.id.button_login_entrar);
        createAndSetListener(registar, R.id.button_login_registar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login_entrar:
                startActivityByClass(MainActivity.class);
                break;
        }
    }

    //AUXILIO
    private void createAndSetListener(View view, int id){
        view = findViewById(id);
        view.setOnClickListener(this);
    }
    private void startActivityByClass(Class activityClass){
        Intent i = new Intent(getApplicationContext(), activityClass);
        startActivity(i);
    }
}
