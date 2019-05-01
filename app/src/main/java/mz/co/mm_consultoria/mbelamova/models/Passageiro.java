package mz.co.mm_consultoria.mbelamova.models;

public class Passageiro {
    private int numeroTelefone;
    private String palavra_passe;
    private String nome;
    private String apelido;
    private String bi;
    private String morada;
    private String genero;

    //null
    private boolean permitido;
    private double classificacao;
    private int numeroTotalViagens;

    public Passageiro() {
    }

    public Passageiro(int numeroTelefone, String palavra_passe, String nome, String apelido, String bi, String morada, String genero) {
        this.numeroTelefone = numeroTelefone;
        this.palavra_passe = palavra_passe;
        this.nome = nome;
        this.apelido = apelido;
        this.bi = bi;
        this.morada = morada;
        this.genero = genero;
        this.permitido=true;
        this.classificacao=5;
        this.numeroTotalViagens=0;
    }

    public Passageiro(int numeroTelefone, String palavra_passe, String nome, String apelido, String bi, String morada, String genero, boolean permitido, double classificacao, int numeroTotalViagens) {
        this.numeroTelefone = numeroTelefone;
        this.palavra_passe = palavra_passe;
        this.nome = nome;
        this.apelido = apelido;
        this.bi = bi;
        this.morada = morada;
        this.genero = genero;
        this.permitido = permitido;
        this.classificacao = classificacao;
        this.numeroTotalViagens = numeroTotalViagens;
    }

    public int getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(int numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getPalavra_passe() {
        return palavra_passe;
    }

    public void setPalavra_passe(String palavra_passe) {
        this.palavra_passe = palavra_passe;
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

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
