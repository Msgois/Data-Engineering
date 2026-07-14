package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* 
 * Classe DAO (Data Access Object) encarregada de abstrair as operações de persistência e acesso a dados para a entidade Curso no PostgreSQL.
 */
public class CursoDAO {
    // 1. CREATE (Insert)

    /*
     * Método para inserir um novo registro na tabela 'curso'.
     * Ele utiliza PreparedStatement para prevenção de SQL Injection e realiza
     * casting em tipos customizados (ENUMS) do banco de dados (ex:
     * ?::universidade.tipo_grau).
     */
    public static void inserirCurso(Curso curso) {
        String sql = "INSERT INTO universidade.curso (nome,grau, turno, campus,nivel) VALUES (?,?::universidade.tipo_grau,?::universidade.tipo_turno,?,?::universidade.tipo_nivel)";

        // Adicionado o Statement.RETURN_GENERATED_KEYS aqui na abertura do
        // PreparedStatement
        /*
         * Bloco try-with-resources que gerencia a conexão e requisita a devolução do ID
         * gerado pelo banco.
         */
        try (Connection conn = Conexao.getPostgresConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getGrau());
            stmt.setString(3, curso.getTurno());
            stmt.setString(4, curso.getCampus());
            stmt.setString(5, curso.getNivel());

            stmt.executeUpdate();

            // Trecho novo: Recupera o ID gerado pelo auto-incremento do Postgres
            /*
             * Recupera o ResultSet contendo as chaves geradas e atualiza o objeto curso em
             * memória com o identificador definido pelo banco de dados.
             */
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    // Atualiza o objeto Curso com o ID real gerado na AWS
                    curso.setIdCurso(idGerado);
                }
            }

            System.out.println("Curso inserido/Cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir Curso: " + e.getMessage());
        }
    }

    // READ
    /*
     * Método para buscar e listar todos os cursos existentes na tabela
     * correspondente.
     * Retorna uma lista de objetos 'Curso' para serem utilizados pela
     * aplicação.
     */
    public List<Curso> listarTodosCursos() {
        String sql = "SELECT * FROM universidade.curso";
        List<Curso> cursos = new ArrayList<>();

        try (Connection conn = Conexao.getPostgresConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()) {

            /*
             * Itera pelos resultados da query, extrai as colunas e popula objetos da classe
             * Curso.
             */
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
    /*
     * Executa a atualização (UPDATE) de um curso previamente existente na tabela
     * baseado no ID do curso.
     * Utiliza casts para ENUMs no SQL para alinhar ao design do esquema
     * PostgreSQL.
     */
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
    /*
     * Executa a remoção (DELETE) física de um registro de curso utilizando sua
     * chave primária (id_curso).
     */
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