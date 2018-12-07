package controller;

import banco.DAO.ControleDAO;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.dao.ClienteFDAO;
import model.dao.ClienteJDAO;
import model.dao.ProdutoDAO;
import model.domain.clienteMF;
import model.domain.clienteMJ;
import model.domain.funcionarioM;
import model.domain.itensVendaFM;
import model.domain.produtoM;
import model.domain.vendasFM;
import util.Combo;
import util.MaskFieldUtil;
import util.diálogo;
import util.mensagens;

public class VendasController implements Initializable {

    private clienteMF clienteF;
    private clienteMJ clienteJ;
    private funcionarioM vendedor;
    private produtoM produto;
    private vendasFM vendasF;
    
    private ClienteFDAO clienteFDAO;
    private ClienteJDAO clienteJDAO;
    private ProdutoDAO produtoDAO;
    private List<clienteMF> listaCliMF;
    private List<produtoM> listaProdM;
    private List<funcionarioM> listaFuncM;
    private ObservableList<itensVendaFM> observableListItensVendaVF;



    @FXML
    private AnchorPane anchorPaneInicioVF;

    @FXML
    private AnchorPane anchorPaneTbInfoVF;

    @FXML
    private TableView<vendasFM> tbVF;

    @FXML
    private TableColumn<vendasFM, String> TableColumnIdVF;

    @FXML
    private TableColumn<vendasFM, String> TableColumnNomeClienteVF;

    @FXML
    private TableColumn<vendasFM, String> TableColumnVendedorVF;

    @FXML
    private TableColumn<vendasFM, String> TableColumnDataHorarioVF;

    @FXML
    private TableColumn<vendasFM, String> TableColumnValorTotalVF;

    @FXML
    private TableView<itensVendaFM> tbItensVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnProdItensVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnQntditensVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnPUItensVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnValorTVF;

    @FXML
    private Label lbTitulo;

    @FXML
    private ToggleButton btInfoVF;

    @FXML
    private ToggleGroup menu22;

    @FXML
    private TextField txtPesquisarVF;

    @FXML
    private ToggleGroup menu2;

    @FXML
    private ToggleButton btNovoVF;

    @FXML
    private ToggleGroup menu;

    @FXML
    private AnchorPane anchorPaneNovoVF;

    @FXML
    private Label lbTitulo1;

    @FXML
    private Button btFinalizarVF;

    @FXML
    private Button btCancelarVF;

    @FXML
    private Button btCancelarItensVF;

    @FXML
    private TextField txtClienteVF;

    @FXML
    private TextField txtIdClienteVF;

    @FXML
    private ComboBox<funcionarioM> comboBoxFuncVF;

    @FXML
    private Button btProcurarClienteVF;

    @FXML
    private TextField txtProdutoVF;

    @FXML
    private TextField txtIdProdutoVF;

    @FXML
    private Button btProcurarProdVF;

    @FXML
    private TextField txtQntdVF;

    @FXML
    private TextField txtPUVF;

    @FXML
    private Label txtLabelTotalVF;

    @FXML
    private Label txtLabelTotalVFTOTAL;

    @FXML
    private Button btRemoverProdVF;

    @FXML
    private Button btAddProdVF;

    @FXML
    private TableView<itensVendaFM> tbItensVendaVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnProdutoVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnQntdVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnPUVF;

    @FXML
    private TableColumn<itensVendaFM, String> TableColumnPTVF;

    @FXML
    private AnchorPane anchorPaneSelecionarCVF;

    @FXML
    private TableView<clienteMF> tbClienteVF;

    @FXML
    private TableColumn<clienteMF, String> TableColumnIdFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnNomeFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnCpfFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnCelularFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnEmailFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnEnderecoFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnBairroFisico;

    @FXML
    private TableColumn<clienteMF, String> TableColumnEstadoFisico;

    @FXML
    private Label lbTitulo12;

    @FXML
    private TextField txtPesquisarCVF;

    @FXML
    private Button btSelecionarCVF;

    @FXML
    private Button btCancelarSelecionarCVF;

    @FXML
    private AnchorPane anchorPaneSelecionarPVF;

    @FXML
    private Label lbTitulo121;

    @FXML
    private TextField txtPesquisarPCV;

    @FXML
    private Button btSelecionarPCV;

    @FXML
    private TableView<produtoM> tbProdCV;

    @FXML
    private TableColumn<produtoM, String> TableColumnIdProd;

    @FXML
    private TableColumn<produtoM, String> TableColumnNomeProd;

