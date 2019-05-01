package mz.co.mm_consultoria.mbelamova.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import mz.co.mm_consultoria.mbelamova.R;
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

    public void adicionarPassageiro(Passageiro passageiro){

    }
    //AUXILIO
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
    private String getString(int string){
        return context.getString(string);
    }
}
