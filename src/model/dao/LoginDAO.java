package model.dao;

import banco.DAO.ControleDAO;
import banco.DAO.DAO;
import java.sql.SQLException;
import model.domain.funcionarioM;
import util.mensagens;

public class LoginDAO extends DAO {

    public LoginDAO() {
        super();
    }

    public boolean autenticarUser(String cpf) {
        try {
            String sql = "SELECT cpf FROM funcionario WHERE cpf=?";

            stm = conector.prepareStatement(sql);
            stm.setString(1, cpf);
            rs = stm.executeQuery();

            if (rs.next()) {
                return cpf.equals(rs.getString(1));
            } else {
                mensagens.erro("Usuário incorreto.");
            }

            stm.close();
            rs.close();

        } catch (SQLException ex) {
            mensagens.erro("Não foi possível autenticar usuário : \n" + ex);
        }

        return false;
    }

    public boolean autenticarSenha(String cpf, String senha) {
        try {
            String sql = "SELECT cpf, senha FROM funcionario WHERE cpf=? AND senha=? ";

            stm = conector.prepareStatement(sql);
            stm.setString(1, cpf);
            stm.setString(2, senha);
            rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getString(1).equals(cpf) && rs.getString(2).equals(senha);
            } else {
                mensagens.erro("Senha incorreta.");
            }

            stm.close();
            rs.close();

        } catch (SQLException ex) {
            mensagens.erro("Não foi possível autenticar a senha do usuário : \n" + ex);
        }

        return false;
    }

    public funcionarioM usuarioLogado(String login) {

        funcionarioM user = null;

        try {
            String sql = "select * from funcionario where cpf = ?";

            stm = conector.prepareStatement(sql);
            stm.setString(1, login);
            rs = stm.executeQuery();
            while (rs.next()) {
                funcionarioM funcionario = new funcionarioM();
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setAdmin(rs.getString("tipo"));
                funcionario.setCargo_id(ControleDAO.getControleBanco().getCargoDAO().buscaCargo(rs.getInt("cargo_id")));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setBairro(rs.getString("bairro"));
                funcionario.setEstado(rs.getString("estado"));
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            mensagens.erro("Erro ao consultar usuário logado : \n" + ex);
        }

        return user;
    }

}
