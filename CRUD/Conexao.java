package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

/* 
 * A classe Conexao centraliza e gerencia a comunicação da aplicação com os bancos de dados PostgreSQL e MongoDB. 
 * Ela armazena as credenciais estáticas de acesso para a AWS e garante que apenas uma conexão seja mantida ativa.
 */
public class Conexao {
    /*
     * Atributos estáticos com a URL de conexão, usuário e senha para o banco de
     * dados relacional PostgreSQL na AWS.
     */
    private static final String URL = "jdbc:postgresql://postgres-mybase.c6pdocmdw1pd.us-east-1.rds.amazonaws.com:5432/CrudJava?currentSchema=universidade";
    private static final String USUARIO = "professor";
    private static final String SENHA = "professor";
    /*
     * Variável estática para armazenar a instância única da conexão com o
     * PostgreSQL.
     */
    private static Connection conexao = null;

    /*
     * Atributos estáticos com a URL de conexão, nome do banco e variáveis de
     * instância para o banco de dados NoSQL MongoDB na AWS.
     */
    private static final String URL_MONGO = "mongodb://professor:professor@3.85.189.11:27017/universidade?authSource=admin";
    private static final String BANCO_MONGO_NOME = "universidade";
    private static MongoClient mongoClient = null;
    private static MongoDatabase databaseMongo = null;

    /*
     * Método responsável por estabelecer e retornar a conexão com o PostgreSQL
     * utilizando o driver JDBC.
     * Ele implementa uma verificação para criar a conexão apenas se ela ainda não
     * existir ou se estiver fechada.
     */
    public static Connection getPostgresConnection() throws SQLException {
        try {
            // Só abre se não existir ou tiver sido fechada
            if (conexao == null || conexao.isClosed()) {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            }
            return conexao;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado no projeto.", e);
        }
    }

    /*
     * Método responsável por estabelecer e retornar a conexão com o MongoDB.[cite:
     * 1]
     * Caso o banco não esteja conectado, ele instancia o MongoClient e recupera o
     * banco de dados especificado.
     */
    public static MongoDatabase getMongoDatabase() {
        if (databaseMongo == null) {
            try {
                mongoClient = MongoClients.create(URL_MONGO);
                databaseMongo = mongoClient.getDatabase(BANCO_MONGO_NOME);
                System.out.println("Conexão com MongoDB na AWS aberta com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            }
        }
        return databaseMongo;
    }

    // NOVO MÉTODO PARA FECHAR
    /*
     * Método utilitário responsável por encerrar de forma segura as conexões ativas
     * tanto com o PostgreSQL quanto com o MongoDB.
     * Garante a liberação de recursos do sistema e reseta as variáveis de estado
     * para nulo após o fechamento.
     */
    public static void fecharConexao() {
        if (conexao != null) {
            try {
                if (!conexao.isClosed()) {
                    conexao.close();
                    System.out.println("Conexão com PostgreSQL (AWS) encerrada com segurança.");
                }
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            } finally {
                conexao = null; // Garante que limpa a variável
            }
        }

        if (mongoClient != null) {
            try {
                mongoClient.close();
                System.out.println("Conexão com MongoDB (AWS) encerrada com segurança.");
            } catch (Exception e) {
                System.err.println("Erro ao fechar MongoDB: " + e.getMessage());
            } finally {
                mongoClient = null; // Garante que limpa o cliente
                databaseMongo = null; // Reseta o banco para poder reabrir se precisar
            }
        }
    }
}