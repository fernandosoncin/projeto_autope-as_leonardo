package controller;

import banco.DAO.ControleDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.domain.produtoM;
import util.Combo;
import util.diálogo;
import util.mensagens;

public class ProdutosController implements Initializable {
    private produtoM produto;

    @FXML
    private AnchorPane anchorPaneProd;

    @FXML
    private AnchorPane anchorPaneInicioProd;

    @FXML
    private TableView<produtoM> tbProd;

    @FXML
    private TableColumn<?, ?> TableColumnIdProd;

    @FXML
    private TableColumn<?, ?> TableColumnNomeProd;

    @FXML
    private TableColumn<?, ?> TableColumnCategoriaProd;

    @FXML
    private TableColumn<?, ?> TableColumnValorProd;

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField txtPesquisarProd;

    @FXML
    private ToggleButton btNovoProd;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarProd;

    @FXML
    private ToggleButton btExcluirProd;

    @FXML
    private AnchorPane anchorPaneNovoProd;

    @FXML
    private Label lbTitulo1;

    @FXML
    private TextField txtIdProd;

    @FXML
    private TextField txtNomeProd;

    @FXML
    private TextField txtValorProd;

    @FXML
    private ComboBox<String> comboBoxCategoriaProd;

    @FXML
    private Button btSalvarProd;

    @FXML
    private Button btCancelarProd;
    
    //----------Início Padrão
    public void preenchercomboBoxCategoria() {
        Combo.popular(comboBoxCategoriaProd, ControleDAO.getControleBanco().getProdutoDAO().comboCategoria());
    }
    
    @FXML
    void eventKeyPressedEnterProd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaProd();
        }

    }
    //----------Fim Padrão
    
    //----------Início Produtos


    @FXML
    void handleButtonAlterarProd(ActionEvent event) {
        if (tbProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Produto para alteração.");
            desativarbtsEditareExcluirProd();
        } else {
            anchorPaneInicioProd.setVisible(false);
            anchorPaneNovoProd.setVisible(true);
            produtoM produto = tbProd.getItems().get(tbProd.getSelectionModel().getSelectedIndex());
            txtIdProd.setText(String.valueOf(produto.getId()));
            txtNomeProd.setText(produto.getNome());
            comboBoxCategoriaProd.setValue(produto.getCategoria());
            txtValorProd.setText(String.valueOf(produto.getValor()));
            desativarbtsEditareExcluirProd();
        }
    }

    @FXML
    void handleButtonCancelarProd(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Produto?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposProd();
            anchorPaneNovoProd.setVisible(false);
            anchorPaneInicioProd.setVisible(true);
            desativarbtsEditareExcluirProd();
        }
    }

    @FXML
    void handleButtonExcluirProd(ActionEvent event) throws Exception {
        if (tbProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Produto para exclusão.");
            desativarbtsEditareExcluirProd();
        } else {
            produtoM produto = tbProd.getItems().get(tbProd.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getProdutoDAO().excluirProd(produto);
            desativarbtsEditareExcluirProd();
            atualizarListaProd();
            tbProd.getSelectionModel().clearSelection();
            tbProd.refresh();
            
        }
    }

    @FXML
    void handleButtonInserirProd(ActionEvent event) {
        anchorPaneInicioProd.setVisible(false);
        anchorPaneNovoProd.setVisible(true);
        txtIdProd.setText("0");
    }

    @FXML
    void handleButtonSalvarProd(ActionEvent event) {
        try {
            produto.setId(Integer.parseInt((txtIdProd.getText())));
            produto.setNome(txtNomeProd.getText());
            produto.setCategoria(comboBoxCategoriaProd.getValue().toString());
            produto.setValor(Float.valueOf(txtValorProd.getText()));
            ControleDAO.getControleBanco().getProdutoDAO().salvarProd(produto);
            anchorPaneNovoProd.setVisible(false);
            anchorPaneInicioProd.setVisible(true);
            limparCamposProd();
            atualizarListaProd();
            tbProd.refresh();
            desativarbtsEditareExcluirProd();
            tbProd.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
        }
    }

    void desativarbtsEditareExcluirProd() {
        btAlterarProd.setDisable(true);
        btExcluirProd.setDisable(true);
    }
    
    void limparCamposProd() {
        txtIdProd.setText("0");
        txtNomeProd.setText("");
        comboBoxCategoriaProd.getItems().clear();
        txtValorProd.setText("");
        preenchercomboBoxCategoria();
    }
    
    private void atualizarListaProd() {
        try {

            tbProd.setItems(ControleDAO.getControleBanco().getProdutoDAO().listar_prod(txtPesquisarProd.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }
    
    public void setCellTableFunc() {
        TableColumnIdProd.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnCategoriaProd.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        TableColumnValorProd.setCellValueFactory(new PropertyValueFactory<>("valor"));

    }
    
    public void selecionarItemTabelaProd() {
        tbProd.setOnMouseClicked(e -> {
            tbProd.requestFocus();
            btExcluirProd.setDisable(false);
            btAlterarProd.setDisable(false);
        });

    }
    //----------Fim Produtos

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //----------Início Inicializador Padrão
        preenchercomboBoxCategoria();
        //----------Fim Inicializador Padrão
        //----------Início Inicializador Produtos
        this.produto = new produtoM();
        setCellTableFunc();
        atualizarListaProd();
        selecionarItemTabelaProd();
        //----------Fim Inicializador Produtos
    }

}
