package model.domain;

public class funcionarioM {

    private int id;
    private String nome;
    private String rg;
    private String cpf;
    private String senha;
    private String celular;
    private String email;
    private String admin;
    private cargoM cargo_id;
    private String endereco;
    private String bairro;
    private String estado;

    public funcionarioM(int id, String nome, String rg, String cpf, String senha, String celular, String email, String admin, cargoM cargo_id, String endereco, String bairro, String estado) {
        this.id = id;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.senha = senha;
        this.celular = celular;
        this.email = email;
        this.admin = admin;
        this.cargo_id = cargo_id;
        this.endereco = endereco;
        this.bairro = bairro;
        this.estado = estado;
    }

    public funcionarioM() {
        this.id = 0;
    }
    
    public funcionarioM(String nome, String celular, String email) {
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public funcionarioM(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public cargoM getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(cargoM cargo_id) {
        this.cargo_id = cargo_id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String toString() {
        return this.nome;
    }

}
