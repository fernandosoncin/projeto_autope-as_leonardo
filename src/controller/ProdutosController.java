package controller;

import banco.DAO.ControleDAO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import model.dao.CategoriaDAO;
import model.dao.FornecedorDAO;
import model.dao.ProdutoDAO;
import model.domain.categoriaM;
import model.domain.fornecedorM;
import model.domain.produtoM;
import util.Combo;
import util.MaskFieldUtil;
import util.diálogo;
import util.mensagens;

public class ProdutosController implements Initializable {

    private produtoM produto;
    private ProdutoDAO produtoDAO;
    private categoriaM categoria;
    private CategoriaDAO categoriaDAO;
    private fornecedorM fornecedor;
    private FornecedorDAO fornecedorDAO;

    @FXML
    private AnchorPane anchorPaneProd;

    @FXML
    private AnchorPane anchorPaneInicioProd;

    @FXML
    private TableView<produtoM> tbProd;

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
    private Label lbTitulo;

    @FXML
    private TextField txtPesquisarProd;

    @FXML
    private ToggleButton btRelatorioProd;

    @FXML
    private ToggleGroup menu21;

    @FXML
    private ToggleButton btNovoProd;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarProd;

    @FXML
    private ToggleButton btExcluirProd;

    @FXML
    private AnchorPane anchorPaneNovoProd;

    @FXML
    private Label lbTitulo1;

    @FXML
    private Button btSalvarProd;

    @FXML
    private Button btCancelarProd;

    @FXML
    private TextField txtIdProd;

    @FXML
    private TextField txtNomeProd;

    @FXML
    private TextField txtPCProd;

    @FXML
    private TextField txtQntdProd;

    @FXML
    private ComboBox<categoriaM> comboBoxCategoriaProd;

    @FXML
    private ToggleButton btNovoFornecedorProd;

    @FXML
    private ToggleGroup menu1;

    @FXML
    private ToggleButton btNovoCategoriaProd;

    @FXML
    private ToggleGroup menu11;

    @FXML
    private TextField txtPVProd;

    @FXML
    private ComboBox<fornecedorM> comboBoxFornecedorProd;

    @FXML
    private AnchorPane anchorPaneNovoCategoriaProd;

    @FXML
    private TableView<categoriaM> tbCategoriaProd;

    @FXML
    private TableColumn<categoriaM, String> colunaIdCategoriaProd;

    @FXML
    private TableColumn<categoriaM, String> colunaNomeCategoriaProd;

    @FXML
    private Button btSalvarCategoriaProd;

    @FXML
    private Button btCancelarCategoriaProd;

    @FXML
    private TextField txtPesquisarCategoriaProd;

    @FXML
    private ToggleButton btExcluirCategoriaProd;

    @FXML
    private ToggleGroup menu12;

    @FXML
    private ToggleButton btRelatorioCategoriaProd;

    @FXML
    private ToggleGroup menu211;

    @FXML
    private ToggleButton btAlterarCategoriaProd;

    @FXML
    private ToggleGroup menu2;

    @FXML
    private Label lbTitulo11;

    @FXML
    private TextField txtIdCategoriaProd;

    @FXML
    private TextField txtNomeCategoriaProd;

    @FXML
    private AnchorPane anchorPaneNovoFornecedorProd;

    @FXML
    private TableView<fornecedorM> tbFornecedorProd;

    @FXML
    private TableColumn<fornecedorM, String> colunaIdFornecedorProd;

    @FXML
    private TableColumn<fornecedorM, String> colunaNomeFornecedorProd;

    @FXML
    private TableColumn<fornecedorM, String> colunaEndFornecedorProd;

    @FXML
    private TableColumn<fornecedorM, String> colunaTelefoneFornecedorProd;

    @FXML
    private TableColumn<fornecedorM, String> colunaEstadoFornecedorProd;

    @FXML
    private Button btSalvarSalvarProd;

    @FXML
    private Button btCancelarFornecedorProd;

    @FXML
    private TextField txtPesquisarFornecedorProd;

    @FXML
    private ToggleButton btExcluirFornecedorProd;

    @FXML
    private ToggleGroup menu121;

