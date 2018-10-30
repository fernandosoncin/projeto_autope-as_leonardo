package controller;

import java.io.IOException;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.links;

/**
 * FXML Controller class
 *
 * @author felli
 */
public class appMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private static appMainController instance;

    @FXML
    private AnchorPane anchormain;

    @FXML
    private ToggleButton btCliente;

    @FXML
    private ToggleGroup grupoMenus;

    @FXML
    private ToggleButton btFornecedores;

    @FXML
    private VBox boxFornecedores;

    @FXML
    private ToggleButton btNovoFornecedor;

    @FXML
    private ToggleGroup grupoFornecedores;

    @FXML
    private ToggleButton btListarFornecedores;

    @FXML
    private Label lbUser;

    @FXML
    private Label lbVersãoDoSistema;

    @FXML
    private Hyperlink hyperLink;

    @FXML
    private AnchorPane boxCounteúdo;

    @FXML
    private Label labelXDash;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instance = this;

    }

    @FXML
    public void handleMenuFormulárioClientes() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Clientes.fxml"));
        boxCounteúdo.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuFormulárioFuncionários() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Funcionários.fxml"));
        boxCounteúdo.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuFormulárioProdutos() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Produtos.fxml"));
        boxCounteúdo.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuInicio() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/inicio.fxml"));
        //a.getStylesheets().add(getClass().getResource("/css/custom.css").toExternalForm());
        boxCounteúdo.getChildren().setAll(a);
    }

    @FXML
    void menuLogOut(MouseEvent event) throws Exception {
        appMain.palco.close();
        new login().start(new Stage());

    }

    //obter instância do controller
    public static appMainController getInstance() {
        return instance;
    }

    @FXML
    void facebookCriador(ActionEvent event) {
        links.siteFacebookCriador("https://www.facebook.com/fellipeluannoliveira");
    }

}
