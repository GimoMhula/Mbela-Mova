package mz.co.mm_consultoria.mbelamova.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.QuerySnapshot;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.activites.MainActivity;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.ContaPassageiro;

public class MainFragment extends ModelOnlineFragment implements View.OnClickListener{
    private EditText numeroTelefone;
    private EditText password;
    private CheckBox lembrar;
    private Button entrar;
    private Button registar;
    private EditTextManager editTextManager;
    private FragmentManager fragmentManager;
    private SharedPreferencesManager sharedPreferencesManager;
    private DatabaseManager manager;

    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        manager = new DatabaseManager(getContext());
        
        editTextManager = new EditTextManager(getContext());
        fragmentManager = new FragmentManager(getContext());

        numeroTelefone = view.findViewById(R.id.edit_text_login_numero_telefone);
        password = view.findViewById(R.id.edit_text_login_password);
        lembrar = view.findViewById(R.id.checkbox_login_lembrar);

        entrar = view.findViewById(R.id.button_login_entrar);
        entrar.setOnClickListener(this);
        registar = view.findViewById(R.id.button_login_registar);
        registar.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login_entrar:
                if(!editTextManager.hasEmptyFields(numeroTelefone, password)){
                    int numeroTelef = Integer.parseInt(numeroTelefone.getText().toString());
                    loading();
                    manager.getPassageiroByNumeroTelefone(numeroTelef)
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    if(queryDocumentSnapshots.size()>0){
                                        if(lembrar.isChecked()){
                                            ContaPassageiro contaPassageiro = new ContaPassageiro(Integer.parseInt(numeroTelefone.getText().toString()), password.getText().toString());
                                            sharedPreferencesManager.adicionarPassageiroConta(contaPassageiro);
                                        }
                                        startNewTaskActivity(MainActivity.class);
                                    }else{
                                        showMessage(getString(R.string.text_login_falha));
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    showMessage(getString(R.string.text_registro_falha));
                                }
                            });
                }
                break;
            case R.id.button_login_registar:
                fragmentManager.replaceCurrentFragment(getActivity().getSupportFragmentManager(),new RegistoDadosFragment(), R.id.activity_login_layout);
                break;
        }
    }

    private void startActivityByClass(Class activityClass){
        Intent i = new Intent(getContext(), activityClass);
        startActivity(i);
    }

    private void startNewTaskActivity(Class activityClass){
        Intent i = new Intent(getContext(), activityClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
