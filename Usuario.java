import java.sql.*;

public class Usuario {

    public void inserirUsuario(long cpf, String nome, Date dataNascimento, String email, String telefone, String login,
            String senha) {
        String sql = "INSERT INTO universidade.usuario (cpf,nome, data_nascimento, email, telefone, login,senha) VALUES (?,?,?,?,?,?,?)";
        /*
         * Como o email e o telefone são Arrays de VARCHAR, foi necessário criar essa
         * Formatação para que sejam lidos corretamente
         */
        String emailFormatado = "{" + email + "}";
        String telefoneFormatado = "{" + telefone + "}";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, cpf);
            stmt.setString(2, nome);
            stmt.setDate(3, dataNascimento);
            stmt.setString(4, emailFormatado);
            stmt.setString(5, telefoneFormatado);
            stmt.setString(6, login);
            stmt.setString(7, senha);

            stmt.executeUpdate();
            System.out.println("Usuario inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Usuario: " + e.getMessage());
        }
    }

    public void listarTodosUsuarios() {
        String sql = "SELECT * FROM universidade.usuario";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                System.out.println("---------------------------------------------");
                System.out.println("CPF: " + resultado.getLong("cpf"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("DataDeNascimento: " + resultado.getDate("data_nascimento"));
                System.out.println("Email: " + resultado.getString("email"));
                System.out.println("Telefone: " + resultado.getString("telefone"));
                System.out.println("Login: " + resultado.getString("login"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao Listar Todos os Usuarios:" + e.getMessage());
        }
    }
}