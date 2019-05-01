package mz.co.mm_consultoria.mbelamova.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.activites.LoginActivity;
import mz.co.mm_consultoria.mbelamova.activites.MainActivity;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.ContaPassageiro;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class RegistoContaFragment extends ModelOnlineFragment implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private View fab;
    private EditTextManager editTextManager;
    private EditText numeroTelefone;
    private EditText palavra_passe;
    private DatabaseManager databaseManager;
    private SharedPreferencesManager sharedPreferencesManager;

    public RegistoContaFragment() {
        fragmentManager = new FragmentManager(getContext());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextManager = new EditTextManager(getContext());
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        databaseManager = new DatabaseManager(getContext());
        
        numeroTelefone = view.findViewById(R.id.edit_text_registo_conta_numero);
        palavra_passe = view.findViewById(R.id.edit_text_registo_conta_password);

        fab = view.findViewById(R.id.fab_registo_conta);
        fab.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_registo_conta, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_registo_conta:
                if(!editTextManager.hasEmptyFields(numeroTelefone, palavra_passe)){
                    ContaPassageiro contaPassageiro = new ContaPassageiro(editTextManager.getEditTextInteger(numeroTelefone), editTextManager.getEditTextString(palavra_passe));
                    sharedPreferencesManager.adicionarPassageiroConta(contaPassageiro);

                    if(hasInternetConnection()){
                        Passageiro passageiro = sharedPreferencesManager.getPassageiro();
                        loading();
                        databaseManager.addPassageiro(passageiro)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivityByClass(MainActivity.class);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        sharedPreferencesManager.clearSharedPreferences();
                                        showMessage(getString(R.string.text_registro_falha));
                                        startActivityByClass(LoginActivity.class);
                                    }
                                });
                    }else {
                        showMessage(getString(R.string.no_internet_conection));
                    }
                }
                break;
        }
    }

    private void startActivityByClass(Class activityClass){
        Intent i = new Intent(getActivity().getApplicationContext(), activityClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
