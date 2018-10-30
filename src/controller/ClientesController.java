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
import model.domain.clienteMF;
import model.domain.clienteMJ;
import util.Combo;
import util.diálogo;
import util.mensagens;

/**
 * FXML Controller class
 *
 * @author Fellipe
 */
public class ClientesController implements Initializable {

    private clienteMF clienteF;
    private clienteMJ clienteJ;

    @FXML
    private AnchorPane anchorPaneCliente;

    @FXML
    private AnchorPane anchorPaneNovoClienteFísico;

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
    private TableColumn<clienteMJ, String> TableColumnIdJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnRazãoSocial;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnCNPJ;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnTelefoneJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnEmailJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnEnderecoJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnBairroJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnEstadoJur;

    @FXML
    private AnchorPane anchorPaneInicioCliente;

    @FXML
    private TableView<clienteMF> tableClienteFísico;

    @FXML
    private TableView<clienteMJ> tableClienteJur;

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
    private TextField txtIdClienteJur;

    @FXML
    private TextField txtRazãoSocial;

    @FXML
    private TextField txtTelefoneClienteJur;

    @FXML
    private TextField txtEmailClienteJur;

    @FXML
    private TextField txtEnderecoClienteJur;

    @FXML
    private TextField txtBairroClienteJur;

    @FXML
    private ComboBox<String> comboBoxEstadoClienteJur;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private Button btSalvarJur;

    @FXML
    private Button btCancelarJur;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //----------Início Inicializador Padrão
        comboEstadoF();
        comboEstadoJ();
        //----------Fim Inicializador Padrão
        //----------Início Inicializador Cliente Físico
        this.clienteF = new clienteMF();
        comboEstadoF();
        setCellTableClienteF();
        atualizarListaClienteFisico();
        selecionarItemTabelaClienteFísico();
        //----------Fim Inicializador Cliente Físico

