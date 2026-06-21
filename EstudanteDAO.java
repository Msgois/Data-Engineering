import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO {
    // 1. CREATE (Insert)
    public void salvar(Estudante estudante) {
        // Não incluímos o id_estudante aqui porque ele é SERIAL no banco
        String sql = "INSERT INTO universidade.estudante (cpf_usuario, matricula) VALUES (?, ?)";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, estudante.getCpfUsuario());
            stmt.setString(2, estudante.getMatricula());
            
            stmt.executeUpdate();
            System.out.println("Estudante cadastrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar estudante: " + e.getMessage());
        }
    }

    // 2. READ (Listar todos)
    public List<Estudante> listarTodos() {
        String sql = "SELECT * FROM universidade.estudante";
        List<Estudante> estudantes = new ArrayList<>();
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Estudante e = new Estudante();
                e.setIdEstudante(rs.getInt("id_estudante"));
                e.setCpfUsuario(rs.getLong("cpf_usuario"));
                e.setMatricula(rs.getString("matricula"));
                estudantes.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar estudantes: " + e.getMessage());
        }
        return estudantes;
    }

    // 3. UPDATE
    public void atualizar(Estudante estudante) {
        String sql = "UPDATE universidade.estudante SET cpf_usuario = ?, matricula = ? WHERE id_estudante = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, estudante.getCpfUsuario());
            stmt.setString(2, estudante.getMatricula());
            stmt.setInt(3, estudante.getIdEstudante()); // Identifica qual estudante atualizar pelo ID
            
            stmt.executeUpdate();
            System.out.println("Dados do estudante atualizados com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estudante: " + e.getMessage());
        }
    }

    // 4. DELETE
    public void deletar(int idEstudante) {
        String sql = "DELETE FROM universidade.estudante WHERE id_estudante = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEstudante);
            stmt.executeUpdate();
            System.out.println("Estudante removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar estudante: " + e.getMessage());
        }
    }
}
