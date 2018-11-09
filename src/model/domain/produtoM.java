
package model.domain;


public class produtoM {
    
    private int id;
    private String nome;
    private String categoria;
    private String valor;

    public produtoM(int id, String nome, String categoria, String valor) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
    }
    
    public produtoM(String nome, String categoria, String valor) {
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
    }

    public produtoM() {
        this.id = 0;
    }

    public produtoM(String categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        return categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    
    
    
    
}
