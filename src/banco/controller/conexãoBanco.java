package banco.controller;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import util.mensagens;

/**
 *
 * @author felli
 */
public class conexãoBanco {

    private static conexãoBanco instancia = new conexãoBanco();
    private Connection connection;

    public void conexãoBanco() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:3306/projetoTCC", "postgres", "");
            mensagens.alerta("Conexão com o banco de dados realizada.");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            mensagens.alerta("Não foi possível conectar-se ao banco de dados ! " +ex);
            System.exit(0);
        }
    }

    public static conexãoBanco instancia() {
        if (instancia == null) {
            instancia = new conexãoBanco();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}
