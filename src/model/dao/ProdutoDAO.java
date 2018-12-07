package model.dao;

import banco.DAO.ControleDAO;
import banco.DAO.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.produtoM;
import util.mensagens;

public class ProdutoDAO extends DAO {

    public void salvarProd(produtoM produto) throws Exception {
        if (produto.getId() == 0) {
            inserirProd(produto);
        } else {
            alterarProd(produto);
        }
    }

    public void inserirProd(produtoM produto) throws Exception {
        try {

            String sql = "insert into produto(nome,categoria_id,fornecedor_id,pc_compra,pc_venda,qntd) values (?,?,?,?,?,?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setInt(2, produto.getCategoria_id().getId_Categoria());
            stm.setInt(3, produto.getFornecedor_id().getId());
            stm.setFloat(4, produto.getPc_Compra());
            stm.setFloat(5, produto.getPc_Venda());
            stm.setInt(6, produto.getQntd());
            stm.executeUpdate();
            mensagens.info("Produto inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir Produto : " + ex);
        }
    }

    public void alterarProd(produtoM produto) throws Exception {
        try {

            String sql = "update produto set nome=?, categoria_id=?, fornecedor_id=?, pc_compra=?, pc_venda=?, qntd=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setInt(2, produto.getCategoria_id().getId_Categoria());
            stm.setInt(3, produto.getFornecedor_id().getId());
            stm.setFloat(4, produto.getPc_Compra());
            stm.setFloat(5, produto.getPc_Venda());
            stm.setInt(6, produto.getQntd());
            stm.setInt(7, produto.getId());
            stm.executeUpdate();
            mensagens.info("Produto alterado com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar Produto : " + ex);
        }
    }

    public void excluirProd(produtoM produto) throws Exception {
        try {
            String sql = "delete from produto where id=?";
            stm = conector.prepareStatement(sql);
            stm.setInt(1, produto.getId());
            stm.executeUpdate();
            mensagens.info("Produto excluído com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir Produto : " + ex);
        }
    }

    public ObservableList<produtoM> listar_prod(String txtPesquisarProd) throws Exception {

        String sql = "select * from produto where nome like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarProd + "%");
        rs = stm.executeQuery();
        ObservableList listaProd = FXCollections.observableArrayList();
        while (rs.next()) {
            produtoM produto = new produtoM();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setCategoria_id(ControleDAO.getControleBanco().getCategoriaDAO().buscaCategoria(rs.getInt("categoria_id")));
            produto.setFornecedor_id(ControleDAO.getControleBanco().getFornecedorDAO().buscaFornecedor(rs.getInt("fornecedor_id")));
            produto.setPc_Compra(rs.getFloat("pc_compra"));
            produto.setPc_Venda(rs.getFloat("pc_venda"));
            produto.setQntd(rs.getInt("qntd"));
            listaProd.add(produto);
        }
        return listaProd;
    }
    
    public produtoM buscaProduto(int id) throws SQLException{
        String sql = "select * from produto where id like ?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, id);
        produtoM prod = null;
        rs = stm.executeQuery();
        while(rs.next()){
           prod = new produtoM((rs.getInt("id")), 
                   rs.getString("nome")
           );
        }
        stm.close();
        return prod;
    }
    public List<produtoM> relatProd() throws SQLException {

        List<produtoM> relatorioProd;
        relatorioProd = new ArrayList();
        String sql = "select nome, pc_compra, pc_venda from produto";
        stm = conector.prepareStatement(sql);
        rs = stm.executeQuery();
        while (rs.next()) {
            relatorioProd.add(new produtoM(rs.getString("nome"), rs.getFloat("pc_compra"), rs.getFloat("pc_venda")));
        }
        stm.close();
        return relatorioProd;
    }

}
