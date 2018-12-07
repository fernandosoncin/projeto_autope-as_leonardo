// Filtra a criação de objetos do pacote DAO, facilitando na hora de popular uma combobox, por exemplo
package banco.DAO;

import model.dao.CargoDAO;
import model.dao.CategoriaDAO;
import model.dao.ClienteFDAO;
import model.dao.ClienteJDAO;
import model.dao.FornecedorDAO;
import model.dao.FuncionárioDAO;
import model.dao.LoginDAO;
import model.dao.ProdutoDAO;
import model.dao.VendasFDAO;

public class ControleDAO {

    private static ControleDAO controleBanco = new ControleDAO();
    private ClienteFDAO clienteFDAO = new ClienteFDAO();
    private ClienteJDAO clienteJDAO = new ClienteJDAO();
    private FuncionárioDAO funcionárioDAO = new FuncionárioDAO();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private CargoDAO cargoDAO = new CargoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private VendasFDAO vendasFDAO = new VendasFDAO();
    private LoginDAO loginDAO = new LoginDAO();

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

    public CargoDAO getCargoDAO() {
        return cargoDAO;
    }

    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public FornecedorDAO getFornecedorDAO() {
        return fornecedorDAO;
    }

    public void setFornecedorDAO(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }

    public VendasFDAO getVendasFDAO() {
        return vendasFDAO;
    }

    public void setVendasFDAO(VendasFDAO vendasFDAO) {
        this.vendasFDAO = vendasFDAO;
    }

    public LoginDAO getLoginDAO() {
        return loginDAO;
    }

    public void setLoginDAO(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

}
