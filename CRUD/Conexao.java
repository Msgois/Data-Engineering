package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Conexao {
    private static final String URL = "jdbc:postgresql://postgres-mybase.c6pdocmdw1pd.us-east-1.rds.amazonaws.com:5432/CrudJava?currentSchema=universidade";
    private static final String USUARIO = "professor";
    private static final String SENHA = "professor";
    private static Connection conexao = null;

    private static final String URL_MONGO = "mongodb://professor:professor@13.217.116.158:27017/universidade?authSource=admin";
    private static final String BANCO_MONGO_NOME = "universidade";
    private static MongoClient mongoClient = null;
    private static MongoDatabase databaseMongo = null;

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
                mongoClient = null;     // Garante que limpa o cliente
                databaseMongo = null;   // Reseta o banco para poder reabrir se precisar
            }
        }
    }
}