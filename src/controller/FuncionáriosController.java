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
public class FuncionáriosController implements Initializable {
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void handleButtonInserirFunc(ActionEvent event) {
        //tabela.getSelectionModel().clearSelection();
        anchorPaneInicioFunc.setVisible(false);
        anchorPaneNovoFunc.setVisible(true);
        txtIdFunc.setText("");
    }
    
    @FXML
    void handleButtonCancelar(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Funcionário?");
        if (resp == diálogo.Resposta.YES) {
            limparCampos();
            anchorPaneNovoFunc.setVisible(false);
            anchorPaneInicioFunc.setVisible(true);
        }
    }
    
    @FXML
    void limparCampos() {
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
    
    
}
