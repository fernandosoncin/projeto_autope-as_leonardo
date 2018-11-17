package model.dao;

import banco.DAO.DAO;
import banco.DAO.conexãoBancoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.clienteMJ;
import util.mensagens;

public class ClienteJDAO extends DAO{

    public void salvarJ(clienteMJ clienteJ) throws Exception {
        if (clienteJ.getId() == 0) {
            inserirJ(clienteJ);
        } else {
            alterarJ(clienteJ);
        }
    }
    
    public void inserirJ(clienteMJ clienteJ) throws Exception {
        try {

            String sql = "insert into clientej(razao,estado,telefone,email,endereco,bairro,cnpj) values (?,?,?,?,?,?,?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, clienteJ.getRs());
            stm.setString(2, clienteJ.getEstado());
            stm.setString(3, clienteJ.getTelefone());
            stm.setString(4, clienteJ.getEmail());
            stm.setString(5, clienteJ.getEndereco());
            stm.setString(6, clienteJ.getBairro());
            stm.setString(7, clienteJ.getCnpj());
            stm.executeUpdate();
            mensagens.info("Cliente Jurídico inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir Cliente Jurídico : " + ex);
        }
    }
    
    public void alterarJ(clienteMJ clienteJ) throws Exception {
        try {

            String sql = "update clientej set razao=?, estado=?, telefone=?, email=?, endereco=?, bairro=?, cnpj=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, clienteJ.getRs());
            stm.setString(2, clienteJ.getEstado());
            stm.setString(3, clienteJ.getTelefone());
            stm.setString(4, clienteJ.getEmail());
            stm.setString(5, clienteJ.getEndereco());
            stm.setString(6, clienteJ.getBairro());
            stm.setString(7, clienteJ.getCnpj());
            stm.setInt(8, clienteJ.getId());
            stm.executeUpdate();
            mensagens.info("Cliente Jurídico alterado com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar Cliente Jurídico : " + ex);
        }
    }
    
    public void excluirClienteJ(clienteMJ clienteJ) throws Exception {
        try {
        String sql = "delete from clientej where id=?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, clienteJ.getId());
        stm.executeUpdate();
        mensagens.info("Cliente Jurídico excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir Cliente Jurídico : "+ex);
        }
    }
    
    public ObservableList<clienteMJ> listar_clienteJ(String txtPesquisarJur) throws Exception {

        String sql = "select * from clientej where razao like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarJur + "%");
        rs = stm.executeQuery();
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
    
    public List<clienteMJ> relatJ() throws SQLException{
        
        List<clienteMJ> relatorioJ;
        relatorioJ = new ArrayList();
        String sql = "select razao, telefone, email from clientej";
        stm = conector.prepareStatement(sql);
        rs = stm.executeQuery();
        while(rs.next()){
            
        relatorioJ.add(new clienteMJ(rs.getString("razao"),rs.getString("telefone"),
        rs.getString("email")));
        }
        stm.close();
        return relatorioJ;      
    }
    
}
