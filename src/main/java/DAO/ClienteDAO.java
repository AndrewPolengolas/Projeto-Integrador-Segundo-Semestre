package DAO;

import utils.GerenciarConexao;
import Models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class ClienteDAO {
    
    /**
     * Metodo que salva o cliente no banco de dados
     * @param c objeto do tipo {@link Models.Cliente}
     * @return boolen - true: Se o cliente foi salvo, false: se ocorrer um erro no salvamento
     */
    public static boolean salvar(Cliente c)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("INSERT INTO cliente (nome, cpf, telefone, endereco, email, sexo) VALUES(?, ?, ?, ?, ?, ?)");
 
            instrucaoSQL.setString(1, c.getNome());
            instrucaoSQL.setString(2, c.getCPFformatado());
            instrucaoSQL.setString(3, c.getTelefoneFormatado());
            instrucaoSQL.setString(4, c.getEndereco());
            instrucaoSQL.setString(5, c.getEmail());
            instrucaoSQL.setString(6, c.getSexo());
            
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
     * Metodo que atualiza um cliente no banco de dados
     * @param c objeto do tipo {@link Models.Cliente}
     * @return boolen - true: Se o cliente foi atualizado, false: se ocorrer um erro na atualização
     */
    public static boolean atualizar(Cliente c)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {

            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("UPDATE cliente SET nome = ?, cpf=?, telefone=?, endereco=?, email=?, sexo=? WHERE cpf =? ");
            
            instrucaoSQL.setString(1, c.getNome());
            instrucaoSQL.setString(2, c.getCPFformatado());
            instrucaoSQL.setString(3, c.getTelefoneFormatado());
            instrucaoSQL.setString(4, c.getEndereco());
            instrucaoSQL.setString(5, c.getEmail());
            instrucaoSQL.setString(6, c.getSexo());
            instrucaoSQL.setString(7, c.getCPFformatado());
            
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
     * Metodo que exclui um cliente por meio de seu CPF
     * @param cpf
     * @return boolen - true: Se o cliente foi excluido, false: se ocorrer um erro na exclusão
     */
    public static boolean excluir(String cpf)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("DELETE FROM cliente WHERE cpf = ?");
            
            instrucaoSQL.setString(1, cpf);

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
     * Metodo que consulta os clientes no banco de dados
     * @return Retorna um ArrayList do tipo {@link Models.Cliente}
     */
    public static ArrayList<Cliente> consultarClientes()
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM cliente;");

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                
                listaClientes.add(c);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaClientes = null;
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
        
        return listaClientes;
    }
    
    /**
     * Metodo que recebe um nome e consulta clientes no banco de dados pelo nome
     * @param nome
     * @return Retorna um ArrayList do tipo {@link Models.Cliente}
     */
    public static ArrayList<Cliente> consultarClientesNome(String nome)
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?;");
            
            instrucaoSQL.setString(1,"%" + nome + '%' );

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("CPF"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                
                listaClientes.add(c);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaClientes = null;
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
        
        return listaClientes;
    }
    
    /**
     * Metodo que recebe um cpf e consulta clientes no banco de dados pelo cpf
     * @param cpf
     * @return Retorna um ArrayList do tipo {@link Models.Cliente}
     */
    public static ArrayList<Cliente> consultarClientesCPF(String cpf)
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE cpf LIKE ?;");
            
            instrucaoSQL.setString(1,"%" + cpf + '%' );

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("CPF"));
                c.setTelefone(rs.getString("telefone"));
                c.setEndereco(rs.getString("endereco"));
                c.setEmail(rs.getString("email"));
                c.setSexo(rs.getString("sexo"));
                
                listaClientes.add(c);
            }
                
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaClientes = null;
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
        
        return listaClientes;
    }
}
