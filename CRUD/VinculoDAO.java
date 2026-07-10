package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VinculoDAO {
    // 1. CREATE
    public static void inserirVinculo(Vinculo vinculo) {
        String sql = "INSERT INTO universidade.vinculo (mat_estudante, curso, data_entrada, status, data_saida) VALUES (?, ?, ?, ?::universidade.status_estudante, ?)";
        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vinculo.getMatricula());
            stmt.setInt(2, vinculo.getIdCurso());
            stmt.setDate(3, new java.sql.Date(vinculo.getDataEntrada().getTime()));
            stmt.setString(4, vinculo.getStatus());

            if (vinculo.getDataSaida() != null) {
                stmt.setDate(5, new java.sql.Date(vinculo.getDataSaida().getTime()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.executeUpdate();
            System.out.println("Vinculo inserido/Cadastrado com Sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao inserir Vinculo: " + e.getMessage());
        }
    }

    // 2. READ
    public List<Vinculo> listarTodosVinculo() {
        String sql = "SELECT * FROM universidade.vinculo";
        List<Vinculo> vinculos = new ArrayList<>();

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Vinculo vinculo = new Vinculo();
                vinculo.setIdVinculo(resultado.getInt("id_vinculo"));
                vinculo.setMatricula(resultado.getString("mat_estudante"));
                vinculo.setIdCurso(resultado.getInt("curso"));
                vinculo.setDataEntrada(resultado.getDate("data_entrada"));
                vinculo.setStatus(resultado.getString("status"));
                vinculo.setDataSaida(resultado.getDate("data_saida"));

                vinculos.add(vinculo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar vínculos: " + e.getMessage());
        }
        return vinculos;
    }

    // 3. UPDATE (Mudar status, ex: de Ativo para Trancado)
    public void atualizarStatus(int idVinculo, String novoStatus) {
        String sql = "UPDATE universidade.vinculo SET status = ?::universidade.status_estudante WHERE id_vinculo = ?";

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
