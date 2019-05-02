package mz.co.mm_consultoria.mbelamova.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.activites.MainActivity;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.ContaPassageiro;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class LoginMainFragment extends ModelOnlineFragment implements View.OnClickListener{
    private EditText numeroTelefone;
    private EditText password;
    private CheckBox lembrar;
    private Button entrar;
    private Button registar;
    private EditTextManager editTextManager;
    private FragmentManager fragmentManager;
    private SharedPreferencesManager sharedPreferencesManager;
    private DatabaseManager manager;

    public LoginMainFragment() {
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

        entrar = view.findViewById(R.id.button_login_entrar);
        entrar.setOnClickListener(this);
        registar = view.findViewById(R.id.button_login_registar);
        registar.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_main, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_login_entrar:
                if(!editTextManager.hasEmptyFields(numeroTelefone, password)){
                    ContaPassageiro contaPassageiro = new ContaPassageiro(Integer.parseInt(editTextManager.getEditTextString(numeroTelefone)), editTextManager.getEditTextString(password));
                    startLogin(contaPassageiro);
                }
                break;
            case R.id.button_login_registar:
                fragmentManager.replaceCurrentFragmentBackStack(getActivity().getSupportFragmentManager(),new RegistoDadosFragment(), R.id.activity_login_layout);
                break;
        }
    }

    private void startLogin(ContaPassageiro contaPassageiro) {
        updateUI(2);
        if(hasInternetConnection()){
            manager.getPassageiroByConta(contaPassageiro)
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                if(task.getResult().getDocuments().size()>0){
                                    DocumentSnapshot snapshot = task.getResult().getDocuments().get(0);
                                    Passageiro passageiro = snapshot.toObject(Passageiro.class);
                                    sharedPreferencesManager.adicionarPassageiro(passageiro);
                                    sharedPreferencesManager.adicionarPassageiroDocumentId(snapshot.getId());
                                    startNewTaskActivity(MainActivity.class);
                                }else{
                                    updateUI(0);
                                }
                            }else{
                                updateUI(0);
                            }
                        }
                    });
        }else{
            updateUI(1);
        }
    }

    private void updateUI(int estado){
        switch (estado){
            case 0:
                showMessage(getString(R.string.text_login_falha));
                break;
            case 1:
                showMessage(getString(R.string.no_internet_conection));
                break;
            case 2:
                loading();
                break;
        }
    }

    private void startNewTaskActivity(Class activityClass){
        Intent i = new Intent(getContext(), activityClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
