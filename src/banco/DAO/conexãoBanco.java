package banco.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.mensagens;

public class conexãoBanco {

    private final String SERVIDOR = "localhost";
    private final String PORTA = "3306";
    private final String BDD = "auto_pecas";
    private final String USUARIO = "root";
    private final String SENHA = "4913";
    private final String URL = "jdbc:mysql://" + SERVIDOR + ":" + PORTA + "/" + BDD + "?useTimezone=true&serverTimezone=UTC";
    public ResultSet rs;

    private Connection conexao;

    public conexãoBanco() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            conexao.setAutoCommit(false);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            mensagens.erro("Não foi possível coletar dados de acesso ao banco.\n" + ex, "Impossível acessar banco");
            System.exit(0);
        } catch (SQLException ex) {
            mensagens.erro("Não foi possível coletar dados de acesso ao banco.\n" + ex, "Impossível acessar banco");
            System.exit(0);
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public void confirmar() throws SQLException {
        try {
            conexao.commit();
        } catch (SQLException ex) {
            mensagens.erro("Erro na instrução SQL.\n"+ex, "Erro");
        } finally {
            conexao.close();
        }
    }
    
}