    @FXML
    private ToggleButton btRelatorioFornecedorProd;

    @FXML
    private ToggleGroup menu2111;

    @FXML
    private ToggleButton btAlterarFornecedorProd;

    @FXML
    private ToggleGroup menu22;

    @FXML
    private Label lbTitulo111;

    @FXML
    private TextField txtIdFornecedorProd;

    @FXML
    private TextField txtNomeFornecedorProd;

    @FXML
    private TextField txtEndFornecedorProd;

    @FXML
    private TextField txtTelefoneFornecedorProd;

    @FXML
    private ComboBox<String> comboBoxEstadoFornecedorProd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //----------Início Inicializador Padrão
        preenchercomboBoxCategoria();
        preenchercomboBoxFornecedor();
        preenchercomboEstadoFornecedorProd();
        //----------Fim Inicializador Padrão
        //----------Início Inicializador Produtos
        this.produto = new produtoM();
        this.produtoDAO = new ProdutoDAO();
        setCellTableProd();
        atualizarListaProd();
        selecionarItemTabelaProd();
        //----------Fim Inicializador Produtos
        //----------Início Inicializador Categoria
        this.categoria = new categoriaM();
        this.categoriaDAO = new CategoriaDAO();
        setCellTableCategoriaProd();
        atualizarListaCategoria();
        selecionarItemTabelaCategoriaProd();
        //----------Fim Inicializador Categoria
        //----------Início Inicializador Fornecedor
        this.fornecedor = new fornecedorM();
        this.fornecedorDAO = new FornecedorDAO();
        setCellTableFornecedorProd();
        atualizarListaFornecedor();
        selecionarItemTabelaFornecedorProd();
        //----------Fim Inicializador Fornecedor
        //----------Início Máscaras
        //==Produtos
        MaskFieldUtil.NomePField(txtNomeProd);
        MaskFieldUtil.monetaryField(txtPCProd);
        MaskFieldUtil.monetaryField(txtPVProd);
        MaskFieldUtil.numericField(txtQntdProd);
        MaskFieldUtil.QntdPField(txtQntdProd);
        //==Fim Produtos
        //==Início Categoria
        MaskFieldUtil.NomeCField(txtNomeCategoriaProd);
        //==Fim Categoria
        //==Início Fornecedor
        MaskFieldUtil.NomeFornField(txtNomeFornecedorProd);
        MaskFieldUtil.EndFornField(txtEndFornecedorProd);
        MaskFieldUtil.foneCelularField(txtTelefoneFornecedorProd);
        //==Fim Fornecedor
        //----------Fim Máscaras
    }

    //----------Início Padrão
    public void preenchercomboBoxCategoria() {
        Combo.popular(comboBoxCategoriaProd, ControleDAO.getControleBanco().getCategoriaDAO().comboCategoria());
    }

    public void preenchercomboBoxFornecedor() {
        Combo.popular(comboBoxFornecedorProd, ControleDAO.getControleBanco().getFornecedorDAO().comboFornecedor());
    }

    private void preenchercomboEstadoFornecedorProd() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoFornecedorProd, tipo);
    }
//----------Fim Padrão

