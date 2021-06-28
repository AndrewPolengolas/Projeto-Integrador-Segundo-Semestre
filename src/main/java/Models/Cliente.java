package Models;

/**
 *@author Andrew Nascimento
 */

public class Cliente {
    
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String sexo;
    
    public Cliente(){
    }

    public Cliente(String nome, String cpf, String telefone, String endereco, String email, String sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.sexo = sexo;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    /**
     * Metodo que formara um cpf
     * @return Retorna um cpf formatado
     */
    public String getCPFformatado (){
        return cpf.replace(".", "").replace("-", "");
    }
    
    /**
     * Metodo que formara um telefone
     * @return Retorna um telefone formatado
     */
    public String getTelefoneFormatado(){
        return telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "").trim();
    }
    
}