    @FXML
    private TableColumn<produtoM, String> TableColumnCategoriaProd;

    @FXML
    private TableColumn<produtoM, String> TableColumnFornecedorProd;

    @FXML
    private TableColumn<produtoM, String> TableColumnPCdeCompraProd;

    @FXML
    private TableColumn<produtoM, String> TableColumnPCdeVendaProd;

    @FXML
    private TableColumn<produtoM, String> TableColumnQuantidadeProd;

    @FXML
    private Button btCancelarSelecionarPVF;

    @FXML
    private AnchorPane anchorPaneInicioVJ;

    @FXML
    private TableView<?> tbVJ;

    @FXML
    private TableColumn<?, ?> TableColumnIdVJ;

    @FXML
    private TableColumn<?, ?> TableColumnNomeClienteVJ;

    @FXML
    private TableColumn<?, ?> TableColumnVendedorVJ;

    @FXML
    private TableColumn<?, ?> TableColumnDataHorarioVJ;

    @FXML
    private TableColumn<?, ?> TableColumnValorTotalVJ;

    @FXML
    private Label lbTitulo2;

    @FXML
    private ToggleButton btInfoVJ;

    @FXML
    private ToggleGroup menu221;

    @FXML
    private TextField txtPesquisarVJ;

    @FXML
    private ToggleGroup menu21;

    @FXML
    private ToggleButton btNovoVJ;

    @FXML
    private ToggleGroup menu1;

    @FXML
    private AnchorPane anchorPaneNovoVJ;

    @FXML
    private Label lbTitulo11;

    @FXML
    private Button btFinalizarVJ;

    @FXML
    private Button btCancelarVJ;

    @FXML
    private TextField txtClienteVJ;

    @FXML
    private TextField txtIdClienteVJ;

    @FXML
    private ComboBox<?> comboBoxFuncVJ;

    @FXML
    private Button btProcurarClienteVJ;

    @FXML
    private TextField txtProdutoVJ;

    @FXML
    private TextField txtIdProdutoVJ;

    @FXML
    private Button btProcurarProdVJ;

    @FXML
    private TextField txtQntdVJ;

    @FXML
    private TextField txtPUVJ;

    @FXML
    private Label txtLabelTotalVJ;

    @FXML
    private Button btRemoverProdVJ;

    @FXML
    private Button btAddProdVJ;

    @FXML
    private TableView<?> tbProdutosVJ;

    @FXML
    private TableColumn<?, ?> TableColumnProdutoVJ;

    @FXML
    private TableColumn<?, ?> TableColumnQntdVJ;

    @FXML
    private TableColumn<?, ?> TableColumnPUVJ;

    @FXML
    private TableColumn<?, ?> TableColumnPTVJ;

    @FXML
    void eventKeyPressedEnterCVF(KeyEvent event) {

    }

    @FXML
    void eventKeyPressedEnterPCF(KeyEvent event) {

    }

    @FXML
    void eventKeyPressedEnterVJ(KeyEvent event) {

    }

    @FXML
    void handleButtonAddProdVJ(ActionEvent event) {

    }

    @FXML
    void handleButtonCancelarVJ(ActionEvent event) {

    }

    @FXML
    void handleButtonFinalizarVJ(ActionEvent event) {

    }

    @FXML
    void handleButtonInfoVJ(ActionEvent event) {

    }

    @FXML
    void handleButtonInserirVJ(ActionEvent event) {

    }

    @FXML
    void handleButtonProcurarCVJ(ActionEvent event) {

    }

    @FXML
    void handleButtonProcurarProdVJ(ActionEvent event) {

    }

    @FXML
    void handleButtonRemoverProdVJ(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //----------Início Inicializador Padrão
        preenchercomboBoxVendedores();
        
        
        this.clienteF = new clienteMF();
        this.clienteJ = new clienteMJ();
        this.vendedor = new funcionarioM();
        this.produto = new produtoM();
        this.vendasF = new vendasFM();
        
        
        this.clienteFDAO = new ClienteFDAO();
        this.produtoDAO = new ProdutoDAO();
        this.clienteJDAO = new ClienteJDAO();
        this.listaCliMF = new ArrayList<>();
        
        
        this.listaProdM = new ArrayList<>();
        this.listaFuncM = new ArrayList<>();
        setCellTableClienteF();
        atualizarListaClienteFisico();
        setCellTableProd();
        atualizarListaProd();
        setCellTableItensVendaVF();
        
        
        
        
        //----------Fim Inicializador Padrão
        //----------Início Inicializador VendasF
        setCellTableTBVF();
        atualizarListaVendas();
        selecionarItemTabelaVVF();
        setCellTableInfoVF();
        //----------Fim Inicializador VendasF
        //----------Início Inicializador VendasJ
        //----------Fim Inicializador VendasJ
        //----------Início Máscaras
        //--Início VF
        MaskFieldUtil.numericField(txtQntdVF);
        //--Fim VF
        //----------Fim Máscaras
    }

