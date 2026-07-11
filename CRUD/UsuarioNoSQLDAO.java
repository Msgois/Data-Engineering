package CRUD;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsuarioNoSQLDAO {
    private static MongoCollection<Document> getMongoColecao() {
        return Conexao.getMongoDatabase().getCollection("usuarios");
    }

    // Auxiliar para limpar chaves/colchetes caso venham formatados do JDBC/Postgres
    private static List<String> formatarLista(String texto) {
        if (texto == null || texto.isEmpty()) return new ArrayList<>();
        String limpo = texto.replaceAll("[\\{\\}\\[\\]]", "");
        return Arrays.asList(limpo.split(","));
    }

    // =========================================================================
    // 1. CREATE COMPLETO (Salva Usuário, Estudante e Vínculos juntos)
    // =========================================================================
    public static void inserirUsuarioNoSQL(Usuario usuario, Estudante estudante, List<Vinculo> vinculos) {
        try {
            MongoCollection<Document> colecao = getMongoColecao();
            
            List<String> emailsList = formatarLista(usuario.getEmail());
            List<String> telefonesList = formatarLista(usuario.getTelefone());

            // Cria o documento base do Usuário
            Document docUsuario = new Document("cpf", usuario.getCpf())
                    .append("nome", usuario.getNome())
                    .append("data_nascimento", usuario.getDataNascimento())
                    .append("email", emailsList)
                    .append("telefone", telefonesList)
                    .append("login", usuario.getLogin())
                    .append("senha", usuario.getSenha());

            // Se este usuário possuir dados de estudante, acopla o subdocumento
            if (estudante != null) {
                Document docEstudante = new Document("mat_estudante", estudante.getMatricula())
                        .append("MC", estudante.getMc())
                        .append("ano_ingresso", estudante.getAnoIngresso());

                // Se o estudante tiver vínculos com cursos, aninha a lista de vínculos
                if (vinculos != null && !vinculos.isEmpty()) {
                    List<Document> docVinculos = new ArrayList<>();
                    for (Vinculo v : vinculos) {
                        docVinculos.add(new Document("id_curso", v.getIdCurso())
                                .append("data_entrada", v.getDataEntrada())
                                .append("status", v.getStatus())
                                .append("data_saida", v.getDataSaida()));
                    }
                    docEstudante.append("vinculos", docVinculos);
                }
                
                // Embutindo o objeto estudante completo dentro do usuário
                docUsuario.append("perfil_estudante", docEstudante);
            }

            colecao.insertOne(docUsuario);
            System.out.println("Usuário completo (com estudante e vínculos) cadastrado com sucesso no MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao inserir Usuário no MongoDB: " + e.getMessage());
        }
    }

    // =========================================================================
    // 2. READ (Retorna todos os documentos complexos da coleção)
    // =========================================================================
    public static List<Document> listarTodosUsuariosNoSQL() {
        List<Document> usuarios = new ArrayList<>();
        try (MongoCursor<Document> cursor = getMongoColecao().find().iterator()) {
            while (cursor.hasNext()) {
                usuarios.add(cursor.next());
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar usuários do MongoDB: " + e.getMessage());
        }
        return usuarios;
    }

    // =========================================================================
    // 3. UPDATE (Atualiza os dados cadastrais principais do Usuário)
    // =========================================================================
    public static void atualizarNoSQL(Usuario usuario) {
        try {
            List<String> emailsList = formatarLista(usuario.getEmail());
            List<String> telefonesList = formatarLista(usuario.getTelefone());

            getMongoColecao().updateOne(
                Filters.eq("cpf", usuario.getCpf()),
                Updates.combine(
                    Updates.set("nome", usuario.getNome()),
                    Updates.set("data_nascimento", usuario.getDataNascimento()),
                    Updates.set("email", emailsList),
                    Updates.set("telefone", telefonesList),
                    Updates.set("login", usuario.getLogin()),
                    Updates.set("senha", usuario.getSenha())
                )
            );
            System.out.println("Dados cadastrais do usuário atualizados no MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário no MongoDB: " + e.getMessage());
        }
    }

    // =========================================================================
    // 3.1 UPDATE SUBDOCUMENTO (Atualiza o status de um vínculo específico)
    // =========================================================================
    public static void atualizarStatusVinculoNoSQL(long cpf, int idCurso, String novoStatus) {
        try {
            // O operador $. localiza e atualiza o elemento exato que deu match no filtro do array
            getMongoColecao().updateOne(
                Filters.and(Filters.eq("cpf", cpf), Filters.eq("perfil_estudante.vinculos.id_curso", idCurso)),
                Updates.set("perfil_estudante.vinculos.$.status", novoStatus)
            );
            System.out.println("Status do vínculo acadêmico modificado com sucesso no MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar status do vínculo no MongoDB: " + e.getMessage());
        }
    }

    // =========================================================================
    // 4. DELETE (Remove o Usuário e, consequentemente, tudo o que estiver dentro)
    // =========================================================================
    public static void deletarNoSQL(long cpf) {
        try {
            getMongoColecao().deleteOne(Filters.eq("cpf", cpf));
            System.out.println("Usuário e todo o seu histórico acadêmico removidos do MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao deletar usuário no MongoDB: " + e.getMessage());
        }
    }
}