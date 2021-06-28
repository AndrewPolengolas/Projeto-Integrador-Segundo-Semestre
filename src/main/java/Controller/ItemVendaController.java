package Controller;

import DAO.ItemVendaDAO;
import Models.ItemVenda;
import Models.Produto;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class ItemVendaController {
    
    /**
     * Metodo que cria um objeto do tipo {@link Models.ItemVenda} e manda para a classe {@link DAO.ItemVendaDAO#salvar}
     * @param cod
     * @param preco
     * @param quantidade
     * @param produto
     * @return boolen - true: se o item venda foi salvo, false: se ocorrer um erro no salvamento
     */
    public static boolean Salvar(int cod, double preco, int quantidade, Produto produto){
        boolean aux, retorno;
        
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setCod_Produto(cod);
        itemVenda.setPreco(preco);
        itemVenda.setQuantidade(quantidade);
        itemVenda.setProduto(produto);
        
        try{
           aux = ItemVendaDAO.salvar(itemVenda);
           if(aux == true){
               return  retorno = true;
           }else{
               return retorno = false;
           }
        }catch(Exception e){
            System.out.println(e);
            return retorno = false;
        }
    }
    
    /**
     * Metodo para executar {@link DAO.ItemVendaDAO#excluir}
     * @return boolen - true: se foram excluidos todos os item venda, false: se ocorrer um erro na exclusão
     */
    public static boolean Excluir(){
        
        boolean aux = ItemVendaDAO.excluir();
        boolean retorno;
        
        if(aux == false){
            return retorno = false;
        }else{
            return retorno = true;
        }
    }
    
    /**
     * Metodo que envia um parametro para executar {@link DAO.ItemVendaDAO#excluirEspecifico}
     * @param cod_item
     * @return boolen - true: se o item venda expecifico foi excluido, false: se ocorrer um erro na exclusão
     */
    public static boolean ExcluirEspecifico(int cod_item){
        
        boolean aux = ItemVendaDAO.excluirEspecifico(cod_item);
        boolean retorno;
        
        if(aux == false){
            return retorno = false;
        }else{
            return retorno = true;
        }
    }
    
    /**
     * Metodo que manda dois parametro para {@link DAO.ItemVendaDAO#atualizar}
     * @param cod_venda
     * @return boolen - true: se o item venda foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean AdicionarVenda(int cod_venda){
        ArrayList<ItemVenda> consultarItens = ConsultaItens();
        
        boolean retorno = false;
        
        try{
            for (ItemVenda item : consultarItens) {
                ItemVendaDAO.atualizar(cod_venda, item.getCod_ItemVenda());
            }
            retorno = true;
        }catch(Exception e){
            System.out.println(e);
            retorno = false;
        }
        return retorno;
    }
    
    /**
     * Metodo que executa {@link DAO.ItemVendaDAO#consultarItemVenda}
     * @return Retorna um ArrayList do tipo {@link Models.ItemVenda}
     */
    public static ArrayList<ItemVenda> ConsultaItens(){
        ArrayList<ItemVenda> listaItens = ItemVendaDAO.consultarItemVenda();
        for (ItemVenda item : listaItens) {
            ArrayList<Produto> prod = ProdutoController.ConsultaProdutoCod(Integer.toString(item.getCod_Produto()));
            item.setProduto(prod.get(0));
        }
        return listaItens;
    }
}
