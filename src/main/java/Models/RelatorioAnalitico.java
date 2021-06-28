/**
 *@author Andrew Nascimento
 */

package Models;

public class RelatorioAnalitico {
    private int codProduto;
    private String nomeProduto;
    private int quantidadeConprada;
    private double precoUnitario;
    private double precoTotal;
    
    public RelatorioAnalitico(){
    }

    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeConprada() {
        return quantidadeConprada;
    }

    public void setQuantidadeConprada(int quantidadeConprada) {
        this.quantidadeConprada = quantidadeConprada;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }
}
