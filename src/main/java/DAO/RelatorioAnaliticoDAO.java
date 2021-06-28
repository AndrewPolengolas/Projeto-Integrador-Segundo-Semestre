package DAO;

import Models.RelatorioAnalitico;
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
public class RelatorioAnaliticoDAO {
    
    /**
     * Metodo que recebe um codigo de venda e faz a pesquisa das vendas no banco de dados
     * @param cod
     * @return Retorna um ArrayList do tipo {@link Models.RelatorioAnalitico}
     */
    public static ArrayList<RelatorioAnalitico> ConsultarItensVenda(int cod){
        ResultSet rs = null;
        Connection conexao = null;
        PreparedStatement instrucaoSQL = null; 
        
        ArrayList<RelatorioAnalitico> relatorioAnalitico = new ArrayList<>();
        
        try {
            
            conexao = GerenciarConexao.abrirConexao();
            instrucaoSQL = conexao.prepareStatement("select produto_bkp.cod, produto_bkp.nome, itemvenda_bkp.quantidade, produto_bkp.preco, itemvenda_bkp.preco from itemvenda_bkp inner join produto_bkp on produto_bkp.cod = itemvenda_bkp.fk_cod_produto where fk_cod_venda = ?;");
            
            instrucaoSQL.setInt(1, cod);

            rs = instrucaoSQL.executeQuery();
            
            while(rs.next())
            {
                RelatorioAnalitico relatorio = new RelatorioAnalitico();
                relatorio.setCodProduto(rs.getInt("produto_bkp.cod"));
                relatorio.setNomeProduto(rs.getString("produto_bkp.nome"));
                relatorio.setQuantidadeConprada(rs.getInt("itemvenda_bkp.quantidade"));
                relatorio.setPrecoUnitario(rs.getDouble("produto_bkp.preco"));
                relatorio.setPrecoTotal(rs.getDouble("itemvenda_bkp.preco"));
                relatorioAnalitico.add(relatorio);
            }
            
        }catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            relatorioAnalitico = null;
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
        
        return relatorioAnalitico;
    }
}
