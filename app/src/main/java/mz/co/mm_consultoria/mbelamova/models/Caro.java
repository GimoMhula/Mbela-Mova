package mz.co.mm_consultoria.mbelamova.models;

public class Caro {
    private String matricula;
    private String combustivel;
    private String imagemFrontal;
    private String imagemDentro;
    private String cor;
    private String modelo;
    private int lotacao;
    private int numeroCiclindros;

    public Caro() {
    }

    public Caro(String combustivel, String imagemFrontal, String imagemDentro, String cor) {
        this.combustivel = combustivel;
        this.imagemFrontal = imagemFrontal;
        this.imagemDentro = imagemDentro;
        this.cor = cor;
    }

    public Caro(String matricula, String combustivel, String imagemFrontal, String imagemDentro, String cor, String modelo, int lotacao, int numeroCiclindros) {
        this.matricula = matricula;
        this.combustivel = combustivel;
        this.imagemFrontal = imagemFrontal;
        this.imagemDentro = imagemDentro;
        this.cor = cor;
        this.modelo = modelo;
        this.lotacao = lotacao;
        this.numeroCiclindros = numeroCiclindros;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getImagemFrontal() {
        return imagemFrontal;
    }

    public void setImagemFrontal(String imagemFrontal) {
        this.imagemFrontal = imagemFrontal;
    }

    public String getImagemDentro() {
        return imagemDentro;
    }

    public void setImagemDentro(String imagemDentro) {
        this.imagemDentro = imagemDentro;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public int getNumeroCiclindros() {
        return numeroCiclindros;
    }

    public void setNumeroCiclindros(int numeroCiclindros) {
        this.numeroCiclindros = numeroCiclindros;
    }
}
