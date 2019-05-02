package mz.co.mm_consultoria.mbelamova.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import mz.co.mm_consultoria.mbelamova.R;
import mz.co.mm_consultoria.mbelamova.models.Caro;
import mz.co.mm_consultoria.mbelamova.models.ContaPassageiro;
import mz.co.mm_consultoria.mbelamova.models.Motorista;
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

    //ADD Motorista
    public void addMotorista(Motorista motorista){
        putData(getMotoristaCaro(), motorista.getCaros().get(0).getId());
        putData(getMotoristaEstado(), motorista.getLocalizacao_atual());
        putData(getMotoristaId(), motorista.getMotorista_id());
        putData(getMotoristaLocalizacao(), motorista.getLocalizacao_atual().toString());
        putData(getMotoristaPermitido(), motorista.isPermitido());
        editorApply();
    }

    public void addMotoristaId(String id) {
        putData(getMotoristaId(), id);
        editorApply();
    }

    //ADD CARO
    public void adicionarCaroLotacao(int lotacao, String modelo, String cor) {
        putData(getCaroLotacao(), lotacao);
        putData(getCaroModelo(), modelo);
        putData(getCaroCor(), cor);
        editorApply();
    }
    public void adicionarCaroTipo(int numeroCilindros, String combustivel){
        putData(getCaroNumeroCilindros(), numeroCilindros);
        putData(getCaroCombustivel(), combustivel);
        editorApply();
    }
    public void adicionarCaroIdentificacao(String matricula, String imagemFrontal, String imagemInterior){
        putData(getCaroMatricula(), matricula);
        putData(getCaroImagemFrontal(), imagemFrontal);
        putData(getCaroImagemInterior(), imagemInterior);
        editorApply();
    }

    //GET CARRO
    public Caro getCarro(){
        Caro caro = new Caro();
        caro.setModelo(getSharedPrefString(getCaroModelo()));
        caro.setLotacao(getSharedPrefInteger(getCaroLotacao()));
        caro.setCombustivel(getSharedPrefString(getCaroCombustivel()));
        caro.setNumeroCiclindros(getSharedPrefInteger(getCaroNumeroCilindros()));
        try{caro.setImagemDentro(getSharedPrefString(getCaroImagemInterior()));}catch (Exception e){}
        try{caro.setImagemFrontal(getSharedPrefString(getCaroImagemFrontal()));}catch (Exception e){}
        caro.setCor(getSharedPrefString(getCaroCor()));
        caro.setMatricula(getSharedPrefString(getCaroMatricula()));
        return caro;
    }

    //ADD PASSAGEIRO
    public void adicionarPassageiro(Passageiro passageiro) {
        ContaPassageiro contaPassageiro = passageiro.getContaPassageiro();
        try {
            putData(getPassageiroNumeroTelefone(), contaPassageiro.getNumeroTelefone());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroPassword(), contaPassageiro.getPalavra_passe());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroNome(), passageiro.getNome());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroApelido(), passageiro.getApelido());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroGenero(), passageiro.getGenero());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroDataNascimento(), passageiro.getDataNascimento());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroSaldoCorrente(), passageiro.getSaldo_corrente());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroSaldoTotal(), passageiro.getSaldo_total());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroPermitido(), passageiro.isPermitido());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroClassificaco(), passageiro.getClassificacao());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroTotalViagens(), passageiro.getNumeroTotalViagens());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroFotoPerfil(), passageiro.getFotoPerfil());
        } catch (Exception e) {
        }
        editorApply();
    }


    public void adicionarPassageiroConta(ContaPassageiro contaPassageiro) {
        try {
            putData(getPassageiroNumeroTelefone(), contaPassageiro.getNumeroTelefone());
        } catch (Exception e) {
        }
        try {
            putData(getPassageiroPassword(), contaPassageiro.getPalavra_passe());
        } catch (Exception e) {
        }
        editorApply();
    }
    public void adicionarPassageiroDocumentId(String id) {
        try {
            putData(getPassageiroDocumentId(), id);
        } catch (Exception e) {
        }
        editor.apply();
    }
    //GET PASSAGEIRO
    public String getPassageiroDocumentIdSharedPrefs() {
        return getSharedPrefString(getPassageiroDocumentId());
    }
    public String getMotoristaDocumentIdSharedPrefs() {
        return getSharedPrefString(getMotoristaId());
    }

    public Passageiro getPassageiro() {
        Passageiro passageiro = new Passageiro();

        ContaPassageiro contaPassageiro = new ContaPassageiro();
        contaPassageiro.setNumeroTelefone(getSharedPrefInteger(getPassageiroNumeroTelefone()));
        contaPassageiro.setPalavra_passe(getSharedPrefString(getPassageiroPassword()));
        passageiro.setContaPassageiro(contaPassageiro);

        passageiro.setNome(getSharedPrefString(getPassageiroNome()));
        passageiro.setApelido(getSharedPrefString(getPassageiroApelido()));
        passageiro.setGenero(getSharedPrefString(getPassageiroGenero()));
        passageiro.setDataNascimento(getSharedPrefString(getPassageiroDataNascimento()));

        passageiro.setSaldo_corrente(getSharedPrefDouble(getPassageiroSaldoCorrente()));
        passageiro.setSaldo_total(getSharedPrefDouble(getPassageiroSaldoTotal()));

        passageiro.setPermitido(getSharedPrefBoolean(getPassageiroPermitido()));
        passageiro.setClassificacao(getSharedPrefDouble(getPassageiroClassificaco()));
        passageiro.setNumeroTotalViagens(getSharedPrefInteger(getPassageiroTotalViagens()));

        return passageiro;
    }
    public Passageiro getNewPassageiro() {
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

    //AUXILIO
    private void editorApply() {
        editor.apply();
    }
    public void clearSharedPreferences() {
        editor.clear();
        editor.apply();
    }
    private void putData(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, value.toString());
        } else if (value instanceof Integer) {
            editor.putInt(key, (int) value);
        } else if (value instanceof Double) {
            editor.putLong(key, Long.parseLong(value.toString()));
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (boolean) value);
        }
    }

    private String getSharedPrefString(String key) {
        return sharedPreferences.getString(key, null);
    }
    private int getSharedPrefInteger(String key) {
        return sharedPreferences.getInt(key, 0);
    }
    private long getSharedPrefDouble(String key) {
        return sharedPreferences.getLong(key, 0);
    }
    private boolean getSharedPrefBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    private String getPassageiroNome() {
        return getString(R.string.passageiro_nome);
    }
    private String getPassageiroFotoPerfil() {return  getString(R.string.passageiro_foto_perfil);}
    private String getPassageiroApelido() {
        return getString(R.string.passageiro_apelido);
    }
    private String getPassageiroGenero() {
        return getString(R.string.passageiro_genero);
    }
    private String getPassageiroDataNascimento() {
        return getString(R.string.passageiro_data_nascimento);
    }
    private String getPassageiroSaldoCorrente() {
        return getString(R.string.passageiro_saldo_corrente);
    }
    private String getPassageiroSaldoTotal() {
        return getString(R.string.passageiro_saldo_corrente);
    }
    private String getPassageiroPermitido() {
        return getString(R.string.passageiro_permitido);
    }
    private String getPassageiroClassificaco() {
        return getString(R.string.passageiro_classificacao);
    }
    private String getPassageiroTotalViagens() {
        return getString(R.string.passageiro_numero_total_viagens);
    }
    private String getPassageiroPassword() {
        return getString(R.string.passageiro_password);
    }
    private String getPassageiroNumeroTelefone() {
        return getString(R.string.passageiro_numeroTelefone);
    }
    public String getPassageiroDocumentId() {
        return getString(R.string.passageiro_document_id);
    }

    public String getCaroCombustivel() {
        return getString(R.string.caro_combustivel);
    }
    public String getCaroImagemFrontal() {
        return getString(R.string.caro_imagem_frontal);
    }
    public String getCaroImagemInterior() {
        return getString(R.string.caro_imagem_dentro);
    }
    public String getCaroCor() {
        return getString(R.string.caro_cor);
    }
    public String getCaroMatricula() {
        return getString(R.string.caro_matricula);
    }
    public String getCaroModelo() {
        return getString(R.string.caro_modelo);
    }
    public String getCaroLotacao() {
        return getString(R.string.caro_lotacao);
    }
    public String getCaroNumeroCilindros() {
        return getString(R.string.caro_numeroCilindros);
    }

    public String getMotoristaId() {
        return getString(R.string.motorista_id);
    }
    public String getMotoristaCaro() {
        return getString(R.string.motorista_caro);
    }
    public String getMotoristaEstado() {
        return getString(R.string.motorista_estado);
    }
    public String getMotoristaLocalizacao() {
        return getString(R.string.motorista_localizacao);
    }
    public String getMotoristaPermitido() {
        return getString(R.string.motorista_localizacao);
    }

    private String getString(int string) {
        return context.getString(string);
    }
}
