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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.dao.ClienteFDAO;
import model.dao.ClienteJDAO;
import model.domain.clienteMF;
import model.domain.clienteMJ;
import util.Combo;
import util.MaskFieldUtil;
import util.diálogo;
import util.mensagens;
import util.validarCPFeCNPJ;

/**
 * FXML Controller class
 *
 * @author Fellipe
 */
public class ClientesController implements Initializable {

    private clienteMF clienteF;
    private clienteMJ clienteJ;
    private ClienteFDAO clienteFDAO;
    private ClienteJDAO clienteJDAO;

    @FXML
    private ToggleButton btRelatorioF;

    @FXML
    private ToggleButton btRelatorioJ;

    @FXML
    private AnchorPane anchorPaneCliente;

    @FXML
    private AnchorPane anchorPaneNovoClienteFísico;

    @FXML
    private TextField txtIdCliente;

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtCelularCliente;

    @FXML
    private TextField txtEmailCliente;

    @FXML
    private TextField txtEnderecoCliente;

    @FXML
    private TextField txtBairroCliente;

    @FXML
    private ComboBox<String> comboBoxEstadoCliente;

    @FXML
    private TextField txtCPFCF;

    @FXML
    private Button btSalvar;

    @FXML
    private Button btCancelar;

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
    private TableColumn<clienteMJ, String> TableColumnIdJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnRazãoSocial;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnCNPJ;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnTelefoneJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnEmailJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnEnderecoJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnBairroJur;

    @FXML
    private TableColumn<clienteMJ, String> TableColumnEstadoJur;

    @FXML
    private AnchorPane anchorPaneInicioCliente;

    @FXML
    private TableView<clienteMF> tableClienteFísico;

    @FXML
    private TableView<clienteMJ> tableClienteJur;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private ToggleButton btNovoClienteFísico;

    @FXML
    private ToggleGroup menu;

    @FXML
    private ToggleButton btAlterarClienteFísico;

    @FXML
    private ToggleButton btExcluirClienteFísico;

    @FXML
    private AnchorPane anchorPaneInicioCliente1;

    @FXML
    private TextField txtPesquisarJur;

    @FXML
    private ToggleButton btNovoClienteJur;

    @FXML
    private ToggleGroup menu1;

    @FXML
    private ToggleButton btAlterarClienteJur;

    @FXML
    private ToggleButton btExcluirClienteJur;

    @FXML
    private AnchorPane anchorPaneNovoClienteJur;

    @FXML
    private TextField txtIdClienteJur;

    @FXML
    private TextField txtRazãoSocial;

    @FXML
    private TextField txtTelefoneClienteJur;

    @FXML
    private TextField txtEmailClienteJur;

    @FXML
    private TextField txtEnderecoClienteJur;

    @FXML
    private TextField txtBairroClienteJur;

    @FXML
    private ComboBox<String> comboBoxEstadoClienteJur;

    @FXML
    private TextField txtCNPJ;

    @FXML
    private Button btSalvarJur;

    @FXML
    private Button btCancelarJur;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //----------Início Inicializador Padrão
        comboEstadoF();
        comboEstadoJ();
        //----------Fim Inicializador Padrão
        //----------Início Inicializador Cliente Físico
        this.clienteF = new clienteMF();
        this.clienteFDAO = new ClienteFDAO();
        comboEstadoF();
        setCellTableClienteF();
        atualizarListaClienteFisico();
        selecionarItemTabelaClienteFísico();
        //----------Fim Inicializador Cliente Físico