    //---------Início Padrão
    public void preenchercomboBoxVendedores() {
        Combo.popular(comboBoxFuncVF, ControleDAO.getControleBanco().getFuncionárioDAO().comboVendedores());
    }

    @FXML
    void eventKeyPressedQntdVF(KeyEvent event) {
        if (txtQntdVF.getText().length() == 0) {
        } else {
            txtLabelTotalVF.setText(String.valueOf(Float.valueOf(txtQntdVF.getText()) * Float.valueOf(txtPUVF.getText())));
        }

    }

    @FXML
    void eventMousePressedActionQntdVF(MouseEvent event) {
        tbItensVendaVF.getSelectionModel().clearSelection();
    }

    float totalVenda() {
        float total = 0;
        for (itensVendaFM iv : vendasF.getItens_VendaFM()) {
            total += iv.getPreco_total();
        }
        return total;
    }

    public void setCellTableItensVendaVF() {
        TableColumnProdutoVF.setCellValueFactory(new PropertyValueFactory<>("prodM"));
        TableColumnQntdVF.setCellValueFactory(new PropertyValueFactory<>("qntd"));
        TableColumnPUVF.setCellValueFactory(new PropertyValueFactory<>("preco"));
        TableColumnPTVF.setCellValueFactory(new PropertyValueFactory<>("preco_total"));

    }

    //---------Fim Padrão
    //----------Início Cliente - Venda Física
    @FXML
    void handleButtonProcurarCVF(ActionEvent event) {
        anchorPaneNovoVF.setVisible(false);
        anchorPaneSelecionarCVF.setVisible(true);
        txtPesquisarCVF.requestFocus();
    }

    @FXML
    void handleButtonCancelarSelecionarCVF(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Voltar", "Realmente deseja voltar para a tela anterior?");
        if (resp == diálogo.Resposta.YES) {
            //limparCamposProd();
            anchorPaneSelecionarCVF.setVisible(false);
            anchorPaneNovoVF.setVisible(true);
        }
    }

    @FXML
    void handleButtonSelecionarCVF(ActionEvent event) {
        if (tbClienteVF.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente para continuar.");
            //desativasbtsExcluirAlterarCargo();
        } else {
            clienteMF cliMF = tbClienteVF.getItems().get(tbClienteVF.getSelectionModel().getSelectedIndex());
            txtIdClienteVF.setText(String.valueOf(cliMF.getId()));
            txtClienteVF.setText(cliMF.getNome());
            anchorPaneSelecionarCVF.setVisible(false);
            anchorPaneNovoVF.setVisible(true);
            //ativarbtsExcluirCancelarAlterarCargo();
        }
    }

    public void setCellTableClienteF() {
        TableColumnIdFisico.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeFisico.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnCpfFisico.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        TableColumnCelularFisico.setCellValueFactory(new PropertyValueFactory<>("celular"));
        TableColumnEmailFisico.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumnEnderecoFisico.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumnBairroFisico.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        TableColumnEstadoFisico.setCellValueFactory(new PropertyValueFactory<>("estado"));

    }

