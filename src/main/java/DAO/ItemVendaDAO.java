package DAO;

import Controller.ProdutoController;
import Models.ItemVenda;
import Models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.GerenciarConexao;

/**
 *@author Andrew Nascimento
 */
public class ItemVendaDAO {
    
    /**
     * Metodo que recebe e salva o item venda no banco de dados
     * @param item objeto do tipo {@link Models.ItemVenda}
     * @return boolen - true: Se o item venda foi salvo, false: se ocorrer um erro no salvamento
     */
    public static boolean salvar(ItemVenda item) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into itemVenda (preco, quantidade, fk_cod_produto) values (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
 
            instrucaoSQL.setDouble(1, item.getPrecoTotal());
            instrucaoSQL.setInt(2, item.getQuantidade());
            instrucaoSQL.setInt(3, item.getCod_Produto());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0){
                retorno = true;
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                        item.setCod_ItemVenda(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao obter o Código do item.");
                }
            }else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
    
    /**
     * Metodo que atualiza o item venda no banco de dados fazendo referencia a qual venda pertence
     * @param cod_venda
     * @param codItem
     * @return boolen - true: Se o item venda foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean atualizar(int cod_venda, int codItem){
         
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {

            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE itemvenda SET fk_cod_venda = ? where cod_itemvenda = ?");
            
            instrucaoSQL.setInt(1, cod_venda);
            instrucaoSQL.setInt(2, codItem);
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
    
    /**
     * Metodo que exclui os itens venda e faz backup para uma nova tabela no banco de dados
     * @return boolen - true: Se o item venda foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean excluir(){
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("DELETE FROM itemvenda");
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
    
    /**
     * Metodo que exclui um item especifico e faz backup para uma nova tabela no banco de dados
     * @param cod_item
     * @return boolen - true: Se o item venda foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean excluirEspecifico(int cod_item){
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("DELETE FROM itemvenda where cod_itemvenda = ?");
            
            instrucaoSQL.setInt(1, cod_item);
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
            }
            else{
                retorno = false;
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            retorno = false;
        } finally{
            
            try {
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                
              } catch (SQLException ex) {
             }
        }
        
        return retorno;
    }
    
    /**
     * Metodo que faz uma consulta no banco de dados de todos os itens venda
     * @return Retorna num ArrayList do tipo {@link Models.ItemVenda}
     */
     public static ArrayList<ItemVenda> consultarItemVenda()
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<ItemVenda> listaItens = new ArrayList<ItemVenda>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM itemvenda;");

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                ItemVenda c = new ItemVenda();
                c.setPreco(rs.getDouble("preco"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setCod_Produto(rs.getInt("fk_cod_produto"));
                c.setCod_ItemVenda(rs.getInt("cod_itemvenda"));
                listaItens.add(c);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaItens = null;
        } finally{
            try {
                if(rs!=null)
                    rs.close();                
                if(instrucaoSQL!=null)
                    instrucaoSQL.close();
                
                GerenciarConexao.fecharConexao();
                        
              } catch (SQLException ex) {
             }
        }
        
        return listaItens;
    }

}
