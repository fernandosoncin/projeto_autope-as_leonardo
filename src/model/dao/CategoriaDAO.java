package model.dao;

import banco.DAO.DAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.categoriaM;
import util.mensagens;

public class CategoriaDAO extends DAO{

    public void salvarCategoria(categoriaM categoria) throws Exception {
        if (categoria.getId_Categoria() == 0) {
            inserir(categoria);
        } else {
            alterar(categoria);
        }
    }

    public void inserir(categoriaM categoria) throws Exception {
        try {

            String sql = "insert into categoria_p(nome) values (?)";
            stm = conector.prepareStatement(sql);
            stm.setString(1, categoria.getNome_Categoria());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Categoria inserida com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao inserir categoria : " + ex);
        }
    }

    public void alterar(categoriaM categoria) throws Exception {
        try {

            String sql = "update categoria_p set nome=? where id=?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, categoria.getNome_Categoria());
            stm.setInt(2, categoria.getId_Categoria());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Categoria alterada com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao alterar categoria : " + ex);
        }
    }

    public void removerCategoria(categoriaM categoria) throws Exception {
        try {
            String sql = "delete from categoria_p where id=?";
            stm = conector.prepareStatement(sql);
            stm.setInt(1, categoria.getId_Categoria());
            stm.executeUpdate();
            stm.close();
            mensagens.info("Categoria excluída com sucesso!");
        } catch (SQLException ex) {
            mensagens.erro("Erro ao excluir categoria.\nVerifique se existe algum produto vinculado a essa categoria.\nErro : \n" + ex);
        }
    }

    public List<categoriaM> comboCategoria() {
        List<categoriaM> dados = new ArrayList<>();
        try {
            String sql = "SELECT id,nome FROM categoria_p ORDER BY nome ";

            stm = conector.prepareStatement(sql);
            rs = stm.executeQuery(sql);

            while (rs.next()) {
                dados.add(new categoriaM(rs.getInt(1), rs.getString(2)));
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            mensagens.erro("Erro ao carregar categorias cadastradas : \n" + ex);
        }
        return dados;
    }

    public ObservableList<categoriaM> listar_categoria(String txtPesquisarCategoriaProd) throws Exception {

        String sql = "select * from categoria_p where nome like ?";
        stm = conector.prepareStatement(sql);
        stm.setString(1, "%" + txtPesquisarCategoriaProd + "%");
        rs = stm.executeQuery();
        ObservableList listaCategoria = FXCollections.observableArrayList();
        while (rs.next()) {
            categoriaM categoria = new categoriaM();
            categoria.setId_Categoria(rs.getInt("id"));
            categoria.setNome_Categoria(rs.getString("nome"));
            listaCategoria.add(categoria);
        }
        return listaCategoria;
    }
    
    public categoriaM buscaCategoria(int id) throws SQLException{
        String sql = "select * from categoria_p where id like ?";
        stm = conector.prepareStatement(sql);
        stm.setInt(1, id);
        categoriaM cat = null;
        rs = stm.executeQuery();
        while(rs.next()){
           cat = new categoriaM((rs.getInt("id")), 
                   rs.getString("nome")
           );
        }
        stm.close();
        return cat;
    }

    public List<categoriaM> relatCategoria() throws SQLException {

        List<categoriaM> relatorioCategoria;
        relatorioCategoria = new ArrayList();
        String sql = "select nome from categoria_p";
        stm = conector.prepareStatement(sql);
        rs = stm.executeQuery();
        while (rs.next()) {

            relatorioCategoria.add(new categoriaM(rs.getString("nome")));
        }
        stm.close();
        return relatorioCategoria;
    }
    
}
