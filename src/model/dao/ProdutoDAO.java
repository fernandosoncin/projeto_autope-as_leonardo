
package model.dao;

import banco.DAO.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.produtoM;
import util.mensagens;

public class ProdutoDAO extends DAO{
    public void salvarProd(produtoM produto) throws Exception {
        if (produto.getId() == 0) {
            inserirProd(produto);
        } else {
           alterarProd(produto);
        }
    }
    
    public void inserirProd(produtoM produto) throws Exception {
        try {

            String sql = "insert into produto(nome,categoria,valor) values (?,?,?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getCategoria());
            stm.setFloat(3, produto.getValor());
            stm.executeUpdate();
            mensagens.info("Produto inserido com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir Produto : " + ex);
        }
    }
    
    public void alterarProd(produtoM produto) throws Exception {
        try {

            String sql = "update produto set nome=?, categoria=?, valor=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, produto.getNome());
            stm.setString(2, produto.getCategoria());
            stm.setFloat(3, produto.getValor());
            stm.setInt(4, produto.getId());
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
            mensagens.erro("Erro ao excluir Produto : "+ex);
        }
    }
    
    public ObservableList<produtoM> listar_prod(String txtPesquisarProd) throws Exception {

        String sql = "select * from produto where id like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarProd + "%");
        rs = stm.executeQuery();
        ObservableList listaProd = FXCollections.observableArrayList();
        while (rs.next()) {
            produtoM produto = new produtoM();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setCategoria(rs.getString("categoria"));
            produto.setValor(rs.getFloat("valor"));
            listaProd.add(produto);
        }
        return listaProd;
    }
    
    public List<produtoM> comboCategoria() {

        List<produtoM> dadosCategoria = new ArrayList<>();

        try {
            String sql = "select distinct(categoria) from produto";

            stm = conector.prepareStatement(sql);
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                produtoM produto = new produtoM(rs.getString("categoria"));
                dadosCategoria.add(produto);
            }

            stm.close();
            rs.close();

        } catch (SQLException ex) {
            mensagens.erro("Não foi possível preencher lista de produtos : \n" + ex);
        }

        return dadosCategoria;
    }
    
    public List<produtoM> relatProd() throws SQLException{
        
        List<produtoM> relatorioProd;
        relatorioProd = new ArrayList();
        String sql = "select nome, categoria from produto";
        stm = conector.prepareStatement(sql);
        rs = stm.executeQuery();
        while(rs.next()){
            
        relatorioProd.add(new produtoM(rs.getString("nome"),rs.getString("categoria")));
        }
        stm.close();
        return relatorioProd;      
    }
    
}
