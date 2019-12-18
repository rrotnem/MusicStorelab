package music.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void freeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
