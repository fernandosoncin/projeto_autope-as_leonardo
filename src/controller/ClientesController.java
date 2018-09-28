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
    private AnchorPane anchorPaneInicioCliente;
    
    @FXML
    private TableView<?> tableCliente;

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private ToggleButton btNovoCliente;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarCliente;

    @FXML
    private ToggleButton btExcluirCliente;

    @FXML
    private AnchorPane anchorPaneNovoCliente;

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
    private ComboBox<?> comboBoxFJ;

    @FXML
    private TextField txtCPFouCNPJ;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    void handleButtonInserir(ActionEvent event) {
        //tabela.getSelectionModel().clearSelection();
        anchorPaneInicioCliente.setVisible(false);
        anchorPaneNovoCliente.setVisible(true);
        txtIdCliente.setText("");
    }
    
    @FXML
    void handleButtonCancelar(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente?");
        if (resp == diálogo.Resposta.YES) {
            limparCampos();
            anchorPaneNovoCliente.setVisible(false);
            anchorPaneInicioCliente.setVisible(true);
        }
    }
    
    @FXML
    void limparCampos() {
        txtNomeCliente.setText("");
        comboBoxEstadoCliente.getItems().clear();
        txtCelularCliente.setText("");
        txtEmailCliente.setText("");
        txtEnderecoCliente.setText("");
        txtBairroCliente.setText("");
        comboBoxFJ.getItems().clear();
        txtCPFouCNPJ.setText("");
    }
    
}
