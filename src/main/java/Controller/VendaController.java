package Controller;

import DAO.VendaDAO;
import Models.Cliente;
import Models.Venda;
import java.util.Date;

/**
 *@author Andrew Nascimento
 */
public class VendaController {
    
    /**
     * Metodo que recebe um objeto do tipo {@link Models.Cliente} e uma data e manda para a classe {@link DAO.VendaDAO#salvar(Models.Venda, Models.Cliente)}
     * @param cliente do tipo {@link Models.Cliente}
     * @param data
     * @return boolen - true: Se a venda foi salva, false: se ocorrer um erro no salvamento
     */
    public static boolean Salvar(Cliente cliente, Date data){
        boolean aux, retorno;
        
        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(data);
        
        try{
           aux = VendaDAO.salvar(venda, cliente);
           if(aux == true){
               ItemVendaController.AdicionarVenda(venda.getCodVenda());
               return  retorno = true;
           }else{
               return retorno = false;
           }
        }catch(Exception e){
            System.out.println(e);
            return retorno = false;
        }
    }
}
