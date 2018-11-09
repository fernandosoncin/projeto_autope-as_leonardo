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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import model.dao.ProdutoDAO;
import model.domain.produtoM;
import util.Combo;
import util.MaskFieldUtil;
import util.diálogo;
import util.mensagens;

public class ProdutosController implements Initializable {

    private produtoM produto;
    private ProdutoDAO produtoDAO;

    @FXML
    private ToggleButton btRelatorioProd;

    @FXML
    private AnchorPane anchorPaneProd;

    @FXML
    private AnchorPane anchorPaneInicioProd;

    @FXML
    private TableView<produtoM> tbProd;

    @FXML
    private TableColumn<?, ?> TableColumnIdProd;

    @FXML
    private TableColumn<?, ?> TableColumnNomeProd;

    @FXML
    private TableColumn<?, ?> TableColumnCategoriaProd;

    @FXML
    private TableColumn<?, ?> TableColumnValorProd;

    @FXML
    private Label lbTitulo;

    @FXML
    private TextField txtPesquisarProd;

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
    private TextField txtIdProd;

    @FXML
    private TextField txtNomeProd;

    @FXML
    private TextField txtValorProd;

    @FXML
    private ComboBox<String> comboBoxCategoriaProd;

    @FXML
    private Button btSalvarProd;

    @FXML
    private Button btCancelarProd;

    //----------Início Padrão
    public void preenchercomboBoxCategoria() {
        Combo.popular(comboBoxCategoriaProd, ControleDAO.getControleBanco().getProdutoDAO().comboCategoria());
    }

    @FXML
    void eventKeyPressedEnterProd(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaProd();
        }

    }
    //----------Fim Padrão

    //----------Início Produtos
    private boolean validarDadosProd() {
        //validação de campos
        String msgErro = "";

        if (txtNomeProd.getText() == null || txtNomeProd.getText().length() == 0) {
            msgErro += "Nome inválido\n";
        }
        if (comboBoxCategoriaProd.getValue() == null || comboBoxCategoriaProd.getValue().length() == 0) {
            msgErro += "Categoria inválida\n";
        }
        if (txtValorProd.getText() == null || txtValorProd.getText().length() == 0) {
            msgErro += "Valor inválido\n";
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

            PdfPCell rEnde = new PdfPCell(new Paragraph("Categoria", f3));
            rEnde.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rEnde.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rEnde.setBorder(0);

            PdfPCell rTelefone = new PdfPCell(new Paragraph("Valor", f3));
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

                Paragraph p2 = new Paragraph(prod.getCategoria(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                col2.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);

                Paragraph p3 = new Paragraph(prod.getValor(), f5);
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
            comboBoxCategoriaProd.setValue(produto.getCategoria());
            txtValorProd.setText(String.valueOf(produto.getValor()));
            desativarbtsEditareExcluirProd();
        }
    }

    @FXML
    void handleButtonCancelarProd(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Produto?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposProd();
            anchorPaneNovoProd.setVisible(false);
            anchorPaneInicioProd.setVisible(true);
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
            tbProd.getSelectionModel().clearSelection();
            tbProd.refresh();

        }
    }

    @FXML
    void handleButtonInserirProd(ActionEvent event) {
        anchorPaneInicioProd.setVisible(false);
        anchorPaneNovoProd.setVisible(true);
        txtIdProd.setText("0");
        comboBoxCategoriaProd.setValue("");
    }

    @FXML
    void handleButtonSalvarProd(ActionEvent event) {
        if (validarDadosProd()) {
            try {
                produto.setId(Integer.parseInt((txtIdProd.getText())));
                produto.setNome(txtNomeProd.getText());
                produto.setCategoria(comboBoxCategoriaProd.getValue().toString());
                produto.setValor(txtValorProd.getText());
                ControleDAO.getControleBanco().getProdutoDAO().salvarProd(produto);
                anchorPaneNovoProd.setVisible(false);
                anchorPaneInicioProd.setVisible(true);
                limparCamposProd();
                atualizarListaProd();
                tbProd.refresh();
                desativarbtsEditareExcluirProd();
                tbProd.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
        }
    }

    void desativarbtsEditareExcluirProd() {
        btAlterarProd.setDisable(true);
        btExcluirProd.setDisable(true);
    }

    void limparCamposProd() {
        txtIdProd.setText("0");
        txtNomeProd.setText("");
        comboBoxCategoriaProd.getItems().clear();
        txtValorProd.setText("");
        preenchercomboBoxCategoria();
    }

    private void atualizarListaProd() {
        try {

            tbProd.setItems(ControleDAO.getControleBanco().getProdutoDAO().listar_prod(txtPesquisarProd.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    public void setCellTableFunc() {
        TableColumnIdProd.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnCategoriaProd.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        TableColumnValorProd.setCellValueFactory(new PropertyValueFactory<>("valor"));

    }

    public void selecionarItemTabelaProd() {
        tbProd.setOnMouseClicked(e -> {
            tbProd.requestFocus();
            btExcluirProd.setDisable(false);
            btAlterarProd.setDisable(false);
        });

    }
    //----------Fim Produtos

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //----------Início Inicializador Padrão
        preenchercomboBoxCategoria();
        //----------Fim Inicializador Padrão
        //----------Início Inicializador Produtos
        this.produto = new produtoM();
        this.produtoDAO = new ProdutoDAO();
        setCellTableFunc();
        atualizarListaProd();
        selecionarItemTabelaProd();
        //----------Fim Inicializador Produtos
        //----------Início Máscaras
        MaskFieldUtil.NomePField(txtNomeProd);
        MaskFieldUtil.monetaryField(txtValorProd);
        //----------Fim Máscaras
    }

}
