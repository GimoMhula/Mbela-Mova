package mz.co.mm_consultoria.mbelamova.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.SetOptions;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.activites.ModelOnlineActivity;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.Caro;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class RegistoCaroIdentificacoFragment extends Fragment implements View.OnClickListener{
    private FragmentManager fragmentManager;
    private EditTextManager editTextManager;
    private DatabaseManager databaseManager;
    private SharedPreferencesManager sharedPreferencesManager;

    private Button imagemFrontal;
    private Button imagemInterior;
    private FloatingActionButton fab;
    private EditText matricula;

    public RegistoCaroIdentificacoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registo_caro_identificacao, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextManager = new EditTextManager(getContext());
        fragmentManager = new FragmentManager(getContext());
        sharedPreferencesManager = new SharedPreferencesManager(getContext());

        imagemFrontal = view.findViewById(R.id.button_registo_caro_imagem_frontal);
        imagemInterior = view.findViewById(R.id.button_registo_caro_imagem_interior);
        imagemFrontal.setOnClickListener(this);
        imagemInterior.setOnClickListener(this);
        fab = view.findViewById(R.id.fab_registo_caro_identificacao);
        fab.setOnClickListener(this);
        matricula = view.findViewById(R.id.edit_text_registo_caro_matricula);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_registo_caro_imagem_frontal:
                break;
            case R.id.button_registo_caro_imagem_interior:
                break;
            case R.id.fab_registo_caro_identificacao:
                if(!editTextManager.hasEmptyFields(matricula)){
                    sharedPreferencesManager.adicionarCaroIdentificacao(editTextManager.getEditTextString(matricula), null,null);
                    fragmentManager.replaceCurrentFragmentBackStack(getActivity().getSupportFragmentManager(), new RegistoCaroTipo(), R.id.activity_registo_caro_layout);
                }
                break;
        }
    }

}