    @FXML
    private void atualizarListaClienteFisico() {
        try {

            tbClienteVF.setItems(ControleDAO.getControleBanco().getClienteFDAO().listar_clienteF(txtPesquisarCVF.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    //----------Fim Cliente - Venda Física
    //----------Início Produto - Venda Física
    @FXML
    void handleButtonProcurarProdVF(ActionEvent event) {
        anchorPaneNovoVF.setVisible(false);
        anchorPaneSelecionarPVF.setVisible(true);
        txtPesquisarPCV.requestFocus();
    }

    @FXML
    void handleButtonCancelarSelecionarPVF(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Voltar", "Realmente deseja voltar para a tela anterior?");
        if (resp == diálogo.Resposta.YES) {
            //limparCamposProd();
            anchorPaneSelecionarPVF.setVisible(false);
            anchorPaneNovoVF.setVisible(true);
        }
    }

    @FXML
    void handleButtonAddProdVF(ActionEvent event) {
        if (txtQntdVF.getText().length() == 0 || txtQntdVF.getText().equals("0")) {
            mensagens.erro("Digite uma quantidade. \nObs : ela não pode ser 0.");
        } else {
            //int idvenda = 0;
            //itensVenda_FM.setVendaFM(idvenda);
            itensVendaFM itensVenda_FM = new itensVendaFM();
            produtoM prod = tbProdCV.getItems().get(tbProdCV.getSelectionModel().getSelectedIndex());
            itensVenda_FM.setProdM(prod);
            itensVenda_FM.setPreco(Float.valueOf(txtPUVF.getText()));
            itensVenda_FM.setQntd(Integer.valueOf(txtQntdVF.getText()));
            itensVenda_FM.setPreco_total(Float.valueOf(txtLabelTotalVF.getText()));
            vendasF.getItens_VendaFM().add(itensVenda_FM);
            txtLabelTotalVFTOTAL.setText(String.valueOf(totalVenda()));
            observableListItensVendaVF = FXCollections.observableArrayList(vendasF.getItens_VendaFM());
            tbItensVendaVF.setItems(observableListItensVendaVF);
            btRemoverProdVF.setDisable(false);
            tbItensVendaVF.getSelectionModel().clearSelection();

        }

    }

    @FXML
    void handleButtonRemoverProdVF(ActionEvent event) {
        if (tbItensVendaVF.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um item da lista para remover.");
        } else {
            vendasF.setItens_VendaFM(observableListItensVendaVF);
            vendasF.getItens_VendaFM().remove(tbItensVendaVF.getSelectionModel().getSelectedIndex());
            txtLabelTotalVFTOTAL.setText(String.valueOf(totalVenda()));
        }

    }

    @FXML
    void handleButtonSelecionarPVF(ActionEvent event) {
        if (tbProdCV.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Produto para continuar.");
        } else {
            produtoM prod = tbProdCV.getItems().get(tbProdCV.getSelectionModel().getSelectedIndex());
            txtIdProdutoVF.setText(String.valueOf(prod.getId()));
            txtProdutoVF.setText(prod.getNome());
            txtPUVF.setText(String.valueOf(prod.getPc_Venda()));
            txtQntdVF.setDisable(false);
            anchorPaneSelecionarPVF.setVisible(false);
            anchorPaneNovoVF.setVisible(true);
            btAddProdVF.setDisable(false);
            txtQntdVF.requestFocus();
        }
    }

    public void setCellTableProd() {
        TableColumnIdProd.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnCategoriaProd.setCellValueFactory(new PropertyValueFactory<>("categoria_id"));
        TableColumnFornecedorProd.setCellValueFactory(new PropertyValueFactory<>("fornecedor_id"));
        TableColumnPCdeCompraProd.setCellValueFactory(new PropertyValueFactory<>("pc_Compra"));
        TableColumnPCdeVendaProd.setCellValueFactory(new PropertyValueFactory<>("pc_Venda"));
        TableColumnQuantidadeProd.setCellValueFactory(new PropertyValueFactory<>("qntd"));
    }

    private void atualizarListaProd() {
        try {

            tbProdCV.setItems(ControleDAO.getControleBanco().getProdutoDAO().listar_prod(txtPesquisarPCV.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }
    //----------Fim Produto - Venda Física

    //---------Início VendasF
    public void setCellTableTBVF() {
        TableColumnIdVF.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeClienteVF.setCellValueFactory(new PropertyValueFactory<>("cliente_id"));
        TableColumnVendedorVF.setCellValueFactory(new PropertyValueFactory<>("funcionario_id"));
        TableColumnValorTotalVF.setCellValueFactory(new PropertyValueFactory<>("valor_total"));
        TableColumnDataHorarioVF.setCellValueFactory(new PropertyValueFactory<>("data_horario"));

    }

    private boolean validarDadosVF() {
        String msgErro = "";

        if (comboBoxFuncVF.getItems().isEmpty()) {
            msgErro += "Vendedor inválido\n";
        }
        if (txtClienteVF.getText().length() == 0 || txtIdClienteVF.getText().length() == 0) {
            msgErro += "Cliente inválido\n";
        }
        if (txtProdutoVF.getText().length() == 0 || txtIdProdutoVF.getText().length() == 0) {
            msgErro += "Produto inválido\n";
        }
        if (txtPUVF.getText().length() == 0) {
            msgErro += "Preço Unitário inválido\n";
        }
        if (txtQntdVF.getText().length() == 0) {
            msgErro += "Quantidade inválida\n";
        }
        if (vendasF.getItens_VendaFM().isEmpty()) {
            msgErro += "Itens de venda inválidos\n";
        }
        //alertas de erros nos campos
        if (msgErro.length() == 0) {
            //se a variável msgErro tiver o tamanho 0, retorna true, não mostrando mensagem de erro
            return true;
        } else {
            mensagens.alerta(msgErro);
            return false;
        }
    }

    private void limparCamposVF() {
        preenchercomboBoxVendedores();
        vendedor = null;
        clienteF = null;
        tbClienteVF.getSelectionModel().clearSelection();
        txtClienteVF.setText("");
        txtIdClienteVF.setText("");
        tbProdCV.getSelectionModel().clearSelection();
        txtProdutoVF.setText("");
        txtIdProdutoVF.setText("");
        txtPUVF.setText("");
        txtQntdVF.setText("");
        txtQntdVF.setDisable(true);
        txtLabelTotalVF.setText("0");
        btAddProdVF.setDisable(true);
        btRemoverProdVF.setDisable(true);
        observableListItensVendaVF = FXCollections.observableArrayList(vendasF.getItens_VendaFM());
        vendasF.getItens_VendaFM().removeAll(observableListItensVendaVF);
        tbItensVendaVF.setItems(observableListItensVendaVF);
        observableListItensVendaVF.clear();
        txtLabelTotalVFTOTAL.setText("0");

    }

    @FXML
    void handleButtonFinalizarVF(ActionEvent event) {
        if (validarDadosVF()) {
            try {
                vendedor = comboBoxFuncVF.getSelectionModel().getSelectedItem();
                clienteF = tbClienteVF.getSelectionModel().getSelectedItem();
                vendasF.setFuncionario_id(vendedor);
                vendasF.setCliente_id(clienteF);
                vendasF.setValor_total(Float.valueOf(txtLabelTotalVFTOTAL.getText()));
                ControleDAO.getControleBanco().getVendasFDAO().finalizarVF(vendasF);
                limparCamposVF();
                atualizarListaVendas();
                anchorPaneNovoVF.setVisible(false);
                anchorPaneInicioVF.setVisible(true);
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
        }
    }

    @FXML
    void handleButtonInserirVF(ActionEvent event) {
        anchorPaneInicioVF.setVisible(false);
        anchorPaneNovoVF.setVisible(true);
        txtIdClienteVF.setText("");
        txtIdProdutoVF.setText("");
    }

    @FXML
    void handleButtonCancelarVF(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Cancelar cadastro de venda", "Realmente deseja cancelar o cadastro da venda?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposVF();
            anchorPaneNovoVF.setVisible(false);
            anchorPaneInicioVF.setVisible(true);
        }
    }

    private void atualizarListaVendas() {
        try {
            tbVF.setItems(ControleDAO.getControleBanco().getVendasFDAO().listar_vendas(txtPesquisarVF.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }
    
    @FXML
    void eventKeyPressedEnterVF(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaVendas();
        }
    }

    @FXML
    void handleButtonInfoVF(ActionEvent event) {
        anchorPaneTbInfoVF.setVisible(true);
        anchorPaneInicioVF.setVisible(false);
    }

    @FXML
    void handleButtonCancelarItensVF(ActionEvent event) {
        anchorPaneTbInfoVF.setVisible(false);
        anchorPaneInicioVF.setVisible(true);
    }

    public void selecionarItemTabelaVVF() {
        tbVF.setOnMouseClicked(e -> {
            if (tbVF.getSelectionModel().isEmpty()) {

            } else {
                btInfoVF.setDisable(false);
                vendasF = tbVF.getSelectionModel().getSelectedItem();
                int cod = vendasF.getId();
                try {
                    tbItensVF.setItems(ControleDAO.getControleBanco().getVendasFDAO().listar_itensVenda(cod));
                } catch (Exception ex) {

                }
            }
        });

    }

    public void setCellTableInfoVF() {
        TableColumnProdItensVF.setCellValueFactory(new PropertyValueFactory<>("prodM"));
        TableColumnQntditensVF.setCellValueFactory(new PropertyValueFactory<>("qntd"));
        TableColumnPUItensVF.setCellValueFactory(new PropertyValueFactory<>("preco"));
        TableColumnValorTVF.setCellValueFactory(new PropertyValueFactory<>("preco_total"));

    }

    //---------Fim VendasF
    //---------InícioVendasJ
    //---------Fim VendasJ
}
