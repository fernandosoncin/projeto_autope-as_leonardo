package model.domain;

public class produtoM {

    private int id;
    private String nome;
    private categoriaM categoria_id;
    private fornecedorM fornecedor_id;
    private String pc_Compra;
    private String pc_Venda;

    public produtoM(int id, String nome, categoriaM categoria_id, fornecedorM fornecedor_id, String pc_Compra, String pc_Venda) {
        this.id = id;
        this.nome = nome;
        this.categoria_id = categoria_id;
        this.fornecedor_id = fornecedor_id;
        this.pc_Compra = pc_Compra;
        this.pc_Venda = pc_Venda;
    }

    public produtoM() {
        this.id = 0;
    }

    public produtoM(String nome, String pc_Compra, String pc_Venda) {
        this.nome = nome;
        this.pc_Compra = pc_Compra;
        this.pc_Venda = pc_Compra;
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

    public categoriaM getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(categoriaM categoria_id) {
        this.categoria_id = categoria_id;
    }

    public fornecedorM getFornecedor_id() {
        return fornecedor_id;
    }

    public void setFornecedor_id(fornecedorM fornecedor_id) {
        this.fornecedor_id = fornecedor_id;
    }

    public String getPc_Compra() {
        return pc_Compra;
    }

    public void setPc_Compra(String pc_Compra) {
        this.pc_Compra = pc_Compra;
    }

    public String getPc_Venda() {
        return pc_Venda;
    }

    public void setPc_Venda(String pc_Venda) {
        this.pc_Venda = pc_Venda;
    }

    public String toString() {
        return this.nome;
    }

}
