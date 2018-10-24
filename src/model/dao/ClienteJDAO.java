package model.dao;

import banco.DAO.conexãoBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.clienteMJ;
import util.mensagens;

public class ClienteJDAO {

    public static void salvarJ(clienteMJ clienteJ) throws Exception {
        if (clienteJ.getId() == 0) {
            inserirJ(clienteJ);
        } else {
            alterarJ(clienteJ);
        }
    }
    
    public static void inserirJ(clienteMJ clienteJ) throws Exception {
        conexãoBanco c = new conexãoBanco();
        try {

            String sql = "insert into clientej(razao,estado,telefone,email,endereco,bairro,cnpj) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);;
            ps.setString(1, clienteJ.getRs());
            ps.setString(2, clienteJ.getEstado());
            ps.setString(3, clienteJ.getTelefone());
            ps.setString(4, clienteJ.getEmail());
            ps.setString(5, clienteJ.getEndereco());
            ps.setString(6, clienteJ.getBairro());
            ps.setString(7, clienteJ.getCnpj());
            ps.execute();
            c.confirmar();
            mensagens.info("Cliente Jurídico inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir Cliente Jurídico : " + ex);
        }
    }
    
    public static void alterarJ(clienteMJ clienteJ) throws Exception {
        conexãoBanco c = new conexãoBanco();
        try {

            String sql = "update clientej set razao=?, estado=?, telefone=?, email=?, endereco=?, bairro=?, cnpj=? where id=?";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.setString(1, clienteJ.getRs());
            ps.setString(2, clienteJ.getEstado());
            ps.setString(3, clienteJ.getTelefone());
            ps.setString(4, clienteJ.getEmail());
            ps.setString(5, clienteJ.getEndereco());
            ps.setString(6, clienteJ.getBairro());
            ps.setString(7, clienteJ.getCnpj());
            ps.setInt(8, clienteJ.getId());
            ps.execute();
            c.confirmar();
            mensagens.info("Cliente Jurídico alterado com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar Cliente Jurídico : " + ex);
        }
    }
    
    public static void excluirClienteJ(clienteMJ clienteJ) throws Exception {
        conexãoBanco c = new conexãoBanco();
        try {
        String sql = "delete from clientej where id=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, clienteJ.getId());
        ps.execute();
        c.confirmar();
        mensagens.info("Cliente Jurídico excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir Cliente Jurídico : "+ex);
        }
    }
    
    public static ObservableList<clienteMJ> listar_clienteJ(String txtPesquisarJur) throws Exception {
        conexãoBanco c = new conexãoBanco();

        String sql = "select * from clientej where id like ?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, "%" + txtPesquisarJur + "%");
        ResultSet rs = ps.executeQuery();
        ObservableList listaClienteJ = FXCollections.observableArrayList();
        while (rs.next()) {
            clienteMJ clienteJ = new clienteMJ();
            clienteJ.setId(rs.getInt("id"));
            clienteJ.setRs(rs.getString("razao"));
            clienteJ.setCnpj(rs.getString("cnpj"));
            clienteJ.setTelefone(rs.getString("telefone"));
            clienteJ.setEmail(rs.getString("email"));
            clienteJ.setEndereco(rs.getString("endereco"));
            clienteJ.setBairro(rs.getString("bairro"));
            clienteJ.setEstado(rs.getString("estado"));
            listaClienteJ.add(clienteJ);
        }
        return listaClienteJ;
    }
    
}
