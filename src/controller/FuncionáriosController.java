package controller;

import banco.DAO.ControleDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.domain.funcionarioM;
import util.Combo;
import util.diálogo;
import util.mensagens;

/**
 * FXML Controller class
 *
 * @author Fellipe
 */
public class FuncionáriosController implements Initializable {

    private funcionarioM funcionario;

    @FXML
    private AnchorPane anchorPaneFunc;

    @FXML
    private AnchorPane anchorPaneInicioFunc;

    @FXML
    private TextField txtPesquisarFunc;

    @FXML
    private ToggleButton btNovoFunc;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarFunc;

    @FXML
    private ToggleButton btExcluirFunc;

    @FXML
    private AnchorPane anchorPaneNovoFunc;

    @FXML
    private TextField txtIdFunc;

    @FXML
    private TextField txtNomeFunc;

    @FXML
    private TextField txtCelularFunc;

    @FXML
    private TextField txtEmailFunc;

    @FXML
    private TextField txtEnderecoFunc;

    @FXML
    private TextField txtBairroFunc;

    @FXML
    private ComboBox<String> comboBoxEstadoFunc;

    @FXML
    private ComboBox<String> comboBoxTipoFunc;

    @FXML
    private TextField txtCPFFunc;

    @FXML
    private TextField txtSenhaFunc;

    @FXML
    private TextField txtRGFunc;

    @FXML
    private ComboBox<String> comboBoxCargoFunc;

    @FXML
    private Button btSalvarFunc;

    @FXML
    private Button btCancelarFunc;

    @FXML
    private TableView<funcionarioM> tbFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnIdFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnNomeFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnRGFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnCPFFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnCelFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnEmailFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnTipoFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnSenhaFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnCargoFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnEndFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnBairroFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnEstadoFunc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //----------Início Inicializador Padrão
        preenchercomboBoxCargos();
        //----------Fim Inicializador Padrão

        //----------Início Inicializador Funcionários
        this.funcionario = new funcionarioM();
        preenchercomboEstadoFunc();
        preenchercomboTipo();
        preenchercomboBoxCargos();
        setCellTableFunc();
        atualizarListaFunc();
        selecionarItemTabelaFunc();

