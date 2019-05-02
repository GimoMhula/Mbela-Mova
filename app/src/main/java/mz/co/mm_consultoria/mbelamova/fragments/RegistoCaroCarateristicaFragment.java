package mz.co.mm_consultoria.mbelamova.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.activites.MotoristaActivity;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.Caro;
import mz.co.mm_consultoria.mbelamova.models.Motorista;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class RegistoCaroCarateristicaFragment extends ModelOnlineFragment implements View.OnClickListener{
    private Spinner cor;
    private FragmentManager fragmentManager;
    private EditTextManager editTextManager;
    private DatabaseManager databaseManager;
    private SharedPreferencesManager sharedPreferencesManager;
    private FloatingActionButton fab;
    private EditText lotacao;
    private EditText modelo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseManager = new DatabaseManager(getContext());
        editTextManager = new EditTextManager(getContext());
        fragmentManager = new FragmentManager(getContext());
        sharedPreferencesManager = new SharedPreferencesManager(getContext());

        populateDataSpinner(view);
        fab = view.findViewById(R.id.fab_registo_caro_descricao);
        fab.setOnClickListener(this);
        lotacao = view.findViewById(R.id.edit_text_caro_lotacao);
        modelo = view.findViewById(R.id.edit_text_caro_modelo);
    }

    private void setSpinnerLayout(Spinner spinner, int array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), array, R.layout.spinner_genero);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void populateDataSpinner(View view){
        cor = view.findViewById(R.id.spinner_registo_caro_cor);
        setSpinnerLayout(cor, R.array.cor);
    }

    private void addNewCaro(int lotacao, String modelo) {
        loading();
        sharedPreferencesManager.adicionarCaroLotacao(lotacao, modelo, cor.getSelectedItem().toString());
        databaseManager.addNewCaro(sharedPreferencesManager.getCarro()).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                databaseManager.addNewMotorista(addMotoristaToSharedPrefs(documentReference)).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        sharedPreferencesManager.addMotoristaId(documentReference.getId());
                        startActivityByClass(MotoristaActivity.class);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Falhou no registo do caro.");
                    }
                });
            }

            private Motorista addMotoristaToSharedPrefs(final DocumentReference documentReference) {
                Motorista motorista = new Motorista();

                ArrayList<DocumentReference> caros = new ArrayList<>();
                caros.add(documentReference);

                motorista.setCaros(caros);
                motorista.setMotorista_id(databaseManager.getPassageiroOnlineDocumentReference());
                motorista.setPermitido(true);
                motorista.setEstado(true);
                motorista.setLocalizacao_atual(new GeoPoint(1,1));

                sharedPreferencesManager.addMotorista(motorista);
                return motorista;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                showMessage("Falha na adicao");
            }
        });
    }

    private void startActivityByClass(Class<MotoristaActivity> motoristaActivityClass) {
        Intent i = new Intent(getContext(), motoristaActivityClass);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registo_caro_descricao, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_registo_caro_descricao:
                if(!editTextManager.hasEmptyFields(lotacao, modelo)){
                    addNewCaro(editTextManager.getEditTextInteger(lotacao), editTextManager.getEditTextString(modelo));
                }
                break;
        }
    }
}
