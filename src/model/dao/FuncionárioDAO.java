package model.dao;

import banco.DAO.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.funcionarioM;
import util.mensagens;

public class FuncionárioDAO extends DAO{
    public void salvarFunc(funcionarioM funcionario) throws Exception {
        if (funcionario.getId() == 0) {
            inserirFunc(funcionario);
        } else {
            alterarFunc(funcionario);
        }
    }
    
    public void inserirFunc(funcionarioM funcionario) throws Exception {
        try {

            String sql = "insert into funcionario(nome,rg,cpf,senha,celular,email,admin,cargo,endereco,bairro,estado) values (?,?,?,?,?,?,?,?,?,?,?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, funcionario.getNome());
            stm.setString(2, funcionario.getRg());
            stm.setString(3, funcionario.getCpf());
            stm.setString(4, funcionario.getSenha());
            stm.setString(5, funcionario.getCelular());
            stm.setString(6, funcionario.getEmail());
            stm.setString(7, funcionario.getAdmin());
            stm.setString(8, funcionario.getCargo().toString());
            stm.setString(9, funcionario.getEndereco());
            stm.setString(10, funcionario.getBairro());
            stm.setString(11, funcionario.getEstado());
            stm.executeUpdate();
            mensagens.info("Funcionário inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir Funcionário : " + ex);
        }
    }
    
    public void alterarFunc(funcionarioM funcionario) throws Exception {
        try {

            String sql = "update funcionario set nome=?, rg=?, cpf=?, senha=?, celular=?, email=?, admin=?, cargo=?, endereco=?, bairro=?, estado=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, funcionario.getNome());
            stm.setString(2, funcionario.getRg());
            stm.setString(3, funcionario.getCpf());
            stm.setString(4, funcionario.getSenha());
            stm.setString(5, funcionario.getCelular());
            stm.setString(6, funcionario.getEmail());
            stm.setString(7, funcionario.getAdmin());
            stm.setString(8, funcionario.getCargo().toString());
            stm.setString(9, funcionario.getEndereco());
            stm.setString(10, funcionario.getBairro());
            stm.setString(11, funcionario.getEstado());
            stm.setInt(12, funcionario.getId());
            stm.executeUpdate();
            mensagens.info("Funcionário alterado com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar Funcionário : " + ex);
        }
    }
    
    public void excluirFunc(funcionarioM funcionario) throws Exception {
        try {
        String sql = "delete from funcionario where id=?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, funcionario.getId());
        stm.executeUpdate();
        mensagens.info("Funcionário excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir Funcionário : "+ex);
        }
    }
    
    public ObservableList<funcionarioM> listar_func(String txtPesquisarFunc) throws Exception {

        String sql = "select * from funcionario where id like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarFunc + "%");
        rs = stm.executeQuery();
        ObservableList listaFunc = FXCollections.observableArrayList();
        while (rs.next()) {
            funcionarioM funcionario = new funcionarioM();
            funcionario.setId(rs.getInt("id"));
            funcionario.setNome(rs.getString("nome"));
            funcionario.setRg(rs.getString("rg"));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setSenha(rs.getString("senha"));
            funcionario.setCelular(rs.getString("celular"));
            funcionario.setEmail(rs.getString("email"));
            funcionario.setAdmin(rs.getString("admin"));
            funcionario.setCargo(rs.getString("cargo"));
            funcionario.setEndereco(rs.getString("endereco"));
            funcionario.setBairro(rs.getString("bairro"));
            funcionario.setEstado(rs.getString("estado"));
            listaFunc.add(funcionario);
        }
        return listaFunc;
    }
    
    public List<funcionarioM> comboCargo() {

        List<funcionarioM> dadosFunc = new ArrayList<>();

        try {
            String sql = "select distinct(cargo) from funcionario";

            stm = conector.prepareStatement(sql);
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                funcionarioM funcionario = new funcionarioM(rs.getString("cargo"));
                dadosFunc.add(funcionario);
            }

            stm.close();
            rs.close();

        } catch (SQLException ex) {
            mensagens.erro("Não foi possível preencher lista de cargos : \n" + ex);
        }

        return dadosFunc;
    }
    
    public List<funcionarioM> relatFunc() throws SQLException{
        
        List<funcionarioM> relatorioFunc;
        relatorioFunc = new ArrayList();
        String sql = "select nome, celular, email from funcionario";
        stm = conector.prepareStatement(sql);
        rs = stm.executeQuery();
        while(rs.next()){
            
        relatorioFunc.add(new funcionarioM(rs.getString("nome"),rs.getString("celular"),
        rs.getString("email")));
        }
        stm.close();
        return relatorioFunc;      
    }
}
