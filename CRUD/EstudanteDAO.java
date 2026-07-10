package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudanteDAO {
    // 1. CREATE (Insert)
    public static void inserirEstudante(Estudante estudante) {
        String sql = "INSERT INTO universidade.estudante (mat_estudante,cpf, MC, ano_ingresso) VALUES (?,?,?,?)";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estudante.getMatricula());
            stmt.setLong(2, estudante.getCpf());
            stmt.setDouble(3, estudante.getMc());
            stmt.setInt(4, estudante.getAnoIngresso());

            stmt.executeUpdate();
            System.out.println("Estudante inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Estudante: " + e.getMessage());
        }
    }

    // 2. READ (Listar todos)
    public List<Estudante> listarTodosEstudantes() {

        String sql = "SELECT * FROM universidade.estudante";
        List<Estudante> listaEstudantes = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Estudante estudante = new Estudante();
                estudante.setMatricula(resultado.getString("mat_estudante"));
                estudante.setCpf(resultado.getLong("cpf"));
                estudante.setMc(resultado.getDouble("MC"));
                estudante.setAnoIngresso(resultado.getInt("ano_ingresso"));

                listaEstudantes.add(estudante);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao Listar todos os Estudantes: " + e.getMessage());
        }
        return listaEstudantes;
    }

    // 3. UPDATE
    public void atualizar(Estudante estudante) {
        String sql = "UPDATE universidade.estudante SET mat_estudante = ?, cpf = ?, MC = ?, ano_ingresso = ? WHERE id_estudante = ?";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estudante.getMatricula());
            stmt.setLong(2, estudante.getCpf());
            stmt.setDouble(3, estudante.getMc());
            stmt.setInt(4, estudante.getAnoIngresso());
            stmt.setInt(5, estudante.getIdEstudante());

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
