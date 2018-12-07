package model.domain;

import java.util.ArrayList;
import java.util.List;

public class vendasFM {

    private int id;
    private clienteMF cliente_id;
    private funcionarioM funcionario_id;
    private List<itensVendaFM> itens_VendaFM;
    private float valor_total;
    private String data_horario;

    public vendasFM(int id, clienteMF cliente_id, funcionarioM funcionario_id, List<itensVendaFM> itens_VendaFM, float valor_total, String data_horario) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.funcionario_id = funcionario_id;
        this.itens_VendaFM = itens_VendaFM;
        this.valor_total = valor_total;
        this.data_horario = data_horario;
    }

    public vendasFM() {
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

    public String getData_horario() {
        return data_horario;
    }

    public void setData_horario(String data_horario) {
        this.data_horario = data_horario;
    }
}
