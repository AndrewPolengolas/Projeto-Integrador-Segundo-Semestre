package DAO;

import Models.Estoque;
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
public class EstoqueDAO {
    
    /**
     * Metodo que recebe um produto e um estoque e adiciona ao banco de dados
     * @param estoque objeto do tipo {@link Models.Estoque}
     * @param p objeto do tipo {@link Models.Produto}
     * @return boolen - true: Se o estoque foi salvo, false: se ocorrer um erro no salvamento
     */
        public static boolean salvar(Estoque estoque, Produto p){
            boolean retorno = false;
            Connection conexao = null;
            PreparedStatement instrucaoSQL = null;

            try {
                conexao = GerenciarConexao.abrirConexao();

                instrucaoSQL = conexao.prepareStatement("INSERT INTO estoque(cod_estoque, quantidade_estoque, fk_cod_produto) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                instrucaoSQL.setInt(1, estoque.getCodEstoque());
                instrucaoSQL.setInt(2, estoque.getQuantidade());
                instrucaoSQL.setDouble(3, p.getCod());

                int linhasAfetadas = instrucaoSQL.executeUpdate();

                if(linhasAfetadas>0){
                    retorno = true;
                     
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                        estoque.setCodEstoque(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao obter o Código do produto.");
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
     * Metodo que recebe um estoque e um codigo de produto e atualiza no banco de dados
     * @param estoque objeto do tipo {@link Models.Estoque}
     * @param cod
     * @return boolen - true: Se o estoque foi salvo, false: se ocorrer um erro no salvamento
    */
     public static boolean atualizar(Estoque estoque, int cod){
         
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {

            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE estoque SET quantidade_estoque = ? WHERE fk_cod_produto = ?");
            
            instrucaoSQL.setInt(1, estoque.getQuantidade());
            instrucaoSQL.setInt(2, cod);
            
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
     * Metodo que recebe um estoque e um codigo de produto e atualiza no banco de dados
     * @param estoque objeto do tipo {@link Models.Estoque}
     * @param cod
     * @return boolen - true: Se o estoque foi salvo, false: se ocorrer um erro no salvamento
    */
     public static boolean ReduzirEstoque(Estoque estoque, int cod){
         
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {

            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE estoque SET quantidade_estoque = ? WHERE fk_cod_produto = ?");
            
            instrucaoSQL.setInt(1, estoque.getQuantidade());
            instrucaoSQL.setInt(2, cod);
            
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
     * Metodo que recebe um estoque e um codigo de produto e atualiza no banco de dados
     * @param estoque objeto do tipo {@link Models.Estoque}
     * @param cod
     * @return boolen - true: Se o estoque foi salvo, false: se ocorrer um erro no salvamento
    */
     public static boolean AumentarEstoque(Estoque estoque, int cod){
         
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {

            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE estoque SET quantidade_estoque = ? WHERE fk_cod_produto = ?");
            
            instrucaoSQL.setInt(1, estoque.getQuantidade());
            instrucaoSQL.setInt(2, cod);
            
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
     * Metodo que consulta a quantidade de produtos em estoque no banco de dados
     * @param cod
     * @return Retorna a quantidade em estoque
    */
     public static int consultarQuantidadeEstoque(int cod)
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Estoque> listaEstoque = new ArrayList<Estoque>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select quantidade_estoque from estoque where fk_cod_produto = ?");
            
            instrucaoSQL.setInt(1, cod);

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Estoque e = new Estoque();
                e.setQuantidade(rs.getInt("quantidade_estoque"));
                listaEstoque.add(e);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaEstoque = null;
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
        
        return listaEstoque.get(0).getQuantidade();
    }
}
