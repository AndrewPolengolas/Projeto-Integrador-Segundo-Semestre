package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yvens Borges
 */
public class GerenciarConexao {
    public static String STATUS = "Não conectado";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String SERVER = "localhost";
    public static String DATABASE = "lojadebrinquedos";
    public static String LOGIN = "root";
    public static String SENHA = "";
    public static String URL = "";
    public static Connection CONEXAO;

    public GerenciarConexao() {}
    
    /**
     * Metodo que faz a ligação com o banco de dados
     * @return Retorna um objeto do tipo conexão
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static Connection abrirConexao() throws ClassNotFoundException,SQLException {
 
        URL = "jdbc:mysql://" + SERVER + ":3306/" +DATABASE + "?useTimezone=true&serverTimezone=UTC&useSSL=false";
        
        if(CONEXAO==null)      
        {    
            try {

                Class.forName(DRIVER);
                CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);

                if (CONEXAO != null) {
                    STATUS = "Conexão realizada com sucesso!";
                } else {
                    STATUS = "Não foi possivel realizar a conexão";
                }

            } catch (ClassNotFoundException e) {

                throw new ClassNotFoundException("O driver expecificado nao foi encontrado.");

            } catch (SQLException e) {

                throw new SQLException("Erro ao estabelecer a conexão.");
            }
            
        }
        else
        {
            try {
                if(CONEXAO.isClosed())
                    CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);
            } catch (SQLException ex) {
                throw new SQLException("Falha ao fechar a conexão.");
            }
        }
        return CONEXAO;
    }
    
    /**
     * Metogo que mostra o status da conexão
     * @return Retorna o status da conexão
     */
    public static String getStatusConexao() {
        return STATUS;
    }
    
    /**
     * Metodo que interrompe a ligação com o banco de dados
     * @return Retorna true: caso a conexão tenha sido fechada, false: caso a conexão nao possa ser fechada
     * @throws java.sql.SQLException
     */
    public static boolean fecharConexao() throws SQLException {
 
        boolean retorno;
        
        try {
            if(CONEXAO!=null){
                if(!CONEXAO.isClosed())
                    CONEXAO.close();
            }
            
            STATUS = "Não conectado";
            retorno = true;
            
         } catch (SQLException e) {
            retorno = false;
        }
        
        return retorno;
    }
}
