package banco.DAO;

import java.sql.SQLException;
import model.TipoUsuario;
import model.usuario;
import util.Criptografia;
import util.Tempo;

/**
 *
 * @author felli
 */
public class loginDAO extends DAO {
    public loginDAO(){
        super();
    }
    
    public boolean autenticarUsuario(String nome){
        try {
            String sql = "SELECT login from tb_usuario WHERE login=? and status = 1";
            stm = conector.prepareStatement(sql);
            stm.setString(1, nome);
            rs = stm.executeQuery();
            
            if(rs.next()){
                return nome.equals(rs.getString(1));
            }
                stm.close();
                rs.close();
        } catch (SQLException ex) {
           // Mensagem.
        }
        return false;
    }

    public boolean autenticarSenha(String nome, String senha){
        String key = Criptografia.converter(senha);
        try {
            String sql = "SELECT login from tb_usuario WHERE login=? AND senha =?";
            stm = conector.prepareStatement(sql);
            stm.setString(1, nome);
            stm.setString(2, key);
            rs = stm.executeQuery();
            
            while (rs.next()){
                return rs.getString(1).equals(nome)&& rs.getString(2).equals(key);
            }
            stm.close();
            rs.close();
            
        } catch (SQLException ex) {
           // Mensagem.
        }
        return false;
    }
    
    public usuario usuarioLogado(String login) {

        usuario user = null;

        try {
            String sql = "SELECT usuario.id_usuario, usuario.nome, usuario.login, usuario.senha, usuario.email, usuario.status, usuario.data_criacao, usuario.descricao, tipo.id_tipo_usuario, tipo.nome "
                    + "FROM tb_usuario AS usuario , tb_tipo_usuario AS tipo "
                    + "WHERE usuario.login=? "
                    + "AND tipo.id_tipo_usuario = usuario.fk_tipo_usuario";

            stm = conector.prepareStatement(sql);
            stm.setString(1, login);
            rs = stm.executeQuery();

            while (rs.next()) {
                TipoUsuario tipo = new TipoUsuario(rs.getInt(9), rs.getString(10));
                user = new usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6) == 1 ? true : false, null, rs.getString(8), tipo);
                user.setDataCriacao(Tempo.toDate(rs.getTimestamp(7)));
            }
            stm.close();
            rs.close();

        } catch (SQLException ex) {
            //Mensagem.erro("Erro ao consultar usuário logado na base de dados! \n" + ex);
        }

        return user;
    }
}
