package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    // 1. CREATE (Insert)

    public static void inserirCurso(Curso curso) {
        String sql = "INSERT INTO universidade.curso (nome,grau, turno, campus,nivel) VALUES (?,?::universidade.tipo_grau,?::universidade.tipo_turno,?,?::universidade.tipo_nivel)";

        try (Connection conn = Conexao.getPostgresConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getGrau());
            stmt.setString(3, curso.getTurno());
            stmt.setString(4, curso.getCampus());
            stmt.setString(5, curso.getNivel());

            stmt.executeUpdate();
            System.out.println("Curso inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Curso: " + e.getMessage());
        }
    }

    // READ
    public List<Curso> listarTodosCursos() {
        String sql = "SELECT * FROM universidade.curso";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = Conexao.getPostgresConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Curso curso = new Curso();

                curso.setIdCurso(resultado.getInt("id_curso"));
                curso.setNome(resultado.getString("nome"));
                curso.setGrau(resultado.getString("grau"));
                curso.setTurno(resultado.getString("turno"));
                curso.setCampus(resultado.getString("campus"));
                curso.setNivel(resultado.getString("nivel"));

                cursos.add(curso);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao Listar Todos os cursos: " + e.getMessage());
        }
        return cursos;
    }

    // 3. UPDATE (Atualizar o nome do curso)
    public void atualizar(Curso curso) {
        String sql = "UPDATE universidade.curso SET nome = ?, grau = ?::universidade.tipo_grau, turno = ?::universidade.tipo_turno, campus = ?, nivel = ?::universidade.tipo_nivel WHERE id_curso = ?";

        try (Connection conn = Conexao.getPostgresConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getGrau());
            stmt.setString(3, curso.getTurno());
            stmt.setString(4, curso.getCampus());
            stmt.setString(5, curso.getNivel());
            stmt.setInt(6, curso.getIdCurso());

            stmt.executeUpdate();
            System.out.println("Curso atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    // 4. DELETE (Remover curso)
    public void deletar(int idCurso) {
        String sql = "DELETE FROM universidade.curso WHERE id_curso = ?";

        try (Connection conn = Conexao.getPostgresConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);

            stmt.executeUpdate();
            System.out.println("Curso removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar curso: " + e.getMessage());
        }
    }
}
