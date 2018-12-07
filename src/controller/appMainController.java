package controller;

import banco.DAO.ControleDAO;
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
import model.domain.funcionarioM;
import util.links;

public class appMainController {

    private static appMainController instance;

    @FXML
    private AnchorPane anchormain;

    @FXML
    private ToggleButton btCliente;

    @FXML
    private ToggleGroup grupoMenus;

    @FXML
    private ToggleButton btFuncionarios;

    @FXML
    private ToggleButton btProdutos;

    @FXML
    private ToggleButton btVendas;

    @FXML
    private ToggleGroup grupoMenus1;

    @FXML
    private Label lbUser;

    @FXML
    private Label lbLogOut;

    @FXML
    private Label lbVersãoDoSistema;

    @FXML
    private Hyperlink hyperLink;

    @FXML
    private AnchorPane boxCounteúdo;



    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
        //funcionarioM func_logado = new funcionarioM();
        //func_logado = LoginController.usuarioLogado;

    }
   

    @FXML
    public void handleMenuInício() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/inicio.fxml"));
        boxCounteúdo.getChildren().setAll(a);
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
    public void handleMenuFormulárioVendas() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/Vendas.fxml"));
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
        links.siteFacebookCriador("https://www.facebook.com/fernando.soncin.5");
        links.siteFacebookCriador("https://www.facebook.com/bruno.pinheiro.v");
    }

}
