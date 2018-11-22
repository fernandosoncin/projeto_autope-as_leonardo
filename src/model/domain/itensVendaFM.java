package model.domain;

public class itensVendaFM {
    private int id;
    private int vendaFM_id;
    private produtoM prodM;
    private int qntd;
    private float preco;
    private float preco_total;

    public itensVendaFM(int id, int vendaFM_id, produtoM prodM, int qntd, float preco, float preco_total) {
        this.id = id;
        this.vendaFM_id = vendaFM_id;
        this.prodM = prodM;
        this.qntd = qntd;
        this.preco = preco;
        this.preco_total = preco_total;
    }

    public itensVendaFM() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendaFM() {
        return vendaFM_id;
    }

    public void setVendaFM(int vendaFM) {
        this.vendaFM_id = vendaFM;
    }

    public produtoM getProdM() {
        return prodM;
    }

    public void setProdM(produtoM prodM) {
        this.prodM = prodM;
    }

    public int getQntd() {
        return qntd;
    }

    public void setQntd(int qntd) {
        this.qntd = qntd;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public float getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }


    
    
}
