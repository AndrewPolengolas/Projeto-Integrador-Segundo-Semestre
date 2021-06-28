/**
 *@author Andrew Nascimento
 */

package Models;

public class Produto {
    private int cod;
    private String nome;
    private double preco;

    public Produto() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getPrecoFormatado(){
        String retorno;
        return retorno = Double.toString(this.preco).replace(".", ",");
    }
}
