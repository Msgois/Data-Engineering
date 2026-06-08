import java.sql.*;

public class Curso {

    public void inserirCurso(String nome, String grau, String turno, String campus, String nivel) {
        String sql = "INSERT INTO universidade.curso (nome,grau, turno, campus,nivel) VALUES (?,?::universidade.tipo_grau,?::universidade.tipo_turno,?,?::universidade.tipo_nivel)";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, grau);
            stmt.setString(3, turno);
            stmt.setString(4, campus);
            stmt.setString(5, nivel);

            stmt.executeUpdate();
            System.out.println("Curso inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Curso: " + e.getMessage());
        }
    }

    public void listarTodosCursos() {
        String sql = "SELECT * FROM universidade.curso";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                System.out.println("---------------------------------------------");
                System.out.println("IDdoCurso: " + resultado.getInt("idCurso"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Grau " + resultado.getString("grau"));
                System.out.println("Turno: " + resultado.getString("turno"));
                System.out.println("Campus: " + resultado.getString("campus"));
                System.out.println("Nivel: " + resultado.getString("nivel"));

            }
        } catch (SQLException e) {
            System.err.println("Erro ao Listar Todos os Cursos:" + e.getMessage());
        }
    }
}
