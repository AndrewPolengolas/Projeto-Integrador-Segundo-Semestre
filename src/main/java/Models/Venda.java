/**
 *@author Andrew Nascimento
 */

package Models;

import java.util.Date;
import java.util.ArrayList;
import utils.ConverterDatas;

public class Venda {
    private Date dataVenda;
    private int codVenda;
    private Cliente cliente;

    public Venda() {
    }

    public String getDataVenda() {
        ConverterDatas.dataFormatada(dataVenda);
        return ConverterDatas.dataFormatada(dataVenda);
    }
    
    public String getDataVendaSQL() {
        return ConverterDatas.dataFormatadaBD(dataVenda);
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public void setCodVenda(int codVenda) {
        this.codVenda = codVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
  
}
