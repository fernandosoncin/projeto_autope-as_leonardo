// Filtra a criação de objetos do pacote DAO, facilitando na hora de popular uma combobox, por exemplo
package banco.DAO;


import model.dao.ClienteFDAO;
import model.dao.ClienteJDAO;
import model.dao.FuncionárioDAO;
import model.dao.ProdutoDAO;

public class ControleDAO {
    private static ControleDAO controleBanco = new ControleDAO();
    private ClienteFDAO clienteFDAO = new ClienteFDAO();
    private ClienteJDAO clienteJDAO = new ClienteJDAO();
    private FuncionárioDAO funcionárioDAO = new FuncionárioDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public static ControleDAO getControleBanco() {
        return controleBanco;
    }

    public ClienteFDAO getClienteFDAO() {
        return clienteFDAO;
    }

    public ClienteJDAO getClienteJDAO() {
        return clienteJDAO;
    }
    
    public FuncionárioDAO getFuncionárioDAO() {
        return funcionárioDAO;
    }
    
    public ProdutoDAO getProdutoDAO() {
        return produtoDAO;
    }
    
}
