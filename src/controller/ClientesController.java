package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.AnchorPane;
import model.dao.ClienteDAO;
import model.domain.clienteMF;
import util.Combo;
import util.diálogo;
import util.mensagens;

/**
 * FXML Controller class
 *
 * @author Fellipe
 */
public class ClientesController implements Initializable {

    private clienteMF cliente;
    private ObservableList<clienteMF> data_cliente;

    @FXML
    private AnchorPane anchorPaneCliente;

    @FXML
    private AnchorPane anchorPaneNovoClienteFísico;

    @FXML
    private Label lbTitulo1;

    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtCelularCliente;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TextField txtEnderecoCliente;

    @FXML
    private TextField txtBairroCliente;

    @FXML
    private ComboBox<String> comboBoxEstadoCliente;

    @FXML
    private TextField txtCPFouCNPJ;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @FXML
    private TableColumn<clienteMF, String> TableColumnIdFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnNomeFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnCpfFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnCelularFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnEmailFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnEnderecoFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnBairroFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnEstadoFisico;

    @FXML
    private AnchorPane anchorPaneInicioCliente;

    @FXML
    private TableView<clienteMF> tableClienteFísico;

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private ToggleButton btNovoClienteFísico;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarClienteFísico;

    @FXML
    private ToggleButton btExcluirClienteFísico;

    @FXML
    private AnchorPane anchorPaneInicioCliente1;

    @FXML
    private TableView<?> tableClienteJur;

    @FXML
    private Label lbTitulo2;

    @FXML
    private TextField txtPesquisarJur;

    @FXML
    private ToggleButton btNovoClienteJur;

    @FXML
    private ToggleGroup menu1;

    @FXML
    private ToggleButton btAlterarClienteJur;

    @FXML
    private ToggleButton btExcluirClienteJur;

    @FXML
    private AnchorPane anchorPaneNovoClienteJur;

    @FXML
    private Label lbTitulo11;

    @FXML
    private TextField txtIdClienteJur;

    @FXML
    private TextField txtRazãoSocial;

    @FXML
    private TextField txtCelularClienteJur;

    @FXML
    private TextField txtEmailClienteJur;

    @FXML
    private TextField txtEnderecoClienteJur;

    @FXML
    private TextField txtBairroClienteJur;

    @FXML
    private ComboBox<?> comboBoxEstadoClienteJur;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private Button btSalvarJur;

    @FXML
    private Button btCancelarJur;

