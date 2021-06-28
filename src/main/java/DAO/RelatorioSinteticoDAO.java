package DAO;

import Models.RelatorioSintetico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import utils.GerenciarConexao;

/**
 *@author Andrew Nascimento
 */
public class RelatorioSinteticoDAO {
    
    /**
     * Metodo que recebe uma data inicio e uma data fim e gera um relatorio de acordo com as datas fornecidas
     * @param inicio
     * @param fim
     * @return retorna um Array do tipo {@link Models.RelatorioSintetico} 
     */
    public static ArrayList<RelatorioSintetico> Relatorio(String inicio, String fim)
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<RelatorioSintetico> relatorioSintetico = new ArrayList<>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select * from relatorio_sintetico where data_venda BETWEEN (?) AND (?);");
            
            instrucaoSQL.setString(1, inicio);
            instrucaoSQL.setString(2, fim);

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next()){
                RelatorioSintetico relatorio = new RelatorioSintetico();
                relatorio.setCod_venda(rs.getInt("cod_venda"));
                relatorio.setDataVenda(rs.getDate("data_venda"));
                relatorio.setNomeCliente(rs.getString("nome"));
                relatorio.setPrecoTotal(rs.getDouble("preco"));
                relatorioSintetico.add(relatorio);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            relatorioSintetico = null;
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
        
        return relatorioSintetico;
    }
    
    /**
     * Metodo que recebe um codigo de venda e faz uma consulta de venda no banco de dados
     * @param cod
     * @return Retorna um ArrayList do tipo {@link Models.RelatorioSintetico}
     */
     public static ArrayList<RelatorioSintetico> ConsultarVendaUnica(int cod)
    {
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<RelatorioSintetico> relatorioSintetico = new ArrayList<>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select cod_venda, data_venda, nome, preco from venda inner join cliente_bkp on fk_cliente_cpf = cpf inner join itemvenda_bkp on fk_cod_venda = cod_venda where cod_venda = ?;");
            
            instrucaoSQL.setInt(1, cod);

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                RelatorioSintetico relatorio = new RelatorioSintetico();
                relatorio.setCod_venda(rs.getInt("cod_venda"));
                relatorio.setDataVenda(rs.getDate("data_venda"));
                relatorio.setNomeCliente(rs.getString("nome"));
                relatorio.setPrecoTotal(rs.getDouble("preco"));
                relatorioSintetico.add(relatorio);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            relatorioSintetico = null;
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
        
        return relatorioSintetico;
    }
     
}
