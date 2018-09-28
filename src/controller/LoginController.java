package controller;

import banco.controller.conexãoBanco;
import banco.controller.controleDAO;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.usuario;
/**
 *
 * @author felli
 */
public class LoginController implements Initializable {
    
    public static usuario usuarioLogado = null;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private JFXButton btEntrar;

    @FXML
    private JFXButton btSair;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    @FXML
    private void handlebtSair(ActionEvent event){
        login.palco.close();
    }
    
    @FXML
    private void handlebtLogar(ActionEvent event) throws Exception{
        //new conexãoBanco().conexãoBanco();
                new appMain().start(new Stage());
                login.palco.close();

    }

}
