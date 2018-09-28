package banco.DAO;

import banco.controller.conexãoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author felli
 */
public class DAO {
    protected Connection conector = conexãoBanco.instancia().getConnection();
    protected ResultSet rs;
    protected PreparedStatement stm;

    public DAO() {
    }
    
}
