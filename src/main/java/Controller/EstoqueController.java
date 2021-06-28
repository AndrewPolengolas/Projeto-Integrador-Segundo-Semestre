package Controller;

import DAO.EstoqueDAO;
import Models.Estoque;
import Models.Produto;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class EstoqueController {
    
    /**
     * Metodo que cria um objeto do tipo {@link Models.Estoque} e um ArrayList do tipo {@link Models.Produto} e manda para a classe {@link DAO.EstoqueDAO#salvar}
     * @param quantidade
     * @param p objeto do tipo {@link Models.Produto}
     * @return boolen - true: se o estoque foi salvo, false: se ocorrer um erro no salvamento
     */
    public static boolean Salvar(String quantidade, Produto p){
        boolean aux, retorno;
        
        Estoque estoque = new Estoque();
        ArrayList<Produto> produto = new ArrayList<>();
        produto.add(p);
        
        estoque.setQuantidade(Integer.parseInt(quantidade));
        estoque.setProduto(produto);
        
        try{
           aux = EstoqueDAO.salvar(estoque, p);
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
     * Metodo que cria um objeto do tipo {@link Models.Estoque} recebendo dois parametros e manda para a classe manda para a classe {@link DAO.EstoqueDAO#atualizar}
     * @param cod
     * @param quantidade
     * @return boolen - true: se o estoque foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean Atualizar(int cod, int quantidade){
        boolean aux, retorno;
        
        Estoque estoque = new Estoque();
        
        estoque.setQuantidade(quantidade);
        
        try{
           aux = EstoqueDAO.atualizar(estoque, cod);
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
     * Metodo que cria um objeto do tipo {@link Models.Estoque} recebendo dois parametros e manda para a classe manda para a classe {@link DAO.EstoqueDAO#ReduzirEstoque}
     * @param cod
     * @param quantidade
     * @return boolen - true: se o estoque foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean ReduzirEstoque(int cod, int quantidade){
        boolean aux, retorno;
        
        Estoque estoque = new Estoque();
        
        estoque.setQuantidade(PesquisaEstoque(cod) - quantidade);
        
        try{
           aux = EstoqueDAO.atualizar(estoque, cod);
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
     * Metodo que cria um objeto do tipo {@link Models.Estoque} recebendo dois parametros e manda para a classe manda para a classe {@link DAO.EstoqueDAO#AumentarEstoque}
     * @param cod
     * @param quantidade
     * @return boolen - true: se o estoque foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean AumentarEstoque(int cod, int quantidade){
        boolean aux, retorno;
        
        Estoque estoque = new Estoque();
        
        estoque.setQuantidade(PesquisaEstoque(cod) + quantidade);
        
        try{
           aux = EstoqueDAO.atualizar(estoque, cod);
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
     * Metodo que manda um parametro para classe {@link DAO.EstoqueDAO#consultarQuantidadeEstoque}
     * @param cod
     * @return retorna um ArraList do tipo {@link Models.Estoque}
     */
    public static int PesquisaEstoque(int cod){
        return EstoqueDAO.consultarQuantidadeEstoque(cod);
    }
}
