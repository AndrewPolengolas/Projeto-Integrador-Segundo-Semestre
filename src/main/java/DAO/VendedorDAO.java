package DAO;

import Models.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.GerenciarConexao;

/**
 *@author Andrew Nascimento
 */
public class VendedorDAO {
    
    /**
     * Metodo que faz uma consulta dos vendedores no banco de dados
     * @return Retorna um ArrayList do tipo {@link Models.Vendedor}
     */
    public static ArrayList<Vendedor> ConsultarVendedores()
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("SELECT * FROM vendedor;");

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                Vendedor v = new Vendedor();
                v.setNome(rs.getString("nome"));
                v.setSenha(rs.getString("senha"));
                
                listaVendedores.add(v);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            listaVendedores = null;
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
        
        return listaVendedores;
    }
}
