package mz.co.mm_consultoria.mbelamova.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.managers.EditTextManager;
import mz.co.mm_consultoria.mbelamova.managers.FragmentManager;
import mz.co.mm_consultoria.mbelamova.managers.SharedPreferencesManager;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class RegistoDadosFragment extends Fragment implements View.OnClickListener{

    private final FragmentManager fragmentManager;
    private EditText nome;
    private EditText apelido;
    private Spinner generoSpinner;
    private DatePicker dateSpinner;
    private FloatingActionButton fab;
    private EditTextManager editTextManager;
    private SharedPreferencesManager sharedPreferencesManager;

    public RegistoDadosFragment() {
        fragmentManager = new FragmentManager(getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextManager = new EditTextManager(getContext());
        sharedPreferencesManager = new SharedPreferencesManager(getContext());
        sharedPreferencesManager.clearSharedPreferences();

        nome = view.findViewById(R.id.edit_text_registo_dados_nome);
        apelido = view.findViewById(R.id.edit_text_registo_dados_apelido);
        populateDataSpinner(view);
        dateSpinner = view.findViewById(R.id.date_picker_registo_dados_data_nascimento);
        fab = view.findViewById(R.id.fab_registo_dados);
        fab.setOnClickListener(this);
    }

    public void populateDataSpinner(View view){
        generoSpinner = view.findViewById(R.id.spinner_registo_dados_genero);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getView().getContext(), R.array.genero, R.layout.spinner_genero);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        generoSpinner.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_registo_dados, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_registo_dados:
                if(!editTextManager.hasEmptyFields(nome, apelido)){
                    String dataNascimento = dateSpinner.getDayOfMonth()+"/"+dateSpinner.getMonth()+"/"+dateSpinner.getYear();
                    Passageiro passageiro  = new Passageiro(editTextManager.getEditTextString(nome), editTextManager.getEditTextString(apelido), generoSpinner.getSelectedItem().toString(), dataNascimento);
                    sharedPreferencesManager.adicionarPassageiroDados(passageiro);
                    fragmentManager.replaceCurrentFragment(getActivity().getSupportFragmentManager(), new RegistoContaFragment(), R.id.activity_login_layout);
                }
                break;
        }
    }
}
