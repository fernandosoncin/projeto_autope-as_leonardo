package model.domain;

public class clienteMJ {

    private int id;
    private String rs;
    private String telefone;
    private String email;
    private String endereco;
    private String bairro;
    private String estado;
    private String cnpj;

    public clienteMJ(int id, String rs, String telefone, String email, String endereco, String bairro, String estado, String cnpj) {
        this.id = id;
        this.rs = rs;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.bairro = bairro;
        this.estado = estado;
        this.cnpj = cnpj;
    }
    
    public clienteMJ(String rs, String telefone, String email) {
        this.rs = rs;
        this.telefone = telefone;
        this.email = email;
    }

    public clienteMJ() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof clienteMJ) {
            clienteMJ c = (clienteMJ) o;
            if (c.getId() == this.getId()) {
                return true;
            }
        }
        return false;
    }

}
