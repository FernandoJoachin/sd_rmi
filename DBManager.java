import java.sql.*;

public final class DBManager {
    private static DBManager _instance = null;
    private Connection _con = null;

    public DBManager() {
        _con = getMySQLConnection();
    }

    public static synchronized DBManager getInstance() {
        if (_instance == null) {
            _instance = new DBManager();
        }
        return _instance;
    }

    public Connection getConnection() { return _con; }

    private static Connection getMySQLConnection() {

        Connection con = null;
        
        try {
            String url = "jdbc:mysql://127.0.0.1/Province?user=rtuser&password=123";
            System.out.println("Intentando conectar a MySQL en: " + url);
    
            con = DriverManager.getConnection(url);
            System.out.println("✅ Conexión a MySQL exitosa.");
        } catch (SQLException se) {
            System.out.println("❌ Error de conexión a MySQL: " + se.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error general: " + e.getMessage());
        }
        return con;
    }
    
    
}
