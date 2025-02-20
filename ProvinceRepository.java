import java.sql.*;
import java.util.*;

public class ProvinceRepository {
    public static int save(Province p) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            if (con == null) {  // <-- Depuración
                System.out.println("Error: La conexión a la base de datos es nula.");
                return iRet;
            }
    
            System.out.println("Insertando ciudad: " + p.getName() + " con nombre corto: " + p.getShortName());
    
            String SQL = "INSERT INTO Province (ShortName, Name) VALUES(?, ?)";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, p.getShortName());
            pstmt.setString(2, p.getName());
    
            iRet = pstmt.executeUpdate();
            System.out.println("Inserción exitosa.");
            
            pstmt.close();
        } catch (SQLException se) {
            System.out.println("Error SQL: " + se.getMessage());
        } catch (Exception e) {
            System.out.println("Error general: " + e.getMessage());
        }
        return iRet;
    }
    
    
    
    public static int update(Province p) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "UPDATE Province SET ShortName=?, Name=? WHERE Id=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, p.getShortName());
            pstmt.setString(2, p.getName());
            pstmt.setInt(3, p.getId());

            iRet = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return iRet;
    }
    
    public static int delete(Province p) {
        int iRet = -1;
        try {
            Connection con = DBManager.getInstance().getConnection();
            String SQL = "DELETE FROM Province WHERE Id=?";
            PreparedStatement pstmt = con.prepareStatement(SQL);
            pstmt.setInt(1, p.getId());

            iRet = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return iRet;
    }
    
    public static void deleteAll() {
        Connection con = DBManager.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            String SQL = "DELETE FROM Province";
            PreparedStatement pstmt = con.prepareStatement(SQL);

            pstmt.executeUpdate();
            con.commit();
        } catch (SQLException se) {
            try {
                con.rollback();
            } catch (SQLException ise) {
                System.out.println(ise);
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException fse) {
                System.out.println(fse);
            }
        }
    }
    
    public static ArrayList<Province> findAll() {
        ArrayList<Province> arr = new ArrayList<>();
        try {
            String QRY = "SELECT * FROM Province ORDER BY Id";
            Connection con = DBManager.getInstance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QRY);

            while (rs.next()) {
                Province p = new Province();
                p.setId(rs.getInt("Id"));
                p.setShortName(rs.getString("ShortName"));
                p.setName(rs.getString("Name"));
                arr.add(p);
            }

            stmt.close();
        } catch (SQLException se) {
            System.out.println(se);
        }
        return arr;
    }
    
    public static ArrayList<Province> findByName(String name) {
        ArrayList<Province> arr = new ArrayList<>();
        try {
            String QRY = "SELECT * FROM Province WHERE name LIKE(?) ORDER BY id";
            Connection con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(QRY);
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Province p = new Province();
                p.setId(rs.getInt("Id"));
                p.setShortName(rs.getString("ShortName"));
                p.setName(rs.getString("Name"));
                arr.add(p);
            }

            pstmt.close();
        } catch (SQLException se) {
        System.out.println(se);
        }
        return arr;
    }
}
