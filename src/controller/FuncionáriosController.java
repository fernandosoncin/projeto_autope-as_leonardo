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
import javafx.scene.control.Label;
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
import model.dao.CargoDAO;
import model.dao.FuncionárioDAO;
import model.domain.cargoM;
import model.domain.funcionarioM;
import util.Combo;
import util.MaskFieldUtil;
import util.diálogo;
import util.mensagens;
import util.validarCPFeCNPJ;

public class FuncionáriosController implements Initializable {

    private funcionarioM funcionario;
    private FuncionárioDAO funcionarioDAO;
    private cargoM cargo;
    private CargoDAO cargoDAO;

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
    private ToggleButton btNovoCargo;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarFunc;

    @FXML
    private ToggleButton btExcluirFunc;

    @FXML
    private AnchorPane anchorPaneNovoFunc;

    @FXML
    private AnchorPane anchorPaneNovoCargo;

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
    private ComboBox<cargoM> comboBoxCargoFunc;

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

    @FXML
    private Label lbTituloCargos1;

    @FXML
    private TextField txtIdCargo;

    @FXML
    private TextField txtNomeCargo;

    @FXML
    private TableView<cargoM> tbCargos;

    @FXML
    private TableColumn<cargoM, String> colunaIdCargo;

    @FXML
    private TableColumn<cargoM, String> colunaNomeCargo;

    @FXML
    private Button btSalvarCargo;

    @FXML
    private Button btCancelarCargo;

    @FXML
    private TextField txtPesquisarCargo;

    @FXML
    private ToggleButton btExcluirCargo;

    @FXML
    private ToggleGroup menu1;

    @FXML
    private ToggleButton btRelatorioCargo;

    @FXML
    private ToggleButton btAlterarCargo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //----------Início Inicializador Padrão

        preenchercomboCargo();
        //----------Fim Inicializador Padrão
        //----------Início Inicializador Funcionários
        this.funcionario = new funcionarioM();
        this.funcionarioDAO = new FuncionárioDAO();
        preenchercomboEstadoFunc();
        preenchercomboTipo();
        setCellTableFunc();
        atualizarListaFunc();
        selecionarItemTabelaFunc();
        //----------Fim Inicializador Funcionários
        //----------Início Inicializador Cargos
        this.cargo = new cargoM();
        this.cargoDAO = new CargoDAO();
        setCellTableCargo();
        atualizarListaCargo();
        selecionarItemTabelaCargos();
        //----------Fim Inicializador Cargos
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
    void preenchercomboCargo() {
        Combo.popular(comboBoxCargoFunc, ControleDAO.getControleBanco().getCargoDAO().comboCargos());
    }

