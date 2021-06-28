/**
 *@author Andrew Nascimento
 */

package Models;

public class ItemVenda {
    private int cod_ItemVenda;
    private int cod_Venda;
    private int cod_Produto;
    private int quantidade;
    private double preco;
    private Produto produto;

    public ItemVenda(Produto produto, int quantidade, double preco) {
        this.produto = new Produto();
    }

    public ItemVenda() {
    }

    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getCod_Produto() {
        return cod_Produto;
    }

    public void setCod_Produto(int cod_Produto) {
        this.cod_Produto = cod_Produto;
    }

    public int getCod_ItemVenda() {
        return cod_ItemVenda;
    }

    public void setCod_ItemVenda(int cod_ItemVenda) {
        this.cod_ItemVenda = cod_ItemVenda;
    }
    
    public double getPrecoTotal(){
        return this.preco * this.quantidade;
    }

}