//----------Início Produto
    @FXML
    void handleButtonRelatorioProd(ActionEvent event) {
        String nomediretorio = null;
        String nomepasta = "Relatorios"; //Nome da pasta que vai armazenar relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarRelatorioProd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioProd() throws IOException, SQLException, DocumentException {
        String data, hora;
        data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        hora = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));

        try {
            List<produtoM> listaRProd = new ArrayList<>();
            listaRProd = produtoDAO.relatProd();
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Relatorios/RelatorioProd" + ".pdf"));
            doc.open();

            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("Relatório de Produtos", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(0);

            Paragraph nomeData = new Paragraph(data + "  " + hora, f5);
            nomeData.setAlignment(Element.ALIGN_CENTER);
            nomeData.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(new float[]{0.40f, 0.60f, 0.80f});
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell rNome = new PdfPCell(new Paragraph("Nome", f3));
            rNome.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rNome.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rNome.setBorder(0);

            PdfPCell rEnde = new PdfPCell(new Paragraph("Preço de compra", f3));
            rEnde.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rEnde.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rEnde.setBorder(0);

            PdfPCell rTelefone = new PdfPCell(new Paragraph("Preço de venda", f3));
            rTelefone.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rTelefone.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rTelefone.setBorder(0);

            tabela.addCell(rNome);
            tabela.addCell(rEnde);
            tabela.addCell(rTelefone);

            for (produtoM prod : listaRProd) {
                Paragraph p1 = new Paragraph(prod.getNome(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                col1.setBorder(0);

                Paragraph p2 = new Paragraph(String.valueOf(prod.getPc_Compra()), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                col2.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);

                Paragraph p3 = new Paragraph(String.valueOf(prod.getPc_Venda()), f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);
                col3.setBorder(0);
                tabela.addCell(col3);

            }

            doc.add(titulo2);
            doc.add(titulo1);
            doc.add(nomeData);
            doc.add(tabela);
            doc.close();
            mensagens.info("Relatório salvo com sucesso.", "Relatório de Produtos");
            String caminho = "C:/Relatorios/RelatorioProd.pdf";
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            mensagens.erro(e + "", "Erro");
        } catch (SQLException ex) {
            mensagens.erro(ex + "", "Erro");
        } catch (IOException exx) {
            mensagens.erro("Outro relatório de produtos se encontra aberto. Feche para gerar outro.", "Relatório de produtos");
        }
    }

    public void selecionarItemTabelaProd() {
        tbProd.setOnMouseClicked(e -> {
            tbProd.requestFocus();
            btExcluirProd.setDisable(false);
            btAlterarProd.setDisable(false);
        });

    }

    void desativarbtsEditareExcluirProd() {
        btAlterarProd.setDisable(true);
        btExcluirProd.setDisable(true);
    }

    @FXML
    void eventKeyPressedEnterProd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaProd();
        }

    }

    private void atualizarListaProd() {
        try {

            tbProd.setItems(ControleDAO.getControleBanco().getProdutoDAO().listar_prod(txtPesquisarProd.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
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

    private boolean validarDadosProd() {
        //validação de campos
        String msgErroSalvarProd = "";

        if (txtNomeProd.getText().length() == 0) {
            msgErroSalvarProd += "Nome do produto inválido\n";
        }
        if (txtPCProd.getText().length() == 0) {
            msgErroSalvarProd += "Preço de compra inválido\n";
        }
        if (txtPVProd.getText().length() == 0) {
            msgErroSalvarProd += "Preço de venda inválido\n";
        }
        if (comboBoxCategoriaProd.getItems().isEmpty()) {
            msgErroSalvarProd += "Categoria inválida\n";
        }
        if (comboBoxFornecedorProd.getItems().isEmpty()) {
            msgErroSalvarProd += "Fornecedor inválido\n";
        }
        if (txtQntdProd.getText().length() == 0) {
            msgErroSalvarProd += "Quantidade inválida\n";
        }
        //alertas de erros nos campos
        if (msgErroSalvarProd.length() == 0) {
            //se a variável msgErro tiver o tamanho 0, retorna true, não mostrando mensagem de erro
            return true;
        } else {
            mensagens.alerta(msgErroSalvarProd);
            return false;
        }
    }

    @FXML
    void handleButtonInserirProd(ActionEvent event) {
        anchorPaneInicioProd.setVisible(false);
        anchorPaneNovoProd.setVisible(true);
        preenchercomboBoxCategoria();
        preenchercomboBoxFornecedor();
        txtIdProd.setText("0");
    }

    @FXML
    void handleButtonSalvarProd(ActionEvent event) {
        if (validarDadosProd()) {
            try {
                produto.setId(Integer.parseInt((txtIdProd.getText())));
                produto.setNome(txtNomeProd.getText());
                produto.setCategoria_id(comboBoxCategoriaProd.getValue());
                produto.setFornecedor_id(comboBoxFornecedorProd.getValue());
                produto.setPc_Compra(Float.valueOf(txtPCProd.getText()));
                produto.setPc_Venda(Float.valueOf(txtPVProd.getText()));
                produto.setQntd(Integer.parseInt(txtQntdProd.getText()));
                ControleDAO.getControleBanco().getProdutoDAO().salvarProd(produto);
                anchorPaneNovoProd.setVisible(false);
                anchorPaneInicioProd.setVisible(true);
                limparCamposProd();
                atualizarListaProd();
                tbProd.refresh();
                tbProd.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
        }
    }

    @FXML
    void handleButtonAlterarProd(ActionEvent event) {
        if (tbProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Produto para alteração.");
            desativarbtsEditareExcluirProd();
        } else {
            anchorPaneInicioProd.setVisible(false);
            anchorPaneNovoProd.setVisible(true);
            produtoM produto = tbProd.getItems().get(tbProd.getSelectionModel().getSelectedIndex());
            txtIdProd.setText(String.valueOf(produto.getId()));
            txtNomeProd.setText(produto.getNome());
            comboBoxCategoriaProd.setValue(produto.getCategoria_id());
            comboBoxFornecedorProd.setValue(produto.getFornecedor_id());
            txtPCProd.setText(String.valueOf(produto.getPc_Compra()));
            txtPVProd.setText(String.valueOf(produto.getPc_Venda()));
            txtQntdProd.setText(String.valueOf(produto.getQntd()));
            desativarbtsEditareExcluirProd();
        }
    }

    @FXML
    void handleButtonExcluirProd(ActionEvent event) throws Exception {
        if (tbProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Produto para exclusão.");
            desativarbtsEditareExcluirProd();
        } else {
            produtoM produto = tbProd.getItems().get(tbProd.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getProdutoDAO().excluirProd(produto);
            desativarbtsEditareExcluirProd();
            atualizarListaProd();
            tbProd.refresh();
            tbProd.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void handleButtonCancelarProd(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do produto?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposProd();
            anchorPaneNovoProd.setVisible(false);
            anchorPaneInicioProd.setVisible(true);
        }
    }

    void limparCamposProd() {
        txtIdProd.setText("0");
        txtNomeProd.setText("");
        comboBoxCategoriaProd.getItems().clear();
        comboBoxFornecedorProd.getItems().clear();
        txtPCProd.setText("");
        txtPVProd.setText("");
        preenchercomboBoxCategoria();
        preenchercomboBoxFornecedor();
    }
//----------Fim Produto
//----------Início Categoria

    @FXML
    void handleButtonRelatorioCategoriaProd(ActionEvent event) {
        String nomediretorio = null;
        String nomepasta = "Relatorios"; //Nome da pasta que vai armazenar relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarRelatorioCategoria();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioCategoria() throws IOException {
        String data, hora;
        data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        hora = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));

        try {
            List<categoriaM> listaRCat = new ArrayList<>();
            listaRCat = categoriaDAO.relatCategoria();
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Relatorios/RelatorioCat" + ".pdf"));
            doc.open();

            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("Relatório de Categoria dos Produtos", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(0);

            Paragraph nomeData = new Paragraph(data + "  " + hora, f5);
            nomeData.setAlignment(Element.ALIGN_CENTER);
            nomeData.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(new float[]{0.40f, 0.60f, 0.80f});
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell rNome = new PdfPCell(new Paragraph("Categorias >", f3));
            rNome.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rNome.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rNome.setBorder(0);

            tabela.addCell(rNome);

            for (categoriaM cat : listaRCat) {
                Paragraph p1 = new Paragraph(cat.getNome_Categoria(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                tabela.addCell(col1);

            }

            doc.add(titulo2);
            doc.add(titulo1);
            doc.add(nomeData);
            doc.add(tabela);
            doc.close();
            mensagens.info("Relatório salvo com sucesso.", "Relatório de Categoria dos Produtos");
            String caminho = "C:/Relatorios/RelatorioCat.pdf";
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            mensagens.erro(e + "");
        } catch (SQLException ex) {
            mensagens.erro(ex + "");
        } catch (IOException exx) {
            mensagens.erro(exx + "\nOutro relatório de categoria dos produtos se encontra aberto. Feche para gerar outro.", "Relatório de Categorias dos Produtos");
        }
    }

    public void selecionarItemTabelaCategoriaProd() {
        tbCategoriaProd.setOnMouseClicked(e -> {
            tbCategoriaProd.requestFocus();
            btExcluirCategoriaProd.setDisable(false);
            btAlterarCategoriaProd.setDisable(false);
        });

    }

    void desativarbtsEditareExcluirCategoria() {
        btAlterarCategoriaProd.setDisable(true);
        btExcluirCategoriaProd.setDisable(true);
    }

    void ativarbtsEditareExcluirCategoria() {
        btAlterarCategoriaProd.setDisable(false);
        btExcluirCategoriaProd.setDisable(false);
    }

    @FXML
    void eventKeyPressedEnterCategoriaProd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaCategoria();
        }

    }

    private void atualizarListaCategoria() {
        try {

            tbCategoriaProd.setItems(ControleDAO.getControleBanco().getCategoriaDAO().listar_categoria(txtPesquisarCategoriaProd.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    public void setCellTableCategoriaProd() {
        colunaIdCategoriaProd.setCellValueFactory(new PropertyValueFactory<>("id_Categoria"));
        colunaNomeCategoriaProd.setCellValueFactory(new PropertyValueFactory<>("nome_Categoria"));

    }

    private boolean validarDadosCategoriaProd() {
        //validação de campos
        String msgErroSalvarCategoriaProd = "";

        if (txtNomeCategoriaProd.getText().length() == 0) {
            msgErroSalvarCategoriaProd += "Nome da categoria inválida\n";
        }
        //alertas de erros nos campos
        if (msgErroSalvarCategoriaProd.length() == 0) {
            //se a variável msgErro tiver o tamanho 0, retorna true, não mostrando mensagem de erro
            return true;
        } else {
            mensagens.alerta(msgErroSalvarCategoriaProd);
            return false;
        }
    }

    @FXML
    void handleButtonInserirCategoriaProd(ActionEvent event) {
        anchorPaneNovoProd.setVisible(false);
        anchorPaneNovoCategoriaProd.setVisible(true);
        txtIdCategoriaProd.setText("0");

    }

    @FXML
    void handleButtonSalvarCategoriaProd(ActionEvent event) {
        if (validarDadosCategoriaProd()) {
            try {
                categoria.setId_Categoria(Integer.parseInt((txtIdCategoriaProd.getText())));
                categoria.setNome_Categoria(txtNomeCategoriaProd.getText());
                ControleDAO.getControleBanco().getCategoriaDAO().salvarCategoria(categoria);
                limparCamposCategoria();
                atualizarListaCategoria();
                preenchercomboBoxCategoria();
                tbCategoriaProd.refresh();
                tbCategoriaProd.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
        }
    }

    @FXML
    void handlebuttonAlterarCategoriaProd(ActionEvent event) {
        if (tbCategoriaProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione uma Categoria para alteração.");
            desativarbtsEditareExcluirCategoria();
        } else {
            categoriaM cat = tbCategoriaProd.getItems().get(tbCategoriaProd.getSelectionModel().getSelectedIndex());
            txtIdCategoriaProd.setText(String.valueOf(cat.getId_Categoria()));
            txtNomeCategoriaProd.setText(cat.getNome_Categoria());
            ativarbtsEditareExcluirCategoria();
        }
    }

    @FXML
    void handleButtonExcluirCategoriaProd(ActionEvent event) throws Exception {
        if (tbCategoriaProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione uma Categoria para exclusão.");
            desativarbtsEditareExcluirCategoria();
        } else {
            categoriaM cat = tbCategoriaProd.getItems().get(tbCategoriaProd.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getCategoriaDAO().removerCategoria(cat);
            desativarbtsEditareExcluirCategoria();
            atualizarListaCategoria();
            preenchercomboBoxCategoria();
            tbCategoriaProd.refresh();
            tbCategoriaProd.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void handleButtonCancelarCategoriaProd(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro da categoria?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposCategoria();
            tbCategoriaProd.getSelectionModel().clearSelection();
            desativarbtsEditareExcluirCategoria();
            anchorPaneNovoCategoriaProd.setVisible(false);
            anchorPaneNovoProd.setVisible(true);
        }
    }

    void limparCamposCategoria() {
        txtIdCategoriaProd.setText("0");
        txtNomeCategoriaProd.setText("");
        txtPesquisarCategoriaProd.setText("");
        txtPCProd.setText("");
        txtPVProd.setText("");
    }
//----------Fim Categoria
//----------Início Fornecedor

    @FXML
    void handleButtonRelatorioFornecedorProd(ActionEvent event) {
        String nomediretorio = null;
        String nomepasta = "Relatorios"; //Nome da pasta que vai armazenar relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarRelatorioFornecedorProd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioFornecedorProd() throws IOException, SQLException, DocumentException {
        String data, hora;
        data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        hora = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));

        try {
            List<fornecedorM> listaRFor = new ArrayList<>();
            listaRFor = fornecedorDAO.relatFornecedor();
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Relatorios/RelatorioFor" + ".pdf"));
            doc.open();

            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("Relatório de Fornecedores", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(0);

            Paragraph nomeData = new Paragraph(data + "  " + hora, f5);
            nomeData.setAlignment(Element.ALIGN_CENTER);
            nomeData.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(new float[]{0.40f, 0.60f, 0.80f});
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell rNome = new PdfPCell(new Paragraph("Nome", f3));
            rNome.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rNome.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rNome.setBorder(0);

            PdfPCell rEnde = new PdfPCell(new Paragraph("Endereço", f3));
            rEnde.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rEnde.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rEnde.setBorder(0);

            PdfPCell rTelefone = new PdfPCell(new Paragraph("Telefone", f3));
            rTelefone.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rTelefone.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rTelefone.setBorder(0);

            tabela.addCell(rNome);
            tabela.addCell(rEnde);
            tabela.addCell(rTelefone);

            for (fornecedorM forn : listaRFor) {
                Paragraph p1 = new Paragraph(forn.getNome(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                col1.setBorder(0);

                Paragraph p2 = new Paragraph(forn.getEnd(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                col2.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);

                Paragraph p3 = new Paragraph(forn.getTelefone(), f5);
                p3.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col3 = new PdfPCell(p3);
                col3.setBorder(0);
                tabela.addCell(col3);

            }

            doc.add(titulo2);
            doc.add(titulo1);
            doc.add(nomeData);
            doc.add(tabela);
            doc.close();
            mensagens.info("Relatório salvo com sucesso.", "Relatório de Fornecedores dos Produtos");
            String caminho = "C:/Relatorios/RelatorioFor.pdf";
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            mensagens.erro(e + "", "Erro");
        } catch (SQLException ex) {
            mensagens.erro(ex + "", "Erro");
        } catch (IOException exx) {
            mensagens.erro("Outro relatório de produtos se encontra aberto. Feche para gerar outro.", "Relatório de Fornecedores dos Produtos");
        }
    }

    public void selecionarItemTabelaFornecedorProd() {
        tbFornecedorProd.setOnMouseClicked(e -> {
            tbFornecedorProd.requestFocus();
            btExcluirFornecedorProd.setDisable(false);
            btAlterarFornecedorProd.setDisable(false);
        });

    }

    void desativarbtsEditareExcluirFornecedor() {
        btAlterarFornecedorProd.setDisable(true);
        btExcluirFornecedorProd.setDisable(true);
    }

    void ativarbtsEditareExcluirFornecedor() {
        btAlterarFornecedorProd.setDisable(false);
        btExcluirFornecedorProd.setDisable(false);
    }

    @FXML
    void eventKeyPressedEnterFornecedorProd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaFornecedor();
        }
    }

    private void atualizarListaFornecedor() {
        try {
            tbFornecedorProd.setItems(ControleDAO.getControleBanco().getFornecedorDAO().listar_fornecedorNome(txtPesquisarFornecedorProd.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    public void setCellTableFornecedorProd() {
        colunaIdFornecedorProd.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNomeFornecedorProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaEndFornecedorProd.setCellValueFactory(new PropertyValueFactory<>("end"));
        colunaTelefoneFornecedorProd.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colunaEstadoFornecedorProd.setCellValueFactory(new PropertyValueFactory<>("estado"));

    }

    private boolean validarDadosFornecedorProd() {
        //validação de campos
        String msgErroSalvarFornecedorProd = "";

        if (txtNomeFornecedorProd.getText().length() == 0) {
            msgErroSalvarFornecedorProd += "Nome inválido\n";
        }
        if (txtEndFornecedorProd.getText().length() == 0) {
            msgErroSalvarFornecedorProd += "Endereço inválido\n";
        }
        if (txtTelefoneFornecedorProd.getText().length() == 0) {
            msgErroSalvarFornecedorProd += "Telefone inválido\n";
        }
        //alertas de erros nos campos
        if (msgErroSalvarFornecedorProd.length() == 0) {
            //se a variável msgErro tiver o tamanho 0, retorna true, não mostrando mensagem de erro
            return true;
        } else {
            mensagens.alerta(msgErroSalvarFornecedorProd);
            return false;
        }
    }

    @FXML
    void handleButtonInserirFornecedorProd(ActionEvent event) {
        anchorPaneNovoProd.setVisible(false);
        anchorPaneNovoFornecedorProd.setVisible(true);
        txtIdCategoriaProd.setText("0");
    }

    @FXML
    void handleButtonSalvarFornecedorProd(ActionEvent event) {
        if (validarDadosFornecedorProd()) {
            try {
                fornecedor.setId(Integer.parseInt((txtIdFornecedorProd.getText())));
                fornecedor.setNome(txtNomeFornecedorProd.getText());
                fornecedor.setEnd(txtEndFornecedorProd.getText());
                fornecedor.setTelefone(txtTelefoneFornecedorProd.getText());
                fornecedor.setEstado(comboBoxEstadoFornecedorProd.getValue());
                ControleDAO.getControleBanco().getFornecedorDAO().salvarFornecedor(fornecedor);
                limparCamposFornecedor();
                atualizarListaFornecedor();
                preenchercomboBoxFornecedor();
                tbFornecedorProd.refresh();
                tbFornecedorProd.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
        }
    }

    @FXML
    void handlebuttonAlterarFornecedorProd(ActionEvent event) {
        if (tbFornecedorProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Fornecedor para alteração.");
            desativarbtsEditareExcluirFornecedor();
        } else {
            fornecedorM forn = tbFornecedorProd.getItems().get(tbFornecedorProd.getSelectionModel().getSelectedIndex());
            txtIdFornecedorProd.setText(String.valueOf(forn.getId()));
            txtNomeFornecedorProd.setText(forn.getNome());
            txtEndFornecedorProd.setText(forn.getEnd());
            txtTelefoneFornecedorProd.setText(forn.getTelefone());
            comboBoxEstadoFornecedorProd.setValue(forn.getEstado());
            ativarbtsEditareExcluirFornecedor();
        }
    }

    @FXML
    void handleButtonExcluirFornecedorProd(ActionEvent event) throws Exception {
        if (tbFornecedorProd.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Fornecedor para exclusão.");
            desativarbtsEditareExcluirFornecedor();
        } else {
            fornecedorM forn = tbFornecedorProd.getItems().get(tbFornecedorProd.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getFornecedorDAO().removerFornecedor(forn);
            desativarbtsEditareExcluirFornecedor();
            atualizarListaFornecedor();
            preenchercomboBoxFornecedor();
            tbFornecedorProd.refresh();
            tbFornecedorProd.getSelectionModel().clearSelection();
            
        }
    }

    @FXML
    void handleButtonCancelarFornecedorProd(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Fornecedor?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposFornecedor();
            tbFornecedorProd.getSelectionModel().clearSelection();
            desativarbtsEditareExcluirFornecedor();
            anchorPaneNovoFornecedorProd.setVisible(false);
            anchorPaneNovoProd.setVisible(true);
        }
    }

    void limparCamposFornecedor() {
        txtIdFornecedorProd.setText("0");
        txtNomeFornecedorProd.setText("");
        txtEndFornecedorProd.setText("");
        txtTelefoneFornecedorProd.setText("");
        txtPesquisarFornecedorProd.setText("");
        preenchercomboEstadoFornecedorProd();

    }
}
