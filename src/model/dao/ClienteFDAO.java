package model.dao;

import banco.DAO.DAO;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.clienteMF;
import util.mensagens;

/**
 *
 * @author Fellipe
 */
public class ClienteFDAO extends DAO {

    public  void salvarF(clienteMF cliente) throws Exception {
        if (cliente.getId() == 0) {
            inserirF(cliente);
        } else {
            alterarF(cliente);
        }
    }

    public void inserirF(clienteMF cliente) throws Exception {
        try {

            String sql = "insert into cliente(nome,cpf,celular,email,endereco,bairro,estado) values (?,?,?,?,?,?,?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCpf());
            stm.setString(3, cliente.getCelular());
            stm.setString(4, cliente.getEmail());
            stm.setString(5, cliente.getEndereco());
            stm.setString(6, cliente.getBairro());
            stm.setString(7, cliente.getEstado());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Cliente Físic inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir Cliente Físico: " + ex);
        }
    }

    public void alterarF(clienteMF cliente) throws Exception {

        try {

            String sql = "update cliente set nome=?, cpf=?, celular=?, email=?, endereco=?, bairro=?, estado=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, cliente.getNome());
            stm.setString(2, cliente.getCpf());
            stm.setString(3, cliente.getCelular());
            stm.setString(4, cliente.getEmail());
            stm.setString(5, cliente.getEndereco());
            stm.setString(6, cliente.getBairro());
            stm.setString(7, cliente.getEstado());
            stm.setInt(8, cliente.getId());
            stm.executeUpdate();
            mensagens.info("Cliente Físico alterado com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar Cliente Físico : " + ex);
        }
    }
    
    public void excluirClienteF(clienteMF cliente) throws Exception {
        try {
        String sql = "delete from cliente where id=?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, cliente.getId());
        stm.executeUpdate();
        mensagens.info("Cliente Físico excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir Cliente Físico : "+ex);
        }
    }

    public ObservableList<clienteMF> listar_clienteF(String txtPesquisar) throws Exception {
        String sql = "select * from cliente where id like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisar + "%");
        rs = stm.executeQuery();
        ObservableList listaCliente = FXCollections.observableArrayList();
        while (rs.next()) {
            clienteMF cliente = new clienteMF();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setCelular(rs.getString("celular"));
            cliente.setEmail(rs.getString("email"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setEstado(rs.getString("estado"));
            listaCliente.add(cliente);
        }
        return listaCliente;
    }
}