    private void comboEstado() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoCliente, tipo);
    }

    @FXML
    void handleButtonSalvarFísico(ActionEvent event) {
        try {
            cliente.setId(Integer.parseInt((txtIdCliente.getText())));
            cliente.setNome(txtNomeCliente.getText());
            cliente.setCpf(txtCPFouCNPJ.getText());
            cliente.setCelular(txtCelularCliente.getText());
            cliente.setEmail(txtEmailCliente.getText());
            cliente.setEndereco(txtEnderecoCliente.getText());
            cliente.setBairro(txtBairroCliente.getText());
            cliente.setEstado(comboBoxEstadoCliente.getValue().toString());
            ClienteDAO.salvarF(cliente);
            anchorPaneNovoClienteFísico.setVisible(false);
            anchorPaneInicioCliente.setVisible(true);
            limparCamposFísico();
            atualizarListaClienteFisico();
            tableClienteFísico.refresh();
            desativarbtsEditareExcluirF();
            tableClienteFísico.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
        }
        //tbCargos.getItems().clear();
        //atualizarListaCargo();
    }

    @FXML
    void handleButtonCancelar(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposFísico();
            anchorPaneNovoClienteFísico.setVisible(false);
            anchorPaneInicioCliente.setVisible(true);
            desativarbtsEditareExcluirF();
        }
    }

    @FXML
    void handleButtonCancelarJur(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente?");
        if (resp == diálogo.Resposta.YES) {
            //limparCamposJur();
            anchorPaneNovoClienteJur.setVisible(false);
            anchorPaneInicioCliente1.setVisible(true);

        }
    }

    @FXML
    void handleButtonInserirFísico(ActionEvent event) {
        anchorPaneInicioCliente.setVisible(false);
        anchorPaneNovoClienteFísico.setVisible(true);
        txtIdCliente.setText("0");

    }

    @FXML
    void handleButtonInserirJur(ActionEvent event) {
        anchorPaneInicioCliente1.setVisible(false);
        anchorPaneNovoClienteJur.setVisible(true);
        txtIdClienteJur.setText("");
    }

    @FXML
    private void atualizarListaClienteFisico() {
        try {

            tableClienteFísico.setItems(ClienteDAO.listar_clienteF(txtPesquisar.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    public void setCellTableClienteF() {
        TableColumnIdFisico.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeFisico.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnCpfFisico.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        TableColumnCelularFisico.setCellValueFactory(new PropertyValueFactory<>("celular"));
        TableColumnEmailFisico.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumnEnderecoFisico.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumnBairroFisico.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        TableColumnEstadoFisico.setCellValueFactory(new PropertyValueFactory<>("estado"));

    }

    @FXML
    public void selecionarItemTabelaClienteFísico() {
        tableClienteFísico.setOnMouseClicked(e -> {
            tableClienteFísico.requestFocus();
            btExcluirClienteFísico.setDisable(false);
            btAlterarClienteFísico.setDisable(false);
        });

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.cliente = new clienteMF();
        comboEstado();
        setCellTableClienteF();
        atualizarListaClienteFisico();
        //tableClienteFísico.getSelectionModel().selectFirst();
        selecionarItemTabelaClienteFísico();

    }

    @FXML
    void limparCamposFísico() {
        txtIdCliente.setText("0");
        txtNomeCliente.setText("");
        comboBoxEstadoCliente.getItems().clear();
        txtCelularCliente.setText("");
        txtEmailCliente.setText("");
        txtEnderecoCliente.setText("");
        txtBairroCliente.setText("");
        comboBoxEstadoCliente.getItems().clear();
        txtCPFouCNPJ.setText("");
        comboEstado();
    }

    @FXML
    void handlebuttonAlterarFísico() {
        if (tableClienteFísico.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Físico para alteração.");
            desativarbtsEditareExcluirF();
        } else {
            anchorPaneInicioCliente.setVisible(false);
            anchorPaneNovoClienteFísico.setVisible(true);
            clienteMF cliente = tableClienteFísico.getItems().get(tableClienteFísico.getSelectionModel().getSelectedIndex());
            txtIdCliente.setText(String.valueOf(cliente.getId()));
            txtNomeCliente.setText(cliente.getNome());
            txtCPFouCNPJ.setText(cliente.getCpf());
            txtCelularCliente.setText(cliente.getCelular());
            txtEmailCliente.setText(cliente.getEmail());
            txtEnderecoCliente.setText(cliente.getEndereco());
            txtBairroCliente.setText(cliente.getBairro());
            comboBoxEstadoCliente.setValue(cliente.getEstado());
            desativarbtsEditareExcluirF();
        }

    }

    void desativarbtsEditareExcluirF() {
        btAlterarClienteFísico.setDisable(true);
        btExcluirClienteFísico.setDisable(true);
    }

    @FXML
    void handlebuttonExcluirFísico() throws Exception {
        if (tableClienteFísico.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Físico para exclusão.");
            desativarbtsEditareExcluirF();
        } else {
            clienteMF cliente = tableClienteFísico.getItems().get(tableClienteFísico.getSelectionModel().getSelectedIndex());
            ClienteDAO.excluirClienteF(cliente);
            desativarbtsEditareExcluirF();
            atualizarListaClienteFisico();
            tableClienteFísico.refresh();
            tableClienteFísico.getSelectionModel().clearSelection();
        }

    }

}
