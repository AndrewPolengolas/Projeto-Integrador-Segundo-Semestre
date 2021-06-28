package Controller;

import DAO.RelatorioSinteticoDAO;
import Models.RelatorioAnalitico;
import Models.RelatorioSintetico;
import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import utils.ConverterDatas;

/**
 *@author Andrew Nascimento
 */
public class RelatorioSinteticoController {
    
    /**
     * Metodo que recebe uma data inicial e uma data final e manda para a classe {@link DAO.RelatorioSinteticoDAO#Relatorio(java.lang.String, java.lang.String)}
     * @param inicio
     * @param fim
     * @return um ArrayList do tipo {@link Models.RelatorioSintetico}
     */
     public static ArrayList<RelatorioSintetico> ConsultarRelatorios(Date inicio, Date fim){
        ArrayList<RelatorioSintetico> listaVenda = RelatorioSinteticoDAO.Relatorio(ConverterDatas.dataFormatadaBD(inicio), ConverterDatas.dataFormatadaBD(fim));
        
        int v[] = new int[listaVenda.size()];
        
        for (int i = 0; i < listaVenda.size(); i++) {
            v[i] = listaVenda.get(i).getCod_venda();
        }
         
        int[] unicos = arruma(v);
        
        ArrayList<RelatorioSintetico> listaRelatorio = new ArrayList<>();
        RelatorioSintetico relatorio;
        
         for (int i = 0; i < unicos.length; i++) {
            ArrayList<RelatorioSintetico> venda = RelatorioSinteticoDAO.ConsultarVendaUnica(unicos[i]);
            double total = 0;
            
            for (RelatorioSintetico relatorioSintetico : venda) {
                total += relatorioSintetico.getPrecoTotal();
            }
            
            relatorio = new RelatorioSintetico();
            relatorio.setCod_venda(venda.get(0).getCod_venda());
            relatorio.setDataVenda(venda.get(0).getDataVenda());
            relatorio.setNomeCliente(venda.get(0).getNomeCliente());
            relatorio.setPrecoTotal(total);
            
            listaRelatorio.add(relatorio);
         }
         
        return listaRelatorio;
     }
     
     /**
      * Metodo que recebe um vetor e exclui valores repetidos do mesmo
     * @param v de inteiros
     * @return retorna um vetor
      */
     public static int[] arruma(int[] v) {
        int[ ] unicos = new int[ v.length ];
        int qtd = 0;
        for( int i = 0 ; i < v.length ; i++ ) {
            boolean existe = false;
            for( int j = 0 ; j < qtd ; j++ ) {
                if( unicos[ j ] == v[ i ] ) {
                    existe = true;
                    break;
                }
            }
            if( !existe ) {
                unicos[ qtd++ ] = v[ i ];
            }
        }
        unicos = Arrays.copyOf( unicos , qtd );
        return unicos;
    }
     
}
