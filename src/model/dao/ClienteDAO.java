/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import banco.DAO.conexãoBanco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.clienteM;
import util.mensagens;

/**
 *
 * @author Fellipe
 */
public class ClienteDAO {
    
    public static void salvar(clienteM cliente) throws Exception {
        if (cliente.getId()== 0) {
            inserir(cliente);
        } else {
            //inserir(cargo);
        }
    }
    
    public static void inserir(clienteM cliente) throws Exception {
        conexãoBanco c = new conexãoBanco();
        try {
            
            String sql = "insert into cliente(nome,cpf,celular,email,endereco,bairro,estado) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);;
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getCelular());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getEndereco());
            ps.setString(6, cliente.getBairro());
            ps.setString(7, cliente.getEstado());
            ps.execute();
            c.confirmar();
            mensagens.info("Cliente inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir cliente : "+ex);
        }
    }
    
        public static ObservableList<clienteM> listar_cliente(String txtPesquisar) throws Exception {
        conexãoBanco c = new conexãoBanco();
        
        String sql = "select * from cliente where id like ?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, "%" + txtPesquisar + "%");
        ResultSet rs = ps.executeQuery();
        ObservableList listaCliente = FXCollections.observableArrayList();
        while (rs.next()) {
            clienteM cliente = new clienteM();
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
