package banco.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.mensagens;

public class conexãoBancoDAO {

    private static conexãoBancoDAO instancia = new conexãoBancoDAO();
    private Connection connection;

    private conexãoBancoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/auto_pecas?useTimezone=true&serverTimezone=UTC", "root", "32799097");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            mensagens.alerta("Erro ao conectar-se com a base de dados! \n" + ex);
        }
    }

    public static conexãoBancoDAO instancia() {
        if (instancia == null) {
            instancia = new conexãoBancoDAO();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}
