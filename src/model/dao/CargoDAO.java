package model.dao;

import banco.DAO.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.cargoM;
import util.mensagens;

public class CargoDAO extends DAO {

    public void salvar(cargoM cargo) throws Exception {
        if (cargo.getCod_Cargo() == 0) {
            inserir(cargo);
        } else {
            alterar(cargo);
        }
    }

    public void inserir(cargoM cargo) throws Exception {
        try {

            String sql = "insert into cargo(nome) values (?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, cargo.getNome_Cargo());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Cargo inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir cargo : " + ex);
        }
    }

    public void alterar(cargoM cargo) throws Exception {
        try {

            String sql = "update cargo set nome=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, cargo.getNome_Cargo());
            stm.setInt(2, cargo.getCod_Cargo());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Cargo alterado com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar Cargo : " + ex);
        }
    }

    public void removerCargo(cargoM cargo) throws Exception {
        try {
            String sql = "delete from cargo where id=?";
            stm = conector.prepareStatement(sql);
            stm.setInt(1, cargo.getCod_Cargo());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Cargo excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir cargo.\nVerifique se existe algum funcionário vinculado ao cargo.\nErro : \n " + ex);
        }
    }

    public List<cargoM> comboCargos() {
        List<cargoM> dados = new ArrayList<>();
        try {
            String sql = "SELECT id,nome FROM cargo ORDER BY nome ";

            stm = conector.prepareStatement(sql);
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                dados.add(new cargoM(rs.getInt(1), rs.getString(2)));
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            mensagens.erro("Erro ao carregar cargos cadastrados : \n" + ex);
        }
        return dados;
    }

    public ObservableList<cargoM> listar_cargoNome(String txtPesquisarCargo) throws Exception {

        String sql = "select * from cargo where nome like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarCargo + "%");
        rs = stm.executeQuery();
        ObservableList listaCargo = FXCollections.observableArrayList();
        while (rs.next()) {
            cargoM cargo = new cargoM();
            cargo.setCod_Cargo(rs.getInt("id"));
            cargo.setNome_Cargo(rs.getString("nome"));
            listaCargo.add(cargo);
        }
        return listaCargo;
    }
    
    public cargoM buscaCargo(int id) throws SQLException{
        String sql = "select * from cargo where id like ?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, id);
        cargoM car = null;
        rs = stm.executeQuery();
        while(rs.next()){
           car = new cargoM((rs.getInt("id")), 
                   rs.getString("nome")
           );
        }
        stm.close();
        return car;
    }

    public List<cargoM> relatCargo() throws SQLException {

        List<cargoM> relatorioCargo;
        relatorioCargo = new ArrayList();
        String sql = "select nome from cargo";
        stm = conector.prepareStatement(sql);
        rs = stm.executeQuery();
        while (rs.next()) {

            relatorioCargo.add(new cargoM(rs.getString("nome")));
        }
        stm.close();
        return relatorioCargo;
    }

}
