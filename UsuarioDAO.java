import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    // 1. CREATE (Insert)
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO universidade.usuario (cpf, nome, data_nascimento, email, telefone, login, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setDate(3, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefone());
            stmt.setString(6, usuario.getLogin());
            stmt.setString(7, usuario.getSenha());
            
            stmt.executeUpdate();
            System.out.println("Usuário salvo com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    // 2. READ (Listar todos)
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM universidade.usuario";
        List<Usuario> usuarios = new ArrayList<>();
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setCpf(rs.getLong("cpf"));
                u.setNome(rs.getString("nome"));
                u.setDataNascimento(rs.getDate("data_nascimento"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    // 3. UPDATE
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE universidade.usuario SET nome = ?, data_nascimento = ?, email = ?, telefone = ?, login = ?, senha = ? WHERE cpf = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setDate(2, new java.sql.Date(usuario.getDataNascimento().getTime()));
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefone());
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