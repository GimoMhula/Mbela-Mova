package mz.co.mm_consultoria.mbelamova.models;

import com.google.firebase.firestore.DocumentReference;

public class Passageiro {
    private ContaPassageiro contaPassageiro;
    private String nome;
    private String apelido;
    private String genero;
    private String dataNascimento;

    private double saldo_corrente;
    private double saldo_total;
    //null
    private boolean permitido;
    private double classificacao;
    private int numeroTotalViagens;

    public Passageiro() {
    }

    public Passageiro(String nome, String apelido, String genero, String dataNascimento) {
        this.nome = nome;
        this.apelido = apelido;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
    }

    public Passageiro(ContaPassageiro contaPassageiro, String nome, String apelido, String genero, String dataNascimento, double saldo_corrente, double saldo_total, boolean permitido, double classificacao, int numeroTotalViagens) {
        this.contaPassageiro = contaPassageiro;
        this.nome = nome;
        this.apelido = apelido;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.saldo_corrente = saldo_corrente;
        this.saldo_total = saldo_total;
        this.permitido = permitido;
        this.classificacao = classificacao;
        this.numeroTotalViagens = numeroTotalViagens;
    }

    public ContaPassageiro getContaPassageiro() {
        return contaPassageiro;
    }

    public void setContaPassageiro(ContaPassageiro contaPassageiro) {
        this.contaPassageiro = contaPassageiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getSaldo_corrente() {
        return saldo_corrente;
    }

    public void setSaldo_corrente(double saldo_corrente) {
        this.saldo_corrente = saldo_corrente;
    }

    public double getSaldo_total() {
        return saldo_total;
    }

    public void setSaldo_total(double saldo_total) {
        this.saldo_total = saldo_total;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public int getNumeroTotalViagens() {
        return numeroTotalViagens;
    }

    public void setNumeroTotalViagens(int numeroTotalViagens) {
        this.numeroTotalViagens = numeroTotalViagens;
    }
}