        //----------Início Inicializador Cliente Jurídico
        this.clienteJ = new clienteMJ();
        setCellTableClienteJ();
        atualizarListaClienteJur();
        selecionarItemTabelaClienteJur();
        //----------Fim Inicializador Cliente Jurídico
    }

    //----------Início Padrão
    private void comboEstadoF() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoCliente, tipo);
    }

    private void comboEstadoJ() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoClienteJur, tipo);
    }
    //----------Fim Padrão

    //----------Início Cliente Físico
    @FXML
    void handleButtonSalvarFísico(ActionEvent event) {
        try {
            clienteF.setId(Integer.parseInt((txtIdCliente.getText())));
            clienteF.setNome(txtNomeCliente.getText());
            clienteF.setCpf(txtCPFouCNPJ.getText());
            clienteF.setCelular(txtCelularCliente.getText());
            clienteF.setEmail(txtEmailCliente.getText());
            clienteF.setEndereco(txtEnderecoCliente.getText());
            clienteF.setBairro(txtBairroCliente.getText());
            clienteF.setEstado(comboBoxEstadoCliente.getValue().toString());
            ControleDAO.getControleBanco().getClienteFDAO().salvarF(clienteF);
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
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente Físico?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposFísico();
            anchorPaneNovoClienteFísico.setVisible(false);
            anchorPaneInicioCliente.setVisible(true);
            desativarbtsEditareExcluirF();
        }
    }

    @FXML
    void handleButtonInserirFísico(ActionEvent event) {
        anchorPaneInicioCliente.setVisible(false);
        anchorPaneNovoClienteFísico.setVisible(true);
        txtIdCliente.setText("0");

    }

    @FXML
    private void atualizarListaClienteFisico() {
        try {

            tableClienteFísico.setItems(ControleDAO.getControleBanco().getClienteFDAO().listar_clienteF(txtPesquisar.getText()));
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
        comboEstadoF();
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
            clienteMF clienteF = tableClienteFísico.getItems().get(tableClienteFísico.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getClienteFDAO().excluirClienteF(clienteF);
            desativarbtsEditareExcluirF();
            atualizarListaClienteFisico();
            tableClienteFísico.refresh();
            tableClienteFísico.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void eventKeyPressedEnterF(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaClienteFisico();
        }

    }

    //----------Fim Cliente Físico
    //----------Início Cliente Jurídico
    @FXML
    public void selecionarItemTabelaClienteJur() {
        tableClienteJur.setOnMouseClicked(e -> {
            tableClienteJur.requestFocus();
            btExcluirClienteJur.setDisable(false);
            btAlterarClienteJur.setDisable(false);
        });

    }

    @FXML
    void eventKeyPressedEnterJ(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaClienteJur();
        }

    }

    @FXML
    private void atualizarListaClienteJur() {
        try {

            tableClienteJur.setItems(ControleDAO.getControleBanco().getClienteJDAO().listar_clienteJ(txtPesquisarJur.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    void desativarbtsEditareExcluirJ() {
        btAlterarClienteJur.setDisable(true);
        btExcluirClienteJur.setDisable(true);
    }

    @FXML
    void limparCamposJ() {
        txtIdClienteJur.setText("0");
        txtRazãoSocial.setText("");
        txtCNPJ.setText("");
        txtTelefoneClienteJur.setText("");
        txtEmailClienteJur.setText("");
        txtEnderecoClienteJur.setText("");
        txtBairroClienteJur.setText("");
        comboBoxEstadoClienteJur.getItems().clear();
        comboEstadoJ();
    }

    @FXML
    void handleButtonCancelarJur(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente Jurídico?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposJ();
            anchorPaneNovoClienteJur.setVisible(false);
            anchorPaneInicioCliente1.setVisible(true);

        }
    }

    @FXML
    void handleButtonInserirJur(ActionEvent event) {
        anchorPaneInicioCliente1.setVisible(false);
        anchorPaneNovoClienteJur.setVisible(true);
        txtIdClienteJur.setText("0");
    }

    public void setCellTableClienteJ() {
        TableColumnIdJur.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnRazãoSocial.setCellValueFactory(new PropertyValueFactory<>("rs"));
        TableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        TableColumnTelefoneJur.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        TableColumnEmailJur.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumnEnderecoJur.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumnBairroJur.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        TableColumnEstadoJur.setCellValueFactory(new PropertyValueFactory<>("estado"));

    }

    @FXML
    void handleButtonSalvarJ(ActionEvent event) {
        try {
            clienteJ.setId(Integer.parseInt((txtIdClienteJur.getText())));
            clienteJ.setRs(txtRazãoSocial.getText());
            clienteJ.setCnpj(txtCNPJ.getText());
            clienteJ.setTelefone(txtTelefoneClienteJur.getText());
            clienteJ.setEmail(txtEmailClienteJur.getText());
            clienteJ.setEndereco(txtEnderecoClienteJur.getText());
            clienteJ.setBairro(txtBairroClienteJur.getText());
            clienteJ.setEstado(comboBoxEstadoClienteJur.getValue().toString());
            ControleDAO.getControleBanco().getClienteJDAO().salvarJ(clienteJ);
            anchorPaneNovoClienteJur.setVisible(false);
            anchorPaneInicioCliente1.setVisible(true);
            limparCamposJ();
            atualizarListaClienteJur();
            tableClienteJur.refresh();
            desativarbtsEditareExcluirJ();
            tableClienteJur.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
        }
        //tbCargos.getItems().clear();
        //atualizarListaCargo();
    }

    @FXML
    void handlebuttonAlterarJ() {
        if (tableClienteJur.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Jurídico para alteração.");
            desativarbtsEditareExcluirJ();
        } else {
            anchorPaneInicioCliente1.setVisible(false);
            anchorPaneNovoClienteJur.setVisible(true);
            clienteMJ clienteJ = tableClienteJur.getItems().get(tableClienteJur.getSelectionModel().getSelectedIndex());
            txtIdClienteJur.setText(String.valueOf(clienteJ.getId()));
            txtRazãoSocial.setText(clienteJ.getRs());
            txtCNPJ.setText(clienteJ.getCnpj());
            txtTelefoneClienteJur.setText(clienteJ.getTelefone());
            txtEmailClienteJur.setText(clienteJ.getEmail());
            txtEnderecoClienteJur.setText(clienteJ.getEndereco());
            txtBairroClienteJur.setText(clienteJ.getBairro());
            comboBoxEstadoClienteJur.setValue(clienteJ.getEstado());
            desativarbtsEditareExcluirF();
        }

    }

    @FXML
    void handlebuttonExcluirJ() throws Exception {
        if (tableClienteJur.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Jurídico para exclusão.");
            desativarbtsEditareExcluirJ();
        } else {
            clienteMJ clienteJ = tableClienteJur.getItems().get(tableClienteJur.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getClienteJDAO().excluirClienteJ(clienteJ);
            desativarbtsEditareExcluirJ();
            atualizarListaClienteJur();
            tableClienteJur.refresh();
            tableClienteJur.getSelectionModel().clearSelection();
        }

    }

    //----------Fim Cliente Jurídico
}
