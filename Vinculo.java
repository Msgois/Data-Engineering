import java.sql.*;

public class Vinculo {

    public static void inserirVinculo(String matricula, int curso, Date dataDeEntrada, String status,
            Date dataDeSaida) {
        String sql = "INSERT INTO universidade.vinculo (mat_estudante,curso, data_entrada, status,data_saida) VALUES (?,?,?,?::universidade.status_estudante,?)";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            stmt.setInt(2, curso);
            stmt.setDate(3, dataDeEntrada);
            stmt.setString(4, status);

            if (dataDeSaida != null) {
                stmt.setDate(5, dataDeSaida);
            } else {
                stmt.setNull(5, Types.DATE);
            }

            stmt.executeUpdate();
            System.out.println("Vinculo inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Vinculo: " + e.getMessage());
        }
    }

    public void listarTodosVinculo() {
        String sql = "SELECT * FROM universidade.vinculo";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                System.out.println("---------------------------------------------");
                System.out.println("IDdeVinculo: " + resultado.getInt("idVinculo"));
                System.out.println("Matricula: " + resultado.getString("mat_estudante"));
                System.out.println("IDdeCurso: " + resultado.getInt("curso"));
                System.out.println("DatadeEntrada" + resultado.getDate("data_entrada"));
                System.out.println("Status: " + resultado.getString("status"));
                System.out.println("DatadeSaida" + resultado.getDate("data_saida"));

            }
        } catch (SQLException e) {
            System.err.println("Erro ao Listar Todos os Vinculos:" + e.getMessage());
        }
    }
}
