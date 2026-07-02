import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // 1. CREATE (Insert)
    public static void inserirUsuario(Usuario usuario) {
        String sql = "INSERT INTO universidade.usuario (cpf,nome, data_nascimento, email, telefone, login,senha) VALUES (?,?,?,?,?,?,?)";
        /*
         * Como o email e o telefone são Arrays de VARCHAR, foi necessário criar essa
         * Formatação para que sejam lidos corretamente
         */
        String emailFormatado = "{" + usuario.getEmail() + "}";
        String telefoneFormatado = "{" + usuario.getTelefone() + "}";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setDate(3, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.setString(4, emailFormatado);
            stmt.setString(5, telefoneFormatado);
            stmt.setString(6, usuario.getLogin());
            stmt.setString(7, usuario.getSenha());

            stmt.executeUpdate();
            System.out.println("Usuario inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Usuario: " + e.getMessage());
        }
    }

    // 2. READ (Listar todos)
    public List<Usuario> listarTodosUsuarios() {
        String sql = "SELECT * FROM universidade.usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Usuario usuario = new Usuario();
                usuario.setCpf(resultado.getLong("cpf"));
                usuario.setNome(resultado.getString("nome"));
                usuario.setDataNascimento(resultado.getDate("data_nascimento"));
                usuario.setEmail(resultado.getString("email"));
                usuario.setTelefone(resultado.getString("telefone"));
                usuario.setLogin(resultado.getString("login"));
                usuario.setSenha(resultado.getString("senha"));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao Listar Todos os Usuarios:" + e.getMessage());
        }
        return usuarios;
    }

    // 3. UPDATE
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE universidade.usuario SET nome = ?, data_nascimento = ?, email = ?, telefone = ?, login = ?, senha = ? WHERE cpf = ?";
        String emailFormatado = "{" + usuario.getEmail() + "}";
        String telefoneFormatado = "{" + usuario.getTelefone() + "}";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.setString(3, emailFormatado);
            stmt.setString(4, telefoneFormatado);
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenha());
            stmt.setLong(7, usuario.getCpf());

            stmt.executeUpdate();
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    // 4. DELETE
    public void deletar(long cpf) {
        String sql = "DELETE FROM universidade.usuario WHERE cpf = ?";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, cpf);
            stmt.executeUpdate();
            System.out.println("Usuário removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}