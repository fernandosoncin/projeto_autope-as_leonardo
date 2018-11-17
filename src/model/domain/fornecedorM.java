package model.domain;

public class fornecedorM {

    private int id;
    private String nome;
    private String end;
    private String telefone;
    private String estado;

    public fornecedorM(int id, String nome, String end, String telefone, String estado) {
        this.id = id;
        this.nome = nome;
        this.end = end;
        this.telefone = telefone;
        this.estado = estado;
    }

    public fornecedorM() {
        this.id = 0;
    }
    
    public fornecedorM(String nome, String end, String telefone, String estado) {
        this.nome = nome;
        this.end = end;
        this.telefone = telefone;
        this.estado = estado;
    }
    
    public fornecedorM(String nome) {
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

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
