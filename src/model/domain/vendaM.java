
package model.domain;

import model.domain.funcionarioM;
import model.domain.clienteM;


public class vendaM {
    
    private int id;
    private String data;
    private String horario;
    private funcionarioM funcionario_id;
    private clienteM cliente_id;
    private produtoM produto_id;

    public vendaM(int id, String data, String horario, funcionarioM funcionario_id, clienteM cliente_id, produtoM produto_id) {
        this.id = id;
        this.data = data;
        this.horario = horario;
        this.funcionario_id = funcionario_id;
        this.cliente_id = cliente_id;
        this.produto_id = produto_id;
    }

    public vendaM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public funcionarioM getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(funcionarioM funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public clienteM getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(clienteM cliente_id) {
        this.cliente_id = cliente_id;
    }

    public produtoM getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(produtoM produto_id) {
        this.produto_id = produto_id;
    }
    
    
    
}
