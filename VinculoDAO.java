import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VinculoDAO {
    // 1. CREATE
    public void salvar(Vinculo vinculo) {
        String sql = "INSERT INTO universidade.vinculo (id_estudante, id_curso, status) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, vinculo.getIdEstudante());
            stmt.setInt(2, vinculo.getIdCurso());
            stmt.setString(3, vinculo.getStatus() != null ? vinculo.getStatus() : "Ativo");
            
            stmt.executeUpdate();
            System.out.println("Vínculo acadêmico registrado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar vínculo: " + e.getMessage());
        }
    }

    // 2. READ
    public List<Vinculo> listarTodos() {
        String sql = "SELECT * FROM universidade.vinculo";
        List<Vinculo> vinculos = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Vinculo v = new Vinculo();
                v.setIdVinculo(rs.getInt("id_vinculo"));
                v.setIdEstudante(rs.getInt("id_estudante"));
                v.setIdCurso(rs.getInt("id_curso"));
                v.setStatus(rs.getString("status"));
                vinculos.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar vínculos: " + e.getMessage());
        }
        return vinculos;
    }

    // 3. UPDATE (Mudar status, ex: de Ativo para Trancado)
    public void atualizarStatus(int idVinculo, String novoStatus) {
        String sql = "UPDATE universidade.vinculo SET status = ? WHERE id_vinculo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, novoStatus);
            stmt.setInt(2, idVinculo);
            
            stmt.executeUpdate();
            System.out.println("Status do vínculo atualizado!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar status do vínculo: " + e.getMessage());
        }
    }

    // 4. DELETE
    public void deletar(int idVinculo) {
        String sql = "DELETE FROM universidade.vinculo WHERE id_vinculo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idVinculo);
            stmt.executeUpdate();
            System.out.println("Vínculo removido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar vínculo: " + e.getMessage());
        }
    }
}
