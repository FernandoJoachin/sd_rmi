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
            String strCon = "jdbc:mysql://localhost:3306/Province?user=root&password=&useSSL=false&serverTimezone=America/Mexico_City";
            con = DriverManager.getConnection(strCon, "root", "");
            if (con != null) {
                System.out.println("¡Conexión exitosa a MySQL!");
            } else {
                System.out.println("Error: la conexión a MySQL es NULL.");
            }
        } catch (SQLException se) {
            System.out.println(se);
            se.printStackTrace();
        }
        return con;
    }
}