        //----------Início Inicializador Cliente Jurídico
        this.clienteJ = new clienteMJ();
        this.clienteJDAO = new ClienteJDAO();
        setCellTableClienteJ();
        atualizarListaClienteJur();
        selecionarItemTabelaClienteJur();
        //----------Fim Inicializador Cliente Jurídico
        //----------Início Máscaras
        //Cliente Físico
        MaskFieldUtil.NomeCFField(txtNomeCliente);
        MaskFieldUtil.foneCelularField(txtCelularCliente);
        MaskFieldUtil.EmailCFField(txtEmailCliente);
        MaskFieldUtil.EndCFField(txtEnderecoCliente);
        MaskFieldUtil.BairroCFField(txtBairroCliente);
        MaskFieldUtil.cnpjField(txtCNPJ);
        MaskFieldUtil.cpfField(txtCPFCF);
        //Cliente Jurídico
        MaskFieldUtil.RazaoSCJField(txtRazãoSocial);
        MaskFieldUtil.foneCelularField(txtTelefoneClienteJur);
        MaskFieldUtil.EmailCJField(txtEmailClienteJur);
        MaskFieldUtil.EndCJField(txtEnderecoClienteJur);
        MaskFieldUtil.BairroCJField(txtBairroClienteJur);
        MaskFieldUtil.cnpjField(txtCNPJ);
        //----------Fim Máscaras
    }

    //----------Início Padrão
    private void comboEstadoF() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoCliente, tipo);
    }

    private void comboEstadoJ() {
        ObservableList<String> tipo = FXCollections.observableArrayList("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
        Combo.popular(comboBoxEstadoClienteJur, tipo);
    }
    //----------Fim Padrão

    //----------Início Cliente Físico
    private boolean validarDadosF() {
        //validação de campos
        String msgErro = "";

        if (txtNomeCliente.getText() == null || txtNomeCliente.getText().length() == 0) {
            msgErro += "Nome inválido\n";
        }
        if (txtCelularCliente.getText() == null || txtCelularCliente.getText().length() == 0) {
            msgErro += "Celular inválido\n";
        }
        if (txtEmailCliente.getText() == null || txtEmailCliente.getText().length() == 0) {
            msgErro += "Email inválido\n";
        }
        if (txtEnderecoCliente.getText() == null || txtEnderecoCliente.getText().length() == 0) {
            msgErro += "Endereço inválido\n";
        }
        if (txtBairroCliente.getText() == null || txtBairroCliente.getText().length() == 0) {
            msgErro += "Bairro inválido\n";
        }
        if (validarCPFeCNPJ.isValidCPF(validarCPFeCNPJ.removeMask(txtCPFCF.getText())) == false) {
            msgErro += "CPF inválido\n";
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
    void handleButtonRelatorioF(ActionEvent event) {
        String nomediretorio = null;
        String nomepasta = "Relatorios"; //Nome da pasta que vai armazenar relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarRelatorioF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioF() throws IOException, SQLException {
        String data, hora;
        data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        hora = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));

        try {
            List<clienteMF> listaRF = new ArrayList<>();
            listaRF = clienteFDAO.relatF();
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Relatorios/RelatorioClienteF" + ".pdf"));
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

            for (clienteMF cliente : listaRF) {
                Paragraph p1 = new Paragraph(cliente.getNome(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                col1.setBorder(0);

                Paragraph p2 = new Paragraph(cliente.getCelular(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                col2.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);

                Paragraph p3 = new Paragraph(cliente.getEmail(), f5);
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
            mensagens.info("Relatório salvo com sucesso.", "Relatório cliente físico");
            //JOptionPane.showMessageDialog(null, "Relatório salvo com sucesso");
            String caminho = "C:/Relatorios/RelatorioClienteF.pdf";
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            mensagens.erro(e + "", "Erro");
        } catch (SQLException ex) {
            mensagens.erro(ex + "", "Erro");
        } catch (IOException exx) {
            mensagens.erro("Outro relatório de cliente jurídico se encontra aberto. Feche para gerar outro.", "Relatório cliente físico");
        }
    }

    @FXML
    void handleButtonSalvarFísico(ActionEvent event) {
        if (validarDadosF()) {
            try {
                clienteF.setId(Integer.parseInt((txtIdCliente.getText())));
                clienteF.setNome(txtNomeCliente.getText());
                clienteF.setCelular(txtCelularCliente.getText());
                clienteF.setCpf(txtCPFCF.getText());
                clienteF.setEmail(txtEmailCliente.getText());
                clienteF.setEndereco(txtEnderecoCliente.getText());
                clienteF.setBairro(txtBairroCliente.getText());
                clienteF.setEstado(comboBoxEstadoCliente.getValue().toString());
                ControleDAO.getControleBanco().getClienteFDAO().salvarF(clienteF);
                anchorPaneNovoClienteFísico.setVisible(false);
                anchorPaneInicioCliente.setVisible(true);
                limparCamposFísico();
                atualizarListaClienteFisico();
                tableClienteFísico.refresh();
                desativarbtsEditareExcluirF();
                tableClienteFísico.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
            //tbCargos.getItems().clear();
            //atualizarListaCargo();
        }
    }

    @FXML
    void handleButtonCancelar(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente Físico?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposFísico();
            anchorPaneNovoClienteFísico.setVisible(false);
            anchorPaneInicioCliente.setVisible(true);
            desativarbtsEditareExcluirF();
        }
    }

    @FXML
    void handleButtonInserirFísico(ActionEvent event) {
        anchorPaneInicioCliente.setVisible(false);
        anchorPaneNovoClienteFísico.setVisible(true);
        txtIdCliente.setText("0");

    }

    @FXML
    private void atualizarListaClienteFisico() {
        try {

            tableClienteFísico.setItems(ControleDAO.getControleBanco().getClienteFDAO().listar_clienteF(txtPesquisar.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
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
    public void selecionarItemTabelaClienteFísico() {
        tableClienteFísico.setOnMouseClicked(e -> {
            tableClienteFísico.requestFocus();
            btExcluirClienteFísico.setDisable(false);
            btAlterarClienteFísico.setDisable(false);
        });

    }

    void limparCamposFísico() {
        txtIdCliente.setText("0");
        txtNomeCliente.setText("");
        comboBoxEstadoCliente.getItems().clear();
        txtCelularCliente.setText("");
        txtEmailCliente.setText("");
        txtEnderecoCliente.setText("");
        txtBairroCliente.setText("");
        comboBoxEstadoCliente.getItems().clear();
        txtCPFCF.setText("");
        comboEstadoF();
    }

    @FXML
    void handlebuttonAlterarFísico() {
        if (tableClienteFísico.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Físico para alteração.");
            desativarbtsEditareExcluirF();
        } else {
            anchorPaneInicioCliente.setVisible(false);
            anchorPaneNovoClienteFísico.setVisible(true);
            clienteMF cliente = tableClienteFísico.getItems().get(tableClienteFísico.getSelectionModel().getSelectedIndex());
            txtIdCliente.setText(String.valueOf(cliente.getId()));
            txtNomeCliente.setText(cliente.getNome());
            txtCPFCF.setText(cliente.getCpf());
            txtCelularCliente.setText(cliente.getCelular());
            txtEmailCliente.setText(cliente.getEmail());
            txtEnderecoCliente.setText(cliente.getEndereco());
            txtBairroCliente.setText(cliente.getBairro());
            comboBoxEstadoCliente.setValue(cliente.getEstado());
            desativarbtsEditareExcluirF();
        }

    }

    void desativarbtsEditareExcluirF() {
        btAlterarClienteFísico.setDisable(true);
        btExcluirClienteFísico.setDisable(true);
    }

    @FXML
    void handlebuttonExcluirFísico() throws Exception {
        if (tableClienteFísico.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Físico para exclusão.");
            desativarbtsEditareExcluirF();
        } else {
            clienteMF clienteF = tableClienteFísico.getItems().get(tableClienteFísico.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getClienteFDAO().excluirClienteF(clienteF);
            desativarbtsEditareExcluirF();
            atualizarListaClienteFisico();
            tableClienteFísico.refresh();
            tableClienteFísico.getSelectionModel().clearSelection();
        }

    }

    @FXML
    void eventKeyPressedEnterF(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaClienteFisico();
        }

    }

    //----------Fim Cliente Físico
    //----------Início Cliente Jurídico
    private boolean validarDadosJ() {
        //validação de campos
        String msgErro = "";

        if (txtRazãoSocial.getText() == null || txtRazãoSocial.getText().length() == 0) {
            msgErro += "Razão Social inválida\n";
        }
        if (txtTelefoneClienteJur.getText() == null || txtTelefoneClienteJur.getText().length() == 0) {
            msgErro += "Telefone inválido\n";
        }
        if (txtEmailClienteJur.getText() == null || txtEmailClienteJur.getText().length() == 0) {
            msgErro += "Email inválido\n";
        }
        if (txtEnderecoClienteJur.getText() == null || txtEnderecoClienteJur.getText().length() == 0) {
            msgErro += "Endereço inválido\n";
        }
        if (txtBairroClienteJur.getText() == null || txtBairroClienteJur.getText().length() == 0) {
            msgErro += "Bairro inválido\n";
        }
        if (validarCPFeCNPJ.isValidCNPJ(validarCPFeCNPJ.removeMask(txtCNPJ.getText())) == false) {
            msgErro += "CNPJ inválido\n";
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
    void handleButtonRelatorioJ(ActionEvent event) {
        String nomediretorio = null;
        String nomepasta = "Relatorios"; //Nome da pasta que vai armazenar relatório
        String separador = java.io.File.separator;
        try {
            nomediretorio = "C:" + separador + nomepasta;
            if (!new File(nomediretorio).exists()) {
                (new File(nomediretorio)).mkdir();
            }
            gerarRelatorioJ();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioJ() throws IOException {
        String data, hora;
        data = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis()));
        hora = new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis()));

        try {
            List<clienteMJ> listaRJ = new ArrayList<>();
            listaRJ = clienteJDAO.relatJ();
            Document doc = new Document(PageSize.A4, 41.5f, 41.5f, 55.2f, 55.2f);
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Relatorios/RelatorioClienteJ" + ".pdf"));
            doc.open();

            Font f1 = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font f2 = new Font(Font.HELVETICA, 12, Font.BOLD);
            Font f3 = new Font(Font.HELVETICA, 12, Font.NORMAL);
            Font f4 = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font f5 = new Font(Font.HELVETICA, 10, Font.NORMAL);

            Paragraph titulo1 = new Paragraph("Universidade do Estado de Minas Gerais", f2);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setSpacingAfter(10);

            Paragraph titulo2 = new Paragraph("Relatório de Clientes Jurídico", f1);
            titulo2.setAlignment(Element.ALIGN_CENTER);
            titulo2.setSpacingAfter(0);

            Paragraph nomeData = new Paragraph(data + "  " + hora, f5);
            nomeData.setAlignment(Element.ALIGN_CENTER);
            nomeData.setSpacingAfter(10);

            PdfPTable tabela = new PdfPTable(new float[]{0.40f, 0.60f, 0.80f});
            tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.setWidthPercentage(100f);

            PdfPCell rRS = new PdfPCell(new Paragraph("Razão Social", f3));
            rRS.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rRS.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rRS.setBorder(0);

            PdfPCell rEnde = new PdfPCell(new Paragraph("Celular", f3));
            rEnde.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rEnde.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rEnde.setBorder(0);

            PdfPCell rTelefone = new PdfPCell(new Paragraph("Email", f3));
            rTelefone.setBackgroundColor(new Color(0xc0, 0xc0, 0xc0));
            rTelefone.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            rTelefone.setBorder(0);

            tabela.addCell(rRS);
            tabela.addCell(rEnde);
            tabela.addCell(rTelefone);

            for (clienteMJ clientej : listaRJ) {
                Paragraph p1 = new Paragraph(clientej.getRs(), f5);
                p1.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col1 = new PdfPCell(p1);
                col1.setBorder(0);

                Paragraph p2 = new Paragraph(clientej.getTelefone(), f5);
                p2.setAlignment(Element.ALIGN_JUSTIFIED);
                PdfPCell col2 = new PdfPCell(p2);
                col2.setBorder(0);
                tabela.addCell(col1);
                tabela.addCell(col2);

                Paragraph p3 = new Paragraph(clientej.getEmail(), f5);
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
            mensagens.info("Relatório salvo com sucesso.", "Relatório cliente jurídico");
            String caminho = "C:/Relatorios/RelatorioClienteJ.pdf";
            Desktop.getDesktop().open(new File(caminho));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException exx) {
            exx.printStackTrace();
            mensagens.erro("Outro relatório de cliente jurídico se encontra aberto. Feche para gerar outro.", "Relatório cliente jurídico");
        }
    }

    @FXML
    public void selecionarItemTabelaClienteJur() {
        tableClienteJur.setOnMouseClicked(e -> {
            tableClienteJur.requestFocus();
            btExcluirClienteJur.setDisable(false);
            btAlterarClienteJur.setDisable(false);
        });

    }

    @FXML
    void eventKeyPressedEnterJ(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            atualizarListaClienteJur();
        }

    }

    @FXML
    private void atualizarListaClienteJur() {
        try {

            tableClienteJur.setItems(ControleDAO.getControleBanco().getClienteJDAO().listar_clienteJ(txtPesquisarJur.getText()));
        } catch (Exception ex) {
            mensagens.erro("Erro : " + ex.getMessage());
        }
    }

    void desativarbtsEditareExcluirJ() {
        btAlterarClienteJur.setDisable(true);
        btExcluirClienteJur.setDisable(true);
    }

    @FXML
    void limparCamposJ() {
        txtIdClienteJur.setText("0");
        txtRazãoSocial.setText("");
        txtCNPJ.setText("");
        txtTelefoneClienteJur.setText("");
        txtEmailClienteJur.setText("");
        txtEnderecoClienteJur.setText("");
        txtBairroClienteJur.setText("");
        comboBoxEstadoClienteJur.getItems().clear();
        comboEstadoJ();
    }

    @FXML
    void handleButtonCancelarJur(ActionEvent event) {
        diálogo.Resposta resp = mensagens.confirmar("Fechar cadastro", "Realmente deseja cancelar o cadastro do Cliente Jurídico?");
        if (resp == diálogo.Resposta.YES) {
            limparCamposJ();
            anchorPaneNovoClienteJur.setVisible(false);
            anchorPaneInicioCliente1.setVisible(true);

        }
    }

    @FXML
    void handleButtonInserirJur(ActionEvent event) {
        anchorPaneInicioCliente1.setVisible(false);
        anchorPaneNovoClienteJur.setVisible(true);
        txtIdClienteJur.setText("0");
    }

    public void setCellTableClienteJ() {
        TableColumnIdJur.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumnRazãoSocial.setCellValueFactory(new PropertyValueFactory<>("rs"));
        TableColumnCNPJ.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        TableColumnTelefoneJur.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        TableColumnEmailJur.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumnEnderecoJur.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        TableColumnBairroJur.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        TableColumnEstadoJur.setCellValueFactory(new PropertyValueFactory<>("estado"));

    }

    @FXML
    void handleButtonSalvarJ(ActionEvent event) {
        if (validarDadosJ()) {
            try {
                clienteJ.setId(Integer.parseInt((txtIdClienteJur.getText())));
                clienteJ.setRs(txtRazãoSocial.getText());
                clienteJ.setCnpj(txtCNPJ.getText());
                clienteJ.setTelefone(txtTelefoneClienteJur.getText());
                clienteJ.setEmail(txtEmailClienteJur.getText());
                clienteJ.setEndereco(txtEnderecoClienteJur.getText());
                clienteJ.setBairro(txtBairroClienteJur.getText());
                clienteJ.setEstado(comboBoxEstadoClienteJur.getValue().toString());
                ControleDAO.getControleBanco().getClienteJDAO().salvarJ(clienteJ);
                anchorPaneNovoClienteJur.setVisible(false);
                anchorPaneInicioCliente1.setVisible(true);
                limparCamposJ();
                atualizarListaClienteJur();
                tableClienteJur.refresh();
                desativarbtsEditareExcluirJ();
                tableClienteJur.getSelectionModel().clearSelection();
            } catch (Exception ex) {
                mensagens.erro("Erro ao salvar dados : " + ex.getMessage());
            }
            //tbCargos.getItems().clear();
            //atualizarListaCargo();
        }
    }

    @FXML
    void handlebuttonAlterarJ() {
        if (tableClienteJur.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Jurídico para alteração.");
            desativarbtsEditareExcluirJ();
        } else {
            anchorPaneInicioCliente1.setVisible(false);
            anchorPaneNovoClienteJur.setVisible(true);
            clienteMJ clienteJ = tableClienteJur.getItems().get(tableClienteJur.getSelectionModel().getSelectedIndex());
            txtIdClienteJur.setText(String.valueOf(clienteJ.getId()));
            txtRazãoSocial.setText(clienteJ.getRs());
            txtCNPJ.setText(clienteJ.getCnpj());
            txtTelefoneClienteJur.setText(clienteJ.getTelefone());
            txtEmailClienteJur.setText(clienteJ.getEmail());
            txtEnderecoClienteJur.setText(clienteJ.getEndereco());
            txtBairroClienteJur.setText(clienteJ.getBairro());
            comboBoxEstadoClienteJur.setValue(clienteJ.getEstado());
            desativarbtsEditareExcluirF();
        }

    }

    @FXML
    void handlebuttonExcluirJ() throws Exception {
        if (tableClienteJur.getSelectionModel().isEmpty()) {
            mensagens.erro("Selecione um Cliente Jurídico para exclusão.");
            desativarbtsEditareExcluirJ();
        } else {
            clienteMJ clienteJ = tableClienteJur.getItems().get(tableClienteJur.getSelectionModel().getSelectedIndex());
            ControleDAO.getControleBanco().getClienteJDAO().excluirClienteJ(clienteJ);
            desativarbtsEditareExcluirJ();
            atualizarListaClienteJur();
            tableClienteJur.refresh();
            tableClienteJur.getSelectionModel().clearSelection();
        }

    }

    //----------Fim Cliente Jurídico
}
