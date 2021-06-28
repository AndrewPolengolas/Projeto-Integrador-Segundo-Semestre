package Controller;

import DAO.ClienteDAO;
import Models.Cliente;
import java.util.ArrayList;

/**
 *@author Andrew Nascimento
 */
public class ClienteController {
    
    /**
     * Método que cria um objeto da classe {@link Models.Cliente} e manda para classe {@link DAO.ClienteDAO#salvar }
     * @param nome
     * @param cpf
     * @param telefone
     * @param email
     * @param endereco
     * @param sexo
     * @return boolen - true: Se o cliente foi salvo, false: se ocorrer um erro no salvamento
     */
    
    public static boolean Salvar(String nome, String cpf, String telefone, String email, String endereco, String sexo){
        boolean aux, retorno;
        
        Cliente cliente = new Cliente();
        
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        cliente.setSexo(sexo);
        
        try{
           aux = ClienteDAO.salvar(cliente);
           if(aux == true){
               return  retorno = true;
           }else{
               return retorno = false;
           }
        }catch(Exception e){
            System.out.println(e);
            return retorno = false;
        }
    }
    
    /**
     * Método que cria um objeto da classe {@link Models.Cliente} e manda para classe {@link DAO.ClienteDAO#atualizar }
     * @param nome
     * @param cpf
     * @param telefone
     * @param email
     * @param endereco
     * @param sexo
     * @return boolean - true: se o cliente for atualizado, false: se ocorrer um erro ao atualizar
     */
    
    public static boolean Atualizar(String nome, String cpf, String telefone, String email, String endereco, String sexo){
        boolean aux, retorno;
        
        Cliente cliente = new Cliente();
        
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        cliente.setSexo(sexo);
        
        try{
           aux = ClienteDAO.atualizar(cliente);
           if(aux == true){
               return  retorno = true;
           }else{
               return retorno = false;
           }
        }catch(Exception e){
            System.out.println(e);
            return retorno = false;
        }
    }
    
    /**
     * Método que recebe uma string cpf manda para classe {@link DAO.ClienteDAO#excluir } e {@link DAO.EstoqueDAO#excluir }
     * @param cpf
     * @return boolean - ture: se o cliente for atualizado, false: se ocorrer um erro ao atualizar
     */
    
    public static boolean Excluir(String cpf){
        
        boolean aux = ClienteDAO.excluir(cpf);
        boolean retorno;
        
        if(aux == false){
            return retorno = false;
        }else{
            return retorno = true;
        }
        
    }
    
    /**
     * Recebe uma string e manda para a classe {@link DAO.ClienteDAO#consultarClientesNome }
     * @param nome
     * @return retorna um ArrayList do tipo {@link Models.Cliente} 
     */
    public static ArrayList<Cliente> ConsultarClientesNome(String nome){
        ArrayList<Cliente> listaClientes = ClienteDAO.consultarClientesNome(nome);
        return listaClientes;
    }

    /**
     * Recebe uma string e manda para a classe {@link DAO.ClienteDAO#consultarClientesCPF }
     * @param cpf
     * @return retorna um ArrayList do tipo {@link Models.Cliente} 
     */
    public static ArrayList<Cliente> ConsultarClienteCpf(String cpf){
        ArrayList<Cliente> listaClientes = ClienteDAO.consultarClientesCPF(cpf);
        return listaClientes;
    }
    
    
    /**
     * Faz uma chamada direta da classe {@link DAO.ClienteDAO#consultarClientes }
     * @return retorna um ArrayList do tipo {@link Models.Cliente}
     */
    public static ArrayList<Cliente> ConsultarClientes(){
        ArrayList<Cliente> listaClientes = ClienteDAO.consultarClientes();
        return listaClientes;
    }
}
