package model.domain;

import java.io.Serializable;

public class clienteMF implements Serializable{

    private int id;
    private String nome;
    private String cpf;
    private String celular;
    private String email;
    private String endereco;
    private String bairro;
    private String estado;

    public clienteMF() {
        this.id = 0;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public void getEstado(String toString) {

    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof clienteMF) {
            clienteMF c = (clienteMF) o;
            if (c.getId()== this.getId()) {
                return true;
            }
        }
        return false;
    }

}
