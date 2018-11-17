package model.domain;

public class categoriaM {

    private int id_Categoria;
    private String nome_Categoria;

    public categoriaM(int id_Categoria, String nome_Categoria) {
        this.id_Categoria = id_Categoria;
        this.nome_Categoria = nome_Categoria;
    }

    public categoriaM() {
        this.id_Categoria = 0;
    }

    public categoriaM(String nome_Categoria) {
        this.nome_Categoria = nome_Categoria;
    }

    public int getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public String getNome_Categoria() {
        return nome_Categoria;
    }

    public void setNome_Categoria(String nome_Categoria) {
        this.nome_Categoria = nome_Categoria;
    }

    public String toString() {
        return this.nome_Categoria;
    }
    
}
