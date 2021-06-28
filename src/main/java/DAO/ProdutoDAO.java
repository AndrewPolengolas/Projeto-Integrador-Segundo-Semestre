package DAO;

import utils.GerenciarConexao;
import Models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class ProdutoDAO {
    
    /**
     * Metodo que salva o produto no banco de dados
     * @param p objeto do tipo {@link Models.Produto}
     * @return boolen - true: Se o produto foi salvo, false: se ocorrer um erro no salvamento
     */
    public static boolean salvar(Produto p)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("INSERT INTO produto(nome, preco) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
 
            instrucaoSQL.setString(1, p.getNome());
            instrucaoSQL.setDouble(2, p.getPreco());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                        p.setCod(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao obter o Código do produto.");
                }
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
     * Metodo que atualiza um produto no banco de dados
     * @param p objeto do tipo {@link Models.Produto}
     * @return boolen - true: Se o produto foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean atualizar(Produto p)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {

            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE produto SET nome = ?, preco=? WHERE cod =? ");
            
            instrucaoSQL.setString(1, p.getNome());
            instrucaoSQL.setDouble(2, p.getPreco());
            instrucaoSQL.setInt(3, p.getCod());
            
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
     * Metodo que exclui um produto por meio de seu cod
     * @param cod
     * @return boolen - true: Se o produto foi excluido, false: se ocorrer um erro na exclusão
     */
    public static boolean excluir(String cod)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("DELETE FROM produto WHERE cod = ?");
            
            instrucaoSQL.setString(1, cod);

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
     * Metodo que exclui um estoque referenciando o cod do produto
     * @param cod
     * @return boolen - true: Se o estoque foi excluido, false: se ocorrer um erro na exclusão
     */
    public static boolean excluirEstoque(String cod)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("DELETE FROM estoque WHERE fk_cod_produto = ?");
            
            instrucaoSQL.setString(1, cod);

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
     * Metodo que consulta os produtos no banco de dados
     * @return Retorna um ArrayList do tipo {@link Models.Produto}
     */
    public static ArrayList<Produto> consultarProdutos()
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM produto;");

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Produto p = new Produto();
                p.setCod(rs.getInt("cod"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                listaProdutos.add(p);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaProdutos = null;
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
        
        return listaProdutos;
    }
    
    /**
     * Metodo que recebe um nome e consulta produtos no banco de dados pelo nome
     * @param nome
     * @return Retorna um ArrayList do tipo {@link Models.Cliente}
     */
    public static ArrayList<Produto> consultarProdutosNome(String nome)
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?;");
            
            instrucaoSQL.setString(1,"%" + nome + '%' );

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Produto p = new Produto();
                p.setCod(rs.getInt("cod"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                listaProdutos.add(p);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaProdutos = null;
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
        
        return listaProdutos;
    }
    
    /**
     * Metodo que recebe um cod e consulta produtos no banco de dados pelo cod
     * @param cod
     * @return Retorna um ArrayList do tipo {@link Models.Cliente}
     */
    public static ArrayList<Produto> consultarProdutoCod(String cod)
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM produto WHERE cod LIKE ?;");
            
            instrucaoSQL.setString(1, cod);

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Produto p = new Produto();
                p.setCod(rs.getInt("cod"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                listaProdutos.add(p);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaProdutos = null;
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
        
        return listaProdutos;
    }
}
