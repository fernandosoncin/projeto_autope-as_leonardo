package controller;

import banco.DAO.ControleDAO;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.domain.funcionarioM;
import util.MaskFieldUtil;
import util.mensagens;

/**
 *
 * @author felli
 */
public class LoginController implements Initializable {

    public static funcionarioM usuarioLogado = null;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtLOGIN;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private JFXButton btEntrar;

    @FXML
    private JFXButton btSair;


    public void initialize(URL url, ResourceBundle rb) {
        //--Início Máscaras Login
        MaskFieldUtil.cpfField(txtLOGIN);
        MaskFieldUtil.SenhaFuncField(txtSenha);
        //--Fim Máscaras Login

    }

    @FXML
    private void handlebtSair(ActionEvent event) {
        login.palco.close();
    }

    @FXML
    private void handlebtLogar() throws Exception {
        String cpf = txtLOGIN.getText();
        String senha = txtSenha.getText();
        if (ControleDAO.getControleBanco().getLoginDAO().autenticarUser(cpf)) {
            if (ControleDAO.getControleBanco().getLoginDAO().autenticarSenha(cpf, senha)) {
                usuarioLogado = ControleDAO.getControleBanco().getLoginDAO().usuarioLogado(cpf);
                new appMain(cpf).start(new Stage());
                login.palco.close();
            }
        }
    }
}
