package CRUD;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class CursoNoSQLDAO {
    private static MongoCollection<Document> getMongoColecao() {
        return Conexao.getMongoDatabase().getCollection("cursos");
    }

    // =========================================================================
    // 1. CREATE
    // =========================================================================
    public static void inserirCursoNoSQL(Curso curso) {
        try {
            Document doc = new Document("id_curso", curso.getIdCurso())
                    .append("nome", curso.getNome())
                    .append("grau", curso.getGrau())
                    .append("turno", curso.getTurno())
                    .append("campus", curso.getCampus())
                    .append("nivel", curso.getNivel());

            getMongoColecao().insertOne(doc);
            System.out.println("Curso cadastrado com sucesso no MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao inserir Curso no MongoDB: " + e.getMessage());
        }
    }

    // =========================================================================
    // 2. READ
    // =========================================================================
    public static List<Document> listarTodosCursosNoSQL() {
        List<Document> cursos = new ArrayList<>();
        try (MongoCursor<Document> cursor = getMongoColecao().find().iterator()) {
            while (cursor.hasNext()) {
                cursos.add(cursor.next());
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar Cursos do MongoDB: " + e.getMessage());
        }
        return cursos;
    }

    // =========================================================================
    // 3. UPDATE
    // =========================================================================
    public static void atualizarCursoNoSQL(Curso curso) {
        try {
            getMongoColecao().updateOne(
                Filters.eq("id_curso", curso.getIdCurso()),
                Updates.combine(
                    Updates.set("nome", curso.getNome()),
                    Updates.set("grau", curso.getGrau()),
                    Updates.set("turno", curso.getTurno()),
                    Updates.set("campus", curso.getCampus()),
                    Updates.set("nivel", curso.getNivel())
                )
            );
            System.out.println("Dados do curso atualizados com sucesso no MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar curso no MongoDB: " + e.getMessage());
        }
    }

    // =========================================================================
    // 4. DELETE
    // =========================================================================
    public static void deletarCursoNoSQL(int idCurso) {
        try {
            getMongoColecao().deleteOne(Filters.eq("id_curso", idCurso));
            System.out.println("Curso removido com sucesso do MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar curso no MongoDB: " + e.getMessage());
        }
    }
}