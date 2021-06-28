
package Controller;

import DAO.RelatorioAnaliticoDAO;
import Models.RelatorioAnalitico;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class RelatorioAnaliticoController {
    
    /**
     * Metodo que manda um parametro para a classe {@link DAO.RelatorioAnaliticoDAO#ConsultarItensVenda(int)}
     * @param cod_venda
     * @return Retorna um ArrayList do tipo {@link Models.RelatorioAnalitico}
     */
    public static ArrayList<RelatorioAnalitico> ConsultarItens(int cod_venda){
         ArrayList<RelatorioAnalitico> relatorioAnalitico = RelatorioAnaliticoDAO.ConsultarItensVenda(cod_venda);
         return relatorioAnalitico;
     }
}
