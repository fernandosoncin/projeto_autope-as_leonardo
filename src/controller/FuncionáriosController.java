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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.dao.FuncionárioDAO;
import model.domain.funcionarioM;
import util.Combo;
import util.MaskFieldUtil;
import util.diálogo;
import util.mensagens;

/**
 * FXML Controller class
 *
 * @author Fellipe
 */
public class FuncionáriosController implements Initializable {

    private funcionarioM funcionario;
    private FuncionárioDAO funcionarioDAO;

    @FXML
    private ToggleButton btRelatorioFunc;

    @FXML
    private AnchorPane anchorPaneFunc;

    @FXML
    private AnchorPane anchorPaneInicioFunc;

    @FXML
    private TextField txtPesquisarFunc;

    @FXML
    private ToggleButton btNovoFunc;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarFunc;

    @FXML
    private ToggleButton btExcluirFunc;

    @FXML
    private AnchorPane anchorPaneNovoFunc;

    @FXML
    private TextField txtIdFunc;

    @FXML
    private TextField txtNomeFunc;

    @FXML
    private TextField txtCelularFunc;

    @FXML
    private TextField txtEmailFunc;

    @FXML
    private TextField txtEnderecoFunc;

    @FXML
    private TextField txtBairroFunc;

    @FXML
    private ComboBox<String> comboBoxEstadoFunc;

    @FXML
    private ComboBox<String> comboBoxTipoFunc;

    @FXML
    private TextField txtCPFFunc;

    @FXML
    private PasswordField txtSenhaFunc;

    @FXML
    private TextField txtRGFunc;

    @FXML
    private ComboBox<String> comboBoxCargoFunc;

    @FXML
    private Button btSalvarFunc;

    @FXML
    private Button btCancelarFunc;

    @FXML
    private TableView<funcionarioM> tbFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnIdFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnNomeFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnRGFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnCPFFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnCelFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnEmailFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnTipoFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnSenhaFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnCargoFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnEndFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnBairroFunc;

    @FXML
    private TableColumn<funcionarioM, String> TableColumnEstadoFunc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //----------Início Inicializador Padrão
        preenchercomboBoxCargos();
        //----------Fim Inicializador Padrão

