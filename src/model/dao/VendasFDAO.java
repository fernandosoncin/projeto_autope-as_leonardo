package model.dao;

import banco.DAO.ControleDAO;
import banco.DAO.DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.itensVendaFM;
import model.domain.vendasFM;
import util.mensagens;

public class VendasFDAO extends DAO {

    public void finalizarVF(vendasFM vendasF) throws Exception {
        try {
            int idVenda = 0;
            String sql = "insert into vendasf values (?,?,?,?,now())";
            stm = conector.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, 0);
            stm.setInt(2, vendasF.getCliente_id().getId());
            stm.setInt(3, vendasF.getFuncionario_id().getId());
            stm.setFloat(4, vendasF.getValor_total());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            while (rs.next()){
                idVenda = rs.getInt(1);
            }
            stm.close();
            salvarItensVendaF(vendasF.getItens_VendaFM(),idVenda);
            mensagens.info("Venda finalizada com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao finalizar venda : " + ex);
        }
    }
    
    public void salvarItensVendaF(List<itensVendaFM> itensVendaF, int idVenda) throws SQLException{
        for (itensVendaFM itensF : itensVendaF){
            String sql = "insert into itens_vendaf values (?,?,?,?,?,?)";
            stm = conector.prepareStatement(sql,RETURN_GENERATED_KEYS);
            stm.setInt(1, 0);
            stm.setInt(2, idVenda);
            stm.setInt(3, itensF.getProdM().getId());
            stm.setInt(4, itensF.getQntd());
            stm.setFloat(5, itensF.getPreco());
            stm.setFloat(6, itensF.getPreco_total());
            stm.executeUpdate();
            stm.close();
        }
    }
    
    public ObservableList<vendasFM> listar_vendas(String txtPesquisarVF) throws Exception {

        String sql = "select * from vendasf vf inner join clientef cf on vf.cliente_id = cf.id where cf.nome like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarVF + "%");
        rs = stm.executeQuery();
        ObservableList listaVendasF = FXCollections.observableArrayList();
        while (rs.next()) {
            vendasFM vendasF = new vendasFM();
            vendasF.setId(rs.getInt("id"));
            vendasF.setCliente_id(ControleDAO.getControleBanco().getClienteFDAO().buscaClienteF(rs.getInt("cliente_id")));
            vendasF.setFuncionario_id(ControleDAO.getControleBanco().getFuncionárioDAO().buscaFunc(rs.getInt("funcionario_id")));
            vendasF.setValor_total(rs.getFloat("valortotalv"));
            vendasF.setData_horario(rs.getString("horario"));
            listaVendasF.add(vendasF);
        }
        return listaVendasF;
    }
    
    public ObservableList<itensVendaFM> listar_itensVenda(int idVF) throws Exception {

        String sql = "select * from itens_vendaf where id_vendaf = ?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, idVF);
        rs = stm.executeQuery();
        ObservableList listaItensVendaF = FXCollections.observableArrayList();
        while (rs.next()) {
            itensVendaFM ivF = new itensVendaFM();
            ivF.setProdM(ControleDAO.getControleBanco().getProdutoDAO().buscaProduto(rs.getInt("id_prod")));
            ivF.setQntd(rs.getInt("quantidade"));
            ivF.setPreco(rs.getFloat("preco_prod"));
            ivF.setPreco_total(rs.getFloat("total_prod"));
            listaItensVendaF.add(ivF);
        }
        return listaItensVendaF;
    }
}
