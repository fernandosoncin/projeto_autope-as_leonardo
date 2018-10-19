package model.dao;
import banco.DAO.conexãoBanco;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.Cargo;
import util.mensagens;

public class CargoDAO {
    
    public static void salvar(Cargo cargo) throws Exception {
        if (cargo.getCod_Cargo()== 0) {
            inserir(cargo);
        } else {
            //inserir(cargo);
        }
    }
    
    public static void inserir(Cargo cargo) throws Exception {
        conexãoBanco c = new conexãoBanco();
        try {
            
            String sql = "insert into cargo(nome) values (?)";
            PreparedStatement ps = c.getConexao().prepareStatement(sql);
            ps.setString(1, cargo.getNome_Cargo());
            ps.execute();
            c.confirmar();
            mensagens.info("Cargo inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir cargo : "+ex);
        }
    }
    
    public static void removerCargo(Cargo cargo) throws Exception {
        conexãoBanco c = new conexãoBanco();
        try {
        String sql = "delete from cargo where id=?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setInt(1, cargo.getCod_Cargo());
        ps.execute();
        c.confirmar();
        mensagens.info("Cargo excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir cargo : "+ex);
        }
    }
    
    public static ObservableList<Cargo> listar_cargo(String txtPesquisarCargo) throws Exception {
        conexãoBanco c = new conexãoBanco();
        
        String sql = "select * from cargo where nome like ?";
        PreparedStatement ps = c.getConexao().prepareStatement(sql);
        ps.setString(1, "%" + txtPesquisarCargo + "%");
        ResultSet rs = ps.executeQuery();
        ObservableList listaCargo = FXCollections.observableArrayList();
        while (rs.next()) {
            Cargo cargo = new Cargo();
            cargo.setCod_Cargo(rs.getInt("id"));
            cargo.setNome_Cargo(rs.getString("nome"));
            listaCargo.add(cargo);
        }
        return listaCargo;
    }
    
    
}
