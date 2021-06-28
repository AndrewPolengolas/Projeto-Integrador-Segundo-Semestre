package DAO;

import utils.GerenciarConexao;
import Models.Cliente;
import Models.ItemVenda;
import Models.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *@author Andrew Nascimento
 */
public class VendaDAO {
    
    /**
     * Metodo que sauma venda no banco de dados
     * @param v objeto do tipo {@link Models.Venda}
     * @param c objeto do tipo {@link Models.Cliente}
     * @return boolen - true: Se a venda foi salva, false: se ocorrer um erro no salvamento
     */
    public static boolean salvar(Venda v, Cliente c)
    {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null;
                
        try {
            conexao = GerenciarConexao.abrirConexao();

            instrucaoSQL = conexao.prepareStatement("insert into venda (data_venda, fk_cliente_cpf) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
 
            instrucaoSQL.setString(1, v.getDataVendaSQL());
            instrucaoSQL.setString(2, c.getCPFformatado());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            
            if(linhasAfetadas>0)
            {
                retorno = true;
                
                ResultSet generatedKeys = instrucaoSQL.getGeneratedKeys();
                if (generatedKeys.next()) {
                        v.setCodVenda(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Falha ao obter o ID do cliente.");
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
}