        //----------Fim Inicializador Funcionários
    }

    //----------Início Padrão
    public void preenchercomboBoxCargos() {
        Combo.popular(comboBoxCargoFunc, ControleDAO.getControleBanco().getFuncionárioDAO().comboCargo());
        
        
    }
        @FXML
    void eventKeyPressedEnterFunc(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaFunc();
        }

    }    //----------Fim Padrão
    //----------Início Funcionários

    private void preenchercomboEstadoFunc() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoFunc, tipo);
    }

    private void preenchercomboTipo() {
        ObservableList<String> tipo = FXCollections.observableArrayList("Comum", "ADM");
        Combo.popular(comboBoxTipoFunc, tipo);
    }

    private void atualizarListaFunc() {
        try {

            tbFunc.setItems(ControleDAO.getControleBanco().getFuncionárioDAO().listar_func(txtPesquisarFunc.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    public void setCellTableFunc() {
        TableColumnIdFunc.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeFunc.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnRGFunc.setCellValueFactory(new PropertyValueFactory<>("rg"));
        TableColumnCPFFunc.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        TableColumnSenhaFunc.setCellValueFactory(new PropertyValueFactory<>("senha"));
        TableColumnCelFunc.setCellValueFactory(new PropertyValueFactory<>("celular"));
        TableColumnEmailFunc.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumnTipoFunc.setCellValueFactory(new PropertyValueFactory<>("admin"));
        TableColumnCargoFunc.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        TableColumnEndFunc.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumnBairroFunc.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        TableColumnEstadoFunc.setCellValueFactory(new PropertyValueFactory<>("estado"));

    }

    public void selecionarItemTabelaFunc() {
        tbFunc.setOnMouseClicked(e -> {
            tbFunc.requestFocus();
            btExcluirFunc.setDisable(false);
            btAlterarFunc.setDisable(false);
        });

    }

    void desativarbtsEditareExcluirFunc() {
        btAlterarFunc.setDisable(true);
        btExcluirFunc.setDisable(true);
    }

    @FXML
    void handleButtonSalvarFunc(ActionEvent event) {
        try {
            funcionario.setId(Integer.parseInt((txtIdFunc.getText())));
            funcionario.setNome(txtNomeFunc.getText());
            funcionario.setRg(txtRGFunc.getText());
            funcionario.setCpf(txtCPFFunc.getText());
            funcionario.setSenha(txtSenhaFunc.getText());
            funcionario.setCelular(txtCelularFunc.getText());
            funcionario.setEmail(txtEmailFunc.getText());
            funcionario.setAdmin(comboBoxTipoFunc.getValue().toString());
            funcionario.setCargo(comboBoxCargoFunc.getValue());
            funcionario.setEndereco(txtSenhaFunc.getText());
            funcionario.setBairro(txtBairroFunc.getText());
            funcionario.setEstado(comboBoxEstadoFunc.getValue().toString());
            ControleDAO.getControleBanco().getFuncionárioDAO().salvarFunc(funcionario);
            anchorPaneNovoFunc.setVisible(false);
            anchorPaneInicioFunc.setVisible(true);
            limparCamposFunc();
            atualizarListaFunc();
            tbFunc.refresh();
            desativarbtsEditareExcluirFunc();
            tbFunc.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
        }
    }

    @FXML
    void handlebuttonAlterarFunc() {
        if (tbFunc.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Funcionário para alteração.");
            desativarbtsEditareExcluirFunc();
        } else {
            anchorPaneInicioFunc.setVisible(false);
            anchorPaneNovoFunc.setVisible(true);
            funcionarioM funcionario = tbFunc.getItems().get(tbFunc.getSelectionModel().getSelectedIndex());
            txtIdFunc.setText(String.valueOf(funcionario.getId()));
            txtNomeFunc.setText(funcionario.getNome());
            txtRGFunc.setText(funcionario.getRg());
            txtSenhaFunc.setText(funcionario.getSenha());
            txtCelularFunc.setText(funcionario.getCelular());
            txtEmailFunc.setText(funcionario.getEmail());
            comboBoxTipoFunc.setValue(funcionario.getAdmin());
            comboBoxCargoFunc.setValue(funcionario.getCargo());
            txtEnderecoFunc.setText(funcionario.getEndereco());
            txtBairroFunc.setText(funcionario.getBairro());
            comboBoxTipoFunc.setValue(funcionario.getAdmin());
            comboBoxEstadoFunc.setValue(funcionario.getEstado());
            desativarbtsEditareExcluirFunc();
        }

    }
    
    @FXML
    void handlebuttonExcluirFunc() throws Exception {
        if (tbFunc.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Funcionário para exclusão.");
            desativarbtsEditareExcluirFunc();
        } else {
            funcionarioM funcionario = tbFunc.getItems().get(tbFunc.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getFuncionárioDAO().excluirFunc(funcionario);
            desativarbtsEditareExcluirFunc();
            atualizarListaFunc();
            tbFunc.refresh();
            tbFunc.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void handleButtonCancelarFunc(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Funcionário?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposFunc();
            anchorPaneNovoFunc.setVisible(false);
            anchorPaneInicioFunc.setVisible(true);
        }
    }

    @FXML
    void handleButtonInserirFunc(ActionEvent event) {
        //tabela.getSelectionModel().clearSelection();
        anchorPaneInicioFunc.setVisible(false);
        anchorPaneNovoFunc.setVisible(true);
    }

    @FXML
    void limparCamposFunc() {
        txtNomeFunc.setText("");
        comboBoxEstadoFunc.getItems().clear();
        txtCelularFunc.setText("");
        txtEmailFunc.setText("");
        txtEnderecoFunc.setText("");
        txtBairroFunc.setText("");
        comboBoxCargoFunc.getItems().clear();
        txtCPFFunc.setText("");
        comboBoxTipoFunc.getItems().clear();
        txtSenhaFunc.setText("");
        txtRGFunc.setText("");
        txtIdFunc.setText("0");
        preenchercomboEstadoFunc();
        preenchercomboTipo();
        preenchercomboBoxCargos();
    }

    //----------Fim Funcionários
}
