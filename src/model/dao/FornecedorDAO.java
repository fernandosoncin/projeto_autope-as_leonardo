package model.dao;

import banco.DAO.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.fornecedorM;
import util.mensagens;

public class FornecedorDAO extends DAO{

    public void salvarFornecedor(fornecedorM fornecedor) throws Exception {
        if (fornecedor.getId()== 0) {
            inserir(fornecedor);
        } else {
            alterar(fornecedor);
        }
    }

    public void inserir(fornecedorM fornecedor) throws Exception {
        try {

            String sql = "insert into fornecedor_p(nome,end,telefone,estado) values (?,?,?,?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, fornecedor.getNome());
            stm.setString(2, fornecedor.getEnd());
            stm.setString(3, fornecedor.getTelefone());
            stm.setString(4, fornecedor.getEstado());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Fornecedor inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir fornecedor : " + ex);
        }
    }

    public void alterar(fornecedorM fornecedor) throws Exception {
        try {

            String sql = "update fornecedor_p set nome=?, end=?, telefone=?, estado=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, fornecedor.getNome());
            stm.setString(2, fornecedor.getEnd());
            stm.setString(3, fornecedor.getTelefone());
            stm.setString(4, fornecedor.getEstado());
            stm.setInt(5, fornecedor.getId());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Fornecedor alterado com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar fornecedor : " + ex);
        }
    }

    public void removerFornecedor(fornecedorM fornecedor) throws Exception {
        try {
            String sql = "delete from fornecedor_p where id=?";
            stm = conector.prepareStatement(sql);
            stm.setInt(1, fornecedor.getId());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Fornecedor excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir fornecedor.\nVerifique se existe algum produto vinculado a esse fornecedor.\nErro : \n" + ex);
        }
    }

    public List<fornecedorM> comboFornecedor() {
        List<fornecedorM> dados = new ArrayList<>();
        try {
            String sql = "SELECT id,nome,end,telefone,estado FROM fornecedor_p ORDER BY nome";

            stm = conector.prepareStatement(sql);
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                dados.add(new fornecedorM(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            mensagens.erro("Erro ao carregar fornecedores cadastrados : \n" + ex);
        }
        return dados;
    }

    public ObservableList<fornecedorM> listar_fornecedorNome(String txtPesquisarFornecedorProd) throws Exception {

        String sql = "select * from fornecedor_p where nome like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarFornecedorProd + "%");
        rs = stm.executeQuery();
        ObservableList listaFornecedor = FXCollections.observableArrayList();
        while (rs.next()) {
            fornecedorM fornecedor = new fornecedorM();
            fornecedor.setId(rs.getInt("id"));
            fornecedor.setNome(rs.getString("nome"));
            fornecedor.setEnd(rs.getString("end"));
            fornecedor.setTelefone(rs.getString("telefone"));
            fornecedor.setEstado(rs.getString("estado"));
            listaFornecedor.add(fornecedor);
        }
        return listaFornecedor;
    }
    
    public fornecedorM buscaFornecedor(int id) throws SQLException{
        String sql = "select * from fornecedor_p where id like ?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, id);
        fornecedorM forn = null;
        rs = stm.executeQuery();
        while(rs.next()){
           forn = new fornecedorM((rs.getInt("id")), 
                   rs.getString("nome"),
                   rs.getString("end"),
                   rs.getString("telefone"),
                   rs.getString("estado")
           );
        }
        stm.close();
        return forn;
    }

    public List<fornecedorM> relatFornecedor() throws SQLException {

        List<fornecedorM> relatorioFornecedor;
        relatorioFornecedor = new ArrayList();
        String sql = "select nome,end,telefone,estado from fornecedor_p";
        stm = conector.prepareStatement(sql);
        rs = stm.executeQuery();
        while (rs.next()) {

            relatorioFornecedor.add(new fornecedorM(rs.getString("nome"),rs.getString("end"),rs.getString("telefone"),rs.getString("estado")));
        }
        stm.close();
        return relatorioFornecedor;
    }
    
}
