package br.com.rodolfo.xml.models;

public class Produto {

    private String nome;
    private double preco;

    public Produto() {}

    public Produto(String nome, double preco) {

        this.nome = nome;
        this.preco = preco;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return double return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("Produto : %s - R$%.2f", this.nome, this.preco);
    }

}