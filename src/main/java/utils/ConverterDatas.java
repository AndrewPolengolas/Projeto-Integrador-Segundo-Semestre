package utils;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Yvens Borges
 */
public class ConverterDatas {
    
    /**
     * Metodo que recebe uma data e formata ela
     * @param data
     * @return Retorna uma data formatada
     */
    public static String dataFormatada(Date data){
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormatada.format(data);
    }
    
    /**
     * Metodo que recebe uma data e formata ela
     * @param data
     * @return Retorna uma data formatada para o banco de dados
     */
    public static String dataFormatadaBD(Date data){
        SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd");
        return dataFormatada.format(data);
    }
}
