import java.sql.*;

public class Conexao {
    /* Falta resolver isso do AWS */
    private static String URL = "ex";
    private static String USUARIO = "ex";
    private static String SENHA = "ex";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
