package banco.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
    protected Connection conector = conexãoBancoDAO.instancia().getConnection();
    protected ResultSet rs;
    protected PreparedStatement stm;
}
