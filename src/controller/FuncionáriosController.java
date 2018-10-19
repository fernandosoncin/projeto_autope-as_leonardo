package controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.dao.CargoDAO;
import model.domain.Cargo;
import util.diálogo;
import util.mensagens;

/**
 * FXML Controller class
 *
 * @author Fellipe
 */
public class FuncionáriosController implements Initializable {

    private Stage palco;
    private Cargo cargo;
    private ObservableList<Cargo> data_cargo;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @FXML
    private TableColumn<Cargo, String> colunaIdCargo;

    @FXML
    private TableColumn<Cargo, String> colunaNomeCargo;

    @FXML
    private AnchorPane anchorPaneFunc;

    @FXML
    private AnchorPane anchorPaneInicioFunc;

    @FXML
    private TableView<?> tableFunc;

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private TextField txtPesquisarCargo;

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
    private Label lbTitulo1;

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
    private ComboBox<?> comboBoxEstadoFunc;

    @FXML
    private ComboBox<?> comboBoxTipoFunc;

    @FXML
    private TextField txtCPFFunc;

    @FXML
    private TextField txtSenhaFunc;

    @FXML
    private TextField txtRGFunc;

    @FXML
    private ComboBox<?> comboBoxCargoFunc;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @FXML
    private Label lbTitulo11;

    @FXML
    private TextField txtIdCargo;

    @FXML
    private TextField txtNomeCargo;

    @FXML
    private TableView<Cargo> tbCargos;

    @FXML
    private Button btSalvarCargo;

    @FXML
    private Button btExcluirCargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.cargo = new Cargo();
        setCellTable();
        atualizarListaCargo();
        selecionarItemTabelaCargos();

    }

    public void setCellTable() {
        colunaIdCargo.setCellValueFactory(new PropertyValueFactory<>("cod_Cargo"));
        colunaNomeCargo.setCellValueFactory(new PropertyValueFactory<>("nome_Cargo"));
    }

    @FXML
    void handleButtonInserirFunc(ActionEvent event) {
        //tabela.getSelectionModel().clearSelection();
        anchorPaneInicioFunc.setVisible(false);
        anchorPaneNovoFunc.setVisible(true);
        txtIdFunc.setText("");
    }

    @FXML
    private void atualizarListaCargo() {
        try {

            tbCargos.setItems(CargoDAO.listar_cargo(txtPesquisarCargo.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    @FXML
    public void selecionarItemTabelaCargos() {
        tbCargos.setOnMouseClicked(e -> {
            btSalvarCargo.setDisable(true);
            tbCargos.requestFocus();
            Cargo cargo = tbCargos.getItems().get(tbCargos.getSelectionModel().getSelectedIndex());
            txtIdCargo.setText(String.valueOf(cargo.getCod_Cargo()));
            txtNomeCargo.setText(cargo.getNome_Cargo());
        });

    }

    @FXML
    void handleButtonCancelar(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Funcionário?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposFunc();
            anchorPaneNovoFunc.setVisible(false);
            anchorPaneInicioFunc.setVisible(true);
        }
    }

    @FXML
    void handleButtonSalvarCargo(ActionEvent event) {
        if (validarDadosSalvar()) {
            try {
                cargo.setNome_Cargo(txtNomeCargo.getText());
                CargoDAO.salvar(cargo);
                limparCamposCargo();
                tbCargos.requestFocus();
                tbCargos.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
            tbCargos.getItems().clear();
            atualizarListaCargo();
        }
    }

    @FXML
    void handleButtonExcluirCargo(ActionEvent event) throws Exception {
        // Cliente cliente = tabela.getSelectionModel().getSelectedItem();      
        if (validarDadosExcluir()) {
            try {
                cargo.setCod_Cargo(Integer.parseInt(txtIdCargo.getText()));
                CargoDAO.removerCargo(cargo);
                tbCargos.getItems().clear();
                atualizarListaCargo();
                limparCamposCargo();
                btSalvarCargo.setDisable(false);
            } catch (SQLException ex) {
                mensagens.erro("Não foi possível remover cargo : " + ex.getMessage());
            }

        }
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
        txtIdFunc.setText("");
    }

    @FXML
    void limparCamposCargo() {
        txtIdCargo.setText("");
        txtNomeCargo.setText("");
        cargo.setCod_Cargo(0);
    }

    @FXML
    private boolean validarDadosSalvar() {
        //validação de campos
        String msgErroSalvar = "";

        if (txtNomeCargo.getText().length() == 0) {
            msgErroSalvar += "Nome de cargo inválido.\n";
        }
        //alertas de erros nos campos
        if (msgErroSalvar.length() == 0) {
            //se a variável msgErro tiver o tamanho 0, retorna true, não mostrando mensagem de erro
            return true;
        } else {
            mensagens.alerta(msgErroSalvar);
            return false;
        }
    }

    @FXML
    private boolean validarDadosExcluir() {
        //validação de campos
        String msgErroExcluir = "";

        if (txtIdCargo.getText().length() == 0) {
            msgErroExcluir += "Dados para exclusão inválidos.\n";
        }
        //alertas de erros nos campos
        if (msgErroExcluir.length() == 0) {
            //se a variável msgErro tiver o tamanho 0, retorna true, não mostrando mensagem de erro
            return true;
        } else {
            mensagens.alerta(msgErroExcluir);
            return false;
        }
    }

}
