package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import util.diálogo;
import util.mensagens;

/**
 * FXML Controller class
 *
 * @author Fellipe
 */
public class ClientesController implements Initializable {
    
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
    private ComboBox<?> comboBoxEstadoCliente;

    @FXML
    private TextField txtCPFouCNPJ;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    @FXML
    private AnchorPane anchorPaneInicioCliente;

    @FXML
    private TableView<?> tableClienteFísico;

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
    private TextField txtNomeClienteJur;

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

    @FXML
    void handleButtonCancelar(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente?");
        if (resp == diálogo.Resposta.YES) {
            limparCampos();
            anchorPaneNovoClienteFísico.setVisible(false);
            anchorPaneInicioCliente.setVisible(true);
        }
    }

    @FXML
    void handleButtonInserirFísico(ActionEvent event) {
        anchorPaneInicioCliente.setVisible(false);
        anchorPaneNovoClienteFísico.setVisible(true);
        txtIdCliente.setText("");
    }

    @FXML
    void handleButtonInserirJur(ActionEvent event) {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    void limparCampos() {
        txtNomeCliente.setText("");
        comboBoxEstadoCliente.getItems().clear();
        txtCelularCliente.setText("");
        txtEmailCliente.setText("");
        txtEnderecoCliente.setText("");
        txtBairroCliente.setText("");
        comboBoxEstadoCliente.getItems().clear();
        txtCPFouCNPJ.setText("");
    }
    
}
