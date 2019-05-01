package mz.co.mm_consultoria.mbelamova.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.models.ContaPassageiro;
import mz.co.mm_consultoria.mbelamova.models.Passageiro;

public class SharedPreferencesManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SharedPreferencesManager(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void adicionarPassageiroDados(Passageiro passageiro){
        putData(getPassageiroNome(), passageiro.getNome());
        putData(getPassageiroApelido(),passageiro.getApelido());
        putData(getPassageiroNome(), passageiro.getNome());
        putData(getPassageiroGenero(), passageiro.getGenero());
        putData(getPassageiroDataNascimento(), passageiro.getDataNascimento());
        editoApply();
    }

    public void adicionarPassageiroConta(ContaPassageiro contaPassageiro){
        putData(getPassageiroNumeroTelefone(), contaPassageiro.getNumeroTelefone());
        putData(getPassageiroPassword(), contaPassageiro.getPalavra_passe());
        editoApply();
    }

    private void putData(String key, Object value){
        if(value instanceof String){
            editor.putString(key, value.toString());
        }else if(value instanceof Integer){
            editor.putInt(key, (int)value);
        }
    }

    private void editoApply(){
        editor.apply();
    }

    public Passageiro getPassageiro(){
        Passageiro passageiro = new Passageiro();

        ContaPassageiro contaPassageiro = new ContaPassageiro();
        contaPassageiro.setNumeroTelefone(getSharedPrefInteger(getPassageiroNumeroTelefone()));
        contaPassageiro.setPalavra_passe(getSharedPrefString(getPassageiroPassword()));

        passageiro.setContaPassageiro(contaPassageiro);

        passageiro.setNome(getSharedPrefString(getPassageiroNome()));
        passageiro.setApelido(getSharedPrefString(getPassageiroApelido()));
        passageiro.setGenero(getSharedPrefString(getPassageiroGenero()));
        passageiro.setDataNascimento(getSharedPrefString(getPassageiroDataNascimento()));

        passageiro.setSaldo_corrente(0);
        passageiro.setSaldo_total(0);

        passageiro.setPermitido(true);
        passageiro.setClassificacao(5);
        passageiro.setNumeroTotalViagens(0);

        return passageiro;
    }

    public void clearSharedPreferences(){
        editor.clear();
        editor.apply();
    }

    private String getSharedPrefString(String key){
        return sharedPreferences.getString(key,null);
    }
    private int getSharedPrefInteger(String key){
        return sharedPreferences.getInt(key,0);
    }

    //AUXILIO
    private String getPassageiroPassword() {
        return getString(R.string.passageiro_genero);
    }
    private String getPassageiroGenero() {
        return getString(R.string.passageiro_genero);
    }
    private String getPassageiroNome(){
        return getString(R.string.passageiro_nome);
    }
    private String getPassageiroApelido(){return getString(R.string.passageiro_apelido);}
    private String getPassageiroNumeroTelefone(){
        return getString(R.string.passageiro_numeroTelefone);
    }
    private String getPassageiroSaldoCorrente(){
        return getString(R.string.passageiro_saldo_corrente);
    }
    private String getPassageiroDataNascimento(){
        return getString(R.string.passageiro_data_nascimento);
    }
    private String getString(int string){
        return context.getString(string);
    }
}
