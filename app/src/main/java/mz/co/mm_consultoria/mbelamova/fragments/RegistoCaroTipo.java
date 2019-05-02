package mz.co.mm_consultoria.mbelamova.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.managers.DatabaseManager;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;

public class RegistoCaroTipo extends Fragment implements View.OnClickListener{
    private Spinner combustivel;
    private FragmentManager fragmentManager;
    private EditTextManager editTextManager;
    private DatabaseManager databaseManager;
    private SharedPreferencesManager sharedPreferencesManager;
    private EditText numero_cilindros;
    private FloatingActionButton fab;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextManager = new EditTextManager(getContext());
        fragmentManager = new FragmentManager(getContext());
        sharedPreferencesManager = new SharedPreferencesManager(getContext());

        numero_cilindros = view.findViewById(R.id.edit_text_caro_numero_cilindros);
        populateDataSpinner(view);
        fab = view.findViewById(R.id.fab_registo_caro_tipo);
        fab.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registo_caro_tipo, container, false);
    }

    private void populateDataSpinner(View view){
        combustivel = view.findViewById(R.id.spinner_registo_caro_combustivel);
        setSpinnerLayout(combustivel, R.array.combustivel);
    }

    private void setSpinnerLayout(Spinner spinner, int array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), array, R.layout.spinner_genero);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_registo_caro_tipo:
                if(!editTextManager.hasEmptyFields(numero_cilindros)){
                    sharedPreferencesManager.adicionarCaroTipo(editTextManager.getEditTextInteger(numero_cilindros), combustivel.getSelectedItem().toString());
                    fragmentManager.replaceCurrentFragmentBackStack(getActivity().getSupportFragmentManager(), new RegistoCaroCarateristicaFragment(), R.id.activity_registo_caro_layout);
                }
                break;
        }
    }
}
