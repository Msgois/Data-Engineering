import java.sql.*;

public class Estudante {
    public static void inserirEstudante(String matricula, long cpf, double MC, int anoDeIngresso) {
        String sql = "INSERT INTO universidade.estudante (mat_estudante,cpf, MC, ano_ingresso) VALUES (?,?,?,?)";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.setLong(2, cpf);
            stmt.setDouble(3, MC);
            stmt.setInt(4, anoDeIngresso);

            stmt.executeUpdate();
            System.out.println("Estudante inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Estudante: " + e.getMessage());
        }
    }

    public void listarTodosEstudantes() {
        String sql = "SELECT * FROM universidade.estudante";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                System.out.println("---------------------------------------------");
                System.out.println("Matricula: " + resultado.getString("mat_estudante"));
                System.out.println("Cpf: " + resultado.getLong("cpf"));
                System.out.println("MC: " + resultado.getDouble("MC"));
                System.out.println("AnodeIngresso: " + resultado.getInt("ano_ingresso"));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao Listar Todos os Estudantes:" + e.getMessage());
        }
    }
}
