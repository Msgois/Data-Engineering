import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://postgres-mybase.c6pdocmdw1pd.us-east-1.rds.amazonaws.com:5432/CrudJava?currentSchema=universidade";
    private static String USUARIO = "professor";
    private static String SENHA = "professor";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado no projeto.", e);
        }
    }
}