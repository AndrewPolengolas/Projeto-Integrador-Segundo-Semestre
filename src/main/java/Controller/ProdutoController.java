package Controller;

import DAO.ProdutoDAO;
import Models.Produto;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class ProdutoController {
    
    /**
     * Método que cria um objeto da classe {@link Models.Produto} e manda para classe {@link DAO.ProdutoDAO#salvar }
     * @param nome
     * @param preco
     * @param quantidade
     * @return boolen - true: Se o produto foi salvo, false: se ocorrer um erro no salvamento
     */
    public static boolean Salvar(String nome, String preco, String quantidade){
        boolean aux, retorno;
        
        Produto produto = new Produto();
        String pre = preco.replace(".", "");
        
        produto.setNome(nome);
        produto.setPreco(Double.parseDouble(pre.replace(",", ".")));
        
        try{
           aux = ProdutoDAO.salvar(produto);
           if(aux == true){
               EstoqueController.Salvar(quantidade, produto);
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
     * Método que cria um objeto da classe {@link Models.Produto} e manda para classe {@link DAO.ProdutoDAO#atualizar }
     * @param cod
     * @param nome
     * @param preco
     * @param quantidade
     * @return boolean - true: se o produto for atualizado, false: se ocorrer um erro ao atualizar
     */
    public static boolean Atualizar(int cod, String nome, String preco, String quantidade){
        boolean aux, retorno;
        
        Produto produto = new Produto();
        
        produto.setCod(cod);
        produto.setNome(nome);
        produto.setPreco(Double.parseDouble(preco.replace(",", ".")));
        
        try{
           aux = ProdutoDAO.atualizar(produto);
           EstoqueController.Atualizar(produto.getCod(), Integer.parseInt(quantidade));
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
     * Método que recebe uma string cod manda para classe {@link DAO.ProdutoDAO#excluir} {@link DAO.ProdutoDAO#excluirEstoque}
     * @param cod
     * @return boolean - ture: se o produto for excluido, false: se ocorrer um erro na exclusão
     */
    public static boolean Excluir(String cod){
        
        boolean aux = ProdutoDAO.excluirEstoque(cod);
        boolean aux2 = ProdutoDAO.excluir(cod);
        boolean aux3 = aux && aux2;
        
        boolean retorno;
        
        if(aux3 == false){
            return retorno = false;
        }else{
            return retorno = true;
        }
    }
    
    /**
     * Recebe uma string e manda para a classe {@link DAO.ProdutoDAO#consultarProdutosNome }
     * @param nome
     * @return retorna um ArrayList do tipo {@link Models.Produto} 
     */    
    public static ArrayList<Produto> ConsultaProdutoNome(String nome){
        ArrayList<Produto> listaProdutos = ProdutoDAO.consultarProdutosNome(nome);
        return listaProdutos;
    }

    /**
     * Recebe uma string e manda para a classe {@link DAO.ProdutoDAO#consultarProdutoCod }
     * @param cod
     * @return retorna um ArrayList do tipo {@link Models.Produto} 
     */
    public static ArrayList<Produto> ConsultaProdutoCod(String cod){
        ArrayList<Produto> listaProdutos = ProdutoDAO.consultarProdutoCod(cod);
        return listaProdutos;
    }
    
    /**
     * Faz uma chamada direta da classe {@link DAO.ProdutoDAO#consultarProdutos }
     * @return retorna um ArrayList do tipo {@link Models.Produto}
     */
    public static ArrayList<Produto> ConsultaProduto(){
        ArrayList<Produto> listaProdutos = ProdutoDAO.consultarProdutos();
        return listaProdutos;
    }
}
