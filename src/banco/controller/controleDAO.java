package banco.controller;

import banco.DAO.loginDAO;

/**
 *
 * @author felli
 */
public class controleDAO {
    private static controleDAO banco = new controleDAO();
    //private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private loginDAO LoginDAO = new loginDAO();
    
    
    public static controleDAO getBanco() {
        return banco;
    }

    public loginDAO getLoginDAO() {
        return LoginDAO;
    }

    public void setLoginDAO(loginDAO LoginDAO) {
        this.LoginDAO = LoginDAO;
    }
    
}
