package mz.co.mm_consultoria.mbelamova.models;

public class ContaPassageiro {
    private int numeroTelefone;
    private String palavra_passe;

    public ContaPassageiro() {
    }

    public ContaPassageiro(int numeroTelefone, String palavra_passe) {
        this.numeroTelefone = numeroTelefone;
        this.palavra_passe = palavra_passe;
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
}
