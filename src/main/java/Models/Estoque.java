/**
 *@author Andrew Nascimento
 */

package Models;

import java.util.ArrayList;

public class Estoque {
    private int codEstoque;
    private int quantidade;
    private ArrayList<Produto> produto;

    public Estoque(int quantidade, ArrayList<Produto> produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }
    
    public Estoque() {
    }

    public int getCodEstoque() {
        return codEstoque;
    }

    public void setCodEstoque(int codEstoque) {
        this.codEstoque = codEstoque;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ArrayList<Produto> getProduto() {
        return produto;
    }

    public void setProduto(ArrayList<Produto> produto) {
        this.produto = produto;
    }
}
