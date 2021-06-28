package utils;

import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Yvens Borges
 */
public class Validacoes {
    
    /**
     * Metodo que recebe uma string e verifica se ela esta vazia ou não
     * @param campo
     * @return Retorna true: para strings preenchidas, false: para strings vazias
     */
    public static boolean ValidarCampo(String campo){
        boolean retorno;
        if(campo.equals("") || campo.equals("dd/MM/yyyy")){
            JOptionPane.showMessageDialog(null, "Favor preencher os campos obrigatórios ");
            return retorno = false;
        }else{
            return retorno = true;
        }
    }
    
    /**
     * Metodo que recebe uma dade do tipo Date e verifica se esta vazio ou não
     * @param campo
     * @return Retorna true: para datas preenchidas, false: para datas vazias
     */
    public static boolean ValidarCampoData(Date campo){
        boolean retorno;
        if(campo == null){
            JOptionPane.showMessageDialog(null, "Favor preencher os campos obrigatórios ");
            return retorno = false;
        }else{
            return retorno = true;
        }
    }
    
    /**
     * Metodo que recebe um cpf do tipo string e verifica se esta vazio ou não
     * @param cpf
     * @return Retorna true: para cpf preenchido, false: para cpf vazio
     */
    public static boolean ValidarCpf(String cpf){
        boolean retorno;
        String cpfFormatado = cpf.replace(".", "").replace("-", "");
        if(cpfFormatado.length() == 11){
            return retorno = true;
        }else{
            return retorno = false;
        }
    }
    
    /**
     * Metodo que recebe um double e formata ele para string
     * @param numero
     * @return Retorna uma string formatada
     */
    public static String DecimalFormatado(double numero){
        DecimalFormat formato = new DecimalFormat("#,###.00");      
        String retorno = formato.format(numero);
        return retorno;
    }
    
}