    //----------Fim Padrão
    //----------Início Funcionários
    private boolean validarDadosFunc() {
        //validação de campos
        String msgErro = "";

        if (txtNomeFunc.getText() == null || txtNomeFunc.getText().length() == 0) {
            msgErro += "Nome inválido\n";
        }
        if (txtRGFunc.getText() == null || txtRGFunc.getText().length() == 0) {
            msgErro += "Celular inválido\n";
        }
        if (validarCPFeCNPJ.isValidCPF(validarCPFeCNPJ.removeMask(txtCPFFunc.getText())) == false) {
            msgErro += "CPF inválido\n";
        }
        if (txtSenhaFunc.getText() == null || txtSenhaFunc.getText().length() == 0) {
            msgErro += "Senha inválida\n";
        }
        if (txtCelularFunc.getText() == null || txtCelularFunc.getText().length() == 0) {
            msgErro += "Celular inválido\n";
        }
        if (txtEmailFunc.getText() == null || txtEmailFunc.getText().length() == 0) {
            msgErro += "Email inválido\n";
        }
        if (txtEnderecoFunc.getText() == null || txtEnderecoFunc.getText().length() == 0) {
            msgErro += "Endereço inválido\n";
        }
        if (txtBairroFunc.getText() == null || txtBairroFunc.getText().length() == 0) {
            msgErro += "Bairro inválido\n";
        }
        if (comboBoxCargoFunc.getItems().isEmpty()) {
            msgErro += "Cargo inválido\n";
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
    void eventKeyPressedEnterFunc(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaFunc();
        }

    }

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
        TableColumnCargoFunc.setCellValueFactory(new PropertyValueFactory<>("cargo_id"));
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
        if (validarDadosFunc()) {
            try {
                funcionario.setId(Integer.parseInt((txtIdFunc.getText())));
                funcionario.setNome(txtNomeFunc.getText());
                funcionario.setRg(txtRGFunc.getText());
                funcionario.setCpf(txtCPFFunc.getText());
                funcionario.setSenha(txtSenhaFunc.getText());
                funcionario.setCelular(txtCelularFunc.getText());
                funcionario.setEmail(txtEmailFunc.getText());
                funcionario.setAdmin(comboBoxTipoFunc.getValue().toString());
                funcionario.setCargo_id(comboBoxCargoFunc.getValue());
                funcionario.setEndereco(txtSenhaFunc.getText());
                funcionario.setBairro(txtBairroFunc.getText());
                funcionario.setEstado(comboBoxEstadoFunc.getValue().toString());
                ControleDAO.getControleBanco().getFuncionárioDAO().salvarFunc(funcionario);
                anchorPaneNovoFunc.setVisible(false);
                anchorPaneInicioFunc.setVisible(true);
                limparCamposFunc();
                atualizarListaFunc();
                tbFunc.refresh();
                tbFunc.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
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
            txtCPFFunc.setText(funcionario.getCpf());
            txtSenhaFunc.setText(funcionario.getSenha());
            txtCelularFunc.setText(funcionario.getCelular());
            txtEmailFunc.setText(funcionario.getEmail());
            comboBoxTipoFunc.setValue(funcionario.getAdmin());
            comboBoxCargoFunc.setValue(funcionario.getCargo_id());
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
        anchorPaneInicioFunc.setVisible(false);
        anchorPaneNovoFunc.setVisible(true);
        preenchercomboCargo();
        txtIdFunc.setText("0");

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
        preenchercomboCargo();
    }

    //----------Fim Funcionários
    //----------Início Cargos
    @FXML
    void handleButtonInserirCargo(ActionEvent event) {
        anchorPaneNovoFunc.setVisible(false);
        anchorPaneNovoCargo.setVisible(true);
        txtIdCargo.setText("0");

    }

    public void setCellTableCargo() {
        colunaIdCargo.setCellValueFactory(new PropertyValueFactory<>("cod_Cargo"));
        colunaNomeCargo.setCellValueFactory(new PropertyValueFactory<>("nome_Cargo"));
    }

    private void atualizarListaCargo() {
        try {

            tbCargos.setItems(ControleDAO.getControleBanco().getCargoDAO().listar_cargoNome(txtPesquisarCargo.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    void ativarbtsExcluirCancelarAlterarCargo() {
        btExcluirCargo.setDisable(false);
        btAlterarCargo.setDisable(false);
    }

    void desativasbtsExcluirAlterarCargo() {
        btExcluirCargo.setDisable(true);
        btAlterarCargo.setDisable(true);
    }

    public void selecionarItemTabelaCargos() {
        tbCargos.setOnMouseClicked(e -> {
            ativarbtsExcluirCancelarAlterarCargo();
            tbCargos.requestFocus();

        });

    }

    void limparCamposCargo() {
        txtIdCargo.setText("0");
        txtNomeCargo.setText("");
        txtPesquisarCargo.setText("");
        cargo.setCod_Cargo(0);
    }

    @FXML
    void handleButtonCancelarCargo(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cargo?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposCargo();
            tbCargos.getSelectionModel().clearSelection();
            desativasbtsExcluirAlterarCargo();
            anchorPaneNovoCargo.setVisible(false);
            anchorPaneNovoFunc.setVisible(true);
        }
    }

    private boolean validarDadosSalvarCargos() {
        //validação de campos
        String msgErroSalvar = "";

        if (txtNomeCargo.getText().length() == 0) {
            msgErroSalvar += "Nome de cargo inválido\n";
        }
        //alertas de erros nos campos
        if (msgErroSalvar.length() == 0) {
            //se a variável msgErro tiver o tamanho 0, retorna true, não mostrando mensagem de erro
            return true;
        } else {
            mensagens.alerta(msgErroSalvar);
            return false;
        }
    }

    @FXML
    void handleButtonExcluirCargo(ActionEvent event) throws Exception {
        if (tbCargos.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cargo para exclusão.");
            desativasbtsExcluirAlterarCargo();
        } else if (txtNomeCargo.getText().equals("Vendedor")) {
            mensagens.erro("Não é possível excluir o cargo de vendedor.");
        } else {
            {
                cargoM cargo = tbCargos.getItems().get(tbCargos.getSelectionModel().getSelectedIndex());
                if (cargo.getNome_Cargo().equals("Vendedor")) {
                    mensagens.erro("Não é possível excluir o cargo de vendedor.");
                } else {
                    ControleDAO.getControleBanco().getCargoDAO().removerCargo(cargo);
                    tbCargos.getItems().clear();
                    atualizarListaCargo();
                    limparCamposCargo();
                    preenchercomboCargo();
                    btSalvarCargo.setDisable(false);
                    btExcluirCargo.setDisable(true);
                    btAlterarCargo.setDisable(true);
                }

            }
        }
    }

    @FXML
    void handleButtonSalvarCargo(ActionEvent event) {
        if (validarDadosSalvarCargos()) {
            try {
                cargo.setCod_Cargo(Integer.valueOf(txtIdCargo.getText()));
                cargo.setNome_Cargo(txtNomeCargo.getText());
                ControleDAO.getControleBanco().getCargoDAO().salvar(cargo);
                limparCamposCargo();
                tbCargos.requestFocus();
                btExcluirCargo.setDisable(true);
                btAlterarCargo.setDisable(true);
                tbCargos.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
            tbCargos.getItems().clear();
            atualizarListaCargo();
            preenchercomboCargo();
        }
    }

    @FXML
    void handlebuttonAlterarCargo() {
        if (tbCargos.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cargo para alteração.");
            desativasbtsExcluirAlterarCargo();
        } else {
            cargoM cargo = tbCargos.getItems().get(tbCargos.getSelectionModel().getSelectedIndex());
            txtIdCargo.setText(String.valueOf(cargo.getCod_Cargo()));
            txtNomeCargo.setText(cargo.getNome_Cargo());
            ativarbtsExcluirCancelarAlterarCargo();
        }

    }

    @FXML
    void eventKeyPressedEnterCargo(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaCargo();
        }

    }

    public void gerarRelatorioCargo() throws IOException {
        String data, hora;
        data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        hora = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));

        try {
            List<cargoM> listaRCargo = new ArrayList<>();
            listaRCargo = cargoDAO.relatCargo();
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Relatorios/RelatorioCargo" + ".pdf"));
            doc.open();

            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("Relatório de Cargos", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(0);

            Paragraph nomeData = new Paragraph(data + "  " + hora, f5);
            nomeData.setAlignment(Element.ALIGN_CENTER);
            nomeData.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(new float[]{0.40f, 0.60f, 0.80f});
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell rNome = new PdfPCell(new Paragraph("Cargos >", f3));
            rNome.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rNome.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rNome.setBorder(0);

            tabela.addCell(rNome);

            for (cargoM cargoR : listaRCargo) {
                Paragraph p1 = new Paragraph(cargoR.getNome_Cargo(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                tabela.addCell(col1);

            }

            doc.add(titulo2);
            doc.add(titulo1);
            doc.add(nomeData);
            doc.add(tabela);
            doc.close();
            mensagens.info("Relatório salvo com sucesso.", "Relatório de cargos");
            String caminho = "C:/Relatorios/RelatorioCargo.pdf";
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            mensagens.erro(e + "");
        } catch (SQLException ex) {
            mensagens.erro(ex + "");
        } catch (IOException exx) {
            mensagens.erro(exx + "\nOutro relatório de cargos se encontra aberto. Feche para gerar outro.", "Relatório de cargos");
        }
    }

    @FXML
    void handleButtonRelatorioCargos(ActionEvent event) {
        String nomediretorio = null;
        String nomepasta = "Relatorios"; //Nome da pasta que vai armazenar relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarRelatorioCargo();
        } catch (Exception e) {
            mensagens.erro(e + "");
        }
    }
    //----------Fim Cargos
}
