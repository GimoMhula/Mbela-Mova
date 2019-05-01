package mz.co.mm_consultoria.mbelamova.models;

public class Combustivel {
    private String tipo;
    private double preco;
    private double volume;

    public Combustivel() {
    }

    public Combustivel(String tipo, double preco, double volume) {
        this.tipo = tipo;
        this.preco = preco;
        this.volume = volume;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