        //----------Início Inicializador Funcionários
        this.funcionario = new funcionarioM();
        this.funcionarioDAO = new FuncionárioDAO();
        preenchercomboEstadoFunc();
        preenchercomboTipo();
        preenchercomboBoxCargos();
        setCellTableFunc();
        atualizarListaFunc();
        selecionarItemTabelaFunc();
        //----------Fim Inicializador Funcionários
        //----------Início Máscaras
        MaskFieldUtil.NomeFuncField(txtNomeFunc);
        MaskFieldUtil.foneCelularField(txtCelularFunc);
        MaskFieldUtil.EmailFuncField(txtEmailFunc);
        MaskFieldUtil.EndFuncField(txtEnderecoFunc);
        MaskFieldUtil.BairroFuncField(txtBairroFunc);
        MaskFieldUtil.cpfField(txtCPFFunc);
        MaskFieldUtil.rgField(txtRGFunc);
        MaskFieldUtil.SenhaFuncField(txtSenhaFunc);
        //----------Fim Máscaras
    }

    //----------Início Padrão
    public void preenchercomboBoxCargos() {
        Combo.popular(comboBoxCargoFunc, ControleDAO.getControleBanco().getFuncionárioDAO().comboCargo());

    }

    @FXML
    void eventKeyPressedEnterFunc(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaFunc();
        }

    }    //----------Fim Padrão
    //----------Início Funcionários

    @FXML
    void handleButtonRelatorioFunc(ActionEvent event) {
        String nomediretorio = null;
        String nomepasta = "Relatorios"; //Nome da pasta que vai armazenar relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarRelatorioFunc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioFunc() throws IOException, SQLException, DocumentException {
        String data, hora;
        data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        hora = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));

        try {
            List<funcionarioM> listaRFunc = new ArrayList<>();
            listaRFunc = funcionarioDAO.relatFunc();
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Relatorios/RelatorioFunc" + ".pdf"));
            doc.open();

            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("Relatório de Clientes Físico", f1);
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

            PdfPCell rEnde = new PdfPCell(new Paragraph("Celular", f3));
            rEnde.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rEnde.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rEnde.setBorder(0);

            PdfPCell rTelefone = new PdfPCell(new Paragraph("Email", f3));
            rTelefone.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rTelefone.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rTelefone.setBorder(0);

            tabela.addCell(rNome);
            tabela.addCell(rEnde);
            tabela.addCell(rTelefone);

            for (funcionarioM func : listaRFunc) {
                Paragraph p1 = new Paragraph(func.getNome(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                col1.setBorder(0);

                Paragraph p2 = new Paragraph(func.getCelular(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                col2.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);

                Paragraph p3 = new Paragraph(func.getEmail(), f5);
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
            mensagens.info("Relatório salvo com sucesso.", "Relatório de Funcionários");
            //JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/Relatorios/RelatorioFunc.pdf";
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            mensagens.erro(e + "", "Erro");
        } catch (SQLException ex) {
            mensagens.erro(ex + "", "Erro");
        } catch (IOException exx) {
            mensagens.erro("Outro relatório de funcionários se encontra aberto. Feche para gerar outro.", "Relatório de funcionários");
        }
    }

    private void preenchercomboEstadoFunc() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoFunc, tipo);
    }

    private void preenchercomboTipo() {
        ObservableList<String> tipo = FXCollections.observableArrayList("COMUM", "ADM");
        Combo.popular(comboBoxTipoFunc, tipo);
    }

    private void atualizarListaFunc() {
        try {

            tbFunc.setItems(ControleDAO.getControleBanco().getFuncionárioDAO().listar_func(txtPesquisarFunc.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    public void setCellTableFunc() {
        TableColumnIdFunc.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnNomeFunc.setCellValueFactory(new PropertyValueFactory<>("nome"));
        TableColumnRGFunc.setCellValueFactory(new PropertyValueFactory<>("rg"));
        TableColumnCPFFunc.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        TableColumnSenhaFunc.setCellValueFactory(new PropertyValueFactory<>("senha"));
        TableColumnCelFunc.setCellValueFactory(new PropertyValueFactory<>("celular"));
        TableColumnEmailFunc.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumnTipoFunc.setCellValueFactory(new PropertyValueFactory<>("admin"));
        TableColumnCargoFunc.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        TableColumnEndFunc.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumnBairroFunc.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        TableColumnEstadoFunc.setCellValueFactory(new PropertyValueFactory<>("estado"));

    }

    public void selecionarItemTabelaFunc() {
        tbFunc.setOnMouseClicked(e -> {
            tbFunc.requestFocus();
            btExcluirFunc.setDisable(false);
            btAlterarFunc.setDisable(false);
        });

    }

    void desativarbtsEditareExcluirFunc() {
        btAlterarFunc.setDisable(true);
        btExcluirFunc.setDisable(true);
    }

    @FXML
    void handleButtonSalvarFunc(ActionEvent event) {
        try {
            funcionario.setId(Integer.parseInt((txtIdFunc.getText())));
            funcionario.setNome(txtNomeFunc.getText());
            funcionario.setRg(txtRGFunc.getText());
            funcionario.setCpf(txtCPFFunc.getText());
            funcionario.setSenha(txtSenhaFunc.getText());
            funcionario.setCelular(txtCelularFunc.getText());
            funcionario.setEmail(txtEmailFunc.getText());
            funcionario.setAdmin(comboBoxTipoFunc.getValue().toString());
            funcionario.setCargo(comboBoxCargoFunc.getValue());
            funcionario.setEndereco(txtSenhaFunc.getText());
            funcionario.setBairro(txtBairroFunc.getText());
            funcionario.setEstado(comboBoxEstadoFunc.getValue().toString());
            ControleDAO.getControleBanco().getFuncionárioDAO().salvarFunc(funcionario);
            anchorPaneNovoFunc.setVisible(false);
            anchorPaneInicioFunc.setVisible(true);
            limparCamposFunc();
            atualizarListaFunc();
            tbFunc.refresh();
            desativarbtsEditareExcluirFunc();
            tbFunc.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
        }
    }

    @FXML
    void handlebuttonAlterarFunc() {
        if (tbFunc.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Funcionário para alteração.");
            desativarbtsEditareExcluirFunc();
        } else {
            anchorPaneInicioFunc.setVisible(false);
            anchorPaneNovoFunc.setVisible(true);
            funcionarioM funcionario = tbFunc.getItems().get(tbFunc.getSelectionModel().getSelectedIndex());
            txtIdFunc.setText(String.valueOf(funcionario.getId()));
            txtNomeFunc.setText(funcionario.getNome());
            txtRGFunc.setText(funcionario.getRg());
            txtSenhaFunc.setText(funcionario.getSenha());
            txtCelularFunc.setText(funcionario.getCelular());
            txtEmailFunc.setText(funcionario.getEmail());
            comboBoxTipoFunc.setValue(funcionario.getAdmin());
            comboBoxCargoFunc.setValue(funcionario.getCargo());
            txtEnderecoFunc.setText(funcionario.getEndereco());
            txtBairroFunc.setText(funcionario.getBairro());
            comboBoxTipoFunc.setValue(funcionario.getAdmin());
            comboBoxEstadoFunc.setValue(funcionario.getEstado());
            desativarbtsEditareExcluirFunc();
        }

    }

    @FXML
    void handlebuttonExcluirFunc() throws Exception {
        if (tbFunc.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Funcionário para exclusão.");
            desativarbtsEditareExcluirFunc();
        } else {
            funcionarioM funcionario = tbFunc.getItems().get(tbFunc.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getFuncionárioDAO().excluirFunc(funcionario);
            desativarbtsEditareExcluirFunc();
            atualizarListaFunc();
            tbFunc.refresh();
            tbFunc.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void handleButtonCancelarFunc(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Funcionário?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposFunc();
            anchorPaneNovoFunc.setVisible(false);
            anchorPaneInicioFunc.setVisible(true);
        }
    }

    @FXML
    void handleButtonInserirFunc(ActionEvent event) {
        //tabela.getSelectionModel().clearSelection();
        anchorPaneInicioFunc.setVisible(false);
        anchorPaneNovoFunc.setVisible(true);
        comboBoxCargoFunc.setValue("");
    }

    @FXML
    void limparCamposFunc() {
        txtNomeFunc.setText("");
        comboBoxEstadoFunc.getItems().clear();
        txtCelularFunc.setText("");
        txtEmailFunc.setText("");
        txtEnderecoFunc.setText("");
        txtBairroFunc.setText("");
        comboBoxCargoFunc.getItems().clear();
        txtCPFFunc.setText("");
        comboBoxTipoFunc.getItems().clear();
        txtSenhaFunc.setText("");
        txtRGFunc.setText("");
        txtIdFunc.setText("0");
        preenchercomboEstadoFunc();
        preenchercomboTipo();
        preenchercomboBoxCargos();
    }

    //----------Fim Funcionários
}
