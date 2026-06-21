import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    // 1. CREATE (Insert)
    public void salvar(Curso curso) {
        String sql = "INSERT INTO universidade.curso (nome) VALUES (?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, curso.getNome());
            
            stmt.executeUpdate();
            System.out.println("Curso criado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar curso: " + e.getMessage());
        }
    }

    // 2. READ (Listar todos)
    public List<Curso> listarTodos() {
        String sql = "SELECT * FROM universidade.curso";
        List<Curso> cursos = new ArrayList<>();
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("id_curso"));
                c.setNome(rs.getString("nome"));
                cursos.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar cursos: " + e.getMessage());
        }
        return cursos;
    }

    // 2b. READ (Buscar por ID específico - Útil para o vínculo)
    public Curso buscarPorId(int idCurso) {
        String sql = "SELECT * FROM universidade.curso WHERE id_curso = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso c = new Curso();
                    c.setIdCurso(rs.getInt("id_curso"));
                    c.setNome(rs.getString("nome"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar curso por ID: " + e.getMessage());
        }
        return null;
    }

    // 3. UPDATE (Atualizar o nome do curso)
    public void atualizar(Curso curso) {
        String sql = "UPDATE universidade.curso SET nome = ? WHERE id_curso = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getIdCurso());
            
            stmt.executeUpdate();
            System.out.println("Curso atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    // 4. DELETE (Remover curso)
    public void deletar(int idCurso) {
        String sql = "DELETE FROM universidade.curso WHERE id_curso = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idCurso);
            
            stmt.executeUpdate();
            System.out.println("Curso removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar curso: " + e.getMessage());
        }
    }
}
