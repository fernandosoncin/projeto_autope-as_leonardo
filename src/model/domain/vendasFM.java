package model.domain;

import java.util.ArrayList;
import java.util.List;


public class vendasFM {
    
    private int id;
    private clienteMF cliente_id;
    private funcionarioM funcionario_id;
    private List<itensVendaFM> itens_VendaFM;
    private float valor_total;

    public vendasFM(int id, clienteMF cliente_id, funcionarioM funcionario_id, List<itensVendaFM> itens_VendaFM, float valor_total) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.funcionario_id = funcionario_id;
        this.itens_VendaFM = itens_VendaFM;
        this.valor_total = valor_total;
    }

    public vendasFM() {
        this.id = 0;
        this.itens_VendaFM = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public clienteMF getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(clienteMF cliente_id) {
        this.cliente_id = cliente_id;
    }

    public funcionarioM getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(funcionarioM funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public List<itensVendaFM> getItens_VendaFM() {
        return itens_VendaFM;
    }

    public void setItens_VendaFM(List<itensVendaFM> itens_VendaFM) {
        this.itens_VendaFM = itens_VendaFM;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }


    

    
}